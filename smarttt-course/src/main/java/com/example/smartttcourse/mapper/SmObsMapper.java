package com.example.smartttcourse.mapper;

import com.example.smartttcourse.dto.ObsRPTree;
import com.example.smartttcourse.dto.SchoolInforReq;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SmObsMapper {

    @Select("select id,obsname,remark from sm_obs where obsdeep = 1")
    SchoolInforReq getSchoolObs();
    @Select("select * from sm_obs where obsdeep<=3")
    List<ObsRPTree> getRPTree();

    @Select("select obsdeep from sm_obs where id = #{obsid}")
    long getObsdeepByObsid(String obsid);
}
