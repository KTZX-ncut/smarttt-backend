package com.example.smartttcourse.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lunSir
 * @create 2024-09-08 19:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentTree {
    private String id;
    private String pid;
    private long orderno;
    private long obsdeep;
    private String obsname;
    private String levelcode;
    List<StudentDto> studentDtos;
    private List<StudentTree> children;
}
