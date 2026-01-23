package com.example.smartttevaluation.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmCourseUnitVValue {
    String id;
    String unitid;
    String vid;
    String name;
    // v_ideology_value.vname，前端查看绑定时直接使用
    String vname;
    String parentId;
    Integer level;
    int status;
}

