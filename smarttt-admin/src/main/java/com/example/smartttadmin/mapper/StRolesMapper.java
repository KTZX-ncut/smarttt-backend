package com.example.smartttadmin.mapper;

import com.example.smartttadmin.pojo.StRoles;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StRolesMapper {
    @Select("select * from st_roles left join st_roleuser on st_roles.id = st_roleuser.roleid and userid = #{userid}")
    List<StRoles> GetRolesByUserID(Integer userid);
}
