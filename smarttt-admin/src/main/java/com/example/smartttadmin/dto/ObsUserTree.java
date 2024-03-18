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
public class ObsUserTree {
    private String id;
    private String pid;
    private long orderno;
    private String obsname;
    private List<StUsers> stUsersList;
    private List<ObsUserTree> children;
}
