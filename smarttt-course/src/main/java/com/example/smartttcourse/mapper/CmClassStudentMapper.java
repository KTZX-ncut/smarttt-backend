package com.example.smartttcourse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.smartttcourse.pojo.CmClassroomStudent;
import com.example.smartttcourse.dto.StudentInfoDto;
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

    List<StudentInfoDto> getStudentListByClassRoomId(@Param("classRoomId") String classRoomId);

    @Select("SELECT obsname FROM cm_classroom_student WHERE userId = #{userId}")
    String getObsNameByUserId(@Param("userId") String userId);
}
