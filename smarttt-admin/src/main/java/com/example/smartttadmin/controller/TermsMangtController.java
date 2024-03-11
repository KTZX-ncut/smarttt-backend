package com.example.smartttadmin.controller;

import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.CmTerm;
import com.example.smartttadmin.service.CmTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学期管理
 */
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

    /**
     * 新建学期
     * 修改（待定）因为有文件导入功能
     * @param cmTerm
     * @return
     */
    @GetMapping("/create")
    public Result createTerms(@RequestBody CmTerm cmTerm) {
        return smTermsService.createTerms(cmTerm);
    }

    /**
     * 删除学期
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public Result deleteTermsByID(@RequestBody List<String> ids) {
        return smTermsService.deleteTermsByID(ids);
    }

    @GetMapping("/currentterm")
    public Result getCurrentTerms(@RequestParam(name = "iscurrentterm")boolean iscurrentterm) {
        return smTermsService.getCurrentTerms(iscurrentterm);
    }
}
