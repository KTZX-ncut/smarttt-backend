package com.example.smartttevaluation.pojo;

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
    private String startid;//起始节点id
    private String endid;//终止节点id
    private String remark;//备注
    private String courseid;//课程id

}
