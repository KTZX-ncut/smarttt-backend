package com.example.smartttadmin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StMenus {

  private String id;
  private String name;
  private String pid;
  private String orderno;
  private String url;
  private String isused;
  private String createtime;
  private String levelcode;
  private String fullpath;
  private String by1;

}
