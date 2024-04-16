package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.CmClassroomUnitTree;
import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmClassroomUnit;
import com.example.smartttevaluation.pojo.CmClassroomUnitKwa;

import java.util.List;

public interface CmClassroomUnitService {

    Result getClassroomUnitList(String courseid);
    Result insertChapter(CmClassroomUnit cmClassroomUnit);
    Result insertSection(CmClassroomUnit cmClassroomUnit);
    Result insertClassroomUnitKwa(CmClassroomUnitKwa cmClassroomUnitKwa);
    Result deleteClassroomUnitKwa(String unitid,List<String> kwaids);
    Result deleteSection(List<String> ids);
    Result deleteChapter(String chapterUnitid);
}
