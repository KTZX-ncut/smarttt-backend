package com.example.smartttadmin.mapper;

import com.example.smartttadmin.dto.LoginReq;
import com.example.smartttadmin.dto.PersonnelRoster;
import com.example.smartttadmin.pojo.StUsers;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StUsersMapper {
    /**
     * 用账号密码查找对应的用户
     * @param loginReq ...
     * @return 用户列表（用于判空）
     */
    @Select("select * from st_users where username=#{username} and pwd = #{pwd} and catelog = #{catelog}")
    StUsers getStUsersByUsernameAndPwdAndCatelog(LoginReq loginReq);
    @Select("select id,username,loginname from st_users where id in (select userid from st_roleuser where obsid = #{obsid} and obsdeep = 2) ")
    List<StUsers> getStUsersByobsid(String obsid);

    @Insert("insert into st_users(id,username,loginname,pwd,phone,status,catelog,remark,createtime) values " +
            "(#{id},#{username},#{loginname},#{pwd},#{phone},#{status},#{catelog},#{remark},#{createtime})")
    void createOneStUsersByPersonnelRoster(PersonnelRoster personnelRoster);

    @Insert("insert into sm_teacher(id,obsid,usersid,createtime,jobno) values (#{id},#{obsid},#{usersid},#{createtime},#{jobno})")
    void createOneSmTeacher(@Param("id")String id, @Param("obsid")String obsid, @Param("usersid")String usersid, @Param("createtime")String createtime,@Param("jobno")String jobno);

    @Insert("insert into sm_student(id,obsid,usersid,createtime,stuno) values (#{id},#{obsid},#{usersid},#{createtime},#{stuno})")
    void createOneSmStudent(@Param("id")String id, @Param("obsid")String obsid, @Param("usersid")String usersid, @Param("createtime")String createtime,@Param("stuno")String stuno);
}
