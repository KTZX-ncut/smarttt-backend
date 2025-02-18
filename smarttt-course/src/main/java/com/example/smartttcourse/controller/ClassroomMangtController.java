package com.example.smartttcourse.controller;

import com.example.smartttcourse.Utils.AuthRequired;
import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.pojo.CmClassroom;
import com.example.smartttcourse.service.CmClassRoomService;
import com.example.smartttcourse.service.SmObsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.example.smartttcourse.Utils.AuthorizationAspect.getTokenFromContext;

@RestController
@RequestMapping("/coursemangt/classroom")
public class ClassroomMangtController {
    @Autowired
    private CmClassRoomService cmClassRoomService;
    @Autowired
    private SmObsService smObsService;

    @GetMapping
    @AuthRequired(type = "admin",menu = "531500340-074abac7-fe4c-4908-aa7e-d72dacd94014",isReadOnly = true)
    public Result getClassRoomList(HttpServletRequest request){
        Token token = getTokenFromContext();
        System.out.println(token.getObsid());
        return cmClassRoomService.getClassRoomList(token);
    }

    @PostMapping("/delete")
    public Result deleteClassroom(@RequestBody List<String> ids){
        return cmClassRoomService.deleteClassroom(ids);
    }
    /*
    token获取创建者信息
     */
    @PostMapping("/create")
    @AuthRequired(type = "admin",menu = "531500340-074abac7-fe4c-4908-aa7e-d72dacd94014",isReadOnly = true)
    public Result createOneClassroom(@RequestBody CmClassroom classroom,HttpServletRequest request){
        Token token = getTokenFromContext();
        classroom.setCreator(token.getId());
        return cmClassRoomService.createOneClassroom(classroom);
    }
    @PostMapping("/update")
    public Result updateClassroom(@RequestBody CmClassroom classroom){
        return cmClassRoomService.updateOneClassroom(classroom);
    }

    @GetMapping("/teacher")
    public Result getTeacherList(HttpServletRequest request){
        String obsID = smObsService.getSchoolObs();
        return smObsService.getObsRPList(obsID);
    }
    @PostMapping("/classroomRP")
    public Result classroomRPList(){
        String obsID = smObsService.getSchoolObs();
        return smObsService.getObsRPList(obsID);
    }

    /**
     * 实验教师获取当前课程下所有的实验课堂
     */
    @GetMapping("/getClassroomListByClassroomId")
    @AuthRequired(type = "admin", menu = "531500340-c860b4c0-743a-40cd-a215-c4b22e351531", isReadOnly = true)
    public Result getClassroomListByClassroomId(HttpServletRequest request) {
        Token token = getTokenFromContext();
        String classroomId = token.getObsid();
        return cmClassRoomService.getClassroomListByClassroomId(classroomId);
    }
}
