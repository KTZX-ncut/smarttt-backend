package com.example.smartttcourse.mapper;

import com.example.smartttcourse.pojo.CmCourseFile;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMangtMapper {

    @Select("select * from cm_course_file where type = #{type} and obsid = #{obsid}")
    List<CmCourseFile> getFileList(@Param("obsid") String obsid, @Param("type") String type);


    @Delete("delete from cm_course_file where obsid = #{obsid} and type = #{type} and filename = #{filename}")
    void deleteFile(@Param("obsid") String obsid, @Param("type") String type,@Param("filename")String filename);
}
