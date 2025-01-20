package com.example.smartttevaluation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.smartttevaluation.dto.CalculatePortraitReq;
import com.example.smartttevaluation.dto.PaperInfoDto;
import com.example.smartttevaluation.dto.StudentDynamicStateReq;
import com.example.smartttevaluation.pojo.AiInStuAnsInfo;
import com.example.smartttevaluation.vo.*;

import java.util.List;

/**
 * (AiInStuAnsInfo)表服务接口
 *
 * @author makejava
 * @since 2024-11-28 19:14:42
 */
public interface AiInStuAnsInfoService extends IService<AiInStuAnsInfo> {
    StudentPortraitVO calculateStudentPortrait(List<PaperInfoDto> whitePaperIdList,
                                         String courseId, String stuId, String classroomId);

    List<TestPaperInfoVO> getTestPaperInfo(String courseId, String search,String classroomId);

    List<TestStudentVO> getTestStudentList(String testId);

    List<ClassroomVO> getClassroomList(String courseId, String classroomName);


    StudentPaperPortraitVO getStudentPaperPortrait(List<PaperInfoDto> whitePaperIdList,
                                                   String stuId,
                                                   String courseId,
                                                   String classroomId);

    StudentPortraitVO calculateClassroomPortrait(List<PaperInfoDto> whitePaperIdList, String courseId, String classroomId,List<String> stuIdList);


    boolean calculatePortrait(CalculatePortraitReq calculatePortraitReq);

    StudentPortraitVO getClassroomPortrait(String courseId, String classroomId, Integer num);

    StudentPortraitVO getStudentPortrait(String courseId, String classroomId, String stuId,Integer num);

    Integer getEvalTotal(String courseId, String classroomId);

    String getCourseIdByClassroomId(String classroomId);

    List<Integer> getStudentEvalNums(String stuId, String courseId, String classroomId);

    boolean modifyStudentDynamicState(List<StudentDynamicStateReq> studentDynamicStateReqList);
}
