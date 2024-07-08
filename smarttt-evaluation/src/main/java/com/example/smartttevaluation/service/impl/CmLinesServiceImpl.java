package com.example.smartttevaluation.service.impl;
import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.mapper.CmLinesMapper;
import com.example.smartttevaluation.pojo.CmLines;
import com.example.smartttevaluation.service.CmLinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.smartttevaluation.pojo.CommonFunctions.generateEnhancedID;

@Service
public class CmLinesServiceImpl implements CmLinesService {

    @Autowired
    private CmLinesMapper cmLinesMapper;
    /**
     *获取连线列表
     */
    @Override
    public Result getLines() {
        return Result.success(cmLinesMapper.getLines());
    }
    /**
     *创建连线
     */
    @Override
    public Result createLines(CmLines cmLines) {
        cmLines.setId(generateEnhancedID("cm_keywords"));
        cmLinesMapper.createLines(cmLines);
        return Result.success();
    }
    /**
     *删除连线
     */
    @Override
    public Result deleteLinesByID(List<String> ids) {
        cmLinesMapper.deleteLinesByIDs(ids);
        return Result.success();
    }

}
