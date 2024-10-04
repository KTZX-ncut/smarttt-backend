package com.example.smartttcourse.pojo;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 我的作业和考试表(CmClassroomMypracticelist)表实体类
 *
 * @author makejava
 * @since 2024-09-06 16:36:20
 */
@Data
public class CmClassroomMypracticelist{
    //标识
    private String id;
    //课堂Id
    private String classroomid;
    //作业Id
    private String testid;
    //试卷Id
    private String paperid;
    //学生Id
    private String stuid;
    //已完成人数
    private Integer finishedcount;
    //未完成人数
    private Integer unfinishedcount;
    //完成状态  1.完成  2.未完成
    private Integer myfinishstatus;
    //完成顺序号
    private Integer submitno;
    //正确题数
    private Integer correctcount;
    //错误题数
    private Integer errorcount;
    //正确率
    private Double correctpercent;
    //成绩等级  1-优秀，2-良好，3-合格，4-不合格
    private String resultlevel;
    //题目
    private String title;
    //布置时间
    private String begintime;
    //结束时间
    private String endtime;
    //我的答卷
    private String myanswers;
    //提交试卷时间
    private String submittime;
    //作业和考试分类  1-作业，2-考试
    private String catelog;
    //成绩得分
    private Integer resultscore;
    //批阅结果
    private String checkresult;
    //批阅人Id
    private String checkerid;
    //提交阅卷时间
    private String checktime;
    //开始答卷时间
    private String beginanswertime;
    //发布时间
    private String publishtime;
}

