package com.example.smartttevaluation.pojo;

import lombok.Data;

@Data
public class IdeologyCalculatePaper {
    private Integer id;
    private String paperId;
    private String classroomId;
    private String testId;
    private String courseName;
    private String classroomName;
    private String paperName;
    private String testName;
    private String catelog;
    private String creator;
    private String createTime;
    private Integer row;
}
