/*
 Navicat Premium Dump SQL

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80030 (8.0.30)
 Source Host           : localhost:3306
 Source Schema         : smarttt_rebuild

 Target Server Type    : MySQL
 Target Server Version : 80030 (8.0.30)
 File Encoding         : 65001

 Date: 19/04/2026 14:27:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ai_calculate_paper
-- ----------------------------
DROP TABLE IF EXISTS `ai_calculate_paper`;
CREATE TABLE `ai_calculate_paper` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `paper_id` varchar(255) DEFAULT NULL COMMENT '试卷id',
  `classroom_id` varchar(255) DEFAULT NULL COMMENT '课堂id',
  `test_id` varchar(255) DEFAULT NULL COMMENT '测试id',
  `course_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程名称',
  `classroom_name` varchar(155) DEFAULT NULL COMMENT '课堂名称',
  `test_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '考试名称',
  `paper_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '试卷名称',
  `catelog` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '试卷类型(1作业2考试)',
  `creator` varchar(255) DEFAULT NULL COMMENT '试卷创建人',
  `create_time` varchar(255) DEFAULT NULL COMMENT '试卷创建时间',
  `row` bigint DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='形成性评价试卷配置表，可以选择哪些试卷参与评价。';

-- ----------------------------
-- Records of ai_calculate_paper
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for ai_classroom_portrait_ability
-- ----------------------------
DROP TABLE IF EXISTS `ai_classroom_portrait_ability`;
CREATE TABLE `ai_classroom_portrait_ability` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_id` varchar(255) DEFAULT NULL COMMENT '课程id',
  `classroom_id` varchar(255) DEFAULT NULL COMMENT '课堂id',
  `ability_id` varchar(255) DEFAULT NULL COMMENT '能力id',
  `ability_name` varchar(255) DEFAULT NULL COMMENT '能力名称',
  `datavalue` int DEFAULT NULL COMMENT '期望值',
  `stu_datavalue` varchar(25) DEFAULT NULL COMMENT '学生得分率',
  `num` int DEFAULT NULL COMMENT '评价次数',
  `import_level` int DEFAULT NULL COMMENT '重要程度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9641 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课堂能力的形成性评价结果表';

-- ----------------------------
-- Records of ai_classroom_portrait_ability
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for ai_classroom_portrait_keyword
-- ----------------------------
DROP TABLE IF EXISTS `ai_classroom_portrait_keyword`;
CREATE TABLE `ai_classroom_portrait_keyword` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `keyword_id` varchar(255) DEFAULT NULL COMMENT '关键字id',
  `keyword_name` varchar(255) DEFAULT NULL COMMENT '关键字名称',
  `course_id` varchar(255) DEFAULT NULL COMMENT '课程id',
  `classroom_id` varchar(255) DEFAULT NULL COMMENT '课堂id',
  `num` int DEFAULT NULL COMMENT '评价次数',
  `datavalue` int DEFAULT NULL COMMENT '期望值',
  `stu_datavalue` varchar(25) DEFAULT NULL COMMENT '学生得分率',
  `import_level` int DEFAULT NULL COMMENT '重要程度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23765 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课堂关键字的形成性评价结果表';

-- ----------------------------
-- Records of ai_classroom_portrait_keyword
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for ai_classroom_portrait_unit
-- ----------------------------
DROP TABLE IF EXISTS `ai_classroom_portrait_unit`;
CREATE TABLE `ai_classroom_portrait_unit` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `unit_id` varchar(255) DEFAULT NULL COMMENT '知识单元id',
  `unit_name` varchar(255) DEFAULT NULL COMMENT '知识单元名称',
  `type` varchar(255) DEFAULT NULL,
  `order_num` varchar(255) DEFAULT NULL,
  `pid` varchar(255) DEFAULT NULL,
  `datavalue` int DEFAULT NULL,
  `stu_datavalue` varchar(25) DEFAULT NULL,
  `classroom_id` varchar(255) DEFAULT NULL,
  `course_id` varchar(255) DEFAULT NULL,
  `num` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14887 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='课堂知识单元的形成性评价结果表';

-- ----------------------------
-- Records of ai_classroom_portrait_unit
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for ai_in_stu_ans_info
-- ----------------------------
DROP TABLE IF EXISTS `ai_in_stu_ans_info`;
CREATE TABLE `ai_in_stu_ans_info` (
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
  `v_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '思政价值id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `aisai_index_ti_si_li` (`testId`,`libId`,`stuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='学生作业试题详细信息日志表,颗粒度为试题维度';

-- ----------------------------
-- Records of ai_in_stu_ans_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for ai_portrait_total
-- ----------------------------
DROP TABLE IF EXISTS `ai_portrait_total`;
CREATE TABLE `ai_portrait_total` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_id` varchar(255) DEFAULT NULL COMMENT '课程id',
  `classroom_id` varchar(255) DEFAULT NULL COMMENT '课堂id',
  `total_nums` int DEFAULT NULL COMMENT '评价总次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='记录课堂下形成性评价的总次数表，这个次数是和学生形成性评价一样的';

-- ----------------------------
-- Records of ai_portrait_total
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for ai_stu_portrait_ability
-- ----------------------------
DROP TABLE IF EXISTS `ai_stu_portrait_ability`;
CREATE TABLE `ai_stu_portrait_ability` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ability_id` varchar(255) DEFAULT NULL COMMENT '能力id',
  `ability_name` varchar(255) DEFAULT NULL COMMENT '能力名称',
  `datavalue` int DEFAULT '0' COMMENT '能力值',
  `stu_datavalue` varchar(25) DEFAULT NULL COMMENT '学生得分率',
  `stu_id` varchar(255) DEFAULT NULL COMMENT '学生Id',
  `num` int DEFAULT NULL COMMENT '评价次数',
  `classroom_id` varchar(255) DEFAULT NULL COMMENT '课堂id',
  `course_id` varchar(255) DEFAULT NULL COMMENT '课程id',
  `import_level` int DEFAULT NULL COMMENT '重要程度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=104136 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生能力的形成性评价结果表';

-- ----------------------------
-- Records of ai_stu_portrait_ability
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for ai_stu_portrait_keyword
-- ----------------------------
DROP TABLE IF EXISTS `ai_stu_portrait_keyword`;
CREATE TABLE `ai_stu_portrait_keyword` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `keyword_id` varchar(255) DEFAULT NULL COMMENT '关键字id',
  `keyword_name` varchar(255) DEFAULT NULL COMMENT '关键字',
  `datavalue` int DEFAULT NULL COMMENT '关键字值',
  `stu_datavalue` varchar(25) DEFAULT NULL COMMENT '学生得分率',
  `stu_id` varchar(255) DEFAULT NULL COMMENT '学生id',
  `import_level` int DEFAULT NULL COMMENT '重要程度',
  `num` int DEFAULT NULL COMMENT '评价次数',
  `classroom_id` varchar(255) DEFAULT NULL COMMENT '课堂id',
  `course_id` varchar(255) DEFAULT NULL COMMENT '课程id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=291200 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生关键字的形成性评价结果表';

-- ----------------------------
-- Records of ai_stu_portrait_keyword
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for ai_stu_portrait_unit
-- ----------------------------
DROP TABLE IF EXISTS `ai_stu_portrait_unit`;
CREATE TABLE `ai_stu_portrait_unit` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `stu_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `unit_id` varchar(255) DEFAULT NULL COMMENT '知识单元id',
  `unit_name` varchar(255) DEFAULT NULL COMMENT '知识单元名称',
  `type` varchar(255) DEFAULT NULL,
  `order_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `pid` varchar(255) DEFAULT NULL,
  `datavalue` int DEFAULT NULL,
  `stu_datavalue` varchar(25) DEFAULT NULL,
  `classroom_id` varchar(255) DEFAULT NULL,
  `course_id` varchar(255) DEFAULT NULL,
  `num` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101318 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生知识单元的形成性评价结果表';

-- ----------------------------
-- Records of ai_stu_portrait_unit
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for classroom_ideology_achievements
-- ----------------------------
DROP TABLE IF EXISTS `classroom_ideology_achievements`;
CREATE TABLE `classroom_ideology_achievements` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `course_id` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '课程ID，外键引用cm_course.id',
  `v_id` varchar(64) NOT NULL COMMENT '思政价值id，外键引用v_ideology_value.id',
  `classroom_id` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '课堂id，外键引用cm_classroom.id',
  `value_count` int NOT NULL DEFAULT '0' COMMENT '思政个数',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  KEY `v_id` (`v_id`),
  KEY `classroom_id` (`classroom_id`),
  KEY `idx_classroom_ideology_value` (`classroom_id`,`v_id`),
  CONSTRAINT `classroom_ideology_achievements_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `cm_course` (`id`) ON DELETE CASCADE,
  CONSTRAINT `classroom_ideology_achievements_ibfk_2` FOREIGN KEY (`v_id`) REFERENCES `v_ideology_value` (`id`) ON DELETE CASCADE,
  CONSTRAINT `classroom_ideology_achievements_ibfk_3` FOREIGN KEY (`classroom_id`) REFERENCES `cm_classroom` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课堂思政评价结果表';

-- ----------------------------
-- Records of classroom_ideology_achievements
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_ability
-- ----------------------------
DROP TABLE IF EXISTS `cm_ability`;
CREATE TABLE `cm_ability` (
  `id` varchar(255) NOT NULL COMMENT '标识',
  `pid` varchar(255) DEFAULT NULL COMMENT '父节点',
  `professionid` varchar(255) DEFAULT NULL,
  `orderno` mediumtext COMMENT '顺序号',
  `abilitydeep` mediumtext COMMENT '深度',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `datavalue` varchar(255) DEFAULT NULL COMMENT '数值',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `levelcode` varchar(255) DEFAULT NULL COMMENT '层级码',
  `importantlevel` varchar(255) DEFAULT NULL COMMENT '重要程度',
  PRIMARY KEY (`id`),
  KEY `fk_ability_obs` (`professionid`),
  CONSTRAINT `fk_ability_obs` FOREIGN KEY (`professionid`) REFERENCES `sm_obs` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='专业能力字典表';

-- ----------------------------
-- Records of cm_ability
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_class
-- ----------------------------
DROP TABLE IF EXISTS `cm_class`;
CREATE TABLE `cm_class` (
  `id` varchar(255) NOT NULL COMMENT '标识',
  `obsid` varchar(255) DEFAULT NULL COMMENT '机构ID\n',
  `classname` varchar(255) DEFAULT NULL COMMENT '班级名\n',
  `grade` varchar(255) DEFAULT NULL COMMENT '年级',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `fk_class_obs` (`obsid`),
  CONSTRAINT `fk_class_obs` FOREIGN KEY (`obsid`) REFERENCES `sm_obs` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='课表';

-- ----------------------------
-- Records of cm_class
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_classroom
-- ----------------------------
DROP TABLE IF EXISTS `cm_classroom`;
CREATE TABLE `cm_classroom` (
  `id` varchar(64) NOT NULL COMMENT '标识',
  `classroomName` varchar(255) DEFAULT NULL COMMENT '课堂名称',
  `termId` varchar(64) DEFAULT NULL COMMENT '学期Id',
  `courseId` varchar(255) DEFAULT NULL COMMENT '课程Id',
  `teacherId` varchar(255) DEFAULT NULL COMMENT '主讲教师Id',
  `assistantId` varchar(255) DEFAULT NULL COMMENT '助教Id',
  `usedClassList` varchar(255) DEFAULT NULL COMMENT 'json结构存储',
  `teachingProgram` varchar(255) DEFAULT NULL COMMENT '附件',
  `studentCount` int DEFAULT NULL COMMENT '学员数',
  `beginTime` varchar(19) DEFAULT NULL COMMENT '开始时间',
  `endTime` varchar(19) DEFAULT NULL COMMENT '结束时间',
  `remark` varchar(19) DEFAULT NULL COMMENT '备注',
  `unitCount` int DEFAULT NULL COMMENT '知识单元数',
  `relationCount` int DEFAULT NULL COMMENT '单元关联数',
  `targetCount` int DEFAULT NULL COMMENT '课程目标数',
  `teachingProgramUploadFileName` varchar(255) DEFAULT NULL COMMENT '附件名字',
  `teacherName` varchar(255) DEFAULT NULL COMMENT '主讲教师姓名',
  `assistantName` varchar(255) DEFAULT NULL COMMENT '助教姓名',
  `studentOnline` int DEFAULT NULL COMMENT '在线学生',
  `teachingProgram1` varchar(255) DEFAULT NULL COMMENT '教学计划1',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `creatorName` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `labTeacherId` varchar(64) DEFAULT NULL COMMENT '实验教师Id',
  `labTeacher` varchar(64) DEFAULT NULL COMMENT '实验教师',
  `practiceTeacherId` varchar(64) DEFAULT NULL COMMENT '实践教师Id',
  `practiceTeacher` varchar(64) DEFAULT NULL COMMENT '实践教师',
  `teachTime` int DEFAULT NULL COMMENT '讲授学时',
  `labTime` int DEFAULT NULL COMMENT '实验学时',
  `practiceTime` int DEFAULT NULL COMMENT '实践学时',
  `unitrelation` mediumtext COMMENT '知识单元关联',
  `diagramInfo` mediumtext COMMENT '图表信息',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_classroom_courseid` (`courseId`),
  CONSTRAINT `fk_classroom_courseid` FOREIGN KEY (`courseId`) REFERENCES `cm_course` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='课堂表';

-- ----------------------------
-- Records of cm_classroom
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_classroom_classroommenu
-- ----------------------------
DROP TABLE IF EXISTS `cm_classroom_classroommenu`;
CREATE TABLE `cm_classroom_classroommenu` (
  `id` varchar(64) NOT NULL,
  `stuId` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `classroomId` varchar(64) DEFAULT NULL COMMENT '在教师端选择课堂学员的时候复制过来',
  `taskCount` int DEFAULT NULL,
  `menuId` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_classmenus_stu_userid` (`stuId`),
  CONSTRAINT `fk_classmenus_stu_userid` FOREIGN KEY (`stuId`) REFERENCES `st_users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of cm_classroom_classroommenu
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_classroom_homeworkinfo
-- ----------------------------
DROP TABLE IF EXISTS `cm_classroom_homeworkinfo`;
CREATE TABLE `cm_classroom_homeworkinfo` (
  `id` varchar(64) NOT NULL COMMENT '标识',
  `classroomId` varchar(64) DEFAULT NULL COMMENT '课堂Id',
  `stuId` varchar(64) DEFAULT NULL COMMENT '在教师端选择课堂学员的时候复制过来',
  `publishTimes` int DEFAULT NULL COMMENT '已布置作业次数',
  `finishedTimes` int DEFAULT NULL COMMENT '完成次数',
  `finishedPercent` decimal(8,2) DEFAULT NULL COMMENT '完成率',
  `correctPercent` decimal(8,2) DEFAULT NULL COMMENT '平均正确数',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_classhomework_stu_userid` (`stuId`),
  CONSTRAINT `fk_classhomework_stu_userid` FOREIGN KEY (`stuId`) REFERENCES `st_users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='模块四: 学生作业统计信息表, 记录学生历史完成次数 完成率和平均正确数';

-- ----------------------------
-- Records of cm_classroom_homeworkinfo
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_classroom_mypracticelist
-- ----------------------------
DROP TABLE IF EXISTS `cm_classroom_mypracticelist`;
CREATE TABLE `cm_classroom_mypracticelist` (
  `id` varchar(64) NOT NULL COMMENT '标识',
  `classroomId` varchar(64) DEFAULT NULL COMMENT '课堂Id',
  `testId` varchar(255) DEFAULT NULL COMMENT '作业Id',
  `paperId` varchar(64) DEFAULT NULL COMMENT '试卷Id',
  `stuId` varchar(64) DEFAULT NULL COMMENT '学生Id',
  `finishedCount` int DEFAULT NULL COMMENT '已完成人数',
  `unFinishedCount` int DEFAULT NULL COMMENT '未完成人数',
  `myFinishStatus` int DEFAULT NULL COMMENT '完成状态  1.完成  2.未完成',
  `submitNo` int DEFAULT NULL COMMENT '完成顺序号',
  `correctCount` int DEFAULT NULL COMMENT '正确题数',
  `errorCount` int DEFAULT NULL COMMENT '错误题数',
  `correctPercent` decimal(8,2) DEFAULT NULL COMMENT '正确率',
  `resultLevel` varchar(255) DEFAULT NULL COMMENT '成绩等级  1-优秀，2-良好，3-合格，4-不合格',
  `title` varchar(255) DEFAULT NULL COMMENT '题目',
  `beginTime` varchar(255) DEFAULT NULL COMMENT '布置时间',
  `endTime` varchar(255) DEFAULT NULL COMMENT '结束时间',
  `myAnswers` mediumtext COMMENT '我的答卷',
  `submitTime` varchar(19) DEFAULT NULL COMMENT '提交试卷时间',
  `catelog` varchar(64) DEFAULT NULL COMMENT '作业和考试分类  1-作业，2-考试',
  `resultScore` int DEFAULT NULL COMMENT '成绩得分',
  `checkResult` mediumtext COMMENT '批阅结果',
  `checkerId` varchar(64) DEFAULT NULL COMMENT '批阅人Id',
  `checkTime` varchar(19) DEFAULT NULL COMMENT '提交阅卷时间',
  `beginAnswerTime` varchar(19) DEFAULT NULL COMMENT '开始答卷时间',
  `publishTime` varchar(19) DEFAULT NULL COMMENT '发布时间',
  `state` int DEFAULT '1' COMMENT '1=正常 2=已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `ccm_index_ti_si` (`testId`,`stuId`),
  KEY `fk_classpractice_stu_userid` (`stuId`),
  CONSTRAINT `fk_classpractice_stu_userid` FOREIGN KEY (`stuId`) REFERENCES `st_users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='模块四: 学生作业试卷信息和批改信息表,颗粒度为作业试卷';

-- ----------------------------
-- Records of cm_classroom_mypracticelist
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_classroom_student
-- ----------------------------
DROP TABLE IF EXISTS `cm_classroom_student`;
CREATE TABLE `cm_classroom_student` (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标识',
  `classroomId` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '课堂id',
  `userId` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '学员id',
  `obsId` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '班级id',
  `userName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '姓名',
  `obsName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '班级',
  `proName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '专业名称',
  `loginname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '登录名称',
  `rowNo` int DEFAULT NULL COMMENT '序号',
  `courseScore` decimal(9,2) DEFAULT NULL COMMENT '课程综合得分',
  `dynamic_state` tinyint DEFAULT '1' COMMENT '是否参与形成性评价（0不是1是）',
  `reach_state` tinyint DEFAULT '1' COMMENT '是否参与达成性评价（0不是1是）',
  `ideology_state` tinyint DEFAULT '1' COMMENT '是否参与思想价值评价',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_classroom_student_classroomid` (`classroomId`),
  KEY `fk_class_stu_userid` (`userId`),
  KEY `fk_classstu_obs` (`obsId`),
  CONSTRAINT `fk_class_stu_userid` FOREIGN KEY (`userId`) REFERENCES `st_users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_classroom_student_classroomid` FOREIGN KEY (`classroomId`) REFERENCES `cm_classroom` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_classstu_obs` FOREIGN KEY (`obsId`) REFERENCES `sm_obs` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of cm_classroom_student
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_classroom_target_achievement
-- ----------------------------
DROP TABLE IF EXISTS `cm_classroom_target_achievement`;
CREATE TABLE `cm_classroom_target_achievement` (
  `id` varchar(255) NOT NULL COMMENT '标识',
  `stuId` varchar(255) DEFAULT NULL COMMENT '学生userId',
  `classroomId` varchar(255) DEFAULT NULL COMMENT '班级id',
  `targetId` varchar(255) DEFAULT NULL COMMENT '课程目标id',
  `degree` float DEFAULT NULL COMMENT '课程目标达成度',
  PRIMARY KEY (`id`),
  KEY `fk_target_user` (`stuId`),
  CONSTRAINT `fk_target_user` FOREIGN KEY (`stuId`) REFERENCES `st_users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='课程目标的达成度表';

-- ----------------------------
-- Records of cm_classroom_target_achievement
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_classroom_total_score
-- ----------------------------
DROP TABLE IF EXISTS `cm_classroom_total_score`;
CREATE TABLE `cm_classroom_total_score` (
  `id` varchar(255) NOT NULL COMMENT '标识',
  `stuId` varchar(255) DEFAULT NULL COMMENT '学生的userId',
  `classroomId` varchar(255) DEFAULT NULL COMMENT '课堂id',
  `checkitemId` varchar(255) DEFAULT NULL COMMENT '考核项id',
  `ratio` float DEFAULT NULL COMMENT '考核项分数在总评分数的占比',
  `checkItemScore` float DEFAULT NULL COMMENT '考核项的成绩',
  PRIMARY KEY (`id`),
  KEY `fk_score_user` (`stuId`),
  CONSTRAINT `fk_score_user` FOREIGN KEY (`stuId`) REFERENCES `st_users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='课堂总评成绩表';

-- ----------------------------
-- Records of cm_classroom_total_score
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_course
-- ----------------------------
DROP TABLE IF EXISTS `cm_course`;
CREATE TABLE `cm_course` (
  `id` varchar(64) NOT NULL COMMENT '标识',
  `courseChineseName` varchar(255) DEFAULT NULL COMMENT '课程名称（中文）',
  `courseEnglishName` varchar(255) DEFAULT NULL COMMENT '课程名称（英文）',
  `courseCode` varchar(255) DEFAULT NULL COMMENT '课程代码',
  `teachingProgram` varchar(255) DEFAULT NULL COMMENT '教学大纲',
  `credit` decimal(8,2) DEFAULT NULL COMMENT '学分',
  `dutyManId` varchar(255) DEFAULT NULL COMMENT '课程负责人Id',
  `dutyMan` varchar(255) DEFAULT NULL COMMENT '课程负责人',
  `verNo` varchar(255) DEFAULT NULL COMMENT '版本',
  `hourInClass` decimal(8,2) DEFAULT NULL COMMENT '课程计划学时',
  `hourOutside` decimal(8,2) DEFAULT NULL COMMENT '课外学时建议',
  `electiveOrRequired` varchar(255) DEFAULT NULL COMMENT '课程属性',
  `theoryOfflineHour` decimal(8,2) DEFAULT NULL COMMENT '线下理论学时',
  `theoryOnlineHour` decimal(8,2) DEFAULT NULL COMMENT '线上理论学时',
  `experimentHour` decimal(8,2) DEFAULT NULL COMMENT '实验学时',
  `hourOnline` decimal(8,2) DEFAULT NULL COMMENT '线上学习要求学时',
  `hourOffline` decimal(8,2) DEFAULT NULL COMMENT '线下自主学习要求',
  `firstCourse` varchar(255) DEFAULT NULL COMMENT '先修课程名称',
  `professionalGrade` varchar(255) DEFAULT NULL COMMENT '适用专业年级',
  `summary` mediumtext COMMENT '课程简介',
  `learningResource` mediumtext COMMENT '教材和学习资源',
  `markPicture` varchar(255) DEFAULT NULL COMMENT '服务器相对路径名称',
  `attachFileName` varchar(255) DEFAULT NULL COMMENT '服务器相对路径名称',
  `schooltermId` varchar(64) DEFAULT NULL COMMENT '学期Id',
  `markPictureUploadFileName` varchar(255) DEFAULT NULL COMMENT '下载使用',
  `questionNumber` varchar(255) DEFAULT NULL COMMENT '题库数量',
  `attachFileNameUploadFileName` varchar(255) DEFAULT NULL COMMENT '下载使用',
  `openTimes` int DEFAULT NULL COMMENT '开课次数',
  `firstTime` varchar(255) DEFAULT NULL COMMENT '首次开课时间',
  `lastTime` varchar(255) DEFAULT NULL COMMENT '最近开课时间',
  `professionId` varchar(64) DEFAULT NULL COMMENT '所属专业Id',
  `professionName` varchar(255) DEFAULT NULL COMMENT '所属专业',
  `createtime` varchar(255) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_course_profession` (`professionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='课程表';

-- ----------------------------
-- Records of cm_course
-- ----------------------------
BEGIN;
INSERT INTO `cm_course` (`id`, `courseChineseName`, `courseEnglishName`, `courseCode`, `teachingProgram`, `credit`, `dutyManId`, `dutyMan`, `verNo`, `hourInClass`, `hourOutside`, `electiveOrRequired`, `theoryOfflineHour`, `theoryOnlineHour`, `experimentHour`, `hourOnline`, `hourOffline`, `firstCourse`, `professionalGrade`, `summary`, `learningResource`, `markPicture`, `attachFileName`, `schooltermId`, `markPictureUploadFileName`, `questionNumber`, `attachFileNameUploadFileName`, `openTimes`, `firstTime`, `lastTime`, `professionId`, `professionName`, `createtime`) VALUES ('123', '实验系统课程', 'expcourse	1111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for cm_course_assessment
-- ----------------------------
DROP TABLE IF EXISTS `cm_course_assessment`;
CREATE TABLE `cm_course_assessment` (
  `id` varchar(64) NOT NULL COMMENT '标识',
  `courseId` varchar(64) DEFAULT NULL COMMENT '课程Id',
  `coursetargetId` varchar(64) DEFAULT NULL COMMENT '课程目标Id',
  `checkitemId` varchar(64) DEFAULT NULL COMMENT '考核项Id',
  `taskId` varchar(64) DEFAULT NULL COMMENT '考核任务Id',
  `standardScore` int DEFAULT NULL COMMENT '目标分数',
  `nProportion` varchar(255) DEFAULT NULL COMMENT 'n比例',
  `eProportion` varchar(255) DEFAULT NULL COMMENT 'e比例',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_course_assessment_courseid` (`courseId`),
  CONSTRAINT `fk_course_assessment_courseid` FOREIGN KEY (`courseId`) REFERENCES `cm_course` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='课程考核方案表';

-- ----------------------------
-- Records of cm_course_assessment
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_course_assessment_checkitem_file
-- ----------------------------
DROP TABLE IF EXISTS `cm_course_assessment_checkitem_file`;
CREATE TABLE `cm_course_assessment_checkitem_file` (
  `id` varchar(255) NOT NULL COMMENT '标识',
  `checkitemId` varchar(255) DEFAULT NULL COMMENT '考核项id',
  `fileId` varchar(255) DEFAULT NULL COMMENT '关联的作业/测试/实验id',
  `classroomId` varchar(255) DEFAULT NULL COMMENT '课堂ID',
  `type` int DEFAULT NULL COMMENT '1-作业 2-测试 3-实验 4-用户自己上传的成绩',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='课程考核方案中考核项与其关联的文件表';

-- ----------------------------
-- Records of cm_course_assessment_checkitem_file
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_course_assessment_file
-- ----------------------------
DROP TABLE IF EXISTS `cm_course_assessment_file`;
CREATE TABLE `cm_course_assessment_file` (
  `id` varchar(255) NOT NULL COMMENT '标识',
  `classroomId` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL COMMENT '课堂ID',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL COMMENT '文件名',
  `createTime` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='考核方案用户上传的文件的信息';

-- ----------------------------
-- Records of cm_course_assessment_file
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_course_assessment_filedata
-- ----------------------------
DROP TABLE IF EXISTS `cm_course_assessment_filedata`;
CREATE TABLE `cm_course_assessment_filedata` (
  `id` varchar(255) NOT NULL COMMENT '标识',
  `fileId` varchar(255) DEFAULT NULL COMMENT '对应上传文件的id',
  `stuno` varchar(255) DEFAULT NULL COMMENT '学号',
  `score` float DEFAULT NULL COMMENT '分数',
  `rowNo` int DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`id`),
  KEY `cm_course_assessment_filedata_cm_course_assessment_file_id_fk` (`fileId`),
  CONSTRAINT `cm_course_assessment_filedata_cm_course_assessment_file_id_fk` FOREIGN KEY (`fileId`) REFERENCES `cm_course_assessment_file` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='考核方案用户上传的文件数据';

-- ----------------------------
-- Records of cm_course_assessment_filedata
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_course_checkitem
-- ----------------------------
DROP TABLE IF EXISTS `cm_course_checkitem`;
CREATE TABLE `cm_course_checkitem` (
  `id` varchar(255) NOT NULL COMMENT '标识',
  `courseid` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT 'id',
  `pid` varchar(255) DEFAULT NULL COMMENT '父节点id',
  `orderno` char(255) DEFAULT NULL COMMENT '顺序号',
  `itemName` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '考核项',
  `itemCode` varchar(255) DEFAULT NULL COMMENT '层级码',
  `task` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `percent` varchar(255) DEFAULT NULL COMMENT '总评占比',
  PRIMARY KEY (`id`),
  KEY `fk_checkitem_course` (`courseid`),
  CONSTRAINT `fk_checkitem_course` FOREIGN KEY (`courseid`) REFERENCES `cm_course` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='课程考核项表';

-- ----------------------------
-- Records of cm_course_checkitem
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_course_file
-- ----------------------------
DROP TABLE IF EXISTS `cm_course_file`;
CREATE TABLE `cm_course_file` (
  `id` varchar(255) NOT NULL,
  `obsid` varchar(255) DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  `size` double DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `object_name` varchar(255) DEFAULT NULL,
  `bucket_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of cm_course_file
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_course_target
-- ----------------------------
DROP TABLE IF EXISTS `cm_course_target`;
CREATE TABLE `cm_course_target` (
  `id` varchar(255) NOT NULL COMMENT '标识',
  `code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '代码',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '课程目标名称',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `courseid` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '璇剧▼Id',
  `s1` int(11) unsigned zerofill DEFAULT NULL,
  `s2` int(11) unsigned zerofill DEFAULT NULL,
  `s3` int(11) unsigned zerofill DEFAULT NULL,
  `s4` int(11) unsigned zerofill DEFAULT NULL,
  `s5` int(11) unsigned zerofill DEFAULT NULL,
  `unitname` varchar(255) DEFAULT NULL COMMENT '基本教学目标',
  `unitid` varchar(255) DEFAULT NULL COMMENT '基本教学目标Id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `fk_target_course` (`courseid`),
  CONSTRAINT `fk_target_course` FOREIGN KEY (`courseid`) REFERENCES `cm_course` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='课程目标表';

-- ----------------------------
-- Records of cm_course_target
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_course_target_kwa
-- ----------------------------
DROP TABLE IF EXISTS `cm_course_target_kwa`;
CREATE TABLE `cm_course_target_kwa` (
  `id` varchar(255) DEFAULT NULL COMMENT '标识',
  `kwaId` varchar(255) NOT NULL COMMENT 'kwa的id',
  `targetId` varchar(255) NOT NULL COMMENT '课程目标id',
  `obsId` varchar(255) DEFAULT NULL COMMENT '教学单位id',
  PRIMARY KEY (`targetId`,`kwaId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='课程目标对应的kwa表';

-- ----------------------------
-- Records of cm_course_target_kwa
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_course_target_unit
-- ----------------------------
DROP TABLE IF EXISTS `cm_course_target_unit`;
CREATE TABLE `cm_course_target_unit` (
  `id` varchar(255) NOT NULL COMMENT '标识',
  `unitid` varchar(255) DEFAULT NULL COMMENT '基本教学目标Id',
  `unitname` varchar(255) DEFAULT NULL COMMENT '基本教学目标',
  `targetid` varchar(255) DEFAULT NULL COMMENT '课程目标Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='课程目标_知识单元表';

-- ----------------------------
-- Records of cm_course_target_unit
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_course_unit
-- ----------------------------
DROP TABLE IF EXISTS `cm_course_unit`;
CREATE TABLE `cm_course_unit` (
  `id` varchar(64) NOT NULL COMMENT '标识',
  `courseId` varchar(64) DEFAULT NULL COMMENT '课程Id',
  `pId` varchar(64) DEFAULT NULL COMMENT '父节点Id',
  `orderNum` int DEFAULT NULL COMMENT '顺序号',
  `code` varchar(255) DEFAULT NULL COMMENT '代码',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `nodeType` varchar(64) DEFAULT NULL COMMENT '章节目',
  `teachingMode` varchar(255) DEFAULT NULL COMMENT '教学模式',
  `teachingResources` varchar(255) DEFAULT NULL COMMENT '教学资源',
  `relationNodeCount` int DEFAULT NULL COMMENT '关系节点数量',
  `parent` varchar(255) DEFAULT NULL COMMENT '父节点',
  `dataValue` decimal(8,2) DEFAULT NULL COMMENT '数值',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_course_unit_courseid` (`courseId`),
  CONSTRAINT `fk_course_unit_courseid` FOREIGN KEY (`courseId`) REFERENCES `cm_course` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='课程知识单元表';

-- ----------------------------
-- Records of cm_course_unit
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_course_unit_kwa
-- ----------------------------
DROP TABLE IF EXISTS `cm_course_unit_kwa`;
CREATE TABLE `cm_course_unit_kwa` (
  `id` varchar(64) NOT NULL COMMENT '标识',
  `unitId` varchar(64) DEFAULT NULL COMMENT '基本教学目标Id',
  `kwaId` varchar(64) DEFAULT NULL COMMENT 'KWAId',
  `datavalue` varchar(255) DEFAULT NULL COMMENT '数值',
  `sourceKWAId` varchar(64) DEFAULT NULL COMMENT '源KWAId',
  `sourceJson` varchar(255) DEFAULT NULL COMMENT '源Json',
  `destKWAId` varchar(64) DEFAULT NULL COMMENT '目标KWAId',
  `destJson` varchar(255) DEFAULT NULL COMMENT '目标Json',
  `linkJson` varchar(255) DEFAULT NULL COMMENT '关系Json',
  `sourceKWAName` varchar(255) DEFAULT NULL COMMENT '源KWA',
  `destKWAName` varchar(255) DEFAULT NULL COMMENT '目标KWA',
  `status` int DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='课程知识单元_kwa表';

-- ----------------------------
-- Records of cm_course_unit_kwa
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_course_unit_v_values
-- ----------------------------
DROP TABLE IF EXISTS `cm_course_unit_v_values`;
CREATE TABLE `cm_course_unit_v_values` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `unitId` varchar(64) NOT NULL COMMENT '知识单元ID，关联cm_course_unit表',
  `vId` varchar(64) NOT NULL COMMENT 'v值ID，关联v_ideology_value表',
  `status` int DEFAULT '0' COMMENT '状态：0-未完成，1-已完成',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_unitId_vId` (`unitId`,`vId`),
  KEY `idx_unitId` (`unitId`),
  KEY `idx_vId` (`vId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='知识单元与v值关联表';

-- ----------------------------
-- Records of cm_course_unit_v_values
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_course_usestate
-- ----------------------------
DROP TABLE IF EXISTS `cm_course_usestate`;
CREATE TABLE `cm_course_usestate` (
  `id` varchar(64) NOT NULL COMMENT '标识',
  `courseId` varchar(255) DEFAULT NULL COMMENT '课程Id',
  `testQuestionCount` varchar(255) DEFAULT NULL COMMENT '题库数量',
  `useTimes` int DEFAULT NULL COMMENT '开课次数',
  `firstUseTime` varchar(19) DEFAULT NULL COMMENT '首次开课时间',
  `lastUseTime` varchar(19) DEFAULT NULL COMMENT '最近开课时间',
  `kwacount` int DEFAULT NULL COMMENT '关联KWA数量',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_course_usestate_courseid` (`courseId`),
  CONSTRAINT `fk_course_usestate_courseid` FOREIGN KEY (`courseId`) REFERENCES `cm_course` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='课程使用情况表';

-- ----------------------------
-- Records of cm_course_usestate
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_getability
-- ----------------------------
DROP TABLE IF EXISTS `cm_getability`;
CREATE TABLE `cm_getability` (
  `id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '标识',
  `orderno` bigint NOT NULL COMMENT '顺序号',
  `abilitydeep` bigint NOT NULL COMMENT '深度',
  `levelcode` varchar(255) DEFAULT NULL COMMENT '层级码',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '能力名称',
  `datavalue` varchar(255) DEFAULT NULL COMMENT '数值',
  `importantlevel` varchar(255) DEFAULT NULL COMMENT '重要程度',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `courseid` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '课程Id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_getability_courseid` (`courseid`),
  CONSTRAINT `fk_getability_courseid` FOREIGN KEY (`courseid`) REFERENCES `cm_course` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='课程能力表';

-- ----------------------------
-- Records of cm_getability
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_keywords
-- ----------------------------
DROP TABLE IF EXISTS `cm_keywords`;
CREATE TABLE `cm_keywords` (
  `id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '标识',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '名称',
  `datavalue` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '数值',
  `importantlevelid` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '重要程度',
  `remark` mediumtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '备注',
  `courseid` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '璇剧▼Id',
  `code` varchar(255) DEFAULT NULL COMMENT '代码',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_keywords_course` (`courseid`),
  CONSTRAINT `fk_keywords_course` FOREIGN KEY (`courseid`) REFERENCES `cm_course` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='关键字表';

-- ----------------------------
-- Records of cm_keywords
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_kwadict
-- ----------------------------
DROP TABLE IF EXISTS `cm_kwadict`;
CREATE TABLE `cm_kwadict` (
  `id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '标识',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '名称',
  `keywordid` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '关键字Id',
  `abilityid` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '能力Id',
  `courseid` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '课程Id',
  `datavalue` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '数值',
  `status` varchar(50) DEFAULT NULL COMMENT '状态',
  `keywordcode` varchar(255) DEFAULT NULL COMMENT '关键字代码',
  `abilitycode` varchar(255) DEFAULT NULL COMMENT '能力代码',
  `code` varchar(255) DEFAULT NULL COMMENT 'kwa代码',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_kwadict_keywordid` (`keywordid`),
  KEY `fk_kwadict_abilityid` (`abilityid`),
  KEY `fk_kwadict_courseid` (`courseid`),
  CONSTRAINT `fk_kwadict_courseid` FOREIGN KEY (`courseid`) REFERENCES `cm_course` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='kwa表';

-- ----------------------------
-- Records of cm_kwadict
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_lines
-- ----------------------------
DROP TABLE IF EXISTS `cm_lines`;
CREATE TABLE `cm_lines` (
  `id` varchar(255) NOT NULL COMMENT '标识',
  `startunitid` varchar(255) DEFAULT NULL COMMENT '头unit节点Id',
  `endunitid` varchar(255) DEFAULT NULL COMMENT '尾unit节点Id',
  `remark` text COMMENT '备注',
  `courseid` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '课程Id',
  `startkwaid` varchar(255) DEFAULT NULL COMMENT '头KWA节点Id',
  `endkwaid` varchar(255) DEFAULT NULL COMMENT '尾KWA节点Id',
  PRIMARY KEY (`id`),
  KEY `fk_lines_courseid` (`courseid`),
  CONSTRAINT `fk_lines_courseid` FOREIGN KEY (`courseid`) REFERENCES `cm_course` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='图谱连线表';

-- ----------------------------
-- Records of cm_lines
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_practice
-- ----------------------------
DROP TABLE IF EXISTS `cm_practice`;
CREATE TABLE `cm_practice` (
  `id` varchar(64) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `practiceCatelogId` varchar(64) DEFAULT NULL,
  `practiceCatelog` varchar(255) DEFAULT NULL,
  `score` int DEFAULT NULL,
  `purposeRemark` mediumtext,
  `contentRemark` mediumtext,
  `lastUpdateManId` varchar(64) DEFAULT NULL,
  `lastUpdateMan` varchar(255) DEFAULT NULL,
  `lastUpdateTime` varchar(255) DEFAULT NULL,
  `facePicture` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of cm_practice
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_practicecatelog
-- ----------------------------
DROP TABLE IF EXISTS `cm_practicecatelog`;
CREATE TABLE `cm_practicecatelog` (
  `id` varchar(64) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `orderno` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of cm_practicecatelog
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_profession
-- ----------------------------
DROP TABLE IF EXISTS `cm_profession`;
CREATE TABLE `cm_profession` (
  `id` varchar(255) NOT NULL COMMENT '专业ID',
  `obsid` varchar(255) DEFAULT NULL COMMENT '机构ID',
  `proname` varchar(255) DEFAULT NULL COMMENT '专业名称',
  `procode` varchar(255) DEFAULT NULL COMMENT '专业代码',
  `reachpercent` varchar(255) DEFAULT NULL COMMENT '课程目标达到阈值',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `createtime` varchar(255) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `fk_profession_obs` (`obsid`),
  CONSTRAINT `fk_profession_obs` FOREIGN KEY (`obsid`) REFERENCES `sm_obs` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='专业表';

-- ----------------------------
-- Records of cm_profession
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_term
-- ----------------------------
DROP TABLE IF EXISTS `cm_term`;
CREATE TABLE `cm_term` (
  `id` varchar(255) NOT NULL COMMENT '标识',
  `termname` varchar(255) DEFAULT NULL COMMENT '学期',
  `begindate` varchar(255) DEFAULT NULL COMMENT '起始日期',
  `enddate` varchar(255) DEFAULT NULL COMMENT '结束日期',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `iscurrentterm` varchar(255) DEFAULT NULL COMMENT '是否是当前学期',
  `createtime` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `orderno` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `orderno` (`orderno`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 COMMENT='学期表';

-- ----------------------------
-- Records of cm_term
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_term_course
-- ----------------------------
DROP TABLE IF EXISTS `cm_term_course`;
CREATE TABLE `cm_term_course` (
  `id` varchar(64) NOT NULL COMMENT '标识',
  `termId` varchar(64) DEFAULT NULL COMMENT '学期Id',
  `courseId` varchar(64) DEFAULT NULL COMMENT '课程Id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_term_course_courseid` (`courseId`),
  CONSTRAINT `fk_term_course_courseid` FOREIGN KEY (`courseId`) REFERENCES `cm_course` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='学期_课程表';

-- ----------------------------
-- Records of cm_term_course
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for fe_achievements
-- ----------------------------
DROP TABLE IF EXISTS `fe_achievements`;
CREATE TABLE `fe_achievements` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `user_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '学生ID，外键引用cm_users.id',
  `course_id` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '课程ID，外键引用cm_course.id',
  `objective_id` varchar(64) NOT NULL COMMENT '课程目标ID，外键引用fe_course_objectives.id',
  `classroom_id` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '课堂id，外键引用cm_classroom.id',
  `achievement_score` decimal(5,2) NOT NULL DEFAULT '0.00' COMMENT '达成度分数（0-100或0-1，根据业务规范）',
  `source` varchar(64) NOT NULL DEFAULT '系统计算' COMMENT '数据来源（手动输入、Excel导入、系统计算）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注（如导入批次）',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  KEY `objective_id` (`objective_id`),
  KEY `classroom_id` (`classroom_id`),
  KEY `idx_fe_achievements_student_course_objective` (`user_id`,`classroom_id`,`objective_id`),
  CONSTRAINT `fe_achievements_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `st_users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fe_achievements_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `cm_course` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fe_achievements_ibfk_3` FOREIGN KEY (`objective_id`) REFERENCES `fe_course_objectives` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fe_achievements_ibfk_4` FOREIGN KEY (`classroom_id`) REFERENCES `cm_classroom` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='达成度表';

-- ----------------------------
-- Records of fe_achievements
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for fe_assessment_categories
-- ----------------------------
DROP TABLE IF EXISTS `fe_assessment_categories`;
CREATE TABLE `fe_assessment_categories` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `course_id` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '所属课程ID，外键引用cm_course.id',
  `category_name` varchar(100) NOT NULL COMMENT '类别名称（如作业、实验）',
  `category_description` varchar(255) DEFAULT NULL COMMENT '类别描述',
  `score` int NOT NULL DEFAULT '0' COMMENT '类别分数（范围：0-100）',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `percent` double DEFAULT '0' COMMENT '百分比',
  PRIMARY KEY (`id`),
  KEY `idx_fe_assessment_categories_course_id` (`course_id`),
  CONSTRAINT `fe_assessment_categories_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `cm_course` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='考核项类别表';

-- ----------------------------
-- Records of fe_assessment_categories
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for fe_assessment_category_achievements
-- ----------------------------
DROP TABLE IF EXISTS `fe_assessment_category_achievements`;
CREATE TABLE `fe_assessment_category_achievements` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `user_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '学生ID，外键引用cm_users.id',
  `course_id` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '课程ID，外键引用cm_course.id',
  `assessment_category_id` varchar(64) NOT NULL COMMENT '课程目标ID，外键引用fe_assessment_categories.id',
  `classroom_id` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '课堂id，外键引用cm_classroom.id',
  `achievement_score` decimal(5,2) NOT NULL DEFAULT '0.00' COMMENT '达成度分数（0-100或0-1，根据业务规范）',
  `source` varchar(64) NOT NULL DEFAULT '系统计算' COMMENT '数据来源（手动输入、Excel导入、系统计算）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注（如导入批次）',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  KEY `assessment_category_id` (`assessment_category_id`),
  KEY `classroom_id` (`classroom_id`),
  KEY `idx_fe_achievements_student_course_objective` (`user_id`,`classroom_id`,`assessment_category_id`),
  CONSTRAINT `fe_assessment_category_achievements_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `st_users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fe_assessment_category_achievements_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `cm_course` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fe_assessment_category_achievements_ibfk_3` FOREIGN KEY (`assessment_category_id`) REFERENCES `fe_assessment_categories` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fe_assessment_category_achievements_ibfk_4` FOREIGN KEY (`classroom_id`) REFERENCES `cm_classroom` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生考核类型达成度表';

-- ----------------------------
-- Records of fe_assessment_category_achievements
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for fe_assessment_items
-- ----------------------------
DROP TABLE IF EXISTS `fe_assessment_items`;
CREATE TABLE `fe_assessment_items` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `category_id` varchar(64) NOT NULL COMMENT '所属类别ID，外键引用fe_assessment_categories.id',
  `course_id` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '所属课程ID，外键引用cm_course.id',
  `classroom_id` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '所属课堂ID，外键引用cm_classroom.id',
  `item_name` varchar(255) NOT NULL COMMENT '考核项名称（如“作业1”、“期末考试”）',
  `item_type` varchar(64) NOT NULL COMMENT '类型（作业、实验、考试等）',
  `objective_id` varchar(64) DEFAULT NULL COMMENT '绑定的课程目标ID，外键引用fe_course_objectives.id（一个考核项一个目标）',
  `item_description` varchar(255) DEFAULT NULL COMMENT '考核项描述',
  `type_id` varchar(64) DEFAULT NULL COMMENT '类型ID（对应实验ID/作业ID/考试ID）',
  `sort_order` int NOT NULL DEFAULT '0' COMMENT '展示顺序（支持拖动调整）',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `source` int DEFAULT '0' COMMENT '0:作业，1:实验，2：外部',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_category_course_classroom_type` (`category_id`,`course_id`,`classroom_id`,`type_id`),
  KEY `idx_fe_assessment_items_course_id` (`course_id`),
  KEY `idx_fe_assessment_items_objective_id` (`objective_id`),
  KEY `idx_fe_assessment_items_classroom_id` (`classroom_id`),
  CONSTRAINT `fe_assessment_items_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `fe_assessment_categories` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fe_assessment_items_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `cm_course` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fe_assessment_items_ibfk_3` FOREIGN KEY (`objective_id`) REFERENCES `fe_course_objectives` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fe_assessment_items_ibfk_4` FOREIGN KEY (`classroom_id`) REFERENCES `cm_classroom` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='考核项表';

-- ----------------------------
-- Records of fe_assessment_items
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for fe_course_objectives
-- ----------------------------
DROP TABLE IF EXISTS `fe_course_objectives`;
CREATE TABLE `fe_course_objectives` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `course_id` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '所属课程ID，外键引用cm_course.id',
  `objective_name` varchar(128) NOT NULL COMMENT '目标名称',
  `objective_description` varchar(255) DEFAULT NULL COMMENT '目标描述',
  `weight` decimal(5,2) NOT NULL DEFAULT '0.00' COMMENT '权重（0-1之间）',
  `sort_order` int NOT NULL DEFAULT '0' COMMENT '展示顺序（支持拖动调整）',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_fe_course_objectives_course_id` (`course_id`),
  CONSTRAINT `fe_course_objectives_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `cm_course` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程目标表';

-- ----------------------------
-- Records of fe_course_objectives
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for fe_external_assessment_task
-- ----------------------------
DROP TABLE IF EXISTS `fe_external_assessment_task`;
CREATE TABLE `fe_external_assessment_task` (
  `id` varchar(64) NOT NULL COMMENT '外部考核id',
  `label_id` varchar(64) DEFAULT NULL COMMENT '外部考核标签id',
  `ex_assessment_name` varchar(128) DEFAULT NULL COMMENT '外部考核名称',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `label_id` (`label_id`),
  CONSTRAINT `fe_external_assessment_task_ibfk_1` FOREIGN KEY (`label_id`) REFERENCES `fe_external_assessment_task_label` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='外部考核任务';

-- ----------------------------
-- Records of fe_external_assessment_task
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for fe_external_assessment_task_detail
-- ----------------------------
DROP TABLE IF EXISTS `fe_external_assessment_task_detail`;
CREATE TABLE `fe_external_assessment_task_detail` (
  `id` varchar(64) NOT NULL COMMENT '主键id',
  `ex_assessment_task_id` varchar(64) DEFAULT NULL COMMENT '外部考核任务id',
  `stuno` varchar(128) DEFAULT NULL COMMENT '学号',
  `student_name` varchar(128) DEFAULT NULL COMMENT '学生姓名',
  `stu_score` decimal(10,2) DEFAULT NULL,
  `full_score` decimal(10,2) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `ex_assessment_task_id` (`ex_assessment_task_id`),
  CONSTRAINT `fe_external_assessment_task_detail_ibfk_1` FOREIGN KEY (`ex_assessment_task_id`) REFERENCES `fe_external_assessment_task` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='外部考核详情';

-- ----------------------------
-- Records of fe_external_assessment_task_detail
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for fe_external_assessment_task_label
-- ----------------------------
DROP TABLE IF EXISTS `fe_external_assessment_task_label`;
CREATE TABLE `fe_external_assessment_task_label` (
  `id` varchar(64) NOT NULL COMMENT '主键id',
  `classroom_id` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '课堂id，外键引用cm_classroom.id',
  `label_name` varchar(128) DEFAULT NULL COMMENT '外部分类名称',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `classroom_id` (`classroom_id`),
  CONSTRAINT `fe_external_assessment_task_label_ibfk_1` FOREIGN KEY (`classroom_id`) REFERENCES `cm_classroom` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='外部考核任务标签表';

-- ----------------------------
-- Records of fe_external_assessment_task_label
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for fe_objective_assessment_category
-- ----------------------------
DROP TABLE IF EXISTS `fe_objective_assessment_category`;
CREATE TABLE `fe_objective_assessment_category` (
  `category_id` varchar(64) NOT NULL COMMENT '所属类别ID，外键引用fe_assessment_categories.id',
  `objective_id` varchar(64) NOT NULL COMMENT '课程目标ID，外键引用fe_course_objectives.id',
  `score` int DEFAULT NULL COMMENT '分数',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`category_id`,`objective_id`),
  KEY `objective_id` (`objective_id`),
  CONSTRAINT `fe_objective_assessment_category_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `fe_assessment_categories` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fe_objective_assessment_category_ibfk_2` FOREIGN KEY (`objective_id`) REFERENCES `fe_course_objectives` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='目标考核类别关联表';

-- ----------------------------
-- Records of fe_objective_assessment_category
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for global_config
-- ----------------------------
DROP TABLE IF EXISTS `global_config`;
CREATE TABLE `global_config` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `key` varchar(100) NOT NULL COMMENT '配置项的键',
  `val` mediumtext COMMENT '配置项的值',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of global_config
-- ----------------------------
BEGIN;
INSERT INTO `global_config` (`id`, `key`, `val`, `remark`) VALUES (1, 'lib_file_type_config', '[{\"name\":\"图片文件\",\"types\": [\".png\",\".jpg\",\".gif\",\".jpeg\",\"\",\".bmp\",\".webp\"]}, {\"name\":\"文档文件\",\"types\":[\".doc\",\".docx\",\".pdf\",\".xls\",\".xlsx\",\".ppt\",\".pptx\",\".txt\",\".xml\"]}, {\"name\":\"压缩文件\",\"types\":[\".rar\",\".zip\",\".gzip\"]},{\"name\":\"视频文件\",\"types\":[\".mp4\",\".m3u8\",\".flv\",\".f4v\",\".webm\",\".mov\",\".m4v\",\".3gp\",\".avi\",\".wmv\"]},,{\"name\":\"音频文件\",\"types\":[\".mp3\",\".wma\",\".wav\",\".amr\",\".ma4\",\".aac\",\".awb\"}]', '题库文件类型配置');
INSERT INTO `global_config` (`id`, `key`, `val`, `remark`) VALUES (2, 'lib_file_size_config', '{\"items\": [1,3,5,10,20,30,50], \"def\": 20}', '题库文件大小配置');
INSERT INTO `global_config` (`id`, `key`, `val`, `remark`) VALUES (3, 'lib_file_count_config', '{\"items\": [1,2,3,4,5,6,7,8,9,10], \"def\": 1}', '题库文件数量配置');
COMMIT;

-- ----------------------------
-- Table structure for global_msg
-- ----------------------------
DROP TABLE IF EXISTS `global_msg`;
CREATE TABLE `global_msg` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '任务类型',
  `classroomId` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL COMMENT '课堂id,一般情况下不为空',
  `indexId` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '业务索引id,为当前任务的唯一标识',
  `action` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL COMMENT '当前任务的动作, 例如修改 删除 或其他, 可为空',
  `context` mediumtext CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci COMMENT 'json结构的业务上下文',
  `state` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL COMMENT 'ready=待执行 complete=执行成功 failed=执行失败',
  `remark` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL COMMENT '备注',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

-- ----------------------------
-- Records of global_msg
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for ideology_calculate_paper
-- ----------------------------
DROP TABLE IF EXISTS `ideology_calculate_paper`;
CREATE TABLE `ideology_calculate_paper` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `paper_id` varchar(255) DEFAULT NULL COMMENT '试卷id',
  `classroom_id` varchar(255) DEFAULT NULL COMMENT '课堂id',
  `test_id` varchar(255) DEFAULT NULL COMMENT '测试id',
  `course_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程名称',
  `classroom_name` varchar(155) DEFAULT NULL COMMENT '课堂名称',
  `test_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '考试名称',
  `paper_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '试卷名称',
  `catelog` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '试卷类型(1作业2考试)',
  `creator` varchar(255) DEFAULT NULL COMMENT '试卷创建人',
  `create_time` varchar(255) DEFAULT NULL COMMENT '试卷创建时间',
  `row` bigint DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`id`),
  KEY `idx_paper_id` (`paper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=179 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='思政评价试卷配置表，可以选择哪些试卷参与评价。';

-- ----------------------------
-- Records of ideology_calculate_paper
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for mt_material
-- ----------------------------
DROP TABLE IF EXISTS `mt_material`;
CREATE TABLE `mt_material` (
  `id` varchar(64) NOT NULL,
  `catelogId` varchar(64) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `sourceFileName` varchar(255) DEFAULT NULL,
  `serverFileName` varchar(255) DEFAULT NULL,
  `fileSize` varchar(255) DEFAULT NULL,
  `fileExt` varchar(255) DEFAULT NULL,
  `remark` mediumtext,
  `updateManId` varchar(64) DEFAULT NULL,
  `updateMan` varchar(255) DEFAULT NULL,
  `updateTime` varchar(19) DEFAULT NULL,
  `sourceType` varchar(255) DEFAULT NULL,
  `courseId` varchar(64) DEFAULT NULL,
  `courseName` varchar(255) DEFAULT NULL,
  `keywords` varchar(255) DEFAULT NULL,
  `isshared` varchar(255) DEFAULT NULL COMMENT '0-不共享，1-共享',
  `screenImage` varchar(255) DEFAULT NULL,
  `screenImageServerUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_material_courseid` (`courseId`),
  CONSTRAINT `fk_material_courseid` FOREIGN KEY (`courseId`) REFERENCES `cm_course` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of mt_material
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for paper_ideology_achievement
-- ----------------------------
DROP TABLE IF EXISTS `paper_ideology_achievement`;
CREATE TABLE `paper_ideology_achievement` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `course_id` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '课程id，外键引用cm_course.id',
  `classroom_id` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '课堂id，外键引用cm_classroom.id',
  `paper_id` varchar(255) DEFAULT NULL COMMENT '试卷id，外键引用ideology_calculate_paper.paper_id',
  `lib_id` varchar(64) NOT NULL COMMENT '题目id',
  `v_id` varchar(64) NOT NULL COMMENT '思政价值id，外键v_ideology_value.id',
  `paper_type` varchar(64) NOT NULL DEFAULT 'def' COMMENT '题目类型，和学生日志表的含义一样',
  `value_count` int NOT NULL DEFAULT '0' COMMENT '思政个数',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  KEY `classroom_id` (`classroom_id`),
  KEY `v_id` (`v_id`),
  KEY `paper_id` (`paper_id`),
  CONSTRAINT `paper_ideology_achievement_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `cm_course` (`id`) ON DELETE CASCADE,
  CONSTRAINT `paper_ideology_achievement_ibfk_2` FOREIGN KEY (`classroom_id`) REFERENCES `cm_classroom` (`id`) ON DELETE CASCADE,
  CONSTRAINT `paper_ideology_achievement_ibfk_3` FOREIGN KEY (`v_id`) REFERENCES `v_ideology_value` (`id`) ON DELETE CASCADE,
  CONSTRAINT `paper_ideology_achievement_ibfk_4` FOREIGN KEY (`paper_id`) REFERENCES `ideology_calculate_paper` (`paper_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课堂思政评价结果表';

-- ----------------------------
-- Records of paper_ideology_achievement
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for pm_practice
-- ----------------------------
DROP TABLE IF EXISTS `pm_practice`;
CREATE TABLE `pm_practice` (
  `id` varchar(64) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `templateCode` varchar(64) DEFAULT NULL COMMENT '实验模版code',
  `classroomId` varchar(64) DEFAULT NULL COMMENT '课堂id',
  `beginTime` varchar(19) DEFAULT NULL COMMENT '开始时间',
  `endTime` varchar(19) DEFAULT NULL COMMENT '结束时间',
  `status` int DEFAULT '0' COMMENT '状态 0=未发布 1=已发布 2=已删除 3=停止',
  `publisherId` varchar(64) DEFAULT NULL COMMENT '发布人id',
  `publisher` varchar(255) DEFAULT NULL COMMENT '发布人',
  `publishTime` varchar(255) DEFAULT NULL COMMENT '发布时间',
  `updateManId` varchar(64) DEFAULT NULL COMMENT '最近修改人id',
  `updateMan` varchar(255) DEFAULT NULL COMMENT '最近修改人',
  `updateTime` varchar(19) DEFAULT NULL COMMENT '最近修改时间',
  `testPurviewTypeId` varchar(64) DEFAULT NULL COMMENT '实验权限类型id',
  `testPurviewType` varchar(255) DEFAULT NULL COMMENT '实验权限类型',
  `beforeAttention` mediumtext COMMENT '实验前注意事项',
  `totalScore` int DEFAULT NULL COMMENT '总分',
  `passScore` int DEFAULT NULL COMMENT '及格分',
  `points` int DEFAULT NULL COMMENT '积分',
  `enterPassword` varchar(255) DEFAULT NULL COMMENT '进入密码',
  `catelogId` varchar(64) DEFAULT NULL COMMENT '1-实验，2-实践',
  `catelogName` varchar(255) DEFAULT NULL,
  `finishstatus` int DEFAULT NULL COMMENT '0-未公布，1-公布',
  `finishManId` varchar(64) DEFAULT NULL COMMENT '公布人id',
  `finishMan` varchar(255) DEFAULT NULL COMMENT '公布人',
  `finishTime` varchar(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '公布时间',
  `sort` bigint NOT NULL DEFAULT '0' COMMENT '排序值',
  `stuCanReadCorrect` int DEFAULT '1' COMMENT '学生端是否可以获取批改细节',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `pp_index_sort` (`sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='模块四: 实验信息表';

-- ----------------------------
-- Records of pm_practice
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for pm_practice_correct_detail
-- ----------------------------
DROP TABLE IF EXISTS `pm_practice_correct_detail`;
CREATE TABLE `pm_practice_correct_detail` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `practiceId` varchar(64) NOT NULL,
  `stuId` varchar(64) NOT NULL,
  `itemId` varchar(64) DEFAULT NULL,
  `mode` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '小分项模式 subs或percent',
  `score` int NOT NULL COMMENT '该项的得分',
  `scoreDetail` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '小分批改明细',
  `selectedScoreName` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '被选中的小分项名称',
  `totalImg` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '附件合成的整图',
  `correctStep` mediumtext COMMENT '前台批改手动批改步骤',
  `correctImg` varchar(256) DEFAULT NULL COMMENT '批改留痕后的最终图片',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ppcd_index_pi_si_ii` (`practiceId`,`stuId`,`itemId`)
) ENGINE=InnoDB AUTO_INCREMENT=255 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of pm_practice_correct_detail
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for pm_practice_item
-- ----------------------------
DROP TABLE IF EXISTS `pm_practice_item`;
CREATE TABLE `pm_practice_item` (
  `id` varchar(64) NOT NULL,
  `practiceId` varchar(64) NOT NULL COMMENT '实验id',
  `itemCode` varchar(64) DEFAULT NULL COMMENT '项的代码',
  `itemName` varchar(64) NOT NULL COMMENT '实验项的名',
  `beValid` int NOT NULL DEFAULT '1' COMMENT '1=有效 0=无效',
  `beDefault` int NOT NULL COMMENT '是否默认',
  `defaultPath` varchar(256) DEFAULT NULL COMMENT '如果是默认项 则需上传默认文件',
  `setKwa` int NOT NULL,
  `setWeight` int NOT NULL,
  `weight` int NOT NULL DEFAULT '0' COMMENT '实验项的权重',
  `score` int NOT NULL DEFAULT '0' COMMENT '实验项的分数',
  `maxCount` int NOT NULL DEFAULT '1' COMMENT '附件可上传最大数量',
  `stuVisible` int NOT NULL DEFAULT '0' COMMENT '实验项学生是否可见 0=不可见 1=可见',
  `fileTypes` varchar(64) DEFAULT NULL COMMENT '实验项允许上传的文件名',
  `beMultiple` int DEFAULT '0' COMMENT '实验项是否允许上传多个文件',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='模块四: 实验题目信息表. 颗粒度为实验题目';

-- ----------------------------
-- Records of pm_practice_item
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for pm_practice_item_score_config
-- ----------------------------
DROP TABLE IF EXISTS `pm_practice_item_score_config`;
CREATE TABLE `pm_practice_item_score_config` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `practiceId` varchar(64) NOT NULL,
  `itemId` varchar(64) NOT NULL COMMENT '实验中项的值',
  `mode` varchar(16) NOT NULL COMMENT 'subs percent',
  `context` mediumtext NOT NULL COMMENT 'score的小分项json',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ppisc_index_pi_ii` (`practiceId`,`itemId`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of pm_practice_item_score_config
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for pm_practice_kwa
-- ----------------------------
DROP TABLE IF EXISTS `pm_practice_kwa`;
CREATE TABLE `pm_practice_kwa` (
  `id` varchar(64) NOT NULL,
  `practiceId` varchar(64) DEFAULT NULL COMMENT '实验id',
  `itemId` varchar(64) DEFAULT NULL COMMENT '实验的项的id',
  `kwaId` varchar(64) DEFAULT NULL COMMENT '实验的项的kwaId',
  `kwaName` varchar(255) DEFAULT NULL COMMENT '实验的项的kwaName',
  `datavalue` decimal(8,2) DEFAULT NULL,
  `vid` bigint DEFAULT NULL COMMENT '价值项的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='模块四: 实验题目kwa关系表, 保存实验题目和kwa的关系';

-- ----------------------------
-- Records of pm_practice_kwa
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for pm_practice_score_template
-- ----------------------------
DROP TABLE IF EXISTS `pm_practice_score_template`;
CREATE TABLE `pm_practice_score_template` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `templateCode` varchar(50) NOT NULL DEFAULT 'def',
  `mode` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'subs=分数子项模式 percent=百分比模式',
  `theKey` varchar(32) NOT NULL COMMENT '项的key',
  `defVal` decimal(8,2) NOT NULL COMMENT '项的默认值',
  `remark` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of pm_practice_score_template
-- ----------------------------
BEGIN;
INSERT INTO `pm_practice_score_template` (`id`, `templateCode`, `mode`, `theKey`, `defVal`, `remark`) VALUES (1, 'def', 'subs', '报告格式', 25.00, NULL);
INSERT INTO `pm_practice_score_template` (`id`, `templateCode`, `mode`, `theKey`, `defVal`, `remark`) VALUES (2, 'def', 'subs', '报告字数', 25.00, NULL);
INSERT INTO `pm_practice_score_template` (`id`, `templateCode`, `mode`, `theKey`, `defVal`, `remark`) VALUES (3, 'def', 'subs', '结构合理', 25.00, NULL);
INSERT INTO `pm_practice_score_template` (`id`, `templateCode`, `mode`, `theKey`, `defVal`, `remark`) VALUES (4, 'def', 'subs', '内容充实', 25.00, NULL);
INSERT INTO `pm_practice_score_template` (`id`, `templateCode`, `mode`, `theKey`, `defVal`, `remark`) VALUES (5, 'def', 'percent', 'A', 100.00, '默认100%');
INSERT INTO `pm_practice_score_template` (`id`, `templateCode`, `mode`, `theKey`, `defVal`, `remark`) VALUES (6, 'def', 'percent', 'B', 90.00, '默认90%');
INSERT INTO `pm_practice_score_template` (`id`, `templateCode`, `mode`, `theKey`, `defVal`, `remark`) VALUES (7, 'def', 'percent', 'C', 80.00, '默认80%');
INSERT INTO `pm_practice_score_template` (`id`, `templateCode`, `mode`, `theKey`, `defVal`, `remark`) VALUES (8, 'def', 'percent', 'D', 60.00, '默认60%');
COMMIT;

-- ----------------------------
-- Table structure for pm_practice_student
-- ----------------------------
DROP TABLE IF EXISTS `pm_practice_student`;
CREATE TABLE `pm_practice_student` (
  `id` varchar(64) NOT NULL,
  `practiceId` varchar(64) NOT NULL COMMENT '实验id',
  `classroomId` varchar(64) NOT NULL COMMENT '课堂id',
  `stuId` varchar(64) NOT NULL COMMENT '学生id',
  `answerPercent` decimal(8,2) DEFAULT '0.00' COMMENT '当前实验项的完成度',
  `answerContent` mediumtext COMMENT '实验的项的作答内容,json形式',
  `answerTime` varchar(19) DEFAULT NULL COMMENT '作答时间',
  `correctContent` mediumtext COMMENT '实验的项的得分,json形式',
  `status` int NOT NULL DEFAULT '0' COMMENT '0=已发布 1=已作答 2=已批改 3=已删除 4=已退回',
  `creatorId` varchar(64) DEFAULT NULL COMMENT '创建人id',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createTime` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `correctorId` varchar(64) DEFAULT NULL COMMENT '批改人id',
  `correctTime` varchar(19) DEFAULT NULL COMMENT '批改时间',
  `resultScore` int NOT NULL DEFAULT '0' COMMENT '获得的分数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ppi_index_pi_si` (`practiceId`,`stuId`),
  KEY `pps_index_stuid` (`stuId`),
  CONSTRAINT `fk_practice_user` FOREIGN KEY (`stuId`) REFERENCES `st_users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='模块四: 学生实验详细信息表和批改信息表, 试卷发布后生成到此表';

-- ----------------------------
-- Records of pm_practice_student
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for pm_practice_template
-- ----------------------------
DROP TABLE IF EXISTS `pm_practice_template`;
CREATE TABLE `pm_practice_template` (
  `id` int NOT NULL,
  `templateCode` varchar(64) NOT NULL COMMENT '模版代码',
  `itemCode` varchar(64) DEFAULT NULL,
  `itemName` varchar(128) NOT NULL COMMENT '模版字段名',
  `beValid` int NOT NULL COMMENT '1=有效 0=无效',
  `beDefault` int NOT NULL DEFAULT '0' COMMENT '0=非默认项 1=默认项 默认项不可编辑',
  `setKwa` int NOT NULL DEFAULT '0' COMMENT '0=不可设置kwa 1=可设置',
  `setWeight` int NOT NULL DEFAULT '0' COMMENT '0=不可设置权重 1= 可设置权重',
  `weight` int DEFAULT '0' COMMENT '权重',
  `maxCount` int NOT NULL DEFAULT '1' COMMENT '附件可上传最大数量',
  `stuVisible` int DEFAULT '0' COMMENT '0=学生不可见 1=可见',
  `fileTypes` varchar(256) NOT NULL COMMENT '可用的文件扩展名 .jpg .txt',
  `beMultiple` int NOT NULL DEFAULT '0' COMMENT '0=不可上传多个 1=可以上传多个',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='模块四: 实验初始化信息模版表, 用于创建新实验时设定实验各项初始数据';

-- ----------------------------
-- Records of pm_practice_template
-- ----------------------------
BEGIN;
INSERT INTO `pm_practice_template` (`id`, `templateCode`, `itemCode`, `itemName`, `beValid`, `beDefault`, `setKwa`, `setWeight`, `weight`, `maxCount`, `stuVisible`, `fileTypes`, `beMultiple`) VALUES (1, 'def', 'guideBook', '实验指导书', 1, 1, 0, 0, 0, 1, 0, '.doc,.docx,.pdf', 0);
INSERT INTO `pm_practice_template` (`id`, `templateCode`, `itemCode`, `itemName`, `beValid`, `beDefault`, `setKwa`, `setWeight`, `weight`, `maxCount`, `stuVisible`, `fileTypes`, `beMultiple`) VALUES (2, 'def', 'reportTemplate', '报告模版', 1, 1, 0, 0, 0, 1, 0, '.doc,.docx,.pdf', 0);
INSERT INTO `pm_practice_template` (`id`, `templateCode`, `itemCode`, `itemName`, `beValid`, `beDefault`, `setKwa`, `setWeight`, `weight`, `maxCount`, `stuVisible`, `fileTypes`, `beMultiple`) VALUES (3, 'def', 'image', '图片', 1, 0, 1, 1, 20, 5, 0, '.png,.jpg', 0);
INSERT INTO `pm_practice_template` (`id`, `templateCode`, `itemCode`, `itemName`, `beValid`, `beDefault`, `setKwa`, `setWeight`, `weight`, `maxCount`, `stuVisible`, `fileTypes`, `beMultiple`) VALUES (4, 'def', 'video', '视频', 1, 0, 1, 1, 20, 1, 0, '.mp4,.avi', 0);
INSERT INTO `pm_practice_template` (`id`, `templateCode`, `itemCode`, `itemName`, `beValid`, `beDefault`, `setKwa`, `setWeight`, `weight`, `maxCount`, `stuVisible`, `fileTypes`, `beMultiple`) VALUES (5, 'def', 'report', '实验报告', 1, 0, 1, 1, 20, 1, 0, '.doc,.docx,.pdf', 0);
INSERT INTO `pm_practice_template` (`id`, `templateCode`, `itemCode`, `itemName`, `beValid`, `beDefault`, `setKwa`, `setWeight`, `weight`, `maxCount`, `stuVisible`, `fileTypes`, `beMultiple`) VALUES (6, 'def', 'previewReport', '实验预习报告', 1, 0, 1, 1, 20, 1, 0, '.doc,.docx,.pdf', 0);
INSERT INTO `pm_practice_template` (`id`, `templateCode`, `itemCode`, `itemName`, `beValid`, `beDefault`, `setKwa`, `setWeight`, `weight`, `maxCount`, `stuVisible`, `fileTypes`, `beMultiple`) VALUES (7, 'def', 'defence', '答辩验收', 1, 0, 1, 1, 20, 1, 0, '.doc,.docx,.pdf', 0);
COMMIT;

-- ----------------------------
-- Table structure for pm_test
-- ----------------------------
DROP TABLE IF EXISTS `pm_test`;
CREATE TABLE `pm_test` (
  `id` varchar(64) NOT NULL COMMENT '标识',
  `paperId` varchar(64) DEFAULT NULL COMMENT '考试试卷id',
  `paperName` varchar(255) DEFAULT NULL COMMENT '试卷名称',
  `name` varchar(255) DEFAULT NULL COMMENT '考试名称',
  `testModeId` varchar(64) DEFAULT NULL COMMENT '考试类型id',
  `totalScore` varchar(255) DEFAULT NULL COMMENT '总分',
  `passScore` varchar(255) DEFAULT NULL COMMENT '及格线',
  `reviewModeId` varchar(64) DEFAULT NULL COMMENT '阅卷方式id',
  `status` int DEFAULT NULL COMMENT '状态 1=已发布 2=已锁定 3=已删除',
  `beginTime` varchar(19) DEFAULT NULL COMMENT '开考时间',
  `endTime` varchar(19) DEFAULT NULL COMMENT '结束时间',
  `lengthOfTime` int DEFAULT NULL COMMENT '考试时长 单位：分钟',
  `points` int DEFAULT NULL COMMENT '积分',
  `limitTimes` int DEFAULT NULL COMMENT '限考次数',
  `lateTime` int DEFAULT NULL COMMENT '迟到时长 分钟',
  `shortestTime` int DEFAULT NULL COMMENT '最短时长 分钟',
  `needLimitTime` bit(1) DEFAULT NULL,
  `resultShowModeId` varchar(64) DEFAULT NULL COMMENT '考试结果显示 1-仅显示感谢文字，2-显示感谢文字和成绩，3-显示试卷明细',
  `resultShowMode` varchar(255) DEFAULT NULL COMMENT '考试结果显示',
  `beforeAttention` mediumtext COMMENT '考前注意事项',
  `afterAttention` mediumtext COMMENT '靠后感谢文字',
  `equipmentId` varchar(64) DEFAULT NULL COMMENT '答题设备id',
  `equipment` varchar(255) DEFAULT NULL COMMENT '答题设备',
  `questionId` varchar(255) DEFAULT NULL COMMENT '题型',
  `testModeName` varchar(255) DEFAULT NULL COMMENT '考试类型',
  `courseId` varchar(255) DEFAULT NULL COMMENT '课程id',
  `classroomId` varchar(64) DEFAULT NULL COMMENT '课堂id',
  `publishTime` varchar(19) DEFAULT NULL COMMENT '发布时间',
  `publisherId` varchar(64) DEFAULT NULL COMMENT '发布人id',
  `publisher` varchar(255) DEFAULT NULL COMMENT '发布人',
  `creatorId` varchar(64) DEFAULT NULL COMMENT '创建人id',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建人',
  `creatTime` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `takephoto` bit(1) DEFAULT NULL COMMENT '监考拍照',
  `cutscreensubmit` bit(1) DEFAULT NULL COMMENT '切屏强制交卷',
  `noopersubmit` bit(1) DEFAULT NULL COMMENT '无操作强制交卷',
  `testPurviewTypeId` varchar(64) DEFAULT NULL COMMENT '考试权限类型id',
  `testPurviewType` varchar(255) DEFAULT NULL COMMENT '考试权限类型',
  `enterPassword` varchar(255) DEFAULT NULL COMMENT '进入密码',
  `classroomName` varchar(255) DEFAULT NULL COMMENT '课堂名称',
  `catelog` varchar(255) DEFAULT NULL COMMENT '1-作业，2-考试',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `pt_index_paperId` (`paperId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='模块四: 作业和考试信息表,  试卷发布后生成到此表';

-- ----------------------------
-- Records of pm_test
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for pm_test_students
-- ----------------------------
DROP TABLE IF EXISTS `pm_test_students`;
CREATE TABLE `pm_test_students` (
  `id` varchar(64) NOT NULL COMMENT '标识',
  `testId` varchar(64) DEFAULT NULL COMMENT '考试id',
  `userId` varchar(64) DEFAULT NULL COMMENT '考生id',
  `proId` varchar(64) DEFAULT NULL COMMENT '专业id',
  `userName` varchar(255) DEFAULT NULL COMMENT '姓名',
  `obsId` varchar(64) DEFAULT NULL COMMENT '班级id',
  `obsName` varchar(255) DEFAULT NULL COMMENT '班级名',
  `loginname` varchar(255) DEFAULT NULL COMMENT '登录名',
  `rowNo` int DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `pts_index_ti_ui` (`testId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='模块四: 作业或考试和学生关联信息表';

-- ----------------------------
-- Records of pm_test_students
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for pm_testpaper
-- ----------------------------
DROP TABLE IF EXISTS `pm_testpaper`;
CREATE TABLE `pm_testpaper` (
  `id` varchar(64) NOT NULL COMMENT '标识',
  `name` varchar(255) NOT NULL COMMENT '试卷名称',
  `makeModeId` varchar(64) NOT NULL COMMENT '组卷方式id 1=选题组卷 2=抽提组卷 3=随机组卷',
  `testModeId` varchar(64) DEFAULT NULL COMMENT '考试方式id',
  `questionsCount` int DEFAULT NULL COMMENT '试题总数',
  `totalScore` int NOT NULL DEFAULT '0' COMMENT '总分',
  `status` int NOT NULL DEFAULT '0' COMMENT '0=未发布 1=已发布 3=已删除',
  `locked` int NOT NULL DEFAULT '0' COMMENT '0=未锁定 1=锁定',
  `courseId` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '课程id',
  `classroomId` varchar(64) DEFAULT NULL COMMENT '课堂id',
  `creatorId` varchar(64) DEFAULT NULL COMMENT '创建人id',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建人',
  `createTime` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `makeModeName` varchar(255) DEFAULT NULL,
  `testModeName` varchar(255) DEFAULT NULL,
  `courseName` varchar(255) DEFAULT NULL,
  `classroomName` varchar(255) DEFAULT NULL,
  `catelog` varchar(64) DEFAULT NULL COMMENT '试卷类型 1-作业，2-考试',
  `quetypesettings` mediumtext COMMENT 'json结构存储',
  `extendOptions` mediumtext COMMENT '多选题的错误选项也给分',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `pt_index_createTime` (`createTime`),
  KEY `fk_testpaper_courseid` (`courseId`),
  CONSTRAINT `fk_testpaper_courseid` FOREIGN KEY (`courseId`) REFERENCES `cm_course` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='模块四: 试卷信息表, 从题库抽题组成试卷';

-- ----------------------------
-- Records of pm_testpaper
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for pm_testpaper_questions
-- ----------------------------
DROP TABLE IF EXISTS `pm_testpaper_questions`;
CREATE TABLE `pm_testpaper_questions` (
  `id` varchar(64) NOT NULL COMMENT '标识',
  `paperId` varchar(64) DEFAULT NULL COMMENT '试卷id',
  `score` int DEFAULT NULL COMMENT '分数',
  `libId` varchar(64) DEFAULT NULL COMMENT '试题id',
  `sort` bigint DEFAULT '0' COMMENT '顺序号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `ptq_index_` (`paperId`,`libId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='模块四: 试卷和试题关系表';

-- ----------------------------
-- Records of pm_testpaper_questions
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qf_questionform
-- ----------------------------
DROP TABLE IF EXISTS `qf_questionform`;
CREATE TABLE `qf_questionform` (
  `id` varchar(64) NOT NULL COMMENT '标识',
  `name` varchar(64) NOT NULL COMMENT '问卷名',
  `status` int NOT NULL DEFAULT '0' COMMENT '0=未发布 1=已发布 2=删除',
  `creatorId` varchar(64) NOT NULL COMMENT '创建人id',
  `creator` varchar(64) NOT NULL COMMENT '创建人',
  `createTime` varchar(19) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='模块四: 问卷信息表';

-- ----------------------------
-- Records of qf_questionform
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qf_questionform_answer
-- ----------------------------
DROP TABLE IF EXISTS `qf_questionform_answer`;
CREATE TABLE `qf_questionform_answer` (
  `id` varchar(64) NOT NULL,
  `qfId` varchar(64) NOT NULL,
  `userId` varchar(128) DEFAULT NULL COMMENT '用户id',
  `source` varchar(20) DEFAULT NULL COMMENT '用户来源',
  `wxAppId` varchar(128) DEFAULT NULL,
  `status` int NOT NULL DEFAULT '0' COMMENT '0=已发布 1=已提交 2=已删除',
  `answerContent` mediumtext COMMENT '问卷答案',
  `answerTime` varchar(19) DEFAULT NULL COMMENT '提交时间',
  `createTime` varchar(19) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `qqa_index_qi_ui` (`qfId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='模块四: 学生问卷答题信息表';

-- ----------------------------
-- Records of qf_questionform_answer
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qf_questionform_item
-- ----------------------------
DROP TABLE IF EXISTS `qf_questionform_item`;
CREATE TABLE `qf_questionform_item` (
  `id` varchar(64) NOT NULL COMMENT '标识',
  `topicId` varchar(64) NOT NULL COMMENT '题目id',
  `itemOption` varchar(32) DEFAULT NULL COMMENT '选项',
  `itemPicture` varchar(200) DEFAULT NULL COMMENT '选项图片地址',
  `itemContent` mediumtext COMMENT '选项内容',
  `sort` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='模块四: 文件题目选项表, 用于保存问卷中客观题的各个选项信息';

-- ----------------------------
-- Records of qf_questionform_item
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qf_questionform_topic
-- ----------------------------
DROP TABLE IF EXISTS `qf_questionform_topic`;
CREATE TABLE `qf_questionform_topic` (
  `id` varchar(64) NOT NULL COMMENT '标识',
  `qfId` varchar(64) NOT NULL COMMENT '问卷id',
  `title` mediumtext COMMENT '问卷题目',
  `typeId` varchar(32) DEFAULT NULL COMMENT '题目类型',
  `status` int NOT NULL DEFAULT '1' COMMENT '1=有效  2=删除',
  `content` mediumtext COMMENT '题目内容',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建人名',
  `creatorId` varchar(64) DEFAULT NULL COMMENT '创建人id',
  `creatorTime` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `sort` bigint DEFAULT '0' COMMENT '题目排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='模块四: 文件题目信息表, 保存问卷中的题目信息';

-- ----------------------------
-- Records of qf_questionform_topic
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qf_quetype
-- ----------------------------
DROP TABLE IF EXISTS `qf_quetype`;
CREATE TABLE `qf_quetype` (
  `id` varchar(64) DEFAULT NULL COMMENT 'typeId',
  `name` varchar(64) DEFAULT NULL COMMENT 'typeName',
  `status` int DEFAULT NULL COMMENT '0=无效 1=有效'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='模块四: 问卷题目类型表, 用于保存问卷组卷时可选的试题类型';

-- ----------------------------
-- Records of qf_quetype
-- ----------------------------
BEGIN;
INSERT INTO `qf_quetype` (`id`, `name`, `status`) VALUES ('0201', '单选题', 1);
INSERT INTO `qf_quetype` (`id`, `name`, `status`) VALUES ('0202', '多选题', 1);
INSERT INTO `qf_quetype` (`id`, `name`, `status`) VALUES ('0203', '判断题', 1);
INSERT INTO `qf_quetype` (`id`, `name`, `status`) VALUES ('0204', '填空题', 0);
INSERT INTO `qf_quetype` (`id`, `name`, `status`) VALUES ('0205', '简答题', 0);
COMMIT;

-- ----------------------------
-- Table structure for sm_obs
-- ----------------------------
DROP TABLE IF EXISTS `sm_obs`;
CREATE TABLE `sm_obs` (
  `id` varchar(255) NOT NULL COMMENT '组织机构ID',
  `pid` varchar(255) DEFAULT NULL COMMENT '父节点ID',
  `orderno` int DEFAULT NULL COMMENT '顺序号',
  `obsdeep` int DEFAULT NULL COMMENT '深度',
  `obsname` varchar(255) DEFAULT NULL COMMENT '组织名称',
  `obspath` varchar(255) DEFAULT NULL COMMENT '全路径',
  `levelcode` varchar(255) DEFAULT NULL COMMENT '层级码',
  `createtime` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `termid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_termid_obsname_unique` (`termid`,`obsname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='组织机构表';

-- ----------------------------
-- Records of sm_obs
-- ----------------------------
BEGIN;
INSERT INTO `sm_obs` (`id`, `pid`, `orderno`, `obsdeep`, `obsname`, `obspath`, `levelcode`, `createtime`, `remark`, `termid`) VALUES ('237675254', '0', 1, 1, '北方工业大学', '', '101', NULL, 'NCUT', '0');
COMMIT;

-- ----------------------------
-- Table structure for sm_obs_term
-- ----------------------------
DROP TABLE IF EXISTS `sm_obs_term`;
CREATE TABLE `sm_obs_term` (
  `id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `obsid` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `termid` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

-- ----------------------------
-- Records of sm_obs_term
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sm_student
-- ----------------------------
DROP TABLE IF EXISTS `sm_student`;
CREATE TABLE `sm_student` (
  `id` varchar(255) NOT NULL COMMENT 'ID',
  `obsid` varchar(255) DEFAULT NULL COMMENT ' 组织ID',
  `usersid` varchar(255) DEFAULT NULL COMMENT '学生ID',
  `stuno` varchar(255) DEFAULT NULL COMMENT '学号',
  `classno` varchar(255) DEFAULT NULL COMMENT '班级号',
  `proname` varchar(255) DEFAULT NULL COMMENT '专业名称',
  `proid` varchar(255) DEFAULT NULL COMMENT '专业ID',
  `schoolname` varchar(255) DEFAULT NULL COMMENT '学校名称',
  `collegesname` varchar(255) DEFAULT NULL COMMENT '院系名称',
  `status` varchar(50) DEFAULT NULL COMMENT '状态',
  `submittime` varchar(255) DEFAULT NULL COMMENT '提交时间',
  `isselfreg` varchar(255) DEFAULT NULL COMMENT '是否是自己注册',
  `createtime` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `historyobs` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_usersid_stu` (`usersid`),
  KEY `fk_stu_obs` (`obsid`),
  CONSTRAINT `fk_stu_obs` FOREIGN KEY (`obsid`) REFERENCES `sm_obs` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_usersid_stu` FOREIGN KEY (`usersid`) REFERENCES `st_users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='学生表';

-- ----------------------------
-- Records of sm_student
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sm_teacher
-- ----------------------------
DROP TABLE IF EXISTS `sm_teacher`;
CREATE TABLE `sm_teacher` (
  `id` varchar(255) NOT NULL COMMENT '老师ID',
  `obsid` varchar(255) DEFAULT NULL COMMENT '机构ID',
  `usersid` varchar(255) DEFAULT NULL COMMENT '用户ID',
  `jobno` varchar(255) DEFAULT NULL COMMENT '教师工号',
  `title` varchar(255) DEFAULT NULL COMMENT '教师职称',
  `oftenclassroomid` varchar(255) DEFAULT NULL COMMENT '常用课堂ID',
  `createtime` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `historyobs` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_usersid` (`usersid`),
  KEY `fk_tea_obs` (`obsid`),
  CONSTRAINT `fk_usersid` FOREIGN KEY (`usersid`) REFERENCES `st_users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='教师表';

-- ----------------------------
-- Records of sm_teacher
-- ----------------------------
BEGIN;
INSERT INTO `sm_teacher` (`id`, `obsid`, `usersid`, `jobno`, `title`, `oftenclassroomid`, `createtime`, `historyobs`) VALUES ('5657687', '1597933787-d008ec9e-858c-4872-8533-83ebd784d49f', '1', '276325', NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for st_level
-- ----------------------------
DROP TABLE IF EXISTS `st_level`;
CREATE TABLE `st_level` (
  `id` int NOT NULL COMMENT '标识',
  `levelname` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '名称',
  `obsdeep` int DEFAULT NULL COMMENT '深度',
  `teacher` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '教师',
  `student` varchar(255) DEFAULT NULL COMMENT '学生',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of st_level
-- ----------------------------
BEGIN;
INSERT INTO `st_level` (`id`, `levelname`, `obsdeep`, `teacher`, `student`) VALUES (101, '学校', 1, '0', '0');
INSERT INTO `st_level` (`id`, `levelname`, `obsdeep`, `teacher`, `student`) VALUES (102, '学院', 2, '0', '0');
INSERT INTO `st_level` (`id`, `levelname`, `obsdeep`, `teacher`, `student`) VALUES (104, '专业', 4, '1', '0');
COMMIT;

-- ----------------------------
-- Table structure for st_menus
-- ----------------------------
DROP TABLE IF EXISTS `st_menus`;
CREATE TABLE `st_menus` (
  `id` varchar(255) NOT NULL COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `pid` varchar(50) DEFAULT NULL COMMENT '父节点ID',
  `orderno` int DEFAULT NULL COMMENT '排列顺序（1表示父节点的第一个子节点，2表示父节点的第2个子节点，依次类推）',
  `url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `isused` varchar(50) DEFAULT NULL COMMENT '是否使用（1-使用，0-隐藏）',
  `createtime` varchar(255) DEFAULT NULL COMMENT '创建日期',
  `levelcode` varchar(255) DEFAULT NULL COMMENT '层级码',
  `fullpath` varchar(255) DEFAULT NULL COMMENT '全路径',
  `by1` varchar(255) DEFAULT NULL COMMENT '备用字段1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='系统_菜单表';

-- ----------------------------
-- Records of st_menus
-- ----------------------------
BEGIN;
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('0cc25fbaa8a011f097b10242ac110003', '数据导入', '9d895362a89f11f097b10242ac110003', 2, '/external-data/data-import', '1', '2025-10-14 01:49:41', '105.110.102', '/评价系统/外部数据管理/数据导入', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-016c289e-75ae-4034-85db-94b1cff32233', '层级测试菜单', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', 1, '', '1', '2024-04-11T19:03:03.976', '101.101.101', '基础信息管理/角色管理/层级测试菜单', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', '基础信息管理', '0', 1, '', '1', '2024-02-06T21:09:57.846', '101', '基础信息管理', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-074abac7-fe4c-4908-aa7e-d72dacd94014', '课堂管理', '531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35', 7, '/coursemangt/classroom', '1', '2024-09-22T22:03:14.938', '103.107', '/课程管理/课堂管理', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-08310c14-214f-47e0-b973-fc9043e4486d', '实验成绩', '531500340-f5231573-bc14-4862-a77b-d4c09e31d136', 3, '/exp/scoreList', '1', '2025-02-10T12:35:49.452', '107.103', '/实验系统/实验成绩', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-087a4eac-7036-4074-a813-831efa3d6950', '课程调查问卷', '531500340-da5dc88e-3ff9-4ed8-a724-c15377d306a3', 4, '', '1', '2024-05-08T12:36:57.940', '105.102.104', '评价系统/达成性评价模型/课程调查问卷', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-097434fa-dbbc-4ee3-ac93-fed60c6e18ce', '层级测试菜单', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', 1, '', '1', '2024-04-11T18:37:17.397', '101.101.101', '基础信息管理/角色管理/层级测试菜单', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-0ee32ded-100b-4505-95c4-65d5e9b3d93c', '课程管理', '531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35', 1, '/coursemangt/coursemangt', '1', '2024-04-16T12:38:50.502', '103.101', '/课程管理模块/课程管理', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65', '教师首页', '0', 2, 'homes/teacherhome', '1', '2024-03-27T22:12:00.331', '102', '教师首页', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-1111-2222-3333-444455556666', '画像名单', '531500340-9999-aaaa-bbbb-ccccdddd0000', 1, '/sizheng/portraitList', '1', '2025-11-11 00:53:20', '105.101.101', '/评价系统/思政价值评价/画像名单', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-155d2725-4be7-4e83-9ac0-88552a02023f', '教学单位创建', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', 5, '/sysmangt/creatteachunit', '1', '2024-02-06T21:22:22.467', '101.105', '基础信息管理/教学单位创建', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-15731dcc-f552-4160-b5c5-99a9112ae341', '形成性评价', '531500340-82b00b86-cba2-43de-9904-f913eed8a570', 1, '', '1', '2024-12-17T11:41:15.062', '105.110.101', '/评价系统/评估与画像/形成性评价', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-17a53f15-4a36-4450-abf5-387825a2434a', '课程目标', '531500340-da5dc88e-3ff9-4ed8-a724-c15377d306a3', 1, '/evasys/accessible/coursetarget', '1', '2024-05-08T12:35:09.535', '105.102.101', '评价系统/达成性评价模型/课程目标', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-188ff31e-ff6b-47eb-a6d6-1ed0b703c0b5', '课程教案', '531500340-2eb9fc11-cba7-48e1-9af3-f22f8fcae18c', 9, '/coursemangt/classroommangt/lessonplan', '1', '2024-07-01T10:46:48.872', '103.109', '/课程管理/课堂管理/课程教案', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-25cf1417-8726-4732-abd0-38908ba1d9b2', '题库同步', '531500340-a9278b5d-f2ba-4429-854f-4662000f50e6', 2, '/exam/coursequelib/sync', '1', '2024-05-07T14:30:54.815', '104.103.102', '考试系统/课程题库/题库同步', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-2eb9fc11-cba7-48e1-9af3-f22f8fcae18c', '课堂信息管理', '531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35', 4, '/coursemangt/classroommangt', '1', '2024-07-01T10:30:19.151', '103.104', '/课程管理/课堂信息管理', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-3279ddd8-0024-4e37-b1f0-51fe30e57ff4', '层级测试菜单', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', 1, '', '1', '2024-04-11T19:00:07.925', '101.101.101', '基础信息管理/角色管理/层级测试菜单', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-38af2070-0ce6-4912-9ad3-dfd5959cdd53', '评价结果', '531500340-ce704fab-6961-4091-a671-ce8d9e6b6549', 1, '/evaluation/dynamicEvaluation/academicTranscript', '1', '2024-12-02T22:44:06.600', '105.110.102.101', '/评价系统/评估与画像/达成性评价/评价名单', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-407dc22f-4e2a-4c52-8caa-eb667d0ec34c', '我的问卷', '531500340-c0220993-26e0-4d21-bc25-f612c67170c5', 11, '/exam/myquestionnaire', '1', '2024-07-10T11:14:47.759', '104.110', '/考试系统/我的问卷', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-423e72b1-72ea-4dbb-99f4-e72772bafc3c', '学校配置', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', 10, '/sysmangt/schoolmangt', '1', '2024-04-25T19:34:38.392', '101.110', '基础信息管理/学校配置', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-439363cf-9c16-4b9e-8840-64bb093cbbd3', '教学大纲', '531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35', 3, '/coursemangt/instructionalprogram', '1', '2024-06-24T16:40:07.492', '103.103', '/课程管理/教学大纲', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-4d66eeda-f440-4994-8998-bc2fcaba3c16', '考核项设计', '531500340-da5dc88e-3ff9-4ed8-a724-c15377d306a3', 2, '/evaluation/AssessmentItems', '1', '2024-05-08T12:35:42.925', '105.102.102', '评价系统/达成性评价模型/考核项设计', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-50b321e8-2886-4b99-a921-cbb5baacf1ac', '题型设定', '531500340-6f8c6196-a29a-4b1d-89f4-9eec372e2c87', 1, '/exam/classroomquelib/classroomQTS', '1', '2024-07-06T11:53:34.363', '104.104.101', '/考试系统/课堂题库/题型设定', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-534f4acb-9a7c-465a-af38-1c8dadfefe95', '我的问卷', '531500340-c0220993-26e0-4d21-bc25-f612c67170c5', 11, '/exam/myquestionnaire', '1', '2024-07-10T11:12:28.272', '104.110', '/考试系统/我的问卷', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35', '课程管理模块', '0', 3, '', '1', '2024-04-16T12:34:35.343', '103', '课程管理模块', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-551fd76e-0890-4566-a3d0-dfa2215f3e72', '实验实践', '531500340-c0220993-26e0-4d21-bc25-f612c67170c5', 7, '', '1', '2024-05-07T12:59:31.343', '104.107', '考试系统/实验实践', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-58da609f-67ca-4ea4-acea-d1c5fb7ec20d', '教学日历', '531500340-2eb9fc11-cba7-48e1-9af3-f22f8fcae18c', 8, '/coursemangt/classroommangt/academiccalendar', '1', '2024-07-01T10:40:04.105', '103.108', '/课程管理/课堂管理/教学日历', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-5ae215f6-d749-459f-9a64-bb022cc1f6f9', '配置课程信息', '531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35', 11, '/coursemangt/pastCourse', '1', '2025-04-20T17:22:59.150', '103.111', '/课程管理模块/配置课程信息', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-5b87c7af-00e3-44b6-b6c5-0a9a77882864', '往届作业', '531500340-dbfbbf3e-59ce-4076-bbed-2fa699999686', 2, '/exam/test/past', '1', '2024-05-07T12:57:09.744', '104.106.102', '考试系统/作业测试/往届作业', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-5d318e20-3c58-4944-b7ac-c29e26df5ac0', '题型设定', '531500340-a9278b5d-f2ba-4429-854f-4662000f50e6', 1, '/exam/coursequelib/type', '1', '2024-05-07T14:26:42.011', '104.103.101', '考试系统/课程题库/题型设定', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-6226ed9a-1517-471a-8960-c6f23e7fb0e4', '课程目标', '531500340-8daa7ebc-0b27-4236-b4d6-2e872f771f3a', 8, '/evasys/formative/coursetarget', '1', '2025-05-07T18:11:38.056', '105.101.108', '/评价系统/形成性评价模型/课程目标', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-63929fcc-e8f9-11ee-934c-fa163efa1f90', '专业班级管理', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', 9, '/sysmangt/classmangt', '1', '2024-03-23T21:14:01.311', '101.109', '基础信息管理/专业班级管理', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-66382360-f89d-47fc-889a-61c720bdd826', '生成报告', '531500340-ce704fab-6961-4091-a671-ce8d9e6b6549', 2, '/evaluation/dynamicEvaluation/classroomReport', '1', '2024-12-02T22:44:44.028', '105.110.102.102', '/评价系统/评估与画像/达成性评价/生成报告', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-679f830a-db5f-40d8-b210-fded905516ac', '作业管理', '531500340-dbfbbf3e-59ce-4076-bbed-2fa699999686', 1, '/exam/test/testmangt', '1', '2024-05-07T12:53:20.157', '104.106.101', '考试系统/作业测试/作业管理', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3', '学院管理', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', 3, '/sysmangt/collegemangt', '1', '2024-02-06T21:18:34.329', '101.103', '基础信息管理/学院管理', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-6c7208c0-bbc3-4d8d-a19b-6ca9d6b269fb', '复制往届课程', '531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35', 11, '/coursemangt/pastCourse', '1', '2025-05-18T21:16:36.079', '103.111', '/课程管理模块/复制往届课程', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-6d018c56-58b2-4277-a258-28707ae35cc7', '课堂画像', '531500340-15731dcc-f552-4160-b5c5-99a9112ae341', 3, '/evaluation/dynamicEvaluation/classroomGraph', '1', '2024-11-18T12:36:19.077', '105.110.101.103', '/评价系统/评估与画像/形成性评价/课堂画像', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-6d459f8b-86da-44ba-a90d-ee644c046bda', '复制往届课程', '531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35', 11, '/coursemangt/pastCourse', '1', '2025-05-18T21:06:24.146', '103.111', '/课程管理模块/复制往届课程', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-6f8c6196-a29a-4b1d-89f4-9eec372e2c87', '课堂题库', '531500340-c0220993-26e0-4d21-bc25-f612c67170c5', 4, '/exam/classroomquelib', '1', '2024-05-07T12:41:45.643', '104.104', '考试系统/课堂题库', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-731d1bc7-e47b-463e-bf26-5a113dee9716', '画像数据管理', '531500340-8daa7ebc-0b27-4236-b4d6-2e872f771f3a', 7, '/evaluation/dynamicEvaluation/portraitmangt', '1', '2025-01-08T19:20:52.418', '105.101.107', '/评价系统/形成性评价模型/画像数据管理', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-7777-8888-9999-0000aaaabbbb', '画像数据管理', '531500340-9999-aaaa-bbbb-ccccdddd0000', 2, '/sizheng/portraitData', '1', '2025-11-11 00:53:20', '105.101.102', '/评价系统/思政价值评价/画像数据管理', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-7c63297e-ec51-49cb-ab95-2c33229455b4', '我的问卷', '531500340-c0220993-26e0-4d21-bc25-f612c67170c5', 11, '/exam/myquestionnaire', '1', '2024-07-10T11:02:07.104', '104.110', '/考试系统/我的问卷', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998', '人员管理', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', 6, '/sysmangt/peoplemangt', '1', '2024-02-06T21:23:38.285', '101.106', '基础信息管理/人员管理', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-7fb353f6-a56a-4622-a79d-dab0c178febf', '角色授权', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', 2, '/sysmangt/rolepurview', '1', '2024-02-06T21:16:18.599', '101.102', '基础信息管理/角色授权', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-82b00b86-cba2-43de-9904-f913eed8a570', '评估与画像', '531500340-d24188bd-5dc8-4e37-8bf4-0d355cada866', 6, '', '1', '2024-11-18T12:32:29.038', '105.110', '/评价系统/评估与画像', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-8462eabd-4a2d-4b79-8a73-99ecd64f3d40', '图谱', '531500340-d24188bd-5dc8-4e37-8bf4-0d355cada866', 5, '/evasys/graph', '1', '2024-05-14T21:17:00.473', '105.103', '/评价系统/图谱', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-86317a13-8a2c-41fd-b1ad-051ca070a875', '学期管理', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', 4, '/sysmangt/termmangt', '1', '2024-02-06T21:21:29.285', '101.104', '基础信息管理/学期管理', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-86816d21-ec0c-4dc6-ad1d-8edea9716d09', '关键字', '531500340-8daa7ebc-0b27-4236-b4d6-2e872f771f3a', 1, '/evasys/formative/keyword', '1', '2024-05-07T18:51:14.742', '105.101.101', '评价系统/形成性评价模型/关键字', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', '角色管理', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', 1, '/sysmangt/rolemangt', '1', '2024-02-06T21:14:01.311', '101.101', '基础信息管理/角色管理', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-8d0cc23d-60b6-4092-b10a-915e0afcca55', '实验资源', '531500340-f5231573-bc14-4862-a77b-d4c09e31d136', 2, '/exp/myExpResources', '1', '2025-03-14T17:56:25.197', '107.102', '/实验系统/实验资源', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-8daa7ebc-0b27-4236-b4d6-2e872f771f3a', '形成性评价模型', '531500340-d24188bd-5dc8-4e37-8bf4-0d355cada866', 2, '', '1', '2024-05-07T18:44:58.927', '105.101', '评价系统/形成性评价模型', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-8e29b12a-b61e-4ec6-b7ea-abab46cf27f6', '我的问卷', '531500340-c0220993-26e0-4d21-bc25-f612c67170c5', 11, '/exam/myquestionnaire', '1', '2024-07-10T11:13:49.629', '104.110', '/考试系统/我的问卷', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-902ff997-6669-40bd-9f82-d8aeb297f39b', '我的问卷', '531500340-c0220993-26e0-4d21-bc25-f612c67170c5', 11, '/exam/myquestionnaire', '1', '2024-07-08T15:41:24.204', '104.110', '/考试系统/我的问卷', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-910116aa-e8f8-11ee-934c-fa163efa1f90', '专业管理', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', 8, '/sysmangt/professionmangt', '1', '2024-03-23T21:14:01.311', '101.108', '基础信息管理/专业管理', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-94bb8cdb-75e0-4561-8af5-7c1107f0426d', '复制往届课程', '531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35', 11, '/coursemangt/pastCourse', '1', '2025-05-19T12:23:32.899', '103.111', '/课程管理模块/复制往届课程', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-954722d2-76cf-4e64-bdf8-f11ef7ceef23', '课程资源', '531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35', 5, '/coursemangt/courseresources', '1', '2024-07-01T18:06:38.998', '103.105', '/课程管理/课程资源', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-9999-aaaa-bbbb-ccccdddd0000', '思政价值评价', '531500340-d24188bd-5dc8-4e37-8bf4-0d355cada866', 4, '', '1', '2025-11-11 00:53:20', '105.101', '/评价系统/思政价值评价', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-a9278b5d-f2ba-4429-854f-4662000f50e6', '课程题库', '531500340-c0220993-26e0-4d21-bc25-f612c67170c5', 3, '/exam/coursequelib', '1', '2024-05-05T22:27:17.492', '104.103', '考试系统/课程题库', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-a9456c2b-c3f7-41b3-b2c4-1fd8e622dcc6', '学生首页', '0', 6, '/homes/studenthome', '1', '2024-05-14T14:09:50.103', '106', '学生首页', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-aaa1-bbb1-ccc1-ddd1eee1fff1', '思政评价', '531500340-82b00b86-cba2-43de-9904-f913eed8a570', 3, '', '1', '2025-11-11 01:46:07', '105.110.112', '/评估与画像/思政评价', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-aaaa-bbbb-cccc-ddddeeeefff4', '价值标签', '531500340-9999-aaaa-bbbb-ccccdddd0000', 1, '/evaluation/valueLabel', '1', '2025-11-13 07:29:30', '12', '/思政价值评价/价值标签', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-aaaa-bbbb-cccc-ddddeeeefff5', '学生画像', '531500340-aaa1-bbb1-ccc1-ddd1eee1fff1', 1, '/evaluation/studentPortrait', '1', '2025-11-13 07:33:03', '12', '/思政评价/学生画像', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-aaaa-bbbb-cccc-ddddeeeefff6', '课堂画像', '531500340-aaa1-bbb1-ccc1-ddd1eee1fff1', 2, '/evaluation/classroomPortrait', '1', '2025-11-13 07:33:11', '12', '/思政评价/课堂画像', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-b1ebad5d-7fa4-4872-ba1f-13df498414c6', '基本教学目标', '531500340-8daa7ebc-0b27-4236-b4d6-2e872f771f3a', 3, '/evasys/formative/teachingobjectives', '1', '2024-05-07T18:54:37.930', '105.101.103', '评价系统/形成性评价模型/基本教学目标', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-b64043df-c9f1-43a2-b96b-a6134b2953e1', '知识单元', '531500340-8daa7ebc-0b27-4236-b4d6-2e872f771f3a', 4, '/evasys/formative/knowledgeunit', '1', '2024-05-07T18:56:11.412', '105.101.104', '评价系统/形成性评价模型/知识单元', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-b6a3917f-2f46-4d7a-b79d-dc5b91e963c5', '学生报告', '531500340-15731dcc-f552-4160-b5c5-99a9112ae341', 1, '/evaluation/dynamicEvaluation/studentReport', '1', '2024-11-18T12:34:33.190', '105.110.101.101', '/评价系统/评估与画像/形成性评价/学生报告', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-bbf33596-d4ed-41ce-a7d3-cfba75618c64', '层级测试菜单', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', 1, '', '1', '2024-04-11T18:46:17.174', '101.101.101', '基础信息管理/角色管理/层级测试菜单', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-bf697261-39c2-408c-b11a-b5a60ec89994', '问卷发布', '531500340-c0220993-26e0-4d21-bc25-f612c67170c5', 10, '/exam/questionnaire', '1', '2024-07-08T15:40:50.592', '104.110', '/考试系统/问卷发布', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-c0220993-26e0-4d21-bc25-f612c67170c5', '考试系统', '0', 4, '', '1', '2024-05-05T22:24:59.956', '104', '考试系统', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-c03dadaf-4a89-42a1-8b36-847d34c8b337', '实验大纲', '531500340-f5231573-bc14-4862-a77b-d4c09e31d136', 1, '/exp/myExpProgram', '1', '2025-03-14T17:55:27.138', '107.101', '/实验系统/实验大纲', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-c0fc7d55-5459-433c-ad6a-593856295d51', '能力', '531500340-8daa7ebc-0b27-4236-b4d6-2e872f771f3a', 2, '/evaluation/getability', '1', '2024-05-07T18:53:07.257', '105.101.102', '评价系统/形成性评价模型/能力', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-c38a1e47-8271-43f2-aca3-bb7b2db33ad0', '我的作业', '531500340-c0220993-26e0-4d21-bc25-f612c67170c5', 8, '/exam/myhomework', '1', '2024-05-21T13:41:44.089', '105.108', '/考试系统/我的作业', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-cbb13f49-17eb-40fd-80f2-8b7fba973361', '实验管理', '531500340-551fd76e-0890-4566-a3d0-dfa2215f3e72', 12, '/exam/experimental/labmangt', '1', '2024-05-07T13:01:31.169', '104.107.101', '考试系统/实验实践/实验管理', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-ce704fab-6961-4091-a671-ce8d9e6b6549', '达成性评价', '531500340-82b00b86-cba2-43de-9904-f913eed8a570', 2, '', '1', '2024-12-02T22:43:17.385', '105.110.102', '/评价系统/评估与画像/达成性评价', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-d24188bd-5dc8-4e37-8bf4-0d355cada866', '评价系统', '0', 5, '', '1', '2024-05-07T18:32:33.774', '105', '评价系统', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-da5dc88e-3ff9-4ed8-a724-c15377d306a3', '达成性评价模型', '531500340-d24188bd-5dc8-4e37-8bf4-0d355cada866', 3, '', '1', '2024-05-08T12:31:45.163', '105.102', '评价系统/达成性评价模型', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-dbfbbf3e-59ce-4076-bbed-2fa699999686', '作业测试', '531500340-c0220993-26e0-4d21-bc25-f612c67170c5', 6, '', '1', '2024-05-07T12:44:48.849', '104.106', '考试系统/作业测试', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-dc11e4e7-6e9f-4975-a6b7-5f97ba1c46d3', '考核方案', '531500340-da5dc88e-3ff9-4ed8-a724-c15377d306a3', 3, '/evaluation/assessmentPlan', '1', '2024-05-08T12:36:20.489', '105.102.103', '评价系统/达成性评价模型/考核方案', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-e1a6e9a2-8a97-417b-bc5d-ad936b0ac41f', '画像名单', '531500340-da5dc88e-3ff9-4ed8-a724-c15377d306a3', 5, '/evaluation/graphList', '1', '2025-01-15T21:21:36.063', '105.102.105', '/评价系统/达成性评价模型/画像名单', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-e7149e74-4856-4440-8d94-99f915731842', '课堂学生名单', '531500340-2eb9fc11-cba7-48e1-9af3-f22f8fcae18c', 10, '/coursemangt/classroommangt/classStudentList', '1', '2024-09-04T16:42:55.617', '103.110', '/课程管理/课堂管理/课堂学生名单', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-eabd4d64-15df-485c-96b6-d6e9b0792c4f', '学生画像', '531500340-15731dcc-f552-4160-b5c5-99a9112ae341', 2, '/evaluation/dynamicEvaluation/studentGraph', '1', '2024-11-18T12:35:24.738', '105.110.101.102', '/评价系统/评估与画像/形成性评价/学生画像', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-ecaa4072-d8ae-4f54-b681-6d42ca75db12', '知识能力图谱', '531500340-8daa7ebc-0b27-4236-b4d6-2e872f771f3a', 5, '/evasys/formative/KWAgraph', '1', '2024-05-07T18:57:12.952', '105.101.105', '评价系统/形成性评价模型/知识能力图谱', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-eeed1520-d1a8-416c-b42b-a24fa74e0d16', '考试题库', '531500340-c0220993-26e0-4d21-bc25-f612c67170c5', 5, '', '1', '2024-05-07T12:42:27.867', '104.105', '考试系统/考试题库', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-f47ac10b-58cc-4372-a567-0e02b2c3d479', '部门管理', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', 7, '/sysmangt/departmentmangt', '1', '2024-03-23T21:14:01.311', '101.107', '基础信息管理/部门管理', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-f5231573-bc14-4862-a77b-d4c09e31d136', '实验系统', '0', 7, '', '1', '2025-02-10T12:21:25.140', '107', '实验系统', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-f78d8a42-fd40-4643-a737-cb85234737d6', '我的实验', '531500340-c0220993-26e0-4d21-bc25-f612c67170c5', 9, '/exam/myexperiment', '1', '2024-05-21T13:46:27.557', '105.109', '/考试系统/我的实验', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-f844e11c-d447-48c3-87bf-1202492b8b03', '教学目录设定', '531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35', 6, '/coursemangt/teachinggoalsetting', '1', '2024-09-22T23:05:38.258', '103.106', '/课程管理/教学目录设定', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-fb3c1d88-c81f-4775-9c96-684bc9dabc75', '思政价值', '531500340-9999-aaaa-bbbb-ccccdddd0000', 3, '/evasys/formative/idealogy', '1', '2025-08-21T15:20:34.379', '105.101.109', '/评价系统/思政价值评价/思政价值', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-fe5bb833-fdd7-4416-81dd-f5b20107540f', '能力字典', '531500340-d24188bd-5dc8-4e37-8bf4-0d355cada866', 5, '/evaluation/ability', '1', '2024-05-22T11:00:14.314', '103.103', '/课程管理模块/能力字典', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-fe8c5e35-ba92-4f2e-9d2f-f1c89a278ef9', '画像名单', '531500340-8daa7ebc-0b27-4236-b4d6-2e872f771f3a', 6, '/dynamicmodel/graphlist', '1', '2025-01-08T19:07:57.228', '105.101.106', '/评价系统/形成性评价模型/画像名单', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('9d895362a89f11f097b10242ac110003', '外部数据管理', '531500340-d24188bd-5dc8-4e37-8bf4-0d355cada866', 1, '', '1', '2025-10-14 01:46:34', '105.110', '/评价系统/外部数据管理', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('efe66f30a89f11f097b10242ac110003', '类型管理', '9d895362a89f11f097b10242ac110003', 1, '/external-data/type-manage', '1', '2025-10-14 01:48:52', '105.110.101', '/评价系统/外部数据管理/类型管理', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('f7557758da2a11f097b10242ac110003', '思政知识单元', '531500340-9999-aaaa-bbbb-ccccdddd0000', 10, '/evaluation/valueKnowledgeUnit', '1', '2025-12-16 02:57:32', '12', '/思政价值评价/知识单元', NULL);
COMMIT;

-- ----------------------------
-- Table structure for st_rolemenu
-- ----------------------------
DROP TABLE IF EXISTS `st_rolemenu`;
CREATE TABLE `st_rolemenu` (
  `id` varchar(255) NOT NULL COMMENT 'ID',
  `roleid` varchar(50) DEFAULT NULL COMMENT '角色ID',
  `menuid` varchar(50) DEFAULT NULL COMMENT '菜单ID',
  `status` varchar(255) DEFAULT NULL COMMENT '操作权限状态（1-完全权限，2-只读，3-不可见）',
  `createtime` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `by1` varchar(255) DEFAULT NULL COMMENT '备用字段1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_roleid_menuid` (`roleid`,`menuid`),
  KEY `fk_rolemenu_menuid` (`menuid`),
  CONSTRAINT `fk_rolemenu_menuid` FOREIGN KEY (`menuid`) REFERENCES `st_menus` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_rolemenu_roleid` FOREIGN KEY (`roleid`) REFERENCES `st_roles` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='系统_角色菜单关系表';

-- ----------------------------
-- Records of st_rolemenu
-- ----------------------------
BEGIN;
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('07651fd4-c063-11f0-97b1-0242ac110003', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-aaaa-bbbb-cccc-ddddeeeefff5', '1', '2025-11-13 07:33:20', '');
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('07652548-c063-11f0-97b1-0242ac110003', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-aaaa-bbbb-cccc-ddddeeeefff5', '1', '2025-11-13 07:33:20', '');
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('076783b6-c063-11f0-97b1-0242ac110003', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-aaaa-bbbb-cccc-ddddeeeefff6', '1', '2025-11-13 07:33:20', '');
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('07678768-c063-11f0-97b1-0242ac110003', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-aaaa-bbbb-cccc-ddddeeeefff6', '1', '2025-11-13 07:33:20', '');
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1457fbb8-a963-11f0-97b1-0242ac110003', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', 'efe66f30a89f11f097b10242ac110003', '1', '2025-10-15T当前时间（例如10:30:00.00）', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0008f7ca-ed80-44be-b98c-71ef1e91c357', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-15731dcc-f552-4160-b5c5-99a9112ae341', '3', '2025-01-06T06:10:14.680', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-003f54cb-1cbf-46af-b37b-12b2e51b2ac7', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-fb3c1d88-c81f-4775-9c96-684bc9dabc75', '3', '2025-08-21T15:20:34.691', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0075ee1d-e919-4dca-ad6a-aa1fcc59f765', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-8e29b12a-b61e-4ec6-b7ea-abab46cf27f6', '3', '2024-07-10T11:13:50.033', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-00a507db-09b6-4da4-bf07-5822789011d1', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-eabd4d64-15df-485c-96b6-d6e9b0792c4f', '3', '2024-11-18T12:35:25.135', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-00bd5f49-cab4-4d89-933c-2c89718be30f', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3', '1', '2024-02-06T21:18:36.357', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-00cf8e03-abaf-4605-9328-6767bace63f9', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-bf697261-39c2-408c-b11a-b5a60ec89994', '3', '2024-07-08T15:40:51.116', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-00e4ba1e-83c8-4c3d-94ae-0d79e30efb00', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-551fd76e-0890-4566-a3d0-dfa2215f3e72', '3', '2024-05-07T12:59:31.838', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-010cc89b-e0a4-416e-9c54-9e042db29d14', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-eabd4d64-15df-485c-96b6-d6e9b0792c4f', '3', '2024-11-18T12:35:25.217', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0111ae2a-13a9-4fb3-bf14-c6cfb60c2c5f', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-910116aa-e8f8-11ee-934c-fa163efa1f90', '3', '2024-04-17T22:01:37.638', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-014e344a-bc06-44aa-97b0-05b5bf09e549', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-a9456c2b-c3f7-41b3-b2c4-1fd8e622dcc6', '3', '2024-05-14T14:09:50.529', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-01668e71-bd26-46d5-a611-96229f51942f', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-679f830a-db5f-40d8-b210-fded905516ac', '3', '2024-05-07T12:53:20.690', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-018a7098-5fc8-4739-bb5f-3adfeb060328', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', '3', '2024-02-06T21:14:03.950', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-01fa35f1-c629-449c-9ed5-14fa00908ec0', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-954722d2-76cf-4e64-bdf8-f11ef7ceef23', '3', '2025-01-06T06:10:14.906', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-01fd1fc4-7b49-4918-8443-6991c9009243', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-b1ebad5d-7fa4-4872-ba1f-13df498414c6', '3', '2024-05-07T18:54:40.929', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-023a383a-dd25-44bd-8f08-2bcccd111df3', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-f844e11c-d447-48c3-87bf-1202492b8b03', '3', '2024-09-22T23:05:39.912', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0284da66-7daf-4f8b-b240-a2b9bcc4ea72', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-cbb13f49-17eb-40fd-80f2-8b7fba973361', '3', '2024-05-07T13:01:31.723', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-031e089e-85ab-4dd1-8ea6-ef8c30cee194', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-6226ed9a-1517-471a-8960-c6f23e7fb0e4', '3', '2025-05-07T18:11:38.735', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0347778c-943e-4776-b819-9e433beb3580', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-ce704fab-6961-4091-a671-ce8d9e6b6549', '3', '2024-12-02T22:43:18.196', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-038efdb4-7584-4ddd-9ab5-f5c4adcf302d', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-2eb9fc11-cba7-48e1-9af3-f22f8fcae18c', '3', '2024-07-01T10:30:20.414', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-04ba10c2-60cf-4365-b978-2d4ee5ba7d43', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-b64043df-c9f1-43a2-b96b-a6134b2953e1', '3', '2024-05-07T18:56:13.813', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-04be0761-3931-47ad-8d32-e758ece6e567', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-cbb13f49-17eb-40fd-80f2-8b7fba973361', '3', '2024-05-07T13:01:31.598', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-04feecfb-f2fc-49a6-b003-2b15390ef86e', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-82b00b86-cba2-43de-9904-f913eed8a570', '3', '2025-01-06T06:10:14.837', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0501066f-e750-4c75-ad41-26c4ded10dbb', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-8e29b12a-b61e-4ec6-b7ea-abab46cf27f6', '3', '2024-07-10T11:13:50.206', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-056ac14e-bf6f-4114-96a7-1a6e290b4904', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-087a4eac-7036-4074-a813-831efa3d6950', '3', '2024-05-08T12:36:58.709', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-05b74802-e8f4-11ee-934c-fa163efa1f90', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-f47ac10b-58cc-4372-a567-0e02b2c3d479', '1', '2024-03-23T21:14:04.291', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-06617177-c9c5-441f-b646-4c56444dd4d5', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-4d66eeda-f440-4994-8998-bc2fcaba3c16', '3', '2024-05-08T12:35:43.692', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-06702168-851a-4dbb-aca7-414ff15883d5', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35', '3', '2024-04-16T12:34:36.779', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0679be22-f113-4ce8-ab06-50a1cee619f3', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998', '3', '2024-02-06T21:23:40.953', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-06be0b3e-3747-4a2c-8990-95c323440dd4', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-d24188bd-5dc8-4e37-8bf4-0d355cada866', '3', '2024-05-07T18:32:36.291', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-07806f7f-e764-46b9-b0c2-7ecc66cddac7', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-534f4acb-9a7c-465a-af38-1c8dadfefe95', '3', '2024-07-10T11:12:28.752', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-07a8c846-bcb7-4760-a2b3-829513f06418', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-17a53f15-4a36-4450-abf5-387825a2434a', '1', '2024-05-08T12:35:10.242', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-07ae55cb-fdbb-4449-8bdb-695eef9e5c93', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-f844e11c-d447-48c3-87bf-1202492b8b03', '3', '2024-09-22T23:05:39.839', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-07d2ee11-d8cf-43ea-8399-2af268de915d', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-6f8c6196-a29a-4b1d-89f4-9eec372e2c87', '3', '2024-05-07T12:41:46.216', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-07ecca43-1488-454a-83b5-6d67723fc925', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65', '1', '2025-01-06T06:12:35.860', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-08090dfd-7f8f-4286-a301-159308e437b7', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-38af2070-0ce6-4912-9ad3-dfd5959cdd53', '3', '2024-12-02T22:44:07.117', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-08b5c371-5416-490f-ba23-6d6d2c5eb5bd', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65', '1', '2024-03-27T22:12:01.730', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-08bb1865-785f-42de-b451-7188c580a42d', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-4d66eeda-f440-4994-8998-bc2fcaba3c16', '3', '2024-05-08T12:35:43.663', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-08dfbdfb-2d25-4795-9700-01610b23ad21', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-c0220993-26e0-4d21-bc25-f612c67170c5', '1', '2024-05-05T22:25:00.533', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0a254c58-39aa-434b-8b44-224f04d1374a', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-a9456c2b-c3f7-41b3-b2c4-1fd8e622dcc6', '3', '2025-01-06T06:12:36.169', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0a34c481-94d1-4213-90a7-72fe6a88bad6', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-eabd4d64-15df-485c-96b6-d6e9b0792c4f', '3', '2024-11-18T12:35:25.093', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0a4f6d32-9650-47a9-801e-6f8b534e2910', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', '3', '2024-02-06T21:10:00.018', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0a5df010-601a-4d54-98d3-8a34d9354781', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-6d018c56-58b2-4277-a258-28707ae35cc7', '3', '2024-11-18T12:36:19.526', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0acbe92a-060c-45ee-afd9-a3d3a317ef47', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-097434fa-dbbc-4ee3-ac93-fed60c6e18ce', '3', '2024-04-11T18:37:18.307', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0ad255ea-4285-4b02-b2e3-cad5de241ba1', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-bf697261-39c2-408c-b11a-b5a60ec89994', '3', '2024-07-08T15:40:51.095', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0b40c671-aa64-46ab-bc30-a0b52d4dd0f6', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-d24188bd-5dc8-4e37-8bf4-0d355cada866', '1', '2024-05-07T18:32:35.825', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0be76e34-c683-4891-a300-2861d3166f6c', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-d24188bd-5dc8-4e37-8bf4-0d355cada866', '3', '2025-01-06T06:10:14.997', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0c4cbc32-7b5d-40f4-aabb-f33511a3a469', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-8d0cc23d-60b6-4092-b10a-915e0afcca55', '3', '2025-03-14T17:56:25.691', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0cf45c28-eaca-4173-a77d-9ba8f7b560c3', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-6f8c6196-a29a-4b1d-89f4-9eec372e2c87', '3', '2024-05-07T12:41:46.247', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0d359d16-b54a-43e0-b7fc-5ef71764f95b', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-ce704fab-6961-4091-a671-ce8d9e6b6549', '3', '2024-12-02T22:43:18.053', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0d48fd7b-155b-48d5-8989-f27ff87318bd', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-2eb9fc11-cba7-48e1-9af3-f22f8fcae18c', '3', '2024-07-01T10:30:20.456', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0d4acf1a-cc59-490e-b6cf-762ef4e0eccc', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-8e29b12a-b61e-4ec6-b7ea-abab46cf27f6', '3', '2024-07-10T11:13:50.227', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0d9f17e9-3553-4dca-838f-e32dbed945cf', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-7fb353f6-a56a-4622-a79d-dab0c178febf', '3', '2024-02-06T21:16:21.096', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0daed0e2-55e7-4e3a-a953-271bfe264ca6', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-da5dc88e-3ff9-4ed8-a724-c15377d306a3', '3', '2024-05-08T12:31:45.966', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0dd4ea72-f71f-45a2-89e8-151bf720c9db', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998', '3', '2024-03-28T13:54:08.778', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0e23d853-25ef-47a8-8213-c799c39de914', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-087a4eac-7036-4074-a813-831efa3d6950', '3', '2025-01-06T06:10:14.652', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0e57888f-6546-4937-b8cc-d9fe92dc418d', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-c0fc7d55-5459-433c-ad6a-593856295d51', '3', '2025-01-06T06:10:14.965', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0e95b75c-0081-46dd-8a46-deddb3b0a503', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-8daa7ebc-0b27-4236-b4d6-2e872f771f3a', '3', '2024-05-07T18:45:01.519', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0ecfeec0-3e5c-4983-93e2-6b9b38a79495', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-fb3c1d88-c81f-4775-9c96-684bc9dabc75', '3', '2025-08-21T15:20:34.942', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0ed878a5-7d43-40d2-8f89-d97d2aedfd74', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-63929fcc-e8f9-11ee-934c-fa163efa1f90', '3', '2024-04-17T22:01:39.738', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0ee9da00-e684-4f21-a64d-c2e6b724164d', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-ecaa4072-d8ae-4f54-b681-6d42ca75db12', '3', '2024-05-07T18:57:15.182', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0fa10675-8dd7-4fce-bf73-7e5f10fb3492', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-ce704fab-6961-4091-a671-ce8d9e6b6549', '3', '2024-12-02T22:43:17.999', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0fb7f124-1c36-4c5b-ab54-14f9f55c168e', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-8d0cc23d-60b6-4092-b10a-915e0afcca55', '3', '2025-03-14T17:56:25.661', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-1041a33b-5f47-49c6-a0d2-da55626dc09a', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-c38a1e47-8271-43f2-aca3-bb7b2db33ad0', '3', '2025-01-06T06:10:14.971', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-118c0263-64e0-4428-8a9f-dee04bd47a65', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-f78d8a42-fd40-4643-a737-cb85234737d6', '3', '2024-05-21T13:46:27.958', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-11a4e052-6d8b-49d8-baa7-ceb95cb34d8a', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-407dc22f-4e2a-4c52-8caa-eb667d0ec34c', '3', '2025-01-06T06:12:35.982', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-11bd47d4-441d-4be1-bdeb-a5cbe2ffc07e', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-7c63297e-ec51-49cb-ab95-2c33229455b4', '3', '2024-07-10T11:02:07.677', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-11d208f3-67eb-40f8-a611-7f69d4975ca0', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-087a4eac-7036-4074-a813-831efa3d6950', '3', '2024-05-08T12:36:58.694', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-11f46f75-4c71-4153-bba0-0e6967da210f', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-86317a13-8a2c-41fd-b1ad-051ca070a875', '3', '2024-02-06T21:21:31.463', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-129f1128-7434-4df8-a63b-a69b5d21a69f', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-2eb9fc11-cba7-48e1-9af3-f22f8fcae18c', '3', '2025-01-06T06:12:35.935', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-12c3d355-2306-4dee-b99f-7d71697de4ee', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-f5231573-bc14-4862-a77b-d4c09e31d136', '1', '2025-02-10T12:21:25.621', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-13693413-f5d1-4e4b-8f35-c9dc66cb19da', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-b1ebad5d-7fa4-4872-ba1f-13df498414c6', '3', '2024-05-07T18:54:40.574', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-136c24a9-9e4b-4dce-9b28-611180b3edd6', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-954722d2-76cf-4e64-bdf8-f11ef7ceef23', '3', '2024-07-01T18:06:39.569', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-1398194b-a5db-42a5-9ff2-976fdaac8747', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-4d66eeda-f440-4994-8998-bc2fcaba3c16', '3', '2024-05-08T12:35:43.735', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-13fb36f5-f496-4bbf-acc9-27bb9dd60fd8', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-25cf1417-8726-4732-abd0-38908ba1d9b2', '3', '2025-01-06T06:10:14.696', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-141087cc-a94c-4696-a300-6b10ec6e78d2', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-58da609f-67ca-4ea4-acea-d1c5fb7ec20d', '3', '2024-07-01T10:40:04.545', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-142f719a-439a-48a0-a642-975c853e3696', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-155d2725-4be7-4e83-9ac0-88552a02023f', '3', '2024-02-06T21:22:24.569', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-14532f7c-6ac9-453d-a0ee-6bb186ab2f08', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-08310c14-214f-47e0-b973-fc9043e4486d', '3', '2025-02-10T12:35:49.834', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-14a9f383-f47f-45b3-8275-aa01a4d946ff', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', '3', '2024-02-06T21:10:00.082', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-14fa6732-1ee0-4aae-9b63-c518046325c1', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-8d0cc23d-60b6-4092-b10a-915e0afcca55', '3', '2025-03-14T17:56:25.676', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-150ebfb4-4454-4d59-b9b5-34c0e694c9b4', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-da5dc88e-3ff9-4ed8-a724-c15377d306a3', '3', '2025-01-06T06:12:36.248', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-150f9a1c-04b3-4d0f-9ccb-2354358bffa0', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-e1a6e9a2-8a97-417b-bc5d-ad936b0ac41f', '3', '2025-01-15T21:21:36.559', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-153aa835-4a3f-4be5-b26b-3a62ab255d12', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-7c63297e-ec51-49cb-ab95-2c33229455b4', '3', '2024-07-10T11:02:07.704', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-15916b16-8b15-4027-856a-b8faee7d8501', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-c38a1e47-8271-43f2-aca3-bb7b2db33ad0', '3', '2024-05-21T13:41:44.608', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-1610aee7-5d29-4ddc-bb45-0fe69bd7eee4', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-074abac7-fe4c-4908-aa7e-d72dacd94014', '3', '2024-09-22T22:03:15.448', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-165b1910-2e84-4889-a948-7bc5500c2785', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-f5231573-bc14-4862-a77b-d4c09e31d136', '3', '2025-02-10T12:21:25.749', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-16904712-d0f5-413b-9997-aeba595612d1', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998', '1', '2025-01-06T06:12:36.088', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-16fa3ef7-5246-4650-868d-b7cff23a149b', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-2eb9fc11-cba7-48e1-9af3-f22f8fcae18c', '3', '2024-07-01T10:30:20.374', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-173b119c-1a29-4a3f-94e8-b3de6f25143d', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-5ae215f6-d749-459f-9a64-bb022cc1f6f9', '3', '2025-04-20T17:22:59.838', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-17bc57f6-b5bf-4648-94c6-c52df7c4d997', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-c03dadaf-4a89-42a1-8b36-847d34c8b337', '3', '2025-03-14T17:55:27.611', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-183159e4-8b4b-42df-b477-f2625d9246f7', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-097434fa-dbbc-4ee3-ac93-fed60c6e18ce', '3', '2024-04-11T18:37:18.790', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-1853ea99-ef89-4c72-bbdf-3e77b24bba9c', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-dbfbbf3e-59ce-4076-bbed-2fa699999686', '3', '2024-05-07T12:44:49.356', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-18a19fa2-1b2c-472f-972d-3ba57e6ea0c2', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-6d459f8b-86da-44ba-a90d-ee644c046bda', '3', '2025-05-18T21:06:24.772', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-18b018f8-3577-4c6d-b78c-a51782d5971a', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-2eb9fc11-cba7-48e1-9af3-f22f8fcae18c', '3', '2024-07-01T10:30:20.333', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-18bdc0ec-de20-4170-91e4-6c9aac1e35d8', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-423e72b1-72ea-4dbb-99f4-e72772bafc3c', '3', '2024-04-25T19:34:38.899', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-18db4ed1-1bf9-442c-850e-8360a39e8345', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-d24188bd-5dc8-4e37-8bf4-0d355cada866', '3', '2024-05-07T18:32:36.355', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-190442be-a437-4b31-8a56-7d690430b0ca', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-15731dcc-f552-4160-b5c5-99a9112ae341', '3', '2024-12-17T11:41:15.642', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-1908f4d0-44c7-4270-84fa-9d1050b0eff2', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-63929fcc-e8f9-11ee-934c-fa163efa1f90', '3', '2024-04-17T22:01:36.605', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-195e2960-82de-4264-af41-eb9ee078b1a3', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-8daa7ebc-0b27-4236-b4d6-2e872f771f3a', '3', '2024-05-07T18:45:01.078', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-19b47148-b579-40f2-82e3-a10c1ad6c2ff', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-2eb9fc11-cba7-48e1-9af3-f22f8fcae18c', '3', '2024-07-01T10:30:20.313', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-1a01eb54-330c-42e2-b485-5e7817d3beb1', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-6c7208c0-bbc3-4d8d-a19b-6ca9d6b269fb', '3', '2025-05-18T21:16:36.673', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-1a040e8e-964b-4910-abd4-4547e4091d93', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-94bb8cdb-75e0-4561-8af5-7c1107f0426d', '3', '2025-05-19T12:23:33.440', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-1a33717d-d37b-4a20-bfa6-5f0015437101', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-f47ac10b-58cc-4372-a567-0e02b2c3d479', '3', '2025-01-06T06:12:36.288', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-1a37d8de-b47d-42b3-b68b-0716b8cfdb7c', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-f844e11c-d447-48c3-87bf-1202492b8b03', '3', '2024-09-22T23:05:39.546', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-1a72346f-667d-4f12-8d8c-a087fe435638', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-66382360-f89d-47fc-889a-61c720bdd826', '3', '2024-12-02T22:44:44.636', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-1ac3ca1f-18d3-4dfa-932a-88abc404652a', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-902ff997-6669-40bd-9f82-d8aeb297f39b', '3', '2024-07-08T15:41:24.797', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-1ac64316-73c2-4446-bbcd-9cdf91aaa23b', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-902ff997-6669-40bd-9f82-d8aeb297f39b', '3', '2024-07-08T15:41:24.636', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-1b2ddf07-1ac3-45ef-b2ce-3c5b24a143f4', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-82b00b86-cba2-43de-9904-f913eed8a570', '3', '2024-11-18T12:32:29.441', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-1b6a9fa2-f26a-4a1b-ae7d-528929180a52', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-c0fc7d55-5459-433c-ad6a-593856295d51', '3', '2024-05-07T18:53:09.802', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-1b701f70-193a-45b7-aa24-9296e6f85e12', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-bf697261-39c2-408c-b11a-b5a60ec89994', '3', '2024-07-08T15:40:51.013', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-1c030346-2334-49ff-bcdf-3c25c9e9cd8e', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-66382360-f89d-47fc-889a-61c720bdd826', '3', '2024-12-02T22:44:44.612', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-1c66ea72-73ec-463e-8a3b-0ee0fab3beac', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-439363cf-9c16-4b9e-8840-64bb093cbbd3', '3', '2024-06-24T16:40:08.535', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-1c6b2093-2e2b-4c94-a981-e3b7c6022e77', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-08310c14-214f-47e0-b973-fc9043e4486d', '3', '2025-02-10T12:35:49.853', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-1c85a380-2b29-4128-abc5-832d8174344d', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-dbfbbf3e-59ce-4076-bbed-2fa699999686', '3', '2024-05-07T12:44:49.457', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-1d0317d8-a37a-4ea5-8dc9-d33e97061326', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-074abac7-fe4c-4908-aa7e-d72dacd94014', '3', '2024-09-22T22:03:15.366', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-1d11cd9f-9bb2-4fec-bd50-f886636be3de', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-50b321e8-2886-4b99-a921-cbb5baacf1ac', '3', '2024-07-06T11:53:34.843', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-1d7f4f0e-2f2b-4770-b0d6-268ac5395fc4', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-94bb8cdb-75e0-4561-8af5-7c1107f0426d', '3', '2025-05-19T12:23:33.299', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-1dbc6b1b-d7df-4ec4-abc8-bf1adcbc5f32', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-8daa7ebc-0b27-4236-b4d6-2e872f771f3a', '3', '2025-01-06T06:10:14.880', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-1de1f505-cee1-4769-91a7-e90303df35b2', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-f844e11c-d447-48c3-87bf-1202492b8b03', '3', '2025-01-06T06:12:36.299', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-1e7eba75-f68e-44e8-bade-e37bbd259320', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-dbfbbf3e-59ce-4076-bbed-2fa699999686', '1', '2024-05-07T12:44:49.430', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-1edea819-6658-4cb5-a1e4-01f0652c114b', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-f5231573-bc14-4862-a77b-d4c09e31d136', '3', '2025-02-10T12:21:25.512', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-1fb9c11f-66a7-4a06-931b-b54ec3104702', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-5d318e20-3c58-4944-b7ac-c29e26df5ac0', '3', '2025-01-06T06:12:36.048', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-20210330-4b42-46a6-b3b7-2e08114707b2', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-c38a1e47-8271-43f2-aca3-bb7b2db33ad0', '3', '2024-05-21T13:41:44.589', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-20c65134-406c-42e8-98e1-b6cdb2699ccc', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-155d2725-4be7-4e83-9ac0-88552a02023f', '3', '2024-02-06T21:22:25.028', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-214f4a3d-1df6-4ca7-a477-5a2e73ee20cd', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-407dc22f-4e2a-4c52-8caa-eb667d0ec34c', '3', '2024-07-10T11:14:48.219', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-21500549-ac2f-4bed-b5af-fddbb5d52856', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-f5231573-bc14-4862-a77b-d4c09e31d136', '3', '2025-02-10T12:21:25.597', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-21730b0c-033e-44fa-acda-d3c0e28fef27', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-5ae215f6-d749-459f-9a64-bb022cc1f6f9', '3', '2025-04-20T17:23:00.170', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2192e8f8-4fb7-4792-b729-c8788251f4d0', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-86816d21-ec0c-4dc6-ad1d-8edea9716d09', '3', '2024-05-07T18:51:17.366', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-21db06ce-1001-416b-b917-4c9d5fe7c1f9', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-dc11e4e7-6e9f-4975-a6b7-5f97ba1c46d3', '3', '2025-01-06T06:12:36.258', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-21e9d825-1087-4225-a4c8-d555df85896a', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-5d318e20-3c58-4944-b7ac-c29e26df5ac0', '3', '2024-05-07T14:26:42.614', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2270eee1-f86a-4c7d-9af0-b0a66a661403', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-25cf1417-8726-4732-abd0-38908ba1d9b2', '3', '2024-05-07T14:30:55.258', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2286f60e-719c-4364-a27a-acc89e80fe14', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-15731dcc-f552-4160-b5c5-99a9112ae341', '3', '2024-12-17T11:41:15.674', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-233379f8-4ae3-48c8-9437-8fd2d44335c0', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-8462eabd-4a2d-4b79-8a73-99ecd64f3d40', '1', '2024-05-14T21:17:01.300', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2333b34b-2b9f-45e0-8503-4ced007d701b', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-f78d8a42-fd40-4643-a737-cb85234737d6', '3', '2024-05-21T13:46:28.035', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2341ce58-4b4e-4f6c-ad20-e0632e355ac1', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-6c7208c0-bbc3-4d8d-a19b-6ca9d6b269fb', '3', '2025-05-18T21:16:36.653', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-23ad5bd4-faf1-4857-ae26-8509eae50681', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-66382360-f89d-47fc-889a-61c720bdd826', '3', '2024-12-02T22:44:44.531', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2434749f-7d6e-46a2-a167-3210ff750a15', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-ecaa4072-d8ae-4f54-b681-6d42ca75db12', '1', '2024-05-07T18:57:14.880', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2484bc4f-ff62-4473-9a0c-99b290db87e8', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', '3', '2025-01-06T06:10:14.641', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-24d6999d-77fc-4eb0-9a25-a211f6dea1af', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-534f4acb-9a7c-465a-af38-1c8dadfefe95', '3', '2024-07-10T11:12:28.858', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-24facc70-08cd-486e-9252-9c818df6fa40', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35', '3', '2024-04-16T12:34:36.412', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-253ec629-e04e-4c1f-9840-6e47bd4e7ecb', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-5ae215f6-d749-459f-9a64-bb022cc1f6f9', '3', '2025-04-20T17:23:00.032', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-25b0b966-e361-4772-9ca1-b8982739f807', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-7c63297e-ec51-49cb-ab95-2c33229455b4', '3', '2024-07-10T11:02:07.729', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-25c962b0-b311-4b95-9a06-6c95c911e06f', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', '1', '2024-02-06T21:09:59.953', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-25de1e88-ca5f-4248-8bd8-71d37065a41a', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', '3', '2024-02-06T21:14:03.889', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-25fc2a38-0acc-4668-9476-93db349380f5', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-63929fcc-e8f9-11ee-934c-fa163efa1f90', '3', '2024-04-17T22:01:38.960', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-262cf6aa-cca4-45f4-8eab-16ee783c5889', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-25cf1417-8726-4732-abd0-38908ba1d9b2', '3', '2024-05-07T14:30:55.352', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2662a6a9-6c73-4632-a66e-4c1ba56c3296', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35', '1', '2024-04-16T12:34:36.127', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2688fe78-b2e4-4a2e-b49a-cbcef8e33660', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-fe8c5e35-ba92-4f2e-9d2f-f1c89a278ef9', '3', '2025-01-08T19:07:57.826', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-26a56a7a-6280-450f-ac8b-cdf3ce268a83', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-c38a1e47-8271-43f2-aca3-bb7b2db33ad0', '3', '2024-05-21T13:41:44.476', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-26a8c033-add9-43cc-bb78-d308aa9d3a22', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-dc11e4e7-6e9f-4975-a6b7-5f97ba1c46d3', '3', '2024-05-08T12:36:21.272', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-26e2f293-45ec-442d-ba65-bcb3c85cd9df', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-6d018c56-58b2-4277-a258-28707ae35cc7', '3', '2024-11-18T12:36:19.391', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-26ec8352-7601-4998-9c78-3e5e38d733ff', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-fb3c1d88-c81f-4775-9c96-684bc9dabc75', '3', '2025-08-21T15:20:34.719', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-27579f84-ef5a-48e8-9c49-034750540437', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-50b321e8-2886-4b99-a921-cbb5baacf1ac', '3', '2025-01-06T06:12:36.011', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2775e155-75a2-41a9-8f28-c1b7b6a2b9e7', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-fe5bb833-fdd7-4416-81dd-f5b20107540f', '3', '2025-01-06T06:10:15.052', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2810d2aa-14d1-4cef-80c5-07e9d946644e', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-954722d2-76cf-4e64-bdf8-f11ef7ceef23', '3', '2024-07-01T18:06:39.445', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2897d185-e33f-4dd0-8551-292096554a81', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-08310c14-214f-47e0-b973-fc9043e4486d', '3', '2025-02-10T12:35:49.793', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-289aba72-5fdc-4c87-a49a-f9213e8085b0', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-f844e11c-d447-48c3-87bf-1202492b8b03', '3', '2024-09-22T23:05:39.288', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-289e7262-190a-4807-addf-c6fc17f9117a', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-e1a6e9a2-8a97-417b-bc5d-ad936b0ac41f', '3', '2025-01-15T21:21:36.481', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-28a9d865-a35a-48bb-a681-26eeee6abe4d', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-074abac7-fe4c-4908-aa7e-d72dacd94014', '1', '2024-09-22T22:03:15.408', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-28b8558c-d447-460f-aa87-973c88b9ca77', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-eabd4d64-15df-485c-96b6-d6e9b0792c4f', '3', '2025-01-06T06:12:36.267', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-294fd797-055c-4767-b5aa-7bd6edcc3688', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-e7149e74-4856-4440-8d94-99f915731842', '1', '2024-09-04T16:42:57.502', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-29c6da22-cb96-4e39-b3db-b1cebcb4884d', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-016c289e-75ae-4034-85db-94b1cff32233', '3', '2024-04-11T19:03:05.043', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-29fe9ed3-46e7-47c1-bc6e-9586de5d2bca', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-087a4eac-7036-4074-a813-831efa3d6950', '3', '2024-05-08T12:36:58.786', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2a654cbd-c603-4ec6-923a-9d1281c6c568', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-910116aa-e8f8-11ee-934c-fa163efa1f90', '3', '2024-03-28T13:54:09.077', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2a78014a-fc0b-413f-b2df-68da54b19ac1', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-fe8c5e35-ba92-4f2e-9d2f-f1c89a278ef9', '3', '2025-01-08T19:07:57.718', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2ac75568-7fcb-4620-8759-979a1089abc7', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-188ff31e-ff6b-47eb-a6d6-1ed0b703c0b5', '3', '2024-07-01T10:46:49.409', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2b1932bd-3939-4129-8797-6bca174869ad', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-910116aa-e8f8-11ee-934c-fa163efa1f90', '3', '2024-04-17T22:01:43.475', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2b319396-e712-4cdc-b3f3-cd64a00135dd', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-155d2725-4be7-4e83-9ac0-88552a02023f', '1', '2024-02-06T21:22:25.091', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2b65dcbb-e91b-4621-a1b9-9d46fd71bfb7', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-bbf33596-d4ed-41ce-a7d3-cfba75618c64', '3', '2024-04-11T18:46:18.346', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2b7266c3-405e-4b2d-b5a5-de676c2fe0d8', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-7fb353f6-a56a-4622-a79d-dab0c178febf', '3', '2024-02-06T21:16:20.634', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2be64584-fd5d-49d3-82e2-2111be74220c', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-8daa7ebc-0b27-4236-b4d6-2e872f771f3a', '3', '2024-05-07T18:45:01.239', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2c2efbf2-f060-45c3-8a65-82b7868ec246', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-e7149e74-4856-4440-8d94-99f915731842', '3', '2024-09-04T16:42:57.551', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2cbe6f52-9d46-49df-b646-70e93008605d', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-b1ebad5d-7fa4-4872-ba1f-13df498414c6', '3', '2024-05-07T18:54:40.055', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2d1c27ae-423e-41d5-871d-cb605df07cef', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-074abac7-fe4c-4908-aa7e-d72dacd94014', '1', '2025-01-06T06:12:35.804', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2d3046a2-0a3d-45ad-a8b7-e68a0109b905', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-82b00b86-cba2-43de-9904-f913eed8a570', '3', '2024-11-18T12:32:29.411', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2dc90b03-8ce5-43f3-9e1a-6ab2846a64ff', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-7fb353f6-a56a-4622-a79d-dab0c178febf', '3', '2025-01-06T06:12:36.096', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2dcd9a34-cedc-4d2a-b622-ce3af3863204', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-5ae215f6-d749-459f-9a64-bb022cc1f6f9', '3', '2025-04-20T17:22:59.979', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2de612d5-5199-45c3-8bf1-2eba3b32ec9e', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65', '1', '2025-01-06T06:10:14.667', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2e462e09-18df-4e3a-b685-304ad8462ed9', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-407dc22f-4e2a-4c52-8caa-eb667d0ec34c', '3', '2024-07-10T11:14:48.321', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2e5174b4-979b-4152-b18b-909bbd6f8ae6', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-8d0cc23d-60b6-4092-b10a-915e0afcca55', '3', '2025-03-14T17:56:25.518', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2e6f306c-567d-48d6-ae04-f738e19d285b', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-b1ebad5d-7fa4-4872-ba1f-13df498414c6', '1', '2024-05-07T18:54:39.813', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2e7d1bdd-5cb8-475e-bf41-62de462c58c1', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-6d459f8b-86da-44ba-a90d-ee644c046bda', '3', '2025-05-18T21:06:24.709', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2eea2b79-063c-4279-946f-3f12dfab5ad7', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-c38a1e47-8271-43f2-aca3-bb7b2db33ad0', '3', '2024-05-21T13:41:44.494', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2f7de0a6-0a3a-46ff-81fa-4bc19214b6c5', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-38af2070-0ce6-4912-9ad3-dfd5959cdd53', '3', '2025-01-06T06:12:35.962', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2fe6d963-2db6-4f3c-8b73-0a1517f10f2d', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-534f4acb-9a7c-465a-af38-1c8dadfefe95', '3', '2024-07-10T11:12:28.816', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2ff2115f-b6cc-416e-af4f-74070806dac8', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-86816d21-ec0c-4dc6-ad1d-8edea9716d09', '3', '2024-05-07T18:51:17.010', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2ffb062a-56e2-47d4-a82a-3cd610690170', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-c0220993-26e0-4d21-bc25-f612c67170c5', '3', '2024-05-05T22:25:00.582', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-300366a5-319c-416b-ad1e-ef118c6fd8fe', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-b64043df-c9f1-43a2-b96b-a6134b2953e1', '3', '2024-05-07T18:56:14.123', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3066b4c0-4925-4d63-86ca-bcf416cf37b5', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-074abac7-fe4c-4908-aa7e-d72dacd94014', '3', '2024-09-22T22:03:15.468', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-309eb144-3454-4f88-b798-4c95fd3c45e4', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-d24188bd-5dc8-4e37-8bf4-0d355cada866', '3', '2024-05-07T18:32:35.904', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-30d79281-b1f7-4ace-85f5-d549fc1478d1', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-86317a13-8a2c-41fd-b1ad-051ca070a875', '3', '2024-02-06T21:21:31.002', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-30e1009c-95e3-4557-ac58-ca0d90a7ff3a', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-a9456c2b-c3f7-41b3-b2c4-1fd8e622dcc6', '3', '2024-05-14T14:09:50.644', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3152f937-f09d-45f7-942e-fa59293a8bbb', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-8462eabd-4a2d-4b79-8a73-99ecd64f3d40', '3', '2024-05-14T21:17:01.223', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-31956fa8-21d3-416a-ab0a-a2515c152499', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-e1a6e9a2-8a97-417b-bc5d-ad936b0ac41f', '3', '2025-01-15T21:21:36.443', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-31c6969c-e872-4f1c-9c2a-8900b21ac2e0', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-8daa7ebc-0b27-4236-b4d6-2e872f771f3a', '1', '2024-05-07T18:45:01.313', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-326a6b82-5cbe-40c7-ba56-0601d25a5fde', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-f844e11c-d447-48c3-87bf-1202492b8b03', '3', '2024-09-22T23:05:39.879', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-32aad5d1-ea20-4717-813b-fd53ba75850c', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-7c63297e-ec51-49cb-ab95-2c33229455b4', '3', '2024-07-10T11:02:07.808', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-32f231f4-a258-4e15-8f83-dcad66fd9658', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-b6a3917f-2f46-4d7a-b79d-dc5b91e963c5', '3', '2024-11-18T12:34:33.683', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3319f051-687b-44b8-9087-800bcd4afc08', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-b6a3917f-2f46-4d7a-b79d-dc5b91e963c5', '1', '2024-11-18T12:34:33.626', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-33338631-88fc-46f5-a568-155d6dbcd91d', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-087a4eac-7036-4074-a813-831efa3d6950', '1', '2024-05-08T12:36:58.646', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3348eaa4-28e8-467f-9aed-6d964288d557', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-074abac7-fe4c-4908-aa7e-d72dacd94014', '3', '2024-09-22T22:03:15.428', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3394da4f-c4cc-4f0d-9c0b-f5416c468f79', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-c0220993-26e0-4d21-bc25-f612c67170c5', '1', '2025-01-06T06:10:14.959', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-34b75fe6-e774-433c-baf5-b4b2f1511ae5', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-86317a13-8a2c-41fd-b1ad-051ca070a875', '3', '2024-02-06T21:21:31.060', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-34d6e5a3-bdfe-4f9d-87ac-3660bb6e346b', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-4d66eeda-f440-4994-8998-bc2fcaba3c16', '3', '2024-05-08T12:35:43.707', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-34e04c90-30e7-496e-a22e-ac7f3ecc9d4b', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-902ff997-6669-40bd-9f82-d8aeb297f39b', '3', '2024-07-08T15:41:24.619', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3578ddce-e752-4592-a1db-5f733d2edd3a', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-407dc22f-4e2a-4c52-8caa-eb667d0ec34c', '3', '2024-07-10T11:14:48.400', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-35821c53-1b81-4f76-b115-64d7c113ac4d', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-dbfbbf3e-59ce-4076-bbed-2fa699999686', '3', '2025-01-06T06:12:36.253', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-35ba94fd-54fa-46ad-a908-37375ddfa6ba', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-c0220993-26e0-4d21-bc25-f612c67170c5', '1', '2024-05-05T22:25:00.390', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-35c0ba90-868b-4cbe-a00f-51f3815dd778', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-ecaa4072-d8ae-4f54-b681-6d42ca75db12', '3', '2024-05-07T18:57:15.415', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3614b32c-0422-40e5-86ee-013f85c10022', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998', '3', '2024-02-06T21:23:40.430', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-362208e3-f412-413c-b842-da2e15100b18', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-3279ddd8-0024-4e37-b1f0-51fe30e57ff4', '3', '2025-01-06T06:12:35.945', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3718f63f-d9fa-4e36-9736-5a43ef7252df', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-016c289e-75ae-4034-85db-94b1cff32233', '3', '2024-04-11T19:03:05.111', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-379dcb87-846a-47c4-b185-68294a36ca2c', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-eeed1520-d1a8-416c-b42b-a24fa74e0d16', '2', '2024-05-07T12:42:28.499', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-37c97bac-d066-47c3-96e2-35aebadeba3b', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-25cf1417-8726-4732-abd0-38908ba1d9b2', '3', '2024-05-07T14:30:55.241', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-38134576-68b9-4d3d-8af3-f8b8d907c5fc', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-6226ed9a-1517-471a-8960-c6f23e7fb0e4', '3', '2025-05-07T18:11:38.794', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-386615ae-eb27-47ec-9c4c-ee64a4a6d4b3', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-731d1bc7-e47b-463e-bf26-5a113dee9716', '3', '2025-01-08T19:20:53.227', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-386aa10e-6c85-49a8-bb9f-f2277a17ec31', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-ce704fab-6961-4091-a671-ce8d9e6b6549', '3', '2024-12-02T22:43:18.035', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-38898025-0094-4369-986f-4e59acebc1f2', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35', '1', '2024-04-16T12:34:36.341', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3907c8ac-0087-4b79-8e01-1bc2cd6cd9ad', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-50b321e8-2886-4b99-a921-cbb5baacf1ac', '3', '2024-07-06T11:53:34.935', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3911691f-fcfe-43fb-996c-cee1c2ae9ab6', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-a9278b5d-f2ba-4429-854f-4662000f50e6', '3', '2024-05-05T22:27:18.031', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-39329441-6f2c-413e-9b12-5c9e65ad5796', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-b6a3917f-2f46-4d7a-b79d-dc5b91e963c5', '3', '2024-11-18T12:34:33.536', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-394d7133-2df8-41b9-96c9-f2bbab4064e1', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-188ff31e-ff6b-47eb-a6d6-1ed0b703c0b5', '3', '2024-07-01T10:46:49.328', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-39b232ca-b6c7-44eb-b967-ce4a3aea43d1', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-731d1bc7-e47b-463e-bf26-5a113dee9716', '3', '2025-01-08T19:20:53.007', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-39d5871d-c087-4c3b-90a8-378a75dc093a', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-c38a1e47-8271-43f2-aca3-bb7b2db33ad0', '3', '2024-05-21T13:41:44.532', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3a241e33-9c42-426b-a9f2-5b3de6eb2ace', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-dc11e4e7-6e9f-4975-a6b7-5f97ba1c46d3', '3', '2024-05-08T12:36:21.256', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3b2fe454-8933-4093-8bb7-affda6976e42', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-074abac7-fe4c-4908-aa7e-d72dacd94014', '3', '2024-09-22T22:03:15.385', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3b53db9c-baef-479f-bebf-c306a0fe2bac', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-dc11e4e7-6e9f-4975-a6b7-5f97ba1c46d3', '3', '2024-05-08T12:36:21.208', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3b55b9d2-4e91-4321-93f3-ab5e7365c7a3', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-b6a3917f-2f46-4d7a-b79d-dc5b91e963c5', '3', '2024-11-18T12:34:33.645', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3b79b5c8-fea3-45cb-8d7e-234eae4813f7', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-ecaa4072-d8ae-4f54-b681-6d42ca75db12', '3', '2024-05-07T18:57:15.500', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3c38d5ba-3ee4-4e94-affa-633221a6ee30', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-ce704fab-6961-4091-a671-ce8d9e6b6549', '3', '2024-12-02T22:43:17.914', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3d43c691-d8ca-4c43-9b9a-eaddf78901a2', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-17a53f15-4a36-4450-abf5-387825a2434a', '3', '2024-05-08T12:35:10.302', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3d4c1b6a-42a7-4869-a994-3b871f3602e5', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-8e29b12a-b61e-4ec6-b7ea-abab46cf27f6', '3', '2024-07-10T11:13:50.106', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3d551af4-0be9-4e50-8131-f9cb4b12a0f5', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-bbf33596-d4ed-41ce-a7d3-cfba75618c64', '3', '2024-04-11T18:46:18.608', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3d760d9a-f436-4bbf-9206-60e36610f8dd', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-b6a3917f-2f46-4d7a-b79d-dc5b91e963c5', '3', '2024-11-18T12:34:33.513', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3d7df79f-7385-4d96-950f-6cdf495ca961', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35', '1', '2025-01-06T06:10:14.754', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3da2ed4c-333a-4384-9824-e1c469a568f2', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-dc11e4e7-6e9f-4975-a6b7-5f97ba1c46d3', '1', '2024-05-08T12:36:21.194', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3db7e963-a3f0-45f4-a9ab-e048de860810', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-6c7208c0-bbc3-4d8d-a19b-6ca9d6b269fb', '3', '2025-05-18T21:16:36.617', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3dbba893-119a-4852-a8b0-adf761f39ae8', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-ce704fab-6961-4091-a671-ce8d9e6b6549', '3', '2024-12-02T22:43:17.966', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3de34d8f-8d42-4bf9-bdfb-2277f3dd409d', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-b64043df-c9f1-43a2-b96b-a6134b2953e1', '3', '2025-01-06T06:12:36.185', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3e02323a-b0a7-459c-97c0-b89f3d6ff8a2', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-e7149e74-4856-4440-8d94-99f915731842', '3', '2024-09-04T16:42:57.411', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3e41c83f-73c1-4023-9b50-2e526d030c84', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-f47ac10b-58cc-4372-a567-0e02b2c3d479', '3', '2024-04-17T22:01:41.446', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3edb04d7-e6d7-4343-be04-83096ea6d8c2', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-5d318e20-3c58-4944-b7ac-c29e26df5ac0', '3', '2024-05-07T14:26:42.490', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3f9692fc-6196-4f01-926f-caf0167c7dd9', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-86816d21-ec0c-4dc6-ad1d-8edea9716d09', '1', '2024-05-07T18:51:16.828', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3f9bf58e-1413-483f-ae00-ac1e686c76ec', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3', '1', '2024-02-06T21:18:35.917', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3fb8eb83-6f73-4192-9537-0b93f7812af9', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-eabd4d64-15df-485c-96b6-d6e9b0792c4f', '3', '2024-11-18T12:35:25.237', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3fec48dd-01f4-4e7f-a898-abe1e6e6e21e', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-c0220993-26e0-4d21-bc25-f612c67170c5', '3', '2024-05-05T22:25:00.607', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-400f1eb9-8197-41a6-82d1-5c7cc0c0866c', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-910116aa-e8f8-11ee-934c-fa163efa1f90', '3', '2025-01-06T06:12:36.152', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-40d8b437-8b93-49f0-acee-449d83f4c75d', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-fb3c1d88-c81f-4775-9c96-684bc9dabc75', '3', '2025-08-21T15:20:34.758', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-41598f22-011d-49df-9110-ce1b2280fc0b', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-b1ebad5d-7fa4-4872-ba1f-13df498414c6', '3', '2024-05-07T18:54:39.961', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-42392037-13f1-438d-a04d-f435fbff08e9', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-016c289e-75ae-4034-85db-94b1cff32233', '3', '2024-04-11T19:03:05.386', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-42e67b91-391b-4ffd-9c14-f5e7c9ff7158', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-731d1bc7-e47b-463e-bf26-5a113dee9716', '3', '2025-01-08T19:20:53.200', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-42fbdef1-a85f-4bba-8258-f53b120338fd', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-a9278b5d-f2ba-4429-854f-4662000f50e6', '1', '2024-05-05T22:27:18.054', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-430e0806-1ab4-4479-ba63-91dd590c5b2a', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-0ee32ded-100b-4505-95c4-65d5e9b3d93c', '3', '2025-01-06T06:10:14.662', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-43731c5e-eb5b-457f-891c-624ee2c19625', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-6d459f8b-86da-44ba-a90d-ee644c046bda', '3', '2025-05-18T21:06:24.751', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-4397e727-66ad-4fd8-8f74-adedd4b38d7e', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-f78d8a42-fd40-4643-a737-cb85234737d6', '3', '2024-05-21T13:46:27.998', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-43d07c99-77e8-452c-bfec-cab482224b82', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-7c63297e-ec51-49cb-ab95-2c33229455b4', '3', '2024-07-10T11:02:07.599', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-44579c90-2af4-4ced-b44c-0ac69cc9fe03', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-ecaa4072-d8ae-4f54-b681-6d42ca75db12', '1', '2024-05-07T18:57:15.344', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-44a8d16d-4d3e-4cdb-9f94-6380bb903b67', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-c03dadaf-4a89-42a1-8b36-847d34c8b337', '3', '2025-03-14T17:55:27.630', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-44c32e11-9b84-454f-bbc2-c3e32fcfd31c', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-fe5bb833-fdd7-4416-81dd-f5b20107540f', '3', '2024-05-22T11:00:14.983', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-44d76613-a39f-44c9-ac7d-22c05abac755', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-731d1bc7-e47b-463e-bf26-5a113dee9716', '3', '2025-01-08T19:20:53.118', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-4504453e-d0d1-49db-93be-c989870eda94', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-7fb353f6-a56a-4622-a79d-dab0c178febf', '3', '2025-01-06T06:10:14.831', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-452136eb-8980-423f-ba64-5b0e27e5dc09', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-c03dadaf-4a89-42a1-8b36-847d34c8b337', '3', '2025-03-14T17:55:27.662', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-459fb7a4-ba1f-4c2c-a914-6272d99fe663', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-50b321e8-2886-4b99-a921-cbb5baacf1ac', '1', '2024-07-06T11:53:34.958', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-45b992fa-4498-4382-9f6b-168bef32a726', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-8daa7ebc-0b27-4236-b4d6-2e872f771f3a', '3', '2025-01-06T06:12:36.133', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-45cf9374-64a5-4f42-8a3b-0e38e55abf76', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-016c289e-75ae-4034-85db-94b1cff32233', '3', '2024-04-11T19:03:04.780', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-468b69bb-ab89-4976-9687-061838fd0930', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-fe5bb833-fdd7-4416-81dd-f5b20107540f', '3', '2024-05-22T11:00:14.997', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-46e8a3c9-13e4-4536-a027-f3291b4dea8d', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-e1a6e9a2-8a97-417b-bc5d-ad936b0ac41f', '3', '2025-01-15T21:21:36.599', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-4715bb77-cde1-4d4e-84fa-79095b9ace9b', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-551fd76e-0890-4566-a3d0-dfa2215f3e72', '3', '2025-01-06T06:12:36.026', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-47486014-0f5d-4e7f-b50a-38e459a3891c', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-074abac7-fe4c-4908-aa7e-d72dacd94014', '3', '2024-09-22T22:03:15.508', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-47a0f721-a541-4c41-9751-0103c84192bf', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-58da609f-67ca-4ea4-acea-d1c5fb7ec20d', '3', '2024-07-01T10:40:04.676', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-47d4da4f-ca6d-4f90-913e-bcc49ef28495', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-8daa7ebc-0b27-4236-b4d6-2e872f771f3a', '3', '2024-05-07T18:45:00.936', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-483edd52-d407-4b33-885b-721de7753166', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-087a4eac-7036-4074-a813-831efa3d6950', '3', '2024-05-08T12:36:58.756', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-489ed939-6f49-4e8b-831f-7989c4b9fa7b', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-5ae215f6-d749-459f-9a64-bb022cc1f6f9', '1', '2025-04-20T17:22:59.787', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-48b7f5a7-b566-418e-bca6-0a7605a1cdef', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', '3', '2024-03-28T13:54:08.989', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-48c5ce4f-30ba-440f-90c3-b88508c5d757', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-e1a6e9a2-8a97-417b-bc5d-ad936b0ac41f', '1', '2025-01-15T21:21:36.520', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-49860d30-27cb-4ceb-a6e6-26cfd87ba3fb', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-5d318e20-3c58-4944-b7ac-c29e26df5ac0', '3', '2024-05-07T14:26:42.594', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-49fea41d-fd8c-4cf7-95c5-fa06f736a4ab', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-910116aa-e8f8-11ee-934c-fa163efa1f90', '3', '2024-04-17T22:01:41.313', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-4a897ea7-d3a6-4271-b382-ec91af197af5', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-6f8c6196-a29a-4b1d-89f4-9eec372e2c87', '3', '2024-05-07T12:41:46.184', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-4a8d2093-cb79-48a8-b735-ebf99e721d42', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-6d018c56-58b2-4277-a258-28707ae35cc7', '3', '2024-11-18T12:36:19.570', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-4b1772f7-3e10-4c73-9efd-ec3e977ec0ad', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-58da609f-67ca-4ea4-acea-d1c5fb7ec20d', '1', '2025-01-06T06:10:14.766', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-4b51d711-3943-4f0e-84f9-e7d6e41bccdb', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-016c289e-75ae-4034-85db-94b1cff32233', '3', '2025-01-06T06:12:35.774', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-4b5afd37-9b99-470d-bc0c-052abd155982', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-6d459f8b-86da-44ba-a90d-ee644c046bda', '3', '2025-05-18T21:06:24.667', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-4bc2f3a9-2f31-4a46-bbb2-3a492aa2f236', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-b64043df-c9f1-43a2-b96b-a6134b2953e1', '3', '2024-05-07T18:56:13.662', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-4bc317c6-577d-4c5c-96a6-3b3dda69f419', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-da5dc88e-3ff9-4ed8-a724-c15377d306a3', '3', '2024-05-08T12:31:45.947', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-4bddc155-60b5-4642-98a7-64f8364f10e4', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-902ff997-6669-40bd-9f82-d8aeb297f39b', '3', '2024-07-08T15:41:24.778', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-4be55c1a-1c45-4a27-aad1-d74271eb4ea7', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-551fd76e-0890-4566-a3d0-dfa2215f3e72', '1', '2024-05-07T12:59:31.963', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-4c2f654c-b868-46e9-bc5f-93a44e140fb1', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-38af2070-0ce6-4912-9ad3-dfd5959cdd53', '3', '2024-12-02T22:44:07.273', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-4c502887-69e9-40a5-a16a-4504aad312c4', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-f47ac10b-58cc-4372-a567-0e02b2c3d479', '3', '2024-04-17T22:01:37.772', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-4d0ab5f0-39db-440e-bc78-050d81aa4930', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-5b87c7af-00e3-44b6-b6c5-0a9a77882864', '3', '2024-05-07T12:57:10.413', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-4de5f960-ecab-4607-9a5e-56fe2099bc43', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-e1a6e9a2-8a97-417b-bc5d-ad936b0ac41f', '3', '2025-01-15T21:21:36.539', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-4e208c52-5c3e-4d25-ae92-eb85c5982bb9', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-dbfbbf3e-59ce-4076-bbed-2fa699999686', '3', '2025-01-06T06:10:15.006', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-4e209f3e-412e-47bd-8063-5c481fd60014', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-016c289e-75ae-4034-85db-94b1cff32233', '3', '2024-04-11T19:03:05.316', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-4e6f9f3f-e6ae-4cfc-8aa1-0b5f5c3a6e01', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-86816d21-ec0c-4dc6-ad1d-8edea9716d09', '3', '2025-01-06T06:10:14.865', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-4ee711b9-cd43-4b07-ba67-eded9ebac437', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-c0220993-26e0-4d21-bc25-f612c67170c5', '3', '2024-05-05T22:25:00.486', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-4fadabf1-02f6-427d-8871-25ad5413a882', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-dbfbbf3e-59ce-4076-bbed-2fa699999686', '3', '2024-05-07T12:44:49.481', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-4fc8cff9-7b10-4fb8-a28c-cc902f83c9f0', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-f5231573-bc14-4862-a77b-d4c09e31d136', '3', '2025-02-10T12:21:25.687', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-4fe535ce-e7c4-46e3-b4e4-b4fe00816e0b', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-8462eabd-4a2d-4b79-8a73-99ecd64f3d40', '3', '2024-05-14T21:17:01.272', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-4ffae3b2-9fef-4f8d-a107-ef78b68a25fb', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-8daa7ebc-0b27-4236-b4d6-2e872f771f3a', '3', '2024-05-07T18:45:01.151', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-5006ff7a-9c69-495c-a883-d0c0527b1c54', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-dbfbbf3e-59ce-4076-bbed-2fa699999686', '3', '2024-05-07T12:44:49.279', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-5016c541-17e9-4422-baca-8e0bf1ebd92d', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-ce704fab-6961-4091-a671-ce8d9e6b6549', '1', '2024-12-02T22:43:18.083', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-5121e95b-0329-4113-b245-8e36131e3cef', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65', '1', '2024-03-27T22:12:01.244', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-513c190e-6a8b-46bb-a844-cae44b2ff7e3', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-15731dcc-f552-4160-b5c5-99a9112ae341', '3', '2024-12-17T11:41:15.604', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-51717525-6e40-48ba-90e8-7036b2dff28f', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-c03dadaf-4a89-42a1-8b36-847d34c8b337', '3', '2025-03-14T17:55:27.519', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-5173e0ef-6b36-4c43-b512-5c3a148ac702', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-551fd76e-0890-4566-a3d0-dfa2215f3e72', '3', '2024-05-07T12:59:31.935', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-51a4e255-3529-4a1c-9b2f-8d896edc69e7', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-a9456c2b-c3f7-41b3-b2c4-1fd8e622dcc6', '3', '2024-05-14T14:09:50.510', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-51dba702-79d6-46ce-aa12-37bfa730b6b1', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-f47ac10b-58cc-4372-a567-0e02b2c3d479', '3', '2024-04-17T22:01:42.845', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-51e9ba69-c584-401d-840a-6539e4ae38de', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-58da609f-67ca-4ea4-acea-d1c5fb7ec20d', '1', '2024-07-01T10:40:04.655', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-52227bd6-9e85-42fe-a67e-df32909d8f1d', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-66382360-f89d-47fc-889a-61c720bdd826', '1', '2024-12-02T22:44:44.573', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-5293f33e-bd58-4c06-a9a7-bf625d9afbae', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-17a53f15-4a36-4450-abf5-387825a2434a', '3', '2024-05-08T12:35:10.362', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-5350f3e6-c2ac-4b50-8342-aca85a31bd39', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-63929fcc-e8f9-11ee-934c-fa163efa1f90', '3', '2024-04-17T22:01:38.166', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-53e69e9e-0c39-479a-a186-fe6e37682543', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-fb3c1d88-c81f-4775-9c96-684bc9dabc75', '3', '2025-08-21T15:20:34.962', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-543ffc7f-cd3b-4c1c-b351-f6037c2c3327', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-25cf1417-8726-4732-abd0-38908ba1d9b2', '3', '2025-01-06T06:12:35.925', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-54561180-9bdd-4b1c-abc1-5cebdf14eab0', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-954722d2-76cf-4e64-bdf8-f11ef7ceef23', '1', '2024-07-01T18:06:39.400', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-54a7497b-d171-4fcf-95a3-c27e9236fb2b', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-016c289e-75ae-4034-85db-94b1cff32233', '3', '2024-04-11T19:03:04.912', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-54c050f2-fa55-4ce4-8ab1-b258681f85a2', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-c0fc7d55-5459-433c-ad6a-593856295d51', '3', '2025-01-06T06:12:36.212', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-54fa1a8d-0e9f-4269-989d-572e7037de4b', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-679f830a-db5f-40d8-b210-fded905516ac', '2', '2024-05-07T12:53:20.587', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-550e2de7-1c63-4cef-9712-1ed46ee93c15', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-fe5bb833-fdd7-4416-81dd-f5b20107540f', '1', '2024-05-22T11:00:15.012', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-559b6d84-6ee6-4103-80df-2246e0908c99', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-38af2070-0ce6-4912-9ad3-dfd5959cdd53', '3', '2024-12-02T22:44:07.254', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-55befe67-ffe9-42c7-87cd-0a0f9b534d5d', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-eeed1520-d1a8-416c-b42b-a24fa74e0d16', '1', '2024-05-07T12:42:28.347', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-55ceed51-5a3e-480a-a985-ee664bcc1987', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-a9456c2b-c3f7-41b3-b2c4-1fd8e622dcc6', '3', '2024-05-14T14:09:50.625', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-55fbf270-d8d2-4ec2-83b6-4204c7adf7bb', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-679f830a-db5f-40d8-b210-fded905516ac', '3', '2024-05-07T12:53:20.752', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-560cb886-b954-4789-a760-6182464c3a58', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-097434fa-dbbc-4ee3-ac93-fed60c6e18ce', '3', '2024-04-11T18:37:18.929', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-563478a5-b247-4421-af27-e4914cbc0e71', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-534f4acb-9a7c-465a-af38-1c8dadfefe95', '3', '2024-07-10T11:12:28.774', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-56ff8a52-822c-42b1-9021-efe93e6b1254', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65', '1', '2024-03-27T22:12:01.557', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-574275ab-a158-455e-8f6e-8fba007e93f8', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-954722d2-76cf-4e64-bdf8-f11ef7ceef23', '3', '2024-07-01T18:06:39.508', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-577bc7e1-b0e2-4304-9d2b-8128fa845e2b', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-7fb353f6-a56a-4622-a79d-dab0c178febf', '3', '2024-02-06T21:16:20.695', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-57f509dd-94c3-4d7a-9214-8fb348f291ba', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-dc11e4e7-6e9f-4975-a6b7-5f97ba1c46d3', '3', '2024-05-08T12:36:21.304', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-580b81c8-1f12-494c-8e42-c1d842942d9b', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-cbb13f49-17eb-40fd-80f2-8b7fba973361', '3', '2024-05-07T13:01:31.743', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-5889c20e-777d-4d3d-a01c-742845889c87', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-fe8c5e35-ba92-4f2e-9d2f-f1c89a278ef9', '1', '2025-01-08T19:07:57.790', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-588ed559-eae5-4ec1-8353-fd650ee8015f', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-17a53f15-4a36-4450-abf5-387825a2434a', '3', '2025-01-06T06:10:14.686', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-58921e19-ae35-4b21-9df0-71f5ac2ef57e', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-c0fc7d55-5459-433c-ad6a-593856295d51', '3', '2024-05-07T18:53:09.952', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-595cda1a-0cc9-4ac7-8861-c6ba24017593', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-bbf33596-d4ed-41ce-a7d3-cfba75618c64', '3', '2024-04-11T18:46:18.058', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-5961cbfb-20d5-43d0-89fd-98c2cd4f121f', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-954722d2-76cf-4e64-bdf8-f11ef7ceef23', '3', '2024-07-01T18:06:39.590', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-59a0bdde-15d3-49d4-a211-4cf8c1ba634d', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-8d0cc23d-60b6-4092-b10a-915e0afcca55', '3', '2025-03-14T17:56:25.645', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-59c00ad2-f084-4c50-878b-5e5c676f6014', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-407dc22f-4e2a-4c52-8caa-eb667d0ec34c', '3', '2024-07-10T11:14:48.269', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-5a4134c4-6920-4ce1-aba2-601472f7de85', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-da5dc88e-3ff9-4ed8-a724-c15377d306a3', '3', '2024-05-08T12:31:46.062', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-5b07e7f6-ca71-47ed-806d-810f2ed1130a', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-cbb13f49-17eb-40fd-80f2-8b7fba973361', '3', '2024-05-07T13:01:31.619', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-5bd42c8f-6129-4865-862e-8ece2925f722', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-dc11e4e7-6e9f-4975-a6b7-5f97ba1c46d3', '3', '2024-05-08T12:36:21.318', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-5c5d09c8-d708-4322-a3ee-f43e651d769d', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-954722d2-76cf-4e64-bdf8-f11ef7ceef23', '3', '2025-01-06T06:12:36.157', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-5cd721e7-537c-4683-a14c-ccba74806446', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-38af2070-0ce6-4912-9ad3-dfd5959cdd53', '3', '2024-12-02T22:44:07.188', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-5ceb37ba-439a-4b1b-a63e-ac527efe2595', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-8e29b12a-b61e-4ec6-b7ea-abab46cf27f6', '3', '2025-01-06T06:12:36.139', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-5cec1e22-9956-4918-814b-3fb32c3c0c8e', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-6f8c6196-a29a-4b1d-89f4-9eec372e2c87', '3', '2024-05-07T12:41:46.352', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-5d08b63f-c8db-4e9e-99b5-47df9ed036d8', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-188ff31e-ff6b-47eb-a6d6-1ed0b703c0b5', '3', '2024-07-01T10:46:49.287', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-5da0fc94-bfed-416a-9cf4-a75586040e9e', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-3279ddd8-0024-4e37-b1f0-51fe30e57ff4', '3', '2025-01-06T06:10:14.709', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-5da7dbfa-4097-4641-b057-6c9cc512003b', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-731d1bc7-e47b-463e-bf26-5a113dee9716', '3', '2025-01-08T19:20:53.065', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-5dddd9a8-d214-4a2b-8a5b-a2b04e8f7ae9', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-3279ddd8-0024-4e37-b1f0-51fe30e57ff4', '3', '2024-04-11T19:00:08.741', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-5f879522-20a9-40f6-9c96-a3b5d18b88d0', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998', '3', '2024-02-06T21:23:40.495', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-5feac7db-746e-477a-baff-86de4fecb032', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-8d0cc23d-60b6-4092-b10a-915e0afcca55', '3', '2025-03-14T17:56:25.722', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-5ff14298-865a-438b-8117-ab1fca153749', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-c0220993-26e0-4d21-bc25-f612c67170c5', '3', '2024-05-05T22:25:00.557', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-5fff9419-9220-496a-992d-6513eb7613a0', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-bbf33596-d4ed-41ce-a7d3-cfba75618c64', '3', '2024-04-11T18:46:18.127', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6011b62a-a2af-4248-a635-34f552227977', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-38af2070-0ce6-4912-9ad3-dfd5959cdd53', '3', '2025-01-06T06:10:14.714', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-60498d91-a9ca-43d0-9291-8680df89ed3b', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-66382360-f89d-47fc-889a-61c720bdd826', '3', '2024-12-02T22:44:44.555', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-609ab373-8687-47ec-82f3-3b76f2a0ecff', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-da5dc88e-3ff9-4ed8-a724-c15377d306a3', '1', '2024-05-08T12:31:45.915', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-60c8e24a-0ee0-4cbe-865a-47e947ce0018', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-8e29b12a-b61e-4ec6-b7ea-abab46cf27f6', '3', '2024-07-10T11:13:50.166', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6114ac2b-c42b-4865-84a2-2d3926294e9d', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-d24188bd-5dc8-4e37-8bf4-0d355cada866', '3', '2024-05-07T18:32:36.095', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-612d9bde-63b4-4be0-8fe5-68aa42243bc3', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-0ee32ded-100b-4505-95c4-65d5e9b3d93c', '3', '2024-04-16T12:38:51.589', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-617ce46b-8879-4ded-9a4b-3ff7dd8270fd', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-c38a1e47-8271-43f2-aca3-bb7b2db33ad0', '1', '2024-05-21T13:41:44.570', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-621e4298-3336-4e53-a35a-9a07daee39a1', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-ecaa4072-d8ae-4f54-b681-6d42ca75db12', '3', '2025-01-06T06:10:15.025', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6222e076-6346-40bc-bb95-c6c62e1a1704', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-94bb8cdb-75e0-4561-8af5-7c1107f0426d', '3', '2025-05-19T12:23:33.517', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-627fd7b2-c417-4a8f-af0d-91243d334e56', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-bbf33596-d4ed-41ce-a7d3-cfba75618c64', '3', '2024-04-11T18:46:18.261', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6290fed0-2442-4b62-bb97-39f989e01962', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-155d2725-4be7-4e83-9ac0-88552a02023f', '3', '2024-02-06T21:22:24.443', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-62fa56d3-3d2c-43bc-aa95-50e64bfa0b54', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-5ae215f6-d749-459f-9a64-bb022cc1f6f9', '3', '2025-04-20T17:23:00.327', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-63db9520-bebc-4634-8c52-37590cbcfbe5', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-eeed1520-d1a8-416c-b42b-a24fa74e0d16', '3', '2024-05-07T12:42:28.448', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-63dd351b-3365-4ae8-9e5f-1ad5ac049d43', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-86317a13-8a2c-41fd-b1ad-051ca070a875', '1', '2024-02-06T21:21:31.983', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-64456137-9f8c-42ce-b4c2-e26d570e2a74', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-c03dadaf-4a89-42a1-8b36-847d34c8b337', '3', '2025-03-14T17:55:27.498', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-64e8693d-4287-4dc1-b692-470f84934bc3', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-6226ed9a-1517-471a-8960-c6f23e7fb0e4', '3', '2025-05-07T18:11:38.717', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-64fdbfb1-6be6-4aee-9831-0452b5f0146f', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', '3', '2024-02-06T21:14:03.767', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-655e5784-dd15-41b0-97d2-d953b778ec1e', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-f844e11c-d447-48c3-87bf-1202492b8b03', '3', '2024-09-22T23:05:39.475', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-658f0c9a-cb1d-49c2-82c5-66bedda66144', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-423e72b1-72ea-4dbb-99f4-e72772bafc3c', '3', '2024-04-25T19:34:39.010', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-65aefd07-ab71-4834-b71f-69f5f6c222e0', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-82b00b86-cba2-43de-9904-f913eed8a570', '3', '2024-11-18T12:32:29.391', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6600574c-10c3-433a-8570-639da2f91544', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-fb3c1d88-c81f-4775-9c96-684bc9dabc75', '3', '2025-08-21T15:20:34.917', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-660ca07b-7c29-46f9-acc8-4215d0767121', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-50b321e8-2886-4b99-a921-cbb5baacf1ac', '3', '2024-07-06T11:53:35.027', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-66308bb0-2d9b-4097-8f90-f74218441321', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-954722d2-76cf-4e64-bdf8-f11ef7ceef23', '1', '2024-07-01T18:06:39.528', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6636b911-d3d2-40e2-9cca-b0ecd681eb45', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-dc11e4e7-6e9f-4975-a6b7-5f97ba1c46d3', '3', '2024-05-08T12:36:21.226', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-66375cec-6813-46c4-8b50-0ee9bf704abd', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-fb3c1d88-c81f-4775-9c96-684bc9dabc75', '3', '2025-08-21T15:20:34.777', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-66383310-7b2b-4eda-a91c-4e363eb3b9e1', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-731d1bc7-e47b-463e-bf26-5a113dee9716', '1', '2025-01-08T19:20:53.046', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-663ff148-2031-42c6-9e0b-f3b8472c414f', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-4d66eeda-f440-4994-8998-bc2fcaba3c16', '3', '2025-01-06T06:12:36.006', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-664f879d-5436-4337-a497-6a3940524ec3', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-b64043df-c9f1-43a2-b96b-a6134b2953e1', '3', '2024-05-07T18:56:13.907', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-66d5ac18-69ea-4164-a16e-30d8e55c698b', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-439363cf-9c16-4b9e-8840-64bb093cbbd3', '1', '2024-06-24T16:40:08.486', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-66f15a0a-0fbc-4b1f-9082-5083c36291b6', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-4d66eeda-f440-4994-8998-bc2fcaba3c16', '3', '2024-05-08T12:35:43.749', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-674b5274-2628-4526-968f-b26d5c73ab65', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-534f4acb-9a7c-465a-af38-1c8dadfefe95', '3', '2025-01-06T06:12:36.016', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6756121e-3d5c-4822-be47-01ed73db9da3', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3', '3', '2024-03-28T13:54:08.712', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6759753d-f9a6-4888-8228-d63c2a3c2c67', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-b6a3917f-2f46-4d7a-b79d-dc5b91e963c5', '3', '2024-11-18T12:34:33.604', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-676a3312-8890-4941-93b2-cbb626c2c781', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-ecaa4072-d8ae-4f54-b681-6d42ca75db12', '3', '2024-05-07T18:57:15.033', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-67b3fcb8-bf1a-4369-a776-1b5a50aaf20b', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-b6a3917f-2f46-4d7a-b79d-dc5b91e963c5', '3', '2025-01-06T06:12:36.190', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-681654c5-cdd5-4e5c-a330-04c214cdf751', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-902ff997-6669-40bd-9f82-d8aeb297f39b', '3', '2024-07-08T15:41:24.600', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-689a2555-2163-4f29-9e74-94b2ce088686', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-534f4acb-9a7c-465a-af38-1c8dadfefe95', '3', '2024-07-10T11:12:28.879', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-689a2e24-b6db-4e8c-adc7-ea37b7f19025', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-097434fa-dbbc-4ee3-ac93-fed60c6e18ce', '3', '2024-04-11T18:37:18.451', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-689ca59f-aada-4522-a962-8149fc60ffe9', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-423e72b1-72ea-4dbb-99f4-e72772bafc3c', '3', '2024-04-25T19:34:38.923', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-68deed44-b62b-4abf-8b77-9d53458b269e', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-f844e11c-d447-48c3-87bf-1202492b8b03', '3', '2025-01-06T06:10:15.047', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6933351c-18ed-44ac-926a-3a370a3b9d85', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-50b321e8-2886-4b99-a921-cbb5baacf1ac', '3', '2024-07-06T11:53:34.911', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6951a399-8f52-4716-a927-74d25cc4018b', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-439363cf-9c16-4b9e-8840-64bb093cbbd3', '2', '2024-06-24T16:40:08.613', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-695c8a58-e346-4854-9060-5543997b5479', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-c38a1e47-8271-43f2-aca3-bb7b2db33ad0', '3', '2025-01-06T06:12:36.217', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6970c48b-cdf0-4054-a4c2-5cb4b7c26372', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-902ff997-6669-40bd-9f82-d8aeb297f39b', '3', '2024-07-08T15:41:24.692', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6a728775-9673-40d4-9824-15ea42af1356', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-5b87c7af-00e3-44b6-b6c5-0a9a77882864', '3', '2024-05-07T12:57:10.232', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6a8e7372-2e33-42ca-9ff5-0d731d756687', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-b1ebad5d-7fa4-4872-ba1f-13df498414c6', '3', '2024-05-07T18:54:39.879', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6a9f35d1-90f9-421b-8354-f6d81a42ad4b', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-63929fcc-e8f9-11ee-934c-fa163efa1f90', '3', '2024-04-17T22:01:43.224', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6a9fd7a9-eecd-4335-aa82-c1bbf71b9865', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-fb3c1d88-c81f-4775-9c96-684bc9dabc75', '3', '2025-08-21T15:20:34.796', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6aa6e4f8-1f10-41c6-b9a9-9f6dfb56a8c1', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-50b321e8-2886-4b99-a921-cbb5baacf1ac', '3', '2024-07-06T11:53:34.866', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6aa96e11-8440-4af8-a0d1-664c095ad239', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-8462eabd-4a2d-4b79-8a73-99ecd64f3d40', '3', '2024-05-14T21:17:01.344', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6ac36f44-a94b-440e-891d-4e8377b790db', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-2eb9fc11-cba7-48e1-9af3-f22f8fcae18c', '3', '2024-07-01T10:30:20.435', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6b37cc7b-b454-41ff-ae85-e1fa47d40437', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-e1a6e9a2-8a97-417b-bc5d-ad936b0ac41f', '3', '2025-01-15T21:21:36.619', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6b449958-99d9-43a7-8673-388668be4e80', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3', '3', '2024-02-06T21:18:36.035', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6b62241d-6662-4ee7-9159-037de5d9d174', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-4d66eeda-f440-4994-8998-bc2fcaba3c16', '3', '2024-05-08T12:35:43.677', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6b92fa62-c081-4fbb-8b34-ee1feac32ca4', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-17a53f15-4a36-4450-abf5-387825a2434a', '3', '2025-01-06T06:12:35.897', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6bc19c1d-d4c6-4a51-ac5c-6c36fd2218af', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-6f8c6196-a29a-4b1d-89f4-9eec372e2c87', '3', '2025-01-06T06:10:14.815', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6c7d4f11-4f7e-4d36-9b68-f095e61b038b', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-6d459f8b-86da-44ba-a90d-ee644c046bda', '3', '2025-05-18T21:06:24.604', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6cc3ec9b-e45a-4362-9e3f-832a77f76cf7', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-731d1bc7-e47b-463e-bf26-5a113dee9716', '3', '2025-01-08T19:20:52.958', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6d3925c0-81a9-43ff-80d9-f79e7aa7f9d1', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-8462eabd-4a2d-4b79-8a73-99ecd64f3d40', '3', '2024-05-14T21:17:01.329', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6d541109-d13d-4206-af7f-ccb328d154a1', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-86317a13-8a2c-41fd-b1ad-051ca070a875', '3', '2024-03-28T13:54:08.920', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6d86eb48-a0af-4e87-a774-7890a3efe37b', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-94bb8cdb-75e0-4561-8af5-7c1107f0426d', '3', '2025-05-19T12:23:33.479', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6e4d5b65-f504-4807-91a8-ef5667f3aff7', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-a9278b5d-f2ba-4429-854f-4662000f50e6', '1', '2024-05-05T22:27:17.918', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6e674dc3-4b3b-4bb2-9038-c2294cb080f6', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65', '1', '2024-03-27T22:12:01.479', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6e87d1a2-555f-49ae-bf04-e4605d680cce', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-8462eabd-4a2d-4b79-8a73-99ecd64f3d40', '3', '2024-05-14T21:17:01.285', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6eb873b7-1fa7-4479-bbde-adea1402030e', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-6226ed9a-1517-471a-8960-c6f23e7fb0e4', '3', '2025-05-07T18:11:38.850', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6ebb9fb1-d9a2-46dc-84fd-278dc9c39711', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-188ff31e-ff6b-47eb-a6d6-1ed0b703c0b5', '3', '2024-07-01T10:46:49.266', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6ecbe226-d39f-4c8d-81b8-b1e00f50595e', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', '1', '2024-02-06T21:09:59.556', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6f3ee83b-8c2a-42b2-9e5c-a62e85e323ed', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-b1ebad5d-7fa4-4872-ba1f-13df498414c6', '3', '2025-01-06T06:10:14.924', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6fc40801-e53f-46e6-8c2f-00d5551f00d8', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-15731dcc-f552-4160-b5c5-99a9112ae341', '3', '2025-01-06T06:12:35.877', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6fedeff4-1469-4e3a-b7a6-d2ee8e7b7c70', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-f47ac10b-58cc-4372-a567-0e02b2c3d479', '3', '2024-04-17T22:01:40.124', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7083c849-17c0-4046-aa6b-be68bf1fee1d', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-087a4eac-7036-4074-a813-831efa3d6950', '3', '2025-01-06T06:12:35.814', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7105a5c7-b154-4d63-a0d0-0340539e48f5', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-50b321e8-2886-4b99-a921-cbb5baacf1ac', '3', '2024-07-06T11:53:35.004', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-71292f0a-821d-48bb-acb7-ce143a45d10b', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-dc11e4e7-6e9f-4975-a6b7-5f97ba1c46d3', '3', '2024-05-08T12:36:21.333', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7134aaff-0fa3-4ffb-bb2c-a7b59d480ee1', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-188ff31e-ff6b-47eb-a6d6-1ed0b703c0b5', '3', '2024-07-01T10:46:49.450', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-71574356-51c7-475e-ade4-2caa76bf2287', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-c0220993-26e0-4d21-bc25-f612c67170c5', '1', '2024-05-05T22:25:00.511', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-71775413-a214-453d-992e-66b1491228c5', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-38af2070-0ce6-4912-9ad3-dfd5959cdd53', '3', '2024-12-02T22:44:07.160', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-71958a5a-5e74-4819-a0a9-8109651269c7', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-6d459f8b-86da-44ba-a90d-ee644c046bda', '3', '2025-05-18T21:06:24.626', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-72acb886-e395-4e66-a8e9-ff11b711682d', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-fe8c5e35-ba92-4f2e-9d2f-f1c89a278ef9', '3', '2025-01-08T19:07:57.688', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-72e89b00-d6bc-460c-9ecf-9daee42fd677', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-b6a3917f-2f46-4d7a-b79d-dc5b91e963c5', '3', '2025-01-06T06:10:14.940', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7301122b-f59c-4dd0-ba09-1cf1b795d614', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-86317a13-8a2c-41fd-b1ad-051ca070a875', '3', '2024-02-06T21:21:31.524', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-731a767d-c357-40f2-bec5-3774bc3a9a14', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-8e29b12a-b61e-4ec6-b7ea-abab46cf27f6', '3', '2024-07-10T11:13:50.186', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-741a2ab8-a488-4e60-b9e9-ab37dfd3b04a', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35', '1', '2024-04-16T12:34:36.560', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-74508dec-0c70-44b8-b2a8-fc9003729114', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-5b87c7af-00e3-44b6-b6c5-0a9a77882864', '3', '2024-05-07T12:57:10.296', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-74b4520f-f788-42ab-ae1f-a22529b5f218', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-c0fc7d55-5459-433c-ad6a-593856295d51', '3', '2024-05-07T18:53:09.352', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-74df557a-09e3-4957-8bb2-0b52fc601b2a', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-e7149e74-4856-4440-8d94-99f915731842', '3', '2024-09-04T16:42:57.534', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7534a905-0c50-4407-b64b-4d3e58f2a3f9', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-bbf33596-d4ed-41ce-a7d3-cfba75618c64', '3', '2024-04-11T18:46:18.683', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-755632d1-2070-41c7-8b64-48bbb8d6952f', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-731d1bc7-e47b-463e-bf26-5a113dee9716', '3', '2025-01-08T19:20:52.928', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-75bca346-0c5c-4553-8b81-1a8400d277ef', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-6d018c56-58b2-4277-a258-28707ae35cc7', '3', '2024-11-18T12:36:19.547', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-75fb0b85-8c10-4169-b418-182affd3f11f', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-c0fc7d55-5459-433c-ad6a-593856295d51', '3', '2024-05-07T18:53:09.565', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-76125221-fc7b-44de-a9f4-2c1bab9f849d', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3', '3', '2024-02-06T21:18:36.475', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-765d9245-e5ee-4a92-8f9a-18ec55ef0831', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-097434fa-dbbc-4ee3-ac93-fed60c6e18ce', '3', '2024-04-11T18:37:18.861', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7784eea2-b0cc-42e1-9915-f32fc67bc7b5', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-da5dc88e-3ff9-4ed8-a724-c15377d306a3', '3', '2024-05-08T12:31:46', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-778feadb-9c60-49dc-9313-35e08171c9c8', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65', '1', '2024-03-27T22:12:01.964', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-77ce556b-f626-461f-a460-0752f2c02b2f', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-6d018c56-58b2-4277-a258-28707ae35cc7', '3', '2024-11-18T12:36:19.454', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-78449c85-378b-4843-a5ed-19e82b2214a2', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-3279ddd8-0024-4e37-b1f0-51fe30e57ff4', '3', '2024-04-11T19:00:08.809', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-78c4429a-456a-4c0e-8b43-bbae4907d4c5', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-08310c14-214f-47e0-b973-fc9043e4486d', '1', '2025-02-10T12:35:49.989', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7915e5a5-62c2-4347-9f5d-9fd78f584030', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-0ee32ded-100b-4505-95c4-65d5e9b3d93c', '3', '2024-04-16T12:38:51.655', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-799fc823-3ca2-4f04-9417-2989c8090347', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-5ae215f6-d749-459f-9a64-bb022cc1f6f9', '3', '2025-04-20T17:23:00.121', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7adcbf35-3f93-4141-8c92-94247c5f3a57', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-da5dc88e-3ff9-4ed8-a724-c15377d306a3', '1', '2024-05-08T12:31:46.031', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7ae813bb-269c-43bf-84cb-1518f463e935', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-58da609f-67ca-4ea4-acea-d1c5fb7ec20d', '2', '2024-07-01T10:40:04.699', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7af48759-47c7-4eb8-97ae-a21b563af824', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-eabd4d64-15df-485c-96b6-d6e9b0792c4f', '1', '2024-11-18T12:35:25.200', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7b2db14b-7d3e-4ff0-9689-43f703fbb154', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-50b321e8-2886-4b99-a921-cbb5baacf1ac', '3', '2024-07-06T11:53:34.819', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7bb0d1d2-0796-4c4a-bc08-edbb1ea2b7b7', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-b1ebad5d-7fa4-4872-ba1f-13df498414c6', '3', '2024-05-07T18:54:40.514', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7bf0a5e8-1240-42b2-8b80-42e1348495f4', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-5b87c7af-00e3-44b6-b6c5-0a9a77882864', '1', '2024-05-07T12:57:10.328', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7c1d5f7f-3685-4e81-ad52-829b63fcbb07', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-c0fc7d55-5459-433c-ad6a-593856295d51', '1', '2024-05-07T18:53:09.275', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7c4ef6df-e01c-4293-bae4-e3dc3bb129e2', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-82b00b86-cba2-43de-9904-f913eed8a570', '3', '2024-11-18T12:32:29.498', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7c92fe14-0c9c-4eb0-99cd-25de8489239f', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-0ee32ded-100b-4505-95c4-65d5e9b3d93c', '1', '2024-04-16T12:38:51.795', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7ce4822d-3665-4804-aa04-8e9dec8b8590', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-58da609f-67ca-4ea4-acea-d1c5fb7ec20d', '3', '2024-07-01T10:40:04.567', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7d0222eb-bb8b-4c09-b43d-4d43c0d623b6', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3', '3', '2024-02-06T21:18:35.976', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7d1bba98-ca4f-40bb-8b2c-c4a3d167f16d', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-8e29b12a-b61e-4ec6-b7ea-abab46cf27f6', '3', '2024-07-10T11:13:50.126', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7d6829df-99e7-4990-a97f-09e89dd0a6c0', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-7c63297e-ec51-49cb-ab95-2c33229455b4', '3', '2025-01-06T06:12:36.083', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7d68e8de-3d4a-4037-bebb-84adc6d1ed69', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-6c7208c0-bbc3-4d8d-a19b-6ca9d6b269fb', '3', '2025-05-18T21:16:36.556', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7db7d947-5c7c-415b-9ade-033813d82588', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-7fb353f6-a56a-4622-a79d-dab0c178febf', '3', '2024-02-06T21:16:20.233', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7deb57b5-fc17-4bde-acf8-7d4b7d203860', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-423e72b1-72ea-4dbb-99f4-e72772bafc3c', '3', '2025-01-06T06:10:14.725', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7e567520-6790-45a3-aa2e-3d69c957817c', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-38af2070-0ce6-4912-9ad3-dfd5959cdd53', '3', '2024-12-02T22:44:07.141', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7e896707-ed77-4aef-b97e-dc3fc7c97ccd', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-f844e11c-d447-48c3-87bf-1202492b8b03', '3', '2024-09-22T23:05:39.748', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7ed8982d-fcce-4996-882a-4857f23df97e', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-dbfbbf3e-59ce-4076-bbed-2fa699999686', '3', '2024-05-07T12:44:49.404', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7f5e47c9-09df-4b07-adf2-246508055b0a', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-cbb13f49-17eb-40fd-80f2-8b7fba973361', '3', '2024-05-07T13:01:31.661', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7f84b3c9-2361-4797-bc9a-cbb98e6c48c4', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-6d018c56-58b2-4277-a258-28707ae35cc7', '2', '2024-11-18T12:36:19.369', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7f8503e5-e341-4264-8ed8-6db998800c20', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-6f8c6196-a29a-4b1d-89f4-9eec372e2c87', '3', '2024-05-07T12:41:46.299', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7fa57b38-b11a-4aa7-8e5a-7b461e7a7a46', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-bf697261-39c2-408c-b11a-b5a60ec89994', '3', '2025-01-06T06:10:14.951', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-801b750b-a4ca-4e1e-b860-1036677f3d3c', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3', '3', '2024-02-06T21:18:35.857', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8061568b-63a7-4434-895f-53b5b5d357a7', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-66382360-f89d-47fc-889a-61c720bdd826', '3', '2024-12-02T22:44:44.478', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-806898c1-5bac-40ab-a0de-f9c2d31914f6', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-c38a1e47-8271-43f2-aca3-bb7b2db33ad0', '3', '2024-05-21T13:41:44.626', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8129d518-c20a-4d3f-b0d0-b6f020bb1361', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-6c7208c0-bbc3-4d8d-a19b-6ca9d6b269fb', '3', '2025-05-18T21:16:36.481', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-814b8d85-fd05-4cfa-a46c-25493aa641ec', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-d24188bd-5dc8-4e37-8bf4-0d355cada866', '3', '2024-05-07T18:32:36.169', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-815c4d34-4fe6-4697-af9b-7e163cff429c', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-3279ddd8-0024-4e37-b1f0-51fe30e57ff4', '3', '2024-04-11T19:00:09.081', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8178ce5b-c206-4be1-aa28-d56c491e3387', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-097434fa-dbbc-4ee3-ac93-fed60c6e18ce', '3', '2024-04-11T18:37:18.587', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-81c5daa9-236c-40b8-bae7-171418ed3b97', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-25cf1417-8726-4732-abd0-38908ba1d9b2', '3', '2024-05-07T14:30:55.275', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-81db32d4-503a-4a50-ae39-4a48edf82f72', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-25cf1417-8726-4732-abd0-38908ba1d9b2', '3', '2024-05-07T14:30:55.294', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-82726254-4c81-4d28-923d-dbd01ef0d260', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-6d459f8b-86da-44ba-a90d-ee644c046bda', '3', '2025-05-18T21:06:24.646', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-82f1d900-ee41-4826-abb6-c3764451e381', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-ce704fab-6961-4091-a671-ce8d9e6b6549', '3', '2024-12-02T22:43:18.111', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-830c64bc-24b7-43f7-92f0-3c568f44978a', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-8d0cc23d-60b6-4092-b10a-915e0afcca55', '3', '2025-03-14T17:56:25.590', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8316b3ae-18cf-4392-9ffa-643a8bf7a2ff', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-5d318e20-3c58-4944-b7ac-c29e26df5ac0', '3', '2024-05-07T14:26:42.532', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8331379b-b936-44f8-a237-3ac10a41c979', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', '1', '2024-02-06T21:10:00.147', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8394a609-8646-4220-a782-ebbbe8ce5ff9', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-534f4acb-9a7c-465a-af38-1c8dadfefe95', '3', '2024-07-10T11:12:28.711', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-83c87a50-defa-4647-8879-8db58965c63a', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-dc11e4e7-6e9f-4975-a6b7-5f97ba1c46d3', '1', '2024-05-08T12:36:21.287', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-83e023c3-b514-4426-8df8-0efc1d459482', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-5b87c7af-00e3-44b6-b6c5-0a9a77882864', '3', '2024-05-07T12:57:10.156', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-83f01da9-006f-4818-980e-4d184e371210', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-86816d21-ec0c-4dc6-ad1d-8edea9716d09', '3', '2024-05-07T18:51:17.153', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8405185c-6dfb-4fcd-b1ca-9d46ad5a5b27', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-e1a6e9a2-8a97-417b-bc5d-ad936b0ac41f', '3', '2025-01-15T21:21:36.397', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-84283409-0900-4c97-ac4c-90225aa86f0d', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-6d459f8b-86da-44ba-a90d-ee644c046bda', '3', '2025-05-18T21:06:24.792', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8476f2eb-07b4-42fe-9d38-07f655cd6132', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-bf697261-39c2-408c-b11a-b5a60ec89994', '3', '2024-07-08T15:40:51.075', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-847a1a1e-c253-4423-a8be-732293858cd0', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-731d1bc7-e47b-463e-bf26-5a113dee9716', '3', '2025-01-08T19:20:53.031', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8480b553-52a6-40a6-aa41-2469195afc76', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-63929fcc-e8f9-11ee-934c-fa163efa1f90', '3', '2025-01-06T06:10:14.786', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-855fe39e-b66a-4f52-8f78-fe0b65fca654', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-d24188bd-5dc8-4e37-8bf4-0d355cada866', '1', '2024-05-07T18:32:36.235', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-85b30586-c645-455c-82a6-5e6a81f7b498', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-4d66eeda-f440-4994-8998-bc2fcaba3c16', '3', '2025-01-06T06:10:14.736', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-85e70f43-3492-47d1-bea1-5621b6d69691', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-5d318e20-3c58-4944-b7ac-c29e26df5ac0', '3', '2024-05-07T14:26:42.511', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-85ee49dc-3de4-48ce-8f19-f292b6e31ad0', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-7c63297e-ec51-49cb-ab95-2c33229455b4', '3', '2024-07-10T11:02:07.651', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8666e56a-c6d2-4d80-a4ca-6780e405f192', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-5d318e20-3c58-4944-b7ac-c29e26df5ac0', '3', '2024-05-07T14:26:42.469', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-86ba4f5e-05e3-4e91-b1c4-3f0f1706cc43', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-679f830a-db5f-40d8-b210-fded905516ac', '3', '2025-01-06T06:10:14.797', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-86bb0b4d-bc6c-4c83-832d-a6d07a9ee4a8', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-f78d8a42-fd40-4643-a737-cb85234737d6', '3', '2025-01-06T06:10:15.043', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-86f23a32-923f-4419-8d9d-2b9b01b8d60e', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-087a4eac-7036-4074-a813-831efa3d6950', '3', '2024-05-08T12:36:58.741', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-871b6c50-2935-48d4-b8d7-564f053fc9de', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-e7149e74-4856-4440-8d94-99f915731842', '3', '2024-09-04T16:42:57.518', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-883000e4-7094-4baf-956a-a3d69a80ceb3', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-a9456c2b-c3f7-41b3-b2c4-1fd8e622dcc6', '3', '2025-01-06T06:10:14.918', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-883b1f08-2f2b-428a-9870-7530f0a670cb', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-15731dcc-f552-4160-b5c5-99a9112ae341', '3', '2024-12-17T11:41:15.657', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-88493379-19a4-4870-9aaa-10c661e76a8e', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-6d018c56-58b2-4277-a258-28707ae35cc7', '3', '2024-11-18T12:36:19.435', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-88828cb9-d7cf-49de-abbb-247c37d3eeea', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998', '1', '2024-02-06T21:23:40.626', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-89246490-2733-45cf-ab64-ff4f10fa65c5', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998', '3', '2024-02-06T21:23:39.965', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8954cf6b-11c1-4831-b176-b974b568d821', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-08310c14-214f-47e0-b973-fc9043e4486d', '3', '2025-02-10T12:35:49.813', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-89afb1f0-1c6e-4138-a71b-112063ed2142', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', '1', '2024-02-06T21:10:00.543', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8a4966ae-e587-4f84-a09f-95f9a24f1130', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-5b87c7af-00e3-44b6-b6c5-0a9a77882864', '3', '2024-05-07T12:57:10.263', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8a7cc44d-bfba-420d-8e49-abffef0496a6', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-66382360-f89d-47fc-889a-61c720bdd826', '3', '2025-01-06T06:12:36.057', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8a96c876-b43a-44ba-953c-4f4ecc6d642c', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-8e29b12a-b61e-4ec6-b7ea-abab46cf27f6', '3', '2024-07-10T11:13:50.147', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8b0ee182-4034-48fe-a78c-e28752b05035', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', '3', '2025-01-06T06:10:14.875', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8b1343e4-c7d3-43ca-a845-4bc98b123039', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-097434fa-dbbc-4ee3-ac93-fed60c6e18ce', '3', '2024-04-11T18:37:18.381', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8b236114-29d0-4f6d-98ae-e2972cc1663f', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-fb3c1d88-c81f-4775-9c96-684bc9dabc75', '3', '2025-08-21T15:20:34.857', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8b4aa456-3db7-487b-b03d-cde7018a3cc4', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-097434fa-dbbc-4ee3-ac93-fed60c6e18ce', '3', '2024-04-11T18:37:18.238', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8b9f0559-4959-47f8-a8c7-7cbe9dab9fa7', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-cbb13f49-17eb-40fd-80f2-8b7fba973361', '3', '2024-05-07T13:01:31.765', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8be037f0-fa3a-4201-b7d3-4b23caa00cb8', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-dbfbbf3e-59ce-4076-bbed-2fa699999686', '3', '2024-05-07T12:44:49.380', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8bff4643-8c34-4419-bda5-db837064ebea', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-6c7208c0-bbc3-4d8d-a19b-6ca9d6b269fb', '3', '2025-05-18T21:16:36.538', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8c101ea9-6c6b-419d-a1a3-88290acc0638', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-c0220993-26e0-4d21-bc25-f612c67170c5', '3', '2024-05-05T22:25:00.412', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8c8fbdd2-9e65-4b5e-b1ae-f86344bbd491', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-86816d21-ec0c-4dc6-ad1d-8edea9716d09', '3', '2024-05-07T18:51:17.318', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8ca5a055-486f-4329-8da6-f3eb910f2b0d', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-58da609f-67ca-4ea4-acea-d1c5fb7ec20d', '1', '2025-01-06T06:12:36.031', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8cb54170-a667-47e5-bb3d-8497ce7f4428', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-a9278b5d-f2ba-4429-854f-4662000f50e6', '3', '2024-05-05T22:27:17.987', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8cbf6e0e-e68a-4931-a7af-e3c66ecc3689', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65', '1', '2024-03-27T22:12:01.402', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8d0552aa-acda-4f2f-9e85-d50fab1d516c', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', '3', '2024-02-06T21:09:59.492', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8d0870c2-e1ed-4626-847f-9c58c1b2d345', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-58da609f-67ca-4ea4-acea-d1c5fb7ec20d', '3', '2024-07-01T10:40:04.519', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8d0ebe44-abf1-4120-80c5-d08bf586c77e', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-534f4acb-9a7c-465a-af38-1c8dadfefe95', '3', '2025-01-06T06:10:14.748', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8d232c06-0969-43f8-bb45-bca6f4ec3776', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-534f4acb-9a7c-465a-af38-1c8dadfefe95', '3', '2024-07-10T11:12:28.795', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8d255b9c-c5cf-4768-ac12-460b9848258e', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-a9456c2b-c3f7-41b3-b2c4-1fd8e622dcc6', '3', '2024-05-14T14:09:50.548', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8d283eb5-b7b2-4a83-938b-a1d1e6a9d6aa', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-e7149e74-4856-4440-8d94-99f915731842', '3', '2024-09-04T16:42:57.449', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8d297eda-db5a-4a53-a00d-217568fc07ad', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-6f8c6196-a29a-4b1d-89f4-9eec372e2c87', '3', '2024-05-07T12:41:46.151', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8de64266-8fa2-48e3-90c9-6eda35c952ed', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-bf697261-39c2-408c-b11a-b5a60ec89994', '3', '2024-07-08T15:40:51.054', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8dfe2e71-db8d-466c-b4e4-9a617228e166', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-eabd4d64-15df-485c-96b6-d6e9b0792c4f', '2', '2024-11-18T12:35:25.073', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8dfe337d-4395-41bb-86bf-9a7f0d514837', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-016c289e-75ae-4034-85db-94b1cff32233', '3', '2025-01-06T06:10:14.635', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8e6212de-f43f-4c75-84bd-65adaa9a13ef', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-6d459f8b-86da-44ba-a90d-ee644c046bda', '3', '2025-05-18T21:06:24.552', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8ec826dd-20d8-4e6a-bd44-33e3883226b2', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-66382360-f89d-47fc-889a-61c720bdd826', '3', '2025-01-06T06:10:14.792', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8eca472d-6260-467f-81bc-2e59044b876e', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-86317a13-8a2c-41fd-b1ad-051ca070a875', '3', '2024-02-06T21:21:31.584', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8ece97a5-8235-40bc-a065-ec233400d582', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-551fd76e-0890-4566-a3d0-dfa2215f3e72', '3', '2024-05-07T12:59:32.014', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8f229a64-be6f-4fd1-8c01-36fb1b98601b', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-d24188bd-5dc8-4e37-8bf4-0d355cada866', '3', '2024-05-07T18:32:35.960', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8f546a4d-409a-4e26-9c32-2c90b061ccb7', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-eabd4d64-15df-485c-96b6-d6e9b0792c4f', '3', '2025-01-06T06:10:15.020', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8fcf8ea4-4d5f-4b9f-af8d-91747dd0397e', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-e1a6e9a2-8a97-417b-bc5d-ad936b0ac41f', '3', '2025-01-15T21:21:36.500', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-90f73ea2-5043-442e-a7e2-1aa791f82ac2', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-5b87c7af-00e3-44b6-b6c5-0a9a77882864', '3', '2025-01-06T06:10:14.771', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-916ac19a-ef95-4fe1-9ebf-b084a08ecd04', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-17a53f15-4a36-4450-abf5-387825a2434a', '3', '2024-05-08T12:35:10.332', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-91a49212-a128-4020-b7f8-8166ff518f4f', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65', '1', '2024-03-27T22:12:01.809', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-91b16694-f525-4c94-a996-93dd9973058c', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-fb3c1d88-c81f-4775-9c96-684bc9dabc75', '3', '2025-08-21T15:20:34.898', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-91d22e02-63dd-487c-86f3-08dc4a3a4917', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-bf697261-39c2-408c-b11a-b5a60ec89994', '3', '2025-01-06T06:12:36.201', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-921f9d0e-9446-47b6-bd66-96f9c19dc26c', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-25cf1417-8726-4732-abd0-38908ba1d9b2', '1', '2024-05-07T14:30:55.222', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-923549e5-dad2-4bbe-90ce-9ab5a159cf55', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-f844e11c-d447-48c3-87bf-1202492b8b03', '3', '2024-09-22T23:05:40.036', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-924be02b-feca-4b2a-a00e-3b0a6aa204b1', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-17a53f15-4a36-4450-abf5-387825a2434a', '3', '2024-05-08T12:35:10.376', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-925b18dd-d962-4760-a54d-6fd83d99b402', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-bbf33596-d4ed-41ce-a7d3-cfba75618c64', '3', '2024-04-11T18:46:18.477', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9278c569-fe94-4237-b542-5cdc4430d34e', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-f5231573-bc14-4862-a77b-d4c09e31d136', '3', '2025-02-10T12:21:25.535', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-92d3798e-7228-470b-8f4d-12ad3e1ef9ec', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-bf697261-39c2-408c-b11a-b5a60ec89994', '1', '2024-07-08T15:40:51.136', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-933e122e-2fa2-4c81-871a-eddd7cc146e9', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-439363cf-9c16-4b9e-8840-64bb093cbbd3', '3', '2024-06-24T16:40:08.504', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-939347c1-c10b-455f-974e-47ddd9eed676', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-f5231573-bc14-4862-a77b-d4c09e31d136', '3', '2025-02-10T12:21:25.771', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-93e68929-91b4-4695-875a-92c406b84856', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-087a4eac-7036-4074-a813-831efa3d6950', '3', '2024-05-08T12:36:58.771', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9401d6a4-53ba-45c5-b06b-62e4e2a842ec', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-b64043df-c9f1-43a2-b96b-a6134b2953e1', '3', '2024-05-07T18:56:13.575', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-94e49b4c-7b13-4fcb-96f9-f0604f52d882', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-bf697261-39c2-408c-b11a-b5a60ec89994', '3', '2024-07-08T15:40:51.177', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-94ede99e-e472-4f75-b5b8-235718e75207', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', '3', '2024-02-06T21:14:03.436', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9502632b-65e0-4c29-a613-838829faca5c', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-f78d8a42-fd40-4643-a737-cb85234737d6', '3', '2024-05-21T13:46:28.016', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-95053b62-fe60-46be-a399-9c8786dcb516', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-f78d8a42-fd40-4643-a737-cb85234737d6', '3', '2024-05-21T13:46:28.104', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-958ddf61-8ffb-414e-b5ab-3d8f11b11990', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-6f8c6196-a29a-4b1d-89f4-9eec372e2c87', '1', '2024-05-07T12:41:46.273', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-967395de-c260-48a2-bb12-e5976e75a659', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-902ff997-6669-40bd-9f82-d8aeb297f39b', '3', '2024-07-08T15:41:24.751', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-967ece06-c084-4cf3-a341-b9a539cb92c2', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-910116aa-e8f8-11ee-934c-fa163efa1f90', '3', '2024-04-17T22:01:39.991', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9747368d-07fd-4011-b8d8-bc41e10db551', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', '3', '2024-02-06T21:14:03.261', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9769edb3-cc9a-4a61-9c36-e53c82ba4dca', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-82b00b86-cba2-43de-9904-f913eed8a570', '3', '2024-11-18T12:32:29.479', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-97e3864b-64c7-43f9-9cd2-87dc29b6bf7d', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-5b87c7af-00e3-44b6-b6c5-0a9a77882864', '3', '2025-01-06T06:12:36.041', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-981affc9-4820-45f2-b111-942453b7699c', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-63929fcc-e8f9-11ee-934c-fa163efa1f90', '3', '2025-01-06T06:12:36.053', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-982b7b24-752f-4eb0-8bc5-926997582ed4', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-66382360-f89d-47fc-889a-61c720bdd826', '2', '2024-12-02T22:44:44.439', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9855bdf5-f211-4cb9-aaa1-8dac51c8424f', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-86317a13-8a2c-41fd-b1ad-051ca070a875', '3', '2025-01-06T06:12:36.117', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-98688e87-6984-4192-959c-89f98e220fee', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-b64043df-c9f1-43a2-b96b-a6134b2953e1', '3', '2025-01-06T06:10:14.934', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-98f33764-1ae6-4dc5-95dc-cdc292f07a56', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-f78d8a42-fd40-4643-a737-cb85234737d6', '1', '2024-05-21T13:46:28.066', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-997efb8e-a79f-4ec7-ac03-4ac10ccfbbfa', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-eeed1520-d1a8-416c-b42b-a24fa74e0d16', '3', '2024-05-07T12:42:28.525', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-998cb8c0-74ee-47c5-9e0f-4c46a9973301', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-eeed1520-d1a8-416c-b42b-a24fa74e0d16', '3', '2024-05-07T12:42:28.424', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9a6b5045-07b8-400b-88f3-c9d47e7c1554', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998', '3', '2024-02-06T21:23:40.560', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9a82d895-c8cc-45e0-9412-c0e35840ab97', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-8daa7ebc-0b27-4236-b4d6-2e872f771f3a', '3', '2024-05-07T18:45:01.383', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9a85a4c7-4d28-4741-a244-5db6f4d8aec0', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-6c7208c0-bbc3-4d8d-a19b-6ca9d6b269fb', '3', '2025-05-18T21:16:36.459', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9aee1210-4254-4352-922d-b1da2ef08667', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-fe5bb833-fdd7-4416-81dd-f5b20107540f', '3', '2025-01-06T06:12:36.303', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9b6c4a9a-9c92-4b95-891c-aa69a247d55d', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-6226ed9a-1517-471a-8960-c6f23e7fb0e4', '3', '2025-05-07T18:11:38.910', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9bec1c97-1aeb-4967-9ba2-a63bab5bcd0e', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-86816d21-ec0c-4dc6-ad1d-8edea9716d09', '3', '2024-05-07T18:51:17.480', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9c3084be-4dc2-4601-8715-a0f060a2da8a', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-731d1bc7-e47b-463e-bf26-5a113dee9716', '3', '2025-01-08T19:20:52.988', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9c4e0f5c-dd56-4ce8-8382-38db1d18e2fe', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-679f830a-db5f-40d8-b210-fded905516ac', '3', '2024-05-07T12:53:20.733', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9c9e7231-77c8-4d8b-8955-e9221f6e71e5', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-3279ddd8-0024-4e37-b1f0-51fe30e57ff4', '3', '2024-04-11T19:00:08.947', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9cea6eb9-6113-4400-9f2e-90f22b4f81f7', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998', '3', '2024-02-06T21:23:40.029', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9cf72cfa-a027-407a-aad5-f90e389a5efe', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3', '3', '2024-02-06T21:18:36.536', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9d99c3fb-cc2e-4190-a51e-78fae38764e1', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998', '3', '2025-01-06T06:10:14.826', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9ddba73e-18a6-4c84-a7c1-eadc29bd79dd', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-66382360-f89d-47fc-889a-61c720bdd826', '3', '2024-12-02T22:44:44.511', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9de2d6cc-298a-4e10-9582-7307b04c265f', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-6226ed9a-1517-471a-8960-c6f23e7fb0e4', '3', '2025-05-07T18:11:38.870', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9e77c438-e153-427b-afba-207770c7bb80', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-ecaa4072-d8ae-4f54-b681-6d42ca75db12', '3', '2024-05-07T18:57:15.246', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9e8bfc77-91a1-441a-abc4-e7f556a18bbe', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-6d018c56-58b2-4277-a258-28707ae35cc7', '1', '2024-11-18T12:36:19.501', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9e9c5d37-d3fd-4097-b091-b67e62d3e7f5', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-6c7208c0-bbc3-4d8d-a19b-6ca9d6b269fb', '3', '2025-05-18T21:16:36.635', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9ee65009-0f83-4828-9c1c-a45c2b09ecd1', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-954722d2-76cf-4e64-bdf8-f11ef7ceef23', '3', '2024-07-01T18:06:39.465', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9f2c5e99-e8f9-11ee-934c-fa163efa1f90', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-63929fcc-e8f9-11ee-934c-fa163efa1f90', '1', '2024-03-23T21:14:04.291', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9f331084-87e8-457a-9529-264b3c07c8fa', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-439363cf-9c16-4b9e-8840-64bb093cbbd3', '3', '2025-01-06T06:12:36', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9f5d4695-4d5d-4617-bc29-734b0f2e7a7f', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-a9456c2b-c3f7-41b3-b2c4-1fd8e622dcc6', '1', '2024-05-14T14:09:50.607', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a012adae-e554-4abb-abca-7c22bab03442', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-2eb9fc11-cba7-48e1-9af3-f22f8fcae18c', '3', '2024-07-01T10:30:20.292', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a022026e-1dfb-42a5-83fc-f391e793c114', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35', '1', '2025-01-06T06:12:36.022', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a09442f1-d97f-4912-b82d-36c1391d91fd', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-6226ed9a-1517-471a-8960-c6f23e7fb0e4', '3', '2025-05-07T18:11:38.830', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a096fffd-9048-4056-bdb2-66485841f9d5', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-eabd4d64-15df-485c-96b6-d6e9b0792c4f', '3', '2024-11-18T12:35:25.154', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a09c9acd-c927-478e-9907-5aaf1af92dcf', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-e1a6e9a2-8a97-417b-bc5d-ad936b0ac41f', '3', '2025-01-15T21:21:36.419', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a0bbb7ee-ea43-4e5c-bf27-218476f6e479', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-902ff997-6669-40bd-9f82-d8aeb297f39b', '3', '2024-07-08T15:41:24.654', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a0c4959b-5053-49d4-9013-3c52ca083b35', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-6226ed9a-1517-471a-8960-c6f23e7fb0e4', '1', '2025-05-07T18:11:38.653', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a118c052-2b5e-4ced-a38c-567a9688e4ce', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-17a53f15-4a36-4450-abf5-387825a2434a', '3', '2024-05-08T12:35:10.347', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a173e729-8d7c-47ee-8de7-64daa419f1df', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-8d0cc23d-60b6-4092-b10a-915e0afcca55', '3', '2025-03-14T17:56:25.614', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a185ac38-3d32-4c21-a19c-9d2690d52f33', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-17a53f15-4a36-4450-abf5-387825a2434a', '3', '2024-05-08T12:35:10.287', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a1929e0a-6d27-4510-b3ce-b158c3e2b36e', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-423e72b1-72ea-4dbb-99f4-e72772bafc3c', '3', '2024-04-25T19:34:38.964', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a1e5075e-dba9-4ad3-a396-855ceb0bd908', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-c0220993-26e0-4d21-bc25-f612c67170c5', '3', '2025-01-06T06:12:36.207', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a1ef4f03-09cb-4540-b5c6-43eccdfbc0fc', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-da5dc88e-3ff9-4ed8-a724-c15377d306a3', '3', '2024-05-08T12:31:45.983', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a21d19fd-ee81-47ea-af23-a12fcac5b65e', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', '3', '2024-02-06T21:14:03.318', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a287ed57-f9bc-44f2-9ae9-89ae2cb9846a', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-097434fa-dbbc-4ee3-ac93-fed60c6e18ce', '3', '2025-01-06T06:10:14.657', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a2b43f10-e0a2-4282-bf75-9317830c8a5e', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-da5dc88e-3ff9-4ed8-a724-c15377d306a3', '3', '2024-05-08T12:31:46.015', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a2bcaad2-7f14-41e3-acf2-53f8312bf558', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-551fd76e-0890-4566-a3d0-dfa2215f3e72', '1', '2025-01-06T06:10:14.761', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a3914851-45db-41d7-b740-7c3d4c7d7fa6', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-0ee32ded-100b-4505-95c4-65d5e9b3d93c', '3', '2024-04-16T12:38:51.388', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a3cfb5f1-5fd5-4453-9eec-2256d093a139', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-38af2070-0ce6-4912-9ad3-dfd5959cdd53', '2', '2024-12-02T22:44:07.019', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a4240821-ff11-4020-9401-da4ecee54c6e', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-8daa7ebc-0b27-4236-b4d6-2e872f771f3a', '1', '2024-05-07T18:45:00.849', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a4b6f3b1-0222-4e84-9069-4999ae01ed22', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-a9278b5d-f2ba-4429-854f-4662000f50e6', '3', '2024-05-05T22:27:18.128', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a4c2640e-e487-450d-99fc-2b8df83171f2', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-66382360-f89d-47fc-889a-61c720bdd826', '3', '2024-12-02T22:44:44.593', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a4e2eebc-a249-4387-aec3-aaca3710f93b', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-2eb9fc11-cba7-48e1-9af3-f22f8fcae18c', '3', '2024-07-01T10:30:20.271', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a50ad1c5-7b96-4c34-a8bd-10a9f9cebb43', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', '1', '2024-02-06T21:09:59.621', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a6159ad1-c85d-4357-9cc5-25035617e13a', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-8462eabd-4a2d-4b79-8a73-99ecd64f3d40', '3', '2025-01-06T06:10:14.845', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a66930cb-2d46-4b65-a961-e0bba6c9e74f', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-5ae215f6-d749-459f-9a64-bb022cc1f6f9', '3', '2025-04-20T17:23:00.288', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a6f0c3c8-03a1-4434-ba29-9ed93b14cb91', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-4d66eeda-f440-4994-8998-bc2fcaba3c16', '3', '2024-05-08T12:35:43.649', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a702f968-44eb-451c-b8ac-1478f67096bc', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-82b00b86-cba2-43de-9904-f913eed8a570', '3', '2024-11-18T12:32:29.372', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a76a688d-baad-4a03-9d44-25a12e5a82f1', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-439363cf-9c16-4b9e-8840-64bb093cbbd3', '3', '2024-06-24T16:40:08.550', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a7c42a84-66f7-4097-9a1a-0cd503f20df9', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-7c63297e-ec51-49cb-ab95-2c33229455b4', '3', '2024-07-10T11:02:07.780', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a7e1ce94-03e9-4b5f-bc9a-862e020addb6', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-8d0cc23d-60b6-4092-b10a-915e0afcca55', '3', '2025-03-14T17:56:25.537', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a7ff23e9-f9e0-4530-924c-c4fab4d4a255', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-2eb9fc11-cba7-48e1-9af3-f22f8fcae18c', '3', '2025-01-06T06:10:14.704', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a84f3df4-e0d7-4d66-9c6b-32bb4917edb6', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-6226ed9a-1517-471a-8960-c6f23e7fb0e4', '3', '2025-05-07T18:11:38.813', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a85af6a8-e8f9-11ee-934c-fa163efa1f90', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-910116aa-e8f8-11ee-934c-fa163efa1f90', '1', '2024-03-23T21:14:04.291', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a87fef80-8b69-4a4b-88c5-3e85f31fb163', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-8e29b12a-b61e-4ec6-b7ea-abab46cf27f6', '3', '2024-07-10T11:13:50.080', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a8c6eb22-d8a2-45e3-9610-32cf793a3c38', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-f78d8a42-fd40-4643-a737-cb85234737d6', '3', '2024-05-21T13:46:27.979', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a9af304f-3f26-4618-b007-568930ae9d7a', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998', '3', '2024-02-06T21:23:40.094', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a9c7cd67-6e15-45b8-820e-3ccbf8077ba1', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-0ee32ded-100b-4505-95c4-65d5e9b3d93c', '3', '2025-01-06T06:12:35.849', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a9f9e0b1-0e12-4224-9d5a-3923e2a3c3af', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3', '3', '2024-02-06T21:18:36.415', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a9fb7617-b0d2-4bb4-87a6-a6138d545fa6', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-5b87c7af-00e3-44b6-b6c5-0a9a77882864', '3', '2024-05-07T12:57:10.356', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-aa06eb98-152f-4ff4-b64e-b6adcab6c17f', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-3279ddd8-0024-4e37-b1f0-51fe30e57ff4', '3', '2024-04-11T19:00:09.293', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-aa44d9f4-2098-4e7c-a701-6040e17ee806', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-25cf1417-8726-4732-abd0-38908ba1d9b2', '3', '2024-05-07T14:30:55.371', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-aabc6387-3716-43bb-8aa0-20cb8c6ddee6', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-5b87c7af-00e3-44b6-b6c5-0a9a77882864', '3', '2024-05-07T12:57:10.385', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-aace8faf-b41a-48ff-ad3a-7eb87ddcf325', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-407dc22f-4e2a-4c52-8caa-eb667d0ec34c', '3', '2025-01-06T06:10:14.719', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ab0c4e8e-ca18-4a23-bce4-d4344bfc84a6', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-eabd4d64-15df-485c-96b6-d6e9b0792c4f', '3', '2024-11-18T12:35:25.265', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ab19d38b-c46f-4f7e-b9b2-ab8f00802e19', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-5d318e20-3c58-4944-b7ac-c29e26df5ac0', '3', '2024-05-07T14:26:42.552', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ab1f969f-1131-4b95-84b0-e434a0617b96', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3', '3', '2024-02-06T21:18:36.879', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ab3edfc1-0ab2-41ad-9acd-8effd1ad7781', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-6226ed9a-1517-471a-8960-c6f23e7fb0e4', '3', '2025-05-07T18:11:38.675', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ab3f7eb6-22ca-4ff1-b017-e1ebe02efb01', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-08310c14-214f-47e0-b973-fc9043e4486d', '3', '2025-02-10T12:35:49.873', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ab75e97a-f6c9-4bf9-9a83-aace778e076e', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-cbb13f49-17eb-40fd-80f2-8b7fba973361', '3', '2024-05-07T13:01:31.683', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ac0bbf64-9658-4270-8266-46b48bda9812', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-f47ac10b-58cc-4372-a567-0e02b2c3d479', '3', '2024-03-28T13:54:09.145', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ac50a1ef-c2db-4b0f-b6a6-fa8b228b4257', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-86317a13-8a2c-41fd-b1ad-051ca070a875', '3', '2024-02-06T21:21:31.643', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ac945f94-d5fc-408e-be7a-79eb4859e929', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-94bb8cdb-75e0-4561-8af5-7c1107f0426d', '3', '2025-05-19T12:23:33.461', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-acde9834-b00d-4687-8729-c0ab25f34b8c', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-8daa7ebc-0b27-4236-b4d6-2e872f771f3a', '3', '2024-05-07T18:45:01.010', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ad19f6da-aec6-41d6-997f-6ff0c3678400', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-38af2070-0ce6-4912-9ad3-dfd5959cdd53', '3', '2024-12-02T22:44:07.290', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ad4beed1-81d2-4968-8a36-e1569d4721c0', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-423e72b1-72ea-4dbb-99f4-e72772bafc3c', '3', '2024-04-25T19:34:38.803', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ad54ba1d-1a17-4227-bed6-35ac8521f742', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-4d66eeda-f440-4994-8998-bc2fcaba3c16', '3', '2024-05-08T12:35:43.721', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ad7dda97-ba77-4f06-b988-eeb3e3468d5f', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-94bb8cdb-75e0-4561-8af5-7c1107f0426d', '3', '2025-05-19T12:23:33.281', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-add5ae4c-9940-4756-967e-b37a097219d8', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-3279ddd8-0024-4e37-b1f0-51fe30e57ff4', '3', '2024-04-11T19:00:09.015', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ae06ff4c-353a-42dc-80eb-38d061433649', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-155d2725-4be7-4e83-9ac0-88552a02023f', '3', '2025-01-06T06:10:14.675', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-aea421fb-fb22-48d0-94cc-5b1b497005aa', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-4d66eeda-f440-4994-8998-bc2fcaba3c16', '1', '2024-05-08T12:35:43.619', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-af572750-27ed-4c7e-be84-db56387813a4', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-08310c14-214f-47e0-b973-fc9043e4486d', '3', '2025-02-10T12:35:50.010', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-afa31566-052e-497f-b2a9-26434d0783f3', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-439363cf-9c16-4b9e-8840-64bb093cbbd3', '1', '2024-06-24T16:40:08.582', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-afeee343-3326-4319-9a35-23b64d99a026', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-6f8c6196-a29a-4b1d-89f4-9eec372e2c87', '3', '2024-05-07T12:41:46.125', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b06a2263-a039-463d-9ffe-8d760a3ca8ed', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-dbfbbf3e-59ce-4076-bbed-2fa699999686', '3', '2024-05-07T12:44:49.302', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b086121d-86ef-4f76-97a4-a6c952170a67', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-b64043df-c9f1-43a2-b96b-a6134b2953e1', '1', '2024-05-07T18:56:13.993', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b0e4ebb8-3a4f-42e2-8f94-2f68fa202aab', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-17a53f15-4a36-4450-abf5-387825a2434a', '3', '2024-05-08T12:35:10.317', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b14348a1-df98-446c-8a20-f2925a3d0802', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-6d018c56-58b2-4277-a258-28707ae35cc7', '3', '2024-11-18T12:36:19.481', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b1a1557d-0100-44c4-95b7-b08e0984a653', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-910116aa-e8f8-11ee-934c-fa163efa1f90', '3', '2024-04-17T22:01:38.432', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b1b5fb9e-29c1-44ed-afcb-16c99249a2f6', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-a9456c2b-c3f7-41b3-b2c4-1fd8e622dcc6', '3', '2024-05-14T14:09:50.566', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b26cd2e9-e1a2-415f-92cd-12ed667c3941', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-6226ed9a-1517-471a-8960-c6f23e7fb0e4', '3', '2025-05-07T18:11:38.695', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b28ea3fb-581f-4236-92ad-4cd2dc29a383', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-8462eabd-4a2d-4b79-8a73-99ecd64f3d40', '3', '2024-05-14T21:17:01.313', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b3431ab3-805c-46d1-adb1-5ad1b067dd15', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65', '1', '2024-03-28T13:54:08.439', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b3629f27-ade3-4be3-979e-c18baa9f298e', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-188ff31e-ff6b-47eb-a6d6-1ed0b703c0b5', '1', '2024-07-01T10:46:49.388', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b365fa17-bb3a-4c21-a078-5e4ff6a1200d', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-407dc22f-4e2a-4c52-8caa-eb667d0ec34c', '3', '2024-07-10T11:14:48.427', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b389bcfb-d2a1-4def-8840-7c7c22843e4e', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-b1ebad5d-7fa4-4872-ba1f-13df498414c6', '3', '2024-05-07T18:54:40.633', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b39beb45-e690-4ef7-9eaa-d403e48d6636', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-188ff31e-ff6b-47eb-a6d6-1ed0b703c0b5', '3', '2024-07-01T10:46:49.349', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b39f088d-aa3a-43ac-a93f-323fa43f3d99', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-155d2725-4be7-4e83-9ac0-88552a02023f', '3', '2024-02-06T21:22:24.507', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b3cff7c9-19c3-43ea-8df1-697d5e1ac669', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-5d318e20-3c58-4944-b7ac-c29e26df5ac0', '3', '2024-05-07T14:26:42.448', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b3f9545a-dbf7-4e75-aa3e-1ceb9c7ac0a4', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-f5231573-bc14-4862-a77b-d4c09e31d136', '3', '2025-02-10T12:21:25.577', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b3fe34e7-921a-4f99-815c-06906a44bf07', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-eabd4d64-15df-485c-96b6-d6e9b0792c4f', '3', '2024-11-18T12:35:25.116', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b40627dd-12d5-47a0-870a-9c6329bc030b', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-fe5bb833-fdd7-4416-81dd-f5b20107540f', '1', '2024-05-22T11:00:15.055', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b4495699-d5ce-4d73-8be5-786f47fcb39e', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-82b00b86-cba2-43de-9904-f913eed8a570', '3', '2025-01-06T06:12:36.104', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b468b780-f889-4de8-821d-c615aa436629', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-86816d21-ec0c-4dc6-ad1d-8edea9716d09', '3', '2025-01-06T06:12:36.123', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b4a9c99f-3869-4c2f-9a9e-f6b75a33fad7', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-08310c14-214f-47e0-b973-fc9043e4486d', '3', '2025-02-10T12:35:49.892', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b4bbcfae-baee-4d02-a78c-029101c277f9', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-c0fc7d55-5459-433c-ad6a-593856295d51', '3', '2024-05-07T18:53:09.411', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b4d4f0b4-c950-4b7d-9119-20a24d2ac388', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-7fb353f6-a56a-4622-a79d-dab0c178febf', '3', '2024-02-06T21:16:20.571', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b5006659-4be5-4318-a207-4d0a69921b55', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-fb3c1d88-c81f-4775-9c96-684bc9dabc75', '3', '2025-08-21T15:20:34.878', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b53a04b0-944e-45a7-b2da-aeba91b69606', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-5b87c7af-00e3-44b6-b6c5-0a9a77882864', '3', '2024-05-07T12:57:10.203', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b5703ab4-e053-4638-9431-42b314dec0d3', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-155d2725-4be7-4e83-9ac0-88552a02023f', '3', '2024-03-28T13:54:08.506', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b58cdbb8-d380-4d3f-9a12-7c2f8a792f02', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-679f830a-db5f-40d8-b210-fded905516ac', '3', '2024-05-07T12:53:20.773', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b5e96d4f-a8fa-499f-9137-6ae00152afc1', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-cbb13f49-17eb-40fd-80f2-8b7fba973361', '3', '2025-01-06T06:12:36.223', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b6467b38-1146-4355-88d9-b2bcd18bfab2', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-2eb9fc11-cba7-48e1-9af3-f22f8fcae18c', '3', '2024-07-01T10:30:20.354', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b690110c-d017-4239-a13a-b2eda41a1ee9', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-016c289e-75ae-4034-85db-94b1cff32233', '3', '2024-04-11T19:03:05.452', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b6a463a5-4a2d-4af0-beca-af01d47f408c', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-dbfbbf3e-59ce-4076-bbed-2fa699999686', '3', '2024-05-07T12:44:49.332', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b6adc40b-6b56-4e47-9d1c-60edd2eae212', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-e7149e74-4856-4440-8d94-99f915731842', '1', '2025-01-06T06:12:36.263', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b6b35d73-f4e4-4a6e-b464-f13de8ae2feb', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-3279ddd8-0024-4e37-b1f0-51fe30e57ff4', '3', '2024-04-11T19:00:09.224', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b743f441-d357-4b98-b52e-deda7c4963dc', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-551fd76e-0890-4566-a3d0-dfa2215f3e72', '3', '2024-05-07T12:59:31.863', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b75ef179-e4cf-41b6-b145-e11940795585', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-534f4acb-9a7c-465a-af38-1c8dadfefe95', '3', '2024-07-10T11:12:28.900', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b76d6b5e-e367-47e8-a7c7-dae45c495ac6', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35', '3', '2024-04-16T12:34:36.705', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b77d5ea2-bd31-4b0f-a798-bb29c694962b', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-c0fc7d55-5459-433c-ad6a-593856295d51', '3', '2024-05-07T18:53:09.633', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b7be70bf-bc2a-439d-af86-7ace6c61c947', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-fe5bb833-fdd7-4416-81dd-f5b20107540f', '3', '2024-05-22T11:00:15.069', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b7f54f37-7eb4-4025-b22e-b14e6efa3d55', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-954722d2-76cf-4e64-bdf8-f11ef7ceef23', '3', '2024-07-01T18:06:39.487', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b808ad5b-77ea-45e1-89b4-60c3c6123869', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-7c63297e-ec51-49cb-ab95-2c33229455b4', '3', '2024-07-10T11:02:07.754', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b8289874-7af8-4185-b811-551e4ef8e5f9', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-58da609f-67ca-4ea4-acea-d1c5fb7ec20d', '3', '2024-07-01T10:40:04.719', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b85313ae-5614-4df0-9999-4b8b89919b84', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-94bb8cdb-75e0-4561-8af5-7c1107f0426d', '3', '2025-05-19T12:23:33.498', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b88a1d5b-eacc-4f81-a332-dcaf7cf43461', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35', '3', '2024-04-16T12:34:36.196', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b8939447-6552-4938-948d-fa5d97f3aa69', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-da5dc88e-3ff9-4ed8-a724-c15377d306a3', '3', '2024-05-08T12:31:46.076', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b8a5693b-7ddf-4622-ac56-c46537da4a80', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-bbf33596-d4ed-41ce-a7d3-cfba75618c64', '3', '2025-01-06T06:10:14.945', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b8f0a85d-2bf3-4db3-a6f2-940d6eee0ed6', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-15731dcc-f552-4160-b5c5-99a9112ae341', '3', '2024-12-17T11:41:15.522', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b9062f37-2520-4ead-b8ea-3743a1a14261', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-423e72b1-72ea-4dbb-99f4-e72772bafc3c', '3', '2025-01-06T06:12:35.993', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b95f5105-9135-434c-b61f-e64038c05a6e', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-86317a13-8a2c-41fd-b1ad-051ca070a875', '3', '2025-01-06T06:10:14.853', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b97720b5-b69a-4283-8605-1963aa27e061', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-eeed1520-d1a8-416c-b42b-a24fa74e0d16', '3', '2024-05-07T12:42:28.371', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b98fd83f-6198-4cd6-847a-ab7376822508', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-17a53f15-4a36-4450-abf5-387825a2434a', '3', '2024-05-08T12:35:10.272', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ba52e3c5-11ed-4f82-b22a-f52255cd2c86', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-63929fcc-e8f9-11ee-934c-fa163efa1f90', '3', '2024-04-17T22:01:37.377', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ba9afe46-998b-4101-9df6-d92f1c7abd74', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-097434fa-dbbc-4ee3-ac93-fed60c6e18ce', '3', '2024-04-11T18:37:18.723', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-bb0ec1a9-6afe-4b0b-a53e-b5df7e7b5fd2', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-fe5bb833-fdd7-4416-81dd-f5b20107540f', '3', '2024-05-22T11:00:15.098', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-bb5e30cc-b6c6-407d-8d8c-1fcc790a4064', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-f844e11c-d447-48c3-87bf-1202492b8b03', '3', '2024-09-22T23:05:39.416', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-bb6b9340-f0f8-4e85-adcc-e3b977ee6f65', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-66382360-f89d-47fc-889a-61c720bdd826', '3', '2024-12-02T22:44:44.459', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-bbe2ed77-71b2-4bc3-826d-4431bc5c2074', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-902ff997-6669-40bd-9f82-d8aeb297f39b', '3', '2025-01-06T06:12:36.147', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-bbe80bf9-22a7-43e7-b55c-5bfe95775658', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-a9456c2b-c3f7-41b3-b2c4-1fd8e622dcc6', '3', '2024-05-14T14:09:50.585', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-bbf370fc-08c4-42a4-b653-497d277fbfd7', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-bbf33596-d4ed-41ce-a7d3-cfba75618c64', '3', '2024-04-11T18:46:18.194', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-bc2f7697-9d5a-48bc-8701-a5f7d52ad5a1', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-b6a3917f-2f46-4d7a-b79d-dc5b91e963c5', '3', '2024-11-18T12:34:33.666', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-bc99c33b-4b4e-48dc-b6b8-f61321fc8c85', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-86317a13-8a2c-41fd-b1ad-051ca070a875', '3', '2024-02-06T21:21:32.043', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-bd2284d2-279d-478f-a0d4-14f38fff651e', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-7c63297e-ec51-49cb-ab95-2c33229455b4', '3', '2024-07-10T11:02:07.626', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-bd4c7ef1-7580-4fd9-91ff-ff8abd819380', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-82b00b86-cba2-43de-9904-f913eed8a570', '2', '2024-11-18T12:32:29.332', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-bd54532c-6900-4626-9c49-3bd3c8304400', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-94bb8cdb-75e0-4561-8af5-7c1107f0426d', '3', '2025-05-19T12:23:33.419', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-bd94dd38-1f10-4099-a339-288211804aa0', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-25cf1417-8726-4732-abd0-38908ba1d9b2', '3', '2024-05-07T14:30:55.316', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-be06b008-a528-4bac-a47d-11d4d3457123', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-da5dc88e-3ff9-4ed8-a724-c15377d306a3', '3', '2024-05-08T12:31:46.047', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-be16971e-feaf-43f4-9965-dcf9b5f78e04', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-fe8c5e35-ba92-4f2e-9d2f-f1c89a278ef9', '3', '2025-01-08T19:07:57.736', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-be185382-68d6-4271-a97d-7673b9f9d3ec', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-94bb8cdb-75e0-4561-8af5-7c1107f0426d', '3', '2025-05-19T12:23:33.320', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-be2933ae-c4f6-4571-8143-1ef3edb038a2', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-cbb13f49-17eb-40fd-80f2-8b7fba973361', '1', '2024-05-07T13:01:31.702', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-be2945c0-f460-434e-8932-66fe08df8b3f', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-ce704fab-6961-4091-a671-ce8d9e6b6549', '3', '2025-01-06T06:10:14.989', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-be53a13a-8c90-40cb-9969-73c4e9992633', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-b1ebad5d-7fa4-4872-ba1f-13df498414c6', '3', '2024-05-07T18:54:40.443', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-be56f0d5-5d34-4c6f-b4fc-0a9b44f6ff9d', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-08310c14-214f-47e0-b973-fc9043e4486d', '3', '2025-02-10T12:35:50.029', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-be7f175d-59e6-489d-a437-e737fa0ed7a5', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-3279ddd8-0024-4e37-b1f0-51fe30e57ff4', '3', '2024-04-11T19:00:08.879', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-bea0c933-3023-4568-9645-18641a0ce3e9', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-679f830a-db5f-40d8-b210-fded905516ac', '3', '2024-05-07T12:53:20.649', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-bfa8fa0a-533b-4a59-b8db-14ac2be3d85c', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-d24188bd-5dc8-4e37-8bf4-0d355cada866', '3', '2025-01-06T06:12:36.236', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-bfc12d38-7f16-4660-b75e-f6fe8adab978', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3', '3', '2025-01-06T06:12:36.068', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-bfe5b18a-4b8c-44f2-a2c3-77b4ab1e5c14', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-f5231573-bc14-4862-a77b-d4c09e31d136', '3', '2025-02-10T12:21:25.707', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c014ee51-f0fd-4b59-bee3-1d79449c8a50', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-551fd76e-0890-4566-a3d0-dfa2215f3e72', '3', '2024-05-07T12:59:31.889', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c032390d-f128-44b9-a35b-cc8f002e761f', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-679f830a-db5f-40d8-b210-fded905516ac', '1', '2024-05-07T12:53:20.710', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c0683762-0e9d-4499-8f6b-eaabfe1110ae', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-cbb13f49-17eb-40fd-80f2-8b7fba973361', '3', '2024-05-07T13:01:31.577', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c0a74cda-c699-4059-8d4b-33159ed66343', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-439363cf-9c16-4b9e-8840-64bb093cbbd3', '3', '2024-06-24T16:40:08.566', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c0a950e7-76ba-423e-93a0-012819cb1ac3', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-86816d21-ec0c-4dc6-ad1d-8edea9716d09', '3', '2024-05-07T18:51:16.940', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c0f0154a-979a-41db-a8d3-42ec02a2a074', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-82b00b86-cba2-43de-9904-f913eed8a570', '1', '2024-11-18T12:32:29.460', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c10ed7ee-157e-4129-bc6e-42592fb8f41d', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-423e72b1-72ea-4dbb-99f4-e72772bafc3c', '3', '2024-04-25T19:34:38.945', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c13a86a2-3738-4327-be8c-e69f48d8d827', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-8daa7ebc-0b27-4236-b4d6-2e872f771f3a', '3', '2024-05-07T18:45:01.455', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c157c6ed-bd43-4108-bb51-f124e514eae6', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-eeed1520-d1a8-416c-b42b-a24fa74e0d16', '3', '2024-05-07T12:42:28.551', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c1817732-225e-4edd-9884-7795a96a6def', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-074abac7-fe4c-4908-aa7e-d72dacd94014', '3', '2024-09-22T22:03:15.528', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c1e713be-c7ae-4216-8ebe-e5a650703a44', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-fe8c5e35-ba92-4f2e-9d2f-f1c89a278ef9', '3', '2025-01-08T19:07:57.871', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c23fde34-667e-4a23-933e-29d6f1e77ba9', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-407dc22f-4e2a-4c52-8caa-eb667d0ec34c', '3', '2024-07-10T11:14:48.246', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c25c6e0e-df82-4faa-b0e3-f6cf2ccea293', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-b64043df-c9f1-43a2-b96b-a6134b2953e1', '3', '2024-05-07T18:56:13.740', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c2aa664a-28c1-4265-9ba3-1b7653cc6c29', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-82b00b86-cba2-43de-9904-f913eed8a570', '3', '2024-11-18T12:32:29.351', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c2ba9357-9b88-40b9-80c5-762c1b2c6371', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-423e72b1-72ea-4dbb-99f4-e72772bafc3c', '3', '2024-04-25T19:34:39.030', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c2e67eab-6efa-4219-863d-c82254f01c92', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-ce704fab-6961-4091-a671-ce8d9e6b6549', '3', '2025-01-06T06:12:36.231', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c3190c24-d339-45f3-91af-b427e669d4fa', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-dbfbbf3e-59ce-4076-bbed-2fa699999686', '3', '2024-05-07T12:44:49.507', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c340365e-d5a7-4c5b-a4e2-c5b73c78a26d', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-15731dcc-f552-4160-b5c5-99a9112ae341', '3', '2024-12-17T11:41:15.502', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c36cf716-cff1-4acf-8c14-e7a8563596b5', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-f78d8a42-fd40-4643-a737-cb85234737d6', '3', '2024-05-21T13:46:28.085', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c3e933cb-0124-47f7-ae00-ac54ae3eb342', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-423e72b1-72ea-4dbb-99f4-e72772bafc3c', '1', '2024-04-25T19:34:38.849', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c4005420-fe08-4712-8f41-1924ae634a88', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-b1ebad5d-7fa4-4872-ba1f-13df498414c6', '3', '2025-01-06T06:12:36.179', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c438ad91-aa0f-4c8d-845c-9f6ae01b5677', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-dc11e4e7-6e9f-4975-a6b7-5f97ba1c46d3', '3', '2025-01-06T06:10:15.010', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c4475dc8-a1f5-43b9-b0bf-84d0c61bd35a', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-dc11e4e7-6e9f-4975-a6b7-5f97ba1c46d3', '3', '2024-05-08T12:36:21.241', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c4925953-c468-4de4-86fe-2b283c9d0008', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-731d1bc7-e47b-463e-bf26-5a113dee9716', '3', '2025-01-08T19:20:53.160', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c5acb0a1-9e29-47d0-91f5-cae9745371b2', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-534f4acb-9a7c-465a-af38-1c8dadfefe95', '3', '2024-07-10T11:12:28.731', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c5ad5f67-21db-4aec-bc91-737c27a15d98', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-8d0cc23d-60b6-4092-b10a-915e0afcca55', '3', '2025-03-14T17:56:25.707', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c5b4decb-d03a-4b6e-a07b-8eb01c741b15', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-6c7208c0-bbc3-4d8d-a19b-6ca9d6b269fb', '3', '2025-05-18T21:16:36.502', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c5bf7023-9079-45be-913b-99ad6586a01b', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-58da609f-67ca-4ea4-acea-d1c5fb7ec20d', '1', '2024-07-01T10:40:04.634', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c66df1d6-2c50-4033-9e3e-7c0dddd87275', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-2eb9fc11-cba7-48e1-9af3-f22f8fcae18c', '1', '2024-07-01T10:30:20.394', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c68acae8-9a5c-4a5c-a1b9-c406bc12bcd8', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-b6a3917f-2f46-4d7a-b79d-dc5b91e963c5', '2', '2024-11-18T12:34:33.489', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c807b4e2-594b-47b6-904a-158b46cf1a93', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-ecaa4072-d8ae-4f54-b681-6d42ca75db12', '3', '2024-05-07T18:57:14.968', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c8536368-4231-4c45-8e4b-2ee84553188c', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-b64043df-c9f1-43a2-b96b-a6134b2953e1', '3', '2024-05-07T18:56:14.202', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c8acc64c-9765-43df-9fc4-f5d899b9bf8a', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-551fd76e-0890-4566-a3d0-dfa2215f3e72', '3', '2024-05-07T12:59:31.911', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c8b96a59-a094-4950-9b0d-df8f325cf4f9', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-087a4eac-7036-4074-a813-831efa3d6950', '3', '2024-05-08T12:36:58.679', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c9081345-b977-4aa4-bbc3-0ab616a30d26', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-7fb353f6-a56a-4622-a79d-dab0c178febf', '1', '2024-02-06T21:16:20.171', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c912f642-7949-4325-bd02-acbea1de5699', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-eabd4d64-15df-485c-96b6-d6e9b0792c4f', '3', '2024-11-18T12:35:25.173', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c974b840-28f1-4204-8af1-04ca898f1627', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3', '3', '2025-01-06T06:10:14.803', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c9a4d6ce-7e77-42b1-b603-7ef57a883926', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-6d459f8b-86da-44ba-a90d-ee644c046bda', '3', '2025-05-18T21:06:24.819', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c9f70e49-0657-4443-ac79-d66da558f3cf', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', '1', '2024-02-06T21:10:00.478', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ca11aaee-a210-4399-b390-97a9d572aa79', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-a9278b5d-f2ba-4429-854f-4662000f50e6', '3', '2024-05-05T22:27:17.964', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ca1fface-29c6-4a04-b7ee-25ab380d777e', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-eeed1520-d1a8-416c-b42b-a24fa74e0d16', '3', '2024-05-07T12:42:28.397', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-cab7bcc4-fa17-43d2-aeeb-d6c42679abdd', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-58da609f-67ca-4ea4-acea-d1c5fb7ec20d', '3', '2024-07-01T10:40:04.588', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-cacfe656-dcd6-469e-813f-359feb5015eb', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-6d018c56-58b2-4277-a258-28707ae35cc7', '3', '2024-11-18T12:36:19.416', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-caf6cac2-2b39-4a99-bbb5-259dd73d8ecb', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-82b00b86-cba2-43de-9904-f913eed8a570', '3', '2024-11-18T12:32:29.517', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-cb1bfad5-fb24-4cb8-a987-d6f113d5d060', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-097434fa-dbbc-4ee3-ac93-fed60c6e18ce', '3', '2024-04-11T18:37:18.519', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-cb499c62-0eca-4854-af34-dd5fbb8f627c', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-6c7208c0-bbc3-4d8d-a19b-6ca9d6b269fb', '3', '2025-05-18T21:16:36.599', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-cb4b4a07-82ec-46b0-b412-f966683d0e19', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-0ee32ded-100b-4505-95c4-65d5e9b3d93c', '3', '2024-04-16T12:38:51.520', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-cb50e3dd-bbba-437a-a893-692ee2cd2e22', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-6c7208c0-bbc3-4d8d-a19b-6ca9d6b269fb', '3', '2025-05-18T21:16:36.520', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-cb565e55-3213-4b85-a9be-fbb5ba9f6fd5', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-910116aa-e8f8-11ee-934c-fa163efa1f90', '3', '2024-04-17T22:01:36.867', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-cbce7c87-4aad-4505-8015-db902b713489', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-551fd76e-0890-4566-a3d0-dfa2215f3e72', '3', '2024-05-07T12:59:32.038', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-cbfe1fb6-6c43-453c-b883-eaad647ec43d', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-e7149e74-4856-4440-8d94-99f915731842', '3', '2024-09-04T16:42:57.428', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-cc246b73-8275-4c88-a619-4a150a60dc4c', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-c03dadaf-4a89-42a1-8b36-847d34c8b337', '3', '2025-03-14T17:55:27.577', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-cc33a0fd-c3a3-4069-8b38-9478e8a413e3', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-25cf1417-8726-4732-abd0-38908ba1d9b2', '3', '2024-05-07T14:30:55.334', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-cc372b60-c429-4586-9c7c-a115cd0bcdd7', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-c38a1e47-8271-43f2-aca3-bb7b2db33ad0', '3', '2024-05-21T13:41:44.513', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-cc7b61ab-eed3-4bd4-a175-5cec0f8cc8eb', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-6d018c56-58b2-4277-a258-28707ae35cc7', '3', '2025-01-06T06:10:14.810', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-cce04401-56f0-4cb4-82ff-12eb3cf591de', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-58da609f-67ca-4ea4-acea-d1c5fb7ec20d', '3', '2024-07-01T10:40:04.610', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ccfe266d-3807-4a71-bd23-20db48edea91', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', '3', '2025-01-06T06:12:36.128', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ccfe8e95-fc6c-430b-a6ce-1117ecdb7ada', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-5d318e20-3c58-4944-b7ac-c29e26df5ac0', '3', '2025-01-06T06:10:14.782', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-cd0b93e9-cba6-4afd-8eda-adc344fb5beb', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-bf697261-39c2-408c-b11a-b5a60ec89994', '3', '2024-07-08T15:40:51.156', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-cd2864f9-dba5-41f8-bbdd-28ad58a9161c', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-8e29b12a-b61e-4ec6-b7ea-abab46cf27f6', '3', '2025-01-06T06:10:14.885', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ce795a99-a91f-4626-8f52-46af37e9a059', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-423e72b1-72ea-4dbb-99f4-e72772bafc3c', '3', '2024-04-25T19:34:38.874', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ce8101bc-bdb7-4e11-b54d-3a3a6b549b3c', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-fe8c5e35-ba92-4f2e-9d2f-f1c89a278ef9', '3', '2025-01-08T19:07:57.806', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-cf2603d5-62c6-4752-b1ab-1901499ec9c2', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-902ff997-6669-40bd-9f82-d8aeb297f39b', '3', '2024-07-08T15:41:24.732', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-cf554616-30b6-4035-8569-3d693202bc51', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-fe5bb833-fdd7-4416-81dd-f5b20107540f', '3', '2024-05-22T11:00:15.083', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-cf614481-b335-49ce-8cae-299a0fe4377c', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-188ff31e-ff6b-47eb-a6d6-1ed0b703c0b5', '3', '2024-07-01T10:46:49.430', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-cf622acb-483b-4539-81e7-cf3ddae2a82c', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-3279ddd8-0024-4e37-b1f0-51fe30e57ff4', '3', '2024-04-11T19:00:09.429', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d018dcec-c5b7-405b-9641-d757fd0143e5', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-954722d2-76cf-4e64-bdf8-f11ef7ceef23', '3', '2024-07-01T18:06:39.422', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d0944694-aa4b-4f28-b313-f1c0536b0036', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-679f830a-db5f-40d8-b210-fded905516ac', '3', '2024-05-07T12:53:20.607', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d0dc339f-9453-4562-a135-ae8a6efe8b5a', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-94bb8cdb-75e0-4561-8af5-7c1107f0426d', '3', '2025-05-19T12:23:33.382', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d0f18fe7-b39d-4667-9c1e-025e7bd59870', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-c38a1e47-8271-43f2-aca3-bb7b2db33ad0', '3', '2024-05-21T13:41:44.551', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d10a4445-8179-46de-8bad-798a2da47b7f', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-eeed1520-d1a8-416c-b42b-a24fa74e0d16', '3', '2024-05-07T12:42:28.474', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d119a7c5-6387-46dd-b88f-c410be1bbd4e', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65', '1', '2024-03-27T22:12:01.325', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d146ab82-1013-407c-8a75-4eca7d4569cd', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-6226ed9a-1517-471a-8960-c6f23e7fb0e4', '3', '2025-05-07T18:11:38.755', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d1c00cab-0d49-4fb4-850e-d3c824d32d56', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-86816d21-ec0c-4dc6-ad1d-8edea9716d09', '3', '2024-05-07T18:51:17.070', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d1d53b1b-8894-4872-869f-85b7549cb920', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-407dc22f-4e2a-4c52-8caa-eb667d0ec34c', '3', '2024-07-10T11:14:48.454', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d2082817-aa84-4f5b-93fc-db3910463846', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-8d0cc23d-60b6-4092-b10a-915e0afcca55', '3', '2025-03-14T17:56:25.573', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d20eb836-3c3d-4377-a9c3-5dfbb367c85c', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-155d2725-4be7-4e83-9ac0-88552a02023f', '1', '2025-01-06T06:12:35.869', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d235bd6c-296c-4c9a-a45d-9bfddea2553f', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-ce704fab-6961-4091-a671-ce8d9e6b6549', '3', '2024-12-02T22:43:18.177', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d269d708-0490-4ec3-adfc-2341b67775c0', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-a9278b5d-f2ba-4429-854f-4662000f50e6', '3', '2024-05-05T22:27:17.942', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d275f87b-ba9f-4757-b9ff-b87a12c97663', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-f47ac10b-58cc-4372-a567-0e02b2c3d479', '3', '2024-04-17T22:01:36.993', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d27c37a0-ad5a-4ca9-9d89-3f6f1f4e1df3', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-551fd76e-0890-4566-a3d0-dfa2215f3e72', '3', '2024-05-07T12:59:31.988', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d27c84de-5c89-4fe3-a8d6-91d6b28b5db4', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-a9278b5d-f2ba-4429-854f-4662000f50e6', '3', '2024-05-05T22:27:18.075', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d285f6c8-687d-4c71-bf05-7a8a919e5f3d', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-679f830a-db5f-40d8-b210-fded905516ac', '3', '2025-01-06T06:12:36.063', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d2b2cdaa-0c2d-421b-ab1d-d7182c4dd771', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-08310c14-214f-47e0-b973-fc9043e4486d', '3', '2025-02-10T12:35:49.951', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d2c0f8c9-a1c2-4cd2-b81d-4026dada910c', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-86317a13-8a2c-41fd-b1ad-051ca070a875', '3', '2024-02-06T21:21:31.119', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d30df924-0e11-4be6-b18c-d36eb0524b35', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-fe5bb833-fdd7-4416-81dd-f5b20107540f', '3', '2024-05-22T11:00:14.968', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d31f9212-9d27-479c-b3fa-5e72546ba81c', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-7c63297e-ec51-49cb-ab95-2c33229455b4', '3', '2025-01-06T06:10:14.821', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d36379a6-e9e6-49c6-be7e-60e108283c87', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-eeed1520-d1a8-416c-b42b-a24fa74e0d16', '3', '2025-01-06T06:10:15.032', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d386510f-f1cf-4237-9516-11b24362cc16', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', '3', '2024-02-06T21:14:03.828', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d38b3955-17f9-4e78-800c-48497f6b35be', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-6d459f8b-86da-44ba-a90d-ee644c046bda', '3', '2025-05-18T21:06:24.730', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d3ea2007-5bf0-4771-a306-d200af60f7bb', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-fe8c5e35-ba92-4f2e-9d2f-f1c89a278ef9', '3', '2025-01-08T19:07:57.773', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d420d312-e8fc-402f-a6d8-d06c3399a3b9', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-188ff31e-ff6b-47eb-a6d6-1ed0b703c0b5', '1', '2024-07-01T10:46:49.368', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d44add85-02c8-42eb-8917-727100fe54dd', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-5ae215f6-d749-459f-9a64-bb022cc1f6f9', '3', '2025-04-20T17:23:00.249', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d46ebe42-ee5f-4f90-9ebe-686d155c84ab', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-407dc22f-4e2a-4c52-8caa-eb667d0ec34c', '3', '2024-07-10T11:14:48.349', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d47f3eed-b7de-423b-816f-1edea8788f03', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', '3', '2024-02-06T21:14:04.291', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d493f819-8ae4-4325-a8d2-183bde3bad11', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-0ee32ded-100b-4505-95c4-65d5e9b3d93c', '3', '2024-04-16T12:38:51.862', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d52e6e67-831b-4941-ba08-f5bb0a38d920', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-f844e11c-d447-48c3-87bf-1202492b8b03', '3', '2024-09-22T23:05:39.337', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d54746a6-aac5-4aee-91cc-a39081430d18', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-4d66eeda-f440-4994-8998-bc2fcaba3c16', '3', '2024-05-08T12:35:43.634', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d5c47962-85f8-43c1-bd51-a8e134de6607', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-c03dadaf-4a89-42a1-8b36-847d34c8b337', '3', '2025-03-14T17:55:27.557', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d5d805f6-b504-4244-a6af-b3e7b74802c2', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-50b321e8-2886-4b99-a921-cbb5baacf1ac', '3', '2024-07-06T11:53:34.890', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d5edeab5-47a7-4f54-89da-d5f9f38e69c5', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-a9456c2b-c3f7-41b3-b2c4-1fd8e622dcc6', '3', '2024-05-14T14:09:50.663', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d60b3d67-b461-4c7c-ab91-76d1515a37b1', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-6d459f8b-86da-44ba-a90d-ee644c046bda', '3', '2025-05-18T21:06:24.584', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d6190460-5c2f-45c5-ab59-e1e523d56b34', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-b6a3917f-2f46-4d7a-b79d-dc5b91e963c5', '3', '2024-11-18T12:34:33.582', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d63840ab-3b12-4cc3-a772-22b0fee274e8', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-f47ac10b-58cc-4372-a567-0e02b2c3d479', '3', '2025-01-06T06:10:15.037', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d66c7555-df98-4083-a62b-d7ffd5504c68', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-38af2070-0ce6-4912-9ad3-dfd5959cdd53', '1', '2024-12-02T22:44:07.212', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d6c65073-0d6e-4d1a-b794-8e3b5c5dde35', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-7fb353f6-a56a-4622-a79d-dab0c178febf', '3', '2024-02-06T21:16:20.760', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d6fa4a5d-b69e-4540-a313-6d440ca02d92', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-016c289e-75ae-4034-85db-94b1cff32233', '3', '2024-04-11T19:03:05.248', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d6ffce2d-df2d-4390-95eb-c8f4d03021f0', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-86816d21-ec0c-4dc6-ad1d-8edea9716d09', '3', '2024-05-07T18:51:17.421', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d7419bf2-a6cf-450f-80c0-0d488aed78bc', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-63929fcc-e8f9-11ee-934c-fa163efa1f90', '3', '2024-04-17T22:01:42.465', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d78104c2-44f8-4ffd-8466-9abdff84b90a', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-cbb13f49-17eb-40fd-80f2-8b7fba973361', '1', '2025-01-06T06:10:14.984', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d7851be7-fd6f-441b-b914-3a99831007d0', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-7c63297e-ec51-49cb-ab95-2c33229455b4', '3', '2024-07-10T11:02:07.574', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d7e40292-ca98-494d-aa52-a126c2bf747b', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', '1', '2025-01-06T06:12:35.786', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d7f7d3f2-f45d-4e0f-a7a5-38afe671452a', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-407dc22f-4e2a-4c52-8caa-eb667d0ec34c', '3', '2024-07-10T11:14:48.374', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d85d6202-c15e-47fd-8b03-ea2f80ea911a', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-902ff997-6669-40bd-9f82-d8aeb297f39b', '1', '2024-07-08T15:41:24.713', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d9113d3c-3402-4cd1-b3ab-8757b6ee7cd2', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-e1a6e9a2-8a97-417b-bc5d-ad936b0ac41f', '3', '2025-01-15T21:21:36.578', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d9546aec-9697-4d6c-b84e-5e51ecb3c0f2', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-8462eabd-4a2d-4b79-8a73-99ecd64f3d40', '3', '2025-01-06T06:12:36.111', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d986e331-c1e4-45cc-866d-5dd59fcdaee7', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-910116aa-e8f8-11ee-934c-fa163efa1f90', '3', '2025-01-06T06:10:14.898', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-da436760-6f6a-44e6-9fff-75dcc9b3d520', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-fe8c5e35-ba92-4f2e-9d2f-f1c89a278ef9', '3', '2025-01-08T19:07:57.844', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-da8d7fc6-cb8e-4bc0-80f6-f8ff22d5df10', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-087a4eac-7036-4074-a813-831efa3d6950', '3', '2024-05-08T12:36:58.662', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-dab5e5d6-9601-4eac-9009-d5877f8f8708', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-155d2725-4be7-4e83-9ac0-88552a02023f', '3', '2024-02-06T21:22:24.100', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-dae866aa-176e-468d-a4c5-d23c6650d359', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-bf697261-39c2-408c-b11a-b5a60ec89994', '3', '2024-07-08T15:40:51.034', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-db104799-5a17-4775-9c7a-60dfd1012f1e', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-7fb353f6-a56a-4622-a79d-dab0c178febf', '3', '2024-02-06T21:16:19.776', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-db40771e-cee7-4abf-b1fe-b13318bec08d', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-fe8c5e35-ba92-4f2e-9d2f-f1c89a278ef9', '3', '2025-01-08T19:07:57.886', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-db468cc7-37fb-490c-b54a-a0c0f1d0cd35', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-15731dcc-f552-4160-b5c5-99a9112ae341', '1', '2024-12-17T11:41:15.623', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-db770eb1-716c-425e-8d10-2d5c0e82ff10', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-bbf33596-d4ed-41ce-a7d3-cfba75618c64', '3', '2024-04-11T18:46:18.544', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-dbed956b-0c31-422a-a4c2-9724c11a9b5c', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-08310c14-214f-47e0-b973-fc9043e4486d', '3', '2025-02-10T12:35:49.932', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-dc391520-27d8-4c56-a53d-18e2759251dd', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-e1a6e9a2-8a97-417b-bc5d-ad936b0ac41f', '3', '2025-01-15T21:21:36.461', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-dc532afa-0b8a-4d32-845b-50a89e44cf0e', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-c03dadaf-4a89-42a1-8b36-847d34c8b337', '3', '2025-03-14T17:55:27.647', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-dc93bbd4-3e8a-46af-a37d-917897840bde', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-423e72b1-72ea-4dbb-99f4-e72772bafc3c', '3', '2024-04-25T19:34:38.829', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-dcd91ca4-2784-4cb0-a398-e19e2ad6f0e4', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-016c289e-75ae-4034-85db-94b1cff32233', '3', '2024-04-11T19:03:04.844', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-dd047230-1313-4dcb-85a0-e648f9d8833d', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-a9278b5d-f2ba-4429-854f-4662000f50e6', '3', '2025-01-06T06:10:14.911', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-dd12b747-de03-403a-aa81-dba21930c3a5', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-0ee32ded-100b-4505-95c4-65d5e9b3d93c', '3', '2024-04-16T12:38:51.254', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-dd99eda2-a59e-4c7d-b74b-70e98ce8f15a', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-ecaa4072-d8ae-4f54-b681-6d42ca75db12', '3', '2024-05-07T18:57:15.106', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-dda4a6f7-d31a-4009-8b48-97bdeee8e1c6', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-d24188bd-5dc8-4e37-8bf4-0d355cada866', '3', '2024-05-07T18:32:36.420', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-dde02baf-c0af-40c9-a9fb-1b1302733d10', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-439363cf-9c16-4b9e-8840-64bb093cbbd3', '3', '2025-01-06T06:10:14.731', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ddfb37ec-f278-4949-bd08-fc9efa304e28', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65', '1', '2024-03-27T22:12:01.887', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-de39618f-bd04-43e2-80f9-31d45bd7b93e', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-eeed1520-d1a8-416c-b42b-a24fa74e0d16', '3', '2024-05-07T12:42:28.576', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-df5475fc-b022-4c2b-becf-566e88f8faf9', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-25cf1417-8726-4732-abd0-38908ba1d9b2', '3', '2024-05-07T14:30:55.389', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-dfaa5ee5-f2d9-4685-a0b8-676647461dcf', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-679f830a-db5f-40d8-b210-fded905516ac', '3', '2024-05-07T12:53:20.627', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-dfaac014-cb9a-4117-aa16-ae34938f373b', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-b64043df-c9f1-43a2-b96b-a6134b2953e1', '1', '2024-05-07T18:56:13.482', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e0039230-203e-48f6-afce-6c7dd4e1922b', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-f47ac10b-58cc-4372-a567-0e02b2c3d479', '3', '2024-04-17T22:01:38.565', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e0112093-ef1a-4147-9583-a5a4bfc7d516', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-8462eabd-4a2d-4b79-8a73-99ecd64f3d40', '3', '2024-05-14T21:17:01.257', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e014d462-9037-4b1c-bdae-6585f0fd49fd', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-910116aa-e8f8-11ee-934c-fa163efa1f90', '3', '2024-04-17T22:01:42.719', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e031aeb9-f590-45c5-bd74-3255c2a4b9ed', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-5ae215f6-d749-459f-9a64-bb022cc1f6f9', '3', '2025-04-20T17:22:59.936', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e0480972-ca21-432e-bbe7-0d3932986c33', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-155d2725-4be7-4e83-9ac0-88552a02023f', '3', '2024-02-06T21:22:24.632', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e073d770-e25f-4ba8-97ca-949e85d2df56', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-b6a3917f-2f46-4d7a-b79d-dc5b91e963c5', '3', '2024-11-18T12:34:33.557', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e090b4b3-e818-4654-8b6c-8857838b406c', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-15731dcc-f552-4160-b5c5-99a9112ae341', '3', '2024-12-17T11:41:15.559', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e0bdf31a-c635-4261-92e0-abe1866c7410', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-954722d2-76cf-4e64-bdf8-f11ef7ceef23', '3', '2024-07-01T18:06:39.548', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e0ffdc1e-a594-4db8-8ee8-315394d85a89', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-910116aa-e8f8-11ee-934c-fa163efa1f90', '3', '2024-04-17T22:01:39.219', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e1511355-dcb0-4431-8640-b385e4c4adb3', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-534f4acb-9a7c-465a-af38-1c8dadfefe95', '3', '2024-07-10T11:12:28.838', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e16df48c-eaa2-4cbd-a551-759a7c6c8a3a', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-0ee32ded-100b-4505-95c4-65d5e9b3d93c', '3', '2024-04-16T12:38:51.320', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e17d9eab-15c5-42b4-b895-797dbbd15b3d', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-074abac7-fe4c-4908-aa7e-d72dacd94014', '3', '2024-09-22T22:03:15.347', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e287c52f-28d6-4dae-a0d2-383d2097c901', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-3279ddd8-0024-4e37-b1f0-51fe30e57ff4', '3', '2024-04-11T19:00:09.360', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e2a3e2e1-87d1-4ee1-b7f8-7b95f1036008', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-188ff31e-ff6b-47eb-a6d6-1ed0b703c0b5', '1', '2025-01-06T06:10:14.692', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e2b020b1-88bb-4b3a-8425-698c573fdf3b', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-0ee32ded-100b-4505-95c4-65d5e9b3d93c', '3', '2024-04-16T12:38:51.724', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e36ab575-b111-4734-b200-381938ef6251', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-94bb8cdb-75e0-4561-8af5-7c1107f0426d', '3', '2025-05-19T12:23:33.343', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e38c50e1-f0f9-4abb-b853-85cb8e83ded3', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-a9278b5d-f2ba-4429-854f-4662000f50e6', '3', '2024-05-05T22:27:18.095', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e38f0c56-1e75-4825-8bdf-551ba3bca91c', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-551fd76e-0890-4566-a3d0-dfa2215f3e72', '3', '2024-05-07T12:59:31.815', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e3e30069-dfe1-4e7e-ae8b-b2de4fc1c137', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-439363cf-9c16-4b9e-8840-64bb093cbbd3', '3', '2024-06-24T16:40:08.520', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e445829c-f4d8-4003-91fc-6c02e61f71ca', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-188ff31e-ff6b-47eb-a6d6-1ed0b703c0b5', '3', '2024-07-01T10:46:49.308', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e4b730ae-f46e-406c-9b6b-ce24a5b6873b', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-b64043df-c9f1-43a2-b96b-a6134b2953e1', '3', '2024-05-07T18:56:14.283', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e500c081-0c24-4b83-8641-d668032af5f9', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-097434fa-dbbc-4ee3-ac93-fed60c6e18ce', '3', '2025-01-06T06:12:35.838', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e6001ef7-3f7a-42b4-b91a-bdb601278029', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35', '1', '2024-04-16T12:34:36.487', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e6406f4a-e28b-459f-8535-f9b7c7f127ca', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-8462eabd-4a2d-4b79-8a73-99ecd64f3d40', '3', '2024-05-14T21:17:01.244', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e65498ec-53ef-4644-af3e-b138a72cd75b', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-bf697261-39c2-408c-b11a-b5a60ec89994', '3', '2024-07-08T15:40:51.197', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e6bafe06-84eb-4ca0-ac9d-293c37fc919c', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-c0220993-26e0-4d21-bc25-f612c67170c5', '3', '2024-05-05T22:25:00.462', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e75476f9-0578-4782-bcca-032efbb8def0', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-c03dadaf-4a89-42a1-8b36-847d34c8b337', '3', '2025-03-14T17:55:27.539', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e78be06b-35d0-44c0-8321-5cf559bb4a7a', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-6f8c6196-a29a-4b1d-89f4-9eec372e2c87', '3', '2025-01-06T06:12:36.078', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e7a01845-dca9-4e4a-a903-cfc9e7c96fe2', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-ecaa4072-d8ae-4f54-b681-6d42ca75db12', '3', '2025-01-06T06:12:36.273', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e85959fd-e085-49ab-8d29-29db93fc9841', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-a9278b5d-f2ba-4429-854f-4662000f50e6', '3', '2024-05-05T22:27:18.007', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e87366fd-6134-4ec7-ae44-487c4f5c4590', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-da5dc88e-3ff9-4ed8-a724-c15377d306a3', '3', '2025-01-06T06:10:15.002', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e8864dba-0290-463a-aabe-94633befb9f3', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-eeed1520-d1a8-416c-b42b-a24fa74e0d16', '3', '2025-01-06T06:12:36.279', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e892af7f-3635-489f-bb51-d483a3f9aa01', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-c03dadaf-4a89-42a1-8b36-847d34c8b337', '3', '2025-03-14T17:55:27.681', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e934b4c2-4a9e-49ec-9737-adc7abaec612', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-ecaa4072-d8ae-4f54-b681-6d42ca75db12', '3', '2024-05-07T18:57:15.564', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e9510c20-bdc7-471b-a67e-c8597eb974cc', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-c0220993-26e0-4d21-bc25-f612c67170c5', '3', '2024-05-05T22:25:00.437', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e9968e85-6a1f-4b72-aa60-17e1662d76dd', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', '1', '2024-02-06T21:14:03.377', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e9ef7ad8-4e79-43f4-93c0-796daf4d3245', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-f78d8a42-fd40-4643-a737-cb85234737d6', '3', '2024-05-21T13:46:28.123', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ea09fe7a-d458-44fe-b273-8d37c027f36d', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-6c7208c0-bbc3-4d8d-a19b-6ca9d6b269fb', '3', '2025-05-18T21:16:36.697', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ea4f0c55-4e4a-402d-8eb7-4d1bdbe9203d', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-08310c14-214f-47e0-b973-fc9043e4486d', '3', '2025-02-10T12:35:49.970', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ea4ffd4f-0863-4818-aa92-5387d715f66c', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-38af2070-0ce6-4912-9ad3-dfd5959cdd53', '3', '2024-12-02T22:44:07.048', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ea5e809d-620b-4c4d-9bd0-10a07ed104b1', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-188ff31e-ff6b-47eb-a6d6-1ed0b703c0b5', '1', '2025-01-06T06:12:35.910', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-eac9bb0f-90e5-4dfb-bf78-671859f0289a', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-679f830a-db5f-40d8-b210-fded905516ac', '3', '2024-05-07T12:53:20.670', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-eb08a830-19fa-499d-acfe-466eb899571f', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-c0fc7d55-5459-433c-ad6a-593856295d51', '3', '2024-05-07T18:53:09.873', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-eb996cb9-37a5-4537-849a-d0c28e0ecfb5', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-bbf33596-d4ed-41ce-a7d3-cfba75618c64', '3', '2025-01-06T06:12:36.196', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ebb21c29-2392-424c-a529-93d4ca36001f', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-f5231573-bc14-4862-a77b-d4c09e31d136', '3', '2025-02-10T12:21:25.557', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ebf76b4c-d139-48d9-b9a7-a9982892d5a8', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-fb3c1d88-c81f-4775-9c96-684bc9dabc75', '3', '2025-08-21T15:20:34.836', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ecac7f73-7fe8-4216-82e4-9f3738be561f', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', '3', '2024-03-28T13:54:08.371', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ecaeff13-8c19-4b23-9d7c-663f9b767505', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-5d318e20-3c58-4944-b7ac-c29e26df5ac0', '3', '2024-05-07T14:26:42.573', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ecc358a2-656a-4cca-b290-7eef43f6f33b', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-155d2725-4be7-4e83-9ac0-88552a02023f', '3', '2024-02-06T21:22:25.156', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-eccb3fc2-f9fc-43ff-961d-a556b34f1c46', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-087a4eac-7036-4074-a813-831efa3d6950', '3', '2024-05-08T12:36:58.726', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ed276bd6-06b7-44fa-856b-abd819cd4935', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-a9278b5d-f2ba-4429-854f-4662000f50e6', '3', '2025-01-06T06:12:36.162', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ee37bc5c-df86-4c70-8849-e66689db4b95', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-5b87c7af-00e3-44b6-b6c5-0a9a77882864', '3', '2024-05-07T12:57:10.178', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ee71d7e2-9865-4bfe-ae42-02d7d656e278', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-407dc22f-4e2a-4c52-8caa-eb667d0ec34c', '3', '2024-07-10T11:14:48.296', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ef097dd4-539d-4e4b-bd75-eaa05333d59c', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-5ae215f6-d749-459f-9a64-bb022cc1f6f9', '3', '2025-04-20T17:22:59.887', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ef45eb9b-ffe1-44e3-9fcd-c4dcc3673475', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35', '3', '2024-04-16T12:34:36.267', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ef507cee-a4f5-4dd2-b84f-cf2fe52442c8', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-074abac7-fe4c-4908-aa7e-d72dacd94014', '3', '2024-09-22T22:03:15.489', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ef8b11b2-c291-4820-9a7c-4fdca73ad8cc', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-e7149e74-4856-4440-8d94-99f915731842', '1', '2025-01-06T06:10:15.015', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-eff8edcb-163a-4936-9c22-f91cbd29d388', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-155d2725-4be7-4e83-9ac0-88552a02023f', '3', '2024-02-06T21:22:24.965', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-efff6378-c6b1-4a52-826f-2088f2b7a566', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-7fb353f6-a56a-4622-a79d-dab0c178febf', '3', '2024-03-28T13:54:08.849', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f02b3948-7eb4-4c55-9647-371dd1c02f61', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-fe5bb833-fdd7-4416-81dd-f5b20107540f', '3', '2024-05-22T11:00:15.041', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f0596936-d677-415b-a74e-b1df219388e7', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-fe8c5e35-ba92-4f2e-9d2f-f1c89a278ef9', '3', '2025-01-08T19:07:57.668', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f0755680-850a-48f9-a2fc-fa60350fe201', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-c0fc7d55-5459-433c-ad6a-593856295d51', '3', '2024-05-07T18:53:09.710', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f07b40cc-5ddb-4fb4-8cd5-8e7475369cda', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-ce704fab-6961-4091-a671-ce8d9e6b6549', '2', '2024-12-02T22:43:17.863', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f097d084-270c-4fdc-9985-67a2689dfd8f', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-f47ac10b-58cc-4372-a567-0e02b2c3d479', '3', '2024-04-17T22:01:42.076', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f0dede11-e108-4c9e-932e-393404052193', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-8d0cc23d-60b6-4092-b10a-915e0afcca55', '3', '2025-03-14T17:56:25.555', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f0e1ca57-37e1-4a71-9ee6-6fc44da8e984', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-d24188bd-5dc8-4e37-8bf4-0d355cada866', '1', '2024-05-07T18:32:36.021', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f10913a3-441e-4e7d-803b-3a2a582957eb', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-8462eabd-4a2d-4b79-8a73-99ecd64f3d40', '3', '2024-05-14T21:17:01.207', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f18ab5a1-caad-41b2-95f5-4ed6ba29150f', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-bbf33596-d4ed-41ce-a7d3-cfba75618c64', '3', '2024-04-11T18:46:17.991', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f28f436f-3533-4ca3-865f-1f5804ee8147', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-c0fc7d55-5459-433c-ad6a-593856295d51', '3', '2024-05-07T18:53:09.482', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f295f6ce-6a0b-44b8-928f-fa2f79b1b5a4', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-50b321e8-2886-4b99-a921-cbb5baacf1ac', '3', '2025-01-06T06:10:14.742', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f3680d6d-6971-4bf3-a34b-10ca672fbaff', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-fe8c5e35-ba92-4f2e-9d2f-f1c89a278ef9', '3', '2025-01-08T19:07:57.754', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f41b8acb-eca4-4d22-bf85-27cb2d62639d', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-731d1bc7-e47b-463e-bf26-5a113dee9716', '3', '2025-01-08T19:20:52.894', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f4536c7f-c2d0-4c21-81ac-9add76b42aae', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-63929fcc-e8f9-11ee-934c-fa163efa1f90', '3', '2024-03-28T13:54:08.645', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f49354b0-c79c-4d63-8d6a-f8eb332095b3', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998', '3', '2024-02-06T21:23:39.619', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f4b97889-f562-42c5-b002-3fe911a9095f', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-a9456c2b-c3f7-41b3-b2c4-1fd8e622dcc6', '3', '2024-05-14T14:09:50.682', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f4d9cd49-48fa-4b77-b969-214521676da7', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-5ae215f6-d749-459f-9a64-bb022cc1f6f9', '3', '2025-04-20T17:23:00.212', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f54ef61c-7aad-427c-a2ec-ba9392f92d47', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-8e29b12a-b61e-4ec6-b7ea-abab46cf27f6', '3', '2024-07-10T11:13:50.056', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f57ea1e3-4fec-4b16-a15d-9513a3a2901e', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-17a53f15-4a36-4450-abf5-387825a2434a', '3', '2024-05-08T12:35:10.257', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f58cbd92-dbc6-4587-a184-e2d74c28e76b', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-f5231573-bc14-4862-a77b-d4c09e31d136', '1', '2025-02-10T12:21:25.729', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f5bac7d6-7e74-4671-8859-649b27ce2064', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-074abac7-fe4c-4908-aa7e-d72dacd94014', '3', '2025-01-06T06:10:14.646', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f61fd289-b324-46c6-a4cb-b7777c273efa', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-c03dadaf-4a89-42a1-8b36-847d34c8b337', '3', '2025-03-14T17:55:27.698', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f6a5aec7-12af-4a49-908f-57612f01b4b7', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-15731dcc-f552-4160-b5c5-99a9112ae341', '2', '2024-12-17T11:41:15.453', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f6ca62d3-5e73-4575-8f43-17d81c299177', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-c38a1e47-8271-43f2-aca3-bb7b2db33ad0', '3', '2024-05-21T13:41:44.645', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f757ef85-39fc-4ec5-8f9b-2793c3da4188', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-15731dcc-f552-4160-b5c5-99a9112ae341', '3', '2024-12-17T11:41:15.540', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f765955e-eb07-4ddd-a253-cc63dbfa2330', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-fe5bb833-fdd7-4416-81dd-f5b20107540f', '3', '2024-05-22T11:00:15.027', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f7962614-450f-499b-96fd-f786c34504e5', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-f78d8a42-fd40-4643-a737-cb85234737d6', '3', '2024-05-21T13:46:28.140', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f7c22352-1b96-423a-9d70-cc3d41057818', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35', '3', '2024-04-16T12:34:36.632', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f7f242ab-f87d-4e25-b8c5-3343856e4315', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-e7149e74-4856-4440-8d94-99f915731842', '3', '2024-09-04T16:42:57.469', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f80f95b0-e7da-40cb-889c-8b896f70b141', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-016c289e-75ae-4034-85db-94b1cff32233', '3', '2024-04-11T19:03:04.978', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f85e1b68-a930-4f78-8bbd-abe0a6c3e99f', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-f78d8a42-fd40-4643-a737-cb85234737d6', '3', '2025-01-06T06:12:36.293', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f89b1034-8f80-41f2-bec4-f1478b77df73', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-b1ebad5d-7fa4-4872-ba1f-13df498414c6', '3', '2024-05-07T18:54:40.367', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f9b3df70-c5af-408c-858b-014fab8d3a42', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-e7149e74-4856-4440-8d94-99f915731842', '3', '2024-09-04T16:42:57.394', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f9b58916-19f3-4aa2-96d4-2f0f886f4852', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-0ee32ded-100b-4505-95c4-65d5e9b3d93c', '1', '2024-04-16T12:38:51.454', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-fa1e2661-3ea8-4d5e-b827-524d76d059a8', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-6f8c6196-a29a-4b1d-89f4-9eec372e2c87', '3', '2024-05-07T12:41:46.325', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-fa355077-6bc5-4aca-bfa5-c586a46d45c8', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-86816d21-ec0c-4dc6-ad1d-8edea9716d09', '3', '2024-05-07T18:51:16.882', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-fa97d64d-bb3c-4347-a6c4-5ab71750b9fa', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-50b321e8-2886-4b99-a921-cbb5baacf1ac', '3', '2024-07-06T11:53:34.981', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-faf41eb4-8c51-44a7-97db-9131b060de28', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-e7149e74-4856-4440-8d94-99f915731842', '3', '2024-09-04T16:42:57.487', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-fb434577-f04c-4427-aa5f-34a1ee7db9e9', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-6f8c6196-a29a-4b1d-89f4-9eec372e2c87', '3', '2024-05-07T12:41:46.099', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-fb667386-7714-4d56-b3ef-840a847b9185', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '531500340-6d018c56-58b2-4277-a258-28707ae35cc7', '3', '2025-01-06T06:12:36.073', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-fbf49084-eb93-4016-ae7d-55e333255866', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-c03dadaf-4a89-42a1-8b36-847d34c8b337', '3', '2025-03-14T17:55:27.476', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-fcf109e3-abf8-4dc9-a32f-4ec2029547ff', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-63929fcc-e8f9-11ee-934c-fa163efa1f90', '3', '2024-04-17T22:01:41.053', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-fd947db5-6cf0-4620-a327-1b878d9c2b8c', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-439363cf-9c16-4b9e-8840-64bb093cbbd3', '3', '2024-06-24T16:40:08.599', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-fda3a2f6-0911-476d-9164-adcabbcdfbb7', '516761049-d19230c8-c355-4d67-9206-e8de189d5148', '531500340-902ff997-6669-40bd-9f82-d8aeb297f39b', '3', '2025-01-06T06:10:14.892', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-fe30d585-6435-4742-a374-26a839077621', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-94bb8cdb-75e0-4561-8af5-7c1107f0426d', '3', '2025-05-19T12:23:33.364', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-fef9a561-2b5b-4413-aeb7-5c799e9443fc', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-cbb13f49-17eb-40fd-80f2-8b7fba973361', '3', '2024-05-07T13:01:31.641', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ff05b02f-c22b-4611-9795-9589bd725e39', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-439363cf-9c16-4b9e-8840-64bb093cbbd3', '3', '2024-06-24T16:40:08.628', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ff1b3cb1-3918-4b2c-a8a4-91aba4bda466', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-f47ac10b-58cc-4372-a567-0e02b2c3d479', '3', '2024-04-17T22:01:39.348', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ff251a21-149a-42fb-94d1-6f3cac2e771a', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-f5231573-bc14-4862-a77b-d4c09e31d136', '3', '2025-02-10T12:21:25.663', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ff5378a3-bc16-4afc-8667-c266d07d3b38', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-7fb353f6-a56a-4622-a79d-dab0c178febf', '3', '2024-02-06T21:16:20.107', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ffb8af0d-0df3-422b-adb4-fea519347fb2', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-5d318e20-3c58-4944-b7ac-c29e26df5ac0', '1', '2024-05-07T14:26:42.428', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1bb7b638-a963-11f0-97b1-0242ac110003', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '0cc25fbaa8a011f097b10242ac110003', '1', '2025-10-15T当前时间（例如10:30:00.00）', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('30d54c7e-bea0-11f0-97b1-0242ac110003', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-aaa1-bbb1-ccc1-ddd1eee1fff1', '1', '2025-11-11 01:46:07', '');
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('6057f0a5-a962-11f0-97b1-0242ac110003', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '9d895362a89f11f097b10242ac110003', '1', '2025-10-15T当前时间（例如10:30:00.00）', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('62711ab8-bea1-11f0-97b1-0242ac110003', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-9999-aaaa-bbbb-ccccdddd0000', '1', '2025-11-11 01:54:39', '');
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('6276198a-bea1-11f0-97b1-0242ac110003', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-1111-2222-3333-444455556666', '3', '2025-11-11 01:54:39', '');
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('62761e63-bea1-11f0-97b1-0242ac110003', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-7777-8888-9999-0000aaaabbbb', '3', '2025-11-11 01:54:39', '');
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('869cc809-c062-11f0-97b1-0242ac110003', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-aaaa-bbbb-cccc-ddddeeeefff4', '1', '2025-11-13 07:29:44', '');
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('8a3117df-c062-11f0-97b1-0242ac110003', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-aaaa-bbbb-cccc-ddddeeeefff4', '1', '2025-11-13 07:29:50', '');
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('c70b8a70-bea1-11f0-97b1-0242ac110003', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-aaa1-bbb1-ccc1-ddd1eee1fff1', '1', '2025-11-11 01:57:28', '');
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('d13cf01a-be98-11f0-97b1-0242ac110003', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-9999-aaaa-bbbb-ccccdddd0000', '1', '2025-11-11 00:53:20', '');
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('d13e966c-be98-11f0-97b1-0242ac110003', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-1111-2222-3333-444455556666', '1', '2025-11-11 00:53:20', '');
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('d13e999d-be98-11f0-97b1-0242ac110003', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-7777-8888-9999-0000aaaabbbb', '1', '2025-11-11 00:53:20', '');
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('f3367909da2b11f097b10242ac110003', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', 'f7557758da2a11f097b10242ac110003', '1', '2025-12-16 03:04:34', NULL);
COMMIT;

-- ----------------------------
-- Table structure for st_roles
-- ----------------------------
DROP TABLE IF EXISTS `st_roles`;
CREATE TABLE `st_roles` (
  `id` varchar(255) NOT NULL COMMENT 'ID',
  `rolename` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `rolecode` varchar(255) DEFAULT NULL COMMENT '角色代码（1-超级管理员,2-教学秘书,3-教务处,4-学院负责人,5-系主任,6-专业负责人,7-程负责人,8-任课教师,9-助教,10-学生）',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `homename` varchar(255) DEFAULT NULL COMMENT '首页名称',
  `homeurl` varchar(255) DEFAULT NULL COMMENT '首页地址',
  `createtime` varchar(50) DEFAULT NULL COMMENT '创建日期',
  `isRP` varchar(255) DEFAULT NULL COMMENT '是否是负责人（组装名字时是否需要前缀）（1-是，0-否）',
  `by2` varchar(255) DEFAULT NULL COMMENT '备用字段2',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='系统_角色表\r\n';

-- ----------------------------
-- Records of st_roles
-- ----------------------------
BEGIN;
INSERT INTO `st_roles` (`id`, `rolename`, `rolecode`, `remark`, `homename`, `homeurl`, `createtime`, `isRP`, `by2`) VALUES ('516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '课程负责人', '7', '课程负责人', '课程负责人首页', '/homes/coursemanagerhome', '2024-02-06T21:01:27.898', '0', '1');
INSERT INTO `st_roles` (`id`, `rolename`, `rolecode`, `remark`, `homename`, `homeurl`, `createtime`, `isRP`, `by2`) VALUES ('516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '教务处', '3', '教务处', '教务处首页', '/homes/academicaffairshome', '2024-02-06T20:58:03.116', '0', '1');
INSERT INTO `st_roles` (`id`, `rolename`, `rolecode`, `remark`, `homename`, `homeurl`, `createtime`, `isRP`, `by2`) VALUES ('516761049-812a1a24-365d-4697-ab16-b0093b983624', '超级管理员', '1', '超级管理员', '超级管理员', '/homes/superadminhome', '2024-02-06T20:56:09.898', '0', '1');
INSERT INTO `st_roles` (`id`, `rolename`, `rolecode`, `remark`, `homename`, `homeurl`, `createtime`, `isRP`, `by2`) VALUES ('516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '专业负责人', '6', '专业负责人', '专业负责人首页', '/homes/professionhome', '2024-02-06T21:00:32.915', '1', '1');
INSERT INTO `st_roles` (`id`, `rolename`, `rolecode`, `remark`, `homename`, `homeurl`, `createtime`, `isRP`, `by2`) VALUES ('516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '助教', '9', '助教', '助教首页', '/homes/assistanthome', '2024-02-06T21:02:24.708', '0', '1');
INSERT INTO `st_roles` (`id`, `rolename`, `rolecode`, `remark`, `homename`, `homeurl`, `createtime`, `isRP`, `by2`) VALUES ('516761049-9a741546-0b55-489b-9dc4-31789ee07153', '学生', '10', '学生', '学生首页', '/homes/studenthome', '2024-03-28T13:54:07.698', NULL, '1');
INSERT INTO `st_roles` (`id`, `rolename`, `rolecode`, `remark`, `homename`, `homeurl`, `createtime`, `isRP`, `by2`) VALUES ('516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '任课教师', '8', '任课教师', '任课教师首页', '/homes/courseteacherhome', '2024-02-06T21:01:54.200', '0', '1');
INSERT INTO `st_roles` (`id`, `rolename`, `rolecode`, `remark`, `homename`, `homeurl`, `createtime`, `isRP`, `by2`) VALUES ('516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '系主任', '5', '系主任', '系主任首页', '/homes/departmenthome', '2024-02-06T21:00:05.869', '1', '1');
INSERT INTO `st_roles` (`id`, `rolename`, `rolecode`, `remark`, `homename`, `homeurl`, `createtime`, `isRP`, `by2`) VALUES ('516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '教学秘书', '2', '教学秘书', '教学秘书首页', '/homes/secretariatehome', '2024-02-06T20:57:34.340', '1', '1');
INSERT INTO `st_roles` (`id`, `rolename`, `rolecode`, `remark`, `homename`, `homeurl`, `createtime`, `isRP`, `by2`) VALUES ('516761049-d19230c8-c355-4d67-9206-e8de189d5148', '实验教师', '11', '实验教师', '实验教师首页', '/homes/courseteacherhome', '2025-01-06T06:10:14.623', NULL, '1');
INSERT INTO `st_roles` (`id`, `rolename`, `rolecode`, `remark`, `homename`, `homeurl`, `createtime`, `isRP`, `by2`) VALUES ('516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '学院负责人', '4', '学院负责人', '学院负责人首页', '/homes/deanhome', '2024-02-06T20:59:41.042', '1', '1');
INSERT INTO `st_roles` (`id`, `rolename`, `rolecode`, `remark`, `homename`, `homeurl`, `createtime`, `isRP`, `by2`) VALUES ('516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '实验秘书', '12', '实验秘书', '实验秘书首页', '/homes/superadminhome', '2025-01-06T06:12:35.756', NULL, '1');
COMMIT;

-- ----------------------------
-- Table structure for st_roleuser
-- ----------------------------
DROP TABLE IF EXISTS `st_roleuser`;
CREATE TABLE `st_roleuser` (
  `id` varchar(255) NOT NULL COMMENT 'ID',
  `userid` varchar(255) DEFAULT NULL COMMENT '用户ID',
  `roleid` varchar(255) DEFAULT NULL COMMENT '角色ID',
  `obsid` varchar(255) DEFAULT NULL COMMENT '机构id',
  `obsdeep` int DEFAULT NULL COMMENT '机构层级',
  `createtime` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `termid` varchar(255) DEFAULT NULL COMMENT '备用字段1',
  PRIMARY KEY (`id`),
  KEY `fk_roleuser_roleid` (`roleid`),
  KEY `fk_roleuser_userid` (`userid`),
  CONSTRAINT `fk_roleuser_roleid` FOREIGN KEY (`roleid`) REFERENCES `st_roles` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_roleuser_userid` FOREIGN KEY (`userid`) REFERENCES `st_users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='系统_角色用户关系表\r\n';

-- ----------------------------
-- Records of st_roleuser
-- ----------------------------
BEGIN;
INSERT INTO `st_roleuser` (`id`, `userid`, `roleid`, `obsid`, `obsdeep`, `createtime`, `termid`) VALUES ('3', '1', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '237675254', 1, NULL, '0');
INSERT INTO `st_roleuser` (`id`, `userid`, `roleid`, `obsid`, `obsdeep`, `createtime`, `termid`) VALUES ('4', '1', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '237675254', 1, NULL, '0');
INSERT INTO `st_roleuser` (`id`, `userid`, `roleid`, `obsid`, `obsdeep`, `createtime`, `termid`) VALUES ('8', '1', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '237675254', 1, NULL, '0');
INSERT INTO `st_roleuser` (`id`, `userid`, `roleid`, `obsid`, `obsdeep`, `createtime`, `termid`) VALUES ('999', '1', '516761049-e8473133-b6c3-43e4-9a72-9e5a265ba9e4', '237675254', 1, NULL, '0');
COMMIT;

-- ----------------------------
-- Table structure for st_users
-- ----------------------------
DROP TABLE IF EXISTS `st_users`;
CREATE TABLE `st_users` (
  `id` varchar(255) NOT NULL COMMENT 'ID',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名称\n',
  `loginname` varchar(255) DEFAULT NULL COMMENT '登录用户名',
  `pwd` varchar(255) DEFAULT NULL COMMENT '登录密码',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `status` varchar(255) DEFAULT NULL COMMENT '状态码（1-正常，0-停用）',
  `catelog` varchar(10) DEFAULT NULL COMMENT '人员类别（1-学生,2-老师）',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `headimage` varchar(255) DEFAULT NULL COMMENT '头像文件',
  `createtime` varchar(255) DEFAULT NULL COMMENT '创建日期',
  `by1` varchar(255) DEFAULT NULL COMMENT '备用字段1',
  `by2` varchar(255) DEFAULT NULL COMMENT '备用字段2',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='系统_用户表（老师，学生）';

-- ----------------------------
-- Records of st_users
-- ----------------------------
BEGIN;
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('1', '教师测试账号', 'test1', '123456', '11111111', '1', '2', '', NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for student_ideology_achievements
-- ----------------------------
DROP TABLE IF EXISTS `student_ideology_achievements`;
CREATE TABLE `student_ideology_achievements` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `user_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '学生ID，外键引用cm_users.id',
  `course_id` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '课程ID，外键引用cm_course.id',
  `v_id` varchar(64) NOT NULL COMMENT '思政价值id，外键引用v_ideology_value.id',
  `classroom_id` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '课堂id，外键引用cm_classroom.id',
  `value_count` int NOT NULL DEFAULT '0' COMMENT '思政个数',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  KEY `v_id` (`v_id`),
  KEY `classroom_id` (`classroom_id`),
  KEY `idx_student_ideology_course_objective` (`user_id`,`classroom_id`,`v_id`),
  CONSTRAINT `student_ideology_achievements_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `st_users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `student_ideology_achievements_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `cm_course` (`id`) ON DELETE CASCADE,
  CONSTRAINT `student_ideology_achievements_ibfk_3` FOREIGN KEY (`v_id`) REFERENCES `v_ideology_value` (`id`) ON DELETE CASCADE,
  CONSTRAINT `student_ideology_achievements_ibfk_4` FOREIGN KEY (`classroom_id`) REFERENCES `cm_classroom` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生思政评价结果表';

-- ----------------------------
-- Records of student_ideology_achievements
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sync_job
-- ----------------------------
DROP TABLE IF EXISTS `sync_job`;
CREATE TABLE `sync_job` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `uniqueCode` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` varchar(64) NOT NULL,
  `state` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ready complete failed',
  `context` mediumtext,
  `res` mediumtext,
  `msg` varchar(1000) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sj_index_t_uc` (`uniqueCode`,`type`)
) ENGINE=InnoDB AUTO_INCREMENT=271 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sync_job
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tm_quetype
-- ----------------------------
DROP TABLE IF EXISTS `tm_quetype`;
CREATE TABLE `tm_quetype` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(12) NOT NULL COMMENT 'course=课程 classroom=课堂',
  `indexId` varchar(64) NOT NULL COMMENT 'type=course 课程id   type=classroom课堂id',
  `queTypeId` varchar(32) NOT NULL COMMENT '0201 0202 ',
  `name` varchar(32) NOT NULL,
  `status` int DEFAULT NULL COMMENT '0=无效 1=有效',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tq_index_ii_qti` (`indexId`,`queTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=733 DEFAULT CHARSET=utf8mb3 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='模块四: 题库试题类型和课程课堂关系表, 保存各个课堂课程所展示的有效的试题类型';

-- ----------------------------
-- Records of tm_quetype
-- ----------------------------
BEGIN;
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (601, 'classroom', '292104772-d16ed082-70e6-4204-9e3d-2656607a6644', '0201', '单选题', 1);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (602, 'classroom', '292104772-d16ed082-70e6-4204-9e3d-2656607a6644', '0202', '多选题', 1);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (603, 'classroom', '292104772-d16ed082-70e6-4204-9e3d-2656607a6644', '0203', '判断题', 1);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (604, 'classroom', '292104772-d16ed082-70e6-4204-9e3d-2656607a6644', '0204', '填空题', 1);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (605, 'classroom', '292104772-d16ed082-70e6-4204-9e3d-2656607a6644', '0205', '简答题', 1);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (606, 'classroom', '292104772-d16ed082-70e6-4204-9e3d-2656607a6644', '0206', '编程题', 1);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (607, 'classroom', '292104772-d16ed082-70e6-4204-9e3d-2656607a6644', '0207', '预留1', 0);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (608, 'classroom', '292104772-d16ed082-70e6-4204-9e3d-2656607a6644', '0208', '预留2', 0);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (609, 'classroom', '292104772-d16ed082-70e6-4204-9e3d-2656607a6644', '0209', '预留3', 0);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (610, 'classroom', '292104772-d16ed082-70e6-4204-9e3d-2656607a6644', '0210', '预留4', 0);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (611, 'classroom', '292104772-d16ed082-70e6-4204-9e3d-2656607a6644', '0211', '预留5', 0);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (612, 'course', '1508003654-38863549-2b73-4bd3-a66c-3d7e050145e9', '0201', '单选题', 1);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (613, 'course', '1508003654-38863549-2b73-4bd3-a66c-3d7e050145e9', '0202', '多选题', 1);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (614, 'course', '1508003654-38863549-2b73-4bd3-a66c-3d7e050145e9', '0203', '判断题', 1);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (615, 'course', '1508003654-38863549-2b73-4bd3-a66c-3d7e050145e9', '0204', '填空题', 1);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (616, 'course', '1508003654-38863549-2b73-4bd3-a66c-3d7e050145e9', '0205', '简答题', 1);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (617, 'course', '1508003654-38863549-2b73-4bd3-a66c-3d7e050145e9', '0206', '编程题', 1);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (618, 'course', '1508003654-38863549-2b73-4bd3-a66c-3d7e050145e9', '0207', '预留1', 0);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (619, 'course', '1508003654-38863549-2b73-4bd3-a66c-3d7e050145e9', '0208', '预留2', 0);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (620, 'course', '1508003654-38863549-2b73-4bd3-a66c-3d7e050145e9', '0209', '预留3', 0);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (621, 'course', '1508003654-38863549-2b73-4bd3-a66c-3d7e050145e9', '0210', '预留4', 0);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (622, 'course', '1508003654-38863549-2b73-4bd3-a66c-3d7e050145e9', '0211', '预留5', 0);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (723, 'classroom', '292104772-ac328351-8f7c-4940-bb06-2c2fd77cfd15', '0202', '多选题', 1);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (724, 'classroom', '292104772-ac328351-8f7c-4940-bb06-2c2fd77cfd15', '0203', '判断题', 1);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (725, 'classroom', '292104772-ac328351-8f7c-4940-bb06-2c2fd77cfd15', '0204', '填空题', 1);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (726, 'classroom', '292104772-ac328351-8f7c-4940-bb06-2c2fd77cfd15', '0205', '简答题', 1);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (727, 'classroom', '292104772-ac328351-8f7c-4940-bb06-2c2fd77cfd15', '0206', '编程题', 1);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (728, 'classroom', '292104772-ac328351-8f7c-4940-bb06-2c2fd77cfd15', '0207', '预留1', 0);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (729, 'classroom', '292104772-ac328351-8f7c-4940-bb06-2c2fd77cfd15', '0208', '预留2', 0);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (730, 'classroom', '292104772-ac328351-8f7c-4940-bb06-2c2fd77cfd15', '0209', '预留3', 0);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (731, 'classroom', '292104772-ac328351-8f7c-4940-bb06-2c2fd77cfd15', '0210', '预留4', 0);
INSERT INTO `tm_quetype` (`id`, `type`, `indexId`, `queTypeId`, `name`, `status`) VALUES (732, 'classroom', '292104772-ac328351-8f7c-4940-bb06-2c2fd77cfd15', '0211', '预留5', 0);
COMMIT;

-- ----------------------------
-- Table structure for tm_quetype_template
-- ----------------------------
DROP TABLE IF EXISTS `tm_quetype_template`;
CREATE TABLE `tm_quetype_template` (
  `id` varchar(64) DEFAULT NULL COMMENT 'questionTypeId',
  `name` varchar(64) DEFAULT NULL COMMENT 'typeName',
  `status` int DEFAULT NULL COMMENT '0=无效 1=有效'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='模块四: 试题题库类型模版表, 用于在课程课堂题库初始化的时候设定默认题库试题类型';

-- ----------------------------
-- Records of tm_quetype_template
-- ----------------------------
BEGIN;
INSERT INTO `tm_quetype_template` (`id`, `name`, `status`) VALUES ('0201', '单选题', 1);
INSERT INTO `tm_quetype_template` (`id`, `name`, `status`) VALUES ('0202', '多选题', 1);
INSERT INTO `tm_quetype_template` (`id`, `name`, `status`) VALUES ('0203', '判断题', 1);
INSERT INTO `tm_quetype_template` (`id`, `name`, `status`) VALUES ('0204', '填空题', 1);
INSERT INTO `tm_quetype_template` (`id`, `name`, `status`) VALUES ('0205', '简答题', 1);
INSERT INTO `tm_quetype_template` (`id`, `name`, `status`) VALUES ('0206', '编程题', 1);
INSERT INTO `tm_quetype_template` (`id`, `name`, `status`) VALUES ('0207', '预留1', 0);
INSERT INTO `tm_quetype_template` (`id`, `name`, `status`) VALUES ('0208', '预留2', 0);
INSERT INTO `tm_quetype_template` (`id`, `name`, `status`) VALUES ('0209', '预留3', 0);
INSERT INTO `tm_quetype_template` (`id`, `name`, `status`) VALUES ('0210', '预留4', 0);
INSERT INTO `tm_quetype_template` (`id`, `name`, `status`) VALUES ('0211', '预留5', 0);
COMMIT;

-- ----------------------------
-- Table structure for tm_testquelib
-- ----------------------------
DROP TABLE IF EXISTS `tm_testquelib`;
CREATE TABLE `tm_testquelib` (
  `id` varchar(64) NOT NULL COMMENT '标识',
  `libNo` bigint NOT NULL AUTO_INCREMENT,
  `questionTypeId` varchar(64) DEFAULT NULL COMMENT '题型Id',
  `courseId` varchar(64) DEFAULT NULL COMMENT '课程Id',
  `classroomId` varchar(64) DEFAULT NULL COMMENT '课堂id',
  `unitId` varchar(64) DEFAULT NULL COMMENT '知识单元Id',
  `catelogId` varchar(64) DEFAULT NULL COMMENT '试题分类Id',
  `content` mediumtext COMMENT '试题内容',
  `analysis` mediumtext COMMENT '整体解析',
  `markpicture` varchar(255) DEFAULT NULL COMMENT '标题图片',
  `creatorId` varchar(64) DEFAULT NULL COMMENT '提交人Id',
  `creator` varchar(255) DEFAULT NULL COMMENT '提交人',
  `createTime` varchar(19) DEFAULT NULL COMMENT '提交时间',
  `status` int DEFAULT NULL COMMENT '状态  0-草稿，1-待审核，2-入库，3-弃用，4=锁定',
  `creatorUnit` varchar(255) DEFAULT NULL COMMENT '提交人单位',
  `title` varchar(255) DEFAULT NULL COMMENT '题目',
  `differenceLevel` decimal(8,2) DEFAULT NULL COMMENT '区分度',
  `difficultyLevel` decimal(8,2) DEFAULT NULL COMMENT '难度',
  `guesssLevel` decimal(8,2) DEFAULT NULL COMMENT '猜测度',
  `answer` mediumtext COMMENT '填写文本框  简答题、填空题、编程题时使用',
  `codelang` varchar(255) DEFAULT NULL COMMENT '编程语言',
  `libTypeId` varchar(64) DEFAULT NULL COMMENT '类型Id  1-作业题库，2-考试题库',
  `libTypeName` varchar(255) DEFAULT NULL COMMENT '类型',
  `lastWritorId` varchar(64) DEFAULT NULL COMMENT '最近修改人Id',
  `lastWritor` varchar(255) DEFAULT NULL COMMENT '最近修改人',
  `lastWriteTime` varchar(19) DEFAULT NULL COMMENT '最近修改时间',
  `saveCatelog` varchar(2) DEFAULT NULL COMMENT '存放位置',
  `codelangversion` varchar(255) DEFAULT NULL COMMENT '编程语言版本',
  `publistimes` int DEFAULT NULL COMMENT '发布时间',
  `plananswertime` int DEFAULT NULL COMMENT '计划应答时间',
  `answerStandard_0204` text COMMENT '答案标准',
  `scoreStandard_0204` text COMMENT '得分标准',
  `sortVal` bigint DEFAULT '0' COMMENT '当前试题的排序值',
  `libConfig` varchar(512) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '当前题目的其他配置',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `tm_testquelib_index_libNo` (`libNo`),
  KEY `fk_testquelib_courseid` (`courseId`),
  CONSTRAINT `fk_testquelib_courseid` FOREIGN KEY (`courseId`) REFERENCES `cm_course` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=972 DEFAULT CHARSET=utf8mb3 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='模块四: 课程课堂题库表';

-- ----------------------------
-- Records of tm_testquelib
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tm_testquelib_answer
-- ----------------------------
DROP TABLE IF EXISTS `tm_testquelib_answer`;
CREATE TABLE `tm_testquelib_answer` (
  `id` varchar(64) NOT NULL COMMENT '标识',
  `libId` varchar(64) DEFAULT NULL COMMENT '试题Id',
  `isAnswer` bit(1) DEFAULT NULL COMMENT '是否答案  1.不是  2.是',
  `itemPicture` varchar(255) DEFAULT NULL COMMENT '选项图片',
  `itemOption` varchar(255) DEFAULT NULL COMMENT '选项',
  `itemContent` mediumtext COMMENT '选项内容',
  `itemAnalysis` mediumtext COMMENT '选项解析',
  `sortVal` int DEFAULT '0' COMMENT '排序值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='模块四: 课程课堂题库试题答案表';

-- ----------------------------
-- Records of tm_testquelib_answer
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tm_testquelib_kwa
-- ----------------------------
DROP TABLE IF EXISTS `tm_testquelib_kwa`;
CREATE TABLE `tm_testquelib_kwa` (
  `id` varchar(64) NOT NULL COMMENT '标识',
  `libId` varchar(64) DEFAULT NULL COMMENT '试题Id',
  `kwaId` varchar(64) DEFAULT NULL COMMENT 'KWAId',
  `dataValue` decimal(38,2) DEFAULT NULL COMMENT '数值',
  `kwaName` varchar(255) DEFAULT NULL COMMENT 'KWA',
  `vid` bigint DEFAULT NULL COMMENT '价值的id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `ttk_index_li_ki` (`libId`,`kwaId`),
  KEY `ttk_index_ki` (`kwaId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='模块四: 课程课堂题库试题测评点表';

-- ----------------------------
-- Records of tm_testquelib_kwa
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tm_testquelib_samples
-- ----------------------------
DROP TABLE IF EXISTS `tm_testquelib_samples`;
CREATE TABLE `tm_testquelib_samples` (
  `id` varchar(64) NOT NULL COMMENT '标识',
  `libId` varchar(64) DEFAULT NULL COMMENT '试题Id',
  `inputParams` mediumtext COMMENT '输入参数',
  `outputValue` mediumtext COMMENT '输出结果',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='测过样例表';

-- ----------------------------
-- Records of tm_testquelib_samples
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tm_testquelib_v
-- ----------------------------
DROP TABLE IF EXISTS `tm_testquelib_v`;
CREATE TABLE `tm_testquelib_v` (
  `id` varchar(64) NOT NULL,
  `libId` varchar(64) NOT NULL,
  `vid` varchar(64) NOT NULL,
  `vname` varchar(64) NOT NULL COMMENT '仅仅作为保存标识，不跟随更新',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tt_index_li_vi` (`libId`,`vid`),
  KEY `ttv_index_li` (`libId`),
  KEY `tt_index_vid` (`vid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tm_testquelib_v
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for v_ideology_value
-- ----------------------------
DROP TABLE IF EXISTS `v_ideology_value`;
CREATE TABLE `v_ideology_value` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `vname` varchar(125) NOT NULL COMMENT '名称（类型名或价值名）',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `course_id` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '课程ID',
  `parent_id` varchar(64) DEFAULT NULL COMMENT '父级ID，自关联：为 NULL 表示一级类型',
  `leaf` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否叶子节点（1=具体价值, 0=价值类型）',
  `level` int NOT NULL DEFAULT '1' COMMENT '层级',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_course_id` (`course_id`),
  KEY `idx_parent_id` (`parent_id`),
  CONSTRAINT `fk_ideology_course_id` FOREIGN KEY (`course_id`) REFERENCES `cm_course` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_ideology_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `v_ideology_value` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of v_ideology_value
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
