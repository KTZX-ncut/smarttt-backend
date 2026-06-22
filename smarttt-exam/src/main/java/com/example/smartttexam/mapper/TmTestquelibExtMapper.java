package com.example.smartttexam.mapper;

import com.example.smartttexam.pojo.TmTestquelib;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 题库扩展Mapper（在已有TestQueLibMapper基础上增加操作）
 */
@Mapper
public interface TmTestquelibExtMapper {

    /**
     * 批量插入题目
     */
    int batchInsert(@Param("list") List<TmTestquelib> list);

    /**
     * 根据课程ID查询所有题目
     */
    @Select("SELECT * FROM tm_testquelib WHERE courseId = #{courseId} AND status != 3 ORDER BY createTime DESC")
    List<TmTestquelib> getQuestionsByCourseId(@Param("courseId") String courseId);

    /**
     * 根据题目ID列表查询
     */
    List<TmTestquelib> getQuestionsByIds(@Param("ids") List<String> ids);

    /** 分页查询 */
    List<TmTestquelib> getQuestionsByCourseIdPaged(@Param("courseId") String courseId,
                                                    @Param("offset") int offset,
                                                    @Param("pageSize") int pageSize);
    /** 统计总数 */
    long countByCourseId(@Param("courseId") String courseId);

    /**
     * 软删除题目（状态改为3=弃用）
     */
    int softDeleteByIds(@Param("ids") List<String> ids);
}
