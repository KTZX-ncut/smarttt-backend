package com.example.smartttcourse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.dto.StudentDto;
import com.example.smartttcourse.pojo.CmClassroomStudent;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author lunSir
 * @create 2024-09-05 14:08
 */
public interface CmClassroomStudentService extends IService<CmClassroomStudent>{

    Result getStudentListByClassRoomId(String classRoomId);

    boolean deleteClassRoomStudent(List<String> ids, String classRoomId);

    boolean deleteClassRoomStudentAll(String classRoomId);

    Result getStudentList(StudentDto studentDto);

    List<CmClassroomStudent> importClassRoomStudentExcel(MultipartFile file);
}
