package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.pojo.FeExternalAssessmentTaskLabel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LabelMapperTest {

    @Autowired
    private FeExternalAssessmentTaskLabelMapper labelMapper;

    @Test
    void testInsertLabel() {
        FeExternalAssessmentTaskLabel label = new FeExternalAssessmentTaskLabel();
        label.setClassroomId("292104772-1fadaf0b-0b82-4f42-8181-b1621279e074");
        label.setLabelName("测试导入分类");
        labelMapper.insert(label);
        System.out.println("插入成功，生成的ID：" + label.getId());
    }
}

