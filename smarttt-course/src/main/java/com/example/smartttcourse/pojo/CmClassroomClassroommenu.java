package com.example.smartttcourse.pojo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 课堂菜单表(CmClassroomClassroommenu)表实体类
 *
 * @author makejava
 * @since 2024-09-06 16:24:48
 */
@Data
public class CmClassroomClassroommenu {
    //标识
    private String id;
    //学生Id
    private String stuid;
    //在教师端选择课堂学员的时候复制过来
    private String classroomid;
    //未完成任务数
    private Integer taskcount;
    //菜单Id
    private String menuid;
}

