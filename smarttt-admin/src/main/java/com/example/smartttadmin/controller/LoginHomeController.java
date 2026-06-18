package com.example.smartttadmin.controller;

import com.example.smartttadmin.Utils.AuthRequired;
import com.example.smartttadmin.dto.*;
import com.example.smartttadmin.pojo.StRoleUser;
import com.example.smartttadmin.service.StMenusService;
import com.example.smartttadmin.service.StRolesService;
import com.example.smartttadmin.service.StUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "2. 首页工作台", description = "教师/学生首页、菜单、角色切换及密码修改相关接口")
public class LoginHomeController {
   @Autowired
    private StMenusService stMenusService;
   @Autowired
   private StRolesService stRolesService;
   @Autowired
   private StUsersService stUsersService;

    @PostMapping("/token")
    @ApiOperation(value = "获取当前 token 解析结果", notes = "从请求头 token 中解析当前登录用户的身份、角色、组织和学期信息。")
    @AuthRequired(type = "admin",menu = "531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65")
    public Result getToken(HttpServletRequest request){
        Token token = getTokenFromContext();
        return Result.success(token);
    }
    /**
     * 教师类型用户登录到首页
     * @return Result
     */
    @PostMapping("/teacherhome")
    @ApiOperation(value = "获取教师首页菜单", notes = "根据当前 token 中的角色 ID 返回教师/管理员首页菜单。")
    @AuthRequired(type = "admin",menu = "531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65")
    public Result TeacherHome(HttpServletRequest request){
//        return stMenusService.getMenusList(loginHomeReq);
        Token token = getTokenFromContext();
        return stMenusService.getMenusList(token.getRoleid());
    }
    @PostMapping("/studenthome")
    @ApiOperation(value = "获取学生首页课程列表", notes = "根据当前登录学生 ID 返回可选课程信息。")
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
    @ApiOperation(value = "切换学生当前课程", notes = "将当前 token 的 obsid 切换为指定课程/组织节点，再返回课程上下文信息。")
    @AuthRequired(type = "admin",menu = "531500340-a9456c2b-c3f7-41b3-b2c4-1fd8e622dcc6")
    public Result switchStuCourse(@ApiParam(value = "课程或组织节点 ID", required = true, example = "531500340-course") @RequestParam(name = "id") String id, HttpServletRequest request){
        Token token = getTokenFromContext();
        token.setObsid(id);
        return stMenusService.getStudentCourseInfor(token);
    }
    @PostMapping("/studentmenu")
    @ApiOperation(value = "获取学生菜单", notes = "根据当前学生角色返回学生端菜单。")
    @AuthRequired(type = "admin",menu = "531500340-a9456c2b-c3f7-41b3-b2c4-1fd8e622dcc6")
    public Result stuCourse(HttpServletRequest request){
        Token token = getTokenFromContext();
        return stMenusService.getMenusList(token.getRoleid());
    }

    @PostMapping("/switchrole")
    @ApiOperation(value = "获取可切换角色列表", notes = "按当前用户 ID 查询该用户可切换的角色列表。")
    @AuthRequired(type = "admin",menu = "531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65")
    public Result switchRole(HttpServletRequest request){
        Token token = getTokenFromContext();
        return stRolesService.switchRole(token.getId());
    }

    @PostMapping("/history")
    @ApiOperation(value = "获取历史角色列表", notes = "返回当前用户曾经拥有过的角色历史记录。")
    @AuthRequired(type = "admin",menu = "531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65")
    public Result getHistoryRoles(HttpServletRequest request){
        Token token = getTokenFromContext();
        return stRolesService.getHistoryRole(token.getId());
    }

    @GetMapping("/teacherChangePwd")
    @ApiOperation(value = "教师修改密码", notes = "教师或管理员修改当前账号密码。")
    @AuthRequired(type = "admin",menu = "531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65")
    public Result teacherChangePwd(@ApiParam(value = "当前密码", required = true, example = "123456") @RequestParam String currentPwd, @ApiParam(value = "新密码", required = true, example = "12345678") @RequestParam String newPwd, HttpServletRequest request) {
        Token token = getTokenFromContext();
        return stUsersService.teacherChangePwd(currentPwd, newPwd, token.getId());
    }

    @GetMapping("/studentChangePwd")
    @ApiOperation(value = "学生修改密码", notes = "学生修改当前账号密码。")
    @AuthRequired(type = "admin",menu = "531500340-a9456c2b-c3f7-41b3-b2c4-1fd8e622dcc6")
    public Result studentChangePwd(@ApiParam(value = "当前密码", required = true, example = "123456") @RequestParam String currentPwd, @ApiParam(value = "新密码", required = true, example = "12345678") @RequestParam String newPwd, HttpServletRequest request) {
        Token token = getTokenFromContext();
        return stUsersService.studentChangePwd(currentPwd, newPwd, token.getId());
    }
}
