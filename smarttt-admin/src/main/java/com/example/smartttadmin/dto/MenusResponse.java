package com.example.smartttadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回生成侧边栏的菜单列表
 * （这个后续应该修改为返回层级格式的json）
 */
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
