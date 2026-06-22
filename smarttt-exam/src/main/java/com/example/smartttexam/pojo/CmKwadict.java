package com.example.smartttexam.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmKwadict {

    private String id;
    private String name;
    private String keywordid;
    private String abilityid;
    private String courseid;
    private String datavalue;
    private String status;
    private String keywordcode;
    private String abilitycode;
    private String code;
    private LocalDateTime createTime;

    // JOIN查询时附带的关键字名称和能力名称（非数据库列）
    private String keywordName;
    private String abilityName;
}
