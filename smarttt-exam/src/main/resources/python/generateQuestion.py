import re
import json
import random
import pandas as pd
from time import sleep
from openai import OpenAI
import sys
import os
import time
import io

# 强制标准输出使用 utf-8
sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8', write_through=True)
sys.stderr = io.TextIOWrapper(sys.stderr.buffer, encoding='utf-8', write_through=True)


def generate_questions_json(client, kwa_list_str, seeds, question_count, max_retries=3):
    """调用 AI 生成题目并解析 JSON"""
    seeds_formatted = "\n".join([f"- {s}" for s in seeds])

    # 按40%/40%/20%分配三个难度等级的题目数
    d1 = max(1, round(question_count * 0.4))
    d2 = max(1, round(question_count * 0.4))
    d3 = max(1, question_count - d1 - d2)
    # 每个等级客观/主观比例: 简单3:1, 中等1:1, 困难1:1
    d1_obj, d1_sub = max(1, round(d1 * 0.75)), d1 - max(1, round(d1 * 0.75))
    d2_obj, d2_sub = max(1, d2 // 2), d2 - max(1, d2 // 2)
    d3_obj, d3_sub = max(1, d3 // 2), d3 - max(1, d3 // 2)

    prompt = f"""你是一位计算机教学命题专家。请根据提供的 **KWA（知识-能力）组合**，参考 **种子题目** 的风格，生成 {question_count} 道高质量的题目。

# 参考种子题目
{seeds_formatted}

# 本次命题 KWA 组合
{kwa_list_str}

# 命题规则
1. **KWA 覆盖**：每道题目需围绕给定的KWA进行命题。
2. **难度与题型分配**：
    - 难度 1（基础概念）：{d1_obj} 道**客观题**，{d1_sub} 道**主观题**
    - 难度 2（综合分析）：{d2_obj} 道**客观题**，{d2_sub} 道**主观题**
    - 难度 3（原理应用）：{d3_obj} 道**客观题**，{d3_sub} 道**主观题**
3. **题型说明**：
    - type=1 **单选题**：4个选项(A/B/C/D)，只有一个正确答案，题干中列出选项
    - type=2 **多选题**：4个以上选项，至少2个正确答案，题干中列出选项
    - type=3 **判断题**：题干为陈述句，要求判断正确或错误
    - type=4 **填空题**：题干中留空(用______表示)，要求填入关键词或计算结果
    - type=5 **简答题**：问法需灵活多变（简答、分析、设计等），不设选项
4. **客观题**指type=1/2/3/4，**主观题**指type=5
5. **语言风格**：严谨、专业，符合高校考试规范。

# 输出要求
1. **严格 JSON 格式**：必须严格以 JSON 数组格式输出，不得包含任何 Markdown 代码块标识或解释文字。
2. **字段规格**：
    - `question`: 题目文本内容（选择题和判断题需在题干中包含选项）。
    - `level`: 难度分级，必须为整数：1, 2, 3。
    - `type`: 题型标签，整数：**1=单选, 2=多选, 3=判断, 4=填空, 5=简答**。
    - `score`: 题目分值，客观题3-5分，主观题8-15分
    - `KWA`: 字符串数组，如 ["KWA1", "KWA2"],严格保证这里的KWA是从我前面给出的KWA中挑选的。
3. **JSON 结构示例**：
[
  {{
    "question": "学生和课程之间的联系类型是（）\\nA. 一对一  B. 一对多  C. 多对多  D. 无关联",
    "level": 1,
    "type": 1,
    "score": 3,
    "KWA": ["数据库-基础知识"]
  }},
  {{
    "question": "以下哪些是数据库的ACID特性（）\\nA. 原子性  B. 一致性  C. 隔离性  D. 持久性  E. 分布性",
    "level": 2,
    "type": 2,
    "score": 4,
    "KWA": ["数据库-基础知识"]
  }}
]
"""

    for attempt in range(max_retries):
        try:
            print(f"正在进行第 {attempt + 1} 次 AI 请求...")
            response = client.chat.completions.create(
                temperature=1,
                top_p=1,
                max_tokens=16384,
                frequency_penalty=0,
                presence_penalty=0,
                model=model,
                messages=[
                    {"role": "system", "content": "你是一个只输出 JSON 格式数据的命题助手。"},
                    {"role": "user", "content": prompt}
                ]
            )
            content = response.choices[0].message.content
            # 兼容处理：剔除思考过程和 Markdown 标签
            content = re.sub(r"<think>.*?</think>", "", content, flags=re.DOTALL).strip()
            content = re.sub(r"```json\s*|```\s*", "", content).strip()

            # 多策略解析AI返回的JSON（AI经常产出不合法的转义符）
            for parse_attempt in range(3):
                try:
                    return json.loads(content)
                except json.JSONDecodeError:
                    if parse_attempt == 0:
                        # 策略1: 修复\后跟非法字符（包括\u后非4位hex）
                        content = re.sub(r'\\(?!["\\/bfnrtu])', r'\\\\', content)
                        content = re.sub(r'(\\u[0-9a-fA-F]{0,3})([^0-9a-fA-F]|$)', r'\\\\u\1\2', content)
                    elif parse_attempt == 1:
                        # 策略2: 把所有\都双写
                        content = content.replace('\\', '\\\\')
                        content = content.replace('\\\\"', '\\"').replace('\\\\/', '\\/')
                        content = content.replace('\\\\n', '\\n').replace('\\\\t', '\\t')
                        content = content.replace('\\\\r', '\\r')
            return None
        except Exception as e:
            print(f"尝试失败 (重试中...): {e}", file=sys.stderr)
            sleep(2)
    return None


if __name__ == "__main__":
    # 1. 路径配置
    base_dir = "src/main/resources/python"
    input_dir = os.path.join(base_dir, "inputFiles")
    output_dir = os.path.join(base_dir, "outputFiles")
    tmp_dir = os.path.join(output_dir, "tmp")

    # 2. 自动创建目录
    for folder in [input_dir, output_dir, tmp_dir]:
        if not os.path.exists(folder):
            os.makedirs(folder)
            print(f"已创建目录: {folder}")

    # 3. 参数接收: sys.argv[1]=KWA JSON文件路径, sys.argv[2]=输出文件名(可选)
    if len(sys.argv) < 2:
        print("错误: 未接收到 KWA 参数文件路径", file=sys.stderr)
        sys.exit(1)

    json_file_path = sys.argv[1]
    output_name = sys.argv[2] if len(sys.argv) > 2 else "newQuestion"
    print(f"脚本启动成功，KWA文件: {json_file_path}, 输出前缀: {output_name}")

    # 4. 从文件解析KWA JSON并格式化
    try:
        with open(json_file_path, 'r', encoding='utf-8') as f:
            kwa_data = json.load(f)
        kwa_list = kwa_data.get("kwaList", [])
        question_count = kwa_data.get("questionCount", 20)
        # 格式化为可读文本给AI
        kwa_lines = []
        for item in kwa_list:
            kn = item.get("kwaName", "")
            kwa_lines.append(f"  - {kn}")
        kwa_formatted = "\n".join(kwa_lines)
        print(f"共解析到 {len(kwa_list)} 个KWA组合，目标生成 {question_count} 道题目")
    except json.JSONDecodeError as e:
        print(f"解析KWA JSON失败: {e}", file=sys.stderr)
        sys.exit(1)

    # 5. 初始化 OpenAI 客户端（配置由Java端传入）
    api_key = kwa_data.get("apiKey", "")
    api_url = kwa_data.get("apiUrl", "")
    model = kwa_data.get("model", "qwen3-235b")
    client = OpenAI(api_key=api_key, base_url=api_url)
    print(f"LLM配置: url={api_url}, model={model}")

    try:
        # 加载种子题目
        seed_path = os.path.join(input_dir, "种子题目.xlsx")
        if not os.path.exists(seed_path):
            print(f"错误: 找不到种子题目文件 {seed_path}", file=sys.stderr)
            sys.exit(1)

        seed_df = pd.read_excel(seed_path)
        seed_questions = seed_df['question'].tolist()

        # 6. 执行 AI 生成
        current_seeds = random.sample(seed_questions, min(3, len(seed_questions)))
        print("正在连接大模型生成题目...")
        start_time = time.time()
        results = generate_questions_json(client, kwa_formatted, current_seeds, question_count)
        duration = time.time() - start_time

        if results and isinstance(results, list):
            print(f"生成 {len(results)} 道题目，耗时 {duration:.1f} 秒")

            # 保存为 JSON（主输出，Java从此读取，文件名带唯一前缀防并发冲突）
            json_path = os.path.join(output_dir, f"{output_name}.json")
            with open(json_path, 'w', encoding='utf-8') as f:
                json.dump(results, f, ensure_ascii=False, indent=2)
            print(f"JSON 已保存至: {json_path}")

            # 同时保存为 Excel 备份
            try:
                excel_path = os.path.join(output_dir, f"{output_name}.xlsx")
                pd.DataFrame(results).to_excel(excel_path, index=False)
                print(f"Excel 备份已保存至: {excel_path}")
            except Exception as e:
                print(f"Excel 备份失败（JSON已正常生成）: {e}")

            print("脚本任务执行成功。")
            sys.exit(0)
        else:
            print("解析失败: AI 返回内容不符合 JSON 列表要求。", file=sys.stderr)
            sys.exit(1)

    except Exception as e:
        print(f"脚本运行中发生异常: {e}", file=sys.stderr)
        sys.exit(1)
