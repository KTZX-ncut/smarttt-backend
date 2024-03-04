package com.example.smartttadmin.controller;

import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.TermsResponse;
import com.example.smartttadmin.pojo.SmTerms;
import com.example.smartttadmin.service.SmTermsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sysmangt/terms")
public class TermsMangtController {
    @Autowired
    private SmTermsService smTermsService;

    /**
     *获取学期信息
     * @return Result
     */
    @GetMapping
    public Result getTerms() {
        return smTermsService.getTerms();
    }

    @GetMapping
    public Result createTerms(@RequestBody SmTerms smterms) {
        return smTermsService.createTerms(smterms);
    }

    @GetMapping
    public Result deleteTermsByID(@RequestParam(name = "id")String id) {
        return smTermsService.deleteTermsByID(id);
    }

    @GetMapping
    public Result getCurrentTerms(@RequestParam(name = "isActive")boolean isActive) {
        return smTermsService.getCurrentTerms(isActive);
    }
}
