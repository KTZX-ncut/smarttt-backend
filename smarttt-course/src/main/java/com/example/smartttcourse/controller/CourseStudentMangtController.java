package com.example.smartttcourse.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.smartttcourse.Utils.AuthorizationAspect;
import com.example.smartttcourse.dto.CreateStudent;
import com.example.smartttcourse.exception.res.ResponseEnum;
import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.exception.utils.SmartAssert;
import com.example.smartttcourse.pojo.CmClassroomStudent;
import com.example.smartttcourse.service.CmClassroomStudentService;
import com.example.smartttcourse.service.SmObsService;
import org.apache.commons.collections4.SetUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author lunSir
 * @create 2024-09-05 12:13
 */
@RestController
@RequestMapping("/coursemangt/classroommangt/student")
public class CourseStudentMangtController {

    final CmClassroomStudentService cmClassroomStudentService;

    final SmObsService smObsService;

    public CourseStudentMangtController(CmClassroomStudentService cmClassroomStudentService, SmObsService smObsService) {
        this.cmClassroomStudentService = cmClassroomStudentService;
        this.smObsService = smObsService;
    }

    /**
     * 查询学生列表
     */
    @GetMapping("/list")
    public Result getStudentList(@RequestParam("obsid") String obsid){
        return cmClassroomStudentService.getStudentList(obsid);
    }


    @GetMapping("/studentRP")
    public Result StudentRPList(){
        String obsID = smObsService.getSchoolObs();
        return cmClassroomStudentService.getObsRPStudentList(obsID);
    }

    /**
     * 将学生加入指定课堂
     * @param createStudentList
     * @return
     */
    @PostMapping("/create")
    @Transactional(rollbackFor = Exception.class)
    public Result createStudentClassRoom(@RequestBody List<CreateStudent> createStudentList){
        ArrayList<CmClassroomStudent> list = new ArrayList<>();
        // 对数据去重
        HashSet<CreateStudent> createStudentHashSet = new HashSet<>();
        createStudentHashSet.addAll(createStudentList);
        for (CreateStudent createStudent : createStudentHashSet) {
            CmClassroomStudent classroomStudent = new CmClassroomStudent();
            // 校验字段
            validateCreateStudentClassRoom(createStudent,classroomStudent);
            // 这个学生有没有在课堂中
            LambdaQueryWrapper<CmClassroomStudent> wrapper =
                    new LambdaQueryWrapper<>();
            wrapper.eq(CmClassroomStudent::getClassroomId,classroomStudent.getClassroomId())
                    .eq(CmClassroomStudent::getUserId,classroomStudent.getUserId());
            int count = cmClassroomStudentService.count(wrapper);
            if(count == 0){
                list.add(classroomStudent);
            }
        }
        Boolean f = cmClassroomStudentService.saveBatch(list);
        return Result.success(f);
    }

    /**
     * excel导入学生信息
     * @param file
     * @return
     */
    @PostMapping("/import")
    public Result importTeacherAndStudent(@RequestParam("file") MultipartFile file, @RequestParam("classroomId") String classRoomId){
        try{
            List<CmClassroomStudent> classroomStudentList = cmClassroomStudentService.importClassRoomStudentExcel(file,classRoomId);
            // 批量插入
            SmartAssert.notFalse(cmClassroomStudentService
                    .saveBatch(classroomStudentList),
                    ResponseEnum.INSERT_FAIL);
            return Result.success();
        } catch (Exception e){
            return Result.error(400,"文件导入失败");
        }
    }


    /**
     * 删除指定课堂ID下的学生
     * @param stuIds
     * @return
     */
    @DeleteMapping("/delete")
    public Result deleteStudents(@RequestParam("stuIds") List<String> stuIds, @RequestParam("classRoomId") String classRoomId){
        if(stuIds == null || stuIds.isEmpty() || classRoomId == null){
            return Result.error(600,"非法请求");
        }
        // 将业务逻辑转到服务层(删除课堂中的学生)
        if(cmClassroomStudentService.deleteClassRoomStudent(stuIds,classRoomId)){
            return Result.success();
        }
        return Result.error("删除失败，请再尝试");

    }

    /**
     * 删除指定课堂ID下的全部学生
     * @param classRoomId
     * @return
     */
    @DeleteMapping("/deleteAll")
    public Result deleteAllStudent(@RequestParam("classRoomId") String classRoomId){
        if(classRoomId == null || "".equals(classRoomId)){
            return Result.error(600,"非法请求");
        }
        if(cmClassroomStudentService.deleteClassRoomStudentAll(classRoomId)){
            return Result.success();
        }
        return Result.error("删除失败，请再尝试");
    }

    private static void validateCreateStudentClassRoom(CreateStudent createStudent,CmClassroomStudent classroomStudent){
        // 校验参数
        // 课堂ID，userID，专业名称，班级，班级ID，姓名，登录名称
        SmartAssert.notEmpty(createStudent.getClassRoomId(),ResponseEnum.CLASSROOM_ID_NOT_NULL);
        SmartAssert.notEmpty(createStudent.getUsersid(),ResponseEnum.USER_ID_NOT_NULL);
        SmartAssert.notEmpty(createStudent.getProname(),ResponseEnum.PRONAME_NOT_NULL);
        SmartAssert.notEmpty(createStudent.getObsname(),ResponseEnum.OBSNAME_NOT_NULL);
        SmartAssert.notEmpty(createStudent.getObsid(),ResponseEnum.OBSID_NOT_NULL);
        SmartAssert.notEmpty(createStudent.getLoginname(),ResponseEnum.LOGIN_NAME_NOT_NULL);
        SmartAssert.notEmpty(createStudent.getUsername(),ResponseEnum.USERNAME_NOT_NULL);
        // 数据赋值
        classroomStudent.setClassroomId(createStudent.getClassRoomId());
        classroomStudent.setObsId(createStudent.getObsid());
        classroomStudent.setUserId(createStudent.getUsersid());
        classroomStudent.setObsName(createStudent.getObsname());
        classroomStudent.setUserName(createStudent.getUsername());
        classroomStudent.setProName(createStudent.getProname());
        classroomStudent.setLoginname(createStudent.getLoginname());
        classroomStudent.setRowNo(createStudent.getRowNo());
        classroomStudent.setCourseScore(createStudent.getCourseScore());
    }

}
