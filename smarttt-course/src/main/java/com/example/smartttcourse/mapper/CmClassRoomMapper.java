package com.example.smartttcourse.mapper;

import com.example.smartttcourse.dto.ClassroomReq;
import com.example.smartttcourse.pojo.CmClassroom;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CmClassRoomMapper {
    @Select("select * from cm_classroom where courseId =#{id}")
    List<ClassroomReq> getClassRoomList(@Param("id")String id);

    @Select("select courseChineseName from cm_course where id = #{id}")
    String getCourseName(String id);

    void deleteClassroom(@Param("ids") List<String> ids);

    @Insert("insert into cm_classroom(id,classroomName,termId,courseId,teacherId,usedClassList,beginTime,endTime,remark,teacherName,creator,creatorName,labTeacherId,labTeacher,practiceTeacherId,practiceTeacher,teachTime,labTime,practiceTime)" +
            " values (#{id},#{classroomName},#{termId},#{courseId},#{teacherId},#{usedClassList},#{beginTime},#{endTime},#{remark},#{teacherName},#{creator},#{creatorName},#{labTeacherId},#{labTeacher},#{practiceTeacherId},#{practiceTeacher},#{teachTime},#{labTime},#{practiceTime})"
    )
    void createClassroom(CmClassroom classroom);

    void updateOneClassroom(CmClassroom classroom);

    @Select("SELECT id FROM cm_classroom\n" +
            "where classroomName = #{classRoomName}")
    String getClassRoomByClassRoomName(@Param("classRoomName") String classRoomName);
}
