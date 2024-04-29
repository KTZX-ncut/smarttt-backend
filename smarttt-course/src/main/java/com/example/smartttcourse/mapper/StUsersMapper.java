package com.example.smartttcourse.mapper;

import com.example.smartttcourse.dto.ResponsiblePerson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StUsersMapper {
    @Select("SELECT u.id, u.username, o.obsname, t.obsid\n" +
            "FROM sm_teacher t\n" +
            "JOIN st_users u ON t.usersid = u.id\n" +
            "JOIN sm_obs o ON t.obsid = o.id\n" +
            "WHERE t.obsid = #{obsid}\n")
    List<ResponsiblePerson> getAllTeacherByObsID(String id);
}
