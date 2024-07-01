package com.example.smartttcourse.controller;

import com.example.smartttcourse.Utils.AuthRequired;
import com.example.smartttcourse.dto.Result;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.service.CmCourseService;
import com.example.smartttcourse.service.FileMangtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.smartttcourse.Utils.AuthorizationAspect.getTokenFromContext;

@RestController
@RequestMapping("/coursemangt/instructionalprogram")
public class InstructionalProgramController {
    @Autowired
    private CmCourseService cmCourseService;
    @Autowired
    private FileMangtService fileMangtService;
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
        return fileMangtService.uploadfile(file,uploadDir,token.getObsid(),"teachingprogram");
    }
    @GetMapping("/download/{fileName:.+}")
    @AuthRequired(type = "admin", menu = "531500340-439363cf-9c16-4b9e-8840-64bb093cbbd3",isReadOnly = true)
    public void downloadFile(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) {
        Token token = getTokenFromContext();
        fileMangtService.downloadFile(fileName, uploadDir+"/"+token.getObsid(), response);
    }
    @GetMapping("/delete/{fileName:.+}")
    @AuthRequired(type = "admin", menu = "531500340-439363cf-9c16-4b9e-8840-64bb093cbbd3")
    public Result DeleteFile(@PathVariable String fileName, HttpServletRequest request) {
        Token token = getTokenFromContext();
        return fileMangtService.deleteOneFile(uploadDir+"/"+token.getObsid()+fileName,token.getObsid(), "teachingprogram");
    }

}
