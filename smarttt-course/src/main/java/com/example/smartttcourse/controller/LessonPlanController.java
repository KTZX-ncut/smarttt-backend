package com.example.smartttcourse.controller;

import com.example.smartttcourse.Utils.AuthRequired;
import com.example.smartttcourse.enums.CourseFileManageEnum;
import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.factory.CourseFileFactory;
import com.example.smartttcourse.factory.handler.CourseFileHandler;
import com.example.smartttcourse.service.FileMangtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.example.smartttcourse.Utils.AuthorizationAspect.getTokenFromContext;

/**
 * 课程教案
 */
@RestController
@RequestMapping("/coursemangt/classroommangt/lessonplan")
public class LessonPlanController {
    @Resource
    private CourseFileFactory courseFileFactory;
    @GetMapping
    @AuthRequired(type = "admin",menu = "531500340-188ff31e-ff6b-47eb-a6d6-1ed0b703c0b5",isReadOnly = true)
    public Result getLessonPlan(HttpServletRequest request){
        Token token = getTokenFromContext();
        CourseFileHandler handler = courseFileFactory.getHandler(CourseFileManageEnum.LESSON_PLAN);
        return handler.getFileList(token);
    }
    @PostMapping("/upload")
    @AuthRequired(type = "admin",menu = "531500340-188ff31e-ff6b-47eb-a6d6-1ed0b703c0b5")
    public Result FileUpload(@RequestParam("file") MultipartFile file , HttpServletRequest request){
        Token token = getTokenFromContext();
        String classroomId = token.getObsid();
        CourseFileHandler handler = courseFileFactory.getHandler(CourseFileManageEnum.LESSON_PLAN);
        String bucketName = handler.getBucket(classroomId);
        String objectName = handler.buildObjectName(classroomId, handler.isSupport().getFileName());
        // 文件上传
        handler.uploadFile(file,bucketName,objectName,classroomId);
        return Result.success("上传成功！");
    }
    @GetMapping("/download/{fileName:.+}")
    @AuthRequired(type = "admin", menu = "531500340-188ff31e-ff6b-47eb-a6d6-1ed0b703c0b5",isReadOnly = true)
    public void downloadFile(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CourseFileHandler handler = courseFileFactory.getHandler(CourseFileManageEnum.LESSON_PLAN);
        InputStream inputStream = handler.downloadFile(fileName);
        // 设置响应头
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader("Cache-Control", "no-cache");
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, len);
        }
        inputStream.close();
        outputStream.close();
    }

    /**
     * 删除接口
     */
    @GetMapping("/delete/{fileName:.+}")
    @AuthRequired(type = "admin", menu = "531500340-188ff31e-ff6b-47eb-a6d6-1ed0b703c0b5")
    public Result DeleteFile(@PathVariable String fileName, HttpServletRequest request) {
        CourseFileHandler handler = courseFileFactory.getHandler(CourseFileManageEnum.ACADEMIC_CALENDAR);
        handler.deleteFile(fileName);
        return Result.success("删除成功！");
    }

}
