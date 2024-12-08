package com.example.smartttevaluation.mapper;

import com.example.smartttcourse.pojo.CmClassroom;
import com.example.smartttevaluation.pojo.CmCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AttainmentEvaluationMapper {
    @Select("select count(*) from cm_course where id = #{obsId}")
    int checkRole(String obsId);

    @Select("select * from cm_classroom where courseId = #{obsId}")
    List<CmClassroom> getClassroomByCourseId(String obsId);

    @Select("select * from cm_classroom where id = #{obsid}")
    CmClassroom getClassroomByClassroomId(String obsId);

    @Select("select termname from cm_term where id = #{termId}")
    String getTermName(String termName);

    @Select("select * from cm_course where id = #{obsId}")
    CmCourse getCourseByCourseId(String obsId);

    @Select("select * from cm_course where id = (select courseId from cm_classroom where id = #{obsId})")
    CmCourse getCourseByClassroomId(String obsId);

    @Select("select obsName from sm_obs where id = (select professionId from cm_course where id = #{obsId})")
    String getProfessionName(String obsId);
}
