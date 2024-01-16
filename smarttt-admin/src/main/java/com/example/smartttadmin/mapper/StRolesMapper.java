package com.example.smartttadmin.mapper;

import com.example.smartttadmin.dto.SimpleRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StRolesMapper {
    //获取一个用户的角色列表
    @Select("select * from st_roles left join st_roleuser on st_roles.id = st_roleuser.roleid where userid = #{userid}")
    List<SimpleRole> getRolesByUserID(Integer userid);
}
