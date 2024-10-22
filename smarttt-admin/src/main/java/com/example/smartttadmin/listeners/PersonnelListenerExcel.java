package com.example.smartttadmin.listeners;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.smartttadmin.Utils.CommonFunctions;
import com.example.smartttadmin.converts.ExcelConvert;
import com.example.smartttadmin.dto.PersonnelRoster;
import com.example.smartttadmin.enums.CateLogEnum;
import com.example.smartttadmin.pojo.*;
import com.example.smartttadmin.service.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author lunSir
 * @create 2024-10-18 12:45
 */
@Slf4j
public class PersonnelListenerExcel implements ReadListener<PersonnelExcel> {

    // 缓存数量
    private static final int BATCH_COUNT = 50;

    // 缓存
    private List<PersonnelExcel> PersonnelCachedList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    private SmObsService smObsService;
    private StUsersService usersService;
    private StudentService studentService;
    private TeacherService teacherService;

    private StLevelService levelService;


    public PersonnelListenerExcel(SmObsService smObsService, StUsersService usersService, StudentService studentService, TeacherService teacherService,StLevelService levelService) {
        this.smObsService = smObsService;
        this.usersService = usersService;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.levelService = levelService;
    }

    @Override
    public void invoke(PersonnelExcel personnelExcel, AnalysisContext analysisContext) {
        if (log.isInfoEnabled()) {
            log.info("解析一条数据: PersonnelListenerExcel.invoke.PersonnelExcel:{}", JSON.toJSONString(personnelExcel));
        }
        // 加入缓存
        PersonnelCachedList.add(personnelExcel);

        if (PersonnelCachedList.size() >= BATCH_COUNT) {
            // 入库
            saveData(PersonnelCachedList);
            // 清空缓存
            PersonnelCachedList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 入库
        saveData(PersonnelCachedList);
        // 清空缓存
        PersonnelCachedList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        log.info("excel导入人员列表完成！");
    }

    /**
     * 入库
     */
    public void saveData(List<PersonnelExcel> personnelExcelList) {
        // 校验数据
        List<PersonnelRoster> personnelRosterList =
                this.validate(personnelExcelList);
        // 加入User表
        this.saveBachUser(personnelRosterList);
        // 处理Student表和Teacher表
        this.dealStudentAndTeacher(personnelRosterList);
    }

    /**
     * 提前校验数据的合法性，这样就可以不用事务
     */
    private List<PersonnelRoster> validate(List<PersonnelExcel> personnelExcelList) {
        List<PersonnelRoster> list = new LinkedList<>();
        // 判断老师（学生）的层级是够合法
        List<StLevel> levelList = levelService.list().stream()
                .filter(t-> Objects.equals(t.getTeacher(),"1") ||Objects.equals(t.getStudent(),"1"))
                .collect(Collectors.toList());
        Long teacherObsDeep = null;
        Long studentObsDeep = null;
        for (StLevel stLevel : levelList) {
            if (Objects.equals(stLevel.getStudent(),"1")){
                studentObsDeep = stLevel.getObsdeep();
            }
            if (Objects.equals(stLevel.getTeacher(),"1")){
                teacherObsDeep = stLevel.getObsdeep();
            }
        }
        for (PersonnelExcel personnelExcel : personnelExcelList) {

            CateLogEnum cateLogEnum = this.DetermineIdentity(personnelExcel.getCatelog());
            // 1. obsName通过拿到obsId
            QueryWrapper<SmObs> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("obsname",personnelExcel.getObsname());
            List<SmObs> smObsList = new LinkedList<>();
            if (Objects.equals(CateLogEnum.STUDENT,cateLogEnum)){
                queryWrapper.eq("obsdeep",studentObsDeep);
                smObsList = smObsService.list(queryWrapper);
            }
            if (Objects.equals(CateLogEnum.TEACHER,cateLogEnum)){
                queryWrapper.eq("obsdeep",teacherObsDeep);
                smObsList = smObsService.list(queryWrapper);
            }
            List<String> obsIdList = smObsList.stream().map(SmObs::getId).collect(Collectors.toList());
            if (CollectionUtil.isEmpty(obsIdList)) {
                throw new RuntimeException("业务异常:"+personnelExcel.getUsername()+"的所属院系以及层级不存在或者不匹配！");
            }
            // 2. 判断loginName是否唯一（stUsersMapper）
            List<String> stUsersList = usersService.getStUsersByloginName(personnelExcel.getLoginname());
            if (!CollectionUtil.isEmpty(stUsersList)) {
                throw new RuntimeException("业务异常:"+personnelExcel.getUsername()+"的登录名称已经被注册了");
            }
            PersonnelRoster personnelRoster = ExcelConvert.INSTANCE.personnelExcelToPersonnelRoster(personnelExcel);
            personnelRoster.setObsid(obsIdList.get(0));
            personnelRoster.setId(CommonFunctions.generateEnhancedID("st_users"));
            personnelRoster.setCatelog(cateLogEnum.getStatus().toString());
            list.add(personnelRoster);
        }
        return list;
    }


    /**
     * 处理Student表和Teacher表
     */
    private void dealStudentAndTeacher(List<PersonnelRoster> personnelRosterList) {

        List<SmStudent> studentList = new LinkedList<>();
        List<SmTeacher> teacherList = new LinkedList<>();
        for (PersonnelRoster personnelRoster : personnelRosterList) {
            CateLogEnum cateLogEnum = CateLogEnum.getCateLogEnumByStatus(Integer.parseInt(personnelRoster.getCatelog()));
            switch (cateLogEnum) {
                case TEACHER: {
                    SmTeacher teacher = new SmTeacher();
                    teacher.setCreatetime(LocalDateTime.now().toString());
                    teacher.setJobno(personnelRoster.getPersonnelno());
                    teacher.setObsid(personnelRoster.getObsid());
                    teacher.setUsersid(personnelRoster.getId());
                    teacherList.add(teacher);
                    break;
                }
                case STUDENT: {
                    SmStudent student = new SmStudent();
                    student.setObsid(personnelRoster.getObsid());
                    student.setUsersid(personnelRoster.getId());
                    student.setCreatetime(LocalDateTime.now().toString());
                    student.setStuno(personnelRoster.getPersonnelno());
                    studentList.add(student);
                    break;
                }
            }
        }
        if (CollectionUtil.isNotEmpty(studentList)) {
            saveBachStudent(studentList);
        }
        if (CollectionUtil.isNotEmpty(teacherList)) {
            saveBachTeacher(teacherList);
        }
    }


    public CateLogEnum DetermineIdentity(String identity) {
        return CateLogEnum.getCateLogEnumByIdentity(identity);
    }

    /**
     * 入库Student表
     */
    private void saveBachStudent(List<SmStudent> studentList) {
        studentService.saveBatch(studentList);
    }

    /**
     * 入库Teacher表
     */
    private void saveBachTeacher(List<SmTeacher> teacherList) {
        teacherService.saveBatch(teacherList);
    }

    /**
     * 入库User表
     */
    private void saveBachUser(List<PersonnelRoster> personnelRosterList) {
        usersService.saveBach(personnelRosterList);
    }
}
