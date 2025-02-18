package com.example.smartttcourse.service.impl;

import com.example.smartttcourse.dto.ClassroomReq;
import com.example.smartttcourse.dto.CourseClassroomReq;
import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.mapper.CmClassRoomMapper;
import com.example.smartttcourse.mapper.CmCourseMapper;
import com.example.smartttcourse.mapper.CmTermMapper;
import com.example.smartttcourse.mapper.StUsersMapper;
import com.example.smartttcourse.pojo.CmClassroom;
import com.example.smartttcourse.pojo.StRoleUser;
import com.example.smartttcourse.service.CmClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.example.smartttcourse.Utils.CommonFunctions.*;

@Service
public class CmClassRoomServiceImpl implements CmClassRoomService {
    @Autowired
    private CmClassRoomMapper cmClassRoomMapper;
    @Autowired
    private CmTermMapper cmTermMapper;
    @Autowired
    private CmCourseMapper cmCourseMapper;
    @Autowired
    private StUsersMapper stUsersMapper;
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
        StRoleUser stRoleUser = new StRoleUser(generateEnhancedID("st_roleuser"),classroom.getTeacherId(),classroomRoleId,classroom.getId(),-1, LocalDate.now().toString(),null);
        stUsersMapper.createOneRoleUser(stRoleUser);
        //新增实验教师角色
        stRoleUser.setRoleid(labRoleId);
        stRoleUser.setId(generateEnhancedID("st_roleuser"));
        stRoleUser.setCreatetime(LocalDate.now().toString());
        stUsersMapper.createOneRoleUser(stRoleUser);
        return Result.success();
    }

    /**
     * 需要改负责人
     * @param classroom
     * @return
     */
    @Override
    public Result updateOneClassroom(CmClassroom classroom) {
        cmClassRoomMapper.updateOneClassroom(classroom);
        classroom.setRemark(classroomRoleId);
        stUsersMapper.updateClassroomTeacher(classroom);
        classroom.setRemark(labRoleId);
        stUsersMapper.updateClassroomTeacher(classroom);
        return Result.success();
    }

    @Override
    public String getClassRoomByClassRoomName(String classRoomName) {
        return cmClassRoomMapper.getClassRoomByClassRoomName(classRoomName);
    }

    @Override
    public String getTermIdByClassroomId(String classroomId) {
        return cmClassRoomMapper.getTermIdByClassroomId(classroomId);
    }

    @Override
    public String getCourseIdByClassroomId(String classroomId) {
        return cmClassRoomMapper.getCourseIdByClassroomId(classroomId);
    }

    @Override
    public List<String> getClassroomIdByCourseId(String courseId) {
        return cmClassRoomMapper.getClassroomIdByCourseId(courseId);
    }
}
