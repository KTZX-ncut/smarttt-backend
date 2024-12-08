package com.example.smartttevaluation.vo;

import lombok.Data;

/**
 * 返回考试下的所有学生的实体类
 */
@Data
public class TestStudentVO {
    /**
     * 学生userId
     */
    private String userId;

    /**
     * 学生所在专业
     */
    private String proName;

    /**
     * 学生所在班级
     */
    private String obsName;

    /**
     * 学生名称
     */
    private String userName;

    /**
     * 学号
     */
    private String stuNo;

    /**
     * 序号
     */
    private Integer rowNo;
}
