package com.example.smartttcourse.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@ApiModel(value = "CmClassroom", description = "课堂信息")
public class CmClassroom {

  @ApiModelProperty(value = "课堂 ID", example = "classroom-001")
  private String id;
  @ApiModelProperty(value = "课堂名称", example = "软件工程课程实验班")
  private String classroomName;
  @ApiModelProperty(value = "学期 ID", example = "term-2025-2")
  private String termId;
  @ApiModelProperty(value = "课程 ID", example = "course-001")
  private String courseId;
  @ApiModelProperty(value = "授课教师 ID", example = "teacher-001")
  private String teacherId;
  @ApiModelProperty(value = "助教 ID", example = "assistant-001")
  private String assistantId;
  @ApiModelProperty(value = "使用班级列表", example = "软件工程2201班,软件工程2202班")
  private String usedClassList;
  @ApiModelProperty(value = "教学大纲文件标识", example = "teaching-program.pdf")
  private String teachingProgram;
  @ApiModelProperty(value = "学生人数", example = "60")
  private long studentCount;
  @ApiModelProperty(value = "开始时间", example = "2026-03-01 08:00:00")
  private String beginTime;
  @ApiModelProperty(value = "结束时间", example = "2026-06-30 10:00:00")
  private String endTime;
  @ApiModelProperty(value = "备注", example = "周二 1-2 节")
  private String remark;
  private long unitCount;
  private long relationCount;
  private long targetCount;
  private String teachingProgramUploadFileName;
  @ApiModelProperty(value = "授课教师姓名", example = "李老师")
  private String teacherName;
  @ApiModelProperty(value = "助教姓名", example = "王助教")
  private String assistantName;
  private long studentOnline;
  private String teachingProgram1;
  @ApiModelProperty(value = "创建人用户 ID", example = "admin-001")
  private String creator;
  private String creatorName;
  @ApiModelProperty(value = "实验教师 ID", example = "lab-teacher-001")
  private String labTeacherId;
  @ApiModelProperty(value = "实验教师姓名", example = "赵老师")
  private String labTeacher;
  @ApiModelProperty(value = "实训教师 ID", example = "practice-teacher-001")
  private String practiceTeacherId;
  @ApiModelProperty(value = "实训教师姓名", example = "钱老师")
  private String practiceTeacher;
  @ApiModelProperty(value = "理论课时", example = "32")
  private long teachTime;
  @ApiModelProperty(value = "实验课时", example = "16")
  private long labTime;
  @ApiModelProperty(value = "实践课时", example = "8")
  private long practiceTime;
  private String unitrelation;
  private String diagramInfo;

  public CmClassroom() {
    teachTime = -1;
    labTime = -1;
    practiceTime =-1;
  }
}
