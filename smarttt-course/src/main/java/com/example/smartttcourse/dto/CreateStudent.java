package com.example.smartttcourse.dto;

import com.google.common.base.Preconditions;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lunSir
 * @create 2024-09-08 23:02
 */
@Data
@ApiModel(value = "CreateStudent", description = "学生加入课堂请求参数")
public class CreateStudent {
    @ApiModelProperty(value = "课堂 ID", example = "classroom-001")
    private String classRoomId;
    @ApiModelProperty(value = "学生用户 ID", example = "student-001")
    private String usersid;
    @ApiModelProperty(value = "学生姓名", example = "张三")
    private String username;
    @ApiModelProperty(value = "班级名称", example = "软件工程 2201 班")
    private String obsname;
    @ApiModelProperty(value = "专业名称", example = "软件工程")
    private String proname;
    @ApiModelProperty(value = "登录账号", example = "20220001")
    private String loginname;
    @ApiModelProperty(value = "班级 ID", example = "class-001")
    private String obsid; // 班级id
    @ApiModelProperty(value = "座位号或名单顺序", example = "1")
    private long rowNo;
    @ApiModelProperty(value = "课程成绩", example = "95")
    private double courseScore;
}
