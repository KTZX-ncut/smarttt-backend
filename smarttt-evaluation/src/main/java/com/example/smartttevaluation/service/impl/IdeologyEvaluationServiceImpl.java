package com.example.smartttevaluation.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.smartttevaluation.dto.IdeologyEvalDto;
import com.example.smartttevaluation.dto.StudentIdeologyStateReq;
import com.example.smartttevaluation.mapper.IdeologyEvaluationMapper;
import com.example.smartttevaluation.pojo.*;
import com.example.smartttevaluation.service.IdeologyEvaluationService;
import com.example.smartttevaluation.service.IdeologyValueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IdeologyEvaluationServiceImpl implements IdeologyEvaluationService {
    private final IdeologyEvaluationMapper ideologyEvaluationMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean modifyStudentIdeologyState(List<StudentIdeologyStateReq> studentIdeologyStateReqList) {
        Boolean state = true;
        for (StudentIdeologyStateReq studentIdeologyStateReq : studentIdeologyStateReqList){
            state = state & ideologyEvaluationMapper.modifyStudentIdeologyState(studentIdeologyStateReq.getClassroomStudentId(),studentIdeologyStateReq.getIdeologyState());
        }
        return state;
    }

    @Override
    public List<IdeologyCalculatePaper> getPaperList(String classroomId) {
        return ideologyEvaluationMapper.getPaperList(classroomId);
    }

    @Override
    public void calculate(List<CmClassroomStudent> classroomStudentList, List<IdeologyCalculatePaper> paperList, String classroomId, String courseId,List<IdeologyValue> ideologyValueList) {
        // 计算学生的思政评价
        for (CmClassroomStudent classroomStudent : classroomStudentList){
            ideologyEvaluationMapper.deleteStudentIdeologyEvaluation(classroomStudent.getUserId(),classroomId);
            if (Objects.equals(classroomStudent.getIdeologyState(), 0)){
                // 当前学生不参与达成性评价
                continue;
            }

            // 过滤出本课程下的价值列表
            List<IdeologyValue> valueList = ideologyValueList.stream().filter(t -> t.getLevel().equals(2)).collect(Collectors.toList());
            for (IdeologyValue value : valueList){
                // 获取学生答题日志
                List<String> paperIdList = paperList.stream().map(IdeologyCalculatePaper::getPaperId).collect(Collectors.toList());
                List<IdeologyEvalDto> ideologyEvalDtoList = ideologyEvaluationMapper.getQuestionInfo(classroomStudent.getUserId(),paperIdList,classroomId,value.getId());
                Integer vCount = 0;
                List<String> subjectType = Arrays.asList("previewReport", "defence", "report", "video", "image", "reportTemplate", "guideBook", "0205", "0206");
                List<String> objectType = Arrays.asList("0201", "0202", "0203", "0204");
                for (IdeologyEvalDto ideologyEvalDto : ideologyEvalDtoList){
                    // 主观题
                    if (subjectType.contains(ideologyEvalDto.getQuestionTypeId())){
                        Double stuScore = ideologyEvalDto.getStuScore();
                        Double fullScore = ideologyEvalDto.getFullScore();
                        Double rate = stuScore / fullScore;
                        if (rate >= 0.6) vCount ++;
                    }else if (objectType.contains(ideologyEvalDto.getQuestionTypeId())){
                        // 客观题
                        Double stuScore = ideologyEvalDto.getStuScore();
                        Double fullScore = ideologyEvalDto.getFullScore();
                        if (stuScore.equals(fullScore)) vCount ++;
                    }else continue;
                }
                // 入库
                String id = IdWorker.getIdStr();
                ideologyEvaluationMapper.insertStudentIdeologyEvaluation(id,classroomStudent.getUserId(),courseId,value.getId(),classroomId,vCount);
            }

        }
        // 计算本课堂的思政评价
        ideologyEvaluationMapper.deleteClassroomIdeologyEvaluation(classroomId);
        List<IdeologyValue> valueList = ideologyValueList.stream().filter(t -> t.getLevel().equals(2)).collect(Collectors.toList());
        for (IdeologyValue value : valueList){
            List<String> paperIdList = paperList.stream().map(IdeologyCalculatePaper::getPaperId).collect(Collectors.toList());
            List<IdeologyEvalDto> ideologyEvalDtoList = ideologyEvaluationMapper.getQuestionInfoClassroom(paperIdList,classroomId,value.getId());
            List<String> subjectType = Arrays.asList("previewReport", "defence", "report", "video", "image", "reportTemplate", "guideBook", "0205", "0206");
            List<String> objectType = Arrays.asList("0201", "0202", "0203", "0204");
            Integer vCount = 0;
            for (IdeologyEvalDto ideologyEvalDto : ideologyEvalDtoList){
                // 主观题
                if (subjectType.contains(ideologyEvalDto.getQuestionTypeId())){
                    Double stuScore = ideologyEvalDto.getStuScore();
                    Double fullScore = ideologyEvalDto.getFullScore();
                    Double rate = stuScore / fullScore;
                    if (rate >= 0.6) vCount ++;
                }else if (objectType.contains(ideologyEvalDto.getQuestionTypeId())){
                    // 客观题
                    Double stuScore = ideologyEvalDto.getStuScore();
                    Double fullScore = ideologyEvalDto.getFullScore();
                    if (stuScore.equals(fullScore)) vCount ++;
                }else continue;
            }
            // 入库
            String id = IdWorker.getIdStr();
            ideologyEvaluationMapper.insertClassroomIdeologyEvaluation(id,courseId,value.getId(),classroomId,vCount);
        }
    }

    @Override
    public String getCourseIdByClassroomId(String classroomId) {
        return ideologyEvaluationMapper.getCourseIdByClassroomId(classroomId);
    }

    @Override
    public List<StudentIdeologyEvaluation> getIdeologyEvaluationByUserId(String userId, String classroomId) {
        return ideologyEvaluationMapper.getIdeologyEvaluationByUserId(userId,classroomId);
    }

    @Override
    public List<ClassroomIdeologyEvaluation> getIdeologyEvaluationByClassroomId(String classroomId) {
        return ideologyEvaluationMapper.getIdeologyEvaluationByClassroomId(classroomId);
    }
}
