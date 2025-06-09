package com.example.smartttadmin.controller;

import com.alibaba.excel.EasyExcel;
import com.example.smartttadmin.dto.PersonnelRoster;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.listeners.PersonnelListenerExcel;
import com.example.smartttadmin.pojo.PersonnelExcel;
import com.example.smartttadmin.service.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

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
    public Result getSmObsTree(){
        return smObsService.getObsTree();
    }

    /**
     * 人员列表（学生/教师）
     * @param obsid
     * @param catelog
     * @return
     */
    @GetMapping("/person")
    public Result getPersonnelRoster(@RequestParam(name = "obsid")String obsid, @RequestParam(name = "catelog")String catelog){
        return smObsService.getPersonnelRosterByObsIDAndCatelog(obsid,catelog);
    }

    /**
     * 新建教师/学生
     * @param personnelRoster
     * @return
     * 暂定不用修改，因为批量导入文件是后端来处理
     */
    @PostMapping("/create")
    public Result createPersonnelRoster(@RequestBody PersonnelRoster personnelRoster) throws JsonProcessingException {
        return smObsService.createOnePersonnelRoster(personnelRoster);
    }

    @PostMapping("/delete")
    public Result deletePersonnelRosteByIDs(@RequestBody List<String> ids) {
        return stUsersService.deleteUsersByIDs(ids);
    }
    @PostMapping("/update")
    public Result UpdatePersonnalRoster(@RequestBody PersonnelRoster personnelRoster) throws JsonProcessingException {
        return stUsersService.updateOnePersonnelRoster(personnelRoster);
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
