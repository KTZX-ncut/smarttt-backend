package com.example.smartttevaluation.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.james.mime4j.dom.datetime.DateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 课堂的试卷
public class CmTestpaper {
    private String id;  // 标识
    private String name;    // 试卷名称
    private String makeModeId;  // 组卷方式id
    private String testModeId;  // 考试方式id
    private int questionsCount;  // 试题总数
    private int totalScore;  // 试卷总分
    private int status; // 试题状态
    private int locked; // 是否锁定
    private String courseId;    // 课程id
    private String creatorId;   // 创建者id
    private String creator;     // 创建人
    private String createTime;    // 创建时间
    private String catelog;     // 试卷类型
}
