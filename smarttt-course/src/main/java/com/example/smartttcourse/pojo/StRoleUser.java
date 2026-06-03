package com.example.smartttcourse.pojo;

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
@ApiModel(value = "StRoleUser", description = "角色与负责人绑定信息")
public class StRoleUser {

  @ApiModelProperty(value = "主键 ID", example = "role-user-001")
  private String id;
  @ApiModelProperty(value = "用户 ID", example = "teacher-001")
  private String userid;
  @ApiModelProperty(value = "角色 ID", example = "role-course-rp")
  private String roleid;
  @ApiModelProperty(value = "课程或组织节点 ID", example = "course-001")
  private String obsid;
  @ApiModelProperty(value = "组织层级深度", example = "3")
  private long obsdeep;
  private String createtime;
  @ApiModelProperty(value = "学期 ID", example = "term-2025-2")
  private String termid;

}
