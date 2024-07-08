package com.example.smartttevaluation.controller;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmLines;
import com.example.smartttevaluation.service.CmLinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 连线管理
 */
@RestController
@RequestMapping("/evaluation/lines")
public class LinesController {
    @Autowired
    private CmLinesService cmLinesService;
    /**
     *连线列表
     */
    @GetMapping
    public Result getLines() {
        return cmLinesService.getLines();
    }
    /**
     *新增连线
     */
    @PostMapping("/create")
    public Result createLines(@RequestBody CmLines cmLines) {
        return cmLinesService.createLines(cmLines);
    }
    /**
     *删除连线
     */
    @PostMapping("/delete")
    public Result deleteLinesByID(@RequestBody List<String> ids) {
        return cmLinesService.deleteLinesByID(ids);
    }

}
