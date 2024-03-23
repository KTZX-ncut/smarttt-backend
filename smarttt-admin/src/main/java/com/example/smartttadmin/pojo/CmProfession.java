package com.example.smartttadmin.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmProfession {

  private String id;
  private String obsid;
  private String proname;
  private String procode;
  private String reachpercent;
  private String remark;
  private String createtime;

}
