package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmLines;

import java.util.List;

public interface CmLinesService {
    Result getLines();

    Result createLines(CmLines cmLines);

    Result deleteLinesByID(List<String> ids);

}
