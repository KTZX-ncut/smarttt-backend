package com.example.smartttcourse.controller;

import com.example.smartttcourse.Utils.AuthRequired;
import com.example.smartttcourse.dto.Result;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.service.FileMangtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.smartttcourse.Utils.AuthorizationAspect.getTokenFromContext;

@RestController
@RequestMapping("/coursemangt/classroommangt/academiccalendar")
public class AcademicCalendarController {
    @Autowired
    private FileMangtService fileMangtService;
    @Value("${file.upload-dir}")
    private String uploadDir;
    @GetMapping
    @AuthRequired(type = "admin",menu = "531500340-58da609f-67ca-4ea4-acea-d1c5fb7ec20d",isReadOnly = true)
    public Result uploadLessonPlan(HttpServletRequest request){
        Token token = getTokenFromContext();
        return fileMangtService.getFileList(token.getObsid(), "academiccalendar");
    }
    @PostMapping("/upload")
    @AuthRequired(type = "admin",menu = "531500340-58da609f-67ca-4ea4-acea-d1c5fb7ec20d")
    public Result TeachingProgramFileUpload(@RequestParam("file") MultipartFile file , HttpServletRequest request){
        Token token = getTokenFromContext();
        return fileMangtService.uploadfile(file,uploadDir, token.getObsid(), "academiccalendar");
    }
    @GetMapping("/download/{fileName:.+}")
    @AuthRequired(type = "admin", menu = "531500340-58da609f-67ca-4ea4-acea-d1c5fb7ec20d",isReadOnly = true)
    public void downloadFile(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) {
        Token token = getTokenFromContext(); // 获取token信息
        fileMangtService.downloadFile(fileName, uploadDir+"/"+token.getObsid()+"/"+"academiccalendar", response);
    }
    @GetMapping("/delete/{fileName:.+}")
    @AuthRequired(type = "admin", menu = "531500340-58da609f-67ca-4ea4-acea-d1c5fb7ec20d")
    public Result DeleteFile(@PathVariable String fileName, HttpServletRequest request) {
        Token token = getTokenFromContext();
        return fileMangtService.deleteOneFile(uploadDir+"/"+token.getObsid()+"/"+"academiccalendar"+"/"+fileName,token.getObsid(), "academiccalendar");
    }
}
