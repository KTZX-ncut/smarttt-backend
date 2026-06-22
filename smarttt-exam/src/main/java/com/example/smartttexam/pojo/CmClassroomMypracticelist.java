package com.example.smartttexam.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmClassroomMypracticelist {

    private String id;
    private String classroomId;
    private String testId;
    private String paperId;
    private String stuId;
    private long finishedCount;
    private long unFinishedCount;
    private long myFinishStatus;
    private long submitNo;
    private long correctCount;
    private long errorCount;
    private double correctPercent;
    private String resultLevel;
    private String title;
    private String beginTime;
    private String endTime;
    private String myAnswers;
    private String submitTime;
    private String catelog;
    private long resultScore;
    private String checkResult;
    private String checkerId;
    private String checkTime;
    private String beginAnswerTime;
    private String publishTime;
    private long state;
}
