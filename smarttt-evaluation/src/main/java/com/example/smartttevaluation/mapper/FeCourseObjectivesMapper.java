package com.example.smartttevaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.smartttevaluation.dto.CourseObjectiveSearchReq;
import com.example.smartttevaluation.pojo.FeCourseObjectives;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程目标表 Mapper 接口
 */
@Mapper
public interface FeCourseObjectivesMapper extends BaseMapper<FeCourseObjectives> {

    /**
     * 根据课程ID查询课程目标列表（支持分页）
     * @param courseId 课程ID
     * @return 课程目标列表
     */
    List<FeCourseObjectives> selectByCourseIdWithPage(@Param("courseId") String courseId);

    /**
     * 根据课程ID查询课程目标列表（带分页参数）
     * @param courseId 课程ID
     * @param current 当前页码
     * @param size 每页大小
     * @param offset 偏移量
     * @return 课程目标列表
     */
    List<FeCourseObjectives> selectByCourseIdWithPageAndLimit(@Param("courseId") String courseId, 
                                                              @Param("current") Long current, 
                                                              @Param("size") Long size,
                                                              @Param("offset") Long offset);

    /**
     * 根据课程ID查询课程目标总数
     * @param courseId 课程ID
     * @return 总数
     */
    Long countByCourseId(@Param("courseId") String courseId);

    /**
     * 批量删除课程目标
     * @param ids 目标ID列表
     * @return 删除数量
     */
    int batchDeleteByIds(@Param("ids") List<String> ids);

    /**
     * 根据ID更新课程目标
     * @param courseObjective 课程目标对象
     * @return 更新数量
     */
    int updateById(FeCourseObjectives courseObjective);

    /**
     * 搜索课程目标
     * @param searchReq 搜索条件
     * @param offset 偏移量（可选）
     * @return 课程目标列表
     */
    List<FeCourseObjectives> searchCourseObjectives(@Param("searchReq") CourseObjectiveSearchReq searchReq, 
                                                   @Param("offset") Long offset);

    /**
     * 搜索课程目标总数
     * @param searchReq 搜索条件
     * @return 总数
     */
    Long countSearchCourseObjectives(@Param("searchReq") CourseObjectiveSearchReq searchReq);
}
