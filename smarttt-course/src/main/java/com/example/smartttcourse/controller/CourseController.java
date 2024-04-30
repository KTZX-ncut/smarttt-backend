package com.example. smartttcourse.controller;

import com.example.smartttcourse.Utils.AuthRequired;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.pojo.CmCourse;
import com.example.smartttcourse.pojo.StRoleUser;
import com.example.smartttcourse.service.CmTermService;
import com.example.smartttcourse.service.SmObsService;
import com.example.smartttcourse.service.StUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.smartttcourse.dto.Result;
import com.example.smartttcourse.service.CmCourseService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.example.smartttcourse.Utils.AuthorizationAspect.getTokenFromContext;


@RestController
@RequestMapping("/coursemangt/course")
public class CourseController {
    @Autowired
    private CmCourseService cmCourseService;
    @Autowired
    private CmTermService cmTermService;
    @Autowired
    private SmObsService smObsService;
    @Autowired
    private StUsersService stUsersService;

    @GetMapping("/test")
    public Result test(){
        return Result.success();
    }
    /**
     *专业负责人课程管理信息
     * @return Result
     */
    @GetMapping
    @AuthRequired(type = "admin",menu = "531500340-0ee32ded-100b-4505-95c4-65d5e9b3d93c",isReadOnly = true)
    public Result getCourse(HttpServletRequest request) {
        Token token = getTokenFromContext();
        return cmCourseService.getCourse(token);
    }

    /**
     * 获取全部学期
     * @return
     */

    @GetMapping("/allterm")
    public Result getAllTerm(){
        return cmTermService.getHistoryTerm();
    }
    @AuthRequired(type = "admin",menu = "531500340-0ee32ded-100b-4505-95c4-65d5e9b3d93c")
    @PostMapping("/create")
    public Result createCourse(@RequestBody CmCourse cmCourse,HttpServletRequest request) {
        Token token = getTokenFromContext();
        cmCourse.setProfessionId(token.getObsid());
        return cmCourseService.createCourse(cmCourse);
    }

    @PostMapping("/delete")
    public Result deleteCourseByID(@RequestBody List<String> ids) {
        return cmCourseService.deleteCourseByID(ids);
    }

    @AuthRequired(type = "admin",menu = "531500340-0ee32ded-100b-4505-95c4-65d5e9b3d93c")
    @GetMapping("/history")
    public Result historyCourseByTerm(@RequestParam(name = "id")String termID,HttpServletRequest request) {
        Token token = getTokenFromContext();
        return cmCourseService.historyCourseByTerm(termID,token.getObsid());
    }
    @GetMapping("/copy")
    public Result copyHistoryCourse(@RequestParam(name = "id")String id){
        return cmCourseService.copyHistoryCourse(id);
    }
    @PostMapping("/courseRP")
    public Result courseRPList(){
        String obsID = smObsService.getSchoolObs();
        return smObsService.getObsRPList(obsID);
    }
    @PostMapping ("/courseRP/delete")
    public Result deleteCourseRP(@RequestBody StRoleUser stRoleUser){
        stRoleUser.setRoleid("516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21");
        return stUsersService.deleteRP(stRoleUser);
    }
    @PostMapping("/courseRP/create")
    public Result createCourseRP(@RequestBody List<StRoleUser> stRoleUserList){
        for(StRoleUser stRoleUser:stRoleUserList){
            stRoleUser.setRoleid("516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21");
            Result result = stUsersService.createOneRP(stRoleUser);
            if(result.getCode()!=200)return Result.error("新增错误");
        }
        return Result.success();
    }

}
