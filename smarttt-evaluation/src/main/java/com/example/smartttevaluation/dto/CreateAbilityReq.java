package com.example.smartttevaluation.dto;

import com.example.smartttevaluation.pojo.CmAbility;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CreateAbilityReq {
    private String id;
    private String pid;
    private long abilitydeep;
    private String type;
    private CmAbility cmAbility;

}
