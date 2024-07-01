package com.example.smartttcourse.service.impl;

import com.example.smartttcourse.dto.Result;
import com.example.smartttcourse.mapper.FileMangtMapper;
import com.example.smartttcourse.pojo.CmCourseFile;
import com.example.smartttcourse.service.FileMangtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.smartttcourse.Utils.CommonFunctions.generateEnhancedID;

@Service
public class FileMangtServiceImpl implements FileMangtService {

    @Autowired
    private FileMangtMapper fileMangtMapper;
    @Override
    public Result uploadfile(MultipartFile file, String uploadDir,String obsid, String type) {
        String fileName = "1111"+file.getOriginalFilename();
        CmCourseFile cmCourseFile = new CmCourseFile(generateEnhancedID("cm_course_file"),obsid,fileName,file.getSize(),type, LocalDateTime.now().toString(),null);
        try{
            Path directoryPath = Paths.get(uploadDir,obsid);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }
            Path filePath = Paths.get(uploadDir,obsid, fileName);
            Files.copy(file.getInputStream(), filePath);

        }catch (IOException e){
            return Result.error("上传失败");
        }
        fileMangtMapper.createNewFile(cmCourseFile);
        return Result.success(fileName);
    }

    @Override
    public Result getFileList(String obsid, String type) {
        List<CmCourseFile> cmCourseFileList = fileMangtMapper.getFileList(obsid,type);

        return Result.success(cmCourseFileList);
    }

    @Override
    public void downloadFile(String fileName, String uploadDir, HttpServletResponse response) {
        File file = new File(uploadDir +  "/" + fileName);
        if (file.exists()) {
            try {
                String encodedFileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + encodedFileName);
                response.setContentLength((int) file.length());

                try (InputStream inputStream = Files.newInputStream(file.toPath());
                     OutputStream outputStream = response.getOutputStream()) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    outputStream.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                try {
                    response.getWriter().write("Error writing file");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            try {
                response.getWriter().write("File not found");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public Result downloadfile(String fileName, String uploadDir) {
        Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
//        System.out.println(filePath);
        File file = filePath.toFile();
        if (!file.exists() || !file.isFile()) {
            return Result.error("找不到文件");
        }
        try (InputStream inputStream = Files.newInputStream(file.toPath());
             ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {

            byte[] byteBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(byteBuffer, 0, 1024)) != -1) {
                buffer.write(byteBuffer, 0, bytesRead);
            }
            byte[] fileContent = buffer.toByteArray();
            // 将文件内容封装在 Result 的 data 字段中
            return Result.success(fileContent);
        } catch (IOException e) {
            return Result.error(500, "Failed to download file");
        }
    }
}
