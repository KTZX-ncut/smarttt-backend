package com.example.smartttexam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * AI出题请求
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionGenRequest {

    /** 课程ID */
    private String courseId;
    /** 课堂ID（可选） */
    private String classroomId;
    /** 用户选中的KWA ID列表 */
    private List<String> selectedKwaIds;
    /** 生成题目数量，默认20 */
    private Integer questionCount;
    /** 知识单元ID（可选） */
    private String unitId;
    /** 创建人ID（由后端从Token填充） */
    private String creatorId;
    /** 创建人姓名（由后端从Token填充） */
    private String creator;
}
