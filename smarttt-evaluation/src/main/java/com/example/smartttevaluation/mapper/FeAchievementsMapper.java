package com.example.smartttevaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.smartttevaluation.pojo.FeAchievements;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 达成度表 Mapper 接口
 */
@Mapper
public interface FeAchievementsMapper extends BaseMapper<FeAchievements> {

}
