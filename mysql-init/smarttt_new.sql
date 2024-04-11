/*
 Navicat Premium Data Transfer

 Source Server         : new
 Source Server Type    : MySQL
 Source Server Version : 50744 (5.7.44)
 Source Host           : 157.0.19.2:10757
 Source Schema         : smarttt_new

 Target Server Type    : MySQL
 Target Server Version : 50744 (5.7.44)
 File Encoding         : 65001

 Date: 11/04/2024 20:10:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cm_ability
-- ----------------------------
DROP TABLE IF EXISTS `cm_ability`;
CREATE TABLE `cm_ability` (
  `id` varchar(255) NOT NULL,
  `pid` varchar(255) DEFAULT NULL,
  `orderno` mediumtext,
  `abilitydeep` mediumtext,
  `name` varchar(255) DEFAULT NULL,
  `datavalue` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `levelcode` varchar(255) DEFAULT NULL,
  `importantlevel` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cm_ability
-- ----------------------------
BEGIN;
INSERT INTO `cm_ability` (`id`, `pid`, `orderno`, `abilitydeep`, `name`, `datavalue`, `remark`, `levelcode`, `importantlevel`) VALUES ('1683875063-07447ce1-0531-498a-9308-ec8090fbdcc2', '1683875063-bc1315f1-3ed0-42e9-afe8-42ca3b4f0e6c', '1', '3', '合理假设能力', '0.00', 'edfefe98cnjj', '101.106.101', NULL);
INSERT INTO `cm_ability` (`id`, `pid`, `orderno`, `abilitydeep`, `name`, `datavalue`, `remark`, `levelcode`, `importantlevel`) VALUES ('1683875063-0757d916-8ee7-41cd-ac6e-a79044f087ee', '1683875063-93dd999e-c03e-4d78-86b2-df653e36bac2', '3', '3', '诠释解释能力', '0.00', 'edfefe98cnjj', '101.102.103', NULL);
INSERT INTO `cm_ability` (`id`, `pid`, `orderno`, `abilitydeep`, `name`, `datavalue`, `remark`, `levelcode`, `importantlevel`) VALUES ('1683875063-0ca11314-0206-4bf9-a1ef-e7f193c9206b', '1683875063-efc94e55-d185-428f-8fa7-fe5e13e7d807', '4', '3', '综合运用能力', '0.00', 'edfefe98cnjj', '101.103.104', NULL);
INSERT INTO `cm_ability` (`id`, `pid`, `orderno`, `abilitydeep`, `name`, `datavalue`, `remark`, `levelcode`, `importantlevel`) VALUES ('1683875063-27ae610c-4676-4181-932a-908b087ab763', '1683875063-d5ab9ade-6092-4f1f-b4a3-e37b3f011d61', '4', '2', '分析层次', '0.00', 'edfefe98cnjj', '101.104', NULL);
INSERT INTO `cm_ability` (`id`, `pid`, `orderno`, `abilitydeep`, `name`, `datavalue`, `remark`, `levelcode`, `importantlevel`) VALUES ('1683875063-30fb6c65-180e-4e50-8b04-460120faa5b4', '1683875063-47875eb6-6c0b-4a86-a95d-cbbf3dc59bda', '3', '1', '未命名能力(3)', NULL, '', '102.103', NULL);
INSERT INTO `cm_ability` (`id`, `pid`, `orderno`, `abilitydeep`, `name`, `datavalue`, `remark`, `levelcode`, `importantlevel`) VALUES ('1683875063-47875eb6-6c0b-4a86-a95d-cbbf3dc59bda', '0', '2', '0', '技术类型', '0.00', 'edfefe98cnjj', '102', NULL);
INSERT INTO `cm_ability` (`id`, `pid`, `orderno`, `abilitydeep`, `name`, `datavalue`, `remark`, `levelcode`, `importantlevel`) VALUES ('1683875063-4c64ff2e-6256-47c8-b5f3-3fbfa99db3e5', '1683875063-27ae610c-4676-4181-932a-908b087ab763', '1', '3', '比较分析能力', '0.00', 'edfefe98cnjj', '101.104.101', NULL);
INSERT INTO `cm_ability` (`id`, `pid`, `orderno`, `abilitydeep`, `name`, `datavalue`, `remark`, `levelcode`, `importantlevel`) VALUES ('1683875063-4fe8297a-4561-45cd-a931-66a98d0cae33', '1683875063-47875eb6-6c0b-4a86-a95d-cbbf3dc59bda', '2', '1', '未命名能力(2)', NULL, '', '102.102', NULL);
INSERT INTO `cm_ability` (`id`, `pid`, `orderno`, `abilitydeep`, `name`, `datavalue`, `remark`, `levelcode`, `importantlevel`) VALUES ('1683875063-50b5e904-95ba-462a-af8f-47a1c08d4857', '1683875063-27ae610c-4676-4181-932a-908b087ab763', '2', '3', '解构归因能力', '0.00', 'edfefe98cnjj', '101.104.102', NULL);
INSERT INTO `cm_ability` (`id`, `pid`, `orderno`, `abilitydeep`, `name`, `datavalue`, `remark`, `levelcode`, `importantlevel`) VALUES ('1683875063-5192a31e-ad94-4199-ac23-3f9e21b18611', '1683875063-d5ab9ade-6092-4f1f-b4a3-e37b3f011d61', '1', '2', '记忆层次', '0.00', 'edfefe98cnjj', '101.101', NULL);
INSERT INTO `cm_ability` (`id`, `pid`, `orderno`, `abilitydeep`, `name`, `datavalue`, `remark`, `levelcode`, `importantlevel`) VALUES ('1683875063-52c3b1e5-aa5d-44d1-ac17-b1a1ec8c18f5', '1683875063-5192a31e-ad94-4199-ac23-3f9e21b18611', '2', '3', '再现复述能力', '0.00', 'edfefe98cnjj', '101.101.102', NULL);
INSERT INTO `cm_ability` (`id`, `pid`, `orderno`, `abilitydeep`, `name`, `datavalue`, `remark`, `levelcode`, `importantlevel`) VALUES ('1683875063-66f5204b-b27a-4e13-ac16-e014c533a1c6', '1683875063-d5ab9ade-6092-4f1f-b4a3-e37b3f011d61', '5', '2', '评价层次', '0.00', 'edfefe98cnjj', '101.105', NULL);
INSERT INTO `cm_ability` (`id`, `pid`, `orderno`, `abilitydeep`, `name`, `datavalue`, `remark`, `levelcode`, `importantlevel`) VALUES ('1683875063-67a90bcd-3244-4e8b-b8fe-ac18b18b1f25', '1683875063-66f5204b-b27a-4e13-ac16-e014c533a1c6', '2', '3', '评论评价能力', '0.00', 'hcsudn111', '101.105.102', NULL);
INSERT INTO `cm_ability` (`id`, `pid`, `orderno`, `abilitydeep`, `name`, `datavalue`, `remark`, `levelcode`, `importantlevel`) VALUES ('1683875063-67c69925-d484-463a-8393-6ae2ed6de705', '1683875063-93dd999e-c03e-4d78-86b2-df653e36bac2', '2', '3', '识图绘图能力', '0.00', 'hcsudn111', '101.102.102', NULL);
INSERT INTO `cm_ability` (`id`, `pid`, `orderno`, `abilitydeep`, `name`, `datavalue`, `remark`, `levelcode`, `importantlevel`) VALUES ('1683875063-680243d7-41a6-4e29-a8c8-def8e41a5f1c', '1683875063-66f5204b-b27a-4e13-ac16-e014c533a1c6', '1', '3', '校验验证能力', '0.00', 'hcsudn111', '101.105.101', NULL);
INSERT INTO `cm_ability` (`id`, `pid`, `orderno`, `abilitydeep`, `name`, `datavalue`, `remark`, `levelcode`, `importantlevel`) VALUES ('1683875063-72f5da55-5056-4e3d-9ff1-6734ebb16494', '1683875063-93dd999e-c03e-4d78-86b2-df653e36bac2', '4', '3', '代码解析能力', '0.00', 'hcsudn111', '101.102.104', NULL);
INSERT INTO `cm_ability` (`id`, `pid`, `orderno`, `abilitydeep`, `name`, `datavalue`, `remark`, `levelcode`, `importantlevel`) VALUES ('1683875063-93dd999e-c03e-4d78-86b2-df653e36bac2', '1683875063-d5ab9ade-6092-4f1f-b4a3-e37b3f011d61', '2', '2', '理解层次', '0.00', 'hcsudn111', '101.102', NULL);
INSERT INTO `cm_ability` (`id`, `pid`, `orderno`, `abilitydeep`, `name`, `datavalue`, `remark`, `levelcode`, `importantlevel`) VALUES ('1683875063-ac13bd77-a58b-4d61-90ac-b24a3a3e445e', '1683875063-5192a31e-ad94-4199-ac23-3f9e21b18611', '1', '3', '回忆再认能力', '0.00', 'hcsudn111', '101.101.101', NULL);
INSERT INTO `cm_ability` (`id`, `pid`, `orderno`, `abilitydeep`, `name`, `datavalue`, `remark`, `levelcode`, `importantlevel`) VALUES ('1683875063-bc1315f1-3ed0-42e9-afe8-42ca3b4f0e6c', '1683875063-d5ab9ade-6092-4f1f-b4a3-e37b3f011d61', '6', '2', '创造层次', '0.00', 'hcsudn111', '101.106', NULL);
INSERT INTO `cm_ability` (`id`, `pid`, `orderno`, `abilitydeep`, `name`, `datavalue`, `remark`, `levelcode`, `importantlevel`) VALUES ('1683875063-bed053db-68a7-46ea-aa1b-42ca032ea830', '1683875063-bc1315f1-3ed0-42e9-afe8-42ca3b4f0e6c', '2', '3', '设计建构能力', '0.00', 'hcsudn111', '101.106.102', NULL);
INSERT INTO `cm_ability` (`id`, `pid`, `orderno`, `abilitydeep`, `name`, `datavalue`, `remark`, `levelcode`, `importantlevel`) VALUES ('1683875063-d5ab9ade-6092-4f1f-b4a3-e37b3f011d61', '0', '1', '1', '认知类型', '0.00', '基于布鲁姆教育目标分类学的六个层次划分，对每个层次进行了一定程度的细化', '101', NULL);
INSERT INTO `cm_ability` (`id`, `pid`, `orderno`, `abilitydeep`, `name`, `datavalue`, `remark`, `levelcode`, `importantlevel`) VALUES ('1683875063-d6b9f61d-29fa-4a7e-a4ff-fd3612d53fdd', '1683875063-efc94e55-d185-428f-8fa7-fe5e13e7d807', '3', '3', '数模转化能力', '0.00', 'ddmiodd99ss', '101.103.103', NULL);
INSERT INTO `cm_ability` (`id`, `pid`, `orderno`, `abilitydeep`, `name`, `datavalue`, `remark`, `levelcode`, `importantlevel`) VALUES ('1683875063-d864b240-b07d-494c-bd94-82f5657b247c', '1683875063-93dd999e-c03e-4d78-86b2-df653e36bac2', '1', '3', '概念识辨能力', '0.00', 'ddmiodd99ss', '101.102.101', NULL);
INSERT INTO `cm_ability` (`id`, `pid`, `orderno`, `abilitydeep`, `name`, `datavalue`, `remark`, `levelcode`, `importantlevel`) VALUES ('1683875063-dc05062a-ec85-4071-bd0b-9c368486f8af', '1683875063-efc94e55-d185-428f-8fa7-fe5e13e7d807', '2', '3', '数学计算能力', '0.00', 'ddmiodd99ss', '101.103.102', NULL);
INSERT INTO `cm_ability` (`id`, `pid`, `orderno`, `abilitydeep`, `name`, `datavalue`, `remark`, `levelcode`, `importantlevel`) VALUES ('1683875063-efc94e55-d185-428f-8fa7-fe5e13e7d807', '1683875063-d5ab9ade-6092-4f1f-b4a3-e37b3f011d61', '3', '2', '应用层次', '0.00', 'ddmiodd99ss', '101.103', NULL);
INSERT INTO `cm_ability` (`id`, `pid`, `orderno`, `abilitydeep`, `name`, `datavalue`, `remark`, `levelcode`, `importantlevel`) VALUES ('1683875063-f97cf4a1-d42e-416f-b580-eea40f0cfb20', '1683875063-27ae610c-4676-4181-932a-908b087ab763', '3', '3', '数学建模能力', '0.00', 'ddmiodd99ss', '101.104.103', NULL);
INSERT INTO `cm_ability` (`id`, `pid`, `orderno`, `abilitydeep`, `name`, `datavalue`, `remark`, `levelcode`, `importantlevel`) VALUES ('1683875063-fba8d1d2-d553-4831-9c9b-ab530b5d503f', '1683875063-efc94e55-d185-428f-8fa7-fe5e13e7d807', '1', '3', '直接应用能力', '0.00', 'ddmiodd99ss', '101.103.101', NULL);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cm_class
-- ----------------------------
BEGIN;
INSERT INTO `cm_class` (`id`, `obsid`, `classname`, `grade`, `remark`) VALUES ('123123123', '1597933787-f107935e-1c30-4354-8c59-c1bc5a62d21e', '计算机23-2班', '2023', NULL);
INSERT INTO `cm_class` (`id`, `obsid`, `classname`, `grade`, `remark`) VALUES ('1948150749-d6b66167-93ea-4d70-b421-f69f703e587f', '1597933787-748aca38-95d5-4132-a361-03591b045ace', '计算机-21-1班', '2021', '');
INSERT INTO `cm_class` (`id`, `obsid`, `classname`, `grade`, `remark`) VALUES ('2357323', '876757', '计算机22-1班', '2022', NULL);
INSERT INTO `cm_class` (`id`, `obsid`, `classname`, `grade`, `remark`) VALUES ('66666', '4456565', '计算机23-1班', '2023', NULL);
COMMIT;

-- ----------------------------
-- Table structure for cm_course
-- ----------------------------
DROP TABLE IF EXISTS `cm_course`;
CREATE TABLE `cm_course` (
  `id` varchar(64) NOT NULL,
  `courseChineseName` varchar(255) DEFAULT NULL,
  `courseEnglishName` varchar(255) DEFAULT NULL,
  `courseCode` varchar(255) DEFAULT NULL,
  `credit` decimal(8,2) DEFAULT NULL,
  `dutyManId` varchar(255) DEFAULT NULL,
  `dutyMan` varchar(255) DEFAULT NULL,
  `verNo` varchar(255) DEFAULT NULL,
  `hourInClass` decimal(8,2) DEFAULT NULL,
  `hourOutside` decimal(8,2) DEFAULT NULL,
  `electiveOrRequired` varchar(255) DEFAULT NULL,
  `theoryOfflineHour` decimal(8,2) DEFAULT NULL,
  `theoryOnlineHour` decimal(8,2) DEFAULT NULL,
  `experimentHour` decimal(8,2) DEFAULT NULL,
  `hourOnline` decimal(8,2) DEFAULT NULL,
  `hourOffline` decimal(8,2) DEFAULT NULL,
  `firstCourse` varchar(255) DEFAULT NULL,
  `professionalGrade` varchar(255) DEFAULT NULL,
  `summary` mediumtext,
  `learningResource` mediumtext,
  `markPicture` varchar(255) DEFAULT NULL COMMENT '服务器相对路径名称',
  `attachFileName` varchar(255) DEFAULT NULL COMMENT '服务器相对路径名称',
  `schooltermId` varchar(64) DEFAULT NULL,
  `markPictureUploadFileName` varchar(255) DEFAULT NULL COMMENT '下载使用',
  `questionNumber` varchar(255) DEFAULT NULL,
  `attachFileNameUploadFileName` varchar(255) DEFAULT NULL COMMENT '下载使用',
  `openTimes` int(11) DEFAULT NULL,
  `firstTime` varchar(255) DEFAULT NULL,
  `lastTime` varchar(255) DEFAULT NULL,
  `professionId` varchar(64) DEFAULT NULL,
  `professionName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cm_course
-- ----------------------------
BEGIN;
INSERT INTO `cm_course` (`id`, `courseChineseName`, `courseEnglishName`, `courseCode`, `credit`, `dutyManId`, `dutyMan`, `verNo`, `hourInClass`, `hourOutside`, `electiveOrRequired`, `theoryOfflineHour`, `theoryOnlineHour`, `experimentHour`, `hourOnline`, `hourOffline`, `firstCourse`, `professionalGrade`, `summary`, `learningResource`, `markPicture`, `attachFileName`, `schooltermId`, `markPictureUploadFileName`, `questionNumber`, `attachFileNameUploadFileName`, `openTimes`, `firstTime`, `lastTime`, `professionId`, `professionName`) VALUES ('2c918af681fa6ea7018209a505c30672', '机器人研发综合实践I', 'Robot R&D Practice I', '7347901', 4.00, '2c918af681fa6ea70182098682da00c8', '李志军', '', 0.00, 0.00, '', 0.00, 0.00, 0.00, 0.00, 0.00, '', '', '', '', '', '', NULL, '', NULL, '', NULL, NULL, NULL, '2c918af681fa6ea7018209a31be50640', NULL);
INSERT INTO `cm_course` (`id`, `courseChineseName`, `courseEnglishName`, `courseCode`, `credit`, `dutyManId`, `dutyMan`, `verNo`, `hourInClass`, `hourOutside`, `electiveOrRequired`, `theoryOfflineHour`, `theoryOnlineHour`, `experimentHour`, `hourOnline`, `hourOffline`, `firstCourse`, `professionalGrade`, `summary`, `learningResource`, `markPicture`, `attachFileName`, `schooltermId`, `markPictureUploadFileName`, `questionNumber`, `attachFileNameUploadFileName`, `openTimes`, `firstTime`, `lastTime`, `professionId`, `professionName`) VALUES ('2c918af681fa6ea7018209a505c30674', '机器人产业认知', 'Robot Industry Cognition', '7347801', 2.00, '2c918af681fa6ea70182098682da00c8', '李志军', '2022.9', 32.00, 0.00, '选修', 0.00, 0.00, 0.00, 0.00, 0.00, '', '大二、大三', '', '', '', '', NULL, '', NULL, '', NULL, NULL, NULL, '2c918af681fa6ea7018209a31be50640', NULL);
INSERT INTO `cm_course` (`id`, `courseChineseName`, `courseEnglishName`, `courseCode`, `credit`, `dutyManId`, `dutyMan`, `verNo`, `hourInClass`, `hourOutside`, `electiveOrRequired`, `theoryOfflineHour`, `theoryOnlineHour`, `experimentHour`, `hourOnline`, `hourOffline`, `firstCourse`, `professionalGrade`, `summary`, `learningResource`, `markPicture`, `attachFileName`, `schooltermId`, `markPictureUploadFileName`, `questionNumber`, `attachFileNameUploadFileName`, `openTimes`, `firstTime`, `lastTime`, `professionId`, `professionName`) VALUES ('2c918af682a012a80182a0a5893e09f6', '5G基础及应用', '', '7348401', NULL, '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', NULL);
INSERT INTO `cm_course` (`id`, `courseChineseName`, `courseEnglishName`, `courseCode`, `credit`, `dutyManId`, `dutyMan`, `verNo`, `hourInClass`, `hourOutside`, `electiveOrRequired`, `theoryOfflineHour`, `theoryOnlineHour`, `experimentHour`, `hourOnline`, `hourOffline`, `firstCourse`, `professionalGrade`, `summary`, `learningResource`, `markPicture`, `attachFileName`, `schooltermId`, `markPictureUploadFileName`, `questionNumber`, `attachFileNameUploadFileName`, `openTimes`, `firstTime`, `lastTime`, `professionId`, `professionName`) VALUES ('2c918af682a012a80182a0a5893e09f7', '5G工程及应用', '', '7348501', NULL, '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', NULL);
INSERT INTO `cm_course` (`id`, `courseChineseName`, `courseEnglishName`, `courseCode`, `credit`, `dutyManId`, `dutyMan`, `verNo`, `hourInClass`, `hourOutside`, `electiveOrRequired`, `theoryOfflineHour`, `theoryOnlineHour`, `experimentHour`, `hourOnline`, `hourOffline`, `firstCourse`, `professionalGrade`, `summary`, `learningResource`, `markPicture`, `attachFileName`, `schooltermId`, `markPictureUploadFileName`, `questionNumber`, `attachFileNameUploadFileName`, `openTimes`, `firstTime`, `lastTime`, `professionId`, `professionName`) VALUES ('2c918af682a012a80182bfd1cebf1162', '示例课程1', 'course 1', '123456', NULL, '2c918af682a012a80182bfc46e3f1037', '示例课程负责人', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', NULL);
INSERT INTO `cm_course` (`id`, `courseChineseName`, `courseEnglishName`, `courseCode`, `credit`, `dutyManId`, `dutyMan`, `verNo`, `hourInClass`, `hourOutside`, `electiveOrRequired`, `theoryOfflineHour`, `theoryOnlineHour`, `experimentHour`, `hourOnline`, `hourOffline`, `firstCourse`, `professionalGrade`, `summary`, `learningResource`, `markPicture`, `attachFileName`, `schooltermId`, `markPictureUploadFileName`, `questionNumber`, `attachFileNameUploadFileName`, `openTimes`, `firstTime`, `lastTime`, `professionId`, `professionName`) VALUES ('2c918af682a012a80182bfd1cebf1163', '示例课程2', 'course 2', '654321', NULL, '2c918af681fa6ea70182098682da00ce', '徐继宁', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', NULL);
INSERT INTO `cm_course` (`id`, `courseChineseName`, `courseEnglishName`, `courseCode`, `credit`, `dutyManId`, `dutyMan`, `verNo`, `hourInClass`, `hourOutside`, `electiveOrRequired`, `theoryOfflineHour`, `theoryOnlineHour`, `experimentHour`, `hourOnline`, `hourOffline`, `firstCourse`, `professionalGrade`, `summary`, `learningResource`, `markPicture`, `attachFileName`, `schooltermId`, `markPictureUploadFileName`, `questionNumber`, `attachFileNameUploadFileName`, `openTimes`, `firstTime`, `lastTime`, `professionId`, `professionName`) VALUES ('2c918af682a012a80182bfd4265b11f8', '示例课程1', 'course 1', '123456', NULL, '2c918af682a012a80182bfc46e3f1037', '示例课程负责人', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', NULL);
INSERT INTO `cm_course` (`id`, `courseChineseName`, `courseEnglishName`, `courseCode`, `credit`, `dutyManId`, `dutyMan`, `verNo`, `hourInClass`, `hourOutside`, `electiveOrRequired`, `theoryOfflineHour`, `theoryOnlineHour`, `experimentHour`, `hourOnline`, `hourOffline`, `firstCourse`, `professionalGrade`, `summary`, `learningResource`, `markPicture`, `attachFileName`, `schooltermId`, `markPictureUploadFileName`, `questionNumber`, `attachFileNameUploadFileName`, `openTimes`, `firstTime`, `lastTime`, `professionId`, `professionName`) VALUES ('2c918af682a012a80182bfdb201f1324', '示例课程1', 'course 1', '123456', 0.00, '2c918af682a012a80182bfc46e3f1037', '示例课程负责人', '', 32.00, 32.00, '选修', 20.00, 2.00, 10.00, 0.00, 0.00, '无', '一年级', '', '', '', '', NULL, '', NULL, '', NULL, NULL, NULL, '2c918af682a012a80182bfd7b8d812f2', NULL);
INSERT INTO `cm_course` (`id`, `courseChineseName`, `courseEnglishName`, `courseCode`, `credit`, `dutyManId`, `dutyMan`, `verNo`, `hourInClass`, `hourOutside`, `electiveOrRequired`, `theoryOfflineHour`, `theoryOnlineHour`, `experimentHour`, `hourOnline`, `hourOffline`, `firstCourse`, `professionalGrade`, `summary`, `learningResource`, `markPicture`, `attachFileName`, `schooltermId`, `markPictureUploadFileName`, `questionNumber`, `attachFileNameUploadFileName`, `openTimes`, `firstTime`, `lastTime`, `professionId`, `professionName`) VALUES ('2c918af682a012a80182bfdb201f1326', '示例课程2', 'course 2', '654321', 0.00, '2c918af681fa6ea70182098682da00ce', '徐继宁', '', 64.00, 60.00, '必修', 50.00, 4.00, 10.00, 0.00, 0.00, '高等数学', '三年级', '', '', '', '', NULL, '', NULL, '', NULL, NULL, NULL, '2c918af682a012a80182bfd7b8d812f2', NULL);
INSERT INTO `cm_course` (`id`, `courseChineseName`, `courseEnglishName`, `courseCode`, `credit`, `dutyManId`, `dutyMan`, `verNo`, `hourInClass`, `hourOutside`, `electiveOrRequired`, `theoryOfflineHour`, `theoryOnlineHour`, `experimentHour`, `hourOnline`, `hourOffline`, `firstCourse`, `professionalGrade`, `summary`, `learningResource`, `markPicture`, `attachFileName`, `schooltermId`, `markPictureUploadFileName`, `questionNumber`, `attachFileNameUploadFileName`, `openTimes`, `firstTime`, `lastTime`, `professionId`, `professionName`) VALUES ('8aea800182e80d000182e885e1ef0ce4', 'Python编程基础', 'Python Programing', '7303701', NULL, '2c918af681f6d8a30182480ed6290c1e,8aa09fa483cf20380183ee1028eb060e,2c918af681f6d8a30181fa09d7780130', '闫佳庆,翟维枫,雷振伍', '', NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, '', '', '', '', '', '', '', '', '', '', NULL, '', '', '2c918af681f6d8a30181fb7b0eb104b0', '');
INSERT INTO `cm_course` (`id`, `courseChineseName`, `courseEnglishName`, `courseCode`, `credit`, `dutyManId`, `dutyMan`, `verNo`, `hourInClass`, `hourOutside`, `electiveOrRequired`, `theoryOfflineHour`, `theoryOnlineHour`, `experimentHour`, `hourOnline`, `hourOffline`, `firstCourse`, `professionalGrade`, `summary`, `learningResource`, `markPicture`, `attachFileName`, `schooltermId`, `markPictureUploadFileName`, `questionNumber`, `attachFileNameUploadFileName`, `openTimes`, `firstTime`, `lastTime`, `professionId`, `professionName`) VALUES ('8aa09fa48a6e2413018b1845fbce03e8', '昇腾计算机视觉应用案例实践', 'Practice of Ascend Computer Vision Application', '7357401', 2.00, '8aa09fa48a6e2413018b182ae2580130', '姜放放', '1.0', 32.00, 0.00, '选修', 16.00, 0.00, 16.00, 0.00, 0.00, '线性代数、概率论与数理统计、高等数学、Python编程基础', '本科三年级、研究生一二年级', '本课程定位为人工智能实践类课程，课程以华为昇腾MindSpore框架为基础，学习和实现前馈神经网络、卷积神经网络等深度学习基础技术，以及物体识别、图像分割、图像生成、目标检测等计算机视觉的基础理论应用。结合企业应用案例，开展基于昇腾MindSpore框架的深度学习实践，以及基于计算机视觉产业应用视角的端到端训练与部署的多场景实践。完成课程学习后，学生可以胜任初级的人工智能算法工程师、深度学习工程师、图像算法工程师等岗位。', '《人工智能基础》，徐增林，高等教育出版社，2022年8月，ISBN：9787040585278\n《深度学习与MindSpore实践》，陈雷，清华大学出版社，2020年3月，ISBN：9787302546610\n', '9f90b3ec31ac47aa877ec9f403e52bf2.png', '335360edfc2944788e9c276b687bcebd.doc', NULL, '20201125-175106(WeLinkPC).png', NULL, 'DG7357401《昇腾计算机视觉应用案例实践》 课程类教学大纲.doc', NULL, NULL, NULL, '8aa09fa48a6e2413018b183ffbed0352', NULL);
INSERT INTO `cm_course` (`id`, `courseChineseName`, `courseEnglishName`, `courseCode`, `credit`, `dutyManId`, `dutyMan`, `verNo`, `hourInClass`, `hourOutside`, `electiveOrRequired`, `theoryOfflineHour`, `theoryOnlineHour`, `experimentHour`, `hourOnline`, `hourOffline`, `firstCourse`, `professionalGrade`, `summary`, `learningResource`, `markPicture`, `attachFileName`, `schooltermId`, `markPictureUploadFileName`, `questionNumber`, `attachFileNameUploadFileName`, `openTimes`, `firstTime`, `lastTime`, `professionId`, `professionName`) VALUES ('70b67a76673911ee9dbc9440c91732c2', '复制 - 昇腾计算机视觉应用案例实践', 'copy - Practice of Ascend Computer Vision Application', '7357401', 2.00, '8aa09fa48a6e2413018b182ae2580130', '姜放放', '1.0', 32.00, 0.00, '选修', 16.00, 0.00, 16.00, 0.00, 0.00, '线性代数、概率论与数理统计、高等数学、Python编程基础', '本科三年级、研究生一二年级', '本课程定位为人工智能实践类课程，课程以华为昇腾MindSpore框架为基础，学习和实现前馈神经网络、卷积神经网络等深度学习基础技术，以及物体识别、图像分割、图像生成、目标检测等计算机视觉的基础理论应用。结合企业应用案例，开展基于昇腾MindSpore框架的深度学习实践，以及基于计算机视觉产业应用视角的端到端训练与部署的多场景实践。完成课程学习后，学生可以胜任初级的人工智能算法工程师、深度学习工程师、图像算法工程师等岗位。', '《人工智能基础》，徐增林，高等教育出版社，2022年8月，ISBN：9787040585278\n《深度学习与MindSpore实践》，陈雷，清华大学出版社，2020年3月，ISBN：9787302546610\n', '9f90b3ec31ac47aa877ec9f403e52bf2.png', '335360edfc2944788e9c276b687bcebd.doc', NULL, '20201125-175106(WeLinkPC).png', NULL, 'DG7357401《昇腾计算机视觉应用案例实践》 课程类教学大纲.doc', NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for cm_course_ability
-- ----------------------------
DROP TABLE IF EXISTS `cm_course_ability`;
CREATE TABLE `cm_course_ability` (
  `id` varchar(64) NOT NULL,
  `pid` varchar(64) DEFAULT NULL,
  `orderNo` int(11) DEFAULT NULL,
  `courseId` varchar(64) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `datavlue` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cm_course_ability
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_course_keywords
-- ----------------------------
DROP TABLE IF EXISTS `cm_course_keywords`;
CREATE TABLE `cm_course_keywords` (
  `id` varchar(64) NOT NULL,
  `courseId` varchar(64) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `datavalue` decimal(8,2) DEFAULT NULL,
  `importantLevelId` varchar(64) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cm_course_keywords
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_course_kwa
-- ----------------------------
DROP TABLE IF EXISTS `cm_course_kwa`;
CREATE TABLE `cm_course_kwa` (
  `id` varchar(64) NOT NULL,
  `courseId` varchar(64) DEFAULT NULL,
  `sourceKWAId` varchar(64) DEFAULT NULL,
  `destKWAId` varchar(64) DEFAULT NULL,
  `datavalue` varchar(255) DEFAULT NULL,
  `sourceJson` varchar(255) DEFAULT NULL,
  `destJson` varchar(255) DEFAULT NULL,
  `linkJson` varchar(255) DEFAULT NULL,
  `sourceKWAName` varchar(255) DEFAULT NULL,
  `destKWAName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cm_course_kwa
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cm_course_usestate
-- ----------------------------
DROP TABLE IF EXISTS `cm_course_usestate`;
CREATE TABLE `cm_course_usestate` (
  `id` varchar(64) NOT NULL,
  `courseId` varchar(255) DEFAULT NULL,
  `testQuestionCount` varchar(255) DEFAULT NULL,
  `useTimes` int(11) DEFAULT NULL,
  `firstUseTime` varchar(19) DEFAULT NULL,
  `lastUseTime` varchar(19) DEFAULT NULL,
  `kwacount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

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
  `id` varchar(255) CHARACTER SET utf8 NOT NULL,
  `orderno` bigint(20) NOT NULL,
  `abilitydeep` bigint(20) NOT NULL,
  `levelcode` varchar(255) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `datavalue` varchar(255) DEFAULT NULL,
  `importantlevel` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `courseid` varchar(255) NOT NULL,
  PRIMARY KEY (`id`,`courseid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of cm_getability
-- ----------------------------
BEGIN;
INSERT INTO `cm_getability` (`id`, `orderno`, `abilitydeep`, `levelcode`, `name`, `datavalue`, `importantlevel`, `remark`, `courseid`) VALUES ('1683875063-07447ce1-0531-498a-9308-ec8090fbdcc2', 1, 3, '101.106.101', '合理假设能力', '0.00', NULL, 'edfefe98cnjj', '2c918af681fa6ea7018209a505c30672');
INSERT INTO `cm_getability` (`id`, `orderno`, `abilitydeep`, `levelcode`, `name`, `datavalue`, `importantlevel`, `remark`, `courseid`) VALUES ('1683875063-0757d916-8ee7-41cd-ac6e-a79044f087ee', 3, 3, '101.102.103', '诠释解释能力', '0.00', NULL, 'edfefe98cnjj', '2c918af681fa6ea7018209a505c30672');
INSERT INTO `cm_getability` (`id`, `orderno`, `abilitydeep`, `levelcode`, `name`, `datavalue`, `importantlevel`, `remark`, `courseid`) VALUES ('1683875063-0ca11314-0206-4bf9-a1ef-e7f193c9206b', 4, 3, '101.103.104', '综合运用能力', '0.00', NULL, 'edfefe98cnjj', '2c918af681fa6ea7018209a505c30672');
INSERT INTO `cm_getability` (`id`, `orderno`, `abilitydeep`, `levelcode`, `name`, `datavalue`, `importantlevel`, `remark`, `courseid`) VALUES ('1683875063-4c64ff2e-6256-47c8-b5f3-3fbfa99db3e5', 1, 3, '101.104.101', '比较分析能力', '0.00', NULL, 'edfefe98cnjj', '2c918af681fa6ea7018209a505c30672');
INSERT INTO `cm_getability` (`id`, `orderno`, `abilitydeep`, `levelcode`, `name`, `datavalue`, `importantlevel`, `remark`, `courseid`) VALUES ('1683875063-50b5e904-95ba-462a-af8f-47a1c08d4857', 2, 3, '101.104.102', '解构归因能力', '0.00', NULL, 'edfefe98cnjj', '2c918af681fa6ea7018209a505c30672');
INSERT INTO `cm_getability` (`id`, `orderno`, `abilitydeep`, `levelcode`, `name`, `datavalue`, `importantlevel`, `remark`, `courseid`) VALUES ('1683875063-67a90bcd-3244-4e8b-b8fe-ac18b18b1f25', 2, 3, '101.105.102', '评论评价能力', '0.00', NULL, 'hcsudn111', '2c918af681fa6ea7018209a505c30672');
INSERT INTO `cm_getability` (`id`, `orderno`, `abilitydeep`, `levelcode`, `name`, `datavalue`, `importantlevel`, `remark`, `courseid`) VALUES ('1683875063-67c69925-d484-463a-8393-6ae2ed6de705', 2, 3, '101.102.102', '识图绘图能力', '0.00', NULL, 'hcsudn111', '2c918af681fa6ea7018209a505c30672');
INSERT INTO `cm_getability` (`id`, `orderno`, `abilitydeep`, `levelcode`, `name`, `datavalue`, `importantlevel`, `remark`, `courseid`) VALUES ('1683875063-680243d7-41a6-4e29-a8c8-def8e41a5f1c', 1, 3, '101.105.101', '校验验证能力', '0.00', NULL, 'hcsudn111', '2c918af681fa6ea7018209a505c30672');
INSERT INTO `cm_getability` (`id`, `orderno`, `abilitydeep`, `levelcode`, `name`, `datavalue`, `importantlevel`, `remark`, `courseid`) VALUES ('1683875063-72f5da55-5056-4e3d-9ff1-6734ebb16494', 4, 3, '101.102.104', '代码解析能力', '0.00', NULL, 'hcsudn111', '2c918af681fa6ea7018209a505c30672');
INSERT INTO `cm_getability` (`id`, `orderno`, `abilitydeep`, `levelcode`, `name`, `datavalue`, `importantlevel`, `remark`, `courseid`) VALUES ('1683875063-ac13bd77-a58b-4d61-90ac-b24a3a3e445e', 1, 3, '101.101.101', '回忆再认能力', '0.00', NULL, 'hcsudn111', '2c918af681fa6ea7018209a505c30672');
INSERT INTO `cm_getability` (`id`, `orderno`, `abilitydeep`, `levelcode`, `name`, `datavalue`, `importantlevel`, `remark`, `courseid`) VALUES ('1683875063-bed053db-68a7-46ea-aa1b-42ca032ea830', 2, 3, '101.106.102', '设计建构能力', '0.00', NULL, 'hcsudn111', '2c918af681fa6ea7018209a505c30672');
INSERT INTO `cm_getability` (`id`, `orderno`, `abilitydeep`, `levelcode`, `name`, `datavalue`, `importantlevel`, `remark`, `courseid`) VALUES ('1683875063-d6b9f61d-29fa-4a7e-a4ff-fd3612d53fdd', 3, 3, '101.103.103', '数模转化能力', '0.00', NULL, 'ddmiodd99ss', '2c918af681fa6ea7018209a505c30672');
INSERT INTO `cm_getability` (`id`, `orderno`, `abilitydeep`, `levelcode`, `name`, `datavalue`, `importantlevel`, `remark`, `courseid`) VALUES ('1683875063-d864b240-b07d-494c-bd94-82f5657b247c', 1, 3, '101.102.101', '概念识辨能力', '0.00', NULL, 'ddmiodd99ss', '2c918af681fa6ea7018209a505c30672');
INSERT INTO `cm_getability` (`id`, `orderno`, `abilitydeep`, `levelcode`, `name`, `datavalue`, `importantlevel`, `remark`, `courseid`) VALUES ('1683875063-dc05062a-ec85-4071-bd0b-9c368486f8af', 2, 3, '101.103.102', '数学计算能力', '0.00', NULL, 'ddmiodd99ss', '2c918af681fa6ea7018209a505c30672');
INSERT INTO `cm_getability` (`id`, `orderno`, `abilitydeep`, `levelcode`, `name`, `datavalue`, `importantlevel`, `remark`, `courseid`) VALUES ('1683875063-f97cf4a1-d42e-416f-b580-eea40f0cfb20', 3, 3, '101.104.103', '数学建模能力', '0.00', NULL, 'ddmiodd99ss', '2c918af681fa6ea7018209a505c30672');
INSERT INTO `cm_getability` (`id`, `orderno`, `abilitydeep`, `levelcode`, `name`, `datavalue`, `importantlevel`, `remark`, `courseid`) VALUES ('1683875063-fba8d1d2-d553-4831-9c9b-ab530b5d503f', 1, 3, '101.103.101', '直接应用能力', '0.00', NULL, 'ddmiodd99ss', '2c918af681fa6ea7018209a505c30672');
COMMIT;

-- ----------------------------
-- Table structure for cm_keywords
-- ----------------------------
DROP TABLE IF EXISTS `cm_keywords`;
CREATE TABLE `cm_keywords` (
  `id` varchar(255) CHARACTER SET utf8 NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `datavalue` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `importantlevelid` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `remark` mediumtext CHARACTER SET utf8,
  `courseid` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of cm_keywords
-- ----------------------------
BEGIN;
INSERT INTO `cm_keywords` (`id`, `name`, `datavalue`, `importantlevelid`, `remark`, `courseid`) VALUES ('886985639-2b30864a-f536-4efa-8c87-fa42eb054178', '单片机外设模块扩展', '1.00', '9.9', '单片机外设模块扩展', '2c918af681fa6ea7018209a505c30672');
INSERT INTO `cm_keywords` (`id`, `name`, `datavalue`, `importantlevelid`, `remark`, `courseid`) VALUES ('886985639-95661409-7302-4268-b71f-3fb14639ab94', '中断系统', '1.0', '7.5', '中断系统', '2c918af681fa6ea7018209a505c30672');
INSERT INTO `cm_keywords` (`id`, `name`, `datavalue`, `importantlevelid`, `remark`, `courseid`) VALUES ('886985639-a7a55d89-cef5-4a06-99fe-46962d41f6d3', '伪指令集', '0.00', '5.0', '伪指令集', '2c918af681fa6ea7018209a505c30672');
INSERT INTO `cm_keywords` (`id`, `name`, `datavalue`, `importantlevelid`, `remark`, `courseid`) VALUES ('886985639-ab8c9ffb-a1db-49dc-b980-6dc26815297c', '串口并口通讯', '1.22', '6', '串口并口通讯', NULL);
INSERT INTO `cm_keywords` (`id`, `name`, `datavalue`, `importantlevelid`, `remark`, `courseid`) VALUES ('886985639-e7b158dd-0374-4fce-9ad0-751a848dbe2d', '单片机内部结构', '0.00', '3.33', '单片机内部结构', '2c918af681fa6ea7018209a505c30672');
COMMIT;

-- ----------------------------
-- Table structure for cm_kwadict
-- ----------------------------
DROP TABLE IF EXISTS `cm_kwadict`;
CREATE TABLE `cm_kwadict` (
  `id` varchar(255) CHARACTER SET utf8 NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `keywordid` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `abilityid` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `keywordname` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `abilityname` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `courseid` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `datavalue` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_kwadict_keywordid` (`keywordid`),
  KEY `fk_kwadict_abilityid` (`abilityid`),
  CONSTRAINT `fk_kwadict_abilityid` FOREIGN KEY (`abilityid`) REFERENCES `cm_getability` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_kwadict_keywordid` FOREIGN KEY (`keywordid`) REFERENCES `cm_keywords` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of cm_kwadict
-- ----------------------------
BEGIN;
INSERT INTO `cm_kwadict` (`id`, `name`, `keywordid`, `abilityid`, `keywordname`, `abilityname`, `courseid`, `datavalue`, `status`) VALUES ('952187020-4467a846-394f-4892-9ef1-60e22a3eda5d', '单片机外设模块扩展-解构归因能力', '886985639-2b30864a-f536-4efa-8c87-fa42eb054178', '1683875063-50b5e904-95ba-462a-af8f-47a1c08d4857', '单片机外设模块扩展', '解构归因能力', '2c918af681fa6ea7018209a505c30672', '5.20', NULL);
INSERT INTO `cm_kwadict` (`id`, `name`, `keywordid`, `abilityid`, `keywordname`, `abilityname`, `courseid`, `datavalue`, `status`) VALUES ('952187020-d92f8b6d-38dd-4a84-9064-fa1078b4c0e5', '中断系统-评论评价能力', '886985639-95661409-7302-4268-b71f-3fb14639ab94', '1683875063-67a90bcd-3244-4e8b-b8fe-ac18b18b1f25', '中断系统', '评论评价能力', '2c918af681fa6ea7018209a505c30672', '6.00', NULL);
INSERT INTO `cm_kwadict` (`id`, `name`, `keywordid`, `abilityid`, `keywordname`, `abilityname`, `courseid`, `datavalue`, `status`) VALUES ('952187020-ea0f5ccd-d2c6-4d03-82af-6399cb4b1cb7', '伪指令集-数模转化能力', '886985639-a7a55d89-cef5-4a06-99fe-46962d41f6d3', '1683875063-d6b9f61d-29fa-4a7e-a4ff-fd3612d53fdd', '伪指令集', '数模转化能力', '2c918af681fa6ea7018209a505c30672', '3.34', NULL);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专业表';

-- ----------------------------
-- Records of cm_profession
-- ----------------------------
BEGIN;
INSERT INTO `cm_profession` (`id`, `obsid`, `proname`, `procode`, `reachpercent`, `remark`, `createtime`) VALUES ('1948150749-5727a386-2224-4ec4-a916-05d15e090203', '1597933787-c3496eb6-e126-497c-a5b4-dde9b9404295', '人工智能专业', '085666', '1', '666', '2024-03-18T20:56:39.256');
INSERT INTO `cm_profession` (`id`, `obsid`, `proname`, `procode`, `reachpercent`, `remark`, `createtime`) VALUES ('23724576', '7267267352', '计算机科学与技术专业', '085404', '0.8', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for cm_term
-- ----------------------------
DROP TABLE IF EXISTS `cm_term`;
CREATE TABLE `cm_term` (
  `id` varchar(255) NOT NULL,
  `termname` varchar(255) DEFAULT NULL COMMENT '学期',
  `begindate` varchar(255) DEFAULT NULL COMMENT '起始日期',
  `enddate` varchar(255) DEFAULT NULL COMMENT '结束日期',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `iscurrentterm` varchar(255) DEFAULT NULL COMMENT '是否是当前学期',
  `createtime` varchar(50) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学期表';

-- ----------------------------
-- Records of cm_term
-- ----------------------------
BEGIN;
INSERT INTO `cm_term` (`id`, `termname`, `begindate`, `enddate`, `remark`, `iscurrentterm`, `createtime`) VALUES ('1653708435-00f38ff9-f009-40ea-9fc3-9e9a47f6f73c', '2024年秋季学期', '2026-02-26', '2026-07-20', '2026年春季学期', '0', NULL);
INSERT INTO `cm_term` (`id`, `termname`, `begindate`, `enddate`, `remark`, `iscurrentterm`, `createtime`) VALUES ('1653708435-827a0dea-d7b8-40e0-af8a-877ac6e230f8', '2027年春季学期', '2026-02-26', '2026-07-20', '2027年春季学期', '0', '2024-03-14T22:40:49.981');
INSERT INTO `cm_term` (`id`, `termname`, `begindate`, `enddate`, `remark`, `iscurrentterm`, `createtime`) VALUES ('2c918af681fa6ea70182099b47ce0546', '2022年秋季学期', '2022-08-19', '2023-01-30', '2022年秋季学期', '0', '2024-03-14');
INSERT INTO `cm_term` (`id`, `termname`, `begindate`, `enddate`, `remark`, `iscurrentterm`, `createtime`) VALUES ('2c918af682a012a80182bfe95e0e1450', '2023年春季学期', '2023-02-27', '2023-07-21', '123123', '0', '2024-03-14');
INSERT INTO `cm_term` (`id`, `termname`, `begindate`, `enddate`, `remark`, `iscurrentterm`, `createtime`) VALUES ('8aa09fa48a6e2413018b183e04c00320', '2023年秋季学期', '2023-09-11', '2024-01-12', '2023年秋季学期', '1', '2024-03-14');
COMMIT;

-- ----------------------------
-- Table structure for cm_term_course
-- ----------------------------
DROP TABLE IF EXISTS `cm_term_course`;
CREATE TABLE `cm_term_course` (
  `id` varchar(64) NOT NULL,
  `termId` varchar(64) DEFAULT NULL,
  `courseId` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cm_term_course
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sm_obs
-- ----------------------------
DROP TABLE IF EXISTS `sm_obs`;
CREATE TABLE `sm_obs` (
  `id` varchar(255) NOT NULL COMMENT '组织机构ID',
  `pid` varchar(255) DEFAULT NULL COMMENT '父节点ID',
  `orderno` int(50) DEFAULT NULL COMMENT '顺序号',
  `obsdeep` int(50) DEFAULT NULL COMMENT '深度',
  `obsname` varchar(255) DEFAULT NULL COMMENT '组织名称',
  `obspath` varchar(255) DEFAULT NULL COMMENT '全路径',
  `levelcode` varchar(255) DEFAULT NULL COMMENT '层级码',
  `createtime` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织机构表';

-- ----------------------------
-- Records of sm_obs
-- ----------------------------
BEGIN;
INSERT INTO `sm_obs` (`id`, `pid`, `orderno`, `obsdeep`, `obsname`, `obspath`, `levelcode`, `createtime`, `remark`) VALUES ('1597933787-5e545291-9e08-4f57-a109-a3689b65b7b1', '237675254', 5, 2, '机械学院', NULL, '101.105', '2024-04-08T21:07:49.068', '');
INSERT INTO `sm_obs` (`id`, `pid`, `orderno`, `obsdeep`, `obsname`, `obspath`, `levelcode`, `createtime`, `remark`) VALUES ('1597933787-748aca38-95d5-4132-a361-03591b045ace', '7267267352', 4, 5, '计算机-21-1班', NULL, '101.101.101.101.104', '2024-03-25T16:00:08.142', '');
INSERT INTO `sm_obs` (`id`, `pid`, `orderno`, `obsdeep`, `obsname`, `obspath`, `levelcode`, `createtime`, `remark`) VALUES ('1597933787-7670a89c-0c46-4795-b6fe-b62fe6ee4dd4', '78364836', 2, 3, '未命名节点(3)', NULL, '101.102.102', '2024-03-16T16:37:53.026', '');
INSERT INTO `sm_obs` (`id`, `pid`, `orderno`, `obsdeep`, `obsname`, `obspath`, `levelcode`, `createtime`, `remark`) VALUES ('1597933787-ac667504-fe54-40ef-b394-70e5deba9fad', '0', 2, 1, '其他人员管理', NULL, '102', '2024-03-19T17:10:49.085', '');
INSERT INTO `sm_obs` (`id`, `pid`, `orderno`, `obsdeep`, `obsname`, `obspath`, `levelcode`, `createtime`, `remark`) VALUES ('1597933787-c3496eb6-e126-497c-a5b4-dde9b9404295', '4355323', 2, 4, '人工智能专业', NULL, '101.101.101.103', '2024-03-18T20:56:39.225', '666');
INSERT INTO `sm_obs` (`id`, `pid`, `orderno`, `obsdeep`, `obsname`, `obspath`, `levelcode`, `createtime`, `remark`) VALUES ('1597933787-c99cc2b0-74a6-444e-a9c7-36dba4519f7c', '1597933787-d4076872-8734-49bb-a8a1-4524e56996d9', 1, 3, '未命名节点(10)', NULL, '101.103.101', '2024-03-18T13:21:25.673', '');
INSERT INTO `sm_obs` (`id`, `pid`, `orderno`, `obsdeep`, `obsname`, `obspath`, `levelcode`, `createtime`, `remark`) VALUES ('1597933787-d16e6e17-dded-43b2-bcd6-4389a21483df', '0', 3, 1, '外聘人员管理', NULL, '103', '2024-03-19T17:10:59.549', '');
INSERT INTO `sm_obs` (`id`, `pid`, `orderno`, `obsdeep`, `obsname`, `obspath`, `levelcode`, `createtime`, `remark`) VALUES ('1597933787-d4076872-8734-49bb-a8a1-4524e56996d9', '237675254', 3, 2, '马克思主义学院', NULL, '101.103', '2024-02-23T17:17:37.173', '马克思主义学院');
INSERT INTO `sm_obs` (`id`, `pid`, `orderno`, `obsdeep`, `obsname`, `obspath`, `levelcode`, `createtime`, `remark`) VALUES ('1597933787-e64a0d8c-bfe8-4a82-8cbe-0983367b91ff', '237675254', 4, 2, '文法学院', NULL, '101.104', '2024-03-28T19:03:05.946', '');
INSERT INTO `sm_obs` (`id`, `pid`, `orderno`, `obsdeep`, `obsname`, `obspath`, `levelcode`, `createtime`, `remark`) VALUES ('1597933787-f107935e-1c30-4354-8c59-c1bc5a62d21e', '7267267352', 3, 5, '计算机23-2班', NULL, '101.101.101.101.104', '2024-03-23T20:25:53.025', '');
INSERT INTO `sm_obs` (`id`, `pid`, `orderno`, `obsdeep`, `obsname`, `obspath`, `levelcode`, `createtime`, `remark`) VALUES ('1597933787-fc07ee8f-3949-47e1-9b88-45f599b6db59', '1597933787-5f93f6ad-a647-4b5b-aa2d-45550fde7ba6', 1, 5, '未命名节点(4)', NULL, '101.101.102.101.101', '2024-03-16T15:55:27.142', '');
INSERT INTO `sm_obs` (`id`, `pid`, `orderno`, `obsdeep`, `obsname`, `obspath`, `levelcode`, `createtime`, `remark`) VALUES ('237675254', '0', 1, 1, '北方工业大学', '', '101', NULL, NULL);
INSERT INTO `sm_obs` (`id`, `pid`, `orderno`, `obsdeep`, `obsname`, `obspath`, `levelcode`, `createtime`, `remark`) VALUES ('328468276', '237675254', 1, 2, '电气与信息学院', NULL, '101.101', NULL, '电气与信息学院');
INSERT INTO `sm_obs` (`id`, `pid`, `orderno`, `obsdeep`, `obsname`, `obspath`, `levelcode`, `createtime`, `remark`) VALUES ('4355323', '328468276', 1, 3, '计算机系', NULL, '101.101.101', NULL, '计算机系');
INSERT INTO `sm_obs` (`id`, `pid`, `orderno`, `obsdeep`, `obsname`, `obspath`, `levelcode`, `createtime`, `remark`) VALUES ('4456565', '7267267352', 2, 5, '计算机23-1班', NULL, '101.101.101.101', NULL, '计算机1班');
INSERT INTO `sm_obs` (`id`, `pid`, `orderno`, `obsdeep`, `obsname`, `obspath`, `levelcode`, `createtime`, `remark`) VALUES ('545453', '78364836', 1, 3, '控制工程系', NULL, '101.102.101', NULL, '控制工程系');
INSERT INTO `sm_obs` (`id`, `pid`, `orderno`, `obsdeep`, `obsname`, `obspath`, `levelcode`, `createtime`, `remark`) VALUES ('7267267352', '4355323', 1, 4, '计算机科学与技术专业', NULL, NULL, NULL, NULL);
INSERT INTO `sm_obs` (`id`, `pid`, `orderno`, `obsdeep`, `obsname`, `obspath`, `levelcode`, `createtime`, `remark`) VALUES ('78364836', '237675254', 2, 2, '电控学院', NULL, '101.102', NULL, NULL);
INSERT INTO `sm_obs` (`id`, `pid`, `orderno`, `obsdeep`, `obsname`, `obspath`, `levelcode`, `createtime`, `remark`) VALUES ('876757', '7267267352', 1, 5, '计算机22-1班', NULL, NULL, NULL, NULL);
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
  PRIMARY KEY (`id`),
  KEY `fk_usersid_stu` (`usersid`),
  CONSTRAINT `fk_usersid_stu` FOREIGN KEY (`usersid`) REFERENCES `st_users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生表';

-- ----------------------------
-- Records of sm_student
-- ----------------------------
BEGIN;
INSERT INTO `sm_student` (`id`, `obsid`, `usersid`, `stuno`, `classno`, `proname`, `proid`, `schoolname`, `collegesname`, `status`, `submittime`, `isselfreg`, `createtime`) VALUES ('22', '4456565', '2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sm_student` (`id`, `obsid`, `usersid`, `stuno`, `classno`, `proname`, `proid`, `schoolname`, `collegesname`, `status`, `submittime`, `isselfreg`, `createtime`) VALUES ('267536372-18a62399-4baf-4539-82dd-abe64aa48134', '4355323', '508110840-81f560ad-054c-4802-9e7e-579e0b3c2eff', 'A03180347', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-03-20T21:46:13.464');
INSERT INTO `sm_student` (`id`, `obsid`, `usersid`, `stuno`, `classno`, `proname`, `proid`, `schoolname`, `collegesname`, `status`, `submittime`, `isselfreg`, `createtime`) VALUES ('267536372-24ecd871-0526-433c-b39f-9cd2d82dd10b', '4355323', '508110840-2e458cb4-393a-484c-8b65-265c69ffcde8', 'A03180347', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-03-20T21:46:09.506');
INSERT INTO `sm_student` (`id`, `obsid`, `usersid`, `stuno`, `classno`, `proname`, `proid`, `schoolname`, `collegesname`, `status`, `submittime`, `isselfreg`, `createtime`) VALUES ('267536372-4f80a796-cd26-451e-b642-b7a9e80bc059', '4355323', '508110840-76af8fd9-43f9-4d19-98af-c43dcb7cc086', 'A03180347', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-03-20T21:46:10.878');
INSERT INTO `sm_student` (`id`, `obsid`, `usersid`, `stuno`, `classno`, `proname`, `proid`, `schoolname`, `collegesname`, `status`, `submittime`, `isselfreg`, `createtime`) VALUES ('267536372-689bad10-d48d-4e70-9a70-2cacef63136a', '4355323', '508110840-ae98311f-ee05-4a2b-80ae-b3e6c5224ee8', 'A03180347', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-03-20T21:46:06.177');
INSERT INTO `sm_student` (`id`, `obsid`, `usersid`, `stuno`, `classno`, `proname`, `proid`, `schoolname`, `collegesname`, `status`, `submittime`, `isselfreg`, `createtime`) VALUES ('267536372-6924e1a4-7a05-4102-b5bf-377c5377f044', '4355323', '508110840-a50e235d-f645-454c-8952-ce9c5cac48da', 'A03180347', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-03-20T21:46:02.972');
INSERT INTO `sm_student` (`id`, `obsid`, `usersid`, `stuno`, `classno`, `proname`, `proid`, `schoolname`, `collegesname`, `status`, `submittime`, `isselfreg`, `createtime`) VALUES ('267536372-69fefcdd-7995-4095-a229-1a1655112ee9', '4355323', '508110840-c4c1088a-3724-41ee-b372-0c767fb4cdb2', 'A03180347', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-03-20T21:46:08.828');
INSERT INTO `sm_student` (`id`, `obsid`, `usersid`, `stuno`, `classno`, `proname`, `proid`, `schoolname`, `collegesname`, `status`, `submittime`, `isselfreg`, `createtime`) VALUES ('267536372-6c2f40fd-815f-4db9-98b5-7ab4da570865', '4355323', '508110840-e5eed268-b730-4025-9666-7295d1b74a16', 'A03180347', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-03-20T21:46:07.518');
INSERT INTO `sm_student` (`id`, `obsid`, `usersid`, `stuno`, `classno`, `proname`, `proid`, `schoolname`, `collegesname`, `status`, `submittime`, `isselfreg`, `createtime`) VALUES ('267536372-70b4439a-711f-433f-b64c-6ea917b51f78', '4355323', '508110840-06446b9e-2576-4b05-8112-71a4ae7951b5', 'A03180347', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-03-20T21:46:04.504');
INSERT INTO `sm_student` (`id`, `obsid`, `usersid`, `stuno`, `classno`, `proname`, `proid`, `schoolname`, `collegesname`, `status`, `submittime`, `isselfreg`, `createtime`) VALUES ('267536372-7c7fc427-bfe9-46b6-9678-d2f73e609183', '4355323', '508110840-6c14295b-7775-4bce-a8b2-0bce04ea5034', 'A03180347', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-03-04T20:59:46.759');
INSERT INTO `sm_student` (`id`, `obsid`, `usersid`, `stuno`, `classno`, `proname`, `proid`, `schoolname`, `collegesname`, `status`, `submittime`, `isselfreg`, `createtime`) VALUES ('267536372-8a92b640-b38d-4075-a51a-818fc169ca5c', '4355323', '508110840-d9d20c1e-b8f4-4acc-837f-9d4c1d95d48b', 'A03180347', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-03-20T21:46:14.166');
INSERT INTO `sm_student` (`id`, `obsid`, `usersid`, `stuno`, `classno`, `proname`, `proid`, `schoolname`, `collegesname`, `status`, `submittime`, `isselfreg`, `createtime`) VALUES ('267536372-9f8008d6-b05a-4b94-bb35-9b941dd18bd8', '4355323', '508110840-44575f76-227d-4413-b624-40f30380eeaa', 'A03180347', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-03-20T21:46:12.833');
INSERT INTO `sm_student` (`id`, `obsid`, `usersid`, `stuno`, `classno`, `proname`, `proid`, `schoolname`, `collegesname`, `status`, `submittime`, `isselfreg`, `createtime`) VALUES ('267536372-a4ab7ed4-b4b8-49bd-a581-f5d6d9864159', '4355323', '508110840-742fec1d-d002-4ea0-a817-594c0076a346', 'A03180347', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-03-20T21:46:11.542');
INSERT INTO `sm_student` (`id`, `obsid`, `usersid`, `stuno`, `classno`, `proname`, `proid`, `schoolname`, `collegesname`, `status`, `submittime`, `isselfreg`, `createtime`) VALUES ('267536372-ad0b2cac-612f-4c26-8864-4ec14cb61bd9', '4355323', '508110840-07666af8-603d-404f-a16b-2d052ac984ea', '123546', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-03-20T21:29:12.135');
INSERT INTO `sm_student` (`id`, `obsid`, `usersid`, `stuno`, `classno`, `proname`, `proid`, `schoolname`, `collegesname`, `status`, `submittime`, `isselfreg`, `createtime`) VALUES ('267536372-b1397183-edb5-439f-823a-f923a9b0414e', '4355323', '508110840-ec43c492-8f34-4d76-aeae-c475576bd7a5', 'A03180347', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-03-20T21:46:10.182');
INSERT INTO `sm_student` (`id`, `obsid`, `usersid`, `stuno`, `classno`, `proname`, `proid`, `schoolname`, `collegesname`, `status`, `submittime`, `isselfreg`, `createtime`) VALUES ('267536372-bbb7b455-0feb-452e-9975-4d9cfc395c9f', '4355323', '508110840-f9e82fcc-ecd3-4201-b97a-99d2a563ea07', 'A03180347', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-03-20T21:46:05.449');
INSERT INTO `sm_student` (`id`, `obsid`, `usersid`, `stuno`, `classno`, `proname`, `proid`, `schoolname`, `collegesname`, `status`, `submittime`, `isselfreg`, `createtime`) VALUES ('267536372-dbbd9644-21e8-4cc0-84ba-6fa6b39949c2', '4355323', '508110840-932c3104-f70b-4d79-a2af-7b8e2115ad72', 'A03180347', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-03-20T21:46:06.872');
INSERT INTO `sm_student` (`id`, `obsid`, `usersid`, `stuno`, `classno`, `proname`, `proid`, `schoolname`, `collegesname`, `status`, `submittime`, `isselfreg`, `createtime`) VALUES ('267536372-ef08e1ad-376b-401b-812a-0ea6efedbd0b', '4355323', '508110840-7fc5c01e-7046-4203-ad54-ef7043230d3d', 'A03180347', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-03-20T21:46:08.193');
INSERT INTO `sm_student` (`id`, `obsid`, `usersid`, `stuno`, `classno`, `proname`, `proid`, `schoolname`, `collegesname`, `status`, `submittime`, `isselfreg`, `createtime`) VALUES ('267536372-f6d0b89f-0827-4293-b408-a80e999f73c3', '4355323', '508110840-8ba549bf-e304-4196-be47-9ab4aaab9aa1', 'A03180347', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-03-20T21:46:12.181');
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
  PRIMARY KEY (`id`),
  KEY `fk_usersid` (`usersid`),
  CONSTRAINT `fk_usersid` FOREIGN KEY (`usersid`) REFERENCES `st_users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师表';

-- ----------------------------
-- Records of sm_teacher
-- ----------------------------
BEGIN;
INSERT INTO `sm_teacher` (`id`, `obsid`, `usersid`, `jobno`, `title`, `oftenclassroomid`, `createtime`) VALUES ('1051170049-31d2e47e-abc7-4839-b828-73d14a28dfa0', '4355323', '508110840-13b8a6db-e4a6-4ea5-b2ce-34f87ceaf2aa', 'A03180347', NULL, NULL, '2024-03-20T22:34:32.125');
INSERT INTO `sm_teacher` (`id`, `obsid`, `usersid`, `jobno`, `title`, `oftenclassroomid`, `createtime`) VALUES ('1051170049-51bf0584-052a-43b6-b8eb-2abdb717a81c', '4355323', '508110840-f0b30f7c-29f0-4a67-8a89-12bf445ccea9', '209374', NULL, NULL, '2024-04-02T17:23:37.859');
INSERT INTO `sm_teacher` (`id`, `obsid`, `usersid`, `jobno`, `title`, `oftenclassroomid`, `createtime`) VALUES ('1051170049-7fc40f30-90fa-43c4-9105-ca4576b50c8f', '4355323', '508110840-42c64cd1-4925-4755-b17a-51ff26a7a8ec', '209374', NULL, NULL, '2024-03-31T17:21:41.404');
INSERT INTO `sm_teacher` (`id`, `obsid`, `usersid`, `jobno`, `title`, `oftenclassroomid`, `createtime`) VALUES ('1051170049-8ecbd611-6d24-4278-b0e5-2f49483e0e0e', '4355323', '508110840-d38805d7-163e-410b-a00a-e29db59d8829', '209375', NULL, NULL, '2024-04-02T17:23:38.020');
INSERT INTO `sm_teacher` (`id`, `obsid`, `usersid`, `jobno`, `title`, `oftenclassroomid`, `createtime`) VALUES ('1051170049-a81e42f0-dc19-4117-b08c-163987a8a6ee', '4355323', '508110840-5a6bb13b-8eaf-49df-9854-f753d5406aa4', 'A03180347', NULL, NULL, '2024-03-20T22:34:27.836');
INSERT INTO `sm_teacher` (`id`, `obsid`, `usersid`, `jobno`, `title`, `oftenclassroomid`, `createtime`) VALUES ('1051170049-a9890074-aa3c-496d-9eea-04da512e209d', '328468276', '508110840-5b6fc679-f1ea-4207-8e6a-17703845b6f7', '123456', NULL, NULL, '2024-04-08T17:17:26.293');
INSERT INTO `sm_teacher` (`id`, `obsid`, `usersid`, `jobno`, `title`, `oftenclassroomid`, `createtime`) VALUES ('1051170049-afaa3e5c-3886-48a4-8ad5-2e78f74dd00c', '4355323', '508110840-b9e5a441-1c32-48b6-9cc1-0b1dd2dfdc1b', 'A03180347', NULL, NULL, '2024-03-20T22:34:30.916');
INSERT INTO `sm_teacher` (`id`, `obsid`, `usersid`, `jobno`, `title`, `oftenclassroomid`, `createtime`) VALUES ('1051170049-b61f22fa-5c7d-45a0-a974-d19ec02a63c8', '4355323', '508110840-b587fa0e-ecc1-480a-8496-7b0624ebe043', 'A03180347', NULL, NULL, '2024-03-20T22:34:30.289');
INSERT INTO `sm_teacher` (`id`, `obsid`, `usersid`, `jobno`, `title`, `oftenclassroomid`, `createtime`) VALUES ('1051170049-bc0db9e5-41e7-4928-be32-b225d6fe2073', '4355323', '508110840-ebb12d1c-ddf2-46cb-b514-1bc6b58658eb', '209375', NULL, NULL, '2024-03-31T17:21:41.558');
INSERT INTO `sm_teacher` (`id`, `obsid`, `usersid`, `jobno`, `title`, `oftenclassroomid`, `createtime`) VALUES ('1051170049-bc41301b-5b00-4257-9a9f-29e06c15c9a5', '4355323', '508110840-49c130e5-7d06-4824-9254-b27e67d38031', 'A03180347', NULL, NULL, '2024-03-20T22:34:29.720');
INSERT INTO `sm_teacher` (`id`, `obsid`, `usersid`, `jobno`, `title`, `oftenclassroomid`, `createtime`) VALUES ('1051170049-d5d43dc7-6155-4983-9978-3f0000d01e8c', '4355323', '508110840-a51decde-0c60-4153-833c-56aef82defe8', 'A03180347', NULL, NULL, '2024-03-20T22:34:24.321');
INSERT INTO `sm_teacher` (`id`, `obsid`, `usersid`, `jobno`, `title`, `oftenclassroomid`, `createtime`) VALUES ('1051170049-e456fb33-8fb3-4b4d-8632-3237455266b8', '4355323', '508110840-bbd53c54-c506-4282-a08f-3c0ca5ba3b2d', NULL, NULL, NULL, '2024-02-28T11:23:46.688');
INSERT INTO `sm_teacher` (`id`, `obsid`, `usersid`, `jobno`, `title`, `oftenclassroomid`, `createtime`) VALUES ('1051170049-e6a045b9-1c8b-4be6-bb56-be13bdf92fe7', '4355323', '508110840-42283875-bfe4-4a57-9d3d-2cc609326550', 'A03180347', NULL, NULL, '2024-03-20T22:34:32.761');
COMMIT;

-- ----------------------------
-- Table structure for st_level
-- ----------------------------
DROP TABLE IF EXISTS `st_level`;
CREATE TABLE `st_level` (
  `id` int(11) NOT NULL,
  `levelname` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `obsdeep` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `catelog` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of st_level
-- ----------------------------
BEGIN;
INSERT INTO `st_level` (`id`, `levelname`, `obsdeep`, `catelog`) VALUES (101, '学校', '1', '0');
INSERT INTO `st_level` (`id`, `levelname`, `obsdeep`, `catelog`) VALUES (102, '学院', '2', '0');
INSERT INTO `st_level` (`id`, `levelname`, `obsdeep`, `catelog`) VALUES (103, '系', '0', '2');
INSERT INTO `st_level` (`id`, `levelname`, `obsdeep`, `catelog`) VALUES (104, '专业', '3', '0');
INSERT INTO `st_level` (`id`, `levelname`, `obsdeep`, `catelog`) VALUES (105, '班级', '4', '1');
INSERT INTO `st_level` (`id`, `levelname`, `obsdeep`, `catelog`) VALUES (106, '小组', '0', '0');
COMMIT;

-- ----------------------------
-- Table structure for st_menus
-- ----------------------------
DROP TABLE IF EXISTS `st_menus`;
CREATE TABLE `st_menus` (
  `id` varchar(255) NOT NULL COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `pid` varchar(50) DEFAULT NULL COMMENT '父节点ID',
  `orderno` int(50) DEFAULT NULL COMMENT '排列顺序（1表示父节点的第一个子节点，2表示父节点的第2个子节点，依次类推）',
  `url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `isused` varchar(50) DEFAULT NULL COMMENT '是否使用（1-使用，0-隐藏）',
  `createtime` varchar(255) DEFAULT NULL COMMENT '创建日期',
  `levelcode` varchar(255) DEFAULT NULL COMMENT '层级码',
  `fullpath` varchar(255) DEFAULT NULL COMMENT '全路径',
  `by1` varchar(255) DEFAULT NULL COMMENT '备用字段1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统_菜单表';

-- ----------------------------
-- Records of st_menus
-- ----------------------------
BEGIN;
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-016c289e-75ae-4034-85db-94b1cff32233', '层级测试菜单', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', 1, '', '1', '2024-04-11T19:03:03.976', '101.101.101', '基础信息管理/角色管理/层级测试菜单', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', '基础信息管理', '0', 1, '', '1', '2024-02-06T21:09:57.846', '101', '基础信息管理', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-097434fa-dbbc-4ee3-ac93-fed60c6e18ce', '层级测试菜单', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', 1, '', '1', '2024-04-11T18:37:17.397', '101.101.101', '基础信息管理/角色管理/层级测试菜单', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65', '教师首页', '0', 2, 'homes/teacherhome', '1', '2024-03-27T22:12:00.331', '102', '教师首页', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-155d2725-4be7-4e83-9ac0-88552a02023f', '教学单位创建', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', 5, '/sysmangt/creatteachunit', '1', '2024-02-06T21:22:22.467', '101.105', '基础信息管理/教学单位创建', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-3279ddd8-0024-4e37-b1f0-51fe30e57ff4', '层级测试菜单', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', 1, '', '1', '2024-04-11T19:00:07.925', '101.101.101', '基础信息管理/角色管理/层级测试菜单', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-63929fcc-e8f9-11ee-934c-fa163efa1f90', '专业班级管理', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', 9, '/sysmangt/classmangt', '1', '2024-03-23T21:14:01.311', '101.109', '基础信息管理/专业班级管理', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3', '学院管理', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', 3, '/sysmangt/collegemangt', '1', '2024-02-06T21:18:34.329', '101.103', '基础信息管理/学院管理', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998', '人员管理', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', 6, '/sysmangt/peoplemangt', '1', '2024-02-06T21:23:38.285', '101.106', '基础信息管理/人员管理', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-7fb353f6-a56a-4622-a79d-dab0c178febf', '角色授权', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', 2, '/sysmangt/rolepurview', '1', '2024-02-06T21:16:18.599', '101.102', '基础信息管理/角色授权', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-86317a13-8a2c-41fd-b1ad-051ca070a875', '学期管理', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', 4, '/sysmangt/termmangt', '1', '2024-02-06T21:21:29.285', '101.104', '基础信息管理/学期管理', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', '角色管理', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', 1, '/sysmangt/rolemangt', '1', '2024-02-06T21:14:01.311', '101.101', '基础信息管理/角色管理', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-910116aa-e8f8-11ee-934c-fa163efa1f90', '专业管理', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', 8, '/sysmangt/professionmangt', '1', '2024-03-23T21:14:01.311', '101.108', '基础信息管理/专业管理', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-bbf33596-d4ed-41ce-a7d3-cfba75618c64', '层级测试菜单', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', 1, '', '1', '2024-04-11T18:46:17.174', '101.101.101', '基础信息管理/角色管理/层级测试菜单', NULL);
INSERT INTO `st_menus` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES ('531500340-f47ac10b-58cc-4372-a567-0e02b2c3d479', '部门管理', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', 7, '/sysmangt/departmentmangt', '1', '2024-03-23T21:14:01.311', '101.107', '基础信息管理/部门管理', NULL);
COMMIT;

-- ----------------------------
-- Table structure for st_menus_bak
-- ----------------------------
DROP TABLE IF EXISTS `st_menus_bak`;
CREATE TABLE `st_menus_bak` (
  `id` int(11) NOT NULL COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `pid` varchar(50) DEFAULT NULL COMMENT '父节点ID',
  `orderno` int(50) DEFAULT NULL COMMENT '排列顺序（1表示父节点的第一个子节点，2表示父节点的第2个子节点，依次类推）',
  `url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `isused` varchar(50) DEFAULT NULL COMMENT '是否使用（1-使用，0-隐藏）',
  `createtime` varchar(255) DEFAULT NULL COMMENT '创建日期',
  `levelcode` varchar(255) DEFAULT NULL COMMENT '层级码',
  `fullpath` varchar(255) DEFAULT NULL COMMENT '全路径',
  `by1` varchar(255) DEFAULT NULL COMMENT '备用字段1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of st_menus_bak
-- ----------------------------
BEGIN;
INSERT INTO `st_menus_bak` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES (101, '角色管理', '0', 1, '../../sysmangt/rolemangt', '1', NULL, '101', '角色管理', NULL);
INSERT INTO `st_menus_bak` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES (102, '角色授权', '0', 2, '../../sysmangt/rolepurview', '1', NULL, '102', '角色授权', NULL);
INSERT INTO `st_menus_bak` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES (103, '测试', '0', 3, '../../sysmangt/role', '1', NULL, '103', '测试', NULL);
INSERT INTO `st_menus_bak` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES (2121, '测试', '101', 1, '???', '1', NULL, '101.101', '测试', NULL);
INSERT INTO `st_menus_bak` (`id`, `name`, `pid`, `orderno`, `url`, `isused`, `createtime`, `levelcode`, `fullpath`, `by1`) VALUES (12231, '测试', '101', 2, '??', '1', NULL, '101.102', '测试', NULL);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统_角色菜单关系表';

-- ----------------------------
-- Records of st_rolemenu
-- ----------------------------
BEGIN;
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-00bd5f49-cab4-4d89-933c-2c89718be30f', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3', '1', '2024-02-06T21:18:36.357', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-018a7098-5fc8-4739-bb5f-3adfeb060328', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', '3', '2024-02-06T21:14:03.950', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-05b74802-e8f4-11ee-934c-fa163efa1f90', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-f47ac10b-58cc-4372-a567-0e02b2c3d479', '1', '2024-03-23T21:14:04.291', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0679be22-f113-4ce8-ab06-50a1cee619f3', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998', '3', '2024-02-06T21:23:40.953', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0693bae6-4965-47fe-a9a5-4ff03724bab8', '516761049-9f019d9a-36e8-46de-b0ae-801499fc1443', '531500340-86317a13-8a2c-41fd-b1ad-051ca070a875', '3', '2024-03-19T18:01:01.168', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-08b5c371-5416-490f-ba23-6d6d2c5eb5bd', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65', '1', '2024-03-27T22:12:01.730', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0a4f6d32-9650-47a9-801e-6f8b534e2910', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', '3', '2024-02-06T21:10:00.018', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0acbe92a-060c-45ee-afd9-a3d3a317ef47', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-097434fa-dbbc-4ee3-ac93-fed60c6e18ce', '3', '2024-04-11T18:37:18.307', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0d9f17e9-3553-4dca-838f-e32dbed945cf', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-7fb353f6-a56a-4622-a79d-dab0c178febf', '3', '2024-02-06T21:16:21.096', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-0dd4ea72-f71f-45a2-89e8-151bf720c9db', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998', '3', '2024-03-28T13:54:08.778', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-11f46f75-4c71-4153-bba0-0e6967da210f', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-86317a13-8a2c-41fd-b1ad-051ca070a875', '3', '2024-02-06T21:21:31.463', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-142f719a-439a-48a0-a642-975c853e3696', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-155d2725-4be7-4e83-9ac0-88552a02023f', '3', '2024-02-06T21:22:24.569', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-14a9f383-f47f-45b3-8275-aa01a4d946ff', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', '3', '2024-02-06T21:10:00.082', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-183159e4-8b4b-42df-b477-f2625d9246f7', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-097434fa-dbbc-4ee3-ac93-fed60c6e18ce', '3', '2024-04-11T18:37:18.790', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-20c65134-406c-42e8-98e1-b6cdb2699ccc', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-155d2725-4be7-4e83-9ac0-88552a02023f', '3', '2024-02-06T21:22:25.028', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-25c962b0-b311-4b95-9a06-6c95c911e06f', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', '1', '2024-02-06T21:09:59.953', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-25de1e88-ca5f-4248-8bd8-71d37065a41a', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', '3', '2024-02-06T21:14:03.889', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-29c6da22-cb96-4e39-b3db-b1cebcb4884d', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-016c289e-75ae-4034-85db-94b1cff32233', '3', '2024-04-11T19:03:05.043', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2a654cbd-c603-4ec6-923a-9d1281c6c568', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-910116aa-e8f8-11ee-934c-fa163efa1f90', '3', '2024-03-28T13:54:09.077', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2b319396-e712-4cdc-b3f3-cd64a00135dd', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-155d2725-4be7-4e83-9ac0-88552a02023f', '1', '2024-02-06T21:22:25.091', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2b65dcbb-e91b-4621-a1b9-9d46fd71bfb7', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-bbf33596-d4ed-41ce-a7d3-cfba75618c64', '3', '2024-04-11T18:46:18.346', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2b7266c3-405e-4b2d-b5a5-de676c2fe0d8', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-7fb353f6-a56a-4622-a79d-dab0c178febf', '3', '2024-02-06T21:16:20.634', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-2fea04bc-9210-4311-891b-31fc001ef831', '516761049-9f019d9a-36e8-46de-b0ae-801499fc1443', '531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3', '3', '2024-03-19T18:01:01.126', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-30d79281-b1f7-4ace-85f5-d549fc1478d1', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-86317a13-8a2c-41fd-b1ad-051ca070a875', '3', '2024-02-06T21:21:31.002', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-34b75fe6-e774-433c-baf5-b4b2f1511ae5', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-86317a13-8a2c-41fd-b1ad-051ca070a875', '3', '2024-02-06T21:21:31.060', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3614b32c-0422-40e5-86ee-013f85c10022', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998', '3', '2024-02-06T21:23:40.430', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3718f63f-d9fa-4e36-9736-5a43ef7252df', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-016c289e-75ae-4034-85db-94b1cff32233', '3', '2024-04-11T19:03:05.111', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3d551af4-0be9-4e50-8131-f9cb4b12a0f5', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-bbf33596-d4ed-41ce-a7d3-cfba75618c64', '3', '2024-04-11T18:46:18.608', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-3f9bf58e-1413-483f-ae00-ac1e686c76ec', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3', '1', '2024-02-06T21:18:35.917', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-42392037-13f1-438d-a04d-f435fbff08e9', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-016c289e-75ae-4034-85db-94b1cff32233', '3', '2024-04-11T19:03:05.386', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-4418290c-45d0-42a0-ab2a-47fc62e57e45', '516761049-9f019d9a-36e8-46de-b0ae-801499fc1443', '531500340-7fb353f6-a56a-4622-a79d-dab0c178febf', '3', '2024-03-19T18:01:01.154', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-45cf9374-64a5-4f42-8a3b-0e38e55abf76', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-016c289e-75ae-4034-85db-94b1cff32233', '3', '2024-04-11T19:03:04.780', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-48b7f5a7-b566-418e-bca6-0a7605a1cdef', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', '3', '2024-03-28T13:54:08.989', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-4c0ab44e-302c-45aa-9d9b-63d1e57d3f1a', '516761049-9f019d9a-36e8-46de-b0ae-801499fc1443', '531500340-155d2725-4be7-4e83-9ac0-88552a02023f', '3', '2024-03-19T18:01:01.111', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-4e209f3e-412e-47bd-8063-5c481fd60014', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-016c289e-75ae-4034-85db-94b1cff32233', '3', '2024-04-11T19:03:05.316', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-5121e95b-0329-4113-b245-8e36131e3cef', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65', '1', '2024-03-27T22:12:01.244', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-54a7497b-d171-4fcf-95a3-c27e9236fb2b', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-016c289e-75ae-4034-85db-94b1cff32233', '3', '2024-04-11T19:03:04.912', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-560cb886-b954-4789-a760-6182464c3a58', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-097434fa-dbbc-4ee3-ac93-fed60c6e18ce', '3', '2024-04-11T18:37:18.929', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-56ff8a52-822c-42b1-9021-efe93e6b1254', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65', '1', '2024-03-27T22:12:01.557', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-577bc7e1-b0e2-4304-9d2b-8128fa845e2b', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-7fb353f6-a56a-4622-a79d-dab0c178febf', '3', '2024-02-06T21:16:20.695', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-595cda1a-0cc9-4ac7-8861-c6ba24017593', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-bbf33596-d4ed-41ce-a7d3-cfba75618c64', '3', '2024-04-11T18:46:18.058', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-5dddd9a8-d214-4a2b-8a5b-a2b04e8f7ae9', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-3279ddd8-0024-4e37-b1f0-51fe30e57ff4', '3', '2024-04-11T19:00:08.741', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-5f879522-20a9-40f6-9c96-a3b5d18b88d0', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998', '3', '2024-02-06T21:23:40.495', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-5fff9419-9220-496a-992d-6513eb7613a0', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-bbf33596-d4ed-41ce-a7d3-cfba75618c64', '3', '2024-04-11T18:46:18.127', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-627fd7b2-c417-4a8f-af0d-91243d334e56', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-bbf33596-d4ed-41ce-a7d3-cfba75618c64', '3', '2024-04-11T18:46:18.261', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6290fed0-2442-4b62-bb97-39f989e01962', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-155d2725-4be7-4e83-9ac0-88552a02023f', '3', '2024-02-06T21:22:24.443', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-63dd351b-3365-4ae8-9e5f-1ad5ac049d43', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-86317a13-8a2c-41fd-b1ad-051ca070a875', '1', '2024-02-06T21:21:31.983', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-64fdbfb1-6be6-4aee-9831-0452b5f0146f', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', '3', '2024-02-06T21:14:03.767', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6756121e-3d5c-4822-be47-01ed73db9da3', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3', '3', '2024-03-28T13:54:08.712', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-689a2e24-b6db-4e8c-adc7-ea37b7f19025', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-097434fa-dbbc-4ee3-ac93-fed60c6e18ce', '3', '2024-04-11T18:37:18.451', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6b449958-99d9-43a7-8673-388668be4e80', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3', '3', '2024-02-06T21:18:36.035', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6d541109-d13d-4206-af7f-ccb328d154a1', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-86317a13-8a2c-41fd-b1ad-051ca070a875', '3', '2024-03-28T13:54:08.920', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6e104be2-9431-4de0-94b9-8c1a318ecf85', '516761049-9f019d9a-36e8-46de-b0ae-801499fc1443', '531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998', '3', '2024-03-19T18:01:01.138', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6e674dc3-4b3b-4bb2-9038-c2294cb080f6', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65', '1', '2024-03-27T22:12:01.479', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-6ecbe226-d39f-4c8d-81b8-b1e00f50595e', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', '1', '2024-02-06T21:09:59.556', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-705a6255-6fcd-460e-8b27-fc9a8b369ff3', '123456', '531500340-bbf33596-d4ed-41ce-a7d3-cfba75618c64', '3', '2024-04-11T18:46:17.924', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7301122b-f59c-4dd0-ba09-1cf1b795d614', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-86317a13-8a2c-41fd-b1ad-051ca070a875', '3', '2024-02-06T21:21:31.524', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-732c825a-ff5d-446d-afad-29ebd3c81696', '516761049-9f019d9a-36e8-46de-b0ae-801499fc1443', '531500340-3279ddd8-0024-4e37-b1f0-51fe30e57ff4', '3', '2024-04-11T19:00:09.151', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7534a905-0c50-4407-b64b-4d3e58f2a3f9', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-bbf33596-d4ed-41ce-a7d3-cfba75618c64', '3', '2024-04-11T18:46:18.683', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-76125221-fc7b-44de-a9f4-2c1bab9f849d', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3', '3', '2024-02-06T21:18:36.475', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-765d9245-e5ee-4a92-8f9a-18ec55ef0831', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-097434fa-dbbc-4ee3-ac93-fed60c6e18ce', '3', '2024-04-11T18:37:18.861', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-778feadb-9c60-49dc-9313-35e08171c9c8', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65', '1', '2024-03-27T22:12:01.964', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-78449c85-378b-4843-a5ed-19e82b2214a2', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-3279ddd8-0024-4e37-b1f0-51fe30e57ff4', '3', '2024-04-11T19:00:08.809', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7d0222eb-bb8b-4c09-b43d-4d43c0d623b6', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3', '3', '2024-02-06T21:18:35.976', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7d7692a8-b271-4098-ac0d-f75b3e4f1a29', '123456', '531500340-3279ddd8-0024-4e37-b1f0-51fe30e57ff4', '3', '2024-04-11T19:00:08.673', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-7db7d947-5c7c-415b-9ade-033813d82588', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-7fb353f6-a56a-4622-a79d-dab0c178febf', '3', '2024-02-06T21:16:20.233', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-801b750b-a4ca-4e1e-b860-1036677f3d3c', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3', '3', '2024-02-06T21:18:35.857', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-815c4d34-4fe6-4697-af9b-7e163cff429c', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-3279ddd8-0024-4e37-b1f0-51fe30e57ff4', '3', '2024-04-11T19:00:09.081', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8178ce5b-c206-4be1-aa28-d56c491e3387', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-097434fa-dbbc-4ee3-ac93-fed60c6e18ce', '3', '2024-04-11T18:37:18.587', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8331379b-b936-44f8-a237-3ac10a41c979', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', '1', '2024-02-06T21:10:00.147', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-84fb8000-e69a-446a-9234-d8906b7537f3', '516761049-9f019d9a-36e8-46de-b0ae-801499fc1443', '531500340-bbf33596-d4ed-41ce-a7d3-cfba75618c64', '3', '2024-04-11T18:46:18.412', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-88828cb9-d7cf-49de-abbb-247c37d3eeea', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998', '1', '2024-02-06T21:23:40.626', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-89246490-2733-45cf-ab64-ff4f10fa65c5', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998', '3', '2024-02-06T21:23:39.965', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-89afb1f0-1c6e-4138-a71b-112063ed2142', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', '1', '2024-02-06T21:10:00.543', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8b1343e4-c7d3-43ca-a845-4bc98b123039', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-097434fa-dbbc-4ee3-ac93-fed60c6e18ce', '3', '2024-04-11T18:37:18.381', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8b4aa456-3db7-487b-b03d-cde7018a3cc4', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-097434fa-dbbc-4ee3-ac93-fed60c6e18ce', '3', '2024-04-11T18:37:18.238', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8cbf6e0e-e68a-4931-a7af-e3c66ecc3689', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65', '1', '2024-03-27T22:12:01.402', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8d0552aa-acda-4f2f-9e85-d50fab1d516c', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', '3', '2024-02-06T21:09:59.492', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-8eca472d-6260-467f-81bc-2e59044b876e', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-86317a13-8a2c-41fd-b1ad-051ca070a875', '3', '2024-02-06T21:21:31.584', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-91a49212-a128-4020-b7f8-8166ff518f4f', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65', '1', '2024-03-27T22:12:01.809', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-925b18dd-d962-4760-a54d-6fd83d99b402', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-bbf33596-d4ed-41ce-a7d3-cfba75618c64', '3', '2024-04-11T18:46:18.477', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-94ede99e-e472-4f75-b5b8-235718e75207', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', '3', '2024-02-06T21:14:03.436', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9747368d-07fd-4011-b8d8-bc41e10db551', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', '3', '2024-02-06T21:14:03.261', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-99c57edc-9305-4a55-b1ac-c994450a9f7f', '123456', '531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65', '1', '2024-03-27T22:12:01.164', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9a6b5045-07b8-400b-88f3-c9d47e7c1554', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998', '3', '2024-02-06T21:23:40.560', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9c9e7231-77c8-4d8b-8955-e9221f6e71e5', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-3279ddd8-0024-4e37-b1f0-51fe30e57ff4', '3', '2024-04-11T19:00:08.947', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9cea6eb9-6113-4400-9f2e-90f22b4f81f7', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998', '3', '2024-02-06T21:23:40.029', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9cf72cfa-a027-407a-aad5-f90e389a5efe', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3', '3', '2024-02-06T21:18:36.536', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-9f2c5e99-e8f9-11ee-934c-fa163efa1f90', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-63929fcc-e8f9-11ee-934c-fa163efa1f90', '1', '2024-03-23T21:14:04.291', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a21d19fd-ee81-47ea-af23-a12fcac5b65e', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', '3', '2024-02-06T21:14:03.318', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a50ad1c5-7b96-4c34-a8bd-10a9f9cebb43', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', '1', '2024-02-06T21:09:59.621', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a85af6a8-e8f9-11ee-934c-fa163efa1f90', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-910116aa-e8f8-11ee-934c-fa163efa1f90', '1', '2024-03-23T21:14:04.291', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a9af304f-3f26-4618-b007-568930ae9d7a', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998', '3', '2024-02-06T21:23:40.094', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-a9f9e0b1-0e12-4224-9d5a-3923e2a3c3af', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3', '3', '2024-02-06T21:18:36.415', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-aa06eb98-152f-4ff4-b64e-b6adcab6c17f', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-3279ddd8-0024-4e37-b1f0-51fe30e57ff4', '3', '2024-04-11T19:00:09.293', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ab1f969f-1131-4b95-84b0-e434a0617b96', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3', '3', '2024-02-06T21:18:36.879', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ac0bbf64-9658-4270-8266-46b48bda9812', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-f47ac10b-58cc-4372-a567-0e02b2c3d479', '3', '2024-03-28T13:54:09.145', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ac50a1ef-c2db-4b0f-b6a6-fa8b228b4257', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-86317a13-8a2c-41fd-b1ad-051ca070a875', '3', '2024-02-06T21:21:31.643', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-add5ae4c-9940-4756-967e-b37a097219d8', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-3279ddd8-0024-4e37-b1f0-51fe30e57ff4', '3', '2024-04-11T19:00:09.015', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-affe8bd6-7253-49c1-8e22-6e52d3c594d6', '123456', '531500340-097434fa-dbbc-4ee3-ac93-fed60c6e18ce', '3', '2024-04-11T18:37:18.169', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b3431ab3-805c-46d1-adb1-5ad1b067dd15', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65', '3', '2024-03-28T13:54:08.439', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b39f088d-aa3a-43ac-a93f-323fa43f3d99', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-155d2725-4be7-4e83-9ac0-88552a02023f', '3', '2024-02-06T21:22:24.507', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b4d4f0b4-c950-4b7d-9119-20a24d2ac388', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-7fb353f6-a56a-4622-a79d-dab0c178febf', '3', '2024-02-06T21:16:20.571', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b52e1f05-7ad7-42c7-9fc6-8829062821bd', '516761049-9f019d9a-36e8-46de-b0ae-801499fc1443', '531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65', '1', '2024-03-27T22:12:01.651', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b5703ab4-e053-4638-9431-42b314dec0d3', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-155d2725-4be7-4e83-9ac0-88552a02023f', '3', '2024-03-28T13:54:08.506', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b690110c-d017-4239-a13a-b2eda41a1ee9', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-016c289e-75ae-4034-85db-94b1cff32233', '3', '2024-04-11T19:03:05.452', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b6b35d73-f4e4-4a6e-b464-f13de8ae2feb', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-3279ddd8-0024-4e37-b1f0-51fe30e57ff4', '3', '2024-04-11T19:00:09.224', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-b7994b9b-705a-4013-a426-de03dc578215', '123456', '531500340-016c289e-75ae-4034-85db-94b1cff32233', '3', '2024-04-11T19:03:04.713', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ba9afe46-998b-4101-9df6-d92f1c7abd74', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-097434fa-dbbc-4ee3-ac93-fed60c6e18ce', '3', '2024-04-11T18:37:18.723', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-bbf370fc-08c4-42a4-b653-497d277fbfd7', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-bbf33596-d4ed-41ce-a7d3-cfba75618c64', '3', '2024-04-11T18:46:18.194', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-bc99c33b-4b4e-48dc-b6b8-f61321fc8c85', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-86317a13-8a2c-41fd-b1ad-051ca070a875', '3', '2024-02-06T21:21:32.043', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-be7f175d-59e6-489d-a437-e737fa0ed7a5', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-3279ddd8-0024-4e37-b1f0-51fe30e57ff4', '3', '2024-04-11T19:00:08.879', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c9081345-b977-4aa4-bbc3-0ab616a30d26', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-7fb353f6-a56a-4622-a79d-dab0c178febf', '1', '2024-02-06T21:16:20.171', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-c9f70e49-0657-4443-ac79-d66da558f3cf', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', '1', '2024-02-06T21:10:00.478', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-cb1bfad5-fb24-4cb8-a987-d6f113d5d060', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-097434fa-dbbc-4ee3-ac93-fed60c6e18ce', '3', '2024-04-11T18:37:18.519', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-cf622acb-483b-4539-81e7-cf3ddae2a82c', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-3279ddd8-0024-4e37-b1f0-51fe30e57ff4', '3', '2024-04-11T19:00:09.429', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d119a7c5-6387-46dd-b88f-c410be1bbd4e', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65', '1', '2024-03-27T22:12:01.325', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d2c0f8c9-a1c2-4cd2-b81d-4026dada910c', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-86317a13-8a2c-41fd-b1ad-051ca070a875', '3', '2024-02-06T21:21:31.119', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d386510f-f1cf-4237-9516-11b24362cc16', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', '3', '2024-02-06T21:14:03.828', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d47f3eed-b7de-423b-816f-1edea8788f03', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', '3', '2024-02-06T21:14:04.291', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d6c65073-0d6e-4d1a-b794-8e3b5c5dde35', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-7fb353f6-a56a-4622-a79d-dab0c178febf', '3', '2024-02-06T21:16:20.760', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-d6fa4a5d-b69e-4540-a313-6d440ca02d92', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-016c289e-75ae-4034-85db-94b1cff32233', '3', '2024-04-11T19:03:05.248', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-dab5e5d6-9601-4eac-9009-d5877f8f8708', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-155d2725-4be7-4e83-9ac0-88552a02023f', '3', '2024-02-06T21:22:24.100', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-db104799-5a17-4775-9c7a-60dfd1012f1e', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-7fb353f6-a56a-4622-a79d-dab0c178febf', '3', '2024-02-06T21:16:19.776', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-db770eb1-716c-425e-8d10-2d5c0e82ff10', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '531500340-bbf33596-d4ed-41ce-a7d3-cfba75618c64', '3', '2024-04-11T18:46:18.544', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-dcd91ca4-2784-4cb0-a398-e19e2ad6f0e4', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-016c289e-75ae-4034-85db-94b1cff32233', '3', '2024-04-11T19:03:04.844', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ddfb37ec-f278-4949-bd08-fc9efa304e28', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65', '1', '2024-03-27T22:12:01.887', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e0480972-ca21-432e-bbe7-0d3932986c33', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '531500340-155d2725-4be7-4e83-9ac0-88552a02023f', '3', '2024-02-06T21:22:24.632', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e287c52f-28d6-4dae-a0d2-383d2097c901', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '531500340-3279ddd8-0024-4e37-b1f0-51fe30e57ff4', '3', '2024-04-11T19:00:09.360', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e28b9acd-2a53-4017-8ec1-7c9979e5404f', '516761049-9f019d9a-36e8-46de-b0ae-801499fc1443', '531500340-097434fa-dbbc-4ee3-ac93-fed60c6e18ce', '3', '2024-04-11T18:37:18.655', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-e9968e85-6a1f-4b72-aa60-17e1662d76dd', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', '1', '2024-02-06T21:14:03.377', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ecac7f73-7fe8-4216-82e4-9f3738be561f', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', '3', '2024-03-28T13:54:08.371', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ecc358a2-656a-4cca-b290-7eef43f6f33b', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '531500340-155d2725-4be7-4e83-9ac0-88552a02023f', '3', '2024-02-06T21:22:25.156', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-eff8edcb-163a-4936-9c22-f91cbd29d388', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '531500340-155d2725-4be7-4e83-9ac0-88552a02023f', '3', '2024-02-06T21:22:24.965', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-efff6378-c6b1-4a52-826f-2088f2b7a566', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-7fb353f6-a56a-4622-a79d-dab0c178febf', '3', '2024-03-28T13:54:08.849', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f18ab5a1-caad-41b2-95f5-4ed6ba29150f', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-bbf33596-d4ed-41ce-a7d3-cfba75618c64', '3', '2024-04-11T18:46:17.991', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f39c672a-773a-486f-b7a7-ca67b3a75de3', '516761049-9f019d9a-36e8-46de-b0ae-801499fc1443', '531500340-8b762def-a3ea-4a06-a9b4-b91d2fd99b1a', '3', '2024-03-19T18:01:01.182', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f4536c7f-c2d0-4c21-81ac-9add76b42aae', '516761049-9a741546-0b55-489b-9dc4-31789ee07153', '531500340-63929fcc-e8f9-11ee-934c-fa163efa1f90', '3', '2024-03-28T13:54:08.645', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f49354b0-c79c-4d63-8d6a-f8eb332095b3', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998', '3', '2024-02-06T21:23:39.619', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-f80f95b0-e7da-40cb-889c-8b896f70b141', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '531500340-016c289e-75ae-4034-85db-94b1cff32233', '3', '2024-04-11T19:03:04.978', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-faa05270-5a99-4b9e-9278-5f76faf1006a', '516761049-9f019d9a-36e8-46de-b0ae-801499fc1443', '531500340-016c289e-75ae-4034-85db-94b1cff32233', '3', '2024-04-11T19:03:05.179', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-fd338063-b55a-48ec-82d2-c1bc98889522', '516761049-9f019d9a-36e8-46de-b0ae-801499fc1443', '531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d', '3', '2024-03-19T18:01:01.096', NULL);
INSERT INTO `st_rolemenu` (`id`, `roleid`, `menuid`, `status`, `createtime`, `by1`) VALUES ('1666154269-ff5378a3-bc16-4afc-8667-c266d07d3b38', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '531500340-7fb353f6-a56a-4622-a79d-dab0c178febf', '3', '2024-02-06T21:16:20.107', NULL);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统_角色表\r\n';

-- ----------------------------
-- Records of st_roles
-- ----------------------------
BEGIN;
INSERT INTO `st_roles` (`id`, `rolename`, `rolecode`, `remark`, `homename`, `homeurl`, `createtime`, `isRP`, `by2`) VALUES ('123456', '测试角色', '11', '啥也不是', '没有首页', '1231312', NULL, '0', NULL);
INSERT INTO `st_roles` (`id`, `rolename`, `rolecode`, `remark`, `homename`, `homeurl`, `createtime`, `isRP`, `by2`) VALUES ('516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', '课程负责人', '7', '课程负责人', '课程负责人首页', '/homes/coursemanagerhome', '2024-02-06T21:01:27.898', '0', NULL);
INSERT INTO `st_roles` (`id`, `rolename`, `rolecode`, `remark`, `homename`, `homeurl`, `createtime`, `isRP`, `by2`) VALUES ('516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '教务处', '3', '教务处', '教务处首页', '/homes/academicaffairshome', '2024-02-06T20:58:03.116', '0', NULL);
INSERT INTO `st_roles` (`id`, `rolename`, `rolecode`, `remark`, `homename`, `homeurl`, `createtime`, `isRP`, `by2`) VALUES ('516761049-812a1a24-365d-4697-ab16-b0093b983624', '超级管理员', '1', '超级管理员', '超级管理员', '/homes/superadminhome', '2024-02-06T20:56:09.898', '0', NULL);
INSERT INTO `st_roles` (`id`, `rolename`, `rolecode`, `remark`, `homename`, `homeurl`, `createtime`, `isRP`, `by2`) VALUES ('516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '专业负责人', '6', '专业负责人', '专业负责人首页', '/homes/', '2024-02-06T21:00:32.915', '1', NULL);
INSERT INTO `st_roles` (`id`, `rolename`, `rolecode`, `remark`, `homename`, `homeurl`, `createtime`, `isRP`, `by2`) VALUES ('516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', '助教', '9', '助教', '助教首页', '/homes/', '2024-02-06T21:02:24.708', '0', NULL);
INSERT INTO `st_roles` (`id`, `rolename`, `rolecode`, `remark`, `homename`, `homeurl`, `createtime`, `isRP`, `by2`) VALUES ('516761049-9a741546-0b55-489b-9dc4-31789ee07153', '学生', '10', '学生', '学生首页', '/homes/studenthome', '2024-03-28T13:54:07.698', NULL, NULL);
INSERT INTO `st_roles` (`id`, `rolename`, `rolecode`, `remark`, `homename`, `homeurl`, `createtime`, `isRP`, `by2`) VALUES ('516761049-9f019d9a-36e8-46de-b0ae-801499fc1443', '未命名角色', '99', '', 'dsds', '12', '2024-03-19T18:01:01.073', '0', NULL);
INSERT INTO `st_roles` (`id`, `rolename`, `rolecode`, `remark`, `homename`, `homeurl`, `createtime`, `isRP`, `by2`) VALUES ('516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', '任课教师', '8', '任课教师', '任课教师首页', '/homes/', '2024-02-06T21:01:54.200', '0', NULL);
INSERT INTO `st_roles` (`id`, `rolename`, `rolecode`, `remark`, `homename`, `homeurl`, `createtime`, `isRP`, `by2`) VALUES ('516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '系主任', '5', '系主任', '系主任首页', '/homes/departmenthome', '2024-02-06T21:00:05.869', '1', NULL);
INSERT INTO `st_roles` (`id`, `rolename`, `rolecode`, `remark`, `homename`, `homeurl`, `createtime`, `isRP`, `by2`) VALUES ('516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '教学秘书', '2', '教学秘书', '教学秘书首页', '/homes/secretariatehome', '2024-02-06T20:57:34.340', '1', NULL);
INSERT INTO `st_roles` (`id`, `rolename`, `rolecode`, `remark`, `homename`, `homeurl`, `createtime`, `isRP`, `by2`) VALUES ('516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '学院负责人', '4', '学院负责人', '学院负责人首页', '/homes/deanhome', '2024-02-06T20:59:41.042', '1', NULL);
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
  `obsdeep` varchar(255) DEFAULT NULL COMMENT '机构层级',
  `createtime` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `by1` varchar(255) DEFAULT NULL COMMENT '备用字段1',
  PRIMARY KEY (`id`),
  KEY `fk_roleuser_roleid` (`roleid`),
  KEY `fk_roleuser_userid` (`userid`),
  CONSTRAINT `fk_roleuser_roleid` FOREIGN KEY (`roleid`) REFERENCES `st_roles` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_roleuser_userid` FOREIGN KEY (`userid`) REFERENCES `st_users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统_角色用户关系表\r\n';

-- ----------------------------
-- Records of st_roleuser
-- ----------------------------
BEGIN;
INSERT INTO `st_roleuser` (`id`, `userid`, `roleid`, `obsid`, `obsdeep`, `createtime`, `by1`) VALUES ('1', '1', '516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21', NULL, NULL, NULL, NULL);
INSERT INTO `st_roleuser` (`id`, `userid`, `roleid`, `obsid`, `obsdeep`, `createtime`, `by1`) VALUES ('1665399769-1a4a6603-39e5-4273-887c-0702074d714f', '508110840-5b6fc679-f1ea-4207-8e6a-17703845b6f7', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '328468276', '2', '2024-04-08', NULL);
INSERT INTO `st_roleuser` (`id`, `userid`, `roleid`, `obsid`, `obsdeep`, `createtime`, `by1`) VALUES ('1665399769-5af7d321-7703-41f2-9249-b4237af2def6', '508110840-d38805d7-163e-410b-a00a-e29db59d8829', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '328468276', '2', '2024-04-09', NULL);
INSERT INTO `st_roleuser` (`id`, `userid`, `roleid`, `obsid`, `obsdeep`, `createtime`, `by1`) VALUES ('1665399769-5bb2ee1f-f875-4257-990e-22a33a6d013d', '508110840-bbd53c54-c506-4282-a08f-3c0ca5ba3b2d', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '328468276', '2', '2024-04-08', NULL);
INSERT INTO `st_roleuser` (`id`, `userid`, `roleid`, `obsid`, `obsdeep`, `createtime`, `by1`) VALUES ('1665399769-84a466a2-ef2d-4427-8a2d-9eb4e9737192', '508110840-13b8a6db-e4a6-4ea5-b2ce-34f87ceaf2aa', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '4355323', '3', '2024-04-01', NULL);
INSERT INTO `st_roleuser` (`id`, `userid`, `roleid`, `obsid`, `obsdeep`, `createtime`, `by1`) VALUES ('1665399769-8e5e270e-ee8e-4277-a13b-257b98291798', NULL, '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', NULL, NULL, '2024-04-08', NULL);
INSERT INTO `st_roleuser` (`id`, `userid`, `roleid`, `obsid`, `obsdeep`, `createtime`, `by1`) VALUES ('1665399769-99288665-2dd6-4f9d-9c64-d38d7488391f', NULL, '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', NULL, NULL, '2024-04-08', NULL);
INSERT INTO `st_roleuser` (`id`, `userid`, `roleid`, `obsid`, `obsdeep`, `createtime`, `by1`) VALUES ('2', '1', '516761049-459623f8-04cc-4066-8fa8-99f9101450d6', '237675254', '1', NULL, NULL);
INSERT INTO `st_roleuser` (`id`, `userid`, `roleid`, `obsid`, `obsdeep`, `createtime`, `by1`) VALUES ('3', '1', '516761049-812a1a24-365d-4697-ab16-b0093b983624', '237675254', '1', NULL, NULL);
INSERT INTO `st_roleuser` (`id`, `userid`, `roleid`, `obsid`, `obsdeep`, `createtime`, `by1`) VALUES ('4', '1', '516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60', '7267267352', '4', NULL, NULL);
INSERT INTO `st_roleuser` (`id`, `userid`, `roleid`, `obsid`, `obsdeep`, `createtime`, `by1`) VALUES ('5', '1', '516761049-96123be3-e0cd-47b5-89ce-3dd31456be0f', NULL, NULL, NULL, NULL);
INSERT INTO `st_roleuser` (`id`, `userid`, `roleid`, `obsid`, `obsdeep`, `createtime`, `by1`) VALUES ('6', '1', '516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f', NULL, NULL, NULL, NULL);
INSERT INTO `st_roleuser` (`id`, `userid`, `roleid`, `obsid`, `obsdeep`, `createtime`, `by1`) VALUES ('7', '1', '516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58', '4355323', '3', NULL, NULL);
INSERT INTO `st_roleuser` (`id`, `userid`, `roleid`, `obsid`, `obsdeep`, `createtime`, `by1`) VALUES ('8', '1', '516761049-c4227aca-9e02-4706-840d-d5fc85550e24', '237675254', '1', NULL, NULL);
INSERT INTO `st_roleuser` (`id`, `userid`, `roleid`, `obsid`, `obsdeep`, `createtime`, `by1`) VALUES ('9', '1', '516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4', '328468276', '2', NULL, NULL);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统_用户表（老师，学生）';

-- ----------------------------
-- Records of st_users
-- ----------------------------
BEGIN;
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('1', '教师测试账号', 'test1', '123456', '18686850381', '1', '2', '测试', NULL, NULL, NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('2', '学生测试账号1', 'test2', '123456', '18686850382', '1', '1', '测试', NULL, NULL, NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-06446b9e-2576-4b05-8112-71a4ae7951b5', '小1', 'wangliqing', '123456', '18686850381', '1', '1', '测试', NULL, '2024-03-20T21:46:04.440', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-07666af8-603d-404f-a16b-2d052ac984ea', '贺浩洋3', 'hhy3', '123456', '15236521452', '1', '1', '', NULL, '2024-03-20T21:29:12.069', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-13b8a6db-e4a6-4ea5-b2ce-34f87ceaf2aa', '小1', 'wangliqing', '123456', '18686850381', '1', '2', '测试', NULL, '2024-03-20T22:34:32.062', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-2e458cb4-393a-484c-8b65-265c69ffcde8', '小1', 'wangliqing', '123456', '18686850381', '1', '1', '测试', NULL, '2024-03-20T21:46:09.436', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-42283875-bfe4-4a57-9d3d-2cc609326550', '小1', 'wangliqing', '123456', '18686850381', '1', '2', '测试', NULL, '2024-03-20T22:34:32.699', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-42c64cd1-4925-4755-b17a-51ff26a7a8ec', '张三', 'zhangsan', '123', '1362349273', '1', '2', '无', NULL, '2024-03-31T17:21:41.339', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-44575f76-227d-4413-b624-40f30380eeaa', '小1', 'wangliqing', '123456', '18686850381', '1', '1', '测试', NULL, '2024-03-20T21:46:12.768', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-49c130e5-7d06-4824-9254-b27e67d38031', '小1', 'wangliqing', '123456', '18686850381', '1', '2', '测试', NULL, '2024-03-20T22:34:29.659', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-5a6bb13b-8eaf-49df-9854-f753d5406aa4', '小1', 'wangliqing', '123456', '18686850381', '1', '2', '测试', NULL, '2024-03-20T22:34:27.774', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-5b6fc679-f1ea-4207-8e6a-17703845b6f7', '电气老师', 'dianqi', '123456', '13652145236', '1', '2', '', NULL, '2024-04-08T17:17:26.230', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-6c14295b-7775-4bce-a8b2-0bce04ea5034', '王鹂晴', 'libo', '123456', '18686850381', '1', '1', '测试', NULL, '2024-03-04T20:59:46.735', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-742fec1d-d002-4ea0-a817-594c0076a346', '小1', 'wangliqing', '123456', '18686850381', '1', '1', '测试', NULL, '2024-03-20T21:46:11.477', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-76af8fd9-43f9-4d19-98af-c43dcb7cc086', '小1', 'wangliqing', '123456', '18686850381', '1', '1', '测试', NULL, '2024-03-20T21:46:10.814', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-7fc5c01e-7046-4203-ad54-ef7043230d3d', '小1', 'wangliqing', '123456', '18686850381', '1', '1', '测试', NULL, '2024-03-20T21:46:08.118', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-81f560ad-054c-4802-9e7e-579e0b3c2eff', '小1', 'wangliqing', '123456', '18686850381', '1', '1', '测试', NULL, '2024-03-20T21:46:13.400', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-8ba549bf-e304-4196-be47-9ab4aaab9aa1', '小1', 'wangliqing', '123456', '18686850381', '1', '1', '测试', NULL, '2024-03-20T21:46:12.118', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-932c3104-f70b-4d79-a2af-7b8e2115ad72', '小1', 'wangliqing', '123456', '18686850381', '1', '1', '测试', NULL, '2024-03-20T21:46:06.802', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-a50e235d-f645-454c-8952-ce9c5cac48da', '小1', 'wangliqing', '123456', '18686850381', '1', '1', '测试', NULL, '2024-03-20T21:46:02.905', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-a51decde-0c60-4153-833c-56aef82defe8', '小1', 'wangliqing', '123456', '18686850381', '1', '2', '测试', NULL, '2024-03-20T22:34:24.257', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-ae98311f-ee05-4a2b-80ae-b3e6c5224ee8', '小1', 'wangliqing', '123456', '18686850381', '1', '1', '测试', NULL, '2024-03-20T21:46:06.111', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-b587fa0e-ecc1-480a-8496-7b0624ebe043', '小1', 'wangliqing', '123456', '18686850381', '1', '2', '测试', NULL, '2024-03-20T22:34:30.228', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-b9e5a441-1c32-48b6-9cc1-0b1dd2dfdc1b', '小1', 'wangliqing', '123456', '18686850381', '1', '2', '测试', NULL, '2024-03-20T22:34:30.853', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-bbd53c54-c506-4282-a08f-3c0ca5ba3b2d', '王鹂晴', 'wlq', '123456', '18686850381', '1', '2', '测试', NULL, '2024-02-28T11:23:46.664', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-c2cfb30c-035d-4ef7-9a3c-420bfeba3f5d', '王鹂晴', 'wlq1', '123456', '18686850381', '1', '2', '测试', NULL, NULL, NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-c4c1088a-3724-41ee-b372-0c767fb4cdb2', '小1', 'wangliqing', '123456', '18686850381', '1', '1', '测试', NULL, '2024-03-20T21:46:08.750', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-d38805d7-163e-410b-a00a-e29db59d8829', '李四', '李四', '12356', '1362349273', '1', '2', '无', NULL, '2024-04-02T17:23:37.954', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-d9d20c1e-b8f4-4acc-837f-9d4c1d95d48b', '小1', 'wangliqing', '123456', '18686850381', '1', '1', '测试', NULL, '2024-03-20T21:46:14.100', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-dc53f2c3-4f1b-408f-bcb9-25bc529bf0a9', '王鹂晴', 'wlq2', '123456', '18686850381', '1', '1', '其实她是研究生', NULL, NULL, NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-e5eed268-b730-4025-9666-7295d1b74a16', '小1', 'wangliqing', '123456', '18686850381', '1', '1', '测试', NULL, '2024-03-20T21:46:07.449', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-ebb12d1c-ddf2-46cb-b514-1bc6b58658eb', '李四', '李四', '12356', '1362349273', '1', '2', '无', NULL, '2024-03-31T17:21:41.496', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-ec43c492-8f34-4d76-aeae-c475576bd7a5', '小1', 'wangliqing', '123456', '18686850381', '1', '1', '测试', NULL, '2024-03-20T21:46:10.115', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-f0b30f7c-29f0-4a67-8a89-12bf445ccea9', '张三', 'zhangsan', '123', '1362349273', '1', '2', '无', NULL, '2024-04-02T17:23:37.792', NULL, NULL);
INSERT INTO `st_users` (`id`, `username`, `loginname`, `pwd`, `phone`, `status`, `catelog`, `remark`, `headimage`, `createtime`, `by1`, `by2`) VALUES ('508110840-f9e82fcc-ecd3-4201-b97a-99d2a563ea07', '小1', 'wangliqing', '123456', '18686850381', '1', '1', '测试', NULL, '2024-03-20T21:46:05.384', NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
