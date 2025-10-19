package com.example.smartttevaluation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.smartttevaluation.dto.ReachEvaluationDto;
import com.example.smartttevaluation.mapper.FeAssessmentItemsMapper;
import com.example.smartttevaluation.mapper.ReachEvaluationMapper;
import com.example.smartttevaluation.pojo.CmClassroomStudent;
import com.example.smartttevaluation.pojo.FeAssessmentCategories;
import com.example.smartttevaluation.pojo.FeAssessmentItems;
import com.example.smartttevaluation.pojo.FeCourseObjectives;
import com.example.smartttevaluation.service.FeAssessmentItemsService;
import com.example.smartttevaluation.service.ReachEvaluationService;
import com.example.smartttevaluation.vo.ReachCategoryEvaluationVO;
import com.example.smartttevaluation.vo.ReachObjectiveEvaluationVO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReachEvaluationServiceImpl implements ReachEvaluationService {


    private final ReachEvaluationMapper reachEvaluationMapper;

    private final FeAssessmentItemsMapper feAssessmentItemsMapper;

    @Override
    public String getCourseIdByClassroomId(String classroomId) {
        return reachEvaluationMapper.getCourseIdByClassroomId(classroomId);
    }

    @Override
    @Transactional
    public void calculate(List<CmClassroomStudent> classroomStudentList,
                          List<FeCourseObjectives> courseObjectivesList,
                          List<FeAssessmentCategories> categoriesList,
                          String classroomId,
                          String courseId) {

        // 每个学生/每个考核类型/每个目标/每个考核项(实验和作业要做数据处理数据结构)
        for (CmClassroomStudent classroomStudent : classroomStudentList){
            for (FeAssessmentCategories category : categoriesList){
                Double reachScore = 0.0;
                for (FeCourseObjectives courseObjectives : courseObjectivesList){
                    // 获取本考核类型下的本课程目标下的所有考核项
                    List<FeAssessmentItems> assessmentItemsList = feAssessmentItemsMapper.selectList(new QueryWrapper<FeAssessmentItems>()
                            .eq("category_id", category.getId())
                            .eq("classroom_id", classroomId)
                            .eq("objective_id", courseObjectives.getId()));
                    ArrayList<ReachEvaluationDto> reachEvaluationDtoArrayList = new ArrayList<>();
                    for (FeAssessmentItems assessmentItems : assessmentItemsList){
                        switch (assessmentItems.getSource()){
                            case 0:
                            case 1: {
                                // 实验/作业
                                ReachEvaluationDto reachEvaluationDto = this.dealLaboratoryAndWorkData(assessmentItems.getTypeId(),classroomStudent.getUserId());
                                reachEvaluationDtoArrayList.add(reachEvaluationDto);
                                break;
                            }
                            case 2:{
                                // 外部考核
                                ReachEvaluationDto reachEvaluationDto = this.dealExternalData(assessmentItems.getTypeId(),classroomStudent.getUserId());
                                reachEvaluationDtoArrayList.add(reachEvaluationDto);
                                break;
                            }
                        }
                    }
                    Double stuScore = 0.0;
                    Double fullScore = 0.0;
                    for (ReachEvaluationDto reachEvaluationDto : reachEvaluationDtoArrayList){
                        stuScore += reachEvaluationDto.getStuScore() == null ? 0 : reachEvaluationDto.getStuScore();
                        fullScore += reachEvaluationDto.getFullScore() == null ? 0 : reachEvaluationDto.getFullScore();
                    }
                    if (fullScore == 0 || fullScore == null){
                        // 该学生还没有参与在本目标下的考核类型下所有考核项
                        continue;
                    }
                    Double reachScoreRate = stuScore / fullScore;
                    // 获取绑定在本考核类型下课程目标分数
                    Double objectiveScore = reachEvaluationMapper.getObjectiveScore(courseObjectives.getId(),category.getId());
                    reachScore += reachScoreRate * objectiveScore;
                }
                // 入库
                this.saveOrUpdateCategoryEvaluation(reachScore,category.getId(),classroomStudent.getUserId(),classroomId,courseId);
            }
        }

        // 每个学生/每个目标/每个考核类型/每个考核项(实验和作业要做数据处理数据结构)
        for (CmClassroomStudent classroomStudent : classroomStudentList){
            for (FeCourseObjectives courseObjectives : courseObjectivesList){
                Double reachScore = 0.0;
                Double assessmentItemFullScore = 0.0;
                for (FeAssessmentCategories category : categoriesList){
                    // 获取本课程目标下的本考核类型下的所有考核项
                    List<FeAssessmentItems> assessmentItemsList = feAssessmentItemsMapper.selectList(new QueryWrapper<FeAssessmentItems>()
                            .eq("category_id", category.getId())
                            .eq("classroom_id", classroomId)
                            .eq("objective_id", courseObjectives.getId()));
                    ArrayList<ReachEvaluationDto> reachEvaluationDtoArrayList = new ArrayList<>();
                    for (FeAssessmentItems assessmentItems : assessmentItemsList){
                        switch (assessmentItems.getSource()){
                            case 0:
                            case 1: {
                                // 实验/作业
                                ReachEvaluationDto reachEvaluationDto = this.dealLaboratoryAndWorkData(assessmentItems.getTypeId(),classroomStudent.getUserId());
                                reachEvaluationDtoArrayList.add(reachEvaluationDto);
                                break;
                            }
                            case 2:{
                                // 外部考核
                                ReachEvaluationDto reachEvaluationDto = this.dealExternalData(assessmentItems.getTypeId(),classroomStudent.getUserId());
                                reachEvaluationDtoArrayList.add(reachEvaluationDto);
                                break;
                            }
                        }
                    }
                    Double stuScore = 0.0;
                    Double fullScore = 0.0;
                    for (ReachEvaluationDto reachEvaluationDto : reachEvaluationDtoArrayList){
                        stuScore += reachEvaluationDto.getStuScore() == null ? 0 : reachEvaluationDto.getStuScore();
                        fullScore += reachEvaluationDto.getFullScore() == null ? 0 : reachEvaluationDto.getFullScore();
                    }
                    if (fullScore == 0 || fullScore == null){
                        // 该学生还没有参与在本目标下的考核类型下所有考核项
                        continue;
                    }
                    Double reachScoreRate = stuScore / fullScore;
                    // 获取绑定在本考核类型下课程目标分数
                    Double objectiveScore = reachEvaluationMapper.getObjectiveScore(courseObjectives.getId(),category.getId());
                    assessmentItemFullScore += objectiveScore;
                    reachScore += reachScoreRate * objectiveScore;
                }
                // 归100分制
                if (assessmentItemFullScore != 0){
                    reachScore = reachScore / assessmentItemFullScore * 100;
                }
                // 入库
                this.saveOrUpdateObjectiveEvaluation(reachScore,courseObjectives.getId(),classroomStudent.getUserId(),classroomId,courseId);
            }
        }

    }

    @Override
    public List<ReachCategoryEvaluationVO> getReachCategoryEvaluation(String classroomId) {
        return reachEvaluationMapper.getReachCategoryEvaluation(classroomId);
    }

    @Override
    public String getStuNoByUserId(String userId) {
        return reachEvaluationMapper.getStuNoByUserId(userId);
    }

    @Override
    public String getStudentNameByUserId(String userId) {
        return reachEvaluationMapper.getStudentNameByUserId(userId);
    }

    @Override
    public List<ReachObjectiveEvaluationVO> getReachObjectiveEvaluation(String classroomId) {
        return reachEvaluationMapper.getReachObjectiveEvaluation(classroomId);
    }

    @Override
    public List<String> getUserIdList(String classroomId) {
        return reachEvaluationMapper.getUserIdList(classroomId);
    }

    private void saveOrUpdateObjectiveEvaluation(Double reachScore, String ObjectiveId, String userId, String classroomId,String courseId) {
        Integer count = reachEvaluationMapper.searchReachEvaluationResultCount(ObjectiveId,userId,classroomId);
        if (!Objects.equals(count,0)){
            // 更新
            reachEvaluationMapper.updateAchievement(reachScore,ObjectiveId,userId,classroomId,courseId);
            return;
        }
        // 插入
        String id = IdWorker.getIdStr();
        reachEvaluationMapper.insertAchievement(id,reachScore,ObjectiveId,userId,classroomId,courseId);
    }


    private void saveOrUpdateCategoryEvaluation(Double reachScore, String categoryId, String userId, String classroomId,String courseId) {
        Integer count = reachEvaluationMapper.searchReachCategoryEvaluationResultCount(categoryId,userId,classroomId);
        if (!Objects.equals(count,0)){
            // 更新
            reachEvaluationMapper.updateCategoryAchievement(reachScore,categoryId,userId,classroomId,courseId);
            return;
        }
        // 插入
        String id = IdWorker.getIdStr();
        reachEvaluationMapper.insertCategoryAchievement(id,reachScore,categoryId,userId,classroomId,courseId);
    }

    /**
     * 处理考核项目类型为实验/作业的数据
     */
    private ReachEvaluationDto dealLaboratoryAndWorkData(String typeId,String userId){
        ReachEvaluationDto reachEvaluationDto = reachEvaluationMapper.searchReachEvaluationData(typeId,userId);
        if (reachEvaluationDto == null) reachEvaluationDto = new ReachEvaluationDto();
        return reachEvaluationDto;
    }

    /**
     * 处理考核项目类型为外部的数据
     */
    private ReachEvaluationDto dealExternalData(String typeId,String userId){
        // 获取学号
        String stuNo = reachEvaluationMapper.getStuNoByUserId(userId);
        ReachEvaluationDto reachEvaluationDto = reachEvaluationMapper.searchExternalData(typeId,stuNo);
        if (reachEvaluationDto == null) reachEvaluationDto = new ReachEvaluationDto();
        return reachEvaluationDto;
    }

}














