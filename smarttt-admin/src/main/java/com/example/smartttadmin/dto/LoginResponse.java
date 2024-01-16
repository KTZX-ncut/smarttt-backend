package com.example.smartttadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String useid;
    private String catelog;
    private Integer rolescount;
    private List<SimpleRole> simpleRoleList;
}
