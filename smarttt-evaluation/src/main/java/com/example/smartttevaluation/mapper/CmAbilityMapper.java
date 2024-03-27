package com.example.smartttevaluation.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;
import com.example.smartttevaluation.dto.CmAbilityTree;
import com.example.smartttevaluation.pojo.CmAbility;

@Mapper
public interface CmAbilityMapper {

    /**
     * 查找能力列表
     */
    @Insert("INSERT INTO cm_ability (id, pid, orderno, abilitydeep, name, datavalue,importantlevel, remark, levelcode) " +
            "VALUES (#{id},#{pid},#{orderno},#{abilitydeep},#{name},#{databalue},#{importantlevel}, #{remark},#{levelcode})")
    void createOneNewAbility(CmAbility cmAbility);

    @Delete("delete from cm_ability where id = #{id}")
    void deleteAbilityByID(String id);

    void deleteAbilityByIDs(@Param("ids") List<String> ids);

    @Select("select * from cm_ability")
    List<CmAbilityTree> getAllCmAbilityTree();

    @Select("select * from cm_ability")
    List<CmAbility> getAllCmAbilityList();

    @Select("select orderno from cm_ability where pid = #{pid}")
    List<Long> getCmAbilityListByPid(String pid);

    @Update("UPDATE sm_obs o1\n" +
            "JOIN (SELECT pid, orderno FROM sm_obs WHERE id = #{id}) o2 ON o1.pid = o2.pid\n" +
            "SET o1.orderno = o1.orderno - 1\n" +
            "WHERE o1.orderno > o2.orderno;\n")
    void updateBrotherAbilityOrderNo(String id);

    @Update("update cm_ability set pid = (select pid from cm_ability where id =(select pid from cm_ability where id = #{id}) ),orderno = #{orderno}")
    void upgradeOneAbilityByID(@Param("id")String id,@Param("orderno")Long orderno);

    @Select("select pid from cm_ability where id =#{id}")
    String getPidByID(String id);

    List<CmAbility> getCmAbilityByIDs(@Param("ids")List<String> ids);
}