package com.example.smartttevaluation.controller;

import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.pojo.FeExternalAssessmentTaskLabel;
import com.example.smartttevaluation.service.FeExternalAssessmentLabelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.List;

/**
 * 外部考核标签控制器
 */
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
     * 获取外部考核标签（支持从 token 自动识别 classroomId）
     */
    @GetMapping({"/list", "/list/{classroomId}"})
    public Result getLabelsByClassroom(HttpServletRequest request,
                                       @PathVariable(value = "classroomId", required = false) String classroomId) {
        try {
            // 1️⃣ 获取 token
            String authHeader = request.getHeader("Authorization");
            String tokenHeader = request.getHeader("token");

            log.info("📥 Authorization Header = {}", authHeader);
            log.info("📥 token Header = {}", tokenHeader);

            String token = null;
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
            } else if (tokenHeader != null && !tokenHeader.isEmpty()) {
                token = tokenHeader;
            }

            if (token == null || token.trim().isEmpty()) {
                throw new RuntimeException("缺少或无效的 token");
            }

            // 2️⃣ 从 token 中解析 classroomId
            if (classroomId == null || classroomId.isEmpty()) {
                try {
                    String[] tokenParts = token.split("\\.");
                    if (tokenParts.length >= 2) {
                        String decoded = new String(Base64.getDecoder().decode(tokenParts[1]));
                        if (decoded.contains("obsid")) {
                            int start = decoded.indexOf("obsid") + 8;
                            int end = decoded.indexOf('"', start);
                            classroomId = decoded.substring(start, end);
                            log.info("🎯 从 token 提取 classroomId = {}", classroomId);
                        }
                    }
                } catch (Exception e) {
                    log.warn("⚠️ token 解析 classroomId 失败：{}", e.getMessage());
                }
            }

            if (classroomId == null || classroomId.isEmpty()) {
                throw new RuntimeException("无法确定课堂ID，请检查 token 或参数");
            }

            // 3️⃣ 查询数据
            List<FeExternalAssessmentTaskLabel> list = labelService.getLabelsByClassroom(classroomId);
            log.info("✅ 查询标签成功 classroomId={} 共 {} 条", classroomId, list.size());
            return Result.success(list);

        } catch (Exception e) {
            log.error("❌ 查询标签失败：{}", e.getMessage(), e);
            return Result.error(-401, "查询标签失败：" + e.getMessage());
        }
    }

    /**
     * 修改标签
     */
    @PostMapping("/update")
    public Result updateLabel(@RequestBody FeExternalAssessmentTaskLabel label) {
        labelService.updateLabel(label);
        return Result.success("标签修改成功");
    }

    /**
     * 删除标签（级联任务与详情）
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteLabel(@PathVariable String id) {
        labelService.deleteLabel(id);
        return Result.success("标签删除成功");
    }
}
