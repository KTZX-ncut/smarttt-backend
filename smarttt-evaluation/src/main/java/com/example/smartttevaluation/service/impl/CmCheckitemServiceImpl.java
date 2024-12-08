package com.example.smartttevaluation.service.impl;

import com.example.smartttevaluation.dto.Token;
import com.example.smartttevaluation.dto.CmCheckitemTree;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.dto.TreeStructure;
import com.example.smartttevaluation.mapper.CmCheckitemMapper;
import com.example.smartttevaluation.pojo.CmCheckitem;
import com.example.smartttevaluation.pojo.CommonFunctions;
import com.example.smartttevaluation.service.CmCheckitemService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CmCheckitemServiceImpl implements CmCheckitemService {

    @Autowired
    private CmCheckitemMapper cmCheckitemMapper;

    /**
     * 创建考核项
     */
    @Override
    public Result createOneCheckitem(CmCheckitem cmCheckitem, String courseid) {
        cmCheckitem.setId(CommonFunctions.generateEnhancedID("cm_checkitem"));
        //获取兄弟的最大值,然后+1就是orderno
        long orderNoMax = cmCheckitemMapper.getCmCheckitemListByPid(cmCheckitem.getPid()).stream().mapToLong(Long::valueOf).max().orElse(0);
        cmCheckitem.setOrderno(orderNoMax + 1);
        List<CmCheckitem> cmCheckitemList = cmCheckitemMapper.getAllCmCheckitemList(courseid);
        cmCheckitemList.add(cmCheckitem);
        List<TreeStructure> treeStructureList = cmCheckitemList.stream()
                .map(cmCheckitem_ex -> new TreeStructure(cmCheckitem_ex.getId(), cmCheckitem_ex.getPid(), cmCheckitem_ex.getOrderno()))
                .collect(Collectors.toList());
        cmCheckitem.setItemCode(CommonFunctions.generateLevelCode(CommonFunctions.generateTreeStructureList(treeStructureList, cmCheckitem.getId())));
        cmCheckitem.setCourseid(courseid);
        cmCheckitemMapper.createOneNewCheckitem(cmCheckitem);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @Override
    public Result deleteCheckitemByIDs(List<String> ids, String obsId) {
        List<CmCheckitem> cmCheckitemList = cmCheckitemMapper.getCmCheckitemByIDs(ids);
        if (cmCheckitemList.size() < ids.size()) return Result.error(404, "批量删除出错");

        for (String id : ids) {
            cmCheckitemMapper.updateBrotherCheckitemOrderNo(id);
            cmCheckitemMapper.deleteCheckitemByID(id);
            cmCheckitemMapper.deleteCheckitemStandardScore(id);
        }
        return Result.success();
    }

    /**
     * 考核项列表
     */
    @Override
    public Result getCheckitemList(Token token) {
        List<CmCheckitemTree> allCheckitemTree = cmCheckitemMapper.getAllCmCheckitemTree(token.getObsid());
        Map<String, List<CmCheckitemTree>> checkitemMap = allCheckitemTree.stream()
                .collect(Collectors.groupingBy(CmCheckitemTree::getPid,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingLong(CmCheckitemTree::getOrderno))
                                        .collect(Collectors.toList())
                        )
                ));
        List<CmCheckitemTree> rootCheckitem = checkitemMap.get("0"); // 根菜单的pid通常为0
        if (rootCheckitem != null) {
            // 递归构建菜单树
            buildCheckitemTree(rootCheckitem, checkitemMap);
            for (CmCheckitemTree checkitemTree : rootCheckitem) {
                setDeep(checkitemTree, 0);
            }
        }
        return Result.success(rootCheckitem);
    }

    void setDeep(CmCheckitemTree item, int deep) {
        if (item.getTask() == null) {
            item.setTask(false);
        }
        item.setCheckitemdeep(deep);
        if (item.getChildren() != null && !item.getChildren().isEmpty()) {
            for (CmCheckitemTree child : item.getChildren()) {
                setDeep(child, deep + 1);
            }
        }
    }

    /**
     * 升级
     */
    @Override
    public Result upgradeOneCheckitemByID(String id) {
        cmCheckitemMapper.updateBrotherCheckitemOrderNo(id);
        long orderNoMax = cmCheckitemMapper.getCmCheckitemListByPid(cmCheckitemMapper.getPidByID(id)).stream().mapToLong(Long::valueOf).max().orElse(0);
        cmCheckitemMapper.upgradeOneCheckitemByID(id, orderNoMax);
        return Result.success();
    }

    /**
     * 考核项树
     *
     * @param parentCmCheckitem
     * @param CheckitemMap
     */
    private void buildCheckitemTree(List<CmCheckitemTree> parentCmCheckitem, Map<String, List<CmCheckitemTree>> CheckitemMap) {
        for (CmCheckitemTree parentCheckitem : parentCmCheckitem) {
            //找出pid为父菜单的孩子
            List<CmCheckitemTree> childCheckitem = CheckitemMap.get(parentCheckitem.getId());
            if (childCheckitem != null) {
                parentCheckitem.setChildren(childCheckitem);
                buildCheckitemTree(childCheckitem, CheckitemMap);
            }
        }
    }

    /**
     * 递归输出所有children
     */
    public static List<String> getCheckitemChildren(List<CmCheckitem> parentCmCheckitem, Map<String, List<CmCheckitem>> CheckitemMap) {
        List<String> checkitemChildren = parentCmCheckitem.stream()
                .map(CmCheckitem::getId)
                .collect(Collectors.toList());
        for (CmCheckitem parentCheckitem : parentCmCheckitem) {
            List<CmCheckitem> childCheckitem = CheckitemMap.get(parentCheckitem.getId());
            if (childCheckitem != null) {
                checkitemChildren.addAll(getCheckitemChildren(childCheckitem, CheckitemMap));
            }
        }
        return checkitemChildren;
    }

    @Override
    public Result changeCheckitemTaskTrue(String id) {
        cmCheckitemMapper.changeCheckitemTask(id, "true");
        String pid = cmCheckitemMapper.getPidByID(id);
        while (!pid.equals("0")) {
            cmCheckitemMapper.changeCheckitemTask(pid, "true");
            pid = cmCheckitemMapper.getPidByID(pid);
        }
        return Result.success();
    }

    @Override
    public Result changeCheckitemTaskFalse(List<String> ids) {
        for (String id : ids) {
            cmCheckitemMapper.changeCheckitemTask(id, "false");
        }
        return Result.success();
    }

    @Override
    public Result changeCheckitemName(String id, String name) {
        cmCheckitemMapper.changeCheckitemName(id, name);
        return Result.success();
    }

    @Override
    public Result changeCheckitemRemark(String id, String remark) {
        cmCheckitemMapper.changeCheckitemRemark(id, remark);
        return Result.success();
    }
}
