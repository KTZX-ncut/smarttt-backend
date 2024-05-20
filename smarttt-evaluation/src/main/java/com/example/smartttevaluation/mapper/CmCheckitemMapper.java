package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.dto.CmCheckitemTree;
import com.example.smartttevaluation.pojo.CmCheckitem;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Mapper
public interface CmCheckitemMapper {
    @Insert("INSERT INTO cm_checkitem (id, pid, orderno, levelcode, name, task, remark, courseid, checkitemdeep) " +
            "VALUES (#{id},#{pid},#{orderno},#{levelcode},#{name},#{task}, #{remark},#{courseid},#{checkitemdeep})")
    void createOneNewCheckitem(CmCheckitem cmCheckitem);

    @Delete("delete from cm_checkitem where id = #{id}")
    void deleteCheckitemByID(String id);

    void deleteCheckitemByIDs(@Param("ids") List<String> ids);

    @Select("select * from cm_checkitem")
    List<CmCheckitemTree> getAllCmCheckitemTree();

    @Select("select * from cm_checkitem")
    List<CmCheckitem> getAllCmCheckitemList(@RequestParam("courseid") String courseid);

    @Select("select orderno from cm_checkitem where pid = #{pid}")
    List<Long> getCmCheckitemListByPid(String pid);

    @Update("UPDATE sm_obs o1\n" +
            "JOIN (SELECT pid, orderno FROM sm_obs WHERE id = #{id}) o2 ON o1.pid = o2.pid\n" +
            "SET o1.orderno = o1.orderno - 1\n" +
            "WHERE o1.orderno > o2.orderno;\n")
    void updateBrotherCheckitemOrderNo(String id);

    @Update("update cm_checkitem set pid = (select pid from cm_ability where id =(select pid from cm_ability where id = #{id}) ),orderno = #{orderno}")
    void upgradeOneCheckitemByID(@Param("id")String id,@Param("orderno")Long orderno);

    @Select("select pid from cm_checkitem where id =#{id}")
    String getPidByID(String id);

    List<CmCheckitem> getCmCheckitemByIDs(@Param("ids")List<String> ids);
}
