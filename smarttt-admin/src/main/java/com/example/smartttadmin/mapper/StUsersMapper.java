package com.example.smartttadmin.mapper;

import com.example.smartttadmin.pojo.StUsers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StUsersMapper {
    @Select("select * from st_users where username=#{username} and pwd = #{pwd} ")
    StUsers Selectlogin(StUsers stUsers);
}
