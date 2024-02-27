package com.example.smartttadmin.service;

import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.StRoles;
import com.example.smartttadmin.pojo.StUsers;

public interface StRolesService {
    /**
     * 在登录功能里查找某用户的简化版角色列表
     * @param stUsers 用户
     * @return 该用户的角色的简化版列表
     */
    Result getSimpleRolesList(StUsers stUsers);

    /**
     * 超级管理员的角色授权功能里查找角色的全部信息
     * @return 角色列表
     */
    Result getStRolesList();

    /**
     * 新建角色
     * @param stRoles 角色
     * @return ...
     */
    Result createRole(StRoles stRoles);
}
