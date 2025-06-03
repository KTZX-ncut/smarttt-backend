package com.example.smartttcourse.service;



import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.pojo.CmTerm;

import java.util.List;


public interface CmTermService {

    Result getHistoryTerm();

    Result createTerms(CmTerm cmTerm);

    Result setCurrentTerms(String id);

    Result deleteTermsByID(List<String> ids);

    Result updateTermByID(CmTerm cmTerm);

    Result getTerms();
    Result getCurrentTerm();

    String getCurrentTermNo();
}