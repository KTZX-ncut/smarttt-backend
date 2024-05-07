package com.example.smartttexam.service;

import com.example.smartttexam.dto.Result;
import org.springframework.stereotype.Service;

@Service
public interface TestQueLibService {

    Result getCourseQL(String obsid);
}
