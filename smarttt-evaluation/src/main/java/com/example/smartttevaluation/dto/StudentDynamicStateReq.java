package com.example.smartttevaluation.dto;

import lombok.Data;

/**
 * @author lunSir
 * @create 2025-01-16 20:49
 */
@Data
public class StudentDynamicStateReq {
    private String classroomStudentId;
    private Integer dynamicState;
}
