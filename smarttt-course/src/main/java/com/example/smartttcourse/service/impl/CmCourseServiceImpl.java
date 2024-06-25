package com.example.smartttcourse.service.impl;


import com.example.smartttcourse.dto.SimpleCourse;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.mapper.CmTermMapper;
import com.example.smartttcourse.mapper.StUsersMapper;
import com.example.smartttcourse.pojo.CmCourse;
import com.example.smartttcourse.pojo.StRoleUser;
import com.example.smartttcourse.service.CmCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.smartttcourse.dto.Result;
import com.example.smartttcourse.mapper.CmCourseMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import static com.example.smartttcourse.Utils.CommonFunctions.generateEnhancedID;

@Service
public class CmCourseServiceImpl implements CmCourseService {

    @Autowired
    private CmCourseMapper cmCourseMapper;
    @Autowired
    private CmTermMapper cmTermMapper;
    @Autowired
    private StUsersMapper stUsersMapper;

    @Override
    public Result getCourse(Token token) {
        List<SimpleCourse> simpleCourseList = cmCourseMapper.getCourse(token);
        try{
            for(SimpleCourse simpleCourse:simpleCourseList){
                simpleCourse.setTermname(cmCourseMapper.getTermName(simpleCourse.getSchooltermId()));
                simpleCourse.setResponsiblePersonList(cmCourseMapper.getCourseRP(simpleCourse.getId()));
            }
        }catch (NullPointerException e){
            return Result.error("获取失败");
        }
        return Result.success(simpleCourseList);
    }

    @Override
    public Result createCourse(CmCourse cmCourse) {
        cmCourse.setId(generateEnhancedID("cm_course"));
        cmCourse.setProfessionName(cmCourseMapper.getObsName(cmCourse.getProfessionId()));
        cmCourseMapper.createCourse(cmCourse);
        return Result.success(cmCourse.getId());
    }

    @Override
    public Result deleteCourseByID(List<String> ids) {
        cmCourseMapper.deleteCourseByID(ids);
        return Result.success();
    }

    @Override
    public Result historyCourseByTerm(String termID, String obsid) {
        return Result.success(cmCourseMapper.historyCourseByTerm(termID,obsid));
    }

    @Override
    public Result copyHistoryCourse(String id) {
        CmCourse cmCourse = cmCourseMapper.getCopyCourse(id);
        cmCourse.setId(generateEnhancedID("cm_course"));
        cmCourse.setSchooltermId(cmTermMapper.getCurrentTerm());
        cmCourseMapper.createCourse(cmCourse);
        List<StRoleUser> roleUserList = cmCourseMapper.getHistoryRP(id);
        for(StRoleUser stRoleUser: roleUserList){
//            stRoleUser.setRoleid("516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21");
            stRoleUser.setId(generateEnhancedID("st_roleuser"));
//            stRoleUser.setObsdeep(-1);
            stRoleUser.setCreatetime(LocalDate.now().toString());
            stUsersMapper.createOneRoleUser(stRoleUser);
        }
        return Result.success();
    }

    @Override
    public Result updateOneCourse(CmCourse cmCourse) {
        cmCourseMapper.updateOneCourse(cmCourse);
        return Result.success();
    }

    @Override
    public Result uploadTeachingProgram(MultipartFile file, String uploadDir, String obsid) {
        try{
            String fileName = "1111"+file.getOriginalFilename();
            Path directoryPath = Paths.get(uploadDir, obsid);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }
            Path filePath = Paths.get(uploadDir, obsid+"/"+fileName);
            Files.copy(file.getInputStream(), filePath);
            cmCourseMapper.uploadTeachingProgram(fileName,obsid);

        }catch (IOException e){
            return Result.error("上传失败");
        }
        return Result.success();
    }

    @Override
    public Result downloadTeachingProgram(String fileName, String uploadDir) {
        Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
//        System.out.println(filePath);
        File file = filePath.toFile();
        if (!file.exists() || !file.isFile()) {
            return Result.error("找不到文件");
        }
        try (InputStream inputStream = Files.newInputStream(file.toPath());
             ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {

            byte[] byteBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(byteBuffer, 0, 1024)) != -1) {
                buffer.write(byteBuffer, 0, bytesRead);
            }
            byte[] fileContent = buffer.toByteArray();
            // 将文件内容封装在 Result 的 data 字段中
            return Result.success(fileContent);
        } catch (IOException e) {
            return Result.error(500, "Failed to download file");
        }
    }

    @Override
    public Result getInstructionalProgram(String obsid) {
        return Result.success(cmCourseMapper.getInstructionalProgram(obsid));
    }

}

