package com.example.smartttcourse.service;

import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.pojo.CmClassroom;

import java.util.List;

public interface CmClassRoomService {
    Result getClassRoomList(Token token);

    Result deleteClassroom(List<String> ids);

    Result createOneClassroom(CmClassroom classroomReq);

    Result updateOneClassroom(CmClassroom classroom);

    String getClassRoomByClassRoomName(String classRoomName);
}
