package com.example.smartttevaluation.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.smartttevaluation.mapper.IdeologyValueMapper;
import com.example.smartttevaluation.pojo.IdeologyValue;
import com.example.smartttevaluation.service.IdeologyValueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IdeologyValueServiceImpl implements IdeologyValueService {

    private final IdeologyValueMapper ideologyValueMapper;
    @Override
    public void createFirstLevelNode(String courseId) {
        String id = IdWorker.getIdStr();
        ideologyValueMapper.createFirstLevelNode(id,courseId);
    }

    @Override
    public void modifyValueName(IdeologyValue ideologyValue) {
        if (StrUtil.isBlank(ideologyValue.getVname())) return;
        ideologyValueMapper.modifyValueName(ideologyValue);
    }

    @Override
    public void addSameLevelNode(IdeologyValue ideologyValue) {
        String id = IdWorker.getIdStr();
        ideologyValueMapper.addSameLevelNode(id,ideologyValue);
    }

    @Override
    public void addSubLevelNode(IdeologyValue ideologyValue) {
        String id = IdWorker.getIdStr();
        Integer level = ideologyValue.getLevel();
        ideologyValue.setLevel(level+1);
        ideologyValueMapper.addSubLevelNode(id,ideologyValue);
    }


    @Override
    public void deleteNode(String id) {
        ideologyValueMapper.deleteNode(id);
    }

    @Override
    public List<IdeologyValue> selectAllNode(String courseId) {
        return ideologyValueMapper.selectAllNode(courseId);
    }

    @Override
    public IdeologyValue selectById(String id) {
        return ideologyValueMapper.selectById(id);
    }

}
