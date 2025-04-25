package com.example.smartttcourse.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmCheckitem {

    /**
     * 考核项信息列表
     */
    private String id;//考核项id
    private String pid;//父节点
    private long orderno;//顺序号
    private String itemName;//考核项名称
    private String itemCode;//层级码
    private String remark;//备注
    private long checkitemdeep;
    private Boolean task;//考核任务
    private String courseid;//课程id
    private List<CmCheckitem> children;
    int percent;
}
