package com.example.smartttadmin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StRoleUser {

  private long id;
  private String userid;
  private String roleid;
  private String createtime;
  private String by1;
}
