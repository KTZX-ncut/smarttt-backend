package com.example.smartttevaluation.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CmClassroomStudent {

  private String id;
  @TableField(value = "classroomId")
  private String classroomId;
  @TableField(value = "userId")
  private String userId;
  @TableField(value = "obsId")
  private String obsId;
  // 姓名
  @TableField(value = "userName")
  private String userName;
  // 班级
  @TableField(value = "obsName")
  private String obsName;
  // 专业
  @TableField(value = "proName")
  private String proName;
  // 登录名称
  @TableField(value = "loginname")
  private String loginname;
  @TableField(value = "rowNo")
  private long rowNo;
  @TableField(value = "courseScore")
  private double courseScore;
  @TableField(value = "dynamic_state")
  private Integer dynamic_state;
  @TableField(value = "reach_state")
  private Integer reach_state;
}
