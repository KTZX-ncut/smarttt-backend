package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.schedule.entity.EduMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GlobalMessageMapper {

    List<EduMessage> pullMessage(@Param("typeList") List<String> typeList);

    void updateCalPaperName(@Param("testId") String testId,
                            @Param("paperName") String paperName,
                            @Param("testName") String testName);

    void updateMessageState(@Param("messageIdList") List<Long> messageIdList,
                            @Param("complete") String complete);

    void deleteCalPaper(@Param("testId") String testId);

    void deleteIdeologyPaper(@Param("testId") String testId);

    void updateIdeologyPaperName(@Param("testId") String testId,
                                 @Param("paperName") String paperName,
                                 @Param("testName") String testName);
}
