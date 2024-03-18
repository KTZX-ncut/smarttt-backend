package com.example.smartttadmin.dto;

import com.example.smartttadmin.pojo.CmClass;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClassResponse {
    private String id;
    private String obsname;
    private List<CmClass> cmClassList;
}
