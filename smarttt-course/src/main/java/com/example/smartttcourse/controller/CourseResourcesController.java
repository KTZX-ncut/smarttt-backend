package com.example.smartttcourse.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import com.example.smartttcourse.Utils.AuthRequired;
import com.example.smartttcourse.enums.CourseFileManageEnum;
import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.factory.CourseFileFactory;
import com.example.smartttcourse.factory.handler.CourseFileHandler;
import com.example.smartttcourse.service.CmClassRoomService;
import com.example.smartttcourse.service.CmCourseService;
import com.example.smartttcourse.service.FileMangtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.minio.StatObjectResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import static com.example.smartttcourse.Utils.AuthorizationAspect.getTokenFromContext;

/**
 * 课程资源
 */
@RestController
@RequestMapping("/coursemangt/courseresources")
@Slf4j
@Api(tags = "8. 课程资源", description = "课程资源文件的查询、上传、下载和删除接口")
public class CourseResourcesController {
    @Resource
    private CourseFileFactory courseFileFactory;
    @Resource
    private CmCourseService courseService;

    @Resource
    private CmClassRoomService classroomService;
    @GetMapping
    @ApiOperation(value = "获取课程资源文件列表", notes = "查询当前课程或课堂上下文下的课程资源文件列表。")
    @AuthRequired(type = "admin",menu = "531500340-954722d2-76cf-4e64-bdf8-f11ef7ceef23",isReadOnly = true)
    public Result getInstructionalProgram(HttpServletRequest request){
        Token token = getTokenFromContext();
        CourseFileHandler handler = courseFileFactory.getHandler(CourseFileManageEnum.COURSE_RESOURCES);
        return handler.getFileList(token);
    }
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    @ApiOperation(value = "上传课程资源文件", notes = "向当前课程或课堂上下文上传课程资源文件。")
    @AuthRequired(type = "admin",menu = "531500340-954722d2-76cf-4e64-bdf8-f11ef7ceef23")
    public Result CourseResourceFileUpload(@ApiParam(value = "课程资源文件", required = true) @RequestParam("file") MultipartFile file , HttpServletRequest request){
        Token token = getTokenFromContext();
        String courseIdOrClassroomId = token.getObsid();
        String courseId = this.confirmCourseId(courseIdOrClassroomId);
        CourseFileHandler handler = courseFileFactory.getHandler(CourseFileManageEnum.COURSE_RESOURCES);
        String bucketName = handler.getBucket(courseId);
        String objectName = handler.buildObjectName(courseId, handler.isSupport().getFileName());
        // 文件上传
        handler.uploadFile(file,bucketName,objectName,courseId);
        return Result.success("上传成功！");
    }

    private String confirmCourseId(String courseIdOrClassroomId) {

        if (courseService.countByCourseId(courseIdOrClassroomId) > 0){
            return courseIdOrClassroomId;
        }
        if(classroomService.countByClassroomId(courseIdOrClassroomId) > 0){
            String courseId = classroomService.getCourseIdByClassroomId(courseIdOrClassroomId);
            if(StrUtil.isBlank(courseId)){
                throw new RuntimeException("非法请求：数据库中有脏数据！");
            }
            return courseId;
        }
        throw new RuntimeException("非法请求: obsid只能是课程id或者课堂id");

    }

    @GetMapping("/download/{fileName:.+}")
    @ApiOperation(value = "下载课程资源文件", notes = "按文件名下载课程资源文件，支持分片 Range 下载。")
    @AuthRequired(type = "admin", menu = "531500340-954722d2-76cf-4e64-bdf8-f11ef7ceef23",isReadOnly = true)
    public ResponseEntity<InputStreamResource> downloadFile(@ApiParam(value = "文件名", required = true, example = "course-resource.zip") @PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            CourseFileHandler handler = courseFileFactory.getHandler(CourseFileManageEnum.COURSE_RESOURCES);
            StatObjectResponse fileInfo = handler.getFileInfo(fileName);
            // 设置请求头
            HttpHeaders httpHeaders = new HttpHeaders();
            // 流式传输
            httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
            httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
            // 禁用缓存
            httpHeaders.add(HttpHeaders.CACHE_CONTROL, "no-cache");
            httpHeaders.add(HttpHeaders.ACCEPT_RANGES, "bytes");

            // 默认读取的文件字节区间
            Long start = 0L,end = fileInfo.size() - 1L;

            // 获取分片信息 Range: bytes=0-99
            String rangeInfo = request.getHeader(HttpHeaders.RANGE);
            // 处理 文件的开始和结束位置
            if (StrUtil.isNotBlank(rangeInfo)){
                String[] strArr = StrUtil.removeAll(rangeInfo, "bytes=").trim().split("-");
                if (ArrayUtil.length(strArr) == 1){
                    // Range: bytes=99-
                    start = Long.valueOf(strArr[0]);
                }
                if (ArrayUtil.length(strArr) == 2){
                    // Range: bytes=99-1000
                    start = Long.valueOf(strArr[0]);
                    end = Long.valueOf(strArr[1]) > fileInfo.size() - 1 ? fileInfo.size() - 1 : Long.valueOf(strArr[1]);
                }
            }
            Long len = end - start + 1;
            log.info("start:{},end:{},len:{}",start,end,len);
            String rangeContent = new StringBuffer("bytes=").append(start).append("-").append(end).append("/").append(len).toString();
            log.info("rangeContent:{}",rangeContent);
            // 当前分片文件的大小
            httpHeaders.add(HttpHeaders.CONTENT_LENGTH, len.toString());
            httpHeaders.add(HttpHeaders.CONTENT_RANGE, rangeContent);
            // 自定义头(整个大文件的大小)
            httpHeaders.add("File-Size", Long.valueOf(fileInfo.size()).toString());
            fileName = URLEncoder.encode(fileName,"utf-8");
            httpHeaders.add("File-Name", fileName);
            InputStream is = handler.downloadFile(fileName,start,len);
            return ResponseEntity.status(HttpStatus.HTTP_PARTIAL).headers(httpHeaders).body(new InputStreamResource(is));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }


    }
    @GetMapping("/delete/{fileName:.+}")
    @ApiOperation(value = "删除课程资源文件", notes = "按文件名删除课程资源文件。")
    @AuthRequired(type = "admin", menu = "531500340-954722d2-76cf-4e64-bdf8-f11ef7ceef23")
    public Result DeleteFile(@ApiParam(value = "文件名", required = true, example = "course-resource.zip") @PathVariable String fileName, HttpServletRequest request) {
        CourseFileHandler handler = courseFileFactory.getHandler(CourseFileManageEnum.COURSE_RESOURCES);
        handler.deleteFile(fileName);
        return Result.success("删除成功！");
    }
}
