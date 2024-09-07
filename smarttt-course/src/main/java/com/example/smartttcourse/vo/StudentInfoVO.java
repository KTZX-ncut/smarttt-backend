package com.example.smartttcourse.vo;

import com.example.smartttcourse.pojo.CmClassroomStudent;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lunSir
 * @create 2024-09-06 17:29
 */
@Data
public class StudentInfoVO extends CmClassroomStudent {
    // 学员Id
    private String stuId;
    // 学号
    private String stuno;
}
