package com.example.smartttcourse.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.smartttcourse.pojo.CmCourseFile;

/**
 * @author lunSir
 * @create 2024-10-30 23:47
 */
public interface CourseFileService extends IService<CmCourseFile> {
    String getUserIdentity(String roleid);
}
