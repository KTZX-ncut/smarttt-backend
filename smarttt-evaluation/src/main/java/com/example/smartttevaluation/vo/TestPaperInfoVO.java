package com.example.smartttevaluation.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 获取课程下的 考试/试卷信息 类
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestPaperInfoVO {
    /**
     * 考试/作业id
     */
    private String testId;
    /**
     * 试卷id
     */
    private String paperId;

    /**
     * 考试/作业名称
     */
    private String testName;

    /**
     * 试卷名称
     */
    private String paperName;

    /**
     * 课堂id
     */
    private String classroomId;

    /**
     * 课堂名称
     */
    private String classroomName;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 创建人id(不进行系列化返回给前端)
     */
    @JsonIgnore
    private String creatorId;

    /**
     * 提交人
     */
    private String creator;

    /**
     * 提交时间
     */
    private String createTime;


    /**
     * 试卷类型(1作业2考试)
     */
    private String catelog;

}
