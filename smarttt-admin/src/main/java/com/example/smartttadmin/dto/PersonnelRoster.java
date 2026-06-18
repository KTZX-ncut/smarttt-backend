package com.example.smartttadmin.dto;

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
@ApiModel(value = "PersonnelRoster", description = "人员档案信息，可用于教师或学生新增、编辑")
public class PersonnelRoster {
    @ApiModelProperty(value = "用户 ID，新增时可不传，编辑时传已有用户 ID", example = "516761049-user")
    private String id;//userid
    @ApiModelProperty(value = "所属组织节点 ID", example = "531500340-obs")
    private String obsid;
    @ApiModelProperty(value = "姓名", example = "张三")
    private String username;
    @ApiModelProperty(value = "登录账号", example = "zhangsan")
    private String loginname;
    @ApiModelProperty(value = "登录密码，新增时使用", example = "123456")
    private String pwd;
    @ApiModelProperty(value = "手机号", example = "13800000000")
    private String phone;
    @ApiModelProperty(value = "状态", example = "1")
    private String status;
    @ApiModelProperty(value = "人员类别，1 表示学生，其他值通常表示教师", example = "0")
    private String catelog;
    @ApiModelProperty(value = "所属组织名称", example = "计算机学院")
    private String obsname;
    @ApiModelProperty(value = "备注", example = "新入职教师")
    private String remark;
    @ApiModelProperty(value = "创建时间", example = "2026-05-09 10:00:00")
    private String createtime;
    @ApiModelProperty(value = "工号或学号", example = "T20260001")
    private String personnelno;//工号
}
