package com.example.smartttexam.schedule.entity;

import lombok.Data;

@Data
public class EduMessage {
    private Long id;
    /** 消息类型（路由键）：test_delete / test_update */
    private String type;
    /** 课堂ID（可选） */
    private String classroomId;
    /** 动作描述（可选） */
    private String action;
    /** 业务索引ID（必填）：testId */
    private String indexId;
    /** JSON格式的业务上下文（test_update 时需要传 paperName, testName） */
    private String context;
    /** 消息状态：ready */
    private String state;
    /** 备注（可选） */
    private String remark;
    /** 创建时间 */
    private String createTime;
}