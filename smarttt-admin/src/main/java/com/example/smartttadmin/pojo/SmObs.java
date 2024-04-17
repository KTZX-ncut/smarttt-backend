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

  public SmObs(String id, String pid, long obsdeep, String obsname, String remark) {
    this.id = id;
    this.pid = pid;
    this.obsdeep = obsdeep;
    this.obsname = obsname;
    this.remark = remark;
  }

    public SmObs(CmClass cmClass) {
      this.id = cmClass.getObsid();
      this.obsname = cmClass.getClassname();
      this.remark = cmClass.getRemark();
    }
    public SmObs(CmProfession cmProfession) {
      this.id = cmProfession.getObsid();
      this.obsname = cmProfession.getProname();
      this.remark = cmProfession.getRemark();
    }

    public SmObs(SmObs smObs) {
      this.id= smObs.getId();
      this.remark = smObs.getRemark();
      this.obsname =smObs.getObsname();
    }
}
