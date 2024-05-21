package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmCoursetarget;

import java.util.List;

public interface CmCoursetargetService {
    Result getCoursetarget(String courseid);

    Result createCoursetarget(CmCoursetarget cmCoursetarget);

    Result deleteCoursetargetByID(List<String> ids);

    Result updateCoursetarget(CmCoursetarget cmCoursetarget);

    Result insertunit(String courseid, List<String> ids);
}
