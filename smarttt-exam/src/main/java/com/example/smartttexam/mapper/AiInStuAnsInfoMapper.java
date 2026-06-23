package com.example.smartttexam.mapper;

import com.example.smartttexam.pojo.AiInStuAnsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 学生作答详情日志Mapper
 */
@Mapper
public interface AiInStuAnsInfoMapper {

    /**
     * 批量插入作答详情（INSERT IGNORE 防止重复）
     */
    int batchInsert(@Param("list") List<AiInStuAnsInfo> list);

    /**
     * 根据考试ID查询所有作答详情
     */
    @Select("SELECT * FROM ai_in_stu_ans_info WHERE testId = #{testId}")
    List<AiInStuAnsInfo> getByTestId(@Param("testId") String testId);

    /**
     * 根据考试ID和学生ID查询作答详情
     */
    @Select("SELECT * FROM ai_in_stu_ans_info WHERE testId = #{testId} AND stuId = #{stuId}")
    List<AiInStuAnsInfo> getByTestIdAndStuId(@Param("testId") String testId, @Param("stuId") String stuId);
}
