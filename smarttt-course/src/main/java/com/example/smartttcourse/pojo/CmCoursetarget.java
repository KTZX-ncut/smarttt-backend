package com.example.smartttcourse.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmCoursetarget {
    /**
     * 课程目标信息列表
     */
    private String id;//课程目标id
    private String code;//代码
    private String name;//课程目标名称
    private String unitid;
    private String unitname;
    private String remark;//备注
    private String courseid;//课程id
    private List<CmKwadict> kwas;
    private LocalDateTime createTime;
}
