package com.example.smartttevaluation.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.smartttevaluation.dto.*;
import com.example.smartttevaluation.exception.cus.BusinessException;
import com.example.smartttevaluation.mapper.AiInStuAnsInfoMapper;
import com.example.smartttevaluation.pojo.AiInStuAnsInfo;
import com.example.smartttevaluation.service.AiInStuAnsInfoService;
import com.example.smartttevaluation.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * (AiInStuAnsInfo)表服务实现类
 *
 * @author makejava
 * @since 2024-11-28 19:14:42
 */
@Service
@Slf4j
public class AiInStuAnsInfoServiceImpl extends ServiceImpl<AiInStuAnsInfoMapper, AiInStuAnsInfo> implements AiInStuAnsInfoService {
    @Resource
    private AiInStuAnsInfoMapper aiInStuAnsInfoMapper;

    @Resource
    private ThreadPoolExecutor portraitTreadPool;


    @Override
    public List<PaperInfoDto> getPaperInfoListByIds(List<String> paperIdList) {
        return aiInStuAnsInfoMapper.getPaperInfoListByIds(paperIdList);
    }

    @Override
    public StudentPortraitVO getStudentPortrait(List<PaperInfoDto> whitePaperIdList, String courseId, String stuId, String classroomId) {
        try {
            log.info("getStudentPortrait:当前线程{}", Thread.currentThread().getName());
            StudentPortraitVO studentPortraitVO = new StudentPortraitVO();
            CompletableFuture<List<KeywordVO>> keywordVOList = this.getKeyWordList(whitePaperIdList, courseId, stuId, classroomId);
            CompletableFuture<List<AbilityVO>> abilityVOList = this.getAbilityList(whitePaperIdList, courseId, stuId, classroomId);
            CompletableFuture<List<Tree<String>>> knowledgeUnitList = this.getKnowledgeUnitList(whitePaperIdList, courseId, stuId, classroomId);
            CompletableFuture<Void> future = CompletableFuture.allOf(keywordVOList, abilityVOList, knowledgeUnitList);
            future.thenRun(() -> {
                try {
                    studentPortraitVO.setKeyword(keywordVOList.get());
                    studentPortraitVO.setAbility(abilityVOList.get());
                    studentPortraitVO.setUnit(knowledgeUnitList.get());
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    throw new BusinessException("系统繁忙，请稍后再试！");
                }
            }).join(); // 阻塞等待所有任务完成

            return studentPortraitVO;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException("系统繁忙，请稍后再试！");
        }
    }

    @Override
    public List<TestPaperInfoVO> getTestPaperInfo(String courseId, String search) {
        List<TestPaperInfoVO> testPaperInfo = aiInStuAnsInfoMapper.getTestPaperInfo(courseId, search);

        // 拿课程名称
        String courseName = aiInStuAnsInfoMapper.getCourseNameByCourseId(courseId);
        if (StrUtil.isBlank(courseName)) {
            throw new BusinessException("课程id不合法", -600);
        }

        for (TestPaperInfoVO vo : testPaperInfo) {
            // 课程名称
            vo.setCourseName(courseName);
        }

        return testPaperInfo;
    }

    @Override
    public List<TestStudentVO> getTestStudentList(String testId) {
        return aiInStuAnsInfoMapper.getTestStudentList(testId);
    }

    @Override
    public List<ClassroomVO> getClassroomList(String courseId, String classroomName) {
        // Map<termId,termName>
        Map<String, String> cache = new HashMap<>();
        List<ClassroomVO> classroomVOList = aiInStuAnsInfoMapper.getClassroomList(courseId, classroomName);
        String courseName = aiInStuAnsInfoMapper.getCourseNameByCourseId(courseId);
        String proName = aiInStuAnsInfoMapper.getProNameByCourseId(courseId);
        if (StrUtil.isBlank(courseName)) {
            throw new BusinessException("课堂id(courseId)不合法", -710);
        }
        for (ClassroomVO vo : classroomVOList) {
            // 课程名称
            vo.setCourseName(courseName);
            // 专业
            vo.setProName(proName);
            // 学期名称
            String termName = cache.get(vo.getTermId());
            if (StrUtil.isBlank(termName)) {
                termName = aiInStuAnsInfoMapper.getTermNameByTermId(vo.getTermId());
                cache.put(vo.getTermId(), termName);
            }
            vo.setTermName(termName);
        }
        return classroomVOList;
    }

    @Override
    public List<PaperInfoDto> getPaperInfoByTestId(String testId) {
        return aiInStuAnsInfoMapper.getPaperInfoByTestId(testId);
    }

    @Override
    public StudentPaperPortraitVO getStudentPaperPortrait(List<PaperInfoDto> whitePaperIdList, String stuId, String courseId, String classroomId) {
        try {
            log.info("getStudentPaperPortrait:当前线程{}", Thread.currentThread().getName());
            StudentPaperPortraitVO studentPaperPortraitVO = new StudentPaperPortraitVO();
            CompletableFuture<List<KeywordVO>> keywordVOList = this.getPaperKeyWordList(whitePaperIdList, courseId, stuId, classroomId);
            CompletableFuture<List<AbilityVO>> abilityVOList = this.getPaperAbilityList(whitePaperIdList, courseId, stuId, classroomId);
            CompletableFuture<List<KwaVO>> kwaList = this.getPaperKwaList(whitePaperIdList, courseId, stuId, classroomId);
            CompletableFuture<List<AbilityVO>> classAbilityVOList = this.getPaperAbilityList(whitePaperIdList, courseId, null, classroomId);

            CompletableFuture<Void> future = CompletableFuture.allOf(keywordVOList,abilityVOList,kwaList,classAbilityVOList);
            future.thenRun(() -> {
                try {
                    // 组装数据
                    studentPaperPortraitVO.setKeyword(keywordVOList.get());
                    studentPaperPortraitVO.setAbility(abilityVOList.get());
                    studentPaperPortraitVO.setKwa(kwaList.get());
                    studentPaperPortraitVO.setClassAbility(classAbilityVOList.get());
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    throw new BusinessException("系统繁忙，请稍后再试！");
                }
            }).join(); // 阻塞等待所有任务完成

            return studentPaperPortraitVO;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException("系统繁忙，请稍后再试");
        }
    }

    @Override
    public StudentPortraitVO getClassroomPortrait(List<PaperInfoDto> whitePaperIdList, String courseId, String classroomId) {
        try {
            log.info("getStudentPortrait:当前线程{}", Thread.currentThread().getName());
            StudentPortraitVO studentPortraitVO = new StudentPortraitVO();
            CompletableFuture<List<KeywordVO>> keywordVOList = this.getKeyWordList(whitePaperIdList, courseId, null, classroomId);
            CompletableFuture<List<AbilityVO>> abilityVOList = this.getAbilityList(whitePaperIdList, courseId, null, classroomId);
            CompletableFuture<List<Tree<String>>> knowledgeUnitList = this.getKnowledgeUnitList(whitePaperIdList, courseId, null, classroomId);
            CompletableFuture<Void> future = CompletableFuture.allOf(keywordVOList, abilityVOList, knowledgeUnitList);
            future.thenRun(() -> {
                try {
                    studentPortraitVO.setKeyword(keywordVOList.get());
                    studentPortraitVO.setAbility(abilityVOList.get());
                    studentPortraitVO.setUnit(knowledgeUnitList.get());
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    throw new BusinessException("系统繁忙，请稍后再试！");
                }
            }).join(); // 阻塞等待所有任务完成

            return studentPortraitVO;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException("系统繁忙，请稍后再试！");
        }
    }

    @Override
    public String getCurrentTermStartTime() {
        return aiInStuAnsInfoMapper.getCurrentTermStartTime();
    }


    /**
     * 试卷分析：  获取试卷的kwa得分
     */
    private CompletableFuture<List<KwaVO>> getPaperKwaList(List<PaperInfoDto> whitePaperIdList, String courseId, String stuId, String classroomId) {
        return CompletableFuture.supplyAsync(()->{
            log.info("getPaperKwaList:当前线程{}", Thread.currentThread().getName());
            // 1. 获取试卷的所有kwa
            List<KwaVO> kwaVOList = aiInStuAnsInfoMapper.getPaperKwaList(whitePaperIdList);
            if (CollectionUtil.isEmpty(kwaVOList)) {
                return new LinkedList<>();
            }
            // 计算学生的得分率
            List<KwaEvalDto> list = aiInStuAnsInfoMapper.getEvalKwaScore(whitePaperIdList,courseId,classroomId,stuId);
            Map<String, List<KwaEvalDto>> map = list.stream().collect(Collectors.groupingBy(KwaEvalDto::getId));

            // 组装数据
            for (KwaVO kwaVO : kwaVOList) {
                Double dataValue = kwaVO.getDataValue();
                List<KwaEvalDto> dtoList = map.get(kwaVO.getId());
                if (CollectionUtil.isEmpty(dtoList)) {
                    kwaVO.setStuDataValue(0.00);
                    continue;
                }
                // 有且仅有一个元素
                KwaEvalDto kwaEvalDto = dtoList.get(0);
                // 会自动提升精度
                if (kwaEvalDto.getAvgLibScore() == 0) {
                    kwaVO.setStuDataValue(0.00);
                } else {
                    Double avgLibStuScore = kwaEvalDto.getAvgLibStuScore();
                    Double avgLibScore = kwaEvalDto.getAvgLibScore();
                    // Double cal = (avgLibStuScore / avgLibScore) * dataValue;
                    Double cal = (avgLibStuScore / avgLibScore);
                    kwaVO.setStuDataValue(Double.valueOf(NumberUtil.roundStr(cal, 2)));
                }

            }
            // 数据组装完成
            return kwaVOList;

        },portraitTreadPool);
    }

    /**
     * 试卷分析：获取试卷学生ability得分率
     */
    private CompletableFuture<List<AbilityVO>> getPaperAbilityList(List<PaperInfoDto> whitePaperIdList,
                                                                   String courseId,
                                                                   String stuId,
                                                                   String classroomId) {

        return CompletableFuture.supplyAsync(() -> {
            log.info("getPaperAbilityList:当前线程{}", Thread.currentThread().getName());
            // 1. 拿到试卷对应的所有的keyword
            List<AbilityVO> abilityVOList = aiInStuAnsInfoMapper.getAbilityByPaperId(whitePaperIdList);

            if (CollectionUtil.isEmpty(abilityVOList)) {
                return new LinkedList<>();
            }
            // 计算分数
            List<AbilityEvalDto> abilityEvalDtoList = aiInStuAnsInfoMapper.getEvalAbilityScore(whitePaperIdList, courseId, stuId, classroomId);
            Map<String, List<AbilityEvalDto>> map = abilityEvalDtoList.stream()
                    .collect(Collectors.groupingBy(AbilityEvalDto::getId));
            // 组装数据
            for (AbilityVO abilityVO : abilityVOList) {
                Double dataValue = abilityVO.getDataValue();
                List<AbilityEvalDto> dtoList = map.get(abilityVO.getId());
                if (CollectionUtil.isEmpty(dtoList)) {
                    abilityVO.setStuDataValue(0.00);
                    continue;
                }
                // 有且仅有一个元素
                AbilityEvalDto abilityEvalDto = dtoList.get(0);
                // 会自动提升精度
                if (abilityEvalDto.getAvgLibScore() == 0) {
                    abilityVO.setStuDataValue(0.00);
                } else {
                    Double avgLibStuScore = abilityEvalDto.getAvgLibStuScore();
                    Double avgLibScore = abilityEvalDto.getAvgLibScore();
                    // TODO: 有可能不用dataValue
                    // Double cal = (avgLibStuScore / avgLibScore) * dataValue;
                    Double cal = (avgLibStuScore / avgLibScore);
                    abilityVO.setStuDataValue(Double.valueOf(NumberUtil.roundStr(cal, 2)));
                }

            }
            // 数据组装完成
            return abilityVOList;
        }, portraitTreadPool);
    }

    /**
     * 试卷分析：获取试卷学生keyword得分率
     */
    private CompletableFuture<List<KeywordVO>> getPaperKeyWordList(List<PaperInfoDto> whitePaperIdList,
                                                                   String courseId,
                                                                   String stuId,
                                                                   String classroomId) {
        return CompletableFuture.supplyAsync(() -> {
            log.info("getPaperKeyWordList:当前线程{}", Thread.currentThread().getName());
            // 1. 拿到试卷对应的所有的keyword
            List<KeywordVO> keywordVOList = aiInStuAnsInfoMapper.getKeyWordIdByPaperId(whitePaperIdList);

            if (CollectionUtil.isEmpty(keywordVOList)) {
                return new LinkedList<>();
            }
            // 2. 计算得分率
            List<KeywordEvalDto> keywordEvalDtoList = aiInStuAnsInfoMapper.getEvalKeywordScore(whitePaperIdList, courseId, stuId, classroomId);
            Map<String, List<KeywordEvalDto>> map = keywordEvalDtoList.stream()
                    .collect(Collectors.groupingBy(KeywordEvalDto::getId));
            // 组装数据
            for (KeywordVO keywordVO : keywordVOList) {
                Double dataValue = keywordVO.getDataValue();
                List<KeywordEvalDto> dtoList = map.get(keywordVO.getId());
                if (CollectionUtil.isEmpty(dtoList)) {
                    keywordVO.setStuDataValue(0.00);
                    continue;
                }
                // 有且仅有一个元素
                KeywordEvalDto keywordEvalDto = dtoList.get(0);
                // 会自动提升精度
                if (keywordEvalDto.getAvgLibScore() == 0) {
                    keywordVO.setStuDataValue(0.00);
                } else {
                    Double avgLibStuScore = keywordEvalDto.getAvgLibStuScore();
                    Double avgLibScore = keywordEvalDto.getAvgLibScore();
                    // Double cal = (avgLibStuScore / avgLibScore) * dataValue;
                    Double cal = (avgLibStuScore / avgLibScore);
                    keywordVO.setStuDataValue(Double.valueOf(NumberUtil.roundStr(cal, 2)));
                }

            }
            // 数据组装完成
            return keywordVOList;
        }, portraitTreadPool);
    }


    /**
     * 获取课堂keyword数据得分率
     */
    private CompletableFuture<List<KeywordVO>> getKeyWordList(List<PaperInfoDto> whitePaperIdList,
                                                             String courseId,
                                                             String stuId,
                                                             String classroomId) {
        return CompletableFuture.supplyAsync(() -> {
            log.info("getKeyWordList:当前线程{}", Thread.currentThread().getName());
            // 拿到该课堂所有的keyword
            List<KeywordVO> keywordVOList = aiInStuAnsInfoMapper.getKeyWordIdByCourseId(courseId);
            if (CollectionUtil.isEmpty(keywordVOList)) {
                return new LinkedList<>();
            }
            // 计算分数
            List<KeywordEvalDto> keywordEvalDtoList = aiInStuAnsInfoMapper.getEvalKeywordScore(whitePaperIdList, courseId, stuId, classroomId);
            Map<String, List<KeywordEvalDto>> map = keywordEvalDtoList.stream()
                    .collect(Collectors.groupingBy(KeywordEvalDto::getId));
            // 组装数据
            for (KeywordVO keywordVO : keywordVOList) {
                Double dataValue = keywordVO.getDataValue();
                List<KeywordEvalDto> dtoList = map.get(keywordVO.getId());
                if (CollectionUtil.isEmpty(dtoList)) {
                    keywordVO.setStuDataValue(0.00);
                    continue;
                }
                // 有且仅有一个元素
                KeywordEvalDto keywordEvalDto = dtoList.get(0);
                // 会自动提升精度
                if (keywordEvalDto.getAvgLibScore() == 0) {
                    keywordVO.setStuDataValue(0.00);
                } else {
                    Double avgLibStuScore = keywordEvalDto.getAvgLibStuScore();
                    Double avgLibScore = keywordEvalDto.getAvgLibScore();
                    Double cal = (avgLibStuScore / avgLibScore) * dataValue;
                    keywordVO.setStuDataValue(Double.valueOf(NumberUtil.roundStr(cal, 2)));
                }

            }
            // 数据组装完成
            return keywordVOList;
        }, portraitTreadPool);
    }

    /**
     * 获取课堂ability数据得分率
     */
    private CompletableFuture<List<AbilityVO>> getAbilityList(List<PaperInfoDto> whitePaperIdList,
                                                             String courseId,
                                                             String stuId, String classroomId) {
        return CompletableFuture.supplyAsync(() -> {
            log.info("getAbilityList:当前线程{}", Thread.currentThread().getName());
            // 拿到该课堂所有的ability
            List<AbilityVO> abilityVOList = aiInStuAnsInfoMapper.getAbilityIdByCourseId(courseId);
            if (CollectionUtil.isEmpty(abilityVOList)) {
                return new LinkedList<>();
            }
            // 计算分数
            List<AbilityEvalDto> abilityEvalDtoList = aiInStuAnsInfoMapper.getEvalAbilityScore(whitePaperIdList, courseId, stuId, classroomId);
            Map<String, List<AbilityEvalDto>> map = abilityEvalDtoList.stream()
                    .collect(Collectors.groupingBy(AbilityEvalDto::getId));
            // 组装数据
            for (AbilityVO abilityVO : abilityVOList) {
                Double dataValue = abilityVO.getDataValue();
                List<AbilityEvalDto> dtoList = map.get(abilityVO.getId());
                if (CollectionUtil.isEmpty(dtoList)) {
                    abilityVO.setStuDataValue(0.00);
                    continue;
                }
                // 有且仅有一个元素
                AbilityEvalDto abilityEvalDto = dtoList.get(0);
                // 会自动提升精度
                if (abilityEvalDto.getAvgLibScore() == 0) {
                    abilityVO.setStuDataValue(0.00);
                } else {
                    Double avgLibStuScore = abilityEvalDto.getAvgLibStuScore();
                    Double avgLibScore = abilityEvalDto.getAvgLibScore();
                    // TODO: 有可能不用dataValue
                    // Double cal = (avgLibStuScore / avgLibScore) * dataValue;
                    Double cal = (avgLibStuScore / avgLibScore);
                    abilityVO.setStuDataValue(Double.valueOf(NumberUtil.roundStr(cal, 2)));
                }

            }
            // 数据组装完成
            return abilityVOList;
        }, portraitTreadPool);
    }

    /**
     * 获取课堂知识单元数据得分率
     */
    private CompletableFuture<List<Tree<String>>> getKnowledgeUnitList(List<PaperInfoDto> whitePaperIdList,
                                                                      String courseId,
                                                                      String stuId, String classroomId) {
        return CompletableFuture.supplyAsync(() -> {
            // 打印日志
            log.info("getKnowledgeUnitList:当前线程{}", Thread.currentThread().getName());

            List<KnowledgeUnitVO> knowledgeUnitVOList = aiInStuAnsInfoMapper.getUnitList(courseId);
            if (CollectionUtil.isEmpty(knowledgeUnitVOList)) {
                return new LinkedList<>();
            }
            List<UnitEvalDto> unitEvalDtoList = aiInStuAnsInfoMapper.getEvalUnitScore(whitePaperIdList, courseId, stuId, classroomId);
            Map<String, List<UnitEvalDto>> map = unitEvalDtoList.stream()
                    .collect(Collectors.groupingBy(UnitEvalDto::getId));
            // 组装数据
            for (KnowledgeUnitVO knowledgeUnitVO : knowledgeUnitVOList) {
                Double dataValue = knowledgeUnitVO.getDataValue();
                List<UnitEvalDto> dtoList = map.get(knowledgeUnitVO.getId());
                if (CollectionUtil.isEmpty(dtoList)) {
                    knowledgeUnitVO.setStuDataValue(0.00);
                    continue;
                }
                // 有且仅有一个元素
                UnitEvalDto unitEvalDto = dtoList.get(0);
                // 会自动提升精度
                if (unitEvalDto.getAvgLibScore() == 0) {
                    knowledgeUnitVO.setStuDataValue(0.00);
                } else {
                    Double avgLibStuScore = unitEvalDto.getAvgLibStuScore();
                    Double avgLibScore = unitEvalDto.getAvgLibScore();
                    Double cal = (avgLibStuScore / avgLibScore) * dataValue;
                    knowledgeUnitVO.setStuDataValue(Double.valueOf(NumberUtil.roundStr(cal, 2)));
                }
            }
            TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
            treeNodeConfig.setWeightKey("orderNum");
            treeNodeConfig.setIdKey("id");
            treeNodeConfig.setParentIdKey("pid");
            treeNodeConfig.setChildrenKey("children");
            List<Tree<String>> treeNodes = TreeUtil.build(knowledgeUnitVOList, "0", treeNodeConfig,
                    (unitVO, tree) -> {
                        tree.setId(unitVO.getId());
                        tree.setParentId(unitVO.getPid());
                        // 扩展属性 ...
                        tree.putExtra("unitName", unitVO.getUnitName());
                        tree.putExtra("orderNum", unitVO.getOrderNum());
                        tree.putExtra("type", unitVO.getType());
                        tree.putExtra("dataValue", unitVO.getDataValue());
                        tree.putExtra("stuDataValue", unitVO.getStuDataValue());
                    });
            return treeNodes;
        }, portraitTreadPool);
    }
}
