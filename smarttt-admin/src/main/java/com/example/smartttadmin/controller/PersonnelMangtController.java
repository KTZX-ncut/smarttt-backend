package com.example.smartttadmin.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.smartttadmin.Utils.AuthRequired;
import com.example.smartttadmin.dto.PersonnelRoster;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.Token;
import com.example.smartttadmin.enums.CateLogEnum;
import com.example.smartttadmin.listeners.PersonnelListenerExcel;
import com.example.smartttadmin.pojo.PersonnelExcel;
import com.example.smartttadmin.pojo.SmObs;
import com.example.smartttadmin.pojo.StLevel;
import com.example.smartttadmin.service.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.smartttadmin.Utils.AuthorizationAspect.getTokenFromContext;

/**
 * 人员管理
 */
@RestController
@RequestMapping("/sysmangt/personnelmangt")
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
    @AuthRequired(type = "admin",menu = "531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998",isReadOnly = true)
    public Result getSmObsTree(HttpServletRequest request){
        Token token = getTokenFromContext();
        return smObsService.getObsTree(token.getTermid());
    }

    /**
     * 人员列表（学生/教师）
     * @param obsid
     * @param catelog
     * @return
     */
    @GetMapping("/person")
    @AuthRequired(type = "admin",menu = "531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998",isReadOnly = true)
    public Result getPersonnelRoster(@RequestParam(name = "obsid")String obsid, @RequestParam(name = "catelog")String catelog,HttpServletRequest request){
        Token token  = getTokenFromContext();
        return smObsService.getPersonnelRosterByObsIDAndCatelog(obsid,catelog,token.getTermid());
    }

    @GetMapping("/student")
    @AuthRequired(type = "admin",menu = "531500340-e7149e74-4856-4440-8d94-99f915731842",isReadOnly = true)
    public Result getPersonnelRoster(@RequestParam(name = "obsid")String obsid,HttpServletRequest request){
        Token token  = getTokenFromContext();
        return smObsService.getPersonnelRosterByObsIDAndCatelog(obsid,"1",token.getTermid());
    }


    /**
     * 新建教师/学生
     * @param personnelRoster
     * @return
     */
    @PostMapping("/create")
    @AuthRequired(type = "admin",menu = "531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998")
    public Result createPersonnelRoster(@RequestBody PersonnelRoster personnelRoster,HttpServletRequest request) throws JsonProcessingException {
        Token token = getTokenFromContext();
        
        // 先校验数据的合法性
        Result validateResult = stUsersService.validatePersonnelRoster(personnelRoster, token.getTermid());
        if (!validateResult.getCode().equals(200)) {
            return validateResult;
        }
        
        // 校验通过后，设置 obsid 并创建
        // 根据 obsname 获取 obsid
        QueryWrapper<SmObs> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("obsname", personnelRoster.getObsname())
                    .eq("termid", token.getTermid());
        
        CateLogEnum cateLogEnum = CateLogEnum.getCateLogEnumByStatus(Integer.parseInt(personnelRoster.getCatelog()));
        List<StLevel> levelList = levelService.list().stream()
                .filter(t -> Objects.equals(t.getTeacher(), "1") || Objects.equals(t.getStudent(), "1"))
                .collect(Collectors.toList());
        
        Long teacherObsDeep = null;
        Long studentObsDeep = null;
        for (StLevel stLevel : levelList) {
            if (Objects.equals(stLevel.getStudent(), "1")) {
                studentObsDeep = stLevel.getObsdeep();
            }
            if (Objects.equals(stLevel.getTeacher(), "1")) {
                teacherObsDeep = stLevel.getObsdeep();
            }
        }
        
        if (Objects.equals(CateLogEnum.STUDENT, cateLogEnum)) {
            queryWrapper.eq("obsdeep", studentObsDeep);
        } else if (Objects.equals(CateLogEnum.TEACHER, cateLogEnum)) {
            queryWrapper.eq("obsdeep", teacherObsDeep);
        }
        
        List<SmObs> smObsList = smObsService.list(queryWrapper);
        if (!smObsList.isEmpty()) {
            personnelRoster.setObsid(smObsList.get(0).getId());
        }
        
        return smObsService.createOnePersonnelRoster(personnelRoster, token.getTermid());
    }

    @PostMapping("/delete")
    public Result deletePersonnelRosteByIDs(@RequestBody List<String> ids) {
        return stUsersService.deleteUsersByIDs(ids);
    }
    @PostMapping("/update")
    @AuthRequired(type = "admin",menu = "531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998")
    public Result UpdatePersonnalRoster(@RequestBody PersonnelRoster personnelRoster,HttpServletRequest request) throws JsonProcessingException {
        Token token = getTokenFromContext();
        return stUsersService.updateOnePersonnelRoster(personnelRoster,token.getTermid());
    }
    /**
     * excel表格导入教师或者学生
     * @param file
     * @return
     */
    @PostMapping("/import")
    public Result importTeacherAndStudent(@RequestParam("file") MultipartFile file){
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
    public Result searchPerson(@RequestParam("inform")String inform,@RequestParam("catelog")String catelog){
        return stUsersService.searchPerson(inform,catelog);
    }

}
