package com.example.smartttevaluation.pojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmCourseCheckitemFile {
    /**
     * 考核项关联的作业实验
     */
    private String id;
    private String checkitemId;
    private String obsid;
    private String fileName;
    private byte[] fileData;
    private LocalDateTime createTime;
    private String base64FileData;
    private String mimeType;
}
