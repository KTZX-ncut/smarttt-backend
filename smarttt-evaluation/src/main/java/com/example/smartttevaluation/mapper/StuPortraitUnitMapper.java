package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.vo.KnowledgeUnitVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lunSir
 * @create 2024-12-28 20:00
 */
public interface StuPortraitUnitMapper {

    void insert(@Param("unitList") List<KnowledgeUnitVO> unitList,
                @Param("num") int num,
                @Param("stuId")String stuId,
                @Param("classroomId")String classroomId,
                @Param("courseId")String courseId);

    void delete(@Param("stuId") String stuId,
                @Param("classroomId") String classroomId,
                @Param("courseId") String courseId);

    List<KnowledgeUnitVO> select(@Param("courseId") String courseId,
                                 @Param("stuId") String stuId,
                                 @Param("classroomId") String classroomId,
                                 @Param("num") Integer num);
}
