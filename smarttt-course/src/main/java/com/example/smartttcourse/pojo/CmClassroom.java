package com.example.smartttcourse.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CmClassroom {

  private String id;
  private String classroomName;
  private String termId;
  private String courseId;
  private String teacherId;
  private String assistantId;
  private String usedClassList;
  private String teachingProgram;
  private long studentCount;
  private String beginTime;
  private String endTime;
  private String remark;
  private long unitCount;
  private long relationCount;
  private long targetCount;
  private String teachingProgramUploadFileName;
  private String teacherName;
  private String assistantName;
  private long studentOnline;
  private String teachingProgram1;
  private String creator;
  private String creatorName;
  private String labTeacherId;
  private String labTeacher;
  private String practiceTeacherId;
  private String practiceTeacher;
  private long teachTime;
  private long labTime;
  private long practiceTime;
  private String unitrelation;
  private String diagramInfo;

}
