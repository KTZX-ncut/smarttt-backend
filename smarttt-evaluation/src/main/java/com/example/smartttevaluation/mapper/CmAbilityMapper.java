package com.example.smartttevaluation.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;
import com.example.smartttevaluation.dto.CmAbilityTree;
import com.example.smartttevaluation.pojo.CmAbility;

@Mapper
public interface CmAbilityMapper {

    /**
     * 创建一个新能力
     */
    @Insert("INSERT INTO cm_ability (id, pid, orderno, abilitydeep, name, datavalue,importantlevel, remark, levelcode) " +
            "VALUES (#{id},#{pid},#{orderno},#{abilitydeep},#{name},#{datavalue},#{importantlevel}, #{remark},#{levelcode})")
    void createOneNewAbility(CmAbility cmAbility);
    /**
     * 删除一个能力
     */
    @Delete("delete from cm_ability where id = #{id}")
    void deleteAbilityByID(String id);
    /**
     * 批量删除能力
     */
    void deleteAbilityByIDs(@Param("ids") List<String> ids, @Param("proid") List<String> proid);
    /**
     * 返回全部能力树
     */
    @Select("select * from cm_ability")
    List<CmAbilityTree> getAllCmAbilityTree();
    /**
     * 返回全部能力列表
     */
    @Select("select * from cm_ability")
    List<CmAbility> getAllCmAbilityList();
    /**
     * 通过pid返回能力列表
     */
    @Select("select orderno from cm_ability where pid = #{pid}")
    List<Long> getCmAbilityListByPid(String pid);
    /**
     * 更新兄弟节点顺序号
     */
    @Update("UPDATE sm_obs o1\n" +
            "JOIN (SELECT pid, orderno FROM sm_obs WHERE id = #{id}) o2 ON o1.pid = o2.pid\n" +
            "SET o1.orderno = o1.orderno - 1\n" +
            "WHERE o1.orderno > o2.orderno;\n")
    void updateBrotherAbilityOrderNo(String id);
    /**
     * 更新一个能力
     */
    @Update("update cm_ability set pid = (select pid from cm_ability where id =(select pid from cm_ability where id = #{id}) ),orderno = #{orderno}")
    void upgradeOneAbilityByID(@Param("id")String id,@Param("orderno")Long orderno);
    /**
     * 通过id返回pid
     */
    @Select("select pid from cm_ability where id =#{id}")
    String getPidByID(String id);
    /**
     * 通过ids返回能力列表
     */
    List<CmAbility> getCmAbilityByIDs(@Param("ids")List<String> ids);

    @Update("update cm_ability set name = #{name} where id = #{id}")
    void updateAbility(CmAbility cmAbility);
}