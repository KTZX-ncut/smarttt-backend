package com.example.smartttcourse.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 学期管理信息列表
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmTerm {
    private String id;//学期的id
    private String termname;//学期名称
    private LocalDate begindate;//开始时间
    private LocalDate enddate;//结束时间
    private String remark;//备注
    private String iscurrentterm;
    private String createtime;

}