package com.example.smartttcourse.controller;

import com.example.smartttcourse.Utils.AuthRequired;
import com.example.smartttcourse.dto.Result;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.service.CmCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import static com.example.smartttcourse.Utils.AuthorizationAspect.getTokenFromContext;

@RestController
@RequestMapping("/coursemangt/instructionalprogram")
public class InstructionalProgramController {
    @Autowired
    private CmCourseService cmCourseService;
    @Value("${file.upload-dir}")
    private String uploadDir;
    @GetMapping
    @AuthRequired(type = "admin",menu = "531500340-439363cf-9c16-4b9e-8840-64bb093cbbd3",isReadOnly = true)
    public Result getInstructionalProgram(HttpServletRequest request){
        Token token = getTokenFromContext();
        System.out.println(token.getObsid());
        return cmCourseService.getInstructionalProgram(token.getObsid());
    }
    @PostMapping("/upload")
    @AuthRequired(type = "admin",menu = "531500340-439363cf-9c16-4b9e-8840-64bb093cbbd3")
    public Result TeachingProgramFileUpload(@RequestParam("file") MultipartFile file , HttpServletRequest request) throws IOException {
        Token token = getTokenFromContext();
        return cmCourseService.uploadTeachingProgram(file,uploadDir,token.getObsid());
    }
    @GetMapping("/download/{fileName:.+}")
    @AuthRequired(type = "admin",menu = "531500340-439363cf-9c16-4b9e-8840-64bb093cbbd3",isReadOnly = true)
    public Result TeachingProgramFileDownLoad(@PathVariable String fileName, HttpServletRequest request){
        Token token = getTokenFromContext();
        return cmCourseService.downloadTeachingProgram(fileName,uploadDir+"/"+token.getObsid());
    }
}
