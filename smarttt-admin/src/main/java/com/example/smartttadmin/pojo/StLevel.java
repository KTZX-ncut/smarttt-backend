package com.example.smartttadmin.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "StLevel", description = "组织层级配置")
public class StLevel {

  @ApiModelProperty(value = "层级记录 ID", example = "1")
  private long id;
  @ApiModelProperty(value = "层级名称", example = "学院")
  private String levelname;
  @ApiModelProperty(value = "层级深度", example = "2")
  private long obsdeep;
  @ApiModelProperty(value = "是否允许教师角色，1 表示允许", example = "1")
  private String teacher;
  @ApiModelProperty(value = "是否允许学生角色，1 表示允许", example = "1")
  private String student;

}
