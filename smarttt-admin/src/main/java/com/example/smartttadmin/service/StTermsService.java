package com.example.smartttadmin.service;

import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.Termslist;

public interface StTermsService {

    Result getTermsList();

    Result createTerms();

    Result deleteTerms();

    Result Termslist(Termslist updateTermsReq);

    Result getCurrentTerms();
}