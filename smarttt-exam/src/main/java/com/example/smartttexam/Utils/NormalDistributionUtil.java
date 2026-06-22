package com.example.smartttexam.Utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class NormalDistributionUtil {
    private final Random random = new Random();

    /**
     * 生成符合正态分布的整数分数
     *
     * @param difficulty 难度等级 (1=简单, 2=中等, 3=困难)
     * @param maxScore   题目总分
     * @return 整数分数，范围 [0, maxScore]
     */
    public int generateIntegerScore(int difficulty, int maxScore) {
        if (maxScore <= 0) {
            return 0;
        }

        double targetMeanRatio, stdDevRatio;
        switch (difficulty) {
            case 1:
                targetMeanRatio = 0.9;   // 简单题：平均拿90%的分
                stdDevRatio = 0.15;      // 波动15%
                break;
            case 2:
                targetMeanRatio = 0.7;   // 中等题：平均拿70%的分
                stdDevRatio = 0.2;       // 波动20%
                break;
            case 3:
                targetMeanRatio = 0.5;   // 困难题：平均拿50%的分
                stdDevRatio = 0.25;      // 波动25%
                break;
            default:
                targetMeanRatio = 0.6;
                stdDevRatio = 0.20;
        }

        double targetMean = maxScore * targetMeanRatio;
        double stdDev = maxScore * stdDevRatio;

        if (stdDev < 0.5) stdDev = 0.5;

        // nextGaussian() 生成标准正态分布 N(0,1)
        // 变换到目标分布：rawScore = gaussian × stdDev + targetMean
        double rawScore = random.nextGaussian() * stdDev + targetMean;

        int roundedScore = (int) Math.round(rawScore);

        // 钳位到 [0, maxScore]
        return Math.max(0, Math.min(maxScore, roundedScore));
    }

    /**
     * 批量生成分数
     *
     * @param difficulty 难度等级
     * @param maxScore   题目总分
     * @param count      要生成多少个分数（=学生数量）
     * @return 分数数组
     */
    public int[] generateBatchScores(int difficulty, int maxScore, int count) {
        int[] scores = new int[count];
        for (int i = 0; i < count; i++) {
            scores[i] = generateIntegerScore(difficulty, maxScore);
        }
        return scores;
    }
}
