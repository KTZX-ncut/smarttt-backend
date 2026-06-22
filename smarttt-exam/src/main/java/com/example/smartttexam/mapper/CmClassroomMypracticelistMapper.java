package com.example.smartttexam.mapper;

import com.example.smartttexam.pojo.CmClassroomMypracticelist;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学生作答汇总记录Mapper
 */
@Mapper
public interface CmClassroomMypracticelistMapper {

    @Insert("<script>" +
            "INSERT IGNORE INTO cm_classroom_mypracticelist (id, classroomId, testId, paperId, stuId, " +
            "resultScore, myFinishStatus, catelog, beginTime, publishTime, state, correctCount, errorCount, correctPercent) VALUES " +
            "<foreach collection='list' item='item' separator=','>" +
            "(#{item.id}, #{item.classroomId}, #{item.testId}, #{item.paperId}, #{item.stuId}, " +
            "#{item.resultScore}, #{item.myFinishStatus}, #{item.catelog}, #{item.beginTime}, #{item.publishTime}, " +
            "#{item.state}, #{item.correctCount}, #{item.errorCount}, #{item.correctPercent})" +
            "</foreach>" +
            "</script>")
    int batchInsert(@Param("list") List<CmClassroomMypracticelist> list);
}
