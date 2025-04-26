package com.example.smartttcourse.factory.handler;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.smartttcommon.utils.MinioUtil;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.enums.CourseFileManageEnum;
import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.pojo.CmCourseFile;
import com.example.smartttcourse.service.CmClassRoomService;
import com.example.smartttcourse.service.impl.CourseFileService;
import io.minio.StatObjectResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author lunSir
 * @create 2024-11-01 20:41
 */
@Component
@Slf4j
public class AcademicCalendarHandler implements CourseFileHandler{
    @Resource
    private MinioUtil minioUtil;

    @Resource
    private CourseFileService courseFileService;

    @Resource
    private CmClassRoomService classRoomService;

    @Override
    public CourseFileManageEnum isSupport() {
        return CourseFileManageEnum.ACADEMIC_CALENDAR;
    }

    @Override
    public String getBucket(String params) {
        // params对应的是classroomId
        String termId = classRoomService.getTermIdByClassroomId(params);
        log.info("AcademicCalendarHandler.getBucket.termId:{}",termId);
        if (StrUtil.isBlank(termId)){
            throw new RuntimeException("非法请求：没有该课程！");
        }
        minioUtil.buildBucketIfNotExist(termId);
        return termId;
    }

    @Override
    public String buildObjectName(String... params) {
        // params: {classroomid,CoursePlan}
        // courseId.classroomId
        String classroomId = params[0];
        String coursePlan = params[1];
        // 拿到courseId
        String courseId = classRoomService.getCourseIdByClassroomId(classroomId);
        String objectName = courseId + "/" + classroomId + "/" + coursePlan + "/";
        log.info("AcademicCalendarHandler.buildObjectName.objectName:{}",objectName);
        return objectName;
    }

    @Override
    @SneakyThrows
    public void uploadFile(MultipartFile file, String bucketName , String objectName, String params) {
        // 组装数据，插入数据库
        String fileName = file.getOriginalFilename();
        // 获取文件后缀
        String suffix = FileUtil.getSuffix(fileName);
        CmCourseFile courseFile = new CmCourseFile();
        courseFile.setCreatetime(LocalDateTime.now().toString());
        courseFile.setFilename(fileName);
        courseFile.setSize(file.getSize());
        courseFile.setType(this.isSupport().getFileName());
        courseFile.setObsid(params);
        courseFile.setBucketName(bucketName);
        courseFile.setObjectName(objectName);
        // 入库
        courseFileService.save(courseFile);
        // 打印日志
        if (log.isInfoEnabled()){
            log.info("AcademicCalendarHandler.uploadFile.courseFile:{}", JSONUtil.parse(courseFile));
        }
        String name = courseFile.getId()+ "." + suffix;
        log.info("------>name: {}",name);
        minioUtil.upload(file.getInputStream(),bucketName,objectName + name,file.getSize());
    }

    @Override
    public InputStream downloadFile(String fileName,Long start,Long len) {
        // 拿到文件id
        String fileId = FileUtil.getPrefix(fileName);
        CmCourseFile courseFile = courseFileService.getById(fileId);
        String bucketName = courseFile.getBucketName();
        String objectName = courseFile.getObjectName();
        return minioUtil.getPartFile(bucketName,objectName + fileName,start,len);
    }

    @Override
    public void deleteFile(String fileName) {
        // 拿到文件id
        String fileId = FileUtil.getPrefix(fileName);
        CmCourseFile courseFile = courseFileService.getById(fileId);

        String bucketName = courseFile.getBucketName();
        String objectName = courseFile.getObjectName();
        courseFileService.removeById(fileId);
        minioUtil.deleteObject(bucketName,objectName + fileName);
    }

    @Override
    public Result getFileList(Token token) {
        // 判断请求人
        String identity = courseFileService.getUserIdentity(token.getRoleid());
        if (Objects.equals("课程负责人",identity)){
            // 根据courseId找到所有classroomId
            List<String> classRoomIdList =  classRoomService.getClassroomIdByCourseId(token.getObsid());
            if (Objects.isNull(classRoomIdList) || classRoomIdList.isEmpty()){
                return Result.success();
            }
            LambdaQueryWrapper<CmCourseFile> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(CmCourseFile::getObsid,classRoomIdList);
            wrapper.eq(CmCourseFile::getType,this.isSupport().getFileName());
            List<CmCourseFile> list = courseFileService.list(wrapper);
            return Result.success(list);
        }
        // 任课教师
        if (Objects.equals("任课教师",identity) || Objects.equals("实验教师",identity)|| Objects.equals("学生",identity)){
            LambdaQueryWrapper<CmCourseFile> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(CmCourseFile::getObsid,token.getObsid());
            wrapper.eq(CmCourseFile::getType,this.isSupport().getFileName());
            List<CmCourseFile> list = courseFileService.list(wrapper);
            return Result.success(list);
        }
        return Result.error("非法请求");
    }

    @Override
    public StatObjectResponse getFileInfo(String fileName) {
        String fileId = FileUtil.getPrefix(fileName);
        CmCourseFile courseFile = courseFileService.getById(fileId);
        String bucketName = courseFile.getBucketName();
        String objectName = courseFile.getObjectName();
        return minioUtil.getFileInfo(bucketName,objectName + fileName);
    }
}
