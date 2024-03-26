package com.example.smartttadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginToken {
    private String loginname;
    private String pwd;
    private String loginway;
    private String catelog;
    //或者传加密过的id
    private String roleid;
    private String obsid;
    private long obsdeep;
}
