package com.example.smartttadmin.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CmClass", description = "班级信息")
public class CmClass {

  @ApiModelProperty(value = "班级记录 ID", example = "class-001")
  private String id;
  @ApiModelProperty(value = "对应组织节点 ID", example = "531500340-obs")
  private String obsid;
  @ApiModelProperty(value = "班级名称", example = "软件工程 2201 班")
  private String classname;
  @ApiModelProperty(value = "年级", example = "2022")
  private String grade;
  @ApiModelProperty(value = "备注", example = "本科班级")
  private String remark;

    public CmClass(SmObs smObs) {
      this.obsid = smObs.getId();
      this.classname = smObs.getObsname();
      this.remark = smObs.getRemark();
    }
}
