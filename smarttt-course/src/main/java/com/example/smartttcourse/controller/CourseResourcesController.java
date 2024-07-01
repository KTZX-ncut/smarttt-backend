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
@RequestMapping("/coursemangt/courseresources")
public class CourseResourcesController {
    @Autowired
    private FileMangtService fileMangtService;
    @Value("${file.upload-dir}")
    private String uploadDir;
    @GetMapping
    @AuthRequired(type = "admin",menu = "531500340-954722d2-76cf-4e64-bdf8-f11ef7ceef23",isReadOnly = true)
    public Result getInstructionalProgram(HttpServletRequest request){
        Token token = getTokenFromContext();
        return fileMangtService.getFileList(token.getObsid(),"courseresources");
    }
    @PostMapping("/upload")
    @AuthRequired(type = "admin",menu = "531500340-954722d2-76cf-4e64-bdf8-f11ef7ceef23")
    public Result TeachingProgramFileUpload(@RequestParam("file") MultipartFile file , HttpServletRequest request){
        Token token = getTokenFromContext();
        return fileMangtService.uploadfile(file,uploadDir,token.getObsid(),"courseresources");
    }
    @GetMapping("/download/{fileName:.+}")
    @AuthRequired(type = "admin", menu = "531500340-954722d2-76cf-4e64-bdf8-f11ef7ceef23",isReadOnly = true)
    public void downloadFile(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) {
        Token token = getTokenFromContext();
        fileMangtService.downloadFile(fileName, uploadDir+"/"+token.getObsid(), response);
    }
    @GetMapping("/delete/{fileName:.+}")
    @AuthRequired(type = "admin", menu = "531500340-954722d2-76cf-4e64-bdf8-f11ef7ceef23")
    public Result DeleteFile(@PathVariable String fileName, HttpServletRequest request) {
        Token token = getTokenFromContext();
        return fileMangtService.deleteOneFile(uploadDir+"/"+token.getObsid()+"courseresources"+fileName,token.getObsid(), "courseresources");
    }
}
