package com.example.smartttevaluation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.smartttevaluation.dto.PaperInfoDto;
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

    List<PaperInfoDto> getPaperInfoListByIds(List<String> paperIdList);

    StudentPortraitVO getStudentPortrait(List<PaperInfoDto> whitePaperIdList,
                                         String courseId, String stuId, String classroomId);

    List<TestPaperInfoVO> getTestPaperInfo(String courseId, String search);

    List<TestStudentVO> getTestStudentList(String testId);

    List<ClassroomVO> getClassroomList(String courseId, String classroomName);

    List<PaperInfoDto> getPaperInfoByTestId(String testId);

    StudentPaperPortraitVO getStudentPaperPortrait(List<PaperInfoDto> whitePaperIdList,
                                                   String stuId,
                                                   String courseId,
                                                   String classroomId);

    StudentPortraitVO getClassroomPortrait(List<PaperInfoDto> whitePaperIdList, String courseId, String classroomId);

    String getCurrentTermStartTime();
}
