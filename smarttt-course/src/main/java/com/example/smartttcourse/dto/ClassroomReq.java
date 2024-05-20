package com.example.smartttcourse.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClassroomReq {
    private String id;
    private String classroomName;
    private String teacherId;
    private String usedClassList;
    private String beginTime;
    private String endTime;
    private String remark;
    private String teacherName;
    private String creator;
    private String labTeacherId;
    private String labTeacher;
    private String practiceTeacherId;
    private String practiceTeacher;
    private long teachTime;
    private long labTime;
    private long practiceTime;
}
