package com.example.smartttcourse.service.impl;

import com.example.smartttcourse.dto.CourseClassroomReq;
import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.mapper.CmClassRoomMapper;
import com.example.smartttcourse.mapper.CmCourseMapper;
import com.example.smartttcourse.mapper.CmTermMapper;
import com.example.smartttcourse.pojo.CmClassroom;
import com.example.smartttcourse.service.CmClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.smartttcourse.Utils.CommonFunctions.generateEnhancedID;

@Service
public class CmClassRoomServiceImpl implements CmClassRoomService {
    @Autowired
    private CmClassRoomMapper cmClassRoomMapper;
    @Autowired
    private CmTermMapper cmTermMapper;
    @Autowired
    private CmCourseMapper cmCourseMapper;
    @Override
    public Result getClassRoomList(Token token) {
        List<CourseClassroomReq> courseClassroomReqList = cmCourseMapper.getCourseName(token);
        for(CourseClassroomReq courseClassroomReq:courseClassroomReqList) {
            courseClassroomReq.setClassroomReqList(cmClassRoomMapper.getClassRoomList(courseClassroomReq.getId()));
        }
        return Result.success(courseClassroomReqList);
    }

    @Override
    public Result deleteClassroom(List<String> ids) {
        cmClassRoomMapper.deleteClassroom(ids);
        return Result.success();
    }

    @Override
    public Result createOneClassroom(CmClassroom classroom) {
        classroom.setId(generateEnhancedID("cm_classroom"));
        classroom.setTermId(cmTermMapper.getCurrentTerm());
        cmClassRoomMapper.createClassroom(classroom);
        return Result.success();
    }

    @Override
    public Result updateOneClassroom(CmClassroom classroom) {
        cmClassRoomMapper.updateOneClassroom(classroom);
        return Result.success();
    }
}
