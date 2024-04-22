package com.example.smartttadmin.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmClass {

  private String id;
  private String obsid;
  private String classname;
  private String grade;
  private String remark;

    public CmClass(SmObs smObs) {
      this.obsid = smObs.getId();
      this.classname = smObs.getObsname();
      this.remark = smObs.getRemark();
    }
}
