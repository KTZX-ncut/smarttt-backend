package com.example.smartttevaluation.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentIdeologyEvaluation {
    private String id;
    private String userId;
    private String courseId;
    private String vId;
    private String classroomId;
    private Integer valueCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
