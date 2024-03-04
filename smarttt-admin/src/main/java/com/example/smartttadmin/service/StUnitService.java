package com.example.smartttadmin.service;

import com.example.smartttadmin.dto.Result;
import org.springframework.stereotype.Service;

@Service
public interface StUnitService {
    Result getUnitTree(String rolecode);
}
