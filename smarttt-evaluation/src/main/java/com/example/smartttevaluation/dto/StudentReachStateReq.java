package com.example.smartttevaluation.dto;

import lombok.Data;

@Data
public class StudentReachStateReq {
    private String classroomStudentId;
    private Integer reachState;
}
