package com.example.smartttevaluation.service.impl;

import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.mapper.CmGetabilityMapper;
import com.example.smartttevaluation.mapper.CmKeywordsMapper;
import com.example.smartttevaluation.mapper.CmKwadictMapper;
import com.example.smartttevaluation.pojo.CmKeywords;
import com.example.smartttevaluation.pojo.CmKwadict;
import com.example.smartttevaluation.pojo.CommonFunctions;
import com.example.smartttevaluation.service.CmAbilityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

import com.example.smartttevaluation.dto.*;
import com.example.smartttevaluation.mapper.CmAbilityMapper;
import com.example.smartttevaluation.pojo.CmAbility;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CmAbilityServiceImpl implements CmAbilityService {
    @Autowired
    private CmAbilityMapper cmAbilityMapper;
    @Autowired
    private CmKwadictMapper cmKwadictMapper;
    @Autowired
    private CmKeywordsMapper cmKeywordsMapper;
    @Autowired
    private CmGetabilityMapper cmGetabilityMapper;


    /**
     * 创建能力
     */
    @Override
    public Result createOneAbility(CmAbility cmAbility) {
        cmAbility.setId(CommonFunctions.generateEnhancedID("cm_ability"));
        //获取兄弟的最大值,然后+1就是orderno
        long orderNoMax = cmAbilityMapper.getCmAbilityListByPid(cmAbility.getPid())
                .stream().mapToLong(Long::valueOf).max().orElse(0);
        cmAbility.setOrderno(orderNoMax + 1);
        //cmAbility.setCreatetime(LocalDateTime.now().toString());
        List<CmAbility> cmAbilityList = cmAbilityMapper.getAllCmAbilityList();
        cmAbilityList.add(cmAbility);
        List<TreeStructure> treeStructureList = cmAbilityList.stream()
                .map(cmAbility_ex -> new TreeStructure(cmAbility_ex.getId(), cmAbility_ex.getPid(), cmAbility_ex.getOrderno()))
                .collect(Collectors.toList());
        cmAbility.setLevelcode(CommonFunctions.generateLevelCode(CommonFunctions.generateTreeStructureList(treeStructureList, cmAbility.getId())));
        cmAbilityMapper.createOneNewAbility(cmAbility);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @Override
    @Transactional
    public Result deleteAbilityByIDs(List<String> ids) {
        List<CmAbility> cmAbilityList = cmAbilityMapper.getCmAbilityByIDs(ids);
        if (cmAbilityList.size() < ids.size()) return Result.error(404, "批量删除能力出错");
        boolean isValid = true;
        for (String id : ids) {
            if (cmAbilityMapper.checkKWAByAbilityId(id) != 0) {
                isValid = false;
                break;
            }
        }
        if (!isValid) return Result.error("此或其下属能力有课程的kwa使用，禁止删除");
        for (String id : ids) {
            cmAbilityMapper.updateBrotherAbilityOrderNo(id);
            cmAbilityMapper.deleteAbilityByID(id);
//            cmAbilityMapper.deleteGetabilityById(id);
        }
        return Result.success();
    }

    @Override
    public Result updateOneAbility(CmAbility cmAbility) {
        cmAbilityMapper.updateAbility(cmAbility);

        // 获取与这个能力相关的所有kwa
        List<CmKwadict> kwas = cmAbilityMapper.getAllKwaByAbilityId(cmAbility.getId());
        for(CmKwadict kwa : kwas) {
            CmKeywords keyword = cmKeywordsMapper.getOneKeyword(kwa.getKeywordid());
            kwa.setName(keyword.getName() + "-" + cmAbility.getName());
        }
        // 更新有关的kwa
        kwas.forEach(kwa -> {
            cmKwadictMapper.updateKwadictByID(kwa);
        });
        return Result.success();
    }

    /**
     * 能力树
     */
    @Override
    public Result getAbilityTreeByProId(String proId) {

        List<CmAbilityTree> allAbilityTree = cmAbilityMapper.getAllCmAbilityTreeByProId(proId);

        //对能力链表进行流化，之后通过collect方法对这个流里面的数据进行操作，使用lombok减少了代码生成（CmAbilityTree）
        //
        Map<String, List<CmAbilityTree>> abilityMap = allAbilityTree.stream()
                .collect(
                        Collectors.groupingBy(CmAbilityTree::getPid,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> list.stream()
                                                .sorted(Comparator.comparingLong(CmAbilityTree::getOrderno))
                                                .collect(Collectors.toList())
                                )
                        ));
        List<CmAbilityTree> rootAbility = abilityMap.get("0"); // 根菜单的pid通常为0
        // 递归构建菜单树
        if(rootAbility != null){
            buildAbilityTree(rootAbility,  abilityMap);
        }
        return Result.success(rootAbility);
    }


    /**
     * 升级能力
     */
    @Override
    public Result upgradeOneAbilityByID(String id) {
        cmAbilityMapper.updateBrotherAbilityOrderNo(id);
        long orderNoMax = cmAbilityMapper.getCmAbilityListByPid(cmAbilityMapper.getPidByID(id)).stream().mapToLong(Long::valueOf).max().orElse(0);
        cmAbilityMapper.upgradeOneAbilityByID(id, orderNoMax);
        return Result.success();
    }

    /**
     *构建菜单树
     * @param parentCmAbility
     * @param AbilityMap
     */
    private void buildAbilityTree(List<CmAbilityTree> parentCmAbility, Map<String, List<CmAbilityTree>> AbilityMap) {
        for (CmAbilityTree parentAbility : parentCmAbility) {
            //找出pid为父菜单的孩子
            List<CmAbilityTree> childAbility = AbilityMap.get(parentAbility.getId());
            if (childAbility != null) {
                parentAbility.setChildren(childAbility);
                buildAbilityTree(childAbility, AbilityMap);
            }
        }
    }

    /**
     * 递归输出某能力的所有children
     */
    public static List<String> getAbilityChildren(List<CmAbility> parentCmAbility, Map<String, List<CmAbility>> AbilityMap) {
        List<String> abilityChildren = parentCmAbility.stream()
                .map(CmAbility::getId)
                .collect(Collectors.toList());
        for (CmAbility parentAbility : parentCmAbility) {
            List<CmAbility> childAbility = AbilityMap.get(parentAbility.getId());
            if (childAbility != null) {
                abilityChildren.addAll(getAbilityChildren(childAbility, AbilityMap));
            }
        }
        return abilityChildren;
    }

}
