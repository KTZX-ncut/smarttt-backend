package com.example.smartttcourse.mapper;

import com.example.smartttcourse.pojo.CmCourseFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMangtMapper {
    @Insert("insert into cm_course_file (id, obsid, filename, size, type, createtime, remark) " +
            "VALUES (#{id}, #{obsid}, #{filename}, #{size}, #{type}, #{createtime}, #{remark})")
    void createNewFile(CmCourseFile cmCourseFile);

    @Select("select * from cm_course_file where type = #{type} and obsid = #{obsid}")
    List<CmCourseFile> getFileList(@Param("obsid") String obsid, @Param("type") String type);
}
