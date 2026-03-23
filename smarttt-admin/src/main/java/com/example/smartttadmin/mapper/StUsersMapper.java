package com.example.smartttadmin.mapper;

import com.example.smartttadmin.dto.*;
import com.example.smartttadmin.pojo.StRoleUser;
import com.example.smartttadmin.pojo.StUsers;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StUsersMapper {
    /**
     * 用账号密码查找对应的用户
     *
     * @param loginReq ...
     * @return 用户列表（用于判空）
     */
    @Select("select * from st_users where loginname=#{loginname} and pwd = #{pwd} and catelog = #{catelog}")
    StUsers getStUsersByLoginNameAndPwdAndCatelog(LoginReq loginReq);

    @Select("SELECT u.id, u.username, o.obsname\n" +
            "FROM st_roleuser ru\n" +
            "JOIN st_users u ON ru.userid = u.id\n" +
            "JOIN sm_teacher t ON u.id = t.usersid\n" +
            "JOIN sm_obs o ON t.obsid = o.id\n" +
            "WHERE ru.obsid = #{obsid};\n")
    List<ResponsiblePerson> getRPByObsID(String obsid);

    @Insert("insert into st_users(id,username,loginname,pwd,phone,status,catelog,remark,createtime) values " +
            "(#{id},#{username},#{loginname},#{pwd},#{phone},#{status},#{catelog},#{remark},#{createtime})")
    void createOneStUsersByPersonnelRoster(PersonnelRoster personnelRoster);

    @Insert("insert into sm_teacher(id,obsid,usersid,createtime,jobno,historyobs) values (#{id},#{obsid},#{usersid},#{createtime},#{jobno},#{historyobs})")
    void createOneSmTeacher(@Param("id") String id, @Param("obsid") String obsid, @Param("usersid") String usersid, @Param("createtime") String createtime, @Param("jobno") String jobno,@Param("historyobs")String historyobs);

    @Insert("insert into sm_student(id,obsid,usersid,createtime,stuno,historyobs) values (#{id},#{obsid},#{usersid},#{createtime},#{stuno},#{historyobs})")
    void createOneSmStudent(@Param("id") String id, @Param("obsid") String obsid, @Param("usersid") String usersid, @Param("createtime") String createtime, @Param("stuno") String stuno,@Param("historyobs")String historyobs);

    void deleteUsersByIDs(@Param("ids") List<String> ids);

    //@Select("select cm_term.termname as currentterm , st_users.username , st_roles.rolename,st_roles.homeurl from cm_term,st_users,st_roles " +
    //        "where iscurrentterm = 1 and st_users.id = #{id} and st_roles.id = #{roleid}")
    @Select("select COALESCE(cm_term.termname, '默认学期') as currentterm , " +
            "st_users.username , " +
            "st_roles.rolename, " +
            "st_roles.homeurl " +
            "from st_users " +
            "join st_roles on st_roles.id = #{roleid} " +
            " left join cm_term on cm_term.id = #{termid} " +
            " where st_users.id = #{id}")
    UserInfor getAllUserInfor(UserInforReq userInforReq);

    @Delete("delete from st_roleuser where obsid = #{obsid} and userid = #{userid} and roleid = #{roleid}")
    void deletePRByObsIDAndUserID(StRoleUser stRoleUser);

    @Insert("insert into st_roleuser(id, userid, roleid, obsid, obsdeep, createtime,termid) values (#{id}, #{userid}, #{roleid}, #{obsid}, #{obsdeep}, #{createtime},#{termid})")
    void createOneRoleUser(StRoleUser stRoleUser);

    @Select("SELECT u.id, u.username, o.obsname, t.obsid\n" +
            "FROM sm_teacher t\n" +
            "JOIN st_users u ON t.usersid = u.id\n" +
            "JOIN sm_obs o ON t.obsid = o.id\n" +
            "WHERE t.obsid = #{obsid}\n")
    List<ResponsiblePerson> getAllTeacherByObsID(String obsid);

    void updateUserByID(PersonnelRoster personnelRoster);

    void updateStudentByID(PersonnelRoster personnelRoster);

    void updateTeacherByID(PersonnelRoster personnelRoster);

    @Select("select st_users.id,username,stuno as personnelno  from st_users,sm_student where st_users.id=sm_student.usersid  and sm_student.obsid  = #{id}")
    List<SimplePerson> getStudentByID(String id);

    @Select("select rolename,homeurl from st_roles where id = #{id}")
    UserInfor getStudentInfor(String id);

    @Select("select id from cm_term where iscurrentterm = 1")
    String getCurrentTerm();

    @Select("select id,termname from cm_term where iscurrentterm = 1")
    TermsResponse getCurrentTermName();

    @Update("update st_users set phone = 11111111 where loginname =  #{s} ")
    void testTran(String s);

    @Select("select id from st_users where loginname = #{loginname}")
    List<String> getStUsersByloginName(String loginname);

    void saveBach(@Param("personnelRosterList") List<PersonnelRoster> personnelRosterList);

    @Select("select pwd from st_users where id = #{id}")
    String getPwd(String id);

    @Insert("update st_users set pwd = #{pwd} where id = #{id}")
    void updatePwd(@Param("id") String id, @Param("pwd") String pwd);

    @Select("select usersid as id,2 as catelog from sm_teacher where obsid = #{id}")
    List<PersonnelRoster> getTeacherByObsid(String id);

    @Select("select usersid as id,1 as catelog from sm_student where obsid = #{id}")
    List<PersonnelRoster> getStudentByObsid(String id);

    @Select("SELECT usersid AS id, stuno AS personnelno, loginname, username FROM sm_student " +
            "LEFT JOIN st_users ON st_users.id = sm_student.usersid " +
            "WHERE loginname LIKE CONCAT('%', #{inform}, '%') " +
            "OR username LIKE CONCAT('%', #{inform}, '%') " +
            "OR stuno LIKE CONCAT('%', #{inform}, '%')")
    List<PersonnelRoster> getStudentByInform(String inform);

    @Select("SELECT usersid AS id, jobno AS personnelno, loginname, username FROM sm_teacher " +
            "LEFT JOIN st_users ON st_users.id = sm_teacher.usersid " +
            "WHERE loginname LIKE CONCAT('%', #{inform}, '%') " +
            "OR username LIKE CONCAT('%', #{inform}, '%') " +
            "OR jobno LIKE CONCAT('%', #{inform}, '%')")
    List<PersonnelRoster> getTeacherByInform(String inform);

    void updateTeacherByObsid(@Param("ids")List<String> ids);
    void updateStudentByObsid(@Param("ids")List<String> ids);
}
