package com.example.smartttevaluation.controller;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import com.example.smartttevaluation.Utils.AuthRequired;
import com.example.smartttevaluation.dto.Token;
import com.example.smartttevaluation.exception.res.ResponseEnum;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.exception.utils.SmartAssert;
import com.example.smartttevaluation.pojo.IdeologyValue;
import com.example.smartttevaluation.service.IdeologyValueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
}
