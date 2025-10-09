package com.example.smartttevaluation.controller;

import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.pojo.FeExternalAssessmentTaskLabel;
import com.example.smartttevaluation.service.FeExternalAssessmentLabelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/external-assessment/label")
@RequiredArgsConstructor
@Slf4j
public class ExternalAssessmentLabelController {

    private final FeExternalAssessmentLabelService labelService;

    /**
     * 添加外部考核标签
     */
    @PostMapping("/add")
    public Result addLabel(@RequestBody FeExternalAssessmentTaskLabel label) {
        labelService.addLabel(label);
        return Result.success("标签添加成功");
    }

    /**
     * 根据课堂ID查询外部考核标签
     */
    @GetMapping("/list/{classroomId}")
    public Result getLabelsByClassroom(@PathVariable String classroomId) {
        List<FeExternalAssessmentTaskLabel> list = labelService.getLabelsByClassroom(classroomId);
        return Result.success(list);
    }

    /**
     * 根据ID修改外部考核标签
     */
    @PostMapping("/update")
    public Result updateLabel(@RequestBody FeExternalAssessmentTaskLabel label) {
        labelService.updateLabel(label);
        return Result.success("标签修改成功");
    }

    /**
     * 删除外部考核标签（级联删除任务与详情）
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteLabel(@PathVariable String id) {
        labelService.deleteLabel(id);
        return Result.success("标签删除成功");
    }
}
