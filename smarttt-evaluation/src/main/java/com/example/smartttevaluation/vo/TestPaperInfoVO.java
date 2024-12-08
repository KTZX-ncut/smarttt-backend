package com.example.smartttevaluation.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * 获取课程下的 考试/试卷信息 类
 */
@Data
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

}
