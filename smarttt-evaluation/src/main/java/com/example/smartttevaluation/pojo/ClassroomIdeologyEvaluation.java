package com.example.smartttevaluation.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClassroomIdeologyEvaluation {
    private String id;
    private String courseId;
    private String vId;
    private String classroomId;
    private Integer valueCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
