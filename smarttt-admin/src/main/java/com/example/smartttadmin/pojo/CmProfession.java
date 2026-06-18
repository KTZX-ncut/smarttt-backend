package com.example.smartttadmin.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CmProfession", description = "专业信息")
public class CmProfession {

  @ApiModelProperty(value = "专业记录 ID", example = "pro-001")
  private String id;
  @ApiModelProperty(value = "对应组织节点 ID", example = "531500340-obs")
  private String obsid;
  @ApiModelProperty(value = "专业名称", example = "软件工程")
  private String proname;
  @ApiModelProperty(value = "专业代码", example = "080902")
  private String procode;
  @ApiModelProperty(value = "达成度阈值或比例", example = "0.7")
  private String reachpercent;
  @ApiModelProperty(value = "备注", example = "国家一流专业")
  private String remark;
  @ApiModelProperty(value = "创建时间", example = "2026-05-09 10:00:00")
  private String createtime;

  public CmProfession(SmObs smObs){
    this.obsid = smObs.getId();
    this.proname = smObs.getObsname();
    this.remark = smObs.getRemark();
  }

}
