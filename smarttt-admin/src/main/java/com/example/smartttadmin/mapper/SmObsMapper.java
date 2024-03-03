package com.example.smartttadmin.mapper;

import com.example.smartttadmin.dto.CollegeResponse;
import com.example.smartttadmin.dto.PersonnelRoster;
import com.example.smartttadmin.dto.SmObsTree;
import com.example.smartttadmin.pojo.SmObs;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SmObsMapper {

    /**
     * 查找学院列表用于教务处的展示（无负责人列表）
     * @return ...
     */
    @Select("select id,obsname,levelcode from sm_obs where obsdeep=2")
    List<CollegeResponse> getAllCollegeList();

    @Update("INSERT INTO sm_obs (id,pid,orderno,obsdeep,obsname,obspath,levelcode,createtime,remark) " +
            "VALUES (#{id},#{pid},#{orderno},#{obsdeep},#{obsname},#{obspath},#{levelcode},#{createtime},#{remark})")
    void createOneNewCollege(SmObs smObs);

    @Delete("delete from sm_obs where id = #{id}")
    void deleteCollegeByID(String id);

    @Select("select * from sm_obs")
    List<SmObsTree> getAllSmObsTree();
    @Select("select * from sm_obs")
    List<SmObs> getAllSmObsList();

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
}
