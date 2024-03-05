package com.example.smartttadmin.dto;

import com.example.smartttadmin.pojo.SmObs;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateUnitsReq {
    private String id;
    private String pid;
    private long obsdeep;
    private String type;
    private SmObs smObs;
}
