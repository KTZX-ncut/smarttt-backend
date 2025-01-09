package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.pojo.CmClassroom;
import com.example.smartttevaluation.dto.ClassroomTargetAchievement;
import com.example.smartttevaluation.dto.ClassroomTotalScore;
import com.example.smartttevaluation.dto.CmAssessmentStudent;
import com.example.smartttevaluation.pojo.CmCourse;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttainmentEvaluationMapper {
    @Select("select count(*) from cm_course where id = #{obsId}")
    int checkRole(String obsId);

    /**
     * 获取课堂中学生的信息
     */
    @Select("select su.id, ccs.rowNo, st.stuno, su.username, su.loginname, so.obsname as className, cp.proname from cm_classroom_student ccs " +
            "inner join sm_student st on st.usersid = ccs.userid " +
            "inner join st_users su on su.id = ccs.userId " +
            "inner join sm_obs so on ccs.obsId = so.id " +
            "inner join cm_classroom ccla on ccs.classroomId = ccla.id " +
            "inner join cm_course ccou on ccla.courseId = ccou.id " +
            "inner join cm_profession cp on ccou.professionId = cp.obsid " +
            "where ccs.classroomId = #{classroomId} order by ccs.rowNo")
    List<CmAssessmentStudent> getClassroomStuList(String classroomId);

    /**
     * 获取课程下的全部课堂
     */
    @Select("select * from cm_classroom where courseId = #{obsId}")
    List<CmClassroom> getClassroomByCourseId(String obsId);

    /**
     * 根据课堂id获取课堂的信息
     */
    @Select("select * from cm_classroom where id = #{obsid}")
    CmClassroom getClassroomByClassroomId(String obsId);

    /**
     * 获取学期名称
     */
    @Select("select termname from cm_term where id = #{termId}")
    String getTermName(String termName);

    /**
     * 根据课程id获取课程信息
     */
    @Select("select * from cm_course where id = #{obsId}")
    CmCourse getCourseByCourseId(String obsId);

    /**
     * 根据课堂id获取课程的信息
     */
    @Select("select * from cm_course where id = (select courseId from cm_classroom where id = #{obsId})")
    CmCourse getCourseByClassroomId(String obsId);

    /**
     * 根据课程id获取专业名称
     */
    @Select("select obsName from sm_obs where id = (select professionId from cm_course where id = #{obsId})")
    String getProfessionName(String obsId);

    /**
     * 获取学生某个试卷的分数
     */
    @Select("select SUM(libStuScore) from ai_in_stu_ans_info where stuId = #{stuId} and paperId = #{paperId}")
    Integer calcStuTestpaperScore(@Param("stuId") String stuId, @Param("paperId") String paperId);

    /**
     * 获取学生某个试卷内特定kwa的题目的总分
     */
    @Select("select SUM(libStuScore) from ai_in_stu_ans_info where stuId = #{stuId} and paperId = #{paperId} and kwaId = #{kwaId}")
    Integer calcStuTestpaperScoreByKwa(@Param("stuId") String stuId, @Param("paperId") String paperId, @Param("kwaId") String kwaId);

    /**
     * 获取试卷内特定kwa的题目的满分
     */
    @Select("select SUM(libScore) from ai_in_stu_ans_info where stuId = #{stuId} and paperId = #{paperId} and kwaId = #{kwaId}")
    Integer calcTestPaperScoreByKwa(@Param("stuId") String stuId, @Param("paperId") String paperId, @Param("kwaId") String kwaId);

    /**
     * 存储学生总评成绩
     */
    @Insert("insert into cm_classroom_total_score (id, stuId, classroomId, checkitemId, ratio, checkItemScore) values " +
            "(#{id}, #{stuId}, #{classroomId}, #{checkitemId}, #{ratio}, #{checkitemScore})")
    void setTotalScoreData(ClassroomTotalScore classroomTotalScore);

    /**
     * 清除学生总评成绩
     */
    @Delete("delete from cm_classroom_total_score where classroomId = #{classroomId}")
    void cleanTotalScore(String classroomId);

    /**
     * 获取学生总评成绩
     */
    @Select("select * from cm_classroom_total_score where classroomId = #{classroomId} order by stuId")
    List<ClassroomTotalScore> getTotalScore(String classroomId);

    /**
     * 存储学生课程目标的达成度
     */
    @Insert("insert into cm_classroom_target_achievement (id, stuId, classroomId, targetId, degree) values " +
            "(#{id}, #{stuId}, #{classroomId}, #{targetId}, #{degree})")
    void setTargetAchievement(ClassroomTargetAchievement classroomTargetAchievement);

    /**
     * 清除学生课程目标达成度
     */
    @Delete("delete from cm_classroom_target_achievement where classroomId = #{classroomId}")
    void cleanTargetAchievement(String classroomId);

    /**
     * 获取某一作业中与某个kwa关联的题目数量
     */
    @Select("select count(*) from ai_in_stu_ans_info where paperId = #{paperId} and kwaId = #{kwaId}")
    int getLibCountByKwaId(@Param("paperId") String paperId, @Param("kwaId") String kwaId);

    /**
     * 获取学生课程目标达成度
     */
    @Select("select * from cm_classroom_target_achievement where classroomId = #{classroomId} order by stuId")
    List<ClassroomTargetAchievement> getTargetAchievement(String classroomId);
}
