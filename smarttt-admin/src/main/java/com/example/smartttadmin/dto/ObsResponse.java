package com.example.smartttadmin.dto;

import com.example.smartttadmin.pojo.StUsers;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 返回的学院列表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ObsResponse {
    private String id;
    private String levelcode;
    private String obsname;
    private String remark;
    private List<StUsers> stUsersList;
}
