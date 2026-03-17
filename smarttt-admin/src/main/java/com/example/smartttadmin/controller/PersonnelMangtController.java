package com.example.smartttadmin.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.smartttadmin.Utils.AuthRequired;
import com.example.smartttadmin.dto.PersonnelRoster;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.Token;
import com.example.smartttadmin.enums.CateLogEnum;
import com.example.smartttadmin.listeners.PersonnelListenerExcel;
import com.example.smartttadmin.mapper.SmObsMapper;
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
    @Autowired
    private SmObsMapper smObsMapper;

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
        return smObsService.createOnePersonnelRoster(personnelRoster,token.getTermid());
    }

    @PostMapping("/delete")
    public Result deletePersonnelRosteByIDs(@RequestBody List<String> ids) {
        return stUsersService.deleteUsersByIDs(ids);
    }
    @PostMapping("/update")
    @AuthRequired(type = "admin",menu = "531500340-7f750ec8-76b9-42c2-a1ca-e41dcb57c998")
    public Result UpdatePersonnalRoster(@RequestBody PersonnelRoster personnelRoster,HttpServletRequest request) throws JsonProcessingException {
        Token token = getTokenFromContext();
        
        // 如果需要更新 obsname，则进行层级校验
        if (personnelRoster.getObsname() != null && !personnelRoster.getObsname().isEmpty()) {
            // 获取学生和教师的层级配置
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
            
            // 根据人员分类校验层级
            CateLogEnum cateLogEnum = CateLogEnum.getCateLogEnumByStatus(Integer.parseInt(personnelRoster.getCatelog()));
            List<SmObs> smObsList;
            
            if (Objects.equals(CateLogEnum.STUDENT, cateLogEnum)) {
                // 学生必须挂在班级层级 (obsdeep=5)
                smObsList = smObsMapper.getObsByObsNameAndDeep(personnelRoster.getObsname(), studentObsDeep, token.getTermid());
                
                // 额外校验：学生的班级必须属于某个专业（即班级的父节点必须是专业，obsdeep=4）
                if (!smObsList.isEmpty()) {
                    SmObs classObs = smObsList.get(0);
                    // 检查班级的父节点是否为专业 (obsdeep=4)
                    if (classObs.getPid() != null) {
                        SmObs parentObs = smObsMapper.getSmObsByID(classObs.getPid());
                        if (parentObs == null || parentObs.getObsdeep() != 4) {
                            return Result.error("学生只能分配到专业下面的班级！");
                        }
                        
                        // 校验是否是同一个学院的学生
                        // 1. 获取学生原来的 obsid
                        SmObs oldClassObs = smObsMapper.getObsByStuID(personnelRoster.getId());
                        if (oldClassObs != null && oldClassObs.getId() != null) {
                            // 2. 获取原班级的父节点（专业）
                            SmObs oldParentObs = smObsMapper.getSmObsByID(oldClassObs.getPid());
                            if (oldParentObs != null) {
                                // 3. 获取原专业的父节点（学院）
                                SmObs oldCollegeObs = smObsMapper.getSmObsByID(oldParentObs.getPid());
                                
                                // 4. 获取新班级的父节点（专业）的父节点（学院）
                                SmObs newCollegeObs = null;
                                if (parentObs.getPid() != null) {
                                    newCollegeObs = smObsMapper.getSmObsByID(parentObs.getPid());
                                }
                                
                                // 5. 比较新旧学院是否一致
                                if (oldCollegeObs != null && newCollegeObs != null) {
                                    if (!Objects.equals(oldCollegeObs.getId(), newCollegeObs.getId())) {
                                        return Result.error("学生不能跨学院调整班级！当前学院：" + 
                                                oldCollegeObs.getObsname() + "，目标学院：" + newCollegeObs.getObsname());
                                    }
                                }
                            }
                        }
                    } else {
                        return Result.error("班级所属的专业不存在！");
                    }
                }
            } else {
                // 教师层级校验
                smObsList = smObsMapper.getObsByObsNameAndDeep(personnelRoster.getObsname(), teacherObsDeep, token.getTermid());
            }
            
            if (smObsList.isEmpty()) {
                return Result.error("所属院系/班级不存在或者层级不匹配！");
            }
            
            // 校验通过，设置 obsid
            personnelRoster.setObsid(smObsList.get(0).getId());
        }
        
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
