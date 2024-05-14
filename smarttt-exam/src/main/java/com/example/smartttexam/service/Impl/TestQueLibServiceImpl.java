package com.example.smartttexam.service.Impl;

import com.example.smartttexam.dto.Result;
import com.example.smartttexam.mapper.TestQueLibMapper;
import com.example.smartttexam.service.TestQueLibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestQueLibServiceImpl implements TestQueLibService {
    @Autowired
    private TestQueLibMapper testQueLibMapper;
    @Override
    public Result getCourseQL(String courseId) {
        return Result.success(testQueLibMapper.getCourseQL(courseId));
    }
}
