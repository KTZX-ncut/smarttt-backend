package com.example.smartttadmin.controller;

import com.alibaba.excel.EasyExcel;
import com.example.smartttadmin.Utils.AuthRequired;
import com.example.smartttadmin.dto.PersonnelRoster;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.Token;
import com.example.smartttadmin.listeners.PersonnelListenerExcel;
import com.example.smartttadmin.pojo.PersonnelExcel;
import com.example.smartttadmin.service.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

import static com.example.smartttadmin.Utils.AuthorizationAspect.getTokenFromContext;

/**
 * 人员管理
 */
@RestController
@RequestMapping("/sysmangt/personnelmangt")
@Api(tags = "9. 人员管理", description = "教师、学生名单查询、维护、导入和搜索接口")
public class PersonnelMangtController {
    @Autowired
    private SmObsService smObsService;
    @Autowired
    private StUsersService stUsersService;

    @Resource
    private StudentService studentService;
    @Resource
    private TeacherService teacherService;
    @Resource
    private StLevelService levelService;
    @GetMapping
    @ApiOperation(value = "获取组织树", notes = "查询人员管理模块使用的组织结构树。")
    @AuthRequired(type = "admin",menu = "531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998",isReadOnly = true)
    public Result getSmObsTree(HttpServletRequest request){
        Token token = getTokenFromContext();
        return smObsService.getObsTree();
    }

    /**
     * 人员列表（学生/教师）
     * @param obsid
     * @param catelog
     * @return
     */
    @GetMapping("/person")
    @ApiOperation(value = "查询教师/学生名单", notes = "按组织节点和人员类别查询人员列表；catelog=1 通常表示学生，其他值通常表示教师。")
    @AuthRequired(type = "admin",menu = "531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998",isReadOnly = true)
    public Result getPersonnelRoster(@ApiParam(value = "组织节点 ID", required = true, example = "531500340-obs") @RequestParam(name = "obsid")String obsid, @ApiParam(value = "人员类别", required = true, example = "0") @RequestParam(name = "catelog")String catelog,HttpServletRequest request){
        Token token  = getTokenFromContext();
        return smObsService.getPersonnelRosterByObsIDAndCatelog(obsid,catelog);
    }

    @GetMapping("/student")
    @ApiOperation(value = "查询学生名单", notes = "按组织节点 ID 直接查询学生名单。")
    @AuthRequired(type = "admin",menu = "531500340-e7149e74-4856-4440-8d94-99f915731842",isReadOnly = true)
    public Result getPersonnelRoster(@ApiParam(value = "组织节点 ID", required = true, example = "531500340-obs") @RequestParam(name = "obsid")String obsid,HttpServletRequest request){
        Token token  = getTokenFromContext();
        return smObsService.getPersonnelRosterByObsIDAndCatelog(obsid,"1");
    }


    /**
     * 新建教师/学生
     * @param personnelRoster
     * @return
     * 暂定不用修改，因为批量导入文件是后端来处理
     */
    @PostMapping("/create")
    @ApiOperation(value = "新建人员", notes = "新增教师或学生档案。人员类别由请求体中的 catelog 区分。")
    @AuthRequired(type = "admin",menu = "531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998")
    public Result createPersonnelRoster(@ApiParam(value = "人员信息", required = true) @RequestBody PersonnelRoster personnelRoster,HttpServletRequest request) throws JsonProcessingException {
        Token token = getTokenFromContext();
        return smObsService.createOnePersonnelRoster(personnelRoster);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除人员", notes = "按用户 ID 列表批量删除教师或学生。")
    public Result deletePersonnelRosteByIDs(@ApiParam(value = "用户 ID 列表", required = true) @RequestBody List<String> ids) {
        return stUsersService.deleteUsersByIDs(ids);
    }
    @PostMapping("/update")
    @ApiOperation(value = "更新人员信息", notes = "修改教师或学生基础信息。")
    @AuthRequired(type = "admin",menu = "531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998")
    public Result UpdatePersonnalRoster(@ApiParam(value = "人员信息", required = true) @RequestBody PersonnelRoster personnelRoster,HttpServletRequest request) throws JsonProcessingException {
        Token token = getTokenFromContext();
        return stUsersService.updateOnePersonnelRoster(personnelRoster);
    }
    /**
     * excel表格导入教师或者学生
     * @param file
     * @return
     */
    @PostMapping(value = "/import", consumes = "multipart/form-data")
    @ApiOperation(value = "导入教师/学生 Excel", notes = "上传 Excel 文件，按模板批量导入教师或学生。")
    public Result importTeacherAndStudent(@ApiParam(value = "Excel 文件", required = true) @RequestParam("file") MultipartFile file){
        PersonnelListenerExcel personnelListenerExcel = new PersonnelListenerExcel(
                smObsService,
                stUsersService,
                studentService,
                teacherService,
                levelService
        );
        try {
            EasyExcel.read(file.getInputStream(), PersonnelExcel.class, personnelListenerExcel)
                    .sheet()
                    .doRead();
            return Result.success();
        } catch (IOException e) {
            return Result.error(-710,"导入文件失败！");
        }
    }
    @GetMapping("/search")
    @ApiOperation(value = "搜索人员", notes = "按关键字和人员类别搜索教师或学生。")
    public Result searchPerson(@ApiParam(value = "搜索关键字，可传姓名、工号或学号等", required = true, example = "张三") @RequestParam("inform")String inform,@ApiParam(value = "人员类别", required = true, example = "0") @RequestParam("catelog")String catelog){
        return stUsersService.searchPerson(inform,catelog);
    }

}
