package com.example.smartttadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenusResponse {
    private long id;
    private String name;
    private String pid;
    private String orderno;
    private String url;
}
