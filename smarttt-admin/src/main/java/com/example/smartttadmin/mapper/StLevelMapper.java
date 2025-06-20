package com.example.smartttadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.smartttadmin.pojo.StLevel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StLevelMapper extends BaseMapper<StLevel> {
    @Select("select * from st_level")
    List<StLevel> getStLevel();

    @Select("select obsdeep from st_level where teacher = 1")
    long getTeacherLevel();

    @Select("select obsdeep from st_level where id = #{num}")
    long getObsLevel(String num);

//    @Update("update st_level set levelname = #{levelname} where id =#{id}")
    void updateStLevel(StLevel stLevel);

    @Update("update st_level set teacher = 0 where id != #{id}")
    void updateOtherTeacher(long id);

    @Update("update st_level set student = 0 where id != #{id}")
    void updateOtherStudent(long id);


    @Update("update st_level set obsdeep= #{obsdeep} where id = #{id}")
    void updateLevelObsdeep(@Param("obsdeep")long obsdeep,@Param("id")long id);

    @Select("select * from st_level where obsdeep != 0")
    List<StLevel> getStLevelByStatue();

    @Update("update st_level set obsdeep = #{sum} where id = #{id}")
    void updateAllLevel(@Param("id")long id,@Param("sum") long sum);
}
