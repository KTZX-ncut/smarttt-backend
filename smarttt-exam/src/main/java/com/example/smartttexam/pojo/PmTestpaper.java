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
public class PmTestpaper {

    private String id;
    private String name;
    private String makeModeId;
    private String testModeId;
    private long questionsCount;
    private long totalScore;
    private long status;
    private long locked;
    private String courseId;
    private String classroomId;
    private String creatorId;
    private String creator;
    private String createTime;
    private String makeModeName;
    private String testModeName;
    private String courseName;
    private String classroomName;
    private String catelog;
    private String quetypesettings;
    private String extendOptions;
}
