package com.example.smartttcourse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.smartttcourse.pojo.CmCourseFile;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

/**
 * @author lunSir
 * @create 2024-10-30 23:48
 */
@Mapper
public interface CourseFileMapper extends BaseMapper<CmCourseFile> {
    String getUserIdentity( @Param("roleid") String roleid);
}
