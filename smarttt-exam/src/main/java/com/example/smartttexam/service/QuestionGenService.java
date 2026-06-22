package com.example.smartttexam.service;

import com.example.smartttexam.dto.QuestionGenRequest;
import com.example.smartttexam.dto.Result;

import java.util.List;

/**
 * AI出题服务
 */
public interface QuestionGenService {

    /**
     * 获取课程下所有KWA（关键字+能力组合），供前端勾选
     */
    Result getCourseKwa(String courseId);

    /**
     * 调用AI生成题目，存入题库
     */
    Result generateQuestions(QuestionGenRequest request);

    /**
     * 查询某课程的所有题目
     */
    Result getQuestionsByCourse(String courseId);

    /**
     * 删除题目（软删除，status→3）
     */
    Result deleteQuestions(List<String> libIds);
}
