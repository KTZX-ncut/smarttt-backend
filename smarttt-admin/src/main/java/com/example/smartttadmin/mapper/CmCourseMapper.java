package com.example.smartttadmin.mapper;

import com.example.smartttadmin.pojo.CmProfession;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CmCourseMapper {
    @Select("select courseChineseName from cm_course where id = #{obsid}")
    String getCourseName(String obsid);

    @Select("select courseChineseName from cm_course where id = (select courseId from cm_classroom where id = #{obsid})")
    String getCourseNameByClassroom(String obsid);

    @Select("select classroomName from cm_classroom where id = #{obsid}")
    String getClassroomName(String obsid);

    @Update("update cm_course set professionName = #{proname} where professionId = #{id}")
    void updateProfessionName(CmProfession cmProfession);
}
