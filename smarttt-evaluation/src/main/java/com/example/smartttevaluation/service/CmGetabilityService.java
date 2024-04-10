package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmAbility;
import com.example.smartttevaluation.pojo.CmGetability;

import java.util.List;

public interface CmGetabilityService {

    Result getGetability(String courseid);
    /*Y*/
    //Result getAbility();

    Result insertGetability(String courseid,List<String> ids);

    Result deleteGetabilityByID(String courseid,List<String> ids);

    //Result updateGetability(CmGetability cmGetability);


}
