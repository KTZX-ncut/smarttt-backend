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

    // 是否参与形成性评价（0不参与，1参与）
    Integer dynamicState;
    // 是否参与达成性评价（0不参与，1参与）
    Integer reachState;
    // 是否参与反馈性评价（0不参与，1参与）
    Integer ideologyState;
}
