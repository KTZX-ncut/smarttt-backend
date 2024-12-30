package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.vo.KeywordVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lunSir
 * @create 2024-12-28 20:51
 */
public interface ClassroomPortraitKeywordMapper {
    void delete(@Param("classroomId") String classroomId,
                @Param("courseId") String courseId);

    void insert(@Param("keywordList") List<KeywordVO> keywordList,
                @Param("num") int num,
                @Param("classroomId") String classroomId,
                @Param("courseId") String courseId);

    List<KeywordVO> select(@Param("courseId") String courseId,
                           @Param("classroomId") String classroomId,
                           @Param("num") Integer num);
}
