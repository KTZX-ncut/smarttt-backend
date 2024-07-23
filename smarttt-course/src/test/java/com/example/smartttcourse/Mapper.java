package com.example.smartttcourse;

import com.example.smartttcourse.pojo.CmClassroomStudent;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface Mapper {


    @Select("select st_users.id as userId,sm_student.obsid as obsId, st_users.username as userName ,st_users.loginname as loginname " +
            "from sm_student,st_users where sm_student.usersid = st_users.id and sm_student.obsid = #{obsid}")
    List<CmClassroomStudent> getStudent(String obsid);

    @Insert("insert into cm_classroom_student(id,classroomId,userId,obsId,userName,loginname,rowNo) values (#{id},#{classroomId},#{userId},#{obsId},#{userName},#{loginname},#{rowNo})")
    void insertStudent(CmClassroomStudent cmClassroomStudent);
}
