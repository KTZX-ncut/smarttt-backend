package com.example.smartttadmin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SmObsTree {
    private String id;
    private String pid;
    private Integer orderno;
    private long obsdeep;
    private String obsname;
//    private String obspath;
    private String levelcode;
//    private String createtime;
//    private String remark;
    private List<SmObsTree> children;
}
