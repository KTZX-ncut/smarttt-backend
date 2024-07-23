package com.example.smartttcourse.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CmClassroomStudent {

  private String id;
  private String classroomId;
  private String userId;
  private String obsId;
  private String userName;
  private String obsName;
  private String proName;
  private String loginname;
  private long rowNo;
  private double courseScore;
}
