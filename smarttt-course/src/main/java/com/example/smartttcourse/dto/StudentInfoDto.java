package com.example.smartttcourse.dto;

import com.example.smartttcourse.pojo.CmClassroomStudent;
import lombok.Data;

/**
 * @author lunSir
 * @create 2024-09-06 17:29
 */
@Data
public class StudentInfoDto extends CmClassroomStudent {
    // 学员Id
    private String stuId;
    // 学号
    private String stuno;
}
