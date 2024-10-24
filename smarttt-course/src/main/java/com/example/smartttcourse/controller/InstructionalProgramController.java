package com.example.smartttcourse.controller;

import com.example.smartttcommon.utils.MinioUtil;
import com.example.smartttcourse.Utils.AuthRequired;
import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.service.CmCourseService;
import com.example.smartttcourse.service.FileMangtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import static com.example.smartttcourse.Utils.AuthorizationAspect.getTokenFromContext;

/**
 * 教学大纲
 */
@RestController
@RequestMapping("/coursemangt/instructionalprogram")
public class InstructionalProgramController {
    @Autowired
    private CmCourseService cmCourseService;
    @Autowired
    private FileMangtService fileMangtService;

    @Resource
    private MinioUtil minioUtil;
    @Value("${file.upload-dir}")
    private String uploadDir;
    @GetMapping
    @AuthRequired(type = "admin",menu = "531500340-439363cf-9c16-4b9e-8840-64bb093cbbd3",isReadOnly = true)
    public Result getInstructionalProgram(HttpServletRequest request){
        Token token = getTokenFromContext();
        return fileMangtService.getFileList(token.getObsid(),"teachingprogram");
    }
    @PostMapping("/upload")
    @AuthRequired(type = "admin",menu = "531500340-439363cf-9c16-4b9e-8840-64bb093cbbd3")
    public Result TeachingProgramFileUpload(@RequestParam("file") MultipartFile file , HttpServletRequest request){
        Token token = getTokenFromContext();
        String path = fileMangtService.getFilePath(token.getObsid(),"teachingprogram",false);
        return fileMangtService.uploadfile(file,path);
    }
    @GetMapping("/download/{fileName:.+}")
    @AuthRequired(type = "admin", menu = "531500340-439363cf-9c16-4b9e-8840-64bb093cbbd3",isReadOnly = true)
    public Result downloadFile(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Token token = getTokenFromContext();
        // TODO: 测试
        // 拿到流
        InputStream inputStream = minioUtil.download("test", fileName);
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, len);
        }
        inputStream.close();
        String url = minioUtil.getUrl("test",fileName);
        return Result.success(url);
        //fileMangtService.downloadFile(fileName, uploadDir+"/"+token.getObsid()+"/"+"teachingprogram", response);

    }
    @GetMapping("/delete/{fileName:.+}")
    @AuthRequired(type = "admin", menu = "531500340-439363cf-9c16-4b9e-8840-64bb093cbbd3")
    public Result DeleteFile(@PathVariable String fileName, HttpServletRequest request) {
        Token token = getTokenFromContext();
        return fileMangtService.deleteOneFile(uploadDir+"/"+token.getObsid()+"/teachingprogram/"+fileName,fileName,token.getObsid(), "teachingprogram");
    }

}
