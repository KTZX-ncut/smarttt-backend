package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmLines;

import java.util.List;

public interface CmLinesService {
    /**
     *获取连线列表
     */
    Result getLines(String obsid);
    /**
     *创建连线
     */
    Result createLines(CmLines cmLines);
    /**
     *删除连线
     */
    Result deleteLinesByID(List<String> ids);

}
