package com.example.smartttcourse.pojo;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 学生表(SmStudent)表实体类
 *
 * @author makejava
 * @since 2024-09-06 19:44:01
 */
@Data
public class SmStudent{
    //ID
    private String id;
    // 组织ID
    private String obsid;
    //学生ID
    private String usersid;
    //学号
    private String stuno;
    //班级号
    private String classno;
    //专业名称
    private String proname;
    //专业ID
    private String proid;
    //学校名称
    private String schoolname;
    //院系名称
    private String collegesname;
    //状态
    private String status;
    //提交时间
    private String submittime;
    //是否是自己注册
    private String isselfreg;
    //创建时间
    private String createtime;
}

