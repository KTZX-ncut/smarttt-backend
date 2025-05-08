package com.example.smartttcourse.mapper;

import com.example.smartttcourse.pojo.CmTerm;
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
    @Select("select * from cm_term")
    List<CmTerm> getTerms();

    @Update("INSERT INTO cm_term (id,termname,begindate,enddate,remark,iscurrentterm,createtime) " +
            "VALUES (#{id},#{termname},#{begindate},#{enddate},#{remark},#{iscurrentterm},#{createtime})")
    void createTerms(CmTerm cmTerm);
    void deleteTermsByIDs(@Param("ids")List<String> ids);


    @Update("update cm_term set iscurrentterm = 1 where id = #{id}")
    void setCurrentTerms(String id);

    @Update("update cm_term set iscurrentterm = 0 where id != #{id}")
    void setOtherTerms(String id);

    void updateTermByID(CmTerm cmTerm);

    @Select("select id from cm_term where iscurrentterm = 1")
    String getCurrentTerm();

    @Select("select id,termname from cm_term where iscurrentterm = 0")
    List<CmTerm> getHistoryTerms();

    void deleteObsTermByIDs(List<String> ids);
}
