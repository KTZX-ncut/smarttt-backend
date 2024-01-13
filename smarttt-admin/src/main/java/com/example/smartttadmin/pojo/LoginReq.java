package com.example.smartttadmin.pojo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginReq {
    private String username;
    private String pwd;
    private String loginway;
    private String catelog;
}
