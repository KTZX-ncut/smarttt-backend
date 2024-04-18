package com.example.smartttcourse.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleCourse {
    private String id;
    private String courseChineseName;
    private String courseEnglishName;
    private String schooltermId;
    private String professionId;
    private String professionName;
    private String termname;
    private String courseCode;
    private List<ResponsiblePerson> responsiblePersonList;
}
