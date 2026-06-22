package com.example.smartttexam.mapper;

import com.example.smartttexam.pojo.PmTestpaperQuestions;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 试卷-题目关联Mapper
 */
@Mapper
public interface PmTestpaperQuestionsMapper {

    /**
     * 批量插入试卷-题目关联
     */
    int batchInsert(@Param("list") List<PmTestpaperQuestions> list);

    /**
     * 根据试卷ID查询所有题目关联
     */
    @Select("SELECT * FROM pm_testpaper_questions WHERE paperId = #{paperId} ORDER BY sort")
    List<PmTestpaperQuestions> getByPaperId(@Param("paperId") String paperId);

    /**
     * 根据试卷ID删除所有题目关联
     */
    @org.apache.ibatis.annotations.Delete("DELETE FROM pm_testpaper_questions WHERE paperId = #{paperId}")
    int deleteByPaperId(@Param("paperId") String paperId);
}
