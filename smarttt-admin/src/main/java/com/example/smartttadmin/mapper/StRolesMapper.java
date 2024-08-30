package com.example.smartttadmin.mapper;

import com.example.smartttadmin.dto.SimpleRole;
import com.example.smartttadmin.dto.UserInforReq;
import com.example.smartttadmin.pojo.StRoleMenu;
import com.example.smartttadmin.pojo.StRoles;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StRolesMapper {
    /**
     * 在用户表里面加上角色的记录
     * @param stRoles 新建用户的列表
     */
    @Insert("insert into st_roles(id, rolename, rolecode, remark, homename, homeurl, createtime) values (#{id}, #{rolename}, #{rolecode}, #{remark}, #{homename}, #{homeurl}, #{createtime})")
    void createRoleTable(StRoles stRoles);

    /**
     * 加入角色的权限的记录
     * @param stRoleMenu
     */
    @Insert("INSERT INTO st_rolemenu(id,roleid,menuid,status,createtime)" +
            "values (#{id},#{roleid},#{menuid},#{status},#{createtime})")
    void createRoleMenus(StRoleMenu stRoleMenu);
    /**
     * 获取一个用户的角色列表
     * @param userid 用户id
     * @return 简化版角色列表
     */
//    @Select("select * from st_roles left join st_roleuser on st_roles.id = st_roleuser.roleid where userid = #{userid}")
    @Select("select st_roleuser.id,roleid,rolename,obsid,obsdeep from st_roleuser,st_roles where st_roleuser.roleid =st_roles.id and userid = #{userid}")
    List<SimpleRole> getRolesByUserID(String userid);

    /**
     * 全部角色的信息
     * @return 角色的信息列表
     */
    @Select("select id,rolecode,rolename,homename,homeurl,remark from st_roles")
    List<StRoles> getRoles();

    @Select("select id,rolecode,rolename,remark from st_roles")
    List<StRoles> getSimpleRoles();


    @Delete("delete from st_roles where id = #{id}")
    void deleteRoles(String id);

    void updateRoles(StRoles stRoles);

    @Select("select #{id} as id,st_roles.id as roleid,rolename,obsid,obsdeep,2 as catelog from st_roles,st_roleuser\n" +
            "where st_roles.id = st_roleuser.roleid\n" +
            " and userid = #{id};")
    List<UserInforReq> getRoleList(String id);
}
