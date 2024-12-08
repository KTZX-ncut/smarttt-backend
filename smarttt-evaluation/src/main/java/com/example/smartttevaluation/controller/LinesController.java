package com.example.smartttevaluation.controller;

import com.example.smartttevaluation.Utils.AuthRequired;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.dto.Token;
import com.example.smartttevaluation.pojo.CmLines;
import com.example.smartttevaluation.service.CmLinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.smartttevaluation.Utils.AuthorizationAspect.getTokenFromContext;

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
    @AuthRequired(type = "admin",menu = "531500340-ecaa4072-d8ae-4f54-b681-6d42ca75db12",isReadOnly = true)
    public Result getLines(HttpServletRequest request) {
        Token token = getTokenFromContext();
        return cmLinesService.getLines(token.getObsid());
    }
    /**
     *新增连线
     */
    @PostMapping("/create")
    @AuthRequired(type = "admin",menu = "531500340-ecaa4072-d8ae-4f54-b681-6d42ca75db12")
    public Result createLines(@RequestBody CmLines cmLines,HttpServletRequest request) {
        Token token = getTokenFromContext();
        cmLines.setCourseid(token.getObsid());
        return cmLinesService.createLines(cmLines);
    }
    /**
     *删除连线
     */
    @PostMapping("/delete")
    public Result deleteLinesByID(@RequestBody List<String> ids) {
        return cmLinesService.deleteLinesByIds(ids);
    }

}
