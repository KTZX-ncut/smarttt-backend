package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.vo.AbilityVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lunSir
 * @create 2024-12-28 17:20
 */
public interface StuPortraitAbilityMapper {

    void insert(@Param("abilityList") List<AbilityVO> abilityList,
                @Param("num") int num,
                @Param("stuId") String stuId,
                @Param("classroomId") String classroomId,
                @Param("courseId") String courseId);

    void delete(@Param("classroomId") String classroomId,
                @Param("courseId") String courseId);

    List<AbilityVO> select(@Param("courseId") String courseId,
                           @Param("stuId") String stuId,
                           @Param("classroomId") String classroomId,
                           @Param("num") Integer num);
}
