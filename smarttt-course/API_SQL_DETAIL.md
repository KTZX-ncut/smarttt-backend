# smarttt-course 接口实现说明（SQL 版）

## 1. 文档范围

本文件基于 `smarttt-course` 模块当前源码做静态分析，覆盖：

- `smarttt-course/src/main/java/com/example/smartttcourse/controller` 下所有有方法级映射的接口
- 每个接口的主要调用链
- 成功主路径下的 SQL 次数
- SQL 原文或 MyBatis-Plus 推断模板
- 涉及表

不包含：

- MinIO 的桶、对象上传下载删除
- HTTP 文件流输出
- Redis、日志、JWT 解析

## 2. 统计口径

- 所有接口的完整前缀均为 `/api`
- SQL 次数按“成功主路径”统计
- `saveBatch`、`remove(QueryWrapper)`、`getById`、`getOne`、`list`、`selectCount` 等 MyBatis-Plus 生成 SQL，在本文中会以“推断 SQL 模板”给出
- 如果某个接口存在循环或按结果集条数追加查询，SQL 次数会用公式表示

术语约定：

- `N_course`：当前接口返回的课程数
- `N_classroom`：当前课程下的课堂数
- `N_obs`：递归树中实际遍历到的组织节点数
- `N_bind`：本次批量绑定负责人数量
- `N_student`：本次处理的学生数量
- `N_newStudent`：本次真正新插入到课堂中的学生数量
- `N_oldUnit`：复制课程内容时，当前课程删除前已有的知识单元数量
- `N_pastUnit`：复制课程内容时，历史课程中的知识单元数量
- `N_roleHistory`：历史课程已绑定的负责人数量

## 3. 当前没有方法级接口的 Controller

以下 Controller 只有类级别 `@RequestMapping`，没有公开的 REST 方法，因此当前没有可调用接口，也没有 SQL：

- `CmClassroomClassroommenuController`
- `CmClassroomHomeworkinfoController`
- `CmClassroomMypracticelistController`
- `SmStudentController`

## 4. 共享 SQL 模式

### 4.1 负责人树查询模式（P1）

用途：

- `POST /api/coursemangt/course/courseRP`
- `GET /api/coursemangt/classroom/teacher`
- `POST /api/coursemangt/classroom/classroomRP`

SQL 次数：

- 如果调用方先取学校根节点：`1 + 2 + N_obs`
- 其中：
  - 1 次 `getSchoolObs`
  - 1 次 `getTeacherLevel`
  - 1 次 `getRPTree`
  - 每个组织节点 1 次 `getAllTeacherByObsID`

SQL：

1. 学校根节点

```sql
select id,obsname,remark from sm_obs where obsdeep = 1
```

2. 教师层级深度

```sql
select obsdeep from st_level where teacher = 1
```

3. 负责人树节点列表

```sql
select * from sm_obs
where termid = (select id from cm_term where iscurrentterm = 1) or termid = 0
and obsdeep<=#{obsdeep};
```

4. 每个节点下的教师负责人

```sql
SELECT u.id, u.username, o.obsname, t.obsid
FROM sm_teacher t
JOIN st_users u ON t.usersid = u.id
JOIN sm_obs o ON t.obsid = o.id
WHERE t.obsid = #{obsid}
```

涉及表：

- `sm_obs`
- `cm_term`
- `st_level`
- `sm_teacher`
- `st_users`

### 4.2 学生树查询模式（P2）

用途：

- `GET /api/coursemangt/classroommangt/student/studentRP`

SQL 次数：

- `2 + N_obs`
- 其中：
  - 1 次 `getSchoolObs`
  - 1 次 `SELECT * FROM sm_obs`
  - 每个节点 1 次 `getAllStudentByObsID`

SQL：

1. 学校根节点

```sql
select id,obsname,remark from sm_obs where obsdeep = 1
```

2. 全量组织节点

```sql
SELECT * FROM sm_obs
```

3. 每个节点下的学生

```sql
SELECT s.id,s.obsid,s.usersid,
(
SELECT obsname FROM sm_obs WHERE id = (
        SELECT pid FROM sm_obs WHERE id = s.obsid
    )
) proname,
(SELECT obsname FROM sm_obs WHERE id = s.obsid) obsname,
u.loginname,u.username
FROM sm_student s
JOIN st_users u
ON s.usersid = u.id
JOIN sm_obs o
ON s.obsid = o.id
WHERE s.obsid = #{obsid}
```

涉及表：

- `sm_obs`
- `sm_student`
- `st_users`

### 4.3 课程级文件列表模式（F1）

用途：

- `GET /api/coursemangt/instructionalprogram`
- `GET /api/coursemangt/courseresources`

分支 1：当前角色为“课程负责人”

- SQL 次数：`2`

1. 查询角色名

```sql
SELECT rolename FROM st_roles
WHERE id = #{roleid}
```

2. 查询文件列表

```sql
SELECT *
FROM cm_course_file
WHERE obsid = #{obsid}
  AND type = #{type}
```

分支 2：当前角色为“任课教师 / 实验教师 / 学生”

- SQL 次数：`3`

1. 查询角色名

```sql
SELECT rolename FROM st_roles
WHERE id = #{roleid}
```

2. 课堂转课程

```sql
SELECT courseId FROM cm_classroom
WHERE id = #{classroomId}
```

3. 查询文件列表

```sql
SELECT *
FROM cm_course_file
WHERE obsid = #{courseId}
  AND type = #{type}
```

涉及表：

- `st_roles`
- `cm_classroom`
- `cm_course_file`

### 4.4 课堂级文件列表模式（F2）

用途：

- `GET /api/coursemangt/classroommangt/lessonplan`
- `GET /api/coursemangt/classroommangt/academiccalendar`

分支 1：当前角色为“课程负责人”

- SQL 次数：
  - 无课堂时：`2`
  - 有课堂时：`3`

1. 查询角色名

```sql
SELECT rolename FROM st_roles
WHERE id = #{roleid}
```

2. 查询课程下的全部课堂 ID

```sql
SELECT DISTINCT id FROM cm_classroom
WHERE courseId = #{courseId}
```

3. 查询文件列表（仅在课堂列表非空时执行）

```sql
SELECT *
FROM cm_course_file
WHERE obsid IN (#{classroomIdList...})
  AND type = #{type}
```

分支 2：当前角色为“任课教师 / 实验教师 / 学生”

- SQL 次数：`2`

1. 查询角色名

```sql
SELECT rolename FROM st_roles
WHERE id = #{roleid}
```

2. 查询文件列表

```sql
SELECT *
FROM cm_course_file
WHERE obsid = #{classroomId}
  AND type = #{type}
```

涉及表：

- `st_roles`
- `cm_classroom`
- `cm_course_file`

### 4.5 课程级文件上传模式（F3）

用途：

- `POST /api/coursemangt/instructionalprogram/upload`
- `POST /api/coursemangt/courseresources/upload`

分支 1：`token.obsid` 本身就是课程 ID

- SQL 次数：`3`

1. 课程存在性检查

```sql
select count(*) from cm_course where id = #{courseIdOrClassroomId}
```

2. 查询课程所属学期

```sql
SELECT schooltermId FROM cm_course WHERE id = #{courseId}
```

3. 保存文件元数据（MyBatis-Plus 推断）

```sql
INSERT INTO cm_course_file
(id, obsid, filename, size, type, createtime, remark, object_name, bucket_name)
VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
```

分支 2：`token.obsid` 是课堂 ID

- SQL 次数：`5`

1. 课程存在性检查

```sql
select count(*) from cm_course where id = #{courseIdOrClassroomId}
```

2. 课堂存在性检查

```sql
select count(*) from cm_classroom where id = #{courseIdOrClassroomId}
```

3. 查询课堂所属课程

```sql
SELECT courseId FROM cm_classroom
WHERE id = #{classroomId}
```

4. 查询课程所属学期

```sql
SELECT schooltermId FROM cm_course WHERE id = #{courseId}
```

5. 保存文件元数据（MyBatis-Plus 推断）

```sql
INSERT INTO cm_course_file
(id, obsid, filename, size, type, createtime, remark, object_name, bucket_name)
VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
```

涉及表：

- `cm_course`
- `cm_classroom`
- `cm_course_file`

### 4.6 课堂级文件上传模式（F4）

用途：

- `POST /api/coursemangt/classroommangt/lessonplan/upload`
- `POST /api/coursemangt/classroommangt/academiccalendar/upload`

SQL 次数：`3`

1. 查询课堂所属学期

```sql
SELECT termId FROM cm_classroom
WHERE id = #{classroomId}
```

2. 查询课堂所属课程

```sql
SELECT courseId FROM cm_classroom
WHERE id = #{classroomId}
```

3. 保存文件元数据（MyBatis-Plus 推断）

```sql
INSERT INTO cm_course_file
(id, obsid, filename, size, type, createtime, remark, object_name, bucket_name)
VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
```

涉及表：

- `cm_classroom`
- `cm_course_file`

### 4.7 文件下载 / 删除公共模式（F5）

用途：

- `GET /download/{fileName}`
- `GET /delete/{fileName}`

下载 SQL 次数：`1`

```sql
SELECT *
FROM cm_course_file
WHERE id = #{fileId}
```

删除 SQL 次数：`2`

1. 先查元数据

```sql
SELECT *
FROM cm_course_file
WHERE id = #{fileId}
```

2. 再删元数据（MyBatis-Plus 推断）

```sql
DELETE FROM cm_course_file
WHERE id = #{fileId}
```

涉及表：

- `cm_course_file`

## 5. 接口逐项说明

### 5.1 系统配置

#### GET /api/config/system

- 作用：根据 `spring.datasource.url` 识别当前课程模块接的是哪套数据库
- 调用链：`ConfigController.getSystemConfig`
- SQL 次数：`0`
- 涉及表：无
- 备注：纯配置字符串处理

### 5.2 课程切换

#### GET /api/coursemangt/switch

- 作用：获取当前教师可切换的课程列表
- 调用链：`SwitchCourseController.getTeacherCourse -> CmCourseServiceImpl.getTeacherCourse -> CmCourseMapper.getTeacherOtherCourse`
- SQL 次数：`1`
- 涉及表：
  - `cm_course`
  - `st_roleuser`
- SQL：

```sql
select cm_course.id,cm_course.courseChineseName from cm_course
where cm_course.id in (select obsid as id from st_roleuser where roleid =#{roleid}  and userid=#{id})
```

#### GET /api/coursemangt/switch/switchteachercourse?id={courseId}

- 作用：把当前 token 的 `obsid` 切换成指定课程 ID，并返回新的 token
- 调用链：`SwitchCourseController.switchTeacherCourse`
- SQL 次数：`0`
- 涉及表：无
- 备注：纯内存改 token 并重新签发

### 5.3 学期管理

#### GET /api/sysmangt/terms

- 作用：获取全部学期
- 调用链：`TermsMangtController.getTerms -> CmTermServicelmpl.getTerms -> CmTermMapper.getTerms`
- SQL 次数：`1`
- 涉及表：`cm_term`
- SQL：

```sql
select * from cm_term
```

#### POST /api/sysmangt/terms/create

- 作用：创建学期，并创建该学期对应的动态表
- 调用链：`TermsMangtController.createTerms -> CmTermServicelmpl.createTerms`
- SQL 次数：`3`
- 涉及表：
  - `cm_term`
  - 动态表 `ai_in_stu_ans_info_{orderno}`
- SQL：

1. 新增学期

```sql
INSERT INTO cm_term (id,termname,begindate,enddate,remark,iscurrentterm,createtime)
VALUES (#{id},#{termname},#{begindate},#{enddate},#{remark},#{iscurrentterm},#{createtime})
```

2. 查询新学期序号

```sql
select orderno from cm_term where id = #{id}
```

3. 建表

```sql
CREATE TABLE ai_in_stu_ans_info_{orderno} (
  `id` varchar(64) NOT NULL,
  `logId` varchar(64) NOT NULL COMMENT '日志表id',
  `courseId` varchar(64) NOT NULL COMMENT '课程id',
  `classroomId` varchar(64) NOT NULL COMMENT '课堂id',
  `testId` varchar(64) NOT NULL COMMENT '考试id',
  `paperId` varchar(64) NOT NULL COMMENT '试卷id',
  `paperType` varchar(16) NOT NULL DEFAULT 'def' COMMENT '试卷类型 def=默认',
  `libId` varchar(64) NOT NULL COMMENT '题目id',
  `stuId` varchar(64) DEFAULT NULL COMMENT '学生id',
  `questionTypeId` varchar(16) DEFAULT NULL COMMENT '题型',
  `questionContent` mediumtext COMMENT '学生答题内容',
  `difficultLevel` decimal(8,2) DEFAULT NULL COMMENT '题目难度',
  `differenceLevel` decimal(8,2) DEFAULT NULL COMMENT '题目区分度',
  `guessLevel` decimal(8,2) DEFAULT NULL COMMENT '题目猜测度',
  `kwaId` varchar(64) DEFAULT NULL COMMENT 'kwaId',
  `dataValue` decimal(8,2) DEFAULT NULL COMMENT '题目与kwa关联度',
  `libScore` int DEFAULT NULL COMMENT '题目的分数',
  `libStuScore` int DEFAULT NULL COMMENT '题目学生得分',
  `libAnswerTime` int DEFAULT NULL COMMENT '题目作答时长，单位秒',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `aisai_index_ti_si_li` (`testId`,`libId`,`stuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='学生作业试题详细信息日志表,颗粒度为试题维度';
```

#### POST /api/sysmangt/terms/delete

- 作用：按 ID 删除学期，同时删除对应 `termid` 的组织节点
- 调用链：`TermsMangtController.deleteTermsByID -> CmTermServicelmpl.deleteTermsByID`
- SQL 次数：`2`
- 涉及表：
  - `cm_term`
  - `sm_obs`
- SQL：

```sql
delete from cm_term where id in (#{ids...})
```

```sql
delete from sm_obs where termid in (#{ids...})
```

#### POST /api/sysmangt/terms

- 作用：更新学期信息
- 调用链：`TermsMangtController.updateTerm -> CmTermServicelmpl.updateTermByID -> CmTermMapper.updateTermByID`
- SQL 次数：`1`
- 涉及表：`cm_term`
- SQL：

```sql
update cm_term
<set>
  <if test="termname != null">termname=#{termname},</if>
  <if test="begindate != null">begindate=#{begindate},</if>
  <if test="enddate != null">enddate=#{enddate},</if>
  <if test="remark != null">remark=#{remark},</if>
</set>
where id=#{id}
```

#### GET /api/sysmangt/terms/currentterm?id={termId}

- 作用：设置当前学期
- 调用链：`TermsMangtController.setCurrentTerms -> CmTermServicelmpl.setCurrentTerms`
- SQL 次数：`2`
- 涉及表：`cm_term`
- SQL：

```sql
update cm_term set iscurrentterm = 1 where id = #{id}
```

```sql
update cm_term set iscurrentterm = 0 where id != #{id}
```

### 5.4 课程管理

#### GET /api/coursemangt/course/test

- 作用：课程模块连通性测试
- 调用链：`CourseController.test`
- SQL 次数：`0`
- 涉及表：无

#### GET /api/coursemangt/course

- 作用：获取当前专业负责人可管理的课程列表
- 调用链：`CourseController.getCourse -> CmCourseServiceImpl.getCourse`
- SQL 次数：`1 + 2 * N_course`
- 涉及表：
  - `cm_course`
  - `cm_term`
  - `st_roleuser`
  - `st_users`
  - `sm_teacher`
  - `sm_obs`
- SQL：

1. 主查询

```sql
select id,courseChineseName, courseEnglishName, courseCode, professionName,professionId
from cm_course
where professionId=#{obsid} and schooltermId = #{termid}
```

2. 对每门课程补学期名

```sql
select termname from cm_term where id = #{schooltermId}
```

3. 对每门课程补负责人列表

```sql
SELECT u.id, u.username, o.obsname
FROM st_roleuser ru
JOIN st_users u ON ru.userid = u.id
JOIN sm_teacher t ON u.id = t.usersid
JOIN sm_obs o ON t.obsid = o.id
WHERE ru.obsid = #{id};
```

#### GET /api/coursemangt/course/allterm

- 作用：根据当前 token 学期获取历史学期列表
- 调用链：`CourseController.getAllTerm -> CmTermServicelmpl.getHistoryTerm -> CmTermMapper.getHistoryTerms`
- SQL 次数：`1`
- 涉及表：`cm_term`
- SQL：

```sql
select id,termname from cm_term where id != #{termid}
```

#### GET /api/coursemangt/course/currenttermId

- 作用：获取当前学期 ID
- 调用链：`CourseController.getCurrentTerm -> CmTermServicelmpl.getCurrentTerm -> CmTermMapper.getCurrentTerm`
- SQL 次数：`1`
- 涉及表：`cm_term`
- SQL：

```sql
select id from cm_term where iscurrentterm = 1
```

#### POST /api/coursemangt/course/create

- 作用：新建课程
- 调用链：`CourseController.createCourse -> CmCourseServiceImpl.createCourse`
- SQL 次数：`2`
- 涉及表：
  - `sm_obs`
  - `cm_course`
- SQL：

1. 查询所属专业名称

```sql
select obsname from sm_obs where id = #{professionId}
```

2. 新增课程

```sql
INSERT INTO cm_course
(id,schooltermId, courseChineseName, courseEnglishName, courseCode, professionName, professionId)
VALUES
(#{id},#{schooltermId},#{courseChineseName},#{courseEnglishName},#{courseCode},#{professionName},#{professionId})
```

#### POST /api/coursemangt/course/delete

- 作用：删除课程，并清理课程和课堂上的角色绑定
- 调用链：`CourseController.deleteCourseByID -> CmCourseServiceImpl.deleteCourseByID`
- SQL 次数：
  - 无课堂时：`3`
  - 有课堂时：`4`
- 涉及表：
  - `st_roleuser`
  - `cm_classroom`
  - `cm_course`
- SQL：

1. 删除课程负责人绑定

```sql
DELETE FROM st_roleuser WHERE obsid IN (#{courseIdList...})
```

2. 查询课程下课堂 ID

```sql
SELECT DISTINCT id FROM cm_classroom
WHERE courseId IN (#{courseIdList...})
```

3. 如果课堂存在，删除课堂负责人绑定

```sql
DELETE FROM st_roleuser
WHERE obsid IN (#{classroomIdList...})
```

4. 删除课程

```sql
delete from cm_course where id in (#{ids...})
```

#### GET /api/coursemangt/course/history?id={termId}

- 作用：按学期查询当前专业的历史课程
- 调用链：`CourseController.historyCourseByTerm -> CmCourseServiceImpl.historyCourseByTerm -> CmCourseMapper.historyCourseByTerm`
- SQL 次数：`1`
- 涉及表：
  - `cm_course`
  - `cm_profession`
- SQL：

```sql
SELECT c.id, c.courseChineseName, c.courseEnglishName, c.courseCode,
       c.professionName, c.professionId
FROM cm_course c
INNER JOIN cm_profession p ON c.professionId = p.obsid
WHERE c.schooltermId = #{termID}
AND p.procode = (SELECT procode FROM cm_profession WHERE obsid = #{obsid})
```

#### POST /api/coursemangt/course/update

- 作用：更新课程基础信息
- 调用链：`CourseController.updateCourse -> CmCourseServiceImpl.updateOneCourse -> CmCourseMapper.updateOneCourse`
- SQL 次数：`1`
- 涉及表：`cm_course`
- SQL：

```sql
update cm_course
<set>
  <if test="courseChineseName != null">courseChineseName=#{courseChineseName},</if>
  <if test="courseEnglishName != null">courseEnglishName =#{courseEnglishName},</if>
  <if test="courseCode != null">courseCode=#{courseCode},</if>
</set>
where id=#{id}
```

#### GET /api/coursemangt/course/copy?id={courseId}

- 作用：复制一门历史课程到当前学期，并复制历史负责人
- 调用链：`CourseController.copyHistoryCourse -> CmCourseServiceImpl.copyHistoryCourse`
- SQL 次数：`4 + N_roleHistory`
- 涉及表：
  - `cm_course`
  - `cm_term`
  - `st_roleuser`
- SQL：

1. 读取被复制课程

```sql
select * from cm_course where id = #{id}
```

2. 查询当前学期

```sql
select id from cm_term where iscurrentterm = 1
```

3. 插入新课程

```sql
INSERT INTO cm_course
(id,schooltermId, courseChineseName, courseEnglishName, courseCode, professionName, professionId)
VALUES
(#{id},#{schooltermId},#{courseChineseName},#{courseEnglishName},#{courseCode},#{professionName},#{professionId})
```

4. 查询历史负责人

```sql
select * from st_roleuser where obsid = #{id}
```

5. 对每个历史负责人执行一次插入

```sql
insert into st_roleuser(id, userid, roleid, obsid, obsdeep, createtime,termid)
values (#{id}, #{userid}, #{roleid}, #{obsid}, #{obsdeep}, #{createtime},#{termid})
```

#### POST /api/coursemangt/course/courseRP

- 作用：获取课程负责人候选树
- 调用链：`CourseController.courseRPList -> SmObsServiceImpl.getSchoolObs + SmObsServiceImpl.getObsRPList`
- SQL 次数：复用 `P1`
- 涉及表：复用 `P1`

#### POST /api/coursemangt/course/courseRP/delete

- 作用：删除课程负责人绑定
- 调用链：`CourseController.deleteCourseRP -> StUsersServiceImpl.deleteRP`
- SQL 次数：`1`
- 涉及表：`st_roleuser`
- SQL：

```sql
delete from st_roleuser where obsid = #{obsid} and userid = #{userid} and roleid = #{roleid}
```

#### POST /api/coursemangt/course/courseRP/create

- 作用：批量新增课程负责人绑定
- 调用链：`CourseController.createCourseRP`
- SQL 次数：`2 * N_bind`
- 涉及表：
  - `cm_term`
  - `st_roleuser`
- SQL：

1. 每个绑定先查一次当前学期

```sql
select id from cm_term where iscurrentterm = 1
```

2. 每个绑定再插入一次角色关系

```sql
insert into st_roleuser(id, userid, roleid, obsid, obsdeep, createtime,termid)
values (#{id}, #{userid}, #{roleid}, #{obsid}, #{obsdeep}, #{createtime},#{termid})
```

#### GET /api/coursemangt/course/getPreCourseByCode?termId={termId}

- 作用：根据当前课程的课程号，去历史学期查同课程号课程
- 调用链：`CourseController.getPreCourseByCode -> CmCourseServiceImpl.getPreCourseByCode`
- SQL 次数：`2`
- 涉及表：`cm_course`
- SQL：

1. 先查当前课程课程号

```sql
select courseCode from cm_course where id = #{obsId}
```

2. 再查历史课程

```sql
select id, courseChineseName, courseEnglishName, courseCode, professionName, professionId
from cm_course where schooltermId = #{termId} and courseCode = #{courseCode}
```

#### POST /api/coursemangt/course/copyCourseInfo?pastId={pastId}

- 作用：把历史课程中的关键字、能力、KWA、知识单元、课程目标、考核项等全部复制到当前课程
- 调用链：`CourseController.copyInfo -> CmCourseServiceImpl.copyInfo`
- SQL 次数：
  - 理想成功路径：`28 + N_oldUnit + N_pastUnit`
  - 其中 8 个 `copy*` 批量插入语句在对应集合为空时可能不会执行
- 涉及表：
  - `cm_keywords`
  - `cm_getability`
  - `cm_kwadict`
  - `cm_course_unit`
  - `cm_course_unit_kwa`
  - `cm_lines`
  - `cm_course_target`
  - `cm_course_target_kwa`
  - `cm_course_checkitem`
  - `cm_course_assessment`
- SQL 顺序：

1. 删除当前课程关键字

```sql
delete from cm_keywords where courseid = #{obsId}
```

2. 查询历史关键字

```sql
select * from cm_keywords where courseid = #{pastId}
```

3. 批量插入关键字

```sql
insert into cm_keywords values (...), (...), ...
```

4. 删除当前课程能力

```sql
delete from cm_getability where courseid = #{obsId}
```

5. 查询历史能力

```sql
select * from cm_getability where courseid = #{pastId}
```

6. 批量插入能力

```sql
insert into cm_getability values (...), (...), ...
```

7. 删除当前课程 KWA

```sql
delete from cm_kwadict where courseid = #{obsId}
```

8. 查询历史 KWA

```sql
select * from cm_kwadict where courseid = #{pastId}
```

9. 批量插入 KWA

```sql
insert into cm_kwadict values (...), (...), ...
```

10. 查询当前课程旧知识单元（用于后续删除旧 unit-kwa）

```sql
select * from cm_course_unit where courseId = #{pastId}
```

11. 删除当前课程知识单元

```sql
delete from cm_course_unit where courseId = #{obsId}
```

12. 查询历史知识单元

```sql
select * from cm_course_unit where courseId = #{pastId}
```

13. 批量插入知识单元

```sql
insert into cm_course_unit values (...), (...), ...
```

14. 对每个旧知识单元删除 unit-kwa

```sql
delete from cm_course_unit_kwa where unitId = #{unitId}
```

15. 对每个历史知识单元查询 unit-kwa

```sql
select * from cm_course_unit_kwa where unitId = #{unitId}
```

16. 批量插入 unit-kwa

```sql
insert into cm_course_unit_kwa values (...), (...), ...
```

17. 删除当前课程连线

```sql
delete from cm_lines where courseid = #{obsId}
```

18. 查询历史连线

```sql
select * from cm_lines where courseid = #{pastId}
```

19. 批量插入连线

```sql
insert into cm_lines values (...), (...), ...
```

20. 删除当前课程目标

```sql
delete from cm_course_target where courseid = #{obsId}
```

21. 查询历史课程目标

```sql
select * from cm_course_target where courseid = #{pastId}
```

22. 批量插入课程目标

```sql
insert into cm_course_target values (...), (...), ...
```

23. 删除当前课程目标-KWA 关联

```sql
delete from cm_course_target_kwa where obsId = #{obsId}
```

24. 查询历史课程目标-KWA 关联

```sql
select * from cm_course_target_kwa where obsId = #{pastId}
```

25. 批量插入课程目标-KWA 关联

```sql
insert into cm_course_target_kwa values (...), (...), ...
```

26. 删除当前课程考核项

```sql
delete from cm_course_checkitem where courseid = #{obsId}
```

27. 查询历史考核项

```sql
select * from cm_course_checkitem where courseid = #{pastId}
```

28. 批量插入考核项

```sql
insert into cm_course_checkitem values (...), (...), ...
```

29. 删除当前课程考核方案

```sql
delete from cm_course_assessment where courseId = #{obsId}
```

30. 查询历史考核方案

```sql
select * from cm_course_assessment where courseId = #{pastId}
```

31. 批量插入考核方案

```sql
insert into cm_course_assessment values (...), (...), ...
```

备注：

- 第 10 步的 `getPastUnit(obsId)` 方法名和用途不一致，实际是拿“当前课程”的知识单元
- 这条接口是整个模块 SQL 最重的接口之一

### 5.5 课堂管理

#### GET /api/coursemangt/classroom

- 作用：按当前课程/专业返回课堂列表，并按课程分组
- 调用链：`ClassroomMangtController.getClassRoomList -> CmClassRoomServiceImpl.getClassRoomList`
- SQL 次数：`1 + N_course`
- 涉及表：
  - `cm_course`
  - `cm_classroom`
- SQL：

1. 查询课程分组

```sql
select id,courseChineseName from cm_course where professionId=#{obsid} and schooltermId = #{termid}
```

2. 对每门课程查询课堂列表

```sql
select * from cm_classroom where courseId =#{id}
```

#### POST /api/coursemangt/classroom/delete

- 作用：删除课堂并清理该课堂角色绑定
- 调用链：`ClassroomMangtController.deleteClassroom -> CmClassRoomServiceImpl.deleteClassroom`
- SQL 次数：`2`
- 涉及表：
  - `st_roleuser`
  - `cm_classroom`
- SQL：

```sql
DELETE FROM st_roleuser
WHERE obsid IN (#{classroomIdList...})
```

```sql
delete from cm_classroom where id in (#{ids...})
```

#### POST /api/coursemangt/classroom/create

- 作用：新建课堂，并自动插入授课教师/实验教师角色
- 调用链：`ClassroomMangtController.createOneClassroom -> CmClassRoomServiceImpl.createOneClassroom`
- SQL 次数：`3`
- 涉及表：
  - `cm_classroom`
  - `st_roleuser`
- SQL：

1. 新增课堂

```sql
insert into cm_classroom
(id,classroomName,termId,courseId,teacherId,usedClassList,beginTime,endTime,remark,teacherName,creator,creatorName,labTeacherId,labTeacher,practiceTeacherId,practiceTeacher,teachTime,labTime,practiceTime)
values
(#{id},#{classroomName},#{termId},#{courseId},#{teacherId},#{usedClassList},#{beginTime},#{endTime},#{remark},#{teacherName},#{creator},#{creatorName},#{labTeacherId},#{labTeacher},#{practiceTeacherId},#{practiceTeacher},#{teachTime},#{labTime},#{practiceTime})
```

2. 新增授课教师角色

```sql
insert into st_roleuser(id, userid, roleid, obsid, obsdeep, createtime,termid)
values (#{id}, #{userid}, #{roleid}, #{obsid}, #{obsdeep}, #{createtime},#{termid})
```

3. 新增实验教师角色

```sql
insert into st_roleuser(id, userid, roleid, obsid, obsdeep, createtime,termid)
values (#{id}, #{userid}, #{roleid}, #{obsid}, #{obsdeep}, #{createtime},#{termid})
```

#### POST /api/coursemangt/classroom/update

- 作用：更新课堂信息，并同步更新两个角色绑定对应的教师
- 调用链：`ClassroomMangtController.updateClassroom -> CmClassRoomServiceImpl.updateOneClassroom`
- SQL 次数：`3`
- 涉及表：
  - `cm_classroom`
  - `st_roleuser`
- SQL：

1. 更新课堂

```sql
update cm_classroom
<set>
  <if test="classroomName != null">classroomName = #{classroomName},</if>
  <if test="teacherId != null">teacherId = #{teacherId},</if>
  <if test="teachTime != -1">teachTime = #{teachTime},</if>
  <if test="labTime != -1">labTime = #{labTime},</if>
  <if test="practiceTime != -1">practiceTime = #{practiceTime},</if>
  <if test="teacherName != null">teacherName = #{teacherName},</if>
  <if test="labTeacherId != null">labTeacherId = #{labTeacherId},</if>
  <if test="labTeacher != null">labTeacher = #{labTeacher},</if>
  <if test="practiceTeacherId != null">practiceTeacherId = #{practiceTeacherId},</if>
  <if test="practiceTeacher != null">practiceTeacher = #{practiceTeacher}</if>
</set>
where id = #{id}
```

2. 更新授课教师绑定

```sql
update st_roleuser set userid = #{teacherId} where obsid = #{id} and roleid = #{remark}
```

3. 更新实验教师绑定

```sql
update st_roleuser set userid = #{teacherId} where obsid = #{id} and roleid = #{remark}
```

#### GET /api/coursemangt/classroom/teacher

- 作用：获取课堂可绑定教师候选树
- 调用链：`ClassroomMangtController.getTeacherList`
- SQL 次数：复用 `P1`
- 涉及表：复用 `P1`

#### POST /api/coursemangt/classroom/classroomRP

- 作用：获取课堂负责人候选树
- 调用链：`ClassroomMangtController.classroomRPList`
- SQL 次数：复用 `P1`
- 涉及表：复用 `P1`

### 5.6 课堂学生管理

#### GET /api/coursemangt/classroommangt/student/list?obsid={classroomId}

- 作用：获取课堂学生列表
- 调用链：`CourseStudentMangtController.getStudentList -> CmClassroomStudentServiceImpl.getStudentList`
- SQL 次数：`1`
- 涉及表：
  - `cm_classroom_student`
  - `sm_obs`
  - `sm_student`
- SQL：

```sql
SELECT cs.id,cs.classroomId,
       cs.userId,
       cs.obsId,
       cs.userName,
       cs.loginname,
       cs.rowNo,
       cs.courseScore,
       s1.obsname obsname,
       s2.obsName proname,
       st.id stuId,
       st.stuno,
       cs.dynamic_state as dynamicState,
       cs.reach_state as reachState,
       cs.ideology_state as ideologyState
FROM cm_classroom_student cs
JOIN sm_obs s1 ON cs.obsId = s1.id
JOIN sm_obs s2 ON s1.pid = s2.id
JOIN sm_student st ON st.usersid = cs.userId
WHERE cs.classroomId = #{classRoomId}
ORDER BY st.stuno
```

#### GET /api/coursemangt/classroommangt/student/studentRP

- 作用：获取学生树
- 调用链：`CourseStudentMangtController.StudentRPList -> CmClassroomStudentServiceImpl.getObsRPStudentList`
- SQL 次数：复用 `P2`
- 涉及表：复用 `P2`

#### POST /api/coursemangt/classroommangt/student/create

- 作用：批量把学生加入课堂，并同步生成课堂菜单和作业统计记录
- 调用链：`CourseStudentMangtController.createStudentClassRoom`
- SQL 次数：`N_student + 3 * N_newStudent`
- 涉及表：
  - `cm_classroom_student`
  - `cm_classroom_classroommenu`
  - `cm_classroom_homeworkinfo`
- SQL：

1. 对每个学生检查课堂里是否已存在

```sql
SELECT COUNT(*)
FROM cm_classroom_student
WHERE classroom_id = #{classRoomId}
  AND user_id = #{userId}
```

2. 对每个新学生插入课堂学生表（MyBatis-Plus `saveBatch` 推断）

```sql
INSERT INTO cm_classroom_student
(id, classroom_id, user_id, obs_id, user_name, obs_name, pro_name, loginname, row_no, course_score)
VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
```

3. 对每个新学生插入作业统计表（MyBatis-Plus `saveBatch` 推断）

```sql
INSERT INTO cm_classroom_homeworkinfo
(id, classroomid, stuid, publishtimes, finishedtimes, finishedpercent, correctpercent)
VALUES (?, ?, ?, ?, ?, ?, ?)
```

4. 对每个新学生插入课堂菜单表（MyBatis-Plus `saveBatch` 推断）

```sql
INSERT INTO cm_classroom_classroommenu
(id, stuid, classroomid, taskcount, menuid)
VALUES (?, ?, ?, ?, ?)
```

备注：

- 入参里的学生信息直接用于构造记录，不会额外回库校验用户基本信息

#### POST /api/coursemangt/classroommangt/student/import

- 作用：上传 Excel，并按行把学生批量导入课堂
- 调用链：`CourseStudentMangtController.importTeacherAndStudent -> CmClassroomStudentServiceImpl.importClassRoomStudentExcel`
- SQL 次数：`8 * N_student`
- 涉及表：
  - `sm_student`
  - `cm_classroom_student`
  - `st_users`
  - `sm_obs`
  - `cm_classroom_homeworkinfo`
  - `cm_classroom_classroommenu`
- SQL：

对每一行学生：

1. 学号查学生

```sql
SELECT * FROM sm_student WHERE stuno = #{stuno} LIMIT 1
```

2. 检查该学生是否已在课堂中

```sql
SELECT COUNT(*)
FROM cm_classroom_student
WHERE user_id = #{userId}
  AND classroom_id = #{classRoomId}
```

3. 查用户名

```sql
SELECT username FROM st_users where id = #{usersid}
```

4. 查组织节点

```sql
SELECT * FROM sm_obs WHERE id = #{obsid}
```

5. 查登录名

```sql
SELECT loginname FROM st_users WHERE id=#{id}
```

6. 批量插入课堂学生（每个学生 1 条逻辑 INSERT）

```sql
INSERT INTO cm_classroom_student
(id, classroom_id, user_id, obs_id, user_name, obs_name, pro_name, loginname, row_no, course_score)
VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
```

7. 批量插入课堂作业统计（每个学生 1 条逻辑 INSERT）

```sql
INSERT INTO cm_classroom_homeworkinfo
(id, classroomid, stuid, publishtimes, finishedtimes, finishedpercent, correctpercent)
VALUES (?, ?, ?, ?, ?, ?, ?)
```

8. 批量插入课堂菜单（每个学生 1 条逻辑 INSERT）

```sql
INSERT INTO cm_classroom_classroommenu
(id, stuid, classroomid, taskcount, menuid)
VALUES (?, ?, ?, ?, ?)
```

#### DELETE /api/coursemangt/classroommangt/student/delete

- 作用：删除指定课堂中的指定学生
- 调用链：`CourseStudentMangtController.deleteStudents -> CmClassroomStudentServiceImpl.deleteClassRoomStudent`
- SQL 次数：`5`
- 涉及表：
  - `sm_student`
  - `cm_classroom_student`
  - `cm_classroom_classroommenu`
  - `cm_classroom_homeworkinfo`
  - `cm_classroom_mypracticelist`
- SQL：

1. 通过 `stuIds` 查 `usersid`

```sql
SELECT *
FROM sm_student
WHERE id IN (#{stuIds...})
```

2. 删除课堂学生记录（MyBatis-Plus 推断）

```sql
DELETE FROM cm_classroom_student
WHERE user_id IN (#{userIds...})
```

3. 删除课堂菜单

```sql
DELETE FROM cm_classroom_classroommenu
WHERE classroomid = #{classRoomId}
  AND stuid IN (#{userIds...})
```

4. 删除作业统计

```sql
DELETE FROM cm_classroom_homeworkinfo
WHERE classroomid = #{classRoomId}
  AND stuid IN (#{userIds...})
```

5. 删除作业/考试记录

```sql
DELETE FROM cm_classroom_mypracticelist
WHERE classroomid = #{classRoomId}
  AND stuid IN (#{userIds...})
```

备注：

- 第 2 条 SQL 没有带 `classRoomId` 条件，当前实现会把这些学生在所有课堂中的记录都删掉，而不是只删当前课堂

#### DELETE /api/coursemangt/classroommangt/student/deleteAll

- 作用：删除指定课堂的全部学生
- 调用链：`CourseStudentMangtController.deleteAllStudent -> CmClassroomStudentServiceImpl.deleteClassRoomStudentAll`
- SQL 次数：`4`
- 涉及表：
  - `cm_classroom_student`
  - `cm_classroom_classroommenu`
  - `cm_classroom_homeworkinfo`
  - `cm_classroom_mypracticelist`
- SQL：

```sql
DELETE FROM cm_classroom_student
WHERE classroom_id = #{classRoomId}
```

```sql
DELETE FROM cm_classroom_classroommenu
WHERE classroomid = #{classRoomId}
```

```sql
DELETE FROM cm_classroom_homeworkinfo
WHERE classroomid = #{classRoomId}
```

```sql
DELETE FROM cm_classroom_mypracticelist
WHERE classroomid = #{classRoomId}
```

#### GET /api/coursemangt/classroommangt/student/getStudentLevel

- 作用：获取学生所属层级名称
- 调用链：`CourseStudentMangtController.getStudentLevel -> CmClassStudentMapper.getStudentLevel`
- SQL 次数：`1`
- 涉及表：`st_level`
- SQL：

```sql
SELECT levelname FROM st_level WHERE student = 1
```

### 5.7 教学大纲

#### GET /api/coursemangt/instructionalprogram

- 作用：获取教学大纲文件列表
- 调用链：`InstructionalProgramController.getInstructionalProgram`
- SQL 次数：复用 `F1`
- 涉及表：复用 `F1`
- 类型常量：`teachingprogram`

#### POST /api/coursemangt/instructionalprogram/upload

- 作用：上传教学大纲文件
- 调用链：`InstructionalProgramController.TeachingProgramFileUpload`
- SQL 次数：复用 `F3`
- 涉及表：复用 `F3`
- 类型常量：`teachingprogram`

#### GET /api/coursemangt/instructionalprogram/download/{fileName}

- 作用：下载教学大纲文件
- 调用链：`InstructionalProgramController.downloadFile`
- SQL 次数：复用 `F5` 的下载模式
- 涉及表：`cm_course_file`

#### GET /api/coursemangt/instructionalprogram/delete/{fileName}

- 作用：删除教学大纲文件
- 调用链：`InstructionalProgramController.DeleteFile`
- SQL 次数：复用 `F5` 的删除模式
- 涉及表：`cm_course_file`

### 5.8 课程资源

#### GET /api/coursemangt/courseresources

- 作用：获取课程资源文件列表
- 调用链：`CourseResourcesController.getInstructionalProgram`
- SQL 次数：复用 `F1`
- 涉及表：复用 `F1`
- 类型常量：`courseresources`

#### POST /api/coursemangt/courseresources/upload

- 作用：上传课程资源文件
- 调用链：`CourseResourcesController.CourseResourceFileUpload`
- SQL 次数：复用 `F3`
- 涉及表：复用 `F3`
- 类型常量：`courseresources`

#### GET /api/coursemangt/courseresources/download/{fileName}

- 作用：下载课程资源文件
- 调用链：`CourseResourcesController.downloadFile`
- SQL 次数：复用 `F5` 的下载模式
- 涉及表：`cm_course_file`

#### GET /api/coursemangt/courseresources/delete/{fileName}

- 作用：删除课程资源文件
- 调用链：`CourseResourcesController.DeleteFile`
- SQL 次数：复用 `F5` 的删除模式
- 涉及表：`cm_course_file`

### 5.9 课程教案

#### GET /api/coursemangt/classroommangt/lessonplan

- 作用：获取课程教案文件列表
- 调用链：`LessonPlanController.getLessonPlan`
- SQL 次数：复用 `F2`
- 涉及表：复用 `F2`
- 类型常量：`lessonplan`

#### POST /api/coursemangt/classroommangt/lessonplan/upload

- 作用：上传课程教案文件
- 调用链：`LessonPlanController.FileUpload`
- SQL 次数：复用 `F4`
- 涉及表：复用 `F4`
- 类型常量：`lessonplan`

#### GET /api/coursemangt/classroommangt/lessonplan/download/{fileName}

- 作用：下载课程教案文件
- 调用链：`LessonPlanController.downloadFile`
- SQL 次数：复用 `F5` 的下载模式
- 涉及表：`cm_course_file`

#### GET /api/coursemangt/classroommangt/lessonplan/delete/{fileName}

- 作用：删除课程教案文件
- 调用链：`LessonPlanController.DeleteFile`
- SQL 次数：复用 `F5` 的删除模式
- 涉及表：`cm_course_file`
- 备注：虽然 Controller 里拿的是 `ACADEMIC_CALENDAR` handler，但删除逻辑最终仍然是按 `fileId` 读取 `cm_course_file` 后删除该元数据与对象

### 5.10 教学日历

#### GET /api/coursemangt/classroommangt/academiccalendar

- 作用：获取教学日历文件列表
- 调用链：`AcademicCalendarController.uploadLessonPlan`
- SQL 次数：复用 `F2`
- 涉及表：复用 `F2`
- 类型常量：`academiccalendar`

#### POST /api/coursemangt/classroommangt/academiccalendar/upload

- 作用：上传教学日历文件
- 调用链：`AcademicCalendarController.academicCalendarFileUpload`
- SQL 次数：复用 `F4`
- 涉及表：复用 `F4`
- 类型常量：`academiccalendar`

#### GET /api/coursemangt/classroommangt/academiccalendar/download/{fileName}

- 作用：下载教学日历文件
- 调用链：`AcademicCalendarController.downloadFile`
- SQL 次数：复用 `F5` 的下载模式
- 涉及表：`cm_course_file`

#### GET /api/coursemangt/classroommangt/academiccalendar/delete/{fileName}

- 作用：删除教学日历文件
- 调用链：`AcademicCalendarController.DeleteFile`
- SQL 次数：复用 `F5` 的删除模式
- 涉及表：`cm_course_file`

#### GET /api/coursemangt/classroommangt/academiccalendar/download/part/{fileName}

- 作用：按固定 MinIO 路径做分片下载测试
- 调用链：`AcademicCalendarController.downloadPart`
- SQL 次数：`0`
- 涉及表：无
- 备注：
  - 不查数据库
  - 使用硬编码的 bucket 和 objectName
  - 不依赖当前 token 的课程/课堂上下文

## 6. 涉及表汇总

本模块接口当前直接或间接访问到的主要表：

- `cm_term`
- `sm_obs`
- `st_level`
- `st_users`
- `st_roles`
- `st_roleuser`
- `sm_teacher`
- `sm_student`
- `cm_course`
- `cm_profession`
- `cm_classroom`
- `cm_classroom_student`
- `cm_classroom_classroommenu`
- `cm_classroom_homeworkinfo`
- `cm_classroom_mypracticelist`
- `cm_course_file`
- `cm_keywords`
- `cm_getability`
- `cm_kwadict`
- `cm_course_unit`
- `cm_course_unit_kwa`
- `cm_lines`
- `cm_course_target`
- `cm_course_target_kwa`
- `cm_course_checkitem`
- `cm_course_assessment`
- `ai_in_stu_ans_info_{orderno}`（动态创建）

## 7. 代码层面的实现备注

- `CourseStudentMangtController.deleteStudents` 实际删除 `cm_classroom_student` 时没有带课堂条件，行为比接口名更“重”
- `CmCourseServiceImpl.getCourse`、`CmClassRoomServiceImpl.getClassRoomList` 都存在典型 `1 + N` 查询问题
- 文件类接口的数据库操作都只落在 `cm_course_file`，文件二进制本体走 MinIO，不在 SQL 统计内
- `copyCourseInfo` 是最复杂的接口，写库范围覆盖课程画像、知识图谱、目标、考核方案等多个表
