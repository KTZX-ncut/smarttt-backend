package com.example.smartttadmin.dto;

import com.example.smartttadmin.pojo.StUsers;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "UserInforReq", description = "教师端用户信息查询参数")
public class UserInforReq {
    @ApiModelProperty(value = "用户 ID", example = "516761049-xxxx")
    private String id;
    @ApiModelProperty(value = "角色 ID", example = "516761049-role")
    private String roleid;
    @ApiModelProperty(value = "所属组织节点 ID", example = "531500340-obs")
    private String obsid;
    @ApiModelProperty(value = "组织层级深度", example = "3")
    private long obsdeep;
    @ApiModelProperty(value = "用户类别", example = "0")
    private String catelog;
    @ApiModelProperty(value = "学期 ID", example = "2024-2025-2")
    private String termid;

    public UserInforReq(StUsers stUsers) {
        this.id = stUsers.getId();
        this.catelog = stUsers.getCatelog();
    }
}
