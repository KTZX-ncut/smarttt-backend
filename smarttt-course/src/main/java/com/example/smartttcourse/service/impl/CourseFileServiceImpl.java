package com.example.smartttcourse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.smartttcourse.mapper.CourseFileMapper;
import com.example.smartttcourse.pojo.CmCourseFile;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author lunSir
 * @create 2024-10-30 23:47
 */
@Service
public class CourseFileServiceImpl extends ServiceImpl<CourseFileMapper, CmCourseFile> implements CourseFileService {

    @Override
    public String getUserIdentity(String roleid) {
       return baseMapper.getUserIdentity(roleid);
    }
}
