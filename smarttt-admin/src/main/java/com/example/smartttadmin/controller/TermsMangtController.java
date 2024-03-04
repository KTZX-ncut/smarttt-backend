package com.example.smartttadmin.controller;

import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.Termslist;
import com.example.smartttadmin.pojo.EncryptionUtil;
import com.example.smartttadmin.service.StTermsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sysmangt/terms")
public class TermsMangtController {
    @Autowired
    private StTermsService stTermsService;

    /**
     *获取学期信息
     * @return Result
     */
    @GetMapping
    public Result getTermsList() {
        return stTermsService.getTermsList();
    }

    @GetMapping("/create")
    public Result createTerms() {
        return stTermsService.createTerms();
    }

    @GetMapping("/delete")
    public Result deleteTerms() {
        return stTermsService.deleteTerms();
    }

    @PostMapping("/update")
    public Result UpdateTermsList(@RequestBody Termslist updateTermsReq) {
        updateTermsReq.setId(EncryptionUtil.decrypt(updateTermsReq.getId()));
        return stTermsService.Termslist(updateTermsReq);
    }

    @GetMapping("/currentterms")
    public Result getCurrentTerms() {
        return stTermsService.getCurrentTerms();
    }
}
