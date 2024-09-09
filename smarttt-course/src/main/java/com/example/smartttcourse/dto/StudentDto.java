package com.example.smartttcourse.dto;

import com.example.smartttcourse.pojo.CmClassroomStudent;
import com.example.smartttcourse.pojo.SmStudent;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lunSir
 * @create 2024-09-08 19:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto{
    // 学生表中的id（stuId）
    private String id;
    // 学生是属于哪一个班级 相当与班级ID
    private String obsid;
    //学生ID
    private String usersid;
    // 姓名
    private String username;
    // 班级
    private String obsname;
    // 专业名称
    private String proname;
    // 登录名称
    private String loginname;
}
