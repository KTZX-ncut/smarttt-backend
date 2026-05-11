package com.example.smartttadmin.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "StRoleUser", description = "负责人或用户角色绑定关系")
public class StRoleUser {

  @ApiModelProperty(value = "主键 ID", example = "role-user-001")
  private String id;
  @ApiModelProperty(value = "用户 ID", example = "516761049-user")
  private String userid;
  @ApiModelProperty(value = "角色 ID", example = "516761049-role")
  private String roleid;
  @ApiModelProperty(value = "组织节点 ID", example = "531500340-obs")
  private String obsid;
  @ApiModelProperty(value = "组织层级深度", example = "3")
  private long obsdeep;
  @ApiModelProperty(value = "创建时间", example = "2026-05-09 10:00:00")
  private String createtime;
  @ApiModelProperty(value = "学期 ID", example = "2024-2025-2")
  private String termid;

}
