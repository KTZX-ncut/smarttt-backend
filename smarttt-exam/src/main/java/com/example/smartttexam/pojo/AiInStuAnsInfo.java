package com.example.smartttexam.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AiInStuAnsInfo {

    private String id;
    private String logId;
    private String courseId;
    private String classroomId;
    private String testId;
    private String paperId;
    private String paperType;
    private String libId;
    private String stuId;
    private String questionTypeId;
    private String questionContent;
    private double difficultLevel;
    private double differenceLevel;
    private double guessLevel;
    private String kwaId;
    private double dataValue;
    private long libScore;
    private long libStuScore;
    private long libAnswerTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String vId;
}
