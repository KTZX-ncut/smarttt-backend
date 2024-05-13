package com.example.smartttcourse.controller;

import com.example.smartttcourse.Utils.AuthRequired;
import com.example.smartttcourse.dto.ClassroomReq;
import com.example.smartttcourse.dto.Result;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.service.CmClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.example.smartttcourse.Utils.AuthorizationAspect.getTokenFromContext;

@RestController
@RequestMapping("/coursemangt/classroom")
public class ClassRoomMangtController {
    @Autowired
    private CmClassRoomService cmClassRoomService;

    @GetMapping
    @AuthRequired(type = "admin",menu = "531500340-64d18378-9bfe-48c2-b72e-3cc672826b93",isReadOnly = true)
    public Result getClassRoomList(HttpServletRequest request){
        Token token = getTokenFromContext();
        return cmClassRoomService.getClassRoomList(token);
    }
    @PostMapping("/delete")
    public Result deleteClassroom(@RequestBody List<String> ids){
        return cmClassRoomService.deleteClassroom(ids);
    }
    @PostMapping("/create")
    public Result createOneClassroom(@RequestBody ClassroomReq classroomReq){
        return cmClassRoomService.createOneClassroom(classroomReq);
    }

}
