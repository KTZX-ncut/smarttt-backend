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
@ApiModel(value = "StRoles", description = "系统角色信息")
public class StRoles {

  @ApiModelProperty(value = "角色记录 ID", example = "role-001")
  private String id;
  @ApiModelProperty(value = "角色编码", example = "admin")
  private String rolecode;
  @ApiModelProperty(value = "角色名称", example = "系统管理员")
  private String rolename;
  @ApiModelProperty(value = "角色备注", example = "拥有后台全部权限")
  private String remark;
  @ApiModelProperty(value = "首页名称", example = "管理首页")
  private String homename;
  @ApiModelProperty(value = "首页路由", example = "/home")
  private String homeurl;
  @ApiModelProperty(value = "创建时间", example = "2026-05-09 10:00:00")
  private String createtime;
  @ApiModelProperty(value = "扩展字段 1")
  private String by1;
  @ApiModelProperty(value = "扩展字段 2")
  private String by2;

}
