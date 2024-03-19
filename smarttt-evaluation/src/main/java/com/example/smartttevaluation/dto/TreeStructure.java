package com.example.smartttevaluation.dto;

import com.example.smartttevaluation.pojo.SmObs;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeStructure {
    private String id;
    private String pid;
    private long orderno;

    public TreeStructure(SmObs smObs) {
        this.id = smObs.getId();
        this.pid = smObs.getPid();
        this.orderno = smObs.getOrderno();
    }
}