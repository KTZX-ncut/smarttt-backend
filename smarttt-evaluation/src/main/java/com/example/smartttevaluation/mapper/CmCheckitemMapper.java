package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.dto.CmCheckitemTree;
import com.example.smartttevaluation.pojo.CmCheckitem;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Mapper
public interface CmCheckitemMapper {
    /**
     *创建一个考核项
     */
    @Insert("INSERT INTO cm_course_checkitem (id, pid, orderno, itemName, itemCode, remark, courseid, percent) " +
            "VALUES (#{id},#{pid},#{orderno},#{itemName},#{itemCode},#{remark},#{courseid},#{percent})")
    void createOneNewCheckitem(CmCheckitem cmCheckitem);
    /**
     *通过id删除考核项
     */
    @Delete("delete from cm_course_checkitem where id = #{id}")
    void deleteCheckitemByID(String id);
    /**
     *批量删除考核项
     */
    void deleteCheckitemByIDs(@Param("ids") List<String> ids);
    /**
     *获取考核项树
     */
    @Select("select * from cm_course_checkitem")
    List<CmCheckitemTree> getAllCmCheckitemTree();
    /**
     *获取考核项列表
     */
    @Select("select * from cm_course_checkitem")
    List<CmCheckitem> getAllCmCheckitemList(@RequestParam("courseid") String courseid);
    /**
     *通过pid获取考核项列表
     */
    @Select("select orderno from cm_course_checkitem where pid = #{pid}")
    List<Long> getCmCheckitemListByPid(String pid);
    /**
     *更新兄弟节点考核项顺序号
     */
    @Update("UPDATE sm_obs o1\n" +
            "JOIN (SELECT pid, orderno FROM sm_obs WHERE id = #{id}) o2 ON o1.pid = o2.pid\n" +
            "SET o1.orderno = o1.orderno - 1\n" +
            "WHERE o1.orderno > o2.orderno;\n")
    void updateBrotherCheckitemOrderNo(String id);
    /**
     *通过id更新一个考核项
     */
    @Update("update cm_course_checkitem set pid = (select pid from cm_ability where id =(select pid from cm_ability where id = #{id}) ),orderno = #{orderno}")
    void upgradeOneCheckitemByID(@Param("id")String id,@Param("orderno")Long orderno);
    /**
     *通过id获取pid
     */
    @Select("select pid from cm_course_checkitem where id =#{id}")
    String getPidByID(String id);
    /**
     *通过ids获取考核项
     */
    List<CmCheckitem> getCmCheckitemByIDs(@Param("ids")List<String> ids);
}
