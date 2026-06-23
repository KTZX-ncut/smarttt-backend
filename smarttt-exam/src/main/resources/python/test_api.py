"""测试 NCUT 校内 GPU API 连接"""
from openai import OpenAI

BASE_URL = "https://gpu.ncut.edu.cn/v1"
KEYS = [("new", "gpustack_76a49eb69542885e_765fffd37f5ff8a73ddf78b8e08e50f1")]

for name, key in KEYS:
    print(f"\n{'='*50}")
    print(f"Testing: {name}")
    print(f"{'='*50}")

    client = OpenAI(base_url=BASE_URL, api_key=key)

    try:
        response = client.chat.completions.create(
            temperature=1,
            top_p=1,
            max_tokens=100,
            frequency_penalty=0,
            presence_penalty=0,
            model="qwen3-235b",
            messages=[{"role": "user", "content": "Hello, reply with just 'OK'."}],
        )
        print(f"✅ SUCCESS: {response.choices[0].message.content}")
    except Exception as e:
        print(f"❌ FAILED: {e}")
