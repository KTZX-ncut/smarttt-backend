package com.example.smartttevaluation.vo;

import lombok.Data;

import java.util.List;

@Data
public class StudentPaperPortraitVO {
    // 关键字
    private List<KeywordVO> keyword;
    // 能力
    private List<AbilityVO> ability;
    // kwa
    private List<KwaVO> kwa;
    // 班级能力平均值
    private List<AbilityVO> classAbility;
}
