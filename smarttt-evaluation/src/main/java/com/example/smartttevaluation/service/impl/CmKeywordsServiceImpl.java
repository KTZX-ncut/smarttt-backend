package com.example.smartttevaluation.service.impl;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.mapper.CmKeywordsMapper;
import com.example.smartttevaluation.pojo.CmKeywords;
import com.example.smartttevaluation.pojo.CmKwadict;
import com.example.smartttevaluation.service.CmKeywordsService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static com.example.smartttevaluation.pojo.CommonFunctions.generateEnhancedID;

@Service
public class CmKeywordsServiceImpl implements CmKeywordsService {

    @Autowired
    private CmKeywordsMapper cmKeywordsMapper;

    /**
     * 获取关键字
     */
    @Override
    public Result getKeywords(String obsId) {
        return Result.success(cmKeywordsMapper.getKeywords(obsId));
    }

    /**
     * 创建关键字
     */
    @Override
    public Result createKeywords(CmKeywords cmKeywords) {
        cmKeywords.setId(generateEnhancedID("cm_keywords"));
        cmKeywordsMapper.createKeywords(cmKeywords);
        return Result.success();
    }

    /**
     * 批量删除关键字
     */
    @Override
    public Result deleteKeywordsByID(List<String> ids) {
        cmKeywordsMapper.deleteKeywordsByIDs(ids);
        return Result.success();
    }

    /**
     * 更新关键字
     */
    @Override
    public Result updateKeywords(CmKeywords cmKeywords) {
        cmKeywordsMapper.updateKeywordsByID(cmKeywords);
        return Result.success();
    }

    /**
     * 通过关键字id查询相关kwa
     */
    @Override
    public Result getKwaByKeywordsId(String obsId, List<String> ids) {
        //查询课程id是否存在
        if (cmKeywordsMapper.getNumOfCourseById(obsId) == 0) {
            return Result.error(404, "课程id不存在");
        }
        List<CmKwadict> kwas = cmKeywordsMapper.getKwaByKeywordsId(obsId, ids);
        kwas.forEach(kwa -> {
            kwa.setName(kwa.getKeywordname() + "-" + kwa.getAbilityname());
        });
        return Result.success(kwas);
    }

    @Override
    public List<CmKeywords> importKeywordExcel(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0); // 假设 Excel 文件中只有一个工作表
            DataFormatter dataFormatter = new DataFormatter();
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // 跳过标题行

            List<CmKeywords> cmKeywordsList = new ArrayList<>();
            while (rowIterator.hasNext()) {
                CmKeywords cmKeywords = new CmKeywords();
                Row row = rowIterator.next();
                cmKeywords.setName(dataFormatter.formatCellValue(row.getCell(1)));
                cmKeywords.setDatavalue(dataFormatter.formatCellValue(row.getCell(2)));
                cmKeywords.setImportantlevelid(dataFormatter.formatCellValue(row.getCell(3)));
                cmKeywords.setRemark(dataFormatter.formatCellValue(row.getCell(4)));
                cmKeywordsList.add(cmKeywords);

            }
            return cmKeywordsList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
