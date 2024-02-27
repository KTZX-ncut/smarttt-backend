package com.example.smartttadmin.pojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StRoles {

  private String id;
  private String rolecode;
  private String rolename;
  private String remark;
  private String homename;
  private String homeurl;
  private String createtime;
  private String by1;
  private String by2;

}
