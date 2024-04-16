package com.example.smartttevaluation.dto;

import com.example.smartttevaluation.pojo.CmClassroomUnit;
import com.example.smartttevaluation.pojo.CmClassroomUnitKwa;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmClassroomUnitTree {
    String id;
    String name;
    String type;
    List<CmClassroomUnitKwa> kwas;
    List<CmClassroomUnitTree> children;
}
