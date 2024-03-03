package com.example.smartttadmin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonnelRoster {
    private String id;
    private String obsid;
    private String username;
    private String loginname;
    private String phone;
    private String status;
    private String catelog;
    private String obsname;
}
