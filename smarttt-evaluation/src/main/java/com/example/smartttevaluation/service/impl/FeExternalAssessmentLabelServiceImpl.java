package com.example.smartttevaluation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.smartttevaluation.mapper.FeExternalAssessmentTaskDetailMapper;
import com.example.smartttevaluation.mapper.FeExternalAssessmentTaskLabelMapper;
import com.example.smartttevaluation.mapper.FeExternalAssessmentTaskMapper;
import com.example.smartttevaluation.pojo.FeExternalAssessmentTask;
import com.example.smartttevaluation.pojo.FeExternalAssessmentTaskDetail;
import com.example.smartttevaluation.pojo.FeExternalAssessmentTaskLabel;
import com.example.smartttevaluation.service.FeExternalAssessmentLabelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeExternalAssessmentLabelServiceImpl implements FeExternalAssessmentLabelService {

    private final FeExternalAssessmentTaskLabelMapper labelMapper;
    private final FeExternalAssessmentTaskMapper taskMapper;
    private final FeExternalAssessmentTaskDetailMapper detailMapper;

    @Override
    public void addLabel(FeExternalAssessmentTaskLabel label) {
        label.setCreatedAt(LocalDateTime.now());
        label.setUpdatedAt(LocalDateTime.now());
        labelMapper.insert(label);
    }

    @Override
    public List<FeExternalAssessmentTaskLabel> getLabelsByClassroom(String classroomId) {
        return labelMapper.selectList(
                new QueryWrapper<FeExternalAssessmentTaskLabel>().eq("classroom_id", classroomId)
        );
    }

    @Override
    public void updateLabel(FeExternalAssessmentTaskLabel label) {
        label.setUpdatedAt(LocalDateTime.now());
        int rows = labelMapper.updateById(label);
        if (rows == 0) {
            throw new RuntimeException("修改失败：标签不存在");
        }
    }


    @Override
    @Transactional
    public void deleteLabel(String id) {
        // 查任务
        List<FeExternalAssessmentTask> tasks =
                taskMapper.selectList(new QueryWrapper<FeExternalAssessmentTask>().eq("label_id", id));

        if (tasks.isEmpty()) {
            // 检查标签是否存在
            FeExternalAssessmentTaskLabel exist = labelMapper.selectById(id);
            if (exist == null) {
                throw new RuntimeException("删除失败：标签不存在");
            }
        }

        // 删除详情
        for (FeExternalAssessmentTask task : tasks) {
            detailMapper.delete(new QueryWrapper<FeExternalAssessmentTaskDetail>()
                    .eq("ex_assessment_task_id", task.getId()));
        }

        // 删除任务
        taskMapper.delete(new QueryWrapper<FeExternalAssessmentTask>()
                .eq("label_id", id));

        // 删除标签
        int rows = labelMapper.deleteById(id);
        if (rows == 0) {
            throw new RuntimeException("删除失败：标签不存在或已删除");
        }
    }

}
