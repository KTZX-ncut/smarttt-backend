package com.example.smartttadmin.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 教学单位管理
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SmObs", description = "组织机构或教学单位节点信息")
public class SmObs {

  @ApiModelProperty(value = "节点 ID", example = "531500340-obs")
  private String id;
  @ApiModelProperty(value = "父节点 ID", example = "531500340-parent")
  private String pid;
  @ApiModelProperty(value = "排序号", example = "1")
  private long orderno;
  @ApiModelProperty(value = "层级深度", example = "2")
  private long obsdeep;
  @ApiModelProperty(value = "节点名称", example = "计算机学院")
  private String obsname;
  @ApiModelProperty(value = "节点路径", example = "/学校/计算机学院")
  private String obspath;
  @ApiModelProperty(value = "层级编码", example = "102")
  private String levelcode;
  @ApiModelProperty(value = "创建时间", example = "2026-05-09 10:00:00")
  private String createtime;
  @ApiModelProperty(value = "备注", example = "教学单位说明")
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
