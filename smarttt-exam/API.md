# smarttt-exam 模块接口文档

## 基础信息

| 项目 | 值 |
|------|-----|
| 端口 | 8085 |
| 前缀 | `/api` |
| 鉴权 | Header: `token` = JWT（从 admin 模块 `/api/login` 获取） |

---

## 一、AI 出题

### 1. 获取课程 KWA 列表

```
GET /api/exam/questiongen/kwa?courseId=xxx
```

**响应：**
```json
{
  "code": 200,
  "data": [
    {
      "keywordId": "xxx",
      "keywordName": "求导",
      "abilityId": "xxx",
      "abilityName": "能力1",
      "kwaId": "xxx",
      "kwaName": "求导-能力1"
    }
  ]
}
```

---

### 2. AI 生成题目

```
POST /api/exam/questiongen/generate
Content-Type: application/json
```

| 字段 | 必填 | 默认值 | 说明 |
|------|------|--------|------|
| courseId | 是 | - | 课程 ID |
| selectedKwaIds | 是 | - | 选中的 KWA ID 数组 |
| questionCount | 否 | 20 | 生成题目数量 |
| classroomId | 否 | - | 课堂 ID |

**请求示例：**
```json
{
  "courseId": "xxx",
  "selectedKwaIds": ["kwaId1", "kwaId2"],
  "questionCount": 20
}
```

**响应：**
```json
{
  "code": 200,
  "msg": "成功生成 20 道题目",
  "data": null
}
```

**入库表：** `tm_testquelib`（题目正文、难度、题型）、`tm_testquelib_kwa`（题目-KWA 关联）

**注意：** 需要 Python 环境 + 豆包 API，耗时约 10-30 秒。

---

### 3. 查询题目列表

```
GET /api/exam/questiongen/list?courseId=xxx
```

**响应示例：**
```json
{
  "code": 200,
  "data": [
    {
      "id": "1623131586-xxx",
      "content": "已知函数$h(x)=\\frac{\\sin x}{x}$，求一阶导数",
      "title": "已知函数$h(x)=\\frac{\\sin x}{x}$...",
      "questionTypeId": "0205",
      "difficultyLevel": 2.0,
      "status": 0,
      "createTime": "2026-06-22 13:00:00"
    }
  ]
}
```

**题型枚举：**

| questionTypeId | 含义 |
|---------------|------|
| 0204 | 填空（客观题） |
| 0205 | 简答（主观题） |

**难度枚举：**

| difficultyLevel | 含义 |
|----------------|------|
| 1.0 | 简单 |
| 2.0 | 中等 |
| 3.0 | 困难 |

**状态枚举：**

| status | 含义 |
|--------|------|
| 0 | 草稿 |
| 3 | 弃用（已删除） |

---

### 4. 删除题目

```
POST /api/exam/questiongen/delete
Content-Type: application/json
```

**请求示例：**
```json
["libId1", "libId2"]
```

**响应：**
```json
{ "code": 200, "msg": "success", "data": null }
```

> 软删除，status 改为 3

---

## 二、组卷

### 5. 自动组卷（按难度抽题）

```
POST /api/exam/paper/autoGenerate
Content-Type: application/json
```

| 字段 | 必填 | 说明 |
|------|------|------|
| name | 是 | 试卷名称 |
| courseId | 是 | 课程 ID |
| targetDifficulty | 是 | 目标难度 1=简单 2=中等 3=困难 |
| objectiveCount | 是 | 客观题数量（0204 填空题） |
| subjectiveCount | 是 | 主观题数量（0205 简答题） |
| catelog | 是 | "1"=作业 "2"=考试 |

**请求示例：**
```json
{
  "name": "期末测试卷",
  "courseId": "xxx",
  "targetDifficulty": 2,
  "objectiveCount": 5,
  "subjectiveCount": 3,
  "catelog": "1"
}
```

**响应：**
```json
{
  "code": 200,
  "data": {
    "paper": {
      "id": "1316675600-xxx",
      "name": "期末测试卷",
      "makeModeId": "2",
      "makeModeName": "抽题组卷",
      "questionsCount": 8,
      "totalScore": 45,
      "status": 0,
      "catelog": "1",
      "createTime": "2026-06-22 13:00:00"
    },
    "questions": [ ... ]
  }
}
```

**组卷方式枚举：**

| makeModeId | 含义 |
|-----------|------|
| 1 | 选题组卷（手动） |
| 2 | 抽题组卷（自动） |

---

### 6. 手动组卷（指定题目）

```
POST /api/exam/paper/manualGenerate
Content-Type: application/json
```

| 字段 | 必填 | 说明 |
|------|------|------|
| name | 是 | 试卷名称 |
| courseId | 是 | 课程 ID |
| libIds | 是 | 题目 ID 数组 |
| scores | 是 | 每题分值数组（与 libIds 一一对应） |
| catelog | 是 | "1"=作业 "2"=考试 |

**请求示例：**
```json
{
  "name": "手动组卷测试",
  "courseId": "xxx",
  "libIds": ["libId1", "libId2", "libId3"],
  "scores": [5, 10, 5],
  "catelog": "1"
}
```

---

### 7. 试卷列表

```
GET /api/exam/paper/list?courseId=xxx
```

---

### 8. 查看试卷题目

```
GET /api/exam/paper/questions?paperId=xxx
```

---

### 9. 发布试卷

```
POST /api/exam/paper/publish?paperId=xxx&classroomId=xxx
```

| 参数 | 必填 | 说明 |
|------|------|------|
| paperId | 是 | 试卷 ID |
| classroomId | 是 | 课堂 ID |

**响应示例：**
```json
{
  "code": 200,
  "data": {
    "test": { "id": "xxx", "status": 1 },
    "studentCount": 30
  }
}
```

**入库表：** `pm_test`（考试记录）、`pm_test_students`（考试-学生关联）

---

### 10. 删除试卷

```
POST /api/exam/paper/delete?paperId=xxx
```

> 软删除，status 改为 3

---

## 三、分数模拟

### 11. 模拟学生作答

```
POST /api/exam/scoresim/simulate
Content-Type: application/json
```

| 字段 | 必填 | 说明 |
|------|------|------|
| paperId | 二选一 | 试卷 ID |
| testId | 二选一 | 已发布考试 ID |
| classroomId | 是 | 课堂 ID |

**请求示例：**
```json
{
  "paperId": "xxx",
  "classroomId": "xxx"
}
```

**前置条件：** 试卷已发布、课堂有学生

**响应示例：**
```json
{
  "code": 200,
  "data": {
    "insertedRecords": 240,
    "questionCount": 8,
    "studentCount": 30,
    "studentScores": [
      { "stuId": "xxx", "stuName": "张三", "totalScore": 35 },
      { "stuId": "xxx", "stuName": "李四", "totalScore": 42 }
    ]
  }
}
```

> 第二次模拟同一份卷子不会产生重复数据（INSERT IGNORE + UNIQUE 约束）

---

### 12. 查询模拟结果

```
GET /api/exam/scoresim/result?testId=xxx
GET /api/exam/scoresim/result?testId=xxx&stuId=xxx
```

---

## 涉及数据库表

| 表 | 写入时机 | 说明 |
|----|---------|------|
| `tm_testquelib` | AI 出题 | 题库题目正文 |
| `tm_testquelib_kwa` | AI 出题 | 题目-KWA 关联 |
| `pm_testpaper` | 组卷 | 试卷基本信息 |
| `pm_testpaper_questions` | 组卷 | 试卷-题目关联+分值 |
| `pm_test` | 发布 | 考试/作业记录 |
| `pm_test_students` | 发布 | 考试-学生关联 |
| `ai_in_stu_ans_info` | 模拟 | 每题每生得分明细 |
| `cm_classroom_mypracticelist` | 模拟 | 每生每卷总分汇总 |

---

## 完整业务流程

```
获取KWA → AI生成题目 → 查看题库 → 组卷(自动/手动) → 发布 → 模拟作答 → 查看成绩
```
