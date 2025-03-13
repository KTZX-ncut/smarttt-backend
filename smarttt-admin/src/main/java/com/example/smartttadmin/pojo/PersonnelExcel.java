package com.example.smartttadmin.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 读取导入人员的excel实体类
 * @author lunSir
 * @create 2024-10-18 12:40
 * @Desc 具有原子性，不应该被污染！！
 */
@Data
@EqualsAndHashCode
public class PersonnelExcel {
    @ExcelProperty("序号")
    private String row;
    @ExcelProperty("用户名")
    private String username;
    @ExcelProperty("登录名称")
    private String loginname;
    @ExcelProperty("密码")
    private String pwd;
    // 如果导入的是学生，编号就是学号，如果是教师，编号就是工号
    @ExcelProperty("编号")
    private String personnelno;
    @ExcelProperty("手机号")
    private String phone;
    @ExcelProperty("分类")
    private String catelog;
    @ExcelProperty("状态")
    private String status;
    @ExcelProperty("所属院系")
    private String obsname;
    @ExcelProperty("备注")
    private String remark;
}
