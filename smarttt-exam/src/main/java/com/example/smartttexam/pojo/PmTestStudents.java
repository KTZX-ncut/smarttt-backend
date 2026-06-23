package com.example.smartttexam.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PmTestStudents {

    private String id;
    private String testId;
    private String userId;
    private String proId;
    private String userName;
    private String obsId;
    private String obsName;
    private String loginname;
    private long rowNo;
}
