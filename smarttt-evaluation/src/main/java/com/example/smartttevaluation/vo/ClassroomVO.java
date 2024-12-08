package com.example.smartttevaluation.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * 查询课堂列表
 */
@Data
public class ClassroomVO {
    /**
     * 课堂id
     */
    private String classroomId;
    /**
     * 课堂名称
     */
    private String classroomName;
    /**
     * 学期名称
     */
    private String termName;

    /**
     * 学期id
     */
    @JsonIgnore
    private String termId;

    /**
     * 课程id
     */
    @JsonIgnore
    private String courseId;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 主讲教师
     */
    private String teacherName;
    /**
     * 实验教师
     */
    private String labTeacher;
    /**
     * 实践教师
     */
    private String practiceTeacher;

    /**
     * 创建人
     */
    private String creator;


}
