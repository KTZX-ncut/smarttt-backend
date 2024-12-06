package com.example.smartttevaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.smartttevaluation.dto.*;
import com.example.smartttevaluation.pojo.AiInStuAnsInfo;
import com.example.smartttevaluation.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AiInStuAnsInfoMapper extends BaseMapper<AiInStuAnsInfo> {

    List<PaperInfoDto> getPaperInfoListByIds(@Param("paperIdList") List<String> paperIdList);

    List<KeywordVO> getKeyWordIdByCourseId(@Param("courseId") String courseId);

    List<KeywordEvalDto> getEvalKeywordScore(@Param("whitePaperIdList") List<PaperInfoDto> whitePaperIdList,
                                             @Param("courseId") String courseId,
                                             @Param("stuId") String stuId,
                                             @Param("classroomId") String classroomId);

    List<AbilityVO> getAbilityIdByCourseId(@Param("courseId") String courseId);

    List<AbilityEvalDto> getEvalAbilityScore(@Param("whitePaperIdList") List<PaperInfoDto> whitePaperIdList,
                                             @Param("courseId") String courseId,
                                             @Param("stuId") String stuId,
                                             @Param("classroomId") String classroomId);

    List<KnowledgeUnitVO> getUnitList(@Param("courseId") String courseId);

    List<UnitEvalDto> getEvalUnitScore(@Param("whitePaperIdList") List<PaperInfoDto> whitePaperIdList,
                                       @Param("courseId") String courseId,
                                       @Param("stuId") String stuId,
                                       @Param("classroomId") String classroomId);

    List<TestPaperInfoVO> getTestPaperInfo(@Param("courseId") String courseId, @Param("search") String search);

    String getCourseNameByCourseId(@Param("courseId") String courseId);

    String getCreatorByCreatorId(@Param("creatorId") String creatorId);

    List<TestStudentVO> getTestStudentList(@Param("testId") String testId);

    List<ClassroomVO> getClassroomList(@Param("courseId") String courseId,@Param("classroomName") String classroomName);

    String getTermNameByTermId(@Param("termId") String termId);

    List<PaperInfoDto> getPaperInfoByTestId(@Param("testId") String testId);

    List<KeywordVO> getKeyWordIdByPaperId(@Param("whitePaperIdList") List<PaperInfoDto> whitePaperIdList);

    List<AbilityVO> getAbilityByPaperId(@Param("whitePaperIdList") List<PaperInfoDto> whitePaperIdList);

    List<KwaVO> getPaperKwaList(@Param("whitePaperIdList") List<PaperInfoDto> whitePaperIdList);

    List<KwaEvalDto> getEvalKwaScore(@Param("whitePaperIdList") List<PaperInfoDto> whitePaperIdList,
                                     @Param("courseId") String courseId,
                                     @Param("classroomId") String classroomId,
                                     @Param("stuId") String stuId);

    @Select("select begindate  from cm_term where iscurrentterm = 1")
    String getCurrentTermStartTime();
}

