package com.example.smartttadmin.dto;

import com.example.smartttadmin.pojo.StUsers;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfor {
    private String catelog;
    private String currentterm;
    private String username;
    private String rolename;
    private String homeurl;
    private String token;

}
