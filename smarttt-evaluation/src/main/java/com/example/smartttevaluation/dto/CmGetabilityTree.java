package com.example.smartttevaluation.dto;

import com.example.smartttevaluation.pojo.CmGetability;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmGetabilityTree {

    private String id;
    private String name;
    private String datavalue;
    private String importantlevel;
    private String levelcode;
    private String remark;
    private String courseid;
    private List<CmGetabilityTree> children;
}