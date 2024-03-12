package com.example.smartttcourse.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 课程管理信息列表
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmCourse {
    private String id;//课程id
    private String term;//学期
    private String coursechinesename;//课程名称（中文）
    private String courseenglishname;//课程名称（英文）
    private String coursecode;//课程代码
    private String professionname;//所属专业
    private String dutymanid;//课程负责人id
    private String dutyman;//课程负责人
    private String createtime;
}
