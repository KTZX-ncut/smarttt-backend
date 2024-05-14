package com.example.smartttcourse.mapper;

import com.example.smartttcourse.dto.ClassroomReq;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CmClassRoomMapper {
    @Select("select * from cm_classroom where courseId in (select id from cm_course where professionId = #{obsid}) and termId = #{termId}")
    List<ClassroomReq> getClassRoomList(@Param("obsid")String obsid,@Param("termId")String termId);

    @Select("select courseChineseName from cm_course where id = #{id}")
    String getCourseName(String id);

    void deleteClassroom(List<String> ids);

    @Insert("insert into cm_classroom(id,classroomName,courseId,teacherId,usedClassList,beginTime,endTime,remark,teacherName,creator,labTeacherId,labTeacher,practiceTeacherId,practiceTeacher,teachTime,labTime,practiceTime)" +
            " values (#{id},#{classroomName},#{courseId},#{teacherId},#{usedClassList},#{beginTime},#{endTime},#{remark},#{teacherName},#{creator},#{labTeacherId},#{labTeacher},#{practiceTeacherId},#{practiceTeacher},#{teachTime},#{labTime},#{practiceTime})"
    )
    void createClassroom(ClassroomReq classroomReq);
}
