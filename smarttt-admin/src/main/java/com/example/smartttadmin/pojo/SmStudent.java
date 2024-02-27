package com.example.smartttadmin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmStudent {

  private String id;
  private String obsid;
  private String usersid;
  private String stuno;
  private String classno;
  private String proname;
  private String proid;
  private String schoolname;
  private String collegesname;
  private String status;
  private String submittime;
  private String isselfreg;
  private String createtime;
}
