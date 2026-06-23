package com.example.smartttexam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/** 分页查询结果 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    private List<T> data;       // 当前页数据
    private long recordSize;    // 总记录数
    private int pageIndex;      // 当前页码
    private int pageSize;       // 每页大小
}
