package com.example.smartttcourse.service;

import com.example.smartttcourse.dto.ClassroomReq;
import com.example.smartttcourse.dto.Result;
import com.example.smartttcourse.dto.Token;

import java.util.List;

public interface CmClassRoomService {
    Result getClassRoomList(Token token);

    Result deleteClassroom(List<String> ids);

    Result createOneClassroom(ClassroomReq classroomReq);
}
