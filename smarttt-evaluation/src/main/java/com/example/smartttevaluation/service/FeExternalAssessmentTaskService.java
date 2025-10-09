package com.example.smartttevaluation.service;

import com.example.smartttevaluation.pojo.FeExternalAssessmentTaskDetail;
import java.util.List;

public interface FeExternalAssessmentTaskService {
    String batchSaveOrUpdate(List<FeExternalAssessmentTaskDetail> list);
}
