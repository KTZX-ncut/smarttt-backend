package com.example.smartttexam.mapper;

import com.example.smartttexam.pojo.PmTestpaper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 试卷Mapper
 */
@Mapper
public interface PmTestpaperMapper {

    @Insert("INSERT INTO pm_testpaper (id, name, makeModeId, makeModeName, testModeId, questionsCount, " +
            "totalScore, status, locked, courseId, classroomId, creatorId, creator, createTime, " +
            "catelog, courseName, classroomName) " +
            "VALUES (#{id}, #{name}, #{makeModeId}, #{makeModeName}, #{testModeId}, #{questionsCount}, " +
            "#{totalScore}, #{status}, #{locked}, #{courseId}, #{classroomId}, #{creatorId}, #{creator}, " +
            "#{createTime}, #{catelog}, #{courseName}, #{classroomName})")
    int insert(PmTestpaper paper);

    @Select("SELECT * FROM pm_testpaper WHERE id = #{id}")
    PmTestpaper getById(@Param("id") String id);

    @Select("SELECT * FROM pm_testpaper WHERE courseId = #{courseId} AND status != 3 ORDER BY createTime DESC")
    List<PmTestpaper> getListByCourseId(@Param("courseId") String courseId);

    @Update("UPDATE pm_testpaper SET name = #{name}, questionsCount = #{questionsCount}, " +
            "totalScore = #{totalScore} WHERE id = #{id}")
    int updateInfo(PmTestpaper paper);

    @Update("UPDATE pm_testpaper SET status = #{status} WHERE id = #{id}")
    int updateStatus(@Param("id") String id, @Param("status") long status);

    @Update("UPDATE pm_testpaper SET status = 3 WHERE id = #{id}")
    int deleteById(@Param("id") String id);
}
