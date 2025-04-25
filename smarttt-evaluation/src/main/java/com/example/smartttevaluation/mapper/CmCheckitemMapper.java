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
     * 查询此考核项是否有设置分值
     */
    @Select("select count(*) from cm_course_assessment where checkitemId = #{checkitemId} and courseId = #{obsId}")
    int CheckitemHasScore(@Param("checkitemId") String checkitemId, @Param("obsId") String obsId);
    /**
     * 查询考核项信息
     */
    @Select("select * from cm_course_checkitem where id = #{id}")
    CmCheckitem getCheckitem(String id);
    /**
     *批量删除考核项
     */
    void deleteCheckitemByIDs(@Param("ids") List<String> ids);
    /**
     * 删除考核项的所有standardScore
     */
    @Delete("delete from cm_course_assessment where checkitemId = #{id}")
    void deleteCheckitemStandardScore(String id);
    /**
     * 删除与某个考核项有关的所有文件关联项
     */
    @Delete("delete from cm_course_assessment_checkitem_file where checkitemId = #{id}")
    void deleteCheckitemAssociateFileItems(String id);
    /**
     *获取考核项树
     */
    @Select("select id, pid, orderno, itemName, itemCode as levelcode, task, remark from cm_course_checkitem " +
            "where courseid = #{obsid}")
    List<CmCheckitemTree> getAllCmCheckitemTree(String obsid);
    /**
     *获取考核项列表
     */
    @Select("select * from cm_course_checkitem where courseid = #{courseid}")
    List<CmCheckitem> getAllCmCheckitemList(@RequestParam("courseid") String courseid);
    /**
     *通过pid获取考核项列表
     */
    @Select("select orderno from cm_course_checkitem where pid = #{pid}")
    List<Long> getCmCheckitemListByPid(String pid);
    /**
     *更新兄弟节点考核项顺序号
     */
    @Update("UPDATE cm_course_checkitem o1\n" +
            "JOIN (SELECT pid, orderno FROM cm_course_checkitem WHERE id = #{id}) o2 ON o1.pid = o2.pid\n" +
            "SET o1.orderno = o1.orderno - 1\n" +
            "WHERE o1.orderno > o2.orderno;\n")
    void updateBrotherCheckitemOrderNo(String id);
    /**
     *通过id更新一个考核项
     */
    @Update("UPDATE cm_course_checkitem " +
            "SET pid = (SELECT pid FROM (SELECT pid FROM cm_course_checkitem WHERE id = (SELECT pid FROM cm_course_checkitem WHERE id = #{id})) AS temp), " +
            "orderno = #{orderno} " +
            "WHERE id = #{id}")
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

    @Update("update cm_course_checkitem set task = #{status} where id = #{id}")
    void changeCheckitemTask(@Param("id")String id,@Param("status")String status);

    @Update("update cm_course_checkitem set itemName = #{itemName} where id = #{id}")
    void changeCheckitemName(@Param("id")String id,@Param("itemName")String itemName);

    @Update("update cm_course_checkitem set remark = #{remark} where id = #{id}")
    void changeCheckitemRemark(@Param("id")String id,@Param("remark")String remark);
}
