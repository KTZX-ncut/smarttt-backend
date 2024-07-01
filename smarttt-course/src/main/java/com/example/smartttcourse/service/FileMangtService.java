package com.example.smartttcourse.service;

import com.example.smartttcourse.dto.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface FileMangtService {
    Result downloadfile(String fileName, String s);

    Result uploadfile(MultipartFile file, String dir,String obsid, String type);

    Result getFileList(String obsid, String type);

    void downloadFile(String fileName, String obsid, HttpServletResponse response);
}
