package com.example.smartttevaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.smartttevaluation.dto.AssessmentCategorySearchReq;
import com.example.smartttevaluation.pojo.FeAssessmentCategories;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 考核项类别表 Mapper 接口
 */
@Mapper
public interface FeAssessmentCategoriesMapper extends BaseMapper<FeAssessmentCategories> {

    /**
     * 根据课程ID查询考核项类别（分页）
     * @param courseId 课程ID
     * @return 考核项类别列表
     */
    List<FeAssessmentCategories> selectByCourseIdWithPage(@Param("courseId") String courseId);

    /**
     * 根据课程ID查询考核项类别（带分页参数）
     * @param courseId 课程ID
     * @param current 当前页码
     * @param size 每页大小
     * @param offset 偏移量
     * @return 考核项类别列表
     */
    List<FeAssessmentCategories> selectByCourseIdWithPageAndLimit(@Param("courseId") String courseId, 
                                                                 @Param("current") Long current, 
                                                                 @Param("size") Long size,
                                                                 @Param("offset") Long offset);

    /**
     * 根据课程ID统计考核项类别数量
     * @param courseId 课程ID
     * @return 数量
     */
    Long countByCourseId(@Param("courseId") String courseId);

    /**
     * 批量删除考核项类别
     * @param ids 类别ID列表
     * @return 删除数量
     */
    int batchDeleteByIds(@Param("ids") List<String> ids);

    /**
     * 根据ID更新考核项类别
     * @param assessmentCategory 考核项类别对象
     * @return 更新数量
     */
    int updateById(FeAssessmentCategories assessmentCategory);

    /**
     * 搜索考核项类别
     * @param searchReq 搜索条件
     * @param offset 偏移量（可选）
     * @return 考核项类别列表
     */
    List<FeAssessmentCategories> searchAssessmentCategories(@Param("searchReq") AssessmentCategorySearchReq searchReq, 
                                                          @Param("offset") Long offset);

    /**
     * 搜索考核项类别总数
     * @param searchReq 搜索条件
     * @return 总数
     */
    Long countSearchAssessmentCategories(@Param("searchReq") AssessmentCategorySearchReq searchReq);
}
