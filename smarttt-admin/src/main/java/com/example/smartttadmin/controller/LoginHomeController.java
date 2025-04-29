package com.example.smartttadmin.controller;

import com.example.smartttadmin.Utils.AuthRequired;
import com.example.smartttadmin.dto.*;
import com.example.smartttadmin.pojo.StRoleUser;
import com.example.smartttadmin.service.StMenusService;
import com.example.smartttadmin.service.StRolesService;
import com.example.smartttadmin.service.StUsersService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.example.smartttadmin.Utils.AuthorizationAspect.getTokenFromContext;
import static com.example.smartttadmin.Utils.CommonFunctions.TokenSK;
import static com.example.smartttadmin.Utils.JwtTokenUtils.getToken;

@RestController
@RequestMapping("/homes")
public class LoginHomeController {
   @Autowired
    private StMenusService stMenusService;
   @Autowired
   private StRolesService stRolesService;
   @Autowired
   private StUsersService stUsersService;

    /**
     * 教师类型用户登录到首页
     * @return Result
     */
    @PostMapping("/teacherhome")
    @AuthRequired(type = "admin",menu = "531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65")
    public Result TeacherHome(HttpServletRequest request){
//        return stMenusService.getMenusList(loginHomeReq);
        Token token = getTokenFromContext();
        return stMenusService.getMenusList(token.getRoleid());
    }
    @PostMapping("/studenthome")
    @AuthRequired(type = "admin",menu = "531500340-a9456c2b-c3f7-41b3-b2c4-1fd8e622dcc6")
    public Result StuHome(HttpServletRequest request){
        Token token = getTokenFromContext();
        return stMenusService.getStudentCourse(token.getId());
    }

    /**
     * 把token返回
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/switchstucourse")
    @AuthRequired(type = "admin",menu = "531500340-a9456c2b-c3f7-41b3-b2c4-1fd8e622dcc6")
    public Result switchStuCourse(@RequestParam(name = "id") String id, HttpServletRequest request){
        Token token = getTokenFromContext();
        token.setObsid(id);
        return stMenusService.getStudentCourseInfor(token);
    }
    @PostMapping("/studentmenu")
    @AuthRequired(type = "admin",menu = "531500340-a9456c2b-c3f7-41b3-b2c4-1fd8e622dcc6")
    public Result stuCourse(HttpServletRequest request){
        Token token = getTokenFromContext();
        return stMenusService.getMenusList(token.getRoleid());
    }

    @PostMapping("/switchrole")
    @AuthRequired(type = "admin",menu = "531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65")
    public Result switchRole(HttpServletRequest request){
        Token token = getTokenFromContext();
        return stRolesService.switchRole(token.getId());
    }

    @GetMapping("/teacherChangePwd")
    @AuthRequired(type = "admin",menu = "531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65")
    public Result teacherChangePwd(@RequestParam String currentPwd, @RequestParam String newPwd, HttpServletRequest request) {
        Token token = getTokenFromContext();
        return stUsersService.teacherChangePwd(currentPwd, newPwd, token.getId());
    }

    @GetMapping("/studentChangePwd")
    @AuthRequired(type = "admin",menu = "531500340-a9456c2b-c3f7-41b3-b2c4-1fd8e622dcc6")
    public Result studentChangePwd(@RequestParam String currentPwd, @RequestParam String newPwd, HttpServletRequest request) {
        Token token = getTokenFromContext();
        return stUsersService.studentChangePwd(currentPwd, newPwd, token.getId());
    }
}
