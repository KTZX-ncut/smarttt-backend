package com.example.smartttadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    private String id;
    private String username;
    private String roleid;
    private String obsid;
    private long obsdeep;
}
