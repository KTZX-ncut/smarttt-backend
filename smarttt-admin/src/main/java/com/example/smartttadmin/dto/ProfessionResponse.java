package com.example.smartttadmin.dto;

import com.example.smartttadmin.pojo.StUsers;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfessionResponse {
    private String id;//专业对应的obsid
    private String proname;
    private String procode;
    private String reachpercent;
    private String remark;
    private List<ResponsiblePerson> responsiblePersonList;
}
