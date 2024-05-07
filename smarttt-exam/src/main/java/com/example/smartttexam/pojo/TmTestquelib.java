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
public class TmTestquelib {

  private String id;
  private String libNo;
  private String questionTypeId;
  private String courseId;
  private String classroomId;
  private String unitId;
  private String catelogId;
  private String content;
  private String analysis;
  private String markpicture;
  private String creatorId;
  private String creator;
  private String createTime;
  private long status;
  private String creatorUnit;
  private String title;
  private double differenceLevel;
  private double difficultyLevel;
  private double guesssLevel;
  private String answer;
  private String codelang;
  private String libTypeId;
  private String libTypeName;
  private String lastWritorId;
  private String lastWritor;
  private String lastWriteTime;
  private String saveCatelog;
  private String codelangversion;
  private long publistimes;
  private long plananswertime;
  private String answerStandard0204;
  private String scoreStandard0204;
}
