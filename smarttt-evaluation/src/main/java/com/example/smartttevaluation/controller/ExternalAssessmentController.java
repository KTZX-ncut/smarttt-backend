package com.example.smartttevaluation.controller;

import com.example.smartttevaluation.dto.Token;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.service.impl.FeExternalAssessmentServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;

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
     *
     * @param file Excel 文件（form-data 上传）
     * @param classroomId 可选课堂ID（测试阶段用）
     * @return 导入结果
     */
    @PostMapping("/import")
    public Result importExternalAssessment(@RequestParam("file") MultipartFile file,
                                           @RequestParam(value = "classroomId", required = false) String classroomId) {
        try {
            // ✅ 从上下文中获取登录 token
            Token token = getTokenFromContext();
            if (token == null) {
                /*return Result.error(-710, "请登录");\*/
                classroomId = "292104772-1fadaf0b-0b82-4f42-8181-b1621279e074";
            }

            // ✅ 如果 classroomId 没传，从 token 获取 obsid
            if (classroomId == null) {
                String obsid = token.getObsid();
                if (obsid == null) {
                    return Result.error(-710, "token 不合法");
                }
                classroomId = obsid;
            }

            // ✅ 调用 Service 导入
            feExternalAssessmentService.importExternalAssessment(file, classroomId);

            // ✅ 注意：Result.ok() 不能带参数，因此这里直接返回 success 风格
            return Result.success("外部考核数据导入成功");

        } catch (Exception e) {
            log.error("外部考核导入失败：{}", e.getMessage(), e);
            return Result.error(-500, "导入失败：" + e.getMessage());
        }
    }

    /**
     * 从上下文获取 Token
     */
    private Token getTokenFromContext() {
        try {
            // 注意：Utils 首字母大写
            return com.example.smartttevaluation.Utils.AuthorizationAspect.getTokenFromContext();
        } catch (Exception e) {
            log.warn("⚠️ 无法从上下文获取 Token：{}", e.getMessage());
            return null;
        }
    }
}
