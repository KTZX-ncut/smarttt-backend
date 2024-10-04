package com.example.smartttcourse.controller;


import com.example.smartttcourse.service.CmClassroomMypracticelistService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 我的作业和考试表(CmClassroomMypracticelist)表控制层
 *
 * @author makejava
 * @since 2024-09-06 16:36:20
 */
@RestController
@RequestMapping("cmClassroomMypracticelist")
public class CmClassroomMypracticelistController {
    /**
     * 服务对象
     */
    @Resource
    private CmClassroomMypracticelistService cmClassroomMypracticelistService;
}

