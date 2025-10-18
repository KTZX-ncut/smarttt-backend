package com.example.smartttevaluation.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.smartttadmin.pojo.SmStudent;
import com.example.smartttevaluation.service.StudentService;
import com.example.smartttevaluation.exception.cus.BusinessException;
import com.example.smartttevaluation.pojo.CmClassroomStudent;
import com.example.smartttevaluation.service.CmClassroomStudentService;
import com.example.smartttevaluation.dto.ExternalAssessmentExcel;
import com.example.smartttevaluation.mapper.FeExternalAssessmentTaskDetailMapper;
import com.example.smartttevaluation.mapper.FeExternalAssessmentTaskLabelMapper;
import com.example.smartttevaluation.mapper.FeExternalAssessmentTaskMapper;
import com.example.smartttevaluation.pojo.FeExternalAssessmentTask;
import com.example.smartttevaluation.pojo.FeExternalAssessmentTaskDetail;
import com.example.smartttevaluation.pojo.FeExternalAssessmentTaskLabel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 外部考核导入实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FeExternalAssessmentServiceImpl {

    private final FeExternalAssessmentTaskLabelMapper labelMapper;
    private final FeExternalAssessmentTaskMapper taskMapper;
    private final FeExternalAssessmentTaskDetailMapper detailMapper;
    private final CmClassroomStudentService classroomStudentService;
    private final StudentService studentService;

    @Transactional(rollbackFor = Exception.class)
    public void importExternalAssessment(List<ExternalAssessmentExcel> list,
                                         List<String> externalAssessmentNameList,
                                         String classroomId,
                                         String externalLabelId) {
        FeExternalAssessmentTaskLabel label = labelMapper.selectById(externalLabelId);
        if (Objects.isNull(label)) throw new BusinessException("externalLabelId外部考核id不合法");
        for (String externalAssessmentName : externalAssessmentNameList){
            // 创建外部考核任务
            FeExternalAssessmentTask task = new FeExternalAssessmentTask();
            task.setLabelId(externalLabelId);
            task.setExAssessmentName(externalAssessmentName);
            taskMapper.insert(task);
            // 创建外部考核任务详情
            for (ExternalAssessmentExcel po : list){
                String stuNo = po.getStuNo();
                String className = po.getClassName();
                String studentName = po.getStudentName();
                // 通过学号获取本班级的这个学生的信息
                QueryWrapper<SmStudent> stuQueryWrapper = new QueryWrapper<>();
                stuQueryWrapper.eq("stuno",stuNo);
                List<SmStudent> studentList = studentService.list(stuQueryWrapper);
                if (Objects.isNull(studentList) || studentList.isEmpty()) throw new BusinessException("不存在学号为"+stuNo+"的学生！");
                if (studentList.size() > 1) throw new BusinessException("学号为"+stuNo+"的学生有多个,数据已被污染！");
                SmStudent student = studentList.get(0);
                // 校验学生是不是本课堂的学生
                List<CmClassroomStudent> classroomStudentList = classroomStudentService.list(new QueryWrapper<CmClassroomStudent>()
                        .eq("classroomId", classroomId)
                        .eq("userId", student.getUsersid()));
                if (Objects.isNull(classroomStudentList) || classroomStudentList.isEmpty()) throw new BusinessException("本班级不存在学号为"+stuNo+"的学生！");
                if (classroomStudentList.size() > 1) throw new BusinessException("本班级学号为"+stuNo+"的学生有多个,数据已被污染！");
                // 校验学号，姓名，班级的一致性
                CmClassroomStudent classroomStudent = classroomStudentList.get(0);
                if (!Objects.equals(classroomStudent.getUserName(), studentName) || !Objects.equals(classroomStudent.getObsName(), className)){
                    throw new BusinessException("学号"+stuNo+"的姓名或班级有误！");
                }
                // 插入数据
                FeExternalAssessmentTaskDetail detail = new FeExternalAssessmentTaskDetail();
                detail.setExAssessmentTaskId(task.getId());
                detail.setStuno(stuNo);
                detail.setStudentName(studentName);
                detail.setStuScore(po.getAssessmentMap().get(externalAssessmentName));
                detailMapper.insert(detail);
            }
        }

    }
}
