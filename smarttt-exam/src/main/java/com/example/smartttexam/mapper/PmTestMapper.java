package com.example.smartttexam.mapper;

import com.example.smartttexam.pojo.PmTest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 发布考试Mapper
 */
@Mapper
public interface PmTestMapper {

    @Insert("INSERT INTO pm_test (id, paperId, paperName, name, totalScore, status, " +
            "courseId, classroomId, classroomName, catelog, creatorId, creator, creatTime, " +
            "publisherId, publisher, publishTime) " +
            "VALUES (#{id}, #{paperId}, #{paperName}, #{name}, #{totalScore}, #{status}, " +
            "#{courseId}, #{classroomId}, #{classroomName}, #{catelog}, #{creatorId}, #{creator}, #{creatTime}, " +
            "#{publisherId}, #{publisher}, #{publishTime})")
    int insert(PmTest test);

    @Select("SELECT * FROM pm_test WHERE id = #{id}")
    PmTest getById(@Param("id") String id);

    @Select("SELECT * FROM pm_test WHERE paperId = #{paperId}")
    PmTest getByPaperId(@Param("paperId") String paperId);

    @Select("SELECT * FROM pm_test WHERE courseId = #{courseId} AND status = 1 ORDER BY creatTime DESC")
    List<PmTest> getListByCourseId(@Param("courseId") String courseId);
}
