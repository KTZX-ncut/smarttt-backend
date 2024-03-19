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
public class CmAbilityTree {

    private String id;
    private String pid;
    private long orderno;
    private long abilitydeep;
    private String name;
    private String levelcode;
    private List<CmAbilityTree> children;

}