package com.example.smartttadmin.dto;

import lombok.*;

/**
 * 用户登录需要输入的信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginReq {
    private String loginname;
    private String pwd;
    private String loginway;
    private String catelog;

}
