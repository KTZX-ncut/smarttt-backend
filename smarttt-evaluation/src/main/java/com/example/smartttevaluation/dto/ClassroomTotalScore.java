package com.example.smartttevaluation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomTotalScore {
    private String id;
    private String stuId;
    private String classroomId;
    private String checkitemId;
    private float ratio;
    private float checkitemScore;
}
