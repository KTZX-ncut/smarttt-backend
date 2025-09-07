package com.example.smartttevaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.smartttevaluation.dto.AssessmentItemDto;
import com.example.smartttevaluation.pojo.FeAssessmentItems;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 考核项表 Mapper 接口
 */
@Mapper
public interface FeAssessmentItemsMapper extends BaseMapper<FeAssessmentItems> {

    /**
     * 查询试卷信息（作业和考试）
     * @param classroomId 课堂ID
     * @return 试卷信息列表
     */
    List<AssessmentItemDto> selectTestPapersByClassroomId(@Param("classroomId") String classroomId);

    /**
     * 查询实验信息
     * @param classroomId 课堂ID
     * @return 实验信息列表
     */
    List<AssessmentItemDto> selectPracticesByClassroomId(@Param("classroomId") String classroomId);

    /**
     * 批量删除考核项
     * @param ids 考核项ID列表
     * @return 删除的记录数
     */
    int batchDeleteByIds(@Param("ids") List<String> ids);

    /**
     * 分页查询考核项
     * @param current 当前页码
     * @param size 每页大小
     * @param offset 偏移量
     * @param categoryId 类别ID
     * @param courseId 课程ID
     * @param classroomId 课堂ID
     * @param objectiveId 目标ID
     * @return 考核项列表
     */
    List<FeAssessmentItems> selectWithPage(@Param("current") Long current, 
                                          @Param("size") Long size,
                                          @Param("offset") Long offset,
                                          @Param("categoryId") String categoryId,
                                          @Param("courseId") String courseId,
                                          @Param("classroomId") String classroomId,
                                          @Param("objectiveId") String objectiveId);

    /**
     * 查询考核项总数
     * @param categoryId 类别ID
     * @param courseId 课程ID
     * @param classroomId 课堂ID
     * @param objectiveId 目标ID
     * @return 总数
     */
    Long countAll(@Param("categoryId") String categoryId,
                  @Param("courseId") String courseId,
                  @Param("classroomId") String classroomId,
                  @Param("objectiveId") String objectiveId);
}
