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
import com.example.smartttcourse.service.CmCourseService;
import com.example.smartttcourse.service.impl.CourseFileService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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
 * @create 2024-11-01 20:46
 */
@Component
@Slf4j
public class CourseResourceHandler implements CourseFileHandler {
    @Resource
    private MinioUtil minioUtil;

    @Resource
    private CourseFileService courseFileService;

    @Resource
    private CmCourseService courseService;

    @Resource
    private CmClassRoomService classRoomService;

    @Override
    public CourseFileManageEnum isSupport() {
        return CourseFileManageEnum.COURSE_RESOURCES;
    }

    @Override
    public String getBucket(String params) {
        // params对应的是courseId
        String termId = courseService.getTermIdByCourseId(params);
        if (StrUtil.isBlank(termId)){
            throw new RuntimeException("非法请求：没有该课程！");
        }
        minioUtil.buildBucketIfNotExist(termId);
        return termId;
    }

    @Override
    public String buildObjectName(String... params) {
        String objectName = "";

        for (String item : params) {
            objectName += (item + File.separator);
        }
        log.info("CourseResourceHandler.buildObjectName.objectName:{}",objectName);
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
        courseFile.setType(CourseFileManageEnum.COURSE_RESOURCES.getFileName());
        courseFile.setObsid(params);
        courseFile.setBucketName(bucketName);
        courseFile.setObjectName(objectName);
        // 入库
        courseFileService.save(courseFile);
        // 打印日志
        if (log.isInfoEnabled()){
            log.info("CourseResourceHandler.uploadFile.courseFile:{}", JSONUtil.parse(courseFile));
        }
        String name = courseFile.getId()+ "." + suffix;
        log.info("------>name: {}",name);
        minioUtil.upload(file.getInputStream(),bucketName,objectName + name);
    }

    @Override
    public InputStream downloadFile(String fileName) {
        // 拿到文件id
        String fileId = FileUtil.getPrefix(fileName);
        CmCourseFile courseFile = courseFileService.getById(fileId);

        String bucketName = courseFile.getBucketName();
        String objectName = courseFile.getObjectName();
        return minioUtil.download(bucketName,objectName + fileName);
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
            LambdaQueryWrapper<CmCourseFile> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(CmCourseFile::getObsid,token.getObsid());
            wrapper.eq(CmCourseFile::getType,this.isSupport().getFileName());
            List<CmCourseFile> list = courseFileService.list(wrapper);
            return Result.success(list);
        }
        // 任课教师
        if (Objects.equals("任课教师",identity)){
            String courseId = classRoomService.getCourseIdByClassroomId(token.getObsid());
            LambdaQueryWrapper<CmCourseFile> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(CmCourseFile::getObsid,courseId);
            wrapper.eq(CmCourseFile::getType,this.isSupport().getFileName());
            List<CmCourseFile> list = courseFileService.list(wrapper);
            return Result.success(list);
        }
        return Result.error("非法请求");
    }
}
