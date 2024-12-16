package com.example.smartttevaluation.mapper;

import com.example.smartttcourse.pojo.CmClassroom;
import com.example.smartttevaluation.dto.ClassroomTargetAchievement;
import com.example.smartttevaluation.dto.ClassroomTotalScore;
import com.example.smartttevaluation.dto.CmAssessmentStudent;
import com.example.smartttevaluation.pojo.CmCourse;
import com.example.smartttevaluation.pojo.CmKwadict;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttainmentEvaluationMapper {
    @Select("select count(*) from cm_course where id = #{obsId}")
    int checkRole(String obsId);

    @Select("select su.id, ccs.rowNo, st.stuno, su.username, su.loginname, so.obsname as className, cp.proname from cm_classroom_student ccs " +
            "inner join sm_student st on st.usersid = ccs.userid " +
            "inner join st_users su on su.id = ccs.userId " +
            "inner join sm_obs so on ccs.obsId = so.id " +
            "inner join cm_classroom ccla on ccs.classroomId = ccla.id " +
            "inner join cm_course ccou on ccla.courseId = ccou.id " +
            "inner join cm_profession cp on ccou.professionId = cp.obsid " +
            "where ccs.classroomId = #{classroomId} order by ccs.rowNo")
    List<CmAssessmentStudent> getClassroomStuList(String classroomId);

    @Select("select * from cm_classroom where courseId = #{obsId}")
    List<CmClassroom> getClassroomByCourseId(String obsId);

    @Select("select * from cm_classroom where id = #{obsid}")
    CmClassroom getClassroomByClassroomId(String obsId);

    @Select("select termname from cm_term where id = #{termId}")
    String getTermName(String termName);

    @Select("select * from cm_course where id = #{obsId}")
    CmCourse getCourseByCourseId(String obsId);

    @Select("select * from cm_course where id = (select courseId from cm_classroom where id = #{obsId})")
    CmCourse getCourseByClassroomId(String obsId);

    @Select("select obsName from sm_obs where id = (select professionId from cm_course where id = #{obsId})")
    String getProfessionName(String obsId);

    @Select("select SUM(libStuScore) from ai_in_stu_ans_info where stuId = #{stuId} and paperId = #{paperId}")
    Integer calcStuTestpaperScore(@Param("stuId") String stuId, @Param("paperId") String paperId);

    @Select("select SUM(libStuScore) from ai_in_stu_ans_info where stuId = #{stuId} and paperId = #{paperId} and kwaId = #{kwaId}")
    Integer calcStuTestpaperScoreByKwa(@Param("stuId") String stuId, @Param("paperId") String paperId, @Param("kwaId") String kwaId);

    @Select("select SUM(libScore) from ai_in_stu_ans_info where stuId = #{stuId} and paperId = #{paperId} and kwaId = #{kwaId}")
    Integer calcTestPaperScoreByKwa(@Param("stuId") String stuId, @Param("paperId") String paperId, @Param("kwaId") String kwaId);

    @Insert("insert into cm_classroom_total_score (id, stuId, classroomId, checkitemId, ratio, checkItemScore) values " +
            "(#{id}, #{stuId}, #{classroomId}, #{checkitemId}, #{ratio}, #{checkitemScore})")
    void setTotalScoreData(ClassroomTotalScore classroomTotalScore);

    @Delete("delete from cm_classroom_total_score where classroomId = #{classroomId}")
    void cleanTotalScore(String classroomId);

    @Select("select * from cm_classroom_total_score where classroomId = #{classroomId} order by stuId")
    List<ClassroomTotalScore> getTotalScore(String classroomId);

    @Insert("insert into cm_classroom_target_achievement (id, stuId, classroomId, targetId, degree) values " +
            "(#{id}, #{stuId}, #{classroomId}, #{targetId}, #{degree})")
    void setTargetAchievement(ClassroomTargetAchievement classroomTargetAchievement);

    @Delete("delete from cm_classroom_target_achievement where classroomId = #{classroomId}")
    void cleanTargetAchievement(String classroomId);

    /**
     * 获取某一作业中与某个kwa关联的题目数量
     */
    @Select("select count(*) from ai_in_stu_ans_info where paperId = #{paperId} and kwaId = #{kwaId}")
    int getLibCountByKwaId(@Param("paperId") String paperId, @Param("kwaId") String kwaId);

    @Select("select * from cm_classroom_target_achievement where classroomId = #{classroomId} order by stuId")
    List<ClassroomTargetAchievement> getTargetAchievement(String classroomId);
}
