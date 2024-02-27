package com.example.smartttadmin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StRoleMenu {

  private String id;
  private String roleid;
  private String menuid;
  private String status;
  private String createtime;
  private String by1;
}
