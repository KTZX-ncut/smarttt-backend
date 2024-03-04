package com.example.smartttadmin.service;

import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.SmTerms;


public interface SmTermsService {

    Result getTerms();

    Result createTerms(SmTerms smterms);

    Result getCurrentTerms(boolean isActive);

    Result deleteTermsByID(String id);
}