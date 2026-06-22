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
public class PmTest {

    private String id;
    private String paperId;
    private String paperName;
    private String name;
    private String testModeId;
    private String totalScore;
    private String passScore;
    private long status;
    private String beginTime;
    private String endTime;
    private long lengthOfTime;
    private long limitTimes;
    private String courseId;
    private String classroomId;
    private String catelog;
    private String creatorId;
    private String creator;
    private String creatTime;
    private String publisherId;
    private String publisher;
    private String publishTime;
    private String classroomName;
}
