package com.example.smartttadmin.dto;

import com.example.smartttadmin.pojo.StLevel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolInforReq {
    private String id;
    private String obsname;
    private String remark;
    private List<StLevel> stLevelList;
}
