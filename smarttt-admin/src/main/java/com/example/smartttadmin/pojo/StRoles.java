package com.example.smartttadmin.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StRoles {

  private long id;
  private String rolename;
  private String rolecode;
  private String remark;
  private String homename;
  private String homeurl;
  private String createtime;
  private String by1;
  private String by2;

}
