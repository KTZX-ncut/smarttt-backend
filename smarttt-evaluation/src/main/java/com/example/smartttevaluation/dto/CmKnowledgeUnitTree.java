package com.example.smartttevaluation.dto;

import com.example.smartttevaluation.pojo.CmKnowledgeUnit;
import com.example.smartttevaluation.pojo.CmKnowledgeUnitKwa;
import com.example.smartttevaluation.pojo.CmKnowledgeUnitKwa;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmKnowledgeUnitTree {
    String id;
    String name;
    String type;
    double datavalue;
    long ordernum;
    List<CmKnowledgeUnitKwa> kwas;
    List<CmKnowledgeUnitTree> children;
}
