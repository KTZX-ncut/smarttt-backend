package com.example.smartttcourse.dto;


import com.example.smartttcourse.pojo.StLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolInforReq {
    private String id;
    private String obsname;
    private String remark;
    private List<StLevel> stLevelList;
}
