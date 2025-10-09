package com.example.smartttevaluation.service.impl;

import com.alibaba.excel.EasyExcel;
import com.example.smartttevaluation.mapper.FeExternalAssessmentTaskDetailMapper;
import com.example.smartttevaluation.mapper.FeExternalAssessmentTaskLabelMapper;
import com.example.smartttevaluation.mapper.FeExternalAssessmentTaskMapper;
import com.example.smartttevaluation.pojo.FeExternalAssessmentTask;
import com.example.smartttevaluation.pojo.FeExternalAssessmentTaskDetail;
import com.example.smartttevaluation.pojo.FeExternalAssessmentTaskLabel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeExternalAssessmentServiceImpl {

    private final FeExternalAssessmentTaskLabelMapper labelMapper;
    private final FeExternalAssessmentTaskMapper taskMapper;
    private final FeExternalAssessmentTaskDetailMapper detailMapper;

    /**
     * 动态识别考核项（从表头读取任务列）
     */
    public void importExternalAssessment(MultipartFile file, String classroomId) {
        try (InputStream inputStream = file.getInputStream()) {

            // 1️⃣ 读取整个表，包括表头
            List<Map<Integer, String>> allRows = EasyExcel.read(inputStream)
                    .sheet()
                    .headRowNumber(0)
                    .doReadSync();

            if (allRows.isEmpty()) {
                throw new RuntimeException("Excel 文件为空！");
            }

            // 2️⃣ 第一行是表头
            Map<Integer, String> headerRow = allRows.get(0);
            List<Map<Integer, String>> dataRows = allRows.subList(1, allRows.size());

            // 3️⃣ 创建标签
            FeExternalAssessmentTaskLabel label = new FeExternalAssessmentTaskLabel();
            label.setClassroomId(classroomId);
            label.setLabelName("外部考核_" + LocalDateTime.now());
            label.setCreatedAt(LocalDateTime.now());
            label.setUpdatedAt(LocalDateTime.now());
            labelMapper.insert(label);

            log.info("✅ 创建标签成功 labelId={} classroomId={}", label.getId(), classroomId);

            // 4️⃣ 自动识别任务列（排除固定字段）
            List<String> fixedCols = Arrays.asList("序号", "学号", "姓名", "班级");
            Map<Integer, FeExternalAssessmentTask> taskMap = new HashMap<>();

            for (Map.Entry<Integer, String> entry : headerRow.entrySet()) {
                String colName = entry.getValue();
                if (colName == null) continue;
                if (fixedCols.stream().anyMatch(colName::contains)) continue;

                // 创建任务
                FeExternalAssessmentTask task = new FeExternalAssessmentTask();
                task.setLabelId(label.getId());
                task.setExAssessmentName(colName.trim());
                taskMapper.insert(task);
                taskMap.put(entry.getKey(), task);
                log.info("🆕 动态识别任务列：{}", colName);
            }

            // 5️⃣ 遍历学生数据
            for (Map<Integer, String> row : dataRows) {
                String stuno = row.get(1);
                String studentName = row.get(2);
                if (stuno == null || studentName == null) continue;

                for (Map.Entry<Integer, FeExternalAssessmentTask> entry : taskMap.entrySet()) {
                    String scoreStr = row.get(entry.getKey());
                    if (scoreStr == null || !scoreStr.matches("^-?\\d+(\\.\\d+)?$")) {
                        log.warn("⚠️ 非数字成绩跳过：{}", scoreStr);
                        continue;
                    }

                    FeExternalAssessmentTaskDetail detail = new FeExternalAssessmentTaskDetail();
                    detail.setExAssessmentTaskId(entry.getValue().getId());
                    detail.setStuno(stuno);
                    detail.setStudentName(studentName);
                    detail.setStuScore(Double.parseDouble(scoreStr));
                    detail.setFullScore(100.0);
                    detailMapper.insert(detail);
                }
            }

            log.info("✅ 外部考核动态导入完成 labelId={} classroomId={}", label.getId(), classroomId);

        } catch (Exception e) {
            log.error("❌ 导入外部考核失败：{}", e.getMessage(), e);
            throw new RuntimeException("导入外部考核失败：" + e.getMessage());
        }
    }
}
