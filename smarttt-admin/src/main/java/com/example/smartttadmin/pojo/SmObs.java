package com.example.smartttadmin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 教学单位管理
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmObs {

  private String id;
  private String pid;
  private long orderno;
  private long obsdeep;
  private String obsname;
  private String obspath;
  private String levelcode;
  private String createtime;
  private String remark;

}
