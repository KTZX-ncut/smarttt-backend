package com.example.smartttevaluation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.smartttevaluation.dto.CmCourseAssessmentTable;
import com.example.smartttevaluation.pojo.*;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.mapper.CmCourseAssessmentMapper;
import com.example.smartttevaluation.service.CmCourseAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import org.apache.tika.Tika;

import static com.example.smartttevaluation.pojo.CommonFunctions.generateEnhancedID;

@Service
public class CmCourseAssessmentImpl implements CmCourseAssessmentService {
    @Autowired
    private CmCourseAssessmentMapper cmCourseAssessmentMapper;

    @Override
    public Result getAssessmentTable(String courseid) {
        CmCourseAssessmentTable cmCourseAssessmentTable = new CmCourseAssessmentTable(new ArrayList<>(), new ArrayList<>(), new JSONObject());
        //处理表头部分
        List<CmCheckitem> cmCourseCheckItems = cmCourseAssessmentMapper.getCourseCheckItem(courseid);
        //创建通过id的映射
        Map<String, CmCheckitem> t_map = new HashMap<>();
        for (CmCheckitem t_cmCourseCheckItem : cmCourseCheckItems) {
            t_map.put(t_cmCourseCheckItem.getId(), t_cmCourseCheckItem);
            t_cmCourseCheckItem.setChildren(new ArrayList<>());
        }
        for (CmCheckitem t_cmCourseCkeckItem : cmCourseCheckItems) {
            if (Objects.equals(t_cmCourseCkeckItem.getPid(), "0")) {
                //根节点
                //添加期末/平时到表头
                cmCourseAssessmentTable.getHead().add(t_cmCourseCkeckItem);
                //将对应比例添加至表
                cmCourseAssessmentTable.getPercent().put(t_cmCourseCkeckItem.getId(), t_cmCourseCkeckItem.getPercent());

            } else {
                //非根节点,找到父节点连接
                String t_pid = t_cmCourseCkeckItem.getPid();
                CmCheckitem p_cmCourseCheckItem = t_map.get(t_pid);

                p_cmCourseCheckItem.getChildren().add(t_cmCourseCkeckItem);
            }

        }

        //获取所有target
        List<CmCoursetarget> cmCourseTargets = cmCourseAssessmentMapper.getCourseTarget(courseid);

        //创建targerId到JSON的映射
        Map<String, JSONObject> targetMap = new HashMap<>();
        for (CmCoursetarget t_cmCourseTarget : cmCourseTargets) {
            //创建JSON对象
            JSONObject t_json = new JSONObject();
            //添加属性
            t_json.put("id", t_cmCourseTarget.getId());
            t_json.put("name", t_cmCourseTarget.getName());
            //将此JSON对象添加至映射，使用targetId访问
            targetMap.put(t_cmCourseTarget.getId(), t_json);
            //将此JSON添加至items列表
            cmCourseAssessmentTable.getItems().add(t_json);

        }
        //获取所有的CourseAssement
        List<CmCourseAssessment> cmCourseAssessment = cmCourseAssessmentMapper.selectCourseAssessment(courseid);
        //将CourseAssessment中的standardScore填入到相应的位置
        for (CmCourseAssessment t_cmCourseAssessment : cmCourseAssessment) {
            //获取对应的JSON对象
            JSONObject t_json = targetMap.get(t_cmCourseAssessment.getCoursetargetId());
            //若存在此JSON对象则添加JSON属性{"targetId","standardScore"}
            if (t_json != null) {
                t_json.put(t_cmCourseAssessment.getCheckitemId(), t_cmCourseAssessment.getStandardScore());
            }

        }

        return Result.success(cmCourseAssessmentTable);
    }

    public Result updateStandardScores(Map<String, Map<String, Integer>> jsonData, String obsid) {
        for (String key : jsonData.keySet()) {
            Map<String, Integer> innerMap = jsonData.get(key);
            for (Map.Entry<String, Integer> entry : innerMap.entrySet()) {
                CmCourseAssessment targetData = new CmCourseAssessment();
                targetData.setCourseid(obsid);
                targetData.setCoursetargetId(key);
                targetData.setCheckitemId(entry.getKey());
                targetData.setStandardScore(entry.getValue());

                if (targetData.getStandardScore() == 0) {
                    // standardScore改为0，删除对应条存储
                    cmCourseAssessmentMapper.deleteAssessment(targetData);
                } else if (cmCourseAssessmentMapper.selectAssessmentCount(targetData) == 0) {
                    //此项没有数值,需新建
                    targetData.setId(CommonFunctions.generateEnhancedID("cm_course_assessment"));
                    cmCourseAssessmentMapper.insertAssessment(targetData);
                } else {
                    cmCourseAssessmentMapper.updateAssessment(targetData);
                }
            }
        }
        return Result.success();
    }

    public Result updateAssessmentTable(Map<String, Object> data, String obsid) {
        // 对每个考核项的分数修改
        Map<String, Map<String, Integer>> items = (Map<String, Map<String, Integer>>) data.get("items");
        if (items != null) {
            for (String key : items.keySet()) {
                Map<String, Integer> innerMap = items.get(key);
                for (Map.Entry<String, Integer> entry : innerMap.entrySet()) {
                    CmCourseAssessment targetData = new CmCourseAssessment();
                    targetData.setCourseid(obsid);
                    targetData.setCoursetargetId(key);
                    targetData.setCheckitemId(entry.getKey());
                    targetData.setStandardScore(entry.getValue());

                    if (targetData.getStandardScore() == 0) {
                        // standardScore改为0，删除对应条存储
                        cmCourseAssessmentMapper.deleteAssessment(targetData);
                    } else if (cmCourseAssessmentMapper.selectAssessmentCount(targetData) == 0) {
                        //此项没有数值,需新建
                        targetData.setId(CommonFunctions.generateEnhancedID("cm_course_assessment"));
                        cmCourseAssessmentMapper.insertAssessment(targetData);
                    } else {
                        cmCourseAssessmentMapper.updateAssessment(targetData);
                    }
                }
            }
        }

        // 对总评占比修改
        Map<String, Integer> percent = (Map<String, Integer>) data.get("percent");
        if (percent != null) {
            for (Map.Entry<String, Integer> entry : percent.entrySet()) {
                CmCheckitem cmCheckItem = new CmCheckitem();
                cmCheckItem.setCourseid(obsid);
                cmCheckItem.setId(entry.getKey());
                cmCheckItem.setPercent(entry.getValue());
                cmCourseAssessmentMapper.updatePercent(cmCheckItem);
            }
        }
        return Result.success();
    }

    public Result getFiles(CmCheckitem cmCheckitem) {
        Tika tika = new Tika();
        List<CmCourseCheckitemFile> files = cmCourseAssessmentMapper.getFiles(cmCheckitem.getCourseid());
        files.forEach(file -> {
//            file.setMimeType(Files.probeContentType(Paths.get(file.getFileName())));
            file.setMimeType(tika.detect(file.getFileData()));
            file.setBase64FileData(Base64.getEncoder().encodeToString(file.getFileData()));
            file.setFileData(null);
        });
        List<String> associateFileIds = cmCourseAssessmentMapper.getAssociateFileIds(cmCheckitem.getId());
        Map<String, Object> res = new HashMap<>();
        res.put("files", files);
        res.put("associateFileIds", associateFileIds);
        return Result.success(res);
    }

    public Result uploadFile(CmCourseCheckitemFile cmCourseCheckitemFile) {
        String id = generateEnhancedID("cm_course_assessment_file");
        cmCourseCheckitemFile.setId(id);
        cmCourseCheckitemFile.setCreateTime(LocalDateTime.now());
        cmCourseAssessmentMapper.uploadFile(cmCourseCheckitemFile);
        return Result.success();
    }

    public Result getAssociateCheckitems(String fileId, String obsid) {
        List<CmCheckitem> checkitems = cmCourseAssessmentMapper.getAssociateCheckitems(fileId, obsid);
        return Result.success(checkitems);
    }

    public Result deleteFile(CmCourseCheckitemFile cmCourseCheckitemFile) {
        List<CmCheckitem> checkitems = cmCourseAssessmentMapper.getAssociateCheckitems(cmCourseCheckitemFile.getId(), cmCourseCheckitemFile.getObsid());
        if(!checkitems.isEmpty()) {
            return Result.error("请先取消其与所有考核项的关联");
        }
        cmCourseAssessmentMapper.deleteFile(cmCourseCheckitemFile);
        return Result.success();
    }

    public Result associate(Map<String, Object> data) {
        String checkitemId = (String) data.get("checkitemId");
        List<String> fileIds = (List<String>) data.get("fileIds");
        cmCourseAssessmentMapper.associate(checkitemId, fileIds);
        return Result.success();
    }

    public Result disassociate(Map<String, Object> data) {
        String checkitemId = (String) data.get("checkitemId");
        List<String> fileIds = (List<String>) data.get("fileIds");
        cmCourseAssessmentMapper.disassociate(checkitemId, fileIds);
        return Result.success();
    }
}
