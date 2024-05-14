package com.example.smartttcourse.service.impl;

import com.example.smartttcourse.dto.ClassroomReq;
import com.example.smartttcourse.dto.Result;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.mapper.CmClassRoomMapper;
import com.example.smartttcourse.mapper.CmTermMapper;
import com.example.smartttcourse.service.CmClassRoomService;
import com.example.smartttcourse.service.CmTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmClassRoomServiceImpl implements CmClassRoomService {
    @Autowired
    private CmClassRoomMapper cmClassRoomMapper;
    @Autowired
    private CmTermMapper cmTermMapper;
    @Override
    public Result getClassRoomList(Token token) {
        String currentTerm = cmTermMapper.getCurrentTerm();
        List<ClassroomReq> classroomReqList  = cmClassRoomMapper.getClassRoomList(token.getObsid(),currentTerm);
        for(ClassroomReq classroomReq:classroomReqList){
            classroomReq.setCourseChineseName(cmClassRoomMapper.getCourseName(classroomReq.getCourseId()));
        }
        return Result.success(classroomReqList);
    }

    @Override
    public Result deleteClassroom(List<String> ids) {
        cmClassRoomMapper.deleteClassroom(ids);
        return Result.success();
    }

    @Override
    public Result createOneClassroom(ClassroomReq classroomReq) {
        cmClassRoomMapper.createClassroom(classroomReq);
        return Result.success();
    }
}
