package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.vo.AbilityVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ClassroomPortraitAbilityMapper {
    void delete(@Param("classroomId") String classroomId,
                @Param("courseId") String courseId);

    void insert(@Param("abilityList") List<AbilityVO> abilityList,
                      @Param("num") int num,
                      @Param("classroomId") String classroomId,
                      @Param("courseId") String courseId);

    List<AbilityVO> select(@Param("courseId") String courseId,
                           @Param("classroomId") String classroomId,
                           @Param("num") Integer num);
}
