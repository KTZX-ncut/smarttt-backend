-- 创建cm_course_unit_v_values表，用于存储知识单元与v_ideology_value的关联关系
CREATE TABLE IF NOT EXISTS `cm_course_unit_v_values` (
  `id` VARCHAR(64) NOT NULL COMMENT '主键ID',
  `unitId` VARCHAR(64) NOT NULL COMMENT '知识单元ID，关联cm_course_unit表',
  `vId` VARCHAR(64) NOT NULL COMMENT 'v值ID，关联v_ideology_value表',
  `status` INT DEFAULT 0 COMMENT '状态：0-未完成，1-已完成',
  PRIMARY KEY (`id`),
  KEY `idx_unitId` (`unitId`),
  KEY `idx_vId` (`vId`),
  UNIQUE KEY `uk_unitId_vId` (`unitId`, `vId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='知识单元与v值关联表';

