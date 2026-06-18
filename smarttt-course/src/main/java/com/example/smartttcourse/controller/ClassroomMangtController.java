package com.example.smartttcourse.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.example.smartttcourse.Utils.AuthRequired;
import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.pojo.CmClassroom;
import com.example.smartttcourse.service.CmClassRoomService;
import com.example.smartttcourse.service.SmObsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.example.smartttcourse.Utils.AuthorizationAspect.getTokenFromContext;

@RestController
@RequestMapping("/coursemangt/classroom")
@Api(tags = "5. 课堂管理", description = "课堂列表、课堂新增编辑和课堂负责人查询接口")
public class ClassroomMangtController {
    @Autowired
    private CmClassRoomService cmClassRoomService;
    @Autowired
    private SmObsService smObsService;

    @GetMapping
    @ApiOperation(value = "获取课堂列表", notes = "根据当前 token 返回当前课程或专业下的课堂列表。")
    @AuthRequired(type = "admin",menu = "531500340-074abac7-fe4c-4908-aa7e-d72dacd94014",isReadOnly = true)
    public Result getClassRoomList(HttpServletRequest request){
        Token token = getTokenFromContext();
//        System.out.println(token.getObsid());
        return cmClassRoomService.getClassRoomList(token);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除课堂", notes = "按课堂 ID 列表批量删除课堂。")
    public Result deleteClassroom(@ApiParam(value = "课堂 ID 列表", required = true) @RequestBody List<String> ids){
        if(Objects.isNull(ids) || CollectionUtil.isEmpty(ids)){
            return Result.error("课堂id列表不能为空");
        }
        return cmClassRoomService.deleteClassroom(ids);
    }
    /*
    token获取创建者信息
     */
    @PostMapping("/create")
    @ApiOperation(value = "新建课堂", notes = "创建新课堂，并自动写入当前登录人的创建者信息和当前学期。")
    @AuthRequired(type = "admin",menu = "531500340-074abac7-fe4c-4908-aa7e-d72dacd94014")
    public Result createOneClassroom(@ApiParam(value = "课堂信息", required = true) @RequestBody CmClassroom classroom,HttpServletRequest request){
        Token token = getTokenFromContext();
        classroom.setCreator(token.getId());
        classroom.setTermId(token.getTermid());
        return cmClassRoomService.createOneClassroom(classroom);
    }
    @PostMapping("/update")
    @ApiOperation(value = "更新课堂信息", notes = "修改课堂名称、授课教师、课时安排、备注等信息。")
    public Result updateClassroom(@ApiParam(value = "课堂信息", required = true) @RequestBody CmClassroom classroom){
        return cmClassRoomService.updateOneClassroom(classroom);
    }

    @GetMapping("/teacher")
    @ApiOperation(value = "获取教师负责人候选列表", notes = "查询课堂可绑定的教师负责人列表。")
    public Result getTeacherList(HttpServletRequest request){
        String obsID = smObsService.getSchoolObs();
        return smObsService.getObsRPList(obsID);
    }
    @PostMapping("/classroomRP")
    @ApiOperation(value = "获取课堂负责人候选列表", notes = "查询课堂负责人可绑定的候选人员列表。")
    public Result classroomRPList(){
        String obsID = smObsService.getSchoolObs();
        return smObsService.getObsRPList(obsID);
    }
}
