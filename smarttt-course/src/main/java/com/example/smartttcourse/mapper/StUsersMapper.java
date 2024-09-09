package com.example.smartttcourse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.smartttcourse.dto.ResponsiblePerson;
import com.example.smartttcourse.dto.StudentDto;
import com.example.smartttcourse.pojo.StRoleUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StUsersMapper{
    @Select("SELECT u.id, u.username, o.obsname, t.obsid\n" +
            "FROM sm_teacher t\n" +
            "JOIN st_users u ON t.usersid = u.id\n" +
            "JOIN sm_obs o ON t.obsid = o.id\n" +
            "WHERE t.obsid = #{obsid}\n")
    List<ResponsiblePerson> getAllTeacherByObsID(String id);

    @Insert("insert into st_roleuser(id, userid, roleid, obsid, obsdeep, createtime) values (#{id}, #{userid}, #{roleid}, #{obsid}, #{obsdeep}, #{createtime})")
    void createOneRoleUser(StRoleUser stRoleUser);

    @Delete("delete from st_roleuser where obsid = #{obsid} and userid = #{userid} and roleid = #{roleid}")
    void deletePRByObsIDAndUserID(StRoleUser stRoleUser);

    @Select("SELECT username FROM  st_users where id = #{usersid}")
    String getUsernameById(@Param("usersid") String usersid);

    @Select("SELECT s.id,s.obsid,s.usersid,c.proname,c.obsName obsname,c.loginname,c.userName username FROM sm_student s\n" +
            "JOIN st_users u\n" +
            "ON s.usersid = u.id\n" +
            "JOIN sm_obs o \n" +
            "ON s.obsid = o.id\n" +
            "JOIN cm_classroom_student c \n" +
            "ON s.usersid = c.userId\n" +
            "WHERE s.obsid = #{obsid}")
    List<StudentDto> getAllStudentByObsID(@Param("obsid") String id);

    @Select("SELECT loginname FROM st_users WHERE id=#{id}")
    String getloginNameById(@Param("id") String id);
}
