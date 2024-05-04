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
public class ObsRPTree {
    private String id;
    private String pid;
    private long orderno;
    private long obsdeep;
    private String obsname;
    private String levelcode;
    private List<ResponsiblePerson> responsiblePerson;
    private List<ObsRPTree> children;
}
