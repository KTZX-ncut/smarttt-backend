package com.example.smartttevaluation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmCheckitemTree {

    private String id;
    private String pid;
    private int orderno;
    private int checkitemdeep;
    private String itemName;
    private String levelcode;
    private Boolean task;
    private String remark;
    private List<CmCheckitemTree> children;

}
