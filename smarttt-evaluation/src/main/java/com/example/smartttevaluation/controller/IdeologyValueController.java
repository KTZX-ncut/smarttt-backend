package com.example.smartttevaluation.controller;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.example.smartttevaluation.Utils.AuthRequired;
import com.example.smartttevaluation.dto.Token;
import com.example.smartttevaluation.exception.res.ResponseEnum;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.exception.utils.SmartAssert;
import com.example.smartttevaluation.mapper.CmCourseUnitVValueMapper;
import com.example.smartttevaluation.mapper.CmKnowledgeUnitMapper;
import com.example.smartttevaluation.pojo.CmCourseUnitVValue;
import com.example.smartttevaluation.pojo.CmKnowledgeUnit;
import com.example.smartttevaluation.pojo.IdeologyValue;
import com.example.smartttevaluation.service.IdeologyValueService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.smartttevaluation.Utils.AuthorizationAspect.getTokenFromContext;

@RestController
@RequiredArgsConstructor
@RequestMapping("/evaluation/ideology/value")
public class IdeologyValueController {
    private final IdeologyValueService ideologyValueService;

    @Resource
    private CmCourseUnitVValueMapper cmCourseUnitVValueMapper;

    @Resource
    private CmKnowledgeUnitMapper cmKnowledgeUnitMapper;

    /**
     * 新增一级节点
     */
    @PostMapping("/parent")
    @AuthRequired
    public Result createFirstLevelNode(HttpServletRequest request){
        Token token = getTokenFromContext();
        String courseId = token.getObsid();
        ideologyValueService.createFirstLevelNode(courseId);
        return Result.ok().msg("新增成功");
    }

    /**
     *  修改名称
     *  id,vname
     */
    @PutMapping
    public Result modifyValueName(@RequestBody IdeologyValue ideologyValue){
        SmartAssert.checkExpression(StrUtil.isNotBlank(ideologyValue.getId()), ResponseEnum.NO_VALID);
        ideologyValueService.modifyValueName(ideologyValue);
        return Result.ok().msg("修改成功");
    }

    /**
     * 同级新增
     * parentId
     */
    @PostMapping("/addSameLevelNode")
    public Result addSameLevelNode(@RequestBody IdeologyValue ideologyValue){
        SmartAssert.checkExpression(StrUtil.isNotBlank(ideologyValue.getId()), ResponseEnum.NO_VALID);
        ideologyValue = ideologyValueService.selectById(ideologyValue.getId());
        ideologyValueService.addSameLevelNode(ideologyValue);
        return Result.ok().msg("新增成功");
    }

    /**
     * 下级新增
     */
    @PostMapping("/addSubLevelNode")
    public Result addSubLevelNode(@RequestBody IdeologyValue ideologyValue){
        ideologyValue = ideologyValueService.selectById(ideologyValue.getId());
        SmartAssert.checkExpression(Objects.isNull(ideologyValue.getParentId()), ResponseEnum.ADD_SUB_LEVEL_NODE_VALID);
        ideologyValueService.addSubLevelNode(ideologyValue);
        return Result.ok().msg("新增成功");
    }

    /**
     * 删除
     */
    @DeleteMapping
    public Result deleteNode(@RequestBody IdeologyValue ideologyValue){
        SmartAssert.checkExpression(StrUtil.isNotBlank(ideologyValue.getId()), ResponseEnum.NO_VALID);
        ideologyValueService.deleteNode(ideologyValue.getId());
        return Result.ok().msg("删除成功");
    }

    /**
     * 查询
     */
    @GetMapping
    @AuthRequired
    public Result selectAllNode(HttpServletRequest request){
        Token token = getTokenFromContext();
        String courseId = token.getObsid();
        List<IdeologyValue> ideologyValueList = ideologyValueService.selectAllNode(courseId);


        // 方便额外信息查询
        Map<String, IdeologyValue> map = ideologyValueList.stream()
                .collect(Collectors.toMap(IdeologyValue::getId, v -> v));

        // 构造 TreeNode 列表
        List<TreeNode<String>> nodeList = ideologyValueList.stream()
                .map(item -> new TreeNode<>(
                        item.getId(),
                        item.getParentId(),
                        item.getVname(),
                        item.getLevel()
                ))
                .collect(Collectors.toList());
        // TreeNodeConfig
        TreeNodeConfig config = new TreeNodeConfig();
        config.setWeightKey("weight");

        // 构建树
        List<Tree<Object>> treeList = TreeUtil.build(nodeList, null, config, (node, tree) -> {
            // 基础字段
            tree.setId(node.getId());
            tree.setParentId(node.getParentId());
            tree.setName(node.getName());
            tree.setWeight(node.getWeight());

            IdeologyValue ori = map.get(node.getId());

            // 扩展字段
            tree.putExtra("remark", ori.getRemark());
            tree.putExtra("courseId", ori.getCourseId());
            tree.putExtra("leaf", ori.getLeaf());
            tree.putExtra("level", ori.getLevel());
            tree.putExtra("createTime", ori.getCreateTime());
            tree.putExtra("updateTime", ori.getUpdateTime());
        });
        return Result.ok().data(treeList);
    }

    /**
     * 获取 V 类型列表（一级节点）
     */
    @GetMapping("/type")
    @AuthRequired
    public Result selectValueTypes(HttpServletRequest request){
        Token token = getTokenFromContext();
        String courseId = token.getObsid();
        List<IdeologyValue> ideologyValueList = ideologyValueService.selectValueTypes(courseId);
        return Result.ok().data(ideologyValueList);
    }

    /**
     * 复制思政价值评价建模：价值标签树 + 思政知识单元关联
     */
    @PostMapping("/copy")
    @AuthRequired
    @Transactional(rollbackFor = Exception.class)
    public Result copyIdeologyModel(@RequestParam("pastCourseId") String pastCourseId, HttpServletRequest request) {
        Token token = getTokenFromContext();
        String currentCourseId = token.getObsid();

        // 1. 复制价值标签树，获取 oldId->newId 映射
        Map<String, String> valueIdMap = ideologyValueService.copyIdeologyValues(pastCourseId, currentCourseId);

        // 2. 复制思政知识单元关联（cm_course_unit_v_values）
        List<CmCourseUnitVValue> pastLinks = cmCourseUnitVValueMapper.selectByCourseId(pastCourseId);
        if (!pastLinks.isEmpty()) {
            cmCourseUnitVValueMapper.deleteByCourseId(currentCourseId);

            // 构建当前课程知识单元 name+ordernum -> newUnitId 映射
            List<Map<String, Object>> currentUnits = cmCourseUnitVValueMapper.selectUnitBasicByCourseId(currentCourseId);
            Map<String, String> unitKeyMap = new HashMap<>();
            for (Map<String, Object> u : currentUnits) {
                String key = u.get("name") + "_" + u.get("ordernum");
                unitKeyMap.put(key, (String) u.get("id"));
            }

            // 获取历史课程知识单元信息
            List<Map<String, Object>> pastUnits = cmCourseUnitVValueMapper.selectUnitBasicByCourseId(pastCourseId);
            Map<String, String> pastUnitIdToKey = new HashMap<>();
            for (Map<String, Object> u : pastUnits) {
                String key = u.get("name") + "_" + u.get("ordernum");
                pastUnitIdToKey.put((String) u.get("id"), key);
            }

            List<CmCourseUnitVValue> toInsert = new ArrayList<>();
            for (CmCourseUnitVValue link : pastLinks) {
                String newVid = valueIdMap.get(link.getVid());
                if (newVid == null) continue;
                String unitKey = pastUnitIdToKey.get(link.getUnitid());
                if (unitKey == null) continue;
                String newUnitId = unitKeyMap.get(unitKey);
                if (newUnitId == null) continue;
                CmCourseUnitVValue newLink = new CmCourseUnitVValue();
                newLink.setId(IdUtil.fastSimpleUUID());
                newLink.setUnitid(newUnitId);
                newLink.setVid(newVid);
                newLink.setStatus(link.getStatus());
                toInsert.add(newLink);
            }
            if (!toInsert.isEmpty()) cmCourseUnitVValueMapper.batchInsert(toInsert);
        }

        return Result.ok().msg("思政价值评价建模复制成功");
    }

    /**
     * 复制价值标签：仅复制历史课程的价值标签树到当前课程，不影响思政知识单元关联。
     */
    @PostMapping("/copyValues")
    @AuthRequired
    @Transactional(rollbackFor = Exception.class)
    public Result copyValues(@RequestParam("pastCourseId") String pastCourseId, HttpServletRequest request) {
        Token token = getTokenFromContext();
        String currentCourseId = token.getObsid();
        Map<String, String> valueIdMap = ideologyValueService.copyIdeologyValues(pastCourseId, currentCourseId);
        return Result.ok().msg("价值标签复制成功(" + valueIdMap.size() + ")");
    }

    /**
     * 复制思政知识单元：将历史课程的知识单元（章节/小节，cm_course_unit）整棵复制到当前课程，
     * 并一并复制其价值标签绑定（cm_course_unit_v_values）。绑定的价值标签按名称（vname）匹配当前课程已有价值标签。
     * 注意：知识单元表为全课程共用（形成性评价建模也使用），复制将覆盖当前课程已有知识单元。
     */
    @PostMapping("/copyKnowledgeUnit")
    @AuthRequired
    @Transactional(rollbackFor = Exception.class)
    public Result copyKnowledgeUnit(@RequestParam("pastCourseId") String pastCourseId, HttpServletRequest request) {
        Token token = getTokenFromContext();
        String currentCourseId = token.getObsid();

        // 1. 读取源课程知识单元
        List<CmKnowledgeUnit> pastUnits = cmKnowledgeUnitMapper.selectAllUnitsByCourseId(pastCourseId);

        // 2. 清空当前课程的知识单元及其关联（绑定值、kwa、连线）
        cmCourseUnitVValueMapper.deleteByCourseId(currentCourseId);
        List<CmKnowledgeUnit> currentUnits = cmKnowledgeUnitMapper.selectAllUnitsByCourseId(currentCourseId);
        if (!currentUnits.isEmpty()) {
            List<String> currentUnitIds = currentUnits.stream()
                    .map(CmKnowledgeUnit::getId).collect(Collectors.toList());
            cmKnowledgeUnitMapper.deleteKnowledgeUnitKwaByUnitids(currentUnitIds);
            cmKnowledgeUnitMapper.deleteLineByUnitIds(currentUnitIds);
            cmKnowledgeUnitMapper.deleteUnitsByCourseId(currentCourseId);
        }

        if (pastUnits.isEmpty()) {
            return Result.ok().msg("思政知识单元复制成功(0)");
        }

        // 3. 复制知识单元（保留章节->小节层级，旧 id -> 新 id 映射）
        Map<String, String> unitIdMap = new HashMap<>();
        for (CmKnowledgeUnit u : pastUnits) {
            unitIdMap.put(u.getId(), IdUtil.fastSimpleUUID());
        }
        List<CmKnowledgeUnit> toInsertUnits = new ArrayList<>();
        for (CmKnowledgeUnit u : pastUnits) {
            CmKnowledgeUnit nu = new CmKnowledgeUnit();
            nu.setId(unitIdMap.get(u.getId()));
            String pid = u.getPid();
            if (pid == null || "0".equals(pid)) {
                nu.setPid("0");
            } else {
                nu.setPid(unitIdMap.getOrDefault(pid, "0"));
            }
            nu.setName(u.getName());
            nu.setType(u.getType());          // nodeType
            nu.setDatavalue(u.getDatavalue());
            nu.setCourseid(currentCourseId);
            nu.setOrdernum(u.getOrdernum());
            toInsertUnits.add(nu);
        }
        cmKnowledgeUnitMapper.batchInsertUnits(toInsertUnits);

        // 4. 复制绑定值（cm_course_unit_v_values）：旧 unitId -> 新 unitId，vid 按价值标签名称匹配当前课程
        List<CmCourseUnitVValue> pastLinks = cmCourseUnitVValueMapper.selectByCourseId(pastCourseId);
        int linkCount = 0;
        if (!pastLinks.isEmpty()) {
            Map<String, String> pastVidToName = new HashMap<>();
            for (IdeologyValue v : ideologyValueService.selectAllNode(pastCourseId)) {
                pastVidToName.put(v.getId(), v.getVname());
            }
            Map<String, String> currentNameToVid = new HashMap<>();
            for (IdeologyValue v : ideologyValueService.selectAllNode(currentCourseId)) {
                currentNameToVid.put(v.getVname(), v.getId());
            }
            List<CmCourseUnitVValue> toInsertLinks = new ArrayList<>();
            for (CmCourseUnitVValue link : pastLinks) {
                String newUnitId = unitIdMap.get(link.getUnitid());
                if (newUnitId == null) continue;
                String vname = pastVidToName.get(link.getVid());
                if (vname == null) continue;
                String newVid = currentNameToVid.get(vname);
                if (newVid == null) continue;
                CmCourseUnitVValue nl = new CmCourseUnitVValue();
                nl.setId(IdUtil.fastSimpleUUID());
                nl.setUnitid(newUnitId);
                nl.setVid(newVid);
                nl.setStatus(link.getStatus());
                toInsertLinks.add(nl);
            }
            if (!toInsertLinks.isEmpty()) {
                cmCourseUnitVValueMapper.batchInsert(toInsertLinks);
                linkCount = toInsertLinks.size();
            }
        }

        return Result.ok().msg("思政知识单元复制成功(单元" + toInsertUnits.size() + ",绑定" + linkCount + ")");
    }
}
