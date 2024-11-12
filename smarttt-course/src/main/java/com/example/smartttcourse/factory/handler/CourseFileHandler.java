package com.example.smartttcourse.factory.handler;

import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.enums.CourseFileManageEnum;
import com.example.smartttcourse.exception.res.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 定义能力
 * @author lunSir
 * @create 2024-10-30 20:57
 */
public interface CourseFileHandler {

    /**
     * 处理上传文件类型的能力
     */
    CourseFileManageEnum isSupport();

    /**
     * 获得桶的能力
     */
    String getBucket(String params);

    /**
     * 获得文件的能力
     */
    String buildObjectName(String ...params);


    /**
     * 上传文件的能力
     * @param params ：obsid
     */
    void uploadFile(MultipartFile file,String bucketName , String objectName,String params);

    // 下载文件的能力
    InputStream downloadFile(String fileName);

    // 删除文件能力
    void deleteFile(String fileName);

    Result getFileList(Token token);

}
