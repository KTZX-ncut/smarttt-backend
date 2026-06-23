package com.example.smartttexam.service;

import com.example.smartttexam.dto.QuestionGenRequest;
import com.example.smartttexam.dto.Result;

import java.util.List;

public interface QuestionGenService {

    Result getCourseKwa(String courseId);

    Result generateQuestions(QuestionGenRequest request);

    /** 分页查询课程题目 */
    Result getQuestionsByCourse(String courseId, int pageIndex, int pageSize);

    Result deleteQuestions(List<String> libIds);
}
