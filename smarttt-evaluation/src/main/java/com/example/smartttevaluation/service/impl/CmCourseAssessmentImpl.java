package com.example.smartttevaluation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.smartttevaluation.dto.CmAssessmentFile;
import com.example.smartttevaluation.dto.CmAssessmentStudent;
import com.example.smartttevaluation.dto.CmCourseAssessmentTable;
import com.example.smartttevaluation.mapper.AttainmentEvaluationMapper;
import com.example.smartttevaluation.pojo.*;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.mapper.CmCourseAssessmentMapper;
import com.example.smartttevaluation.service.CmCourseAssessmentService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import static com.example.smartttevaluation.pojo.CommonFunctions.generateEnhancedID;

@Service
public class CmCourseAssessmentImpl implements CmCourseAssessmentService {
    @Autowired
    private CmCourseAssessmentMapper cmCourseAssessmentMapper;
    @Autowired
    private AttainmentEvaluationMapper attainmentEvaluationMapper;

    @Override
    public Result getAssessmentTable(String courseid) {
        // 判断是不是任课教师调用接口
        CmCourse course = attainmentEvaluationMapper.getCourseByCourseId(courseid);
        if (course == null) {
            courseid = attainmentEvaluationMapper.getCourseByClassroomId(courseid).getId();
        }

        CmCourseAssessmentTable cmCourseAssessmentTable = new CmCourseAssessmentTable(new ArrayList<>(), new ArrayList<>(), new JSONObject());
        //处理表头部分
        List<CmCheckitem> cmCourseCheckItems = cmCourseAssessmentMapper.getCourseCheckItem(courseid);
        //创建通过id的映射
        Map<String, CmCheckitem> t_map = new HashMap<>();
        for (CmCheckitem t_cmCourseCheckItem : cmCourseCheckItems) {
            t_map.put(t_cmCourseCheckItem.getId(), t_cmCourseCheckItem);
            t_cmCourseCheckItem.setChildren(new ArrayList<>());
        }
        for (CmCheckitem t_cmCourseCkeckItem : cmCourseCheckItems) {
            if (Objects.equals(t_cmCourseCkeckItem.getPid(), "0")) {
                //根节点
                //添加期末/平时到表头
                cmCourseAssessmentTable.getHead().add(t_cmCourseCkeckItem);
                //将对应比例添加至表
                cmCourseAssessmentTable.getPercent().put(t_cmCourseCkeckItem.getId(), t_cmCourseCkeckItem.getPercent());

            } else {
                //非根节点,找到父节点连接
                String t_pid = t_cmCourseCkeckItem.getPid();
                CmCheckitem p_cmCourseCheckItem = t_map.get(t_pid);

                p_cmCourseCheckItem.getChildren().add(t_cmCourseCkeckItem);
            }

        }

        //获取所有target
        List<CmCoursetarget> cmCourseTargets = cmCourseAssessmentMapper.getCourseTarget(courseid);

        //创建targerId到JSON的映射
        Map<String, JSONObject> targetMap = new HashMap<>();
        for (CmCoursetarget t_cmCourseTarget : cmCourseTargets) {
            //创建JSON对象
            JSONObject t_json = new JSONObject();
            //添加属性
            t_json.put("id", t_cmCourseTarget.getId());
            t_json.put("name", t_cmCourseTarget.getName());
            //将此JSON对象添加至映射，使用targetId访问
            targetMap.put(t_cmCourseTarget.getId(), t_json);
            //将此JSON添加至items列表
            cmCourseAssessmentTable.getItems().add(t_json);

        }
        //获取所有的CourseAssement
        List<CmCourseAssessment> cmCourseAssessment = cmCourseAssessmentMapper.selectCourseAssessment(courseid);
        //将CourseAssessment中的standardScore填入到相应的位置
        for (CmCourseAssessment t_cmCourseAssessment : cmCourseAssessment) {
            //获取对应的JSON对象
            JSONObject t_json = targetMap.get(t_cmCourseAssessment.getCoursetargetId());
            //若存在此JSON对象则添加JSON属性{"targetId","standardScore"}
            if (t_json != null) {
                t_json.put(t_cmCourseAssessment.getCheckitemId(), t_cmCourseAssessment.getStandardScore());
            }

        }

        return Result.success(cmCourseAssessmentTable);
    }

    @Override
    public Result updateStandardScores(Map<String, Map<String, Integer>> jsonData, String obsid) {
        for (String key : jsonData.keySet()) {
            Map<String, Integer> innerMap = jsonData.get(key);
            for (Map.Entry<String, Integer> entry : innerMap.entrySet()) {
                CmCourseAssessment targetData = new CmCourseAssessment();
                targetData.setCourseid(obsid);
                targetData.setCoursetargetId(key);
                targetData.setCheckitemId(entry.getKey());
                targetData.setStandardScore(entry.getValue());

                if (targetData.getStandardScore() == 0) {
                    // standardScore改为0，删除对应条存储
                    cmCourseAssessmentMapper.deleteAssessment(targetData);
                } else if (cmCourseAssessmentMapper.selectAssessmentCount(targetData) == 0) {
                    //此项没有数值,需新建
                    targetData.setId(CommonFunctions.generateEnhancedID("cm_course_assessment"));
                    cmCourseAssessmentMapper.insertAssessment(targetData);
                } else {
                    cmCourseAssessmentMapper.updateAssessment(targetData);
                }
            }
        }
        return Result.success();
    }

    @Override
    public Result updateAssessmentTable(Map<String, Object> data, String obsid) {
        // 对每个考核项的分数修改
        Map<String, Map<String, Integer>> items = (Map<String, Map<String, Integer>>) data.get("items");
        if (items != null) {
            for (String key : items.keySet()) {
                Map<String, Integer> innerMap = items.get(key);
                for (Map.Entry<String, Integer> entry : innerMap.entrySet()) {
                    CmCourseAssessment targetData = new CmCourseAssessment();
                    targetData.setCourseid(obsid);
                    targetData.setCoursetargetId(key);
                    targetData.setCheckitemId(entry.getKey());
                    targetData.setStandardScore(entry.getValue());

                    if (targetData.getStandardScore() == 0) {
                        // standardScore改为0，删除对应条存储
                        cmCourseAssessmentMapper.deleteAssessment(targetData);
                    } else if (cmCourseAssessmentMapper.selectAssessmentCount(targetData) == 0) {
                        //此项没有数值,需新建
                        targetData.setId(CommonFunctions.generateEnhancedID("cm_course_assessment"));
                        cmCourseAssessmentMapper.insertAssessment(targetData);
                    } else {
                        cmCourseAssessmentMapper.updateAssessment(targetData);
                    }
                }
            }
        }

        // 对总评占比修改
        Map<String, Integer> percent = (Map<String, Integer>) data.get("percent");
        if (percent != null) {
            for (Map.Entry<String, Integer> entry : percent.entrySet()) {
                CmCheckitem cmCheckItem = new CmCheckitem();
                cmCheckItem.setCourseid(obsid);
                cmCheckItem.setId(entry.getKey());
                cmCheckItem.setPercent(entry.getValue());
                cmCourseAssessmentMapper.updatePercent(cmCheckItem);
            }
        }
        return Result.success();
    }

    @Override
    public Result getFiles(CmCheckitem cmCheckitem) {
        List<CmTestpaper> testpapers = cmCourseAssessmentMapper.getTestpaper(cmCheckitem.getCourseid());
        List<CmAssessmentFile> customFiles = cmCourseAssessmentMapper.getCustomFile(cmCheckitem.getCourseid());

        List<CmAssessmentFile> files = new ArrayList<>();
        for (CmTestpaper testpaper : testpapers) {
            CmAssessmentFile file = new CmAssessmentFile();
            file.setId(testpaper.getId());
            file.setName(testpaper.getName());
            file.setCreateTime(testpaper.getCreateTime());
            file.setCreator(testpaper.getCreator());
            file.setTotalScore(testpaper.getTotalScore());
            file.setType(1);
            files.add(file);
        }
        for (CmAssessmentFile customFile : customFiles) {
            CmAssessmentFile file = new CmAssessmentFile();
            file.setId(customFile.getId());
            file.setName(customFile.getName());
            file.setCreateTime(customFile.getCreateTime());
            file.setType(4);
            files.add(file);
        }

        List<CmAssessmentFile> associateFiles = cmCourseAssessmentMapper.getAssociateFiles(cmCheckitem.getId(), cmCheckitem.getCourseid());
        Map<String, Object> res = new HashMap<>();
        res.put("files", files);
        res.put("associateFileIds", associateFiles.stream().map(CmAssessmentFile::getId).collect(Collectors.toList()));
        return Result.success(res);
    }

    @Override
    public Result showExcel(String fileId) {
        List<CmAssessmentStudent> stuList = cmCourseAssessmentMapper.showExcel(fileId);
        try {
            String base64Data = generateXLSX(stuList);
            return Result.success(base64Data);
        } catch (IOException e) {
            return Result.error(e.getMessage());
        }
    }

    @Override
    public Result getUploadTemplate(String classroomId) {
        List<CmAssessmentStudent> stuList = attainmentEvaluationMapper.getClassroomStuList(classroomId);
        try {
            String base64Data = generateXLSX(stuList);
            return Result.success(base64Data);
        } catch (IOException e) {
            return Result.error(e.getMessage());
        }
    }

    public String generateXLSX(List<CmAssessmentStudent> stuList) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(); // 创建 XLSX 工作簿
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            // 创建一个 Sheet
            Sheet sheet = workbook.createSheet("学生成绩");

            // 创建表头
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("序号");
            headerRow.createCell(1).setCellValue("学号");
            headerRow.createCell(2).setCellValue("姓名");
            headerRow.createCell(3).setCellValue("成绩");

            // 填充数据
            int rowNum = 1;
            for (CmAssessmentStudent stu : stuList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(stu.getRowNo());
                row.createCell(1).setCellValue(stu.getStuno());
                row.createCell(2).setCellValue(stu.getUsername());
                row.createCell(3).setCellValue(stu.getScore());
            }

            sheet.setColumnWidth(0, 5 * 256); // 序号列宽度为 15 个字符
            sheet.setColumnWidth(1, 15 * 256); // 学号列宽度为 15 个字符
            sheet.setColumnWidth(2, 10 * 256); // 姓名列宽度为 10 个字符
            sheet.setColumnWidth(3, 10 * 256); // 成绩列宽度为 10 个字符

            // 将 Workbook 写入输出流
            workbook.write(outputStream);

            // 将输出流转换为字节数组
            byte[] xlsxBytes = outputStream.toByteArray();

            // 将字节数组编码为 Base64 字符串
            return Base64.getEncoder().encodeToString(xlsxBytes);
        }
    }

    @Override
    public Result uploadFile(MultipartFile file, String classroomId) {
        String fileName = file.getOriginalFilename();
        String fileId = generateEnhancedID("cm_course_assessment_file");

        List<CmAssessmentStudent> stuList;
        try {
//            if (fileName.endsWith(".csv")) {
//                stuList = readCsvFile(file.getInputStream());
//            } else if (fileName.endsWith(".xls") || fileName.endsWith(".xlsx")) {
//                stuList = readExcel(file.getInputStream(), fileName.endsWith(".xlsx"));
//            } else return Result.error("文件格式不支持");
            if (fileName.endsWith(".xls") || fileName.endsWith(".xlsx")) {
                stuList = readExcel(file.getInputStream(), fileName.endsWith(".xlsx"));
            } else return Result.error("文件格式不支持");
        } catch (IOException | IllegalArgumentException e) {
            return Result.error(e.getMessage());
        }

        CmAssessmentFile customFile = new CmAssessmentFile();
        customFile.setId(fileId);
        customFile.setName(fileName);
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        customFile.setCreateTime(dateTime.format(formatter));
        cmCourseAssessmentMapper.setFileInfo(customFile, classroomId);

        for (CmAssessmentStudent stu : stuList) {
            String id = generateEnhancedID("cm_course_assessment_filedata");
            cmCourseAssessmentMapper.setFileData(id, stu, fileId);
        }
        return Result.success();
    }

//    public List<CmAssessmentStudent> readCsvFile(InputStream inputStream) throws IOException, CsvValidationException {
//        List<CmAssessmentStudent> stuList = new ArrayList<>();
//        try (CSVReader reader = new CSVReader(new InputStreamReader(inputStream))) {
//            String[] row;
//            reader.readNext();  // 跳过表头
//            while ((row = reader.readNext()) != null) {
//                if (row.length == 4) {
//                    CmAssessmentStudent stu = new CmAssessmentStudent();
//                    stu.setStuno(row[1]);
//                    if (!row[3].matches("^[0-9]+(\\.[0-9]+)?$")) {
//                        throw new IllegalArgumentException("表格格式不支持，请下载模板修改");
//                    }
//                    if (Objects.equals(row[3], "")) row[3] = "0";
//                    stu.setScore(Float.parseFloat(row[3]));
//                    stuList.add(stu);
//                } else {
//                    throw new IllegalArgumentException("表格格式不支持，请下载模板修改");
//                }
//            }
//        }
//        return stuList;
//    }

    public List<CmAssessmentStudent> readExcel(InputStream inputStream, boolean isXLSX) throws IOException {
        List<CmAssessmentStudent> stuList = new ArrayList<>();
        Workbook workbook = isXLSX ? new XSSFWorkbook(inputStream) : new HSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        if (sheet.getLastRowNum() == -1 || sheet.getLastRowNum() == 0) throw new IllegalArgumentException("空表格");
        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;     // 跳过表头
            if (row.getLastCellNum() != 4 ||
                    row.getCell(0) == null || row.getCell(1) == null ||
                    row.getCell(2) == null || row.getCell(3) == null ||
                    row.getCell(0).getCellType() != CellType.NUMERIC ||
                    row.getCell(1).getCellType() != CellType.STRING ||
                    row.getCell(2).getCellType() != CellType.STRING ||
                    row.getCell(3).getCellType() != CellType.NUMERIC ||
                    (row.getCell(0).getCellType() == CellType.NUMERIC && row.getCell(0).getNumericCellValue() < 0) ||
                    (row.getCell(3).getCellType() == CellType.NUMERIC && row.getCell(3).getNumericCellValue() < 0)) {
                throw new IllegalArgumentException("表格格式不支持，请下载模板修改");
            }
            CmAssessmentStudent stu = new CmAssessmentStudent();
            int rowNo = (int) row.getCell(0).getNumericCellValue();
            String stuno = row.getCell(1).getStringCellValue();
            float score = (float) row.getCell(3).getNumericCellValue();

            stu.setRowNo(rowNo);
            stu.setStuno(stuno);
            stu.setScore(score);
            stuList.add(stu);
        }
        workbook.close();
        return stuList;
    }

    @Override
    public Result getAssociateCheckitems(String fileId, String obsid) {
        List<CmCheckitem> checkitems = cmCourseAssessmentMapper.getAssociateCheckitems(fileId, obsid);
        return Result.success(checkitems);
    }

    @Override
    public Result deleteFile(CmCourseCheckitemFile cmCourseCheckitemFile) {
        cmCourseAssessmentMapper.deleteFile(cmCourseCheckitemFile);
        return Result.success();
    }

    @Override
    public Result associate(Map<String, Object> data, String obsId) {
        String checkitemId = (String) data.get("checkitemId");
        List<CmAssessmentFile> files = new ArrayList<>();
        createFileData(files, data);
        files.forEach(file -> {
            String id = generateEnhancedID("cm_course_assessment_checkitem_file");
            cmCourseAssessmentMapper.associate(id, checkitemId, obsId, file);
        });
        return Result.success();
    }

    @Override
    public Result disassociate(Map<String, Object> data) {
        String checkitemId = (String) data.get("checkitemId");
        List<CmAssessmentFile> files = new ArrayList<>();
        createFileData(files, data);
        cmCourseAssessmentMapper.disassociate(checkitemId, files);
        return Result.success();
    }

    public void createFileData(List<CmAssessmentFile> files, Map<String, Object> data) {
        List<Map<String, Object>> filesMap = (List<Map<String, Object>>) data.get("files");
        for (Map<String, Object> t : filesMap) {
            CmAssessmentFile file = new CmAssessmentFile();
            file.setId((String) t.get("id"));
            file.setName((String) t.get("name"));
            file.setCreateTime((String) t.get("createTime"));
            file.setCreator((String) t.get("creator"));
            file.setTotalScore((int) t.get("totalScore"));
            file.setType((int) t.get("type"));
            files.add(file);
        }
    }
}
