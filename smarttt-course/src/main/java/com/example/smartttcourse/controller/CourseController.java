package com.example. smartttcourse.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.example.smartttcourse.Utils.AuthRequired;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.exception.res.ResponseEnum;
import com.example.smartttcourse.exception.utils.SmartAssert;
import com.example.smartttcourse.pojo.CmCourse;
import com.example.smartttcourse.pojo.StRoleUser;
import com.example.smartttcourse.service.CmTermService;
import com.example.smartttcourse.service.SmObsService;
import com.example.smartttcourse.service.StUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.service.CmCourseService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Objects;

import static com.example.smartttcourse.Utils.AuthorizationAspect.getTokenFromContext;
/**
课程管理
 */

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

    @AuthRequired(type = "admin",menu = "531500340-0ee32ded-100b-4505-95c4-65d5e9b3d93c")
    @GetMapping("/allterm")
    public Result getAllTerm(HttpServletRequest request){
        Token token = getTokenFromContext();
        return cmTermService.getHistoryTerm(token.getTermid());
    }

    @GetMapping("/currenttermId")
    public Result getCurrentTerm() {
        return cmTermService.getCurrentTerm();
    }

    @AuthRequired(type = "admin",menu = "531500340-0ee32ded-100b-4505-95c4-65d5e9b3d93c")
    @PostMapping("/create")
    public Result createCourse(@RequestBody CmCourse cmCourse,HttpServletRequest request) {
        Token token = getTokenFromContext();
        SmartAssert.checkExpression(StrUtil.isNotBlank(cmCourse.getSchooltermId()),ResponseEnum.TERM_ID_IS_NOT_BLANK);
        cmCourse.setProfessionId(token.getObsid());
        return cmCourseService.createCourse(cmCourse);
    }

    @PostMapping("/delete")
    public Result deleteCourseByID(@RequestBody List<String> ids) {
        if( Objects.isNull(ids) || CollectionUtil.isEmpty(ids)){
            return Result.error("课程id列表不能为空");
        }
        return cmCourseService.deleteCourseByID(ids);
    }

    @AuthRequired(type = "admin",menu = "531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35")
    @GetMapping("/history")
    public Result historyCourseByTerm(@RequestParam(name = "id")String termID,HttpServletRequest request) {
        Token token = getTokenFromContext();
        return cmCourseService.historyCourseByTerm(termID,token.getObsid());
    }
    @PostMapping("/update")
    public Result updateCourse(@RequestBody CmCourse cmCourse){
        return cmCourseService.updateOneCourse(cmCourse);
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
            Result currentTerm = cmTermService.getCurrentTerm();
            stRoleUser.setTermid(currentTerm.getData().toString());
            Result result = stUsersService.createOneRP(stRoleUser);
            if(result.getCode()!=200)return Result.error("新增错误");
        }
        return Result.success();
    }

    // 根据课程号获取历史课程
    @GetMapping("getPreCourseByCode")
    @AuthRequired(type = "admin",menu = "531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35")
    public Result getPreCourseByCode(@RequestParam("termId") String termId, HttpServletRequest request) {
        Token token = getTokenFromContext();
        return cmCourseService.getPreCourseByCode(termId, token.getObsid());
    }

    // 复制历史课程的具体内容
    @PostMapping("/copyCourseInfo")
    @AuthRequired(type = "admin",menu = "531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35")
    public Result copyInfo(@RequestParam("pastId") String pastId, HttpServletRequest request) {
        Token token = getTokenFromContext();
        return cmCourseService.copyInfo(pastId, token.getObsid());
    }
}
