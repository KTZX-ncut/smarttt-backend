package com.example.smartttevaluation.service;



import com.example.smartttevaluation.pojo.IdeologyCalculatePaper;

import java.util.List;

public interface IdeologyConfigService {
    List<IdeologyCalculatePaper> getConfTestInfoList(String classroomId);

    Boolean saveConfPaper(List<IdeologyCalculatePaper> calculatePaperList);

    Boolean removeConfPaperByIds(List<Long> idList);

    Boolean updateConfTestInfoListRow(List<IdeologyCalculatePaper> calculatePaperList);
}
