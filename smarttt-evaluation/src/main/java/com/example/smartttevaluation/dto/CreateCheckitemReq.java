package com.example.smartttevaluation.dto;


import com.example.smartttevaluation.pojo.CmCheckitem;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateCheckitemReq {

    private String id;
    private String pid;
    private String type;
    private long checkitemdeep;
    private CmCheckitem cmCheckitem;
}
