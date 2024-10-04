package com.example.smartttadmin.controller;

import com.example.smartttadmin.dto.PersonnelRoster;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.service.SmObsService;

import com.example.smartttadmin.service.StUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public Result createPersonnelRoster(@RequestBody PersonnelRoster personnelRoster){
        return smObsService.createOnePersonnelRoster(personnelRoster);
    }

    @PostMapping("/delete")
    public Result deletePersonnelRosteByIDs(@RequestBody List<String> ids) {
        return stUsersService.deleteUsersByIDs(ids);
    }
    @PostMapping("/update")
    public Result UpdatePersonnalRoster(@RequestBody PersonnelRoster personnelRoster){
        return stUsersService.updateOnePersonnelRoster(personnelRoster);
    }
    /**
     * excel表格导入教师
     * @param file
     * @return
     */
    @PostMapping("/import")
    public Result importTeacherAndStudent(@RequestParam("file") MultipartFile file){
        try{
            List<PersonnelRoster> personnelRosterList = stUsersService.importTeacherAndStudentExcel(file);
            for(PersonnelRoster personnelRoster :personnelRosterList){
                smObsService.createOnePersonnelRoster(personnelRoster);
            }
            return Result.success();

        } catch (IOException e){
            return Result.error(400,"文件导入失败");
        }
    }

}
