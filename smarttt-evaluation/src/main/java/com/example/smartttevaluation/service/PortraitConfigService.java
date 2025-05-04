package com.example.smartttevaluation.service;

import com.example.smartttevaluation.pojo.CalculatePaper;
import com.example.smartttevaluation.vo.TestPaperInfoVO;

import java.util.List;

public interface PortraitConfigService {
    List<TestPaperInfoVO> getTestPaperInfo(String classroomId);

    Boolean saveConfPaper(List<CalculatePaper> calculatePaperList);

    Boolean removeConfPaperByIds(List<Long> idList);

    List<CalculatePaper> getConfTestInfoList(String classroomId);

    Boolean updateConfTestInfoListRow(List<CalculatePaper> calculatePaperList);

    List<TestPaperInfoVO> getTestExperimentPaperInfo(String classroomId);
}
