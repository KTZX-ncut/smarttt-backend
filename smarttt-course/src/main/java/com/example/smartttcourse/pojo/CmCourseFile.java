package com.example.smartttcourse.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CmCourseFile {

  private String id;
  private String obsid;
  private String filename;
  private double size;
  private String type;
  private String createtime;
  private String remark;
}
