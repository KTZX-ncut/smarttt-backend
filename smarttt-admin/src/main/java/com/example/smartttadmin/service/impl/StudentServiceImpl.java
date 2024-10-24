package com.example.smartttadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.smartttadmin.mapper.StudentMapper;
import com.example.smartttadmin.pojo.SmStudent;
import com.example.smartttadmin.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * @author lunSir
 * @create 2024-10-18 13:11
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, SmStudent> implements StudentService {
}
