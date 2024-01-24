package com.example.smartttadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取一个用户的全部必要信息，需要的请求参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginHomeReq
{
    private String userid;

    private String roleid;

    private String catelog;
}
