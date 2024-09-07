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
  // 姓名
  private String userName;
  // 班级
  private String obsName;
  private String proName;
  // 登录名称
  private String loginname;
  private long rowNo;
  private double courseScore;
}
