package com.example.smartttcourse.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmLines {
    /**
     * 知识图谱连线
     */
    private String id;//知识图谱连线id
    private String startkwaid;//起始节点kwaid
    private String startunitid;//起始节点unitid
    private String endkwaid;//终止节点kwaid
    private String endunitid;//终止节点unitid
    private String remark;//备注
    private String courseid;//课程id

}
