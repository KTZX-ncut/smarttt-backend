package com.example.smartttexam.mapper;

import com.example.smartttexam.pojo.CmKwadict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * KWA字典查询Mapper
 * JOIN cm_kwadict + cm_keywords + cm_getability 获取完整的KWA上下文
 */
@Mapper
public interface CmKwadictMapper {

    /**
     * 根据课程ID查询所有KWA（含关键字名称和能力名称）
     */
    List<CmKwadict> getKwaByCourseId(@Param("courseId") String courseId);

    /**
     * 根据KWA ID列表查询KWA详情
     */
    List<CmKwadict> getKwaByIds(@Param("kwaIds") List<String> kwaIds);
}
