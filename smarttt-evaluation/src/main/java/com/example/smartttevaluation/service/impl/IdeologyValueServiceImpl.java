package com.example.smartttevaluation.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.smartttevaluation.mapper.IdeologyValueMapper;
import com.example.smartttevaluation.pojo.IdeologyValue;
import com.example.smartttevaluation.service.IdeologyValueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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

    @Override
    public List<IdeologyValue> selectValueTypes(String courseId) {
        return ideologyValueMapper.selectValueTypes(courseId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> copyIdeologyValues(String pastCourseId, String currentCourseId) {
        ideologyValueMapper.deleteByCourseId(currentCourseId);

        List<IdeologyValue> pastList = ideologyValueMapper.selectAllNode(pastCourseId);
        if (pastList.isEmpty()) return new HashMap<>();

        Map<String, String> idMap = new HashMap<>();
        // 父id -> 子节点列表
        Map<String, List<IdeologyValue>> childrenMap = new HashMap<>();
        for (IdeologyValue v : pastList) {
            String parentId = v.getParentId() == null ? "ROOT" : v.getParentId();
            childrenMap.computeIfAbsent(parentId, k -> new ArrayList<>()).add(v);
        }

        List<IdeologyValue> toInsert = new ArrayList<>();
        Queue<IdeologyValue> queue = new LinkedList<>(childrenMap.getOrDefault("ROOT", new ArrayList<>()));
        while (!queue.isEmpty()) {
            IdeologyValue current = queue.poll();
            String oldId = current.getId();
            String newId = IdUtil.fastSimpleUUID();
            idMap.put(oldId, newId);

            IdeologyValue newNode = new IdeologyValue();
            newNode.setId(newId);
            newNode.setVname(current.getVname());
            newNode.setRemark(current.getRemark());
            newNode.setCourseId(currentCourseId);
            newNode.setLeaf(current.getLeaf());
            newNode.setLevel(current.getLevel());
            // parentId: 若有父节点，使用已映射的新 parentId
            if (current.getParentId() == null) {
                newNode.setParentId(null);
            } else {
                newNode.setParentId(idMap.get(current.getParentId()));
            }
            toInsert.add(newNode);

            List<IdeologyValue> children = childrenMap.get(oldId);
            if (children != null) queue.addAll(children);
        }

        if (!toInsert.isEmpty()) ideologyValueMapper.batchInsert(toInsert);
        return idMap;
    }
}
