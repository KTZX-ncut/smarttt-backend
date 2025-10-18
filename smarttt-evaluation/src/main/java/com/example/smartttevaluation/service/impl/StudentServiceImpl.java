package com.example.smartttevaluation.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.smartttadmin.pojo.SmStudent;
import com.example.smartttevaluation.mapper.StudentMapper;
import com.example.smartttevaluation.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, SmStudent> implements StudentService {
}
