package com.example.smartttexam.service;

import com.example.smartttexam.dto.PaperAutoGenerateRequest;
import com.example.smartttexam.dto.PaperManualGenerateRequest;
import com.example.smartttexam.dto.Result;

/**
 * 组卷服务
 */
public interface PaperService {

    /** 自动组卷 */
    Result autoGenerate(PaperAutoGenerateRequest request);

    /** 手动组卷 */
    Result manualGenerate(PaperManualGenerateRequest request);

    /** 获取课堂下的试卷（classroomId可选，为空则查课程下所有） */
    Result getPaperList(String courseId, String classroomId);

    /** 获取试卷中的题目列表 */
    Result getPaperQuestions(String paperId);

    /** 发布试卷 */
    Result publishPaper(String paperId, String classroomId);

    /** 删除试卷 */
    Result deletePaper(String paperId);
}
