package com.example.smartttcourse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.smartttcourse.mapper.SmStudentMapper;
import com.example.smartttcourse.pojo.SmStudent;
import com.example.smartttcourse.service.SmStudentService;
import org.springframework.stereotype.Service;

/**
 * 学生表(SmStudent)表服务实现类
 *
 * @author makejava
 * @since 2024-09-07 10:36:05
 */
@Service
public class SmStudentServiceImpl extends ServiceImpl<SmStudentMapper, SmStudent> implements SmStudentService {
}

