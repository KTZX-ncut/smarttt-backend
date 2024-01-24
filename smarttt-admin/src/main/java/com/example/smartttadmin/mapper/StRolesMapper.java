package com.example.smartttadmin.mapper;

import com.example.smartttadmin.dto.SimpleRole;
import com.example.smartttadmin.pojo.StRoles;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StRolesMapper {
    /**
     * 获取一个用户的角色列表
     * @param userid 用户id
     * @return 简化版角色列表
     */
    @Select("select * from st_roles left join st_roleuser on st_roles.id = st_roleuser.roleid where userid = #{userid}")
    List<SimpleRole> getRolesByUserID(Integer userid);

    /**
     * 全部角色的信息
     * @return 角色的信息列表
     */
    @Select("select rolecode,rolename,remark from st_roles")
    List<StRoles> getRoles ();
}
