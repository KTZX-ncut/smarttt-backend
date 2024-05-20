package com.example.smartttadmin.service;

import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.Token;
import com.example.smartttadmin.dto.UpdateMenuReq;

public interface StMenusService {
    /**
     * 登录到首页后，返回角色的全部侧边栏信息
     *
     * @param roleid ...
     * @param roleid
     * @return 侧边栏
     */
    Result getMenusList(String roleid);
    /**
     * 超级管理员的角色授权功能里，为某角色配置权限，返回层级菜单，并带上该角色的状态码
     * @param id 角色代码
     * @return 层级菜单
     */
    Result getMenuTree(String id);
    /**
     * 超级管理员的角色授权功能里，修改权限状态，更新某角色的权限菜单状态，不用返回数据
     * @param updateMenuReq 更新菜单
     * @return 成功
     */
    Result updateMenuStatus(UpdateMenuReq updateMenuReq);

    Result getStudentCourse(String id);

    Result getStudentCourseInfor(Token token);
}
