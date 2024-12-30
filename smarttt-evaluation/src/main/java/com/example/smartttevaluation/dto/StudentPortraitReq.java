package com.example.smartttevaluation.dto;

import lombok.Data;


@Data
public class StudentPortraitReq {

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 课堂id
     */
    private String classroomId;

    /**
     * 学生id
     */
    private String stuId;

    /**
     * 评价次数
     */
    private Integer num;
}
