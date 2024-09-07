package com.example.smartttcourse.dto;

import lombok.Data;
import org.apache.poi.hpsf.Decimal;

/**
 * @author lunSir
 * @create 2024-09-07 13:47
 */
@Data
public class CreateStudentDto {
    private String id;
    private String classroomId; // 课堂ID
    private String userId;
    private String obsId; // 班级id
    private String userName; // 姓名
    private String obsName; // 班级
    private String proName; // 专业名称
    private String loginname; // 登录名称
    private Integer rowno; // 序号
    private Decimal courseScore; //课程综合得分
}
