package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.pojo.CmAssessmentPlanItem;
import com.example.smartttevaluation.dto.CmAssessmentPlanTable;
import com.example.smartttevaluation.pojo.CmAssessmentPlanProportion;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CmAssessmentPlanMapper {

    /**
     * 获取课程目标数量
     */
    @Select("select count(*) from cm_course_target where courseid=#{courseid}")
    int getCourseTargetNum (@Param("courseid") String courseid);

    @Select("select count(*) from cm_course_assessment where courseid=#{courseid}")
    int getProportiontNum (@Param("courseid") String courseid);

    @Select("select nProportion,eProportion,courseid from cm_course_assessment where courseid=#{courseid}")
    CmAssessmentPlanProportion selectAssessmentPlanProportion (@Param("courseid") String courseid);

    List<CmAssessmentPlanItem> selectAssessmentPlanItems(@Param("courseid") String courseid);


    @Update("update cm_course_target set s1=#{s1},s2=#{s2},s3=#{s3},s4=#{s4},s5=#{s5} where id=#{id}")
    void updateItem(CmAssessmentPlanItem cmAssessmentPlanItem);

    @Update("update cm_course_assessment set nProportion=#{nProportion},eProportion=#{eProportion} where courseid=#{courseid}")
    void updateProportion(CmAssessmentPlanProportion cmAssessmentPlanProportion);

    @Delete("delete from cm_course_target where id=#{id} and courseid=#{courseid}")
    void deleteItem(CmAssessmentPlanItem cmAssessmentPlanItem);

    @Insert("insert into cm_course_assessment(nProportion,eProportion,courseid) value(0,0,#{courseid})")
    void insertVoidProportion(@Param("courseid") String courseid);
}
