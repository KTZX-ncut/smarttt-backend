package com.example.smartttadmin.controller;

import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.CmTerm;
import com.example.smartttadmin.service.CmTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sysmangt/terms")
public class TermsMangtController {
    @Autowired
    private CmTermService smTermsService;

    /**
     *获取学期信息
     * @return Result
     */
    @GetMapping
    public Result getTerms() {
        return smTermsService.getTerms();
    }

    @GetMapping("/create")
    public Result createTerms(@RequestBody CmTerm cmTerm) {
        return smTermsService.createTerms(cmTerm);
    }

    @GetMapping("/delete")
    public Result deleteTermsByID(@RequestParam(name = "id")String id) {
        return smTermsService.deleteTermsByID(id);
    }

    @GetMapping("/currentterm")
    public Result getCurrentTerms(@RequestParam(name = "iscurrentterm")boolean iscurrentterm) {
        return smTermsService.getCurrentTerms(iscurrentterm);
    }
}
