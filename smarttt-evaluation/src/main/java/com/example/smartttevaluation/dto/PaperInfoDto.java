package com.example.smartttevaluation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PaperInfoDto {
    private String paperId;
    private String createTime;
    private String catelog;
    private Integer row;
}
