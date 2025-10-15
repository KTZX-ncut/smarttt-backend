package com.example.smartttevaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.smartttevaluation.pojo.FeObjectiveAssessmentCategory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeObjectiveAssessmentCategoryMapper extends BaseMapper<FeObjectiveAssessmentCategory> {

    @Insert("INSERT INTO fe_objective_assessment_category (category_id, objective_id, score) " +
            "VALUES (#{categoryId}, #{objectiveId}, #{score}) " +
            "ON DUPLICATE KEY UPDATE " +
            "score = VALUES(score), " +
            "updated_at = NOW()")
    int upsert(FeObjectiveAssessmentCategory entity);
}
