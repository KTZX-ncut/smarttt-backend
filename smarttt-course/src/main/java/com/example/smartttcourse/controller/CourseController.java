package com.example. smartttcourse.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.example.smartttcourse.Utils.AuthRequired;
import com.example.smartttcourse.Utils.CommonFunctions;
import com.example.smartttcourse.Utils.JwtTokenUtils;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.exception.res.ResponseEnum;
import com.example.smartttcourse.exception.utils.SmartAssert;
import com.example.smartttcourse.pojo.CmCourse;
import com.example.smartttcourse.pojo.StRoleUser;
import com.example.smartttcourse.service.CmTermService;
import com.example.smartttcourse.service.SmObsService;
import com.example.smartttcourse.service.StUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "4. 课程管理", description = "课程列表、历史课程、负责人维护和课程内容复制接口")
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
    @ApiOperation(value = "课程模块连通性测试", notes = "用于开发阶段验证课程模块接口是否可访问。")
    public Result test(){
        return Result.success();
    }
    /**
     *专业负责人课程管理信息
     * @return Result
     */
    @GetMapping
    @ApiOperation(value = "获取课程列表", notes = "根据当前 token 查询当前专业负责人可管理的课程列表。")
    @AuthRequired(type = "admin",menu = "531500340-0ee32ded-100b-4505-95c4-65d5e9b3d93c",isReadOnly = true)
    public Result getCourse(HttpServletRequest request) {
        Token token = getTokenFromContext();
        return cmCourseService.getCourse(token);
    }

    /**
     * 获取全部学期
     * @return
     */

    // @AuthRequired(type = "admin",menu = "531500340-0ee32ded-100b-4505-95c4-65d5e9b3d93c")
    @GetMapping("/allterm")
    @ApiOperation(value = "获取历史学期列表", notes = "根据当前 token 中的学期信息查询历史学期列表。")
    public Result getAllTerm(HttpServletRequest request){
        String strToken = request.getHeader("token");
        Token token = JwtTokenUtils.parseToken(strToken, CommonFunctions.TokenSK);
        return cmTermService.getHistoryTerm(token.getTermid());
    }

    @GetMapping("/currenttermId")
    @ApiOperation(value = "获取当前学期 ID", notes = "返回系统当前启用的学期 ID。")
    public Result getCurrentTerm() {
        return cmTermService.getCurrentTerm();
    }

    @AuthRequired(type = "admin",menu = "531500340-0ee32ded-100b-4505-95c4-65d5e9b3d93c")
    @PostMapping("/create")
    @ApiOperation(value = "新建课程", notes = "创建课程，并自动将当前 token 的组织节点写入 professionId。")
    public Result createCourse(@ApiParam(value = "课程信息", required = true) @RequestBody CmCourse cmCourse,HttpServletRequest request) {
        Token token = getTokenFromContext();
        SmartAssert.checkExpression(StrUtil.isNotBlank(cmCourse.getSchooltermId()),ResponseEnum.TERM_ID_IS_NOT_BLANK);
        cmCourse.setProfessionId(token.getObsid());
        return cmCourseService.createCourse(cmCourse);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除课程", notes = "按课程 ID 列表批量删除课程。")
    public Result deleteCourseByID(@ApiParam(value = "课程 ID 列表", required = true) @RequestBody List<String> ids) {
        if( Objects.isNull(ids) || CollectionUtil.isEmpty(ids)){
            return Result.error("课程id列表不能为空");
        }
        return cmCourseService.deleteCourseByID(ids);
    }

    @AuthRequired(type = "admin",menu = "531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35")
    @GetMapping("/history")
    @ApiOperation(value = "按学期查询历史课程", notes = "根据学期 ID 查询当前专业下的历史课程。")
    public Result historyCourseByTerm(@ApiParam(value = "学期 ID", required = true, example = "term-2025-1") @RequestParam(name = "id")String termID,HttpServletRequest request) {
        Token token = getTokenFromContext();
        return cmCourseService.historyCourseByTerm(termID,token.getObsid());
    }
    @PostMapping("/update")
    @ApiOperation(value = "更新课程信息", notes = "修改课程名称、课程号、学分、课时、简介等信息。")
    public Result updateCourse(@ApiParam(value = "课程信息", required = true) @RequestBody CmCourse cmCourse){
        return cmCourseService.updateOneCourse(cmCourse);
    }
    @GetMapping("/copy")
    @ApiOperation(value = "复制历史课程记录", notes = "按课程 ID 复制历史课程到当前上下文。")
    public Result copyHistoryCourse(@ApiParam(value = "课程 ID", required = true, example = "course-001") @RequestParam(name = "id")String id){
        return cmCourseService.copyHistoryCourse(id);
    }
    @PostMapping("/courseRP")
    @ApiOperation(value = "获取课程负责人候选列表", notes = "查询课程负责人可绑定的候选人员列表。")
    public Result courseRPList(){
        String obsID = smObsService.getSchoolObs();
        return smObsService.getObsRPList(obsID);
    }
    @PostMapping ("/courseRP/delete")
    @ApiOperation(value = "删除课程负责人", notes = "移除课程负责人的角色绑定关系。")
    public Result deleteCourseRP(@ApiParam(value = "课程负责人绑定信息", required = true) @RequestBody StRoleUser stRoleUser){
        stRoleUser.setRoleid("516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21");
        return stUsersService.deleteRP(stRoleUser);
    }
    @PostMapping("/courseRP/create")
    @ApiOperation(value = "新增课程负责人", notes = "为课程批量绑定负责人角色。")
    public Result createCourseRP(@ApiParam(value = "课程负责人绑定信息列表", required = true) @RequestBody List<StRoleUser> stRoleUserList){
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
    @GetMapping("/getPreCourseByCode")
    @AuthRequired(type = "admin",menu = "531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35")
    @ApiOperation(value = "根据课程号获取历史课程", notes = "根据当前课程上下文和指定学期 ID 查询历史课程。")
    public Result getPreCourseByCode(@ApiParam(value = "历史学期 ID", required = true, example = "term-2025-1") @RequestParam("termId") String termId, HttpServletRequest request) {
        Token token = getTokenFromContext();
        return cmCourseService.getPreCourseByCode(termId, token.getObsid());
    }

    /**
     * 获取当前专业下的所有课程（不按学期过滤）
     */
    @GetMapping("/getAllCourses")
    @AuthRequired(type = "admin", menu = "531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35")
    @ApiOperation(value = "获取所有课程", notes = "获取当前专业下的所有课程，不按学期过滤，用于复制关键字和能力功能。")
    public Result getAllCourses(HttpServletRequest request) {
        Token token = getTokenFromContext();
        return cmCourseService.getAllCourses(token);
    }

    // 复制历史课程的具体内容
    @PostMapping("/copyCourseInfo")
    @AuthRequired(type = "admin",menu = "531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35")
    @ApiOperation(value = "复制历史课程具体内容", notes = "将历史课程中的具体内容复制到当前课程上下文。")
    public Result copyInfo(@ApiParam(value = "历史课程 ID", required = true, example = "course-history-001") @RequestParam("pastId") String pastId, HttpServletRequest request) {
        Token token = getTokenFromContext();
        return cmCourseService.copyInfo(pastId, token.getObsid());
    }

    @PostMapping("/copyFormative")
    @AuthRequired(type = "admin",menu = "531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35")
    @ApiOperation(value = "复制形成性评价建模", notes = "将历史课程的形成性评价建模（关键字、能力、基本教学目标、知识单元）复制到当前课程。")
    public Result copyFormative(@ApiParam(value = "历史课程 ID", required = true) @RequestParam("pastId") String pastId, HttpServletRequest request) {
        Token token = getTokenFromContext();
        return cmCourseService.copyFormative(pastId, token.getObsid());
    }
}
