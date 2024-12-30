package com.example.smartttevaluation.mapper;


import com.example.smartttevaluation.vo.KnowledgeUnitVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassroomPortraitUnitMapper {
    void insert(@Param("unitList") List<KnowledgeUnitVO> unitList,
                @Param("num") int num,
                @Param("classroomId")String classroomId,
                @Param("courseId")String courseId);

    void delete(@Param("classroomId")String classroomId,
                @Param("courseId")String courseId);

    List<KnowledgeUnitVO> select(@Param("courseId") String courseId,
                                 @Param("classroomId") String classroomId,
                                 @Param("num") Integer num);
}
