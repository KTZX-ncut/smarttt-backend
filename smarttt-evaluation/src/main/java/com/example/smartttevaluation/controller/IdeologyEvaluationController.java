package com.example.smartttevaluation.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.smartttevaluation.Utils.AuthRequired;
import com.example.smartttevaluation.dto.*;
import com.example.smartttevaluation.exception.cus.BusinessException;
import com.example.smartttevaluation.exception.res.ResponseEnum;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.exception.utils.SmartAssert;
import com.example.smartttevaluation.pojo.*;
import com.example.smartttevaluation.service.CmClassroomStudentService;
import com.example.smartttevaluation.service.IdeologyEvaluationService;
import com.example.smartttevaluation.service.IdeologyValueService;
import com.example.smartttevaluation.service.ReachEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.smartttevaluation.Utils.AuthorizationAspect.getTokenFromContext;

@RestController
@RequestMapping("/evaluation/ideology")
@RequiredArgsConstructor
public class IdeologyEvaluationController {
    private final IdeologyEvaluationService ideologyEvaluationService;

    private final ReachEvaluationService reachEvaluationService;

    private final CmClassroomStudentService classroomStudentService;

    private final IdeologyValueService ideologyValueService;


    /**
     * 修改学生是否参与达成性评价的状态
     * 接受的是classroom_student表中的id和reach_state
     * @return
     */
    @PutMapping("/modifyStudentIdeologyState")
    Result getStudentEvalNums(@RequestBody List<StudentIdeologyStateReq> studentIdeologyStateReqList){
        // 检验数据
        if(Objects.isNull(studentIdeologyStateReqList) || studentIdeologyStateReqList.size() == 0){
            return Result.error("studentIdeologyStateReqList不能为空");
        }
        for (StudentIdeologyStateReq studentIdeologyStateReq : studentIdeologyStateReqList){
            SmartAssert.checkExpression(!StrUtil.isBlank(studentIdeologyStateReq.getClassroomStudentId()), ResponseEnum.CLASSROOM_STUDENT_ID_IS_NOT_NULL);
            if(studentIdeologyStateReq.getIdeologyState() == null){
                throw new BusinessException("ideologyState不能为空");
            }
            if(studentIdeologyStateReq.getIdeologyState() != 0 && studentIdeologyStateReq.getIdeologyState() != 1){
                throw new BusinessException("ideologyState参数只能是0或者1");
            }
        }
        boolean state = ideologyEvaluationService.modifyStudentIdeologyState(studentIdeologyStateReqList);
        return Result.success(state);
    }

    /**
     * 思政评价一键计算
     */
    @GetMapping("/calculate")
    public Result calculate(@RequestParam("classroomId") String classroomId) {
        // 根据classroomId 获取对应的课堂id
        String courseId = reachEvaluationService.getCourseIdByClassroomId(classroomId);
        SmartAssert.checkExpression(StrUtil.isNotBlank(courseId), ResponseEnum.CLASSROOM_ID_NOT_EXIST);
        // 获取该课堂下所有的学生
        List<CmClassroomStudent> classroomStudentList = classroomStudentService.list(new QueryWrapper<CmClassroomStudent>().eq("classroomId", classroomId));
        // 获取试卷配置信息
        List<IdeologyCalculatePaper> paperList = ideologyEvaluationService.getPaperList(classroomId);

        // 获取本课程下所有的V
        List<IdeologyValue> ideologyValueList = ideologyValueService.selectAllNode(courseId);
        // 一键计算
        ideologyEvaluationService.calculate(classroomStudentList,paperList,classroomId,courseId,ideologyValueList);
        return Result.ok().code(200).msg("计算完成！");
    }

    /**
     * 学生思政价值评价
     */
    @GetMapping("/getStudentIdeologyEvaluation")
    @AuthRequired
    Result getStudentIdeologyEvaluation(@RequestParam("userId") String userId,@RequestParam("classroomId") String classroomId) {
        // 获取courseId
        String courseId = ideologyEvaluationService.getCourseIdByClassroomId(classroomId);
        SmartAssert.checkExpression(StrUtil.isNotBlank(courseId),ResponseEnum.CLASSROOM_ID_NOT_EXIST);
        // 判断学生有没有参与评价
        List<StudentIdeologyEvaluation> stuIdeologyEvalList = ideologyEvaluationService.getIdeologyEvaluationByUserId(userId,classroomId);

        SmartAssert.checkExpression(CollUtil.isNotEmpty(stuIdeologyEvalList),ResponseEnum.NO_STUDENT_EVALUATION);


        // 查询本课堂所有的V
        List<IdeologyValue> ideologyValueList = ideologyValueService.selectAllNode(courseId);

        // 组装数据
        // 方便额外信息查询
        Map<String, IdeologyValue> ideologyValueMap = ideologyValueList.stream()
                .collect(Collectors.toMap(IdeologyValue::getId, v -> v));

        Map<String, StudentIdeologyEvaluation> stuIdeologyEvalMap = stuIdeologyEvalList.stream()
                .collect(Collectors.toMap(StudentIdeologyEvaluation::getVId, t -> t));

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

            IdeologyValue ori = ideologyValueMap.get(node.getId());
            // 扩展字段
            tree.putExtra("remark", ori.getRemark());
            tree.putExtra("courseId", ori.getCourseId());
            tree.putExtra("leaf", ori.getLeaf());
            tree.putExtra("level", ori.getLevel());
            tree.putExtra("createTime", ori.getCreateTime());
            tree.putExtra("updateTime", ori.getUpdateTime());
            if (Objects.equals(ori.getLevel(),2)){
                // 如果层级为2，代表是本节点是思政价值节点，需要添加该节点的评价信息
                // 再次扩展：添加该学生的评价信息
                StudentIdeologyEvaluation stuIdeologyEvalOri = stuIdeologyEvalMap.get(node.getId());
                tree.putExtra("evalResult", stuIdeologyEvalOri);
            }
        });
        return Result.ok().data(treeList).msg("查询成功！");
    }

    /**
     * 课堂思政价值评价
     */
    @GetMapping("/getClassroomIdeologyEvaluation")
    @AuthRequired
    Result getClassroomIdeologyEvaluation(@RequestParam("classroomId") String classroomId) {
        // 获取courseId
        String courseId = ideologyEvaluationService.getCourseIdByClassroomId(classroomId);
        SmartAssert.checkExpression(StrUtil.isNotBlank(courseId),ResponseEnum.CLASSROOM_ID_NOT_EXIST);
        // 判断本课堂有没有参与评价
        List<ClassroomIdeologyEvaluation> clsIdeologyEvalList = ideologyEvaluationService.getIdeologyEvaluationByClassroomId(classroomId);

        SmartAssert.checkExpression(CollUtil.isNotEmpty(clsIdeologyEvalList),ResponseEnum.NO_CLASSROOM_EVALUATION);


        // 查询本课堂所有的V
        List<IdeologyValue> ideologyValueList = ideologyValueService.selectAllNode(courseId);

        // 组装数据
        // 方便额外信息查询
        Map<String, IdeologyValue> ideologyValueMap = ideologyValueList.stream()
                .collect(Collectors.toMap(IdeologyValue::getId, v -> v));

        Map<String, ClassroomIdeologyEvaluation> clsIdeologyEvalMap = clsIdeologyEvalList.stream()
                .collect(Collectors.toMap(ClassroomIdeologyEvaluation::getVId, t -> t));

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

            IdeologyValue ori = ideologyValueMap.get(node.getId());
            // 扩展字段
            tree.putExtra("remark", ori.getRemark());
            tree.putExtra("courseId", ori.getCourseId());
            tree.putExtra("leaf", ori.getLeaf());
            tree.putExtra("level", ori.getLevel());
            tree.putExtra("createTime", ori.getCreateTime());
            tree.putExtra("updateTime", ori.getUpdateTime());
            if (Objects.equals(ori.getLevel(),2)){
                // 如果层级为2，代表是本节点是思政价值节点，需要添加该节点的评价信息
                // 再次扩展：添加该学生的评价信息
                ClassroomIdeologyEvaluation clsIdeologyEvalOri = clsIdeologyEvalMap.get(node.getId());
                tree.putExtra("evalResult", clsIdeologyEvalOri);
            }
        });
        return Result.ok().data(treeList).msg("查询成功！");
    }

    /**
     * 获取全部（已经参与）学生思政价值评价
     */
    @GetMapping("/getAllStudentIdeologyEvaluation")
    @AuthRequired
    Result getStudentIdeologyEvaluation(@RequestParam("classroomId") String classroomId) {
        // 获取courseId
        String courseId = ideologyEvaluationService.getCourseIdByClassroomId(classroomId);
        SmartAssert.checkExpression(StrUtil.isNotBlank(courseId),ResponseEnum.CLASSROOM_ID_NOT_EXIST);
        // 查询所有
        List<StudentIdeologyEvaluation> allStuIdeologyEvalList = ideologyEvaluationService.getAllStuIdeologyEvaluation(classroomId);

        List<List<StudentIdeologyEvaluation>> result =
                allStuIdeologyEvalList.stream()
                        .collect(Collectors.groupingBy(StudentIdeologyEvaluation::getUserId))
                        .values()
                        .stream()
                        .collect(Collectors.toList());
        // 查询本课堂所有的V
        List<IdeologyValue> ideologyValueList = ideologyValueService.selectAllNode(courseId);

        // 查询本课堂下参与思政评价所有的学生
        List<StudentIdeologyEvalDto> studentIdeologyEvalDtoList = ideologyEvaluationService.getAllEvalStu(classroomId);

        for (List<StudentIdeologyEvaluation> stuIdeologyEvalList : result){
            // 组装数据
            // 方便额外信息查询
            Map<String, IdeologyValue> ideologyValueMap = ideologyValueList.stream()
                    .collect(Collectors.toMap(IdeologyValue::getId, v -> v));

            Map<String, StudentIdeologyEvaluation> stuIdeologyEvalMap = stuIdeologyEvalList.stream()
                    .collect(Collectors.toMap(StudentIdeologyEvaluation::getVId, t -> t));

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

                IdeologyValue ori = ideologyValueMap.get(node.getId());
                // 扩展字段
                tree.putExtra("remark", ori.getRemark());
                tree.putExtra("courseId", ori.getCourseId());
                tree.putExtra("leaf", ori.getLeaf());
                tree.putExtra("level", ori.getLevel());
                tree.putExtra("createTime", ori.getCreateTime());
                tree.putExtra("updateTime", ori.getUpdateTime());
                if (Objects.equals(ori.getLevel(),2)){
                    // 如果层级为2，代表是本节点是思政价值节点，需要添加该节点的评价信息
                    // 再次扩展：添加该学生的评价信息
                    StudentIdeologyEvaluation stuIdeologyEvalOri = stuIdeologyEvalMap.get(node.getId());
                    tree.putExtra("evalResult", stuIdeologyEvalOri);
                }

            });


            String userId = Optional.ofNullable(stuIdeologyEvalList)
                    .filter(list -> !list.isEmpty())
                    .map(list -> list.get(0))
                    .map(StudentIdeologyEvaluation::getUserId)
                    .orElse(null);  // 或者换成默认值

            if (StrUtil.isNotBlank(userId)){
                studentIdeologyEvalDtoList.stream()
                        .filter(dto -> userId.equals(dto.getUserId()))
                        .findFirst()
                        .ifPresent(dto -> dto.setIdeologyList(treeList));
            }

        }

        return Result.ok().data(studentIdeologyEvalDtoList).msg("查询成功！");
    }

    @GetMapping("/getPaperIdeologyEvaluation")
    Result getPaperIdeologyEvaluation(@RequestParam("classroomId") String classroomId,@RequestParam("paperId") String paperId){
        PaperIdeologyEvaluationDto paperIdeologyEvaluationDto = ideologyEvaluationService.getPaperIdeologyEvaluation(classroomId,paperId);
        return Result.ok().data(paperIdeologyEvaluationDto).msg("查询成功！");
    }

    @GetMapping("/getAllPaperIdeologyEvaluation")
    Result getAllPaperIdeologyEvaluation(@RequestParam("classroomId") String classroomId){
        List<PaperIdeologyEvaluationDto> paperIdeologyEvaluationDtoList = new ArrayList<>();
        // 获取试卷配置信息
        List<IdeologyCalculatePaper> paperList = ideologyEvaluationService.getPaperList(classroomId);
        for (IdeologyCalculatePaper paper : paperList){
            PaperIdeologyEvaluationDto paperIdeologyEvaluationDto = ideologyEvaluationService.getPaperIdeologyEvaluation(classroomId,paper.getPaperId());
            paperIdeologyEvaluationDtoList.add(paperIdeologyEvaluationDto);
        }
        return Result.ok().data(paperIdeologyEvaluationDtoList).msg("查询成功！");
    }
}
