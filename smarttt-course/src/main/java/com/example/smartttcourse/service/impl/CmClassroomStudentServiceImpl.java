package com.example.smartttcourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.smartttcourse.Utils.CommonFunctions;
import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.dto.StudentDto;
import com.example.smartttcourse.mapper.CmClassStudentMapper;
import com.example.smartttcourse.mapper.SmObsMapper;
import com.example.smartttcourse.pojo.*;
import com.example.smartttcourse.service.*;
import com.example.smartttcourse.vo.StudentInfoVO;
import com.example.smartttcourse.vo.StudentVO;
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



    public CmClassroomStudentServiceImpl(CmClassStudentMapper cmClassStudentMapper, CmClassroomClassroommenuService cmClassroomClassroommenuService, CmClassroomHomeworkinfoService cmClassroomHomeworkinfoService, CmClassroomMypracticelistService cmClassroomMypracticelistService, SmStudentService smStudentService, SmObsMapper smObsMapper, SmObsService smObsService, StUsersService stUsersService) {
        this.cmClassStudentMapper = cmClassStudentMapper;
        this.cmClassroomClassroommenuService = cmClassroomClassroommenuService;
        this.cmClassroomHomeworkinfoService = cmClassroomHomeworkinfoService;
        this.cmClassroomMypracticelistService = cmClassroomMypracticelistService;
        this.smStudentService = smStudentService;
        this.smObsService = smObsService;
        this.stUsersService = stUsersService;
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
    public Result getStudentList(StudentDto studentDto) {
        List<SmObs> AllObs = smObsService.list();
        Map<String, List<SmObs>> obsMap = AllObs.stream()
                .collect(Collectors.groupingBy(SmObs::getPid));
        List<SmObs> rootObs = obsMap.get(studentDto.getObsid());
        List<String> obsChildrenList = new ArrayList<>();
        obsChildrenList.add(studentDto.getObsid());
        if(rootObs!=null)obsChildrenList.addAll(getObsChildren(rootObs,obsMap));
        List<StudentVO> studentVOFinalList = new ArrayList<>();
        for (String obsId : obsChildrenList) {
            // 去学生表里查学生
            LambdaQueryWrapper<SmStudent> studentWrapper = new LambdaQueryWrapper<>();
            studentWrapper.eq(SmStudent::getObsid,obsId);
            // 分别选择性根据班级，专业进行过滤
            studentWrapper.eq(!isEmpty(studentDto.getClassno()),SmStudent::getClassno,studentDto.getClassno());
            studentWrapper.eq(!isEmpty(studentDto.getProname()),SmStudent::getProname,studentDto.getProname());

            List<SmStudent> smStudentList =smStudentService.list(studentWrapper);
            // 获取学生姓名
            List<StudentVO> studentVOList = new ArrayList<>();
            smStudentList.stream().forEach(s->{
                StudentVO studentVO = new StudentVO();
                BeanUtils.copyProperties(s,studentVO);
                // 拿到Username
                String userName = stUsersService.getUsernameById(s.getUsersid());
                studentVO.setUsername(userName);
                // 拿到班级
                String obsName = cmClassStudentMapper.getObsNameByUserId(s.getUsersid());
                studentVO.setObsname(obsName);
                studentVOList.add(studentVO);
            });
            studentVOFinalList.addAll(studentVOList);
        }
        return Result.success(studentVOFinalList);
    }

    @Override
    public List<CmClassroomStudent> importClassRoomStudentExcel(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0); // 假设 Excel 文件中只有一个工作表
            DataFormatter dataFormatter = new DataFormatter();
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // 跳过标题行

            List<CmClassroomStudent> classroomStudentList = new ArrayList<>();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String classRoomId = dataFormatter.formatCellValue(row.getCell(1));
                String UserId = dataFormatter.formatCellValue(row.getCell(2));
                String obsId = dataFormatter.formatCellValue(row.getCell(3));
                String username = dataFormatter.formatCellValue(row.getCell(4));
                String obsname = dataFormatter.formatCellValue(row.getCell(5));
                String proname = dataFormatter.formatCellValue(row.getCell(6));
                String loginname = dataFormatter.formatCellValue(row.getCell(7));

                CmClassroomStudent classroomStudent = new CmClassroomStudent(CommonFunctions.generateEnhancedID(username+UserId),classRoomId,UserId,obsId,username,obsname,proname,loginname,0,0);
                classroomStudentList.add(classroomStudent);
            }
            return classroomStudentList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 递归输出rootobsid下的所有ChildrenObsid
     * @param parentSmObs
     * @param ObsMap
     * @return
     */
    public static List<String> getObsChildren(List<SmObs> parentSmObs, Map<String, List<SmObs>>  ObsMap){
        List<String> obsChildren = parentSmObs.stream()
                .map(SmObs::getId)
                .collect(Collectors.toList());
        for(SmObs parentObs :parentSmObs){
            List<SmObs> childObs =  ObsMap.get(parentObs.getId());
            if (childObs != null) {
                obsChildren.addAll(getObsChildren(childObs, ObsMap));
            }
        }
        return obsChildren;
    }

    public static boolean isEmpty(String o){
        return o == null || "".equals(o);
    }
}
