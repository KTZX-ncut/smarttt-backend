package com.example.smartttcourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.smartttcourse.Utils.CommonFunctions;
import com.example.smartttcourse.dto.StudentTree;
import com.example.smartttcourse.exception.res.ResponseEnum;
import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.exception.utils.SmartAssert;
import com.example.smartttcourse.mapper.CmClassStudentMapper;
import com.example.smartttcourse.mapper.SmObsMapper;
import com.example.smartttcourse.pojo.*;
import com.example.smartttcourse.service.*;
import com.example.smartttcourse.vo.StudentInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lunSir
 * @create 2024-09-05 14:09
 */
@Service
@Slf4j
public class CmClassroomStudentServiceImpl extends ServiceImpl<CmClassStudentMapper,CmClassroomStudent> implements CmClassroomStudentService {

    private final CmClassStudentMapper cmClassStudentMapper;

    private final CmClassroomClassroommenuService cmClassroomClassroommenuService;
    private final CmClassroomHomeworkinfoService cmClassroomHomeworkinfoService;
    private final CmClassroomMypracticelistService cmClassroomMypracticelistService;

    private final SmStudentService smStudentService;

    private final  SmObsService smObsService;

    private final StUsersService stUsersService;

    private final CmClassRoomService cmClassRoomService;



    public CmClassroomStudentServiceImpl(CmClassStudentMapper cmClassStudentMapper, CmClassroomClassroommenuService cmClassroomClassroommenuService, CmClassroomHomeworkinfoService cmClassroomHomeworkinfoService, CmClassroomMypracticelistService cmClassroomMypracticelistService, SmStudentService smStudentService, SmObsMapper smObsMapper, SmObsService smObsService, StUsersService stUsersService, CmClassRoomService cmClassRoomService) {
        this.cmClassStudentMapper = cmClassStudentMapper;
        this.cmClassroomClassroommenuService = cmClassroomClassroommenuService;
        this.cmClassroomHomeworkinfoService = cmClassroomHomeworkinfoService;
        this.cmClassroomMypracticelistService = cmClassroomMypracticelistService;
        this.smStudentService = smStudentService;
        this.smObsService = smObsService;
        this.stUsersService = stUsersService;
        this.cmClassRoomService = cmClassRoomService;
    }

    @Override
    public Result getStudentListByClassRoomId(String classRoomId) {
        List<StudentInfoVO> StudentInfoVOList = cmClassStudentMapper.getStudentListByClassRoomId(classRoomId);
        return Result.success(StudentInfoVOList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteClassRoomStudent(List<String> stuIds, String classRoomId) {
        // delete
        // 1. 从课堂学生表（cm_classroomStudent）里删除
        // 1.1 通过stuIds找到UserId
        List userIds = smStudentService.listByIds(stuIds)
                .stream()
                .map(t->t.getUsersid())
                .collect(Collectors.toList());

        System.out.println("-------->UserIDs:  " + userIds);
        // 从当前表删除
        LambdaQueryWrapper<CmClassroomStudent> classroomStudentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        classroomStudentLambdaQueryWrapper.in(CmClassroomStudent::getUserId,userIds);
        baseMapper.delete(classroomStudentLambdaQueryWrapper);
        // 2. 从课堂菜单表删除
        LambdaQueryWrapper<CmClassroomClassroommenu> classroomClassroommenuLambdaQueryWrapper = new LambdaQueryWrapper<>();
        classroomClassroommenuLambdaQueryWrapper.eq(CmClassroomClassroommenu::getClassroomid,classRoomId)
                        .in(CmClassroomClassroommenu::getStuid,stuIds);
        cmClassroomClassroommenuService.remove(classroomClassroommenuLambdaQueryWrapper);
        // 3. 作业统计信息表
        LambdaQueryWrapper<CmClassroomHomeworkinfo> cmClassroomHomeworkinfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        cmClassroomHomeworkinfoLambdaQueryWrapper.eq(CmClassroomHomeworkinfo::getClassroomid,classRoomId)
                .in(CmClassroomHomeworkinfo::getStuid,stuIds);
        cmClassroomHomeworkinfoService.remove(cmClassroomHomeworkinfoLambdaQueryWrapper);
        // 4. 作业考试表
        LambdaQueryWrapper<CmClassroomMypracticelist> classroomMypracticelistLambdaQueryWrapper = new LambdaQueryWrapper<>();
        classroomMypracticelistLambdaQueryWrapper.eq(CmClassroomMypracticelist::getClassroomid,classRoomId)
                .in(CmClassroomMypracticelist::getStuid,stuIds);
        cmClassroomMypracticelistService.remove(classroomMypracticelistLambdaQueryWrapper);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteClassRoomStudentAll(String classRoomId) {
        // deleteALL
        // 1. 从课堂学生表（cm_classroomStudent）里删除
        // 从当前表删除
        LambdaQueryWrapper<CmClassroomStudent> classroomStudentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        classroomStudentLambdaQueryWrapper.eq(CmClassroomStudent::getClassroomId,classRoomId);
        baseMapper.delete(classroomStudentLambdaQueryWrapper);
        // 2. 从课堂菜单表删除
        LambdaQueryWrapper<CmClassroomClassroommenu> classroomClassroommenuLambdaQueryWrapper = new LambdaQueryWrapper<>();
        classroomClassroommenuLambdaQueryWrapper.eq(CmClassroomClassroommenu::getClassroomid,classRoomId);
        cmClassroomClassroommenuService.remove(classroomClassroommenuLambdaQueryWrapper);
        // 3. 作业统计信息表
        LambdaQueryWrapper<CmClassroomHomeworkinfo> cmClassroomHomeworkinfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        cmClassroomHomeworkinfoLambdaQueryWrapper.eq(CmClassroomHomeworkinfo::getClassroomid,classRoomId);
        cmClassroomHomeworkinfoService.remove(cmClassroomHomeworkinfoLambdaQueryWrapper);
        // 4. 作业考试表
        LambdaQueryWrapper<CmClassroomMypracticelist> classroomMypracticelistLambdaQueryWrapper = new LambdaQueryWrapper<>();
        classroomMypracticelistLambdaQueryWrapper.eq(CmClassroomMypracticelist::getClassroomid,classRoomId);
        cmClassroomMypracticelistService.remove(classroomMypracticelistLambdaQueryWrapper);
        return true;
    }

    @Override
    public Result getStudentList(String obsid){
        SmartAssert.notEmpty(obsid, ResponseEnum.FIELD_IS_NULL);
        // 查数据库
        LambdaQueryWrapper<CmClassroomStudent> wrapper =
                new LambdaQueryWrapper<>();
        wrapper.eq(CmClassroomStudent::getClassroomId,obsid);
        List<CmClassroomStudent> studentList = baseMapper.selectList(wrapper);
        ArrayList<StudentInfoVO> studentInfoVOS = new ArrayList<>();
        for (CmClassroomStudent student : studentList) {
            StudentInfoVO studentInfoVO = new StudentInfoVO();
            BeanUtils.copyProperties(student,studentInfoVO);
            LambdaQueryWrapper<SmStudent> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(SmStudent::getUsersid,studentInfoVO.getUserId());
            SmStudent one = smStudentService.getOne(lambdaQueryWrapper);
            studentInfoVO.setStuId(one.getId());
            studentInfoVO.setStuno(one.getStuno());
            studentInfoVOS.add(studentInfoVO);
        }

        return Result.success(studentInfoVOS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<CmClassroomStudent> importClassRoomStudentExcel(MultipartFile file,String classRoomId) {
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0); // 假设 Excel 文件中只有一个工作表
            DataFormatter dataFormatter = new DataFormatter();
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // 跳过标题行

            List<CmClassroomStudent> classroomStudentList = new ArrayList<>();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String stuno = dataFormatter.formatCellValue(row.getCell(0));
                String username = dataFormatter.formatCellValue(row.getCell(1));
                String obsName = dataFormatter.formatCellValue(row.getCell(2));
                SmartAssert.notEmpty(stuno,ResponseEnum.PARAM_IS_NOT_NULL);
                SmartAssert.notEmpty(username,ResponseEnum.PARAM_IS_NOT_NULL);

                CmClassroomStudent classroomStudent = new CmClassroomStudent();
                classroomStudent.setClassroomId(classRoomId);
                // userId
                QueryWrapper<SmStudent> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("stuno",stuno);
                SmStudent student = smStudentService.getOne(queryWrapper);
                classroomStudent.setUserId(student.getUsersid());
                classroomStudent.setObsId(student.getObsid());
                classroomStudent.setProName(student.getProname());
                // 判断username是否合法
                String usernameInDataSource = stUsersService.getUsernameById(student.getUsersid());
                SmartAssert.eq(usernameInDataSource,username,ResponseEnum.USERNAME_NOT_EQ);
                // username
                classroomStudent.setUserName(username);
                // obsName
                classroomStudent.setObsName(obsName);
                // loginname
                classroomStudent.setLoginname(stUsersService.getloginNameById(student.getUsersid()));
                classroomStudentList.add(classroomStudent);
            }
            return classroomStudentList;
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Result getObsRPStudentList(String RootObsID) {
        List<SmObs> allObs = smObsService.list();
        // List<SmObs> 转 List<StudentTree>
        ArrayList<StudentTree> allObsTree = new ArrayList<>();
        for (SmObs obs : allObs) {
            StudentTree studentTree = new StudentTree();
            BeanUtils.copyProperties(obs,studentTree);
            allObsTree.add(studentTree);
        }
        Map<String, List<StudentTree>> obsMap = allObsTree.stream()
                .collect(Collectors.groupingBy(StudentTree::getPid,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingLong(StudentTree::getOrderno))
                                        .collect(Collectors.toList())
                        )
                ));


        List<StudentTree> rootObs =  obsMap.get(RootObsID);
        // 递归构建菜单树
        try{
            buildObsRPTree(rootObs,  obsMap);
        }catch (NullPointerException e){
            return Result.error("未找到结果");
        }

        return Result.success(rootObs);
    }


    private void buildObsRPTree(List<StudentTree> parentSmObs, Map<String, List<StudentTree>> obsMap) {
        for (StudentTree parentObs : parentSmObs) {
            parentObs.setStudentDtos(stUsersService.getAllStudentByObsID(parentObs.getId()));
            //找出pid为父菜单的孩子
            List<StudentTree> childObs =  obsMap.get(parentObs.getId());
            if (childObs != null) {
                parentObs.setChildren(childObs);
                buildObsRPTree(childObs, obsMap);
            }
        }
    }

    public static boolean isEmpty(String o){
        return o == null || "".equals(o);
    }
}
