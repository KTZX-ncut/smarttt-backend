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
    private long orderno;
    private String name;
    private String levelcode;
    private List<CmCheckitemTree> children;

}
