package com.example.smartttevaluation.schedule.entity;

import lombok.Data;

/**
 * 消息实体
 */
@Data
public class EduMessage {
    private Long id;
    private String type;
    private String classroomId;
    private String action;
    /**
     * 业务索引id
     */
    private String indexId;
    /**
     * 业务结构
     */
    private String context;
    /**
     * 消息状态（ready or complete or fail）
     */
    private String state;
    private String remark;
    private String createTime;
}
