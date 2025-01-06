package com.example.smartttevaluation.service.impl;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.mapper.AttainmentEvaluationMapper;
import com.example.smartttevaluation.mapper.CmLinesMapper;
import com.example.smartttevaluation.pojo.CmCourse;
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
    @Autowired
    private AttainmentEvaluationMapper attainmentEvaluationMapper;
    /**
     *获取连线列表
     */
    @Override
    public Result getLines(String obsid) {
        // 判断是不是任课教师调用接口
        CmCourse course = attainmentEvaluationMapper.getCourseByCourseId(obsid);
        if (course == null) {
            obsid = attainmentEvaluationMapper.getCourseByClassroomId(obsid).getId();
        }
        return Result.success(cmLinesMapper.getLines(obsid));
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
    public Result deleteLinesByIds(List<String> ids) {
        cmLinesMapper.deleteLinesByIds(ids);
        return Result.success();
    }

}
