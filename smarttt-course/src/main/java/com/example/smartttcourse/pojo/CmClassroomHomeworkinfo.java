package com.example.smartttcourse.pojo;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 课堂作业表(CmClassroomHomeworkinfo)表实体类
 *
 * @author makejava
 * @since 2024-09-06 16:36:20
 */
@Data
public class CmClassroomHomeworkinfo {
    //标识
    private String id;
    //课堂Id
    private String classroomid;
    //在教师端选择课堂学员的时候复制过来
    private String stuid;
    //已布置作业次数
    private Integer publishtimes;
    //完成次数
    private Integer finishedtimes;
    //完成率
    private Double finishedpercent;
    //平均正确数
    private Double correctpercent;
}

