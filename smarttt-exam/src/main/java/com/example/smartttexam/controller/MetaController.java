package com.example.smartttexam.controller;

import com.example.smartttexam.Utils.CommonFunctions;
import com.example.smartttexam.dto.Result;
import com.example.smartttexam.dto.Token;
import com.example.smartttexam.mapper.CmClassroomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 元数据接口 - 提供当前上下文信息（不需要鉴权，仅从token解析上下文）
 */
@RestController
@RequestMapping("/exam/meta")
public class MetaController {

    @Autowired
    private CmClassroomMapper classroomMapper;

    @GetMapping("/courseId")
    public Result getCourseId() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) return Result.error("无法获取请求上下文");

        HttpServletRequest request = attrs.getRequest();
        Token token = CommonFunctions.getToken(request);
        if (token == null || token.getObsid() == null) {
            return Result.error("无法获取身份信息");
        }

        String obsid = token.getObsid();

        int count = classroomMapper.isCourse(obsid);
        if (count > 0) {
            Map<String, String> result = new HashMap<>();
            result.put("courseId", obsid);
            return Result.success(result);
        }

        String courseId = classroomMapper.getCourseIdByClassroomId(obsid);
        if (courseId != null) {
            Map<String, String> result = new HashMap<>();
            result.put("courseId", courseId);
            return Result.success(result);
        }

        return Result.error("无法确定课程ID，obsid=" + obsid);
    }
}
