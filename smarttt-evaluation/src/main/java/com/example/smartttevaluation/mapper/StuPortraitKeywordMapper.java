package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.vo.KeywordVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lunSir
 * @create 2024-12-28 19:10
 */
public interface StuPortraitKeywordMapper {

    void insert(@Param("keywordList") List<KeywordVO> keywordList,
                @Param("num") int num,
                @Param("stuId") String stuId,
                @Param("classroomId") String classroomId,
                @Param("courseId") String courseId);

    void delete(@Param("classroomId") String classroomId,
               @Param("courseId") String courseId);

    List<KeywordVO> select(@Param("courseId") String courseId,
                           @Param("stuId") String stuId,
                           @Param("classroomId") String classroomId,
                           @Param("num") Integer num);
}
