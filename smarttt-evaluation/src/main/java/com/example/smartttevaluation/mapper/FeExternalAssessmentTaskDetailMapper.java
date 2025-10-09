package com.example.smartttevaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.smartttevaluation.pojo.FeExternalAssessmentTaskDetail;
import org.apache.ibatis.annotations.Mapper;

/** 外部考核任务明细表 Mapper */
@Mapper
public interface FeExternalAssessmentTaskDetailMapper extends BaseMapper<FeExternalAssessmentTaskDetail> {
    // 可扩展：批量插入、按任务ID查询明细等
}
