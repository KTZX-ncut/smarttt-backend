package com.example.smartttcourse.controller;



import com.baomidou.mybatisplus.extension.api.ApiController;
import com.example.smartttcourse.service.SmStudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 学生表(SmStudent)表控制层
 *
 * @author makejava
 * @since 2024-09-06 19:44:01
 */
@RestController
@RequestMapping("smStudent")
public class SmStudentController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SmStudentService smStudentService;
}

