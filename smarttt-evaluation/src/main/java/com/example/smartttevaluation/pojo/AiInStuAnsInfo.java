package com.example.smartttevaluation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (AiInStuAnsInfo)实体类
 *
 * @author makejava
 * @since 2024-11-28 19:14:42
 */
@Data
public class AiInStuAnsInfo implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 日志表id
     */
    private String logid;
    /**
     * 课程id
     */
    private String courseid;
    /**
     * 课堂id
     */
    private String classroomid;
    /**
     * 考试id
     */
    private String testid;
    /**
     * 试卷id
     */
    private String paperid;
    /**
     * 试卷类型 def=默认
     */
    private String papertype;
    /**
     * 题目id
     */
    private String libid;
    /**
     * 学生id
     */
    private String stuid;
    /**
     * 题型
     */
    private String questiontypeid;
    /**
     * 学生答题内容
     */
    private String questioncontent;
    /**
     * 题目难度
     */
    private Double difficultlevel;
    /**
     * 题目区分度
     */
    private Double differencelevel;
    /**
     * 题目猜测度
     */
    private Double guesslevel;
    /**
     * kwaId
     */
    private String kwaid;
    /**
     * 题目与kwa关联度
     */
    private Double datavalue;
    /**
     * 题目的分数
     */
    private Integer libscore;
    /**
     * 题目学生得分
     */
    private Integer libstuscore;
    /**
     * 题目作答时长，单位秒
     */
    private Integer libanswertime;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 修改时间
     */
    private Date updatetime;

}

