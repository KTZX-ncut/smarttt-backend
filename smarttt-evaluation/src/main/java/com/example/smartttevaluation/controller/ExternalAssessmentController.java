package com.example.smartttevaluation.controller;

import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.service.impl.FeExternalAssessmentServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.List;
import java.util.Map;

/**
 * 外部考核导入接口控制器
 */
@RestController
@RequestMapping("/external-assessment")
@RequiredArgsConstructor
@Slf4j
public class ExternalAssessmentController {

    private final FeExternalAssessmentServiceImpl feExternalAssessmentService;

    @PostConstruct
    public void init() {
        log.info("✅ ExternalAssessmentController 已加载成功！");
    }

    /**
     * 导入外部考核任务 Excel
     */
    @PostMapping("/import")
    public Result importExternalAssessment(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "classroomId", required = false) String classroomId,
            HttpServletRequest request) {
        try {
            // 1️⃣ 获取 token（兼容 Authorization / token 两种写法）
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

            // 2️⃣ 尝试从 token 解析 classroomId
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

            // 3️⃣ 调用 Service 层执行导入
            List<Map<String, Object>> resultList =
                    feExternalAssessmentService.importExternalAssessment(file, classroomId);

            log.info("✅ 外部考核导入成功 classroomId={} 导入 {} 条", classroomId, resultList.size());
            return Result.success(resultList);

        } catch (Exception e) {
            log.error("❌ 外部考核导入失败：{}", e.getMessage(), e);
            return Result.error(-500, "导入失败：" + e.getMessage());
        }
    }
}
