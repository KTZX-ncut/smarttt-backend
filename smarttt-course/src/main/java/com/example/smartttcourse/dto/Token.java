package com.example.smartttcourse.dto;

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
@ApiModel(value = "Token", description = "登录后生成或解析出的认证信息")
public class Token {
    @ApiModelProperty(value = "用户 ID", example = "516761049-user")
    private String id;
    @ApiModelProperty(value = "角色 ID", example = "516761049-role")
    private String roleid;
    @ApiModelProperty(value = "当前课程、课堂或组织节点 ID", example = "course-001")
    private String obsid;
    @ApiModelProperty(value = "组织层级深度", example = "3")
    private long obsdeep;
    @ApiModelProperty(value = "当前学期 ID", example = "term-2025-2")
    private String termid;
}
