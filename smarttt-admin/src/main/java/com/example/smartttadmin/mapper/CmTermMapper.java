package com.example.smartttadmin.mapper;

import com.example.smartttadmin.pojo.CmTerm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CmTermMapper {
    /**
     * 获取学期信息
     */
    @Select("select termname, begindate, enddate, remark, iscurrentterm from cm_term")
    List<CmTerm> getTerms ();

    @Update("INSERT INTO cm_term (id,termname,begindate,endDate,remark,iscurrentterm) " +
            "VALUES (#{id},#{termName},#{startData},#{endData},#{remark},#{isActive})")
    void createTerms(CmTerm smterms);
    void deleteTermsByIDs(@Param("ids")List<String> ids);


    @Select("select * from cm_term where iscurrentterm = #{isActive}")
    void getCurrentTerms(@Param("isActive") boolean isActive);

}
