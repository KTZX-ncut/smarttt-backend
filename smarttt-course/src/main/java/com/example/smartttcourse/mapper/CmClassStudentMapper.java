package com.example.smartttcourse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.smartttcourse.pojo.CmClassroomStudent;
import com.example.smartttcourse.vo.StudentInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lunSir
 * @create 2024-09-05 14:15
 */
@Mapper
public interface CmClassStudentMapper extends BaseMapper<CmClassroomStudent>{
    @Select("SELECT cs.*, ss.stuno,ss.id stuId FROM cm_classroom_student cs\n" +
            "LEFT JOIN sm_student ss\n" +
            "ON cs.userId = ss.usersid\n" +
            "WHERE cs.classroomId = #{classRoomId}")
    List<StudentInfoVO> getStudentListByClassRoomId(@Param("classRoomId") String classRoomId);
}
