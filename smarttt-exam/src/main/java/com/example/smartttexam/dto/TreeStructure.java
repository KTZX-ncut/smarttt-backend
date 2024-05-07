package com.example.smartttexam.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.smartttexam.pojo.SmObs;

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