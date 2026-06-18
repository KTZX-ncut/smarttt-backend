package com.example.smartttadmin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 用户登录需要输入的信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "LoginReq", description = "登录请求参数")
public class LoginReq {
    @ApiModelProperty(value = "登录账号", example = "admin")
    private String loginname;
    @ApiModelProperty(value = "登录密码", example = "123456")
    private String pwd;
    @ApiModelProperty(value = "登录方式", example = "pwd")
    private String loginway;
    @ApiModelProperty(value = "用户类别，1 表示学生，其他值通常表示教师/管理员", example = "0")
    private String catelog;

}
