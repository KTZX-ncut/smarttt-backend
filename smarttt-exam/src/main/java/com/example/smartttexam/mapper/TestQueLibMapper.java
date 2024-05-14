package com.example.smartttexam.mapper;

import com.example.smartttexam.dto.Result;
import com.example.smartttexam.pojo.TmTestquelib;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TestQueLibMapper {
    @Select("select id,libNo,questionTypeId,title from tm_testquelib where courseId = #{courseId}")
    List<TmTestquelib> getCourseQL(String courseId);
}
