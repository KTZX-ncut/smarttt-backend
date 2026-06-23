package com.example.smartttexam.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmClassroomStudent {

    private String id;
    private String classroomId;
    private String userId;
    private String obsId;
    private String userName;
    private String obsName;
    private String proName;
    private String loginname;
    private long rowNo;
    private BigDecimal courseScore;
}
