package com.example.smartttevaluation.service.impl;

import com.example.smartttevaluation.mapper.IdeologyConfigMapper;
import com.example.smartttevaluation.pojo.CalculatePaper;
import com.example.smartttevaluation.pojo.IdeologyCalculatePaper;
import com.example.smartttevaluation.service.IdeologyConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IdeologyConfigServiceImpl implements IdeologyConfigService {

    private final IdeologyConfigMapper ideologyConfigMapper;


    @Override
    public List<IdeologyCalculatePaper> getConfTestInfoList(String classroomId) {
        return ideologyConfigMapper.getConfTestInfoList(classroomId);
    }

    @Override
    public Boolean saveConfPaper(List<IdeologyCalculatePaper> calculatePaperList) {
        String classroomId = calculatePaperList.get(0).getClassroomId();
        // 处理row的数据
        Integer maxRow = ideologyConfigMapper.getMaxRow(classroomId);
        if (maxRow == null){
            maxRow = 0;
        }
        for (IdeologyCalculatePaper calculatePaper : calculatePaperList) {
            calculatePaper.setRow(maxRow + 1);
            maxRow++;
        }
        return ideologyConfigMapper.saveConfPaper(calculatePaperList);
    }

    @Override
    public Boolean removeConfPaperByIds(List<Long> idList) {
        return ideologyConfigMapper.removeConfPaperByIds(idList);
    }

    @Override
    @Transactional
    public Boolean updateConfTestInfoListRow(List<IdeologyCalculatePaper> calculatePaperList) {
        for (IdeologyCalculatePaper calculatePaper : calculatePaperList){
            ideologyConfigMapper.updateConfTestInfoListRow(calculatePaper.getId(), calculatePaper.getRow());
        }
        return true;
    }
}
