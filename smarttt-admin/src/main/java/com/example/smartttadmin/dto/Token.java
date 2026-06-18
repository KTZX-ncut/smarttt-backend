package com.example.smartttadmin.dto;

import com.example.smartttadmin.pojo.StRoleUser;
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
    @ApiModelProperty(value = "当前角色 ID", example = "516761049-role")
    private String roleid;
    @ApiModelProperty(value = "当前组织节点 ID", example = "531500340-obs")
    private String obsid;
    @ApiModelProperty(value = "当前组织层级深度", example = "3")
    private long obsdeep;
    @ApiModelProperty(value = "当前学期 ID", example = "2024-2025-2")
    private String termid;


    public Token(SimpleRole simpleRole,String termid) {
        this.roleid = simpleRole.getRoleid();
        this.obsid = simpleRole.getObsid();
        this.obsdeep = simpleRole.getObsdeep();
        this.termid = termid;
    }
}
