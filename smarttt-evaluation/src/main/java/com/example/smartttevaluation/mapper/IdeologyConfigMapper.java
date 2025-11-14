package com.example.smartttevaluation.mapper;


import com.example.smartttevaluation.pojo.IdeologyCalculatePaper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IdeologyConfigMapper {
    List<IdeologyCalculatePaper> getConfTestInfoList(@Param("classroomId") String classroomId);

    Integer getMaxRow(@Param("classroomId") String classroomId);

    Boolean saveConfPaper(@Param("calculatePaperList") List<IdeologyCalculatePaper> calculatePaperList);

    Boolean removeConfPaperByIds(@Param("idList") List<Long> idList);

    void updateConfTestInfoListRow(@Param("id") Integer id, @Param("row") Integer row);
}
