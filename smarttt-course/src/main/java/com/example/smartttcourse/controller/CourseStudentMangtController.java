package com.example.smartttcourse.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.smartttcourse.dto.CreateStudent;
import com.example.smartttcourse.exception.res.ResponseEnum;
import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.exception.utils.SmartAssert;
import com.example.smartttcourse.pojo.CmClassroomClassroommenu;
import com.example.smartttcourse.pojo.CmClassroomHomeworkinfo;
import com.example.smartttcourse.pojo.CmClassroomStudent;
import com.example.smartttcourse.service.CmClassroomClassroommenuService;
import com.example.smartttcourse.service.CmClassroomHomeworkinfoService;
import com.example.smartttcourse.service.CmClassroomStudentService;
import com.example.smartttcourse.service.SmObsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lunSir
 * @create 2024-09-05 12:13
 */
@RestController
@RequestMapping("/coursemangt/classroommangt/student")
@Slf4j
@Api(tags = "6. 课堂学生管理", description = "课堂学生名单、导入、删除和层级查询接口")
public class CourseStudentMangtController {

    private final CmClassroomStudentService cmClassroomStudentService;


    private final SmObsService smObsService;

    private final CmClassroomClassroommenuService classroommenuService;

    private final CmClassroomHomeworkinfoService classroomHomeworkinfoService;


    public CourseStudentMangtController(CmClassroomStudentService cmClassroomStudentService, SmObsService smObsService, CmClassroomClassroommenuService classroommenuService, CmClassroomHomeworkinfoService cmClassroomHomeworkinfoService, CmClassroomHomeworkinfoService classroomHomeworkinfoService) {
        this.cmClassroomStudentService = cmClassroomStudentService;
        this.smObsService = smObsService;
        this.classroommenuService = classroommenuService;
        this.classroomHomeworkinfoService = classroomHomeworkinfoService;
    }

    /**
     * 查询学生列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询课堂学生列表", notes = "按课堂或组织节点 ID 查询学生列表。")
    public Result getStudentList(@ApiParam(value = "课堂或组织节点 ID", required = true, example = "classroom-001") @RequestParam("obsid") String obsid) {
        return cmClassroomStudentService.getStudentList(obsid);
    }


    @GetMapping("/studentRP")
    @ApiOperation(value = "获取学生负责人候选列表", notes = "查询学生负责人或课堂学生候选人员列表。")
    public Result StudentRPList() {
        String obsID = smObsService.getSchoolObs();
        return cmClassroomStudentService.getObsRPStudentList(obsID);
    }

    /**
     * 将学生加入指定课堂
     *
     * @param createStudentList
     * @return
     */
    @PostMapping("/create")
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "将学生加入课堂", notes = "批量将学生加入指定课堂，并同步生成对应的作业和菜单记录。")
    public Result createStudentClassRoom(@ApiParam(value = "学生加入课堂请求列表", required = true) @RequestBody List<CreateStudent> createStudentList) {
        ArrayList<CmClassroomStudent> list = new ArrayList<>();
        // 对数据去重
        HashSet<CreateStudent> createStudentHashSet = new HashSet<>();
        createStudentHashSet.addAll(createStudentList);
        for (CreateStudent createStudent : createStudentHashSet) {
            CmClassroomStudent classroomStudent = new CmClassroomStudent();
            // 校验字段
            validateCreateStudentClassRoom(createStudent, classroomStudent);
            // 这个学生有没有在课堂中
            LambdaQueryWrapper<CmClassroomStudent> wrapper =
                    new LambdaQueryWrapper<>();
            wrapper.eq(CmClassroomStudent::getClassroomId, classroomStudent.getClassroomId())
                    .eq(CmClassroomStudent::getUserId, classroomStudent.getUserId());
            int count = cmClassroomStudentService.count(wrapper);
            if (count == 0) {
                list.add(classroomStudent);
            }
        }
        cmClassroomStudentService.saveBatch(list);
        // 更新 homeworkInfo
        List<CmClassroomHomeworkinfo> homeworkInfoList = list.stream().map((t) -> {
            CmClassroomHomeworkinfo homeworkInfo = new CmClassroomHomeworkinfo();
            homeworkInfo.setClassroomid(t.getClassroomId());
            homeworkInfo.setStuid(t.getUserId());
            return homeworkInfo;
        }).collect(Collectors.toList());
        classroomHomeworkinfoService.saveBatch(homeworkInfoList);
        // 更新menu
        List<CmClassroomClassroommenu> classroomMenuList = list.stream().map((t) -> {
            CmClassroomClassroommenu classroomMenu = new CmClassroomClassroommenu();
            classroomMenu.setClassroomid(t.getClassroomId());
            classroomMenu.setStuid(t.getUserId());
            return classroomMenu;
        }).collect(Collectors.toList());
        classroommenuService.saveBatch(classroomMenuList);
        return Result.success(true);
    }

    /**
     * excel导入学生信息
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/import", consumes = "multipart/form-data")
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "Excel 导入课堂学生", notes = "上传 Excel 文件并批量导入学生到指定课堂。")
    public Result importTeacherAndStudent(@ApiParam(value = "Excel 文件", required = true) @RequestParam("file") MultipartFile file, @ApiParam(value = "课堂 ID", required = true, example = "classroom-001") @RequestParam("classroomId") String classRoomId) {
        List<CmClassroomStudent> classroomStudentList = cmClassroomStudentService.importClassRoomStudentExcel(file, classRoomId);
        // 批量插入
        cmClassroomStudentService.saveBatch(classroomStudentList);

        // 更新 homeworkInfo
        List<CmClassroomHomeworkinfo> homeworkInfoList = classroomStudentList.stream().map((t) -> {
            CmClassroomHomeworkinfo homeworkInfo = new CmClassroomHomeworkinfo();
            homeworkInfo.setClassroomid(t.getClassroomId());
            homeworkInfo.setStuid(t.getUserId());
            return homeworkInfo;
        }).collect(Collectors.toList());
        classroomHomeworkinfoService.saveBatch(homeworkInfoList);
        // 更新menu
        List<CmClassroomClassroommenu> classroomMenuList = classroomStudentList.stream().map((t) -> {
            CmClassroomClassroommenu classroomMenu = new CmClassroomClassroommenu();
            classroomMenu.setClassroomid(t.getClassroomId());
            classroomMenu.setStuid(t.getUserId());
            return classroomMenu;
        }).collect(Collectors.toList());

        classroommenuService.saveBatch(classroomMenuList);
        return Result.success();
    }


    /**
     * 删除指定课堂ID下的学生
     *
     * @param stuIds
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除课堂中的指定学生", notes = "按学生 ID 列表从指定课堂中删除学生。")
    public Result deleteStudents(@ApiParam(value = "学生 ID 列表", required = true) @RequestParam("stuIds") List<String> stuIds, @ApiParam(value = "课堂 ID", required = true, example = "classroom-001") @RequestParam("classRoomId") String classRoomId) {
        if (stuIds == null || stuIds.isEmpty() || classRoomId == null) {
            return Result.error(600, "非法请求");
        }
        // 将业务逻辑转到服务层(删除课堂中的学生)
        if (cmClassroomStudentService.deleteClassRoomStudent(stuIds, classRoomId)) {
            return Result.success();
        }
        return Result.error("删除失败，请再尝试");

    }

    /**
     * 删除指定课堂ID下的全部学生
     *
     * @param classRoomId
     * @return
     */
    @DeleteMapping("/deleteAll")
    @ApiOperation(value = "删除课堂中的全部学生", notes = "删除指定课堂 ID 下的所有学生记录。")
    public Result deleteAllStudent(@ApiParam(value = "课堂 ID", required = true, example = "classroom-001") @RequestParam("classRoomId") String classRoomId) {
        if (classRoomId == null || "".equals(classRoomId)) {
            return Result.error(600, "非法请求");
        }
        if (cmClassroomStudentService.deleteClassRoomStudentAll(classRoomId)) {
            return Result.success();
        }
        return Result.error("删除失败，请再尝试");
    }

    private static void validateCreateStudentClassRoom(CreateStudent createStudent, CmClassroomStudent classroomStudent) {
        // 校验参数
        // 课堂ID，userID，专业名称，班级，班级ID，姓名，登录名称
        SmartAssert.notEmpty(createStudent.getClassRoomId(), ResponseEnum.CLASSROOM_ID_NOT_NULL);
        SmartAssert.notEmpty(createStudent.getUsersid(), ResponseEnum.USER_ID_NOT_NULL);
        SmartAssert.notEmpty(createStudent.getProname(), ResponseEnum.PRONAME_NOT_NULL);
        SmartAssert.notEmpty(createStudent.getObsname(), ResponseEnum.OBSNAME_NOT_NULL);
        SmartAssert.notEmpty(createStudent.getObsid(), ResponseEnum.OBSID_NOT_NULL);
        SmartAssert.notEmpty(createStudent.getLoginname(), ResponseEnum.LOGIN_NAME_NOT_NULL);
        SmartAssert.notEmpty(createStudent.getUsername(), ResponseEnum.USERNAME_NOT_NULL);
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

    /**
     * 获取学生所属层级(班级/专业/系/院)
     */
    @GetMapping("/getStudentLevel")
    @ApiOperation(value = "获取学生所属层级", notes = "返回学生在组织结构中的层级名称，例如班级、专业、系或学院。")
    public Result getStudentLevel() {
        String levelName = cmClassroomStudentService.getStudentLevel();
        return Result.ok().data(levelName);
    }
}
