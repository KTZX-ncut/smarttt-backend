package com.example.smartttadmin.service;

import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.CmTerm;


public interface CmTermService {

    Result getTerms();

    Result createTerms(CmTerm cmTerm);

    Result getCurrentTerms(boolean iscurrentterm);

    Result deleteTermsByID(String id);
}