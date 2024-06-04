package com.example.smartttevaluation.dto;

import com.example.smartttadmin.dto.SimpleRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Token {
    private String id;
    private String roleid;
    private String obsid;
    private long obsdeep;


    public Token(SimpleRole simpleRole) {
        this.roleid = simpleRole.getRoleid();
        this.obsid = simpleRole.getObsid();
        this.obsdeep = simpleRole.getObsdeep();
    }
}
