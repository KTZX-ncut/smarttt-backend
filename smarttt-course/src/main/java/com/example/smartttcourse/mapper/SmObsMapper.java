package com.example.smartttcourse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.smartttcourse.dto.ObsRPTree;
import com.example.smartttcourse.dto.SchoolInforReq;
import com.example.smartttcourse.pojo.SmObs;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SmObsMapper extends BaseMapper<SmObs> {

    @Select("select id,obsname,remark from sm_obs where obsdeep = 1")
    SchoolInforReq getSchoolObs();
    @Select("select * from sm_obs where obsdeep<=3")
    List<ObsRPTree> getRPTree();

    @Select("select obsdeep from sm_obs where id = #{obsid}")
    long getObsdeepByObsid(String obsid);
}
