package com.example.smartttevaluation.vo;

import cn.hutool.core.lang.tree.Tree;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @author lunSir
 * @create 2024-11-28 21:07
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class StudentPortraitVO {
    private List<AbilityVO> ability;
    private List<KeywordVO> keyword;
    private List<KnowledgeUnitVO> unit;
    private List<Tree<String>> unitTree;
}
