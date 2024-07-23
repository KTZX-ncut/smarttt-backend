package com.example.smartttcourse;

import com.example.smartttcourse.pojo.CmClassroomStudent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.example.smartttcourse.Utils.CommonFunctions.generateEnhancedID;

@SpringBootTest
class SmartttCourseApplicationTests {
    @Autowired
    private Mapper mapper;
    @Test
    void insertClassroomStudent(){
        int rowNo = 0;
        String classroomId = "8aea800182e80d000182e886980c0d7a";
        String Obsid = "1597933787-318708d3-4f8d-4def-8f09-6a856de9237f";
        List<CmClassroomStudent> cmClassroomStudents = mapper.getStudent(Obsid);
        for( CmClassroomStudent cmClassroomStudent :cmClassroomStudents){
            cmClassroomStudent.setId(generateEnhancedID("cm_classroom_student"));
            cmClassroomStudent.setClassroomId(classroomId);
            cmClassroomStudent.setRowNo(++rowNo);
            mapper.insertStudent(cmClassroomStudent);
        }

    }

}
