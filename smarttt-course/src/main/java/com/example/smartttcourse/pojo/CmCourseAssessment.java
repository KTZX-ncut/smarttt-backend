package com.example.smartttcourse.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmCourseAssessment {
    /**
     * 考核方案表格
     */
    private String id;
    private String courseid;
    private String coursetargetId;
    private String checkitemId;
    private Integer standardScore;
}
