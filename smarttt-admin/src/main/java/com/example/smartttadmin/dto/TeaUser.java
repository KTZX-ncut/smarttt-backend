package com.example.smartttadmin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeaUser {
    private String catelog;
    private String currentterm;
    private String username;
    private String rolename;
    private String homeurl;
    private String token;
}
