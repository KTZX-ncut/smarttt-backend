package com.example.smartttadmin.mapper;

import com.example.smartttadmin.dto.SimpleRole;
import com.example.smartttadmin.dto.TermRoles;
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
    @Insert("insert into st_roles(id, rolename, rolecode, remark, homename, homeurl, createtime,by2) values (#{id}, #{rolename}, #{rolecode}, #{remark}, #{homename}, #{homeurl}, #{createtime},#{by2})")
    void createRoleTable(StRoles stRoles);

    /**
     * 加入角色的权限的记录
     * @param stRoleMenu
     */
    @Insert("INSERT INTO st_rolemenu(id,roleid,menuid,status,createtime)" +
            "values (#{id},#{roleid},#{menuid},#{status},#{createtime})")
    void createRoleMenus(StRoleMenu stRoleMenu);
    /**
     * 获取一个用户的角色列表，不用改，就是获取当前的
     * @param userid 用户id
     * @return 简化版角色列表
     */
    @Select("select * from st_roles left join st_roleuser on st_roles.id = st_roleuser.roleid where userid = #{userid}")
    List<SimpleRole> getRolesByUserID(String userid);

    /**
     * 全部角色的信息
     * @return 角色的信息列表
     */
    @Select("select id,rolecode,rolename,homename,homeurl,remark,by2 from st_roles")
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

    /**
     * 获取用户的历史学期列表（通过课程关联查询）
     * @param id 用户ID
     * @return 学期列表
     */
    @Select("SELECT DISTINCT cm_term.id as id, IF(cm_term.termname IS NULL,'全部',cm_term.termname) as termName " +
            "FROM st_roleuser " +
            "INNER JOIN cm_course ON cm_course.professionId = st_roleuser.obsid " +
            "INNER JOIN cm_term ON cm_term.id = cm_course.schooltermId " +
            "WHERE st_roleuser.userid = #{id} AND cm_term.iscurrentterm != '1'")
    List<TermRoles> getTermList(String id);


    /**
     * 获取指定学期的历史角色列表
     * @param userid 用户ID
     * @param termid 学期ID
     * @return 角色列表
     */
    @Select("SELECT st_roleuser.id, st_roleuser.roleid, st_roles.rolename, st_roleuser.obsid, st_roleuser.obsdeep " +
            "FROM st_roleuser " +
            "INNER JOIN st_roles ON st_roleuser.roleid = st_roles.id " +
            "INNER JOIN cm_course ON cm_course.professionId = st_roleuser.obsid " +
            "WHERE st_roleuser.userid = #{userid} " +
            "AND cm_course.schooltermId = #{termid}")
    List<SimpleRole> getHistoryRoles(@Param("userid") String userid, @Param("termid") String termid);

    void deleteRolesByObsid(@Param("ids")List<String> deleteList);
}
