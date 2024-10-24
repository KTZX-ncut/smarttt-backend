package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmAbility;
import com.example.smartttevaluation.pojo.CmGetability;

import java.util.List;

public interface CmGetabilityService {
    /**
     *获取能力
     */
    Result getGetability(String courseid);
    /*Y*/
    //Result getAbility();
    /**
     *插入能力
     */
    Result insertGetability(String courseid,List<String> ids);
    /**
     *删除能力
     */
    Result deleteGetabilityByID(String courseid,List<String> ids);
    //Result updateGetability(CmGetability cmGetability);
    /**
     *通过能力获取kwa
     */
    Result getKwaByGetabilityId(String obsId, List<String> ids);

}
