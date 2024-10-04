package com.example.smartttcourse.controller;


import com.example.smartttcourse.pojo.CmClassroomHomeworkinfo;
import com.example.smartttcourse.service.CmClassroomHomeworkinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 课堂作业表(CmClassroomHomeworkinfo)表控制层
 *
 * @author makejava
 * @since 2024-09-06 16:36:20
 */
@RestController
@RequestMapping("cmClassroomHomeworkinfo")
public class CmClassroomHomeworkinfoController{
    /**
     * 服务对象
     */
    @Autowired
    private CmClassroomHomeworkinfoService cmClassroomHomeworkinfoService;
}

