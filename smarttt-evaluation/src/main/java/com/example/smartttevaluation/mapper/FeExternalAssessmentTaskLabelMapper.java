package com.example.smartttevaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.smartttevaluation.pojo.FeExternalAssessmentTaskLabel;
import org.apache.ibatis.annotations.Mapper;

/** 外部考核任务标签表 Mapper */
@Mapper
public interface FeExternalAssessmentTaskLabelMapper extends BaseMapper<FeExternalAssessmentTaskLabel> {
    // 如果将来有自定义SQL，可以在这里加方法
}
