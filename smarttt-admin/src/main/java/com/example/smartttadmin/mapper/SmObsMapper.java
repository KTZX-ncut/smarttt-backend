package com.example.smartttadmin.mapper;

import com.example.smartttadmin.dto.*;
import com.example.smartttadmin.pojo.CmClass;
import com.example.smartttadmin.pojo.CmProfession;
import com.example.smartttadmin.pojo.SmObs;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SmObsMapper {

    /**
     * 查找学院列表用于教务处的展示（无负责人列表）
     * @return ...
     */
    @Select("select id,obsname,levelcode,remark from sm_obs where obsdeep=2")
    List<ObsResponse> getAllCollegeList();

    @Insert("INSERT INTO sm_obs (id,pid,orderno,obsdeep,obsname,obspath,levelcode,createtime,remark) " +
            "VALUES (#{id},#{pid},#{orderno},#{obsdeep},#{obsname},#{obspath},#{levelcode},#{createtime},#{remark})")
    void createOneNewObs(SmObs smObs);

    @Delete("delete from sm_obs where id = #{id}")
    void deleteObsByID(String id);

    @Select("select * from sm_obs")
    List<SmObsTree> getAllSmObsTree();
    @Select("select * from sm_obs")
    List<SmObs> getAllSmObsList();
    @Select("select * from sm_obs where obsdeep<=3")
    List<ObsRPTree>getRPTree();

    /**
     * 这里需要修改为动态sql
     * @param id
     * @return
     */
    @Select("select * from sm_obs where id = #{id}")
    List<SmObs> getSmObsByID(String id);

    @Select("SELECT u.id AS id,u.username AS username,u.loginname AS loginname,u.phone AS phone,u.status AS status," +
            "t.obsid AS obsid,o.obsname AS obsname,u.catelog AS catelog\n" +
            "FROM st_users u\n" +
            "JOIN sm_teacher t ON u.id = t.usersid\n" +
            "JOIN sm_obs o ON t.obsid = o.id\n" +
            "WHERE u.catelog = #{catelog} AND t.obsid = #{obsid}")
    List<PersonnelRoster> getTeacherPRByObsIDAndCatelog(@Param("obsid")String obsid,@Param("catelog")String catelog);
    @Select("SELECT u.id AS id,u.username AS username,u.loginname AS loginname,u.phone AS phone,u.status AS status," +
            "t.obsid AS obsid,o.obsname AS obsname,u.catelog AS catelog\n" +
            "FROM st_users u\n" +
            "JOIN sm_student t ON u.id = t.usersid\n" +
            "JOIN sm_obs o ON t.obsid = o.id\n" +
            "WHERE u.catelog = #{catelog} AND t.obsid = #{obsid} ")
    List<PersonnelRoster> getStudentPRByObsIDAndCatelog(@Param("obsid")String obsid, @Param("catelog")String catelog);

    @Select("select id from sm_obs where obsname = #{obsname}")
    List<String> getObsIDByObsName(String obsname);

    @Select("select orderno from sm_obs where pid = #{pid}")
    List<Long> getSmObsListByPid(String pid);

    @Update("UPDATE sm_obs o1\n" +
            "JOIN (SELECT pid, orderno FROM sm_obs WHERE id = #{id}) o2 ON o1.pid = o2.pid\n" +
            "SET o1.orderno = o1.orderno - 1\n" +
            "WHERE o1.orderno > o2.orderno;\n")
    void updateBrotherObsOrderNo(String id);

    @Update("update sm_obs set pid = (select pid from sm_obs where id =(select pid from sm_obs where id = #{id}) ),orderno = #{orderno}")
    void upgradeOneObsByID(@Param("id")String id,@Param("orderno")Long orderno);

    @Select("select pid from sm_obs where id =#{id}")
    String getPidByID(String id);

    List<SmObs> getSmObsByIDs(@Param("ids")List<String> ids);
    @Select("select id,obsname,levelcode,remark from sm_obs where pid = #{pid}")
    List<ObsResponse> getSmObsByPid(@Param("pid") String pid);

    List<ProfessionResponse> getProfessionByIDs(@Param("ids") List<String> ids);

    @Insert("insert into cm_profession (id, obsid, proname, procode, reachpercent, remark, createtime) " +
            "values ( #{id}, #{obsid}, #{proname}, #{procode}, #{reachpercent}, #{remark}, #{createtime})")
    void createOneProfession(CmProfession cmProfession);

    @Select("select id,obsname from sm_obs where pid = #{pid} order by orderno")
    List<ClassResponse> getProfessionByPid(@Param("pid")String pid);

//    @Select("select obsid as id ,classname,grade from cm_class where obsid in (select id from sm_obs where pid = #{id})")
    @Select("SELECT o.id AS id, c.classname AS classname ,grade " +
            "FROM sm_obs o JOIN cm_class c ON o.id = c.obsid\n" +
            "WHERE o.pid = #{id} ORDER BY o.orderno;\n")
    List<CmClass> getClassByProfessionID(@Param("id") String id);

    @Insert("insert into cm_class (id, obsid, classname, grade,remark) values (#{id}, #{obsid}, #{classname}, #{grade},#{remark})")
    void createOneClass(CmClass cmClass);

    void updateClass(CmClass cmClass);

    void updateObs(SmObs smObs);
    @Select("select obsname from sm_obs where id = #{obsid} and obsdeep !=1 and (select isRP from st_roles where id = #{roleid}) !=0")
    String getObsName(SimpleRole simpleRole);

    @Select("select id,obsdeep from sm_obs where id in (select obsid from sm_student where usersid = #{id})")
    SmObs getObsByStuID(String id);

    @Select("select obsdeep from sm_obs where id = #{obsid}")
    String getObsdeepByObsid(String obsid);


    void updateProfession(CmProfession cmProfession);

    @Select("select id,obsname,remark from sm_obs where obsdeep = 1")
    SchoolInforReq getSchoolObs();

    @Select("select id from st_level where obsdeep = #{obsdeep}")
    long checkProfession(long obsdeep);
}
