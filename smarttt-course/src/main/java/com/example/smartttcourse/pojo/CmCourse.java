package com.example.smartttcourse.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CmCourse {

  private String id;
  private String courseChineseName;
  private String courseEnglishName;
  private String courseCode;
  private double credit;
  private String dutyManId;
  private String dutyMan;
  private String verNo;
  private double hourInClass;
  private double hourOutside;
  private String electiveOrRequired;
  private double theoryOfflineHour;
  private double theoryOnlineHour;
  private double experimentHour;
  private double hourOnline;
  private double hourOffline;
  private String firstCourse;
  private String professionalGrade;
  private String summary;
  private String learningResource;
  private String markPicture;
  private String attachFileName;
  private String schooltermId;
  private String markPictureUploadFileName;
  private String questionNumber;
  private String attachFileNameUploadFileName;
  private long openTimes;
  private String firstTime;
  private String lastTime;
  private String professionId;
  private String professionName;
  private String createtime;
}
