package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.pojo.CalculatePaper;
import com.example.smartttevaluation.vo.TestPaperInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PortraitConfigMapper {

    List<TestPaperInfoVO> getTestPaperInfo(@Param("classroomId") String classroomId);

    Boolean saveConfPaper(@Param("calculatePaperList") List<CalculatePaper> calculatePaperList);

    Integer getMaxRow(@Param("classroomId") String classroomId);

    Boolean removeConfPaperByIds(@Param("idList") List<Long> idList);

    List<CalculatePaper> getConfTestInfoList(@Param("classroomId")String classroomId);

    void updateConfTestInfoListRow(@Param("id") Integer id, @Param("row") Integer row);
}
