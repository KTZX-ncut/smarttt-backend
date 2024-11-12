package com.example.smartttcourse.service;

import com.example.smartttcourse.exception.res.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface FileMangtService {
    String getFilePath(String ObsId, String type, Boolean isClassroom);

    Result getFileList(String obsid, String type);

    void downloadFile(String fileName, String obsid, HttpServletResponse response);

    Result deleteOneFile(String uploadDir,String fileName, String obsid, String Type);
}
