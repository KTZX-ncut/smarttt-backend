package com.example.smartttevaluation.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PaperIdeologyEvaluationDto {
    private String paperId;
    private String paperType;
    private String paperName;
    private List<PaperQuestionDto> questionList;
}
