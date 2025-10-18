package com.example.smartttevaluation.controller;

import com.alibaba.excel.EasyExcel;
import com.example.smartttevaluation.Utils.AuthRequired;
import com.example.smartttevaluation.dto.ExternalAssessmentExcel;
import com.example.smartttevaluation.dto.Token;
import com.example.smartttevaluation.exception.res.ResponseEnum;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.exception.utils.SmartAssert;
import com.example.smartttevaluation.listener.ExternalAssessmentExcelListener;
import com.example.smartttevaluation.service.impl.FeExternalAssessmentServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.smartttevaluation.Utils.AuthorizationAspect.getTokenFromContext;

/**
 * 外部考核导入接口控制器
 */
@RestController
@RequestMapping("/external-assessment")
@RequiredArgsConstructor
@Slf4j
public class ExternalAssessmentController {

    private final FeExternalAssessmentServiceImpl feExternalAssessmentService;

    /**
     * 导入外部考核任务 Excel
     */
    @PostMapping("/import")
    @AuthRequired
    public Result importExternalAssessment(@RequestParam("file") MultipartFile file,
                                           @RequestParam("externalLabelId") String externalLabelId,
                                           HttpServletRequest request) {
        // 获取token中的classroomId(obsid)
        Token token = getTokenFromContext();
        SmartAssert.checkExpression(token != null, ResponseEnum.TOKEN_IS_NULL);
        String classroomId = token.getObsid();
        SmartAssert.checkExpression(classroomId != null, ResponseEnum.CLASSROOM_ID_NOT_NULL);

        // 读取excel数据
        ExternalAssessmentExcelListener listener = new ExternalAssessmentExcelListener();
        try {
            EasyExcel.read(file.getInputStream(), ExternalAssessmentExcel.class, listener)
                    .sheet()
                    .doRead();
        } catch (IOException e) {
            return Result.error(-710,"导入文件失败！");
        }
        List<ExternalAssessmentExcel> dataList = listener.getDataList();
        List<String> headList = listener.getHeadList();
        List<String> externalAssessmentNameList = new ArrayList<>(headList.subList(3, headList.size()));
        feExternalAssessmentService.importExternalAssessment(dataList,externalAssessmentNameList,classroomId,externalLabelId);

        return Result.ok().msg("导入成功！").code(200);
    }
}
