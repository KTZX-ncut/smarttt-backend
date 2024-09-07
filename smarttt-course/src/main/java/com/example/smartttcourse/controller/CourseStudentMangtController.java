package com.example.smartttcourse.controller;

import com.example.smartttcourse.Utils.AuthorizationAspect;
import com.example.smartttcourse.Utils.CommonFunctions;
import com.example.smartttcourse.dto.CreateStudentDto;
import com.example.smartttcourse.exception.res.ResponseEnum;
import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.dto.StudentDto;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.exception.utils.SmartAssert;
import com.example.smartttcourse.pojo.CmClassroomStudent;
import com.example.smartttcourse.service.CmClassroomStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lunSir
 * @create 2024-09-05 12:13
 */
@RestController
@RequestMapping("/coursemangt/classroommangt/student")
public class CourseStudentMangtController {

    final CmClassroomStudentService cmClassroomStudentService;

    public CourseStudentMangtController(CmClassroomStudentService cmClassroomStudentService) {
        this.cmClassroomStudentService = cmClassroomStudentService;
    }

    /**
     * 查询学生列表
     * @param studentDto
     * @return
     */
    @GetMapping("/list")
    public Result getStudentList(StudentDto studentDto){
        // obsid 必须传
        if(studentDto.getObsid() == null || "".equals(studentDto.getObsid())){
            return Result.error(600,"obsid不能为空");
        }
        return cmClassroomStudentService.getStudentList(studentDto);
    }

    @PostMapping("/create")
    @Transactional(rollbackFor = Exception.class)
    public Result createStudentClassRoom(@RequestBody List<CreateStudentDto> createStudentDtoList){
        // 验参
        SmartAssert.ListIsNotNull(createStudentDtoList,ResponseEnum.PARAM_IS_NOT_NULL);
        for (CreateStudentDto createStudentDto : createStudentDtoList) {
            validateCreateStudentClassRoom(createStudentDto);
        }
        // List<Dto> 转 List<pojo>（可以用mapstruct进行优化）
        List<CmClassroomStudent> classroomStudentList = new ArrayList<>();
        for (CreateStudentDto createStudentDto : createStudentDtoList) {
            CmClassroomStudent cmClassroomStudent = new CmClassroomStudent();
            BeanUtils.copyProperties(createStudentDto,cmClassroomStudent);
            classroomStudentList.add(cmClassroomStudent);
        }
        // 批量插入
        boolean saveBatch = cmClassroomStudentService.saveBatch(classroomStudentList);
        SmartAssert.notFalse(saveBatch,ResponseEnum.INSERT_FAIL);
        return Result.success(true);
    }

    /**
     * excel导入学生信息
     * @param file
     * @return
     */
    @PostMapping("/import")
    public Result importTeacherAndStudent(@RequestParam("file") MultipartFile file){
        try{
            List<CmClassroomStudent> classroomStudentList = cmClassroomStudentService.importClassRoomStudentExcel(file);
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
     * 查询任课老师的课堂下的所有学生
     * @return Result
     */
    @GetMapping("/v/list")
    public Result getStudentListTest(){
        // 从ThreadLocal中拿到token
        Token token = AuthorizationAspect.getTokenFromContext();
        if(token == null || token.getObsid() == null) return Result.error("token为空,非法请求");
        return cmClassroomStudentService.getStudentListByClassRoomId(token.getObsid());
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

    private static void validateCreateStudentClassRoom(CreateStudentDto createStudentDto){
        // 校验参数
        // 课堂ID，userID，专业名称，班级，班级ID，姓名，登录名称
        SmartAssert.notEmpty(createStudentDto.getClassroomId(),ResponseEnum.PARAM_IS_NOT_NULL);
        SmartAssert.notEmpty(createStudentDto.getUserId(),ResponseEnum.PARAM_IS_NOT_NULL);
        SmartAssert.notEmpty(createStudentDto.getProName(),ResponseEnum.PARAM_IS_NOT_NULL);
        SmartAssert.notEmpty(createStudentDto.getObsName(),ResponseEnum.PARAM_IS_NOT_NULL);
        SmartAssert.notEmpty(createStudentDto.getObsId(),ResponseEnum.PARAM_IS_NOT_NULL);
        SmartAssert.notEmpty(createStudentDto.getUserName(),ResponseEnum.PARAM_IS_NOT_NULL);
        SmartAssert.notEmpty(createStudentDto.getLoginname(),ResponseEnum.PARAM_IS_NOT_NULL);
        String id = CommonFunctions.generateEnhancedID("cm_classroom_student");
    }
}
