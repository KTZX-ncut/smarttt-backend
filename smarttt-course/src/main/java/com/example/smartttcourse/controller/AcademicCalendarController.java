package com.example.smartttcourse.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import com.example.smartttcommon.utils.MinioUtil;
import com.example.smartttcourse.Utils.AuthRequired;
import com.example.smartttcourse.enums.CourseFileManageEnum;
import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.factory.CourseFileFactory;
import com.example.smartttcourse.factory.handler.CourseFileHandler;
import io.minio.StatObjectResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.example.smartttcourse.Utils.AuthorizationAspect.getTokenFromContext;

/**
 * 教学日历
 */
@RestController
@Slf4j
@RequestMapping("/coursemangt/classroommangt/academiccalendar")
public class AcademicCalendarController {
    @Resource
    private CourseFileFactory courseFileFactory;
    @Resource
    private MinioUtil minioUtil;

    @GetMapping
    @AuthRequired(type = "admin", menu = "531500340-58da609f-67ca-4ea4-acea-d1c5fb7ec20d", isReadOnly = true)
    public Result uploadLessonPlan(HttpServletRequest request) {
        Token token = getTokenFromContext();
        CourseFileHandler handler = courseFileFactory.getHandler(CourseFileManageEnum.ACADEMIC_CALENDAR);
        return handler.getFileList(token);
    }

    @PostMapping("/upload")
    @AuthRequired(type = "admin", menu = "531500340-58da609f-67ca-4ea4-acea-d1c5fb7ec20d")
    public Result academicCalendarFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Token token = getTokenFromContext();
        String classroomId = token.getObsid();
        CourseFileHandler handler = courseFileFactory.getHandler(CourseFileManageEnum.ACADEMIC_CALENDAR);
        String bucketName = handler.getBucket(classroomId);
        String objectName = handler.buildObjectName(classroomId, handler.isSupport().getFileName());
        // 文件上传
        handler.uploadFile(file, bucketName, objectName, classroomId);
        return Result.success("上传成功！");
    }

    @GetMapping("/download/{fileName:.+}")
    @AuthRequired(type = "admin", menu = "531500340-58da609f-67ca-4ea4-acea-d1c5fb7ec20d", isReadOnly = true)
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            CourseFileHandler handler = courseFileFactory.getHandler(CourseFileManageEnum.ACADEMIC_CALENDAR);
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
            log.error("下载文件失败！",e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/delete/{fileName:.+}")
    @AuthRequired(type = "admin", menu = "531500340-58da609f-67ca-4ea4-acea-d1c5fb7ec20d")
    public Result DeleteFile(@PathVariable String fileName, HttpServletRequest request) {
        CourseFileHandler handler = courseFileFactory.getHandler(CourseFileManageEnum.ACADEMIC_CALENDAR);
        handler.deleteFile(fileName);
        return Result.success("删除成功！");
    }


    @GetMapping("/download/part/{fileName}")
    public ResponseEntity<InputStreamResource> downloadPart(@PathVariable("fileName") String fileName, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        // 获取文件基本信息
        String buckName = "1653708435-3d2a2df2-73d9-423b-afc7-1b7ab4c1dfc3";
        String objectName = "1508003654-75677409-068c-40f7-82ab-1b74a3d6ed3f/teachingprogram/" + fileName;
        StatObjectResponse fileInfo = minioUtil.getFileInfo(buckName, objectName);
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
        InputStream is = minioUtil.getPartFile(buckName, objectName, start, len);
        return ResponseEntity.status(HttpStatus.HTTP_PARTIAL).headers(httpHeaders).body(new InputStreamResource(is));
    }
}
