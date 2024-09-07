package com.example.smartttcourse.controller;


import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.pojo.CmTerm;
import com.example.smartttcourse.service.CmTermService;
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
    private CmTermService cmTermService;

    /**
     *获取学期信息
     * @return Result
     */
    @GetMapping
    public Result getTerms() {
        return cmTermService.getTerms();
    }

    /**
     * 新建学期
     * 修改（待定）因为有文件导入功能
     * @param cmTerm
     * @return
     */
    @PostMapping ("/create")
    public Result createTerms(@RequestBody CmTerm cmTerm) {
        return cmTermService.createTerms(cmTerm);
    }

    /**
     * 删除学期
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public Result deleteTermsByID(@RequestBody List<String> ids) {
        return cmTermService.deleteTermsByID(ids);
    }

    @PostMapping
    public Result updateTerm(@RequestBody CmTerm cmTerm){
        return cmTermService.updateTermByID(cmTerm);
    }

    @GetMapping("/currentterm")
    public Result setCurrentTerms(@RequestParam(name = "id")String id) {
        return cmTermService.setCurrentTerms(id);
    }
}
