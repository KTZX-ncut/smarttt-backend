package com.example.smartttevaluation.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.Cell;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.read.listener.ReadListener;
import com.example.smartttevaluation.dto.ExternalAssessmentExcel;
import com.example.smartttevaluation.exception.cus.BusinessException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@Data
public class ExternalAssessmentExcelListener implements ReadListener<ExternalAssessmentExcel> {

    private List<String> headList = new ArrayList<>();

    private List<ExternalAssessmentExcel> dataList = new ArrayList<>();

    @Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
        // 获取所有表头
        headMap.forEach((k, v) -> {
            headList.add(v.getStringValue());
        });
    }

    @Override
    public void invoke(ExternalAssessmentExcel externalAssessmentExcel, AnalysisContext analysisContext) {
        // 获取外部考核任务信息
        Map<Integer, Cell> cellMap = analysisContext.readRowHolder().getCellMap();
        Map<String, Double> map = new LinkedHashMap<>();

        // 前3列是固定字段，从第3列之后都是动态部分（外部考核任务信息）
        for (int i = 3; i < headList.size(); i++) {
            ReadCellData<?> cell = (ReadCellData<?>) cellMap.get(i);
            map.put(headList.get(i), getCellValue(cell));
        }
        externalAssessmentExcel.setAssessmentMap(map);
        dataList.add(externalAssessmentExcel);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("外部数据excel表格数据解析成功！");
    }

    private Double getCellValue(ReadCellData<?> cellData) {
        if (Objects.equals(cellData.getType(), CellDataTypeEnum.EMPTY)){
            throw new BusinessException("外部考核任务数值不能为空");
        }
        if(!Objects.equals(cellData.getType(), CellDataTypeEnum.NUMBER)){
            throw new BusinessException("外部考核任务数值只能是数字");
        }
        return cellData.getNumberValue().doubleValue();
    }
}
