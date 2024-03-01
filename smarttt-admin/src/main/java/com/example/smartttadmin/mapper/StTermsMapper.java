package com.example.smartttadmin.mapper;

import com.example.smartttadmin.dto.Termslist;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StTermsMapper {
    /**
     * 获取学期信息
     */
    @Select("select termName, startData, endData, remark, isActive from st_term")
    List<Termslist> getTerms ();

    @Select("select ")
    List<Termslist> getTermsByTermsID(String roleid);


    Termslist getCurrentTerms();

    boolean updateTerms(Termslist updateTermsReq);

    boolean deleteTerms();

    boolean insertTerms();
}
