package com.example.smartttadmin.service;

import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.CmTerm;

import java.util.List;


public interface CmTermService {

    Result getTerms();

    Result createTerms(CmTerm cmTerm);

    Result setCurrentTerms(String id);

    Result deleteTermsByID(List<String> ids);

    Result updateTermByID(CmTerm cmTerm);
}