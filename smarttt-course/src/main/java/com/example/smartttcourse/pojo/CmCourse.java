package com.example.smartttcourse.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "CmCourse", description = "课程信息")
public class CmCourse {

  @ApiModelProperty(value = "课程 ID", example = "course-001")
  private String id;
  @ApiModelProperty(value = "课程中文名称", example = "软件工程")
  private String courseChineseName;
  @ApiModelProperty(value = "课程英文名称", example = "Software Engineering")
  private String courseEnglishName;
  @ApiModelProperty(value = "课程代码", example = "SE2201")
  private String courseCode;
  @ApiModelProperty(value = "学分", example = "3")
  private double credit;
  @ApiModelProperty(value = "课程负责人 ID", example = "teacher-001")
  private String dutyManId;
  @ApiModelProperty(value = "课程负责人姓名", example = "张老师")
  private String dutyMan;
  @ApiModelProperty(value = "版本号", example = "v1.0")
  private String verNo;
  @ApiModelProperty(value = "课内学时", example = "48")
  private double hourInClass;
  @ApiModelProperty(value = "课外学时", example = "16")
  private double hourOutside;
  @ApiModelProperty(value = "课程性质", example = "必修")
  private String electiveOrRequired;
  private double theoryOfflineHour;
  private double theoryOnlineHour;
  private double experimentHour;
  private double hourOnline;
  private double hourOffline;
  @ApiModelProperty(value = "是否先修课程", example = "否")
  private String firstCourse;
  @ApiModelProperty(value = "适用专业年级", example = "2022级")
  private String professionalGrade;
  @ApiModelProperty(value = "课程简介", example = "介绍软件工程基本理论与实践。")
  private String summary;
  @ApiModelProperty(value = "学习资源说明", example = "教材、慕课、案例库")
  private String learningResource;
  private String markPicture;
  private String attachFileName;
  @ApiModelProperty(value = "所属学期 ID", example = "term-2025-2")
  private String schooltermId;
  private String markPictureUploadFileName;
  private String questionNumber;
  private String attachFileNameUploadFileName;
  private long openTimes;
  private String firstTime;
  private String lastTime;
  @ApiModelProperty(value = "所属专业 ID", example = "profession-001")
  private String professionId;
  @ApiModelProperty(value = "所属专业名称", example = "软件工程")
  private String professionName;
  private String createtime;
}
