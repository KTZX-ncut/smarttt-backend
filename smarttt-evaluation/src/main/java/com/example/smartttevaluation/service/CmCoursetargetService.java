package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmCoursetarget;
import com.example.smartttevaluation.pojo.CmCoursetargetUnit;

import java.util.List;

public interface CmCoursetargetService {
    Result getCoursetarget(String ObsID);

    Result createCoursetarget(CmCoursetarget cmCoursetarget);

    Result deleteCoursetargetByIDs(List<String> ids, String courseid, String unitid);

    Result updateCoursetarget(CmCoursetarget cmCoursetarget);

    Result insertunit(String courseid, List<String> ids);

    Result insertCoursetargetUnit(String courseid, CmCoursetargetUnit cmCoursetargetUnit);

    Result deleteCoursetargetUnit(String unitid, String targetid);

    Result updateCoursetargetUnit(CmCoursetargetUnit cmCoursetargetUnit);

    Result deleteCoursetargetByID(List<String> ids);
}
