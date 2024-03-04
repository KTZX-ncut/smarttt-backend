package com.example.smartttadmin.mapper;

import com.example.smartttadmin.dto.TermsResponse;
import com.example.smartttadmin.pojo.SmTerms;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface SmTermsMapper {
    /**
     * 获取学期信息
     */
    @Select("select termName, startData, endData, remark, isActive from st_term")
    List<TermsResponse> getTerms ();

    @Update("INSERT INTO sm_terms (id,termName,startDate,endDate,remark,isActive) " +
            "VALUES (#{id},#{termName},#{startData},#{endData},#{remark},#{isActive})")
    void createTerms(SmTerms smterms);

    @Delete("delete from sm_terms where id = #{id}")
    void deleteTermsByID(String id);


    @Select("select * from sm_terms where isActive = #{isActive}")
    void getCurrentTerms(@Param("isActive") boolean isActive);

}
