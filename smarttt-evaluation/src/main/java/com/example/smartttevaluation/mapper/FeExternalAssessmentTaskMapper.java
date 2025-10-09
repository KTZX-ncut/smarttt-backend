package com.example.smartttevaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.smartttevaluation.pojo.FeExternalAssessmentTask;
import org.apache.ibatis.annotations.Mapper;

/** 外部考核任务表 Mapper */
@Mapper
public interface FeExternalAssessmentTaskMapper extends BaseMapper<FeExternalAssessmentTask> {
    // 可以扩展自定义查询，比如根据 labelId 查任务
}
