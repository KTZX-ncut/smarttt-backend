package com.example.smartttadmin.dto;

import com.example.smartttadmin.pojo.EncryptField;
import com.example.smartttadmin.pojo.EncryptFieldSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 简化版的角色列表，用于登录成功之后返回的角色列表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleRole {
    private String rolename;
    private String roleid;
    private String homeurl;
}
