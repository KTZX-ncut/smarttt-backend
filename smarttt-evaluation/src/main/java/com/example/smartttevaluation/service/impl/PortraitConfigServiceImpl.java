package com.example.smartttevaluation.service.impl;

import com.example.smartttevaluation.mapper.PortraitConfigMapper;
import com.example.smartttevaluation.mapper.PortraitTotalMapper;
import com.example.smartttevaluation.pojo.CalculatePaper;
import com.example.smartttevaluation.service.PortraitConfigService;
import com.example.smartttevaluation.vo.TestPaperInfoVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PortraitConfigServiceImpl implements PortraitConfigService {

    @Resource
    private PortraitConfigMapper portraitConfigMapper;

    @Override
    public List<TestPaperInfoVO> getTestPaperInfo(String classroomId) {
        return portraitConfigMapper.getTestPaperInfo(classroomId);
    }

    @Override
    public Boolean saveConfPaper(List<CalculatePaper> calculatePaperList) {
        String classroomId = calculatePaperList.get(0).getClassroomId();
        // 处理row的数据
        Integer maxRow = portraitConfigMapper.getMaxRow(classroomId);
        if (maxRow == null){
            maxRow = 0;
        }
        for (CalculatePaper calculatePaper : calculatePaperList) {
            calculatePaper.setRow(maxRow + 1);
            maxRow++;
        }
        return portraitConfigMapper.saveConfPaper(calculatePaperList);
    }

    @Override
    public Boolean removeConfPaperByIds(List<Long> idList) {
        return portraitConfigMapper.removeConfPaperByIds(idList);
    }

    @Override
    public List<CalculatePaper> getConfTestInfoList(String classroomId) {
        return portraitConfigMapper.getConfTestInfoList(classroomId);
    }

    @Override
    @Transactional
    public Boolean updateConfTestInfoListRow(List<CalculatePaper> calculatePaperList) {
        for (CalculatePaper calculatePaper : calculatePaperList){
            portraitConfigMapper.updateConfTestInfoListRow(calculatePaper.getId(), calculatePaper.getRow());
        }
        return true;
    }
}
