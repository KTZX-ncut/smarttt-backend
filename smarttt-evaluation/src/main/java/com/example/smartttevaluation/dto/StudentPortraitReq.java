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
     * 第几周（week >= 0）
     */
    private Integer week;

    /**
     * 发送请求的当前时间
     */
    private String now;
}
