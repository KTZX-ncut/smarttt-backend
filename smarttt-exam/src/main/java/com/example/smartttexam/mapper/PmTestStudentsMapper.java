package com.example.smartttexam.mapper;

import com.example.smartttexam.pojo.PmTestStudents;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 考试-学生关联Mapper
 */
@Mapper
public interface PmTestStudentsMapper {

    @Insert("<script>" +
            "INSERT INTO pm_test_students (id, testId, userId, proId, userName, obsId, obsName, loginname, rowNo) VALUES " +
            "<foreach collection='list' item='item' separator=','>" +
            "(#{item.id}, #{item.testId}, #{item.userId}, #{item.proId}, #{item.userName}, #{item.obsId}, #{item.obsName}, #{item.loginname}, #{item.rowNo})" +
            "</foreach>" +
            "</script>")
    int batchInsert(@Param("list") List<PmTestStudents> list);

    @Select("SELECT * FROM pm_test_students WHERE testId = #{testId}")
    List<PmTestStudents> getByTestId(@Param("testId") String testId);
}
