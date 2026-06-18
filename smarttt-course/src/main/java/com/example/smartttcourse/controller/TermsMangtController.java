package com.example.smartttcourse.controller;


import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.pojo.CmTerm;
import com.example.smartttcourse.service.CmTermService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学期管理
 */
@RestController
@RequestMapping("/sysmangt/terms")
@Api(tags = "2. 学期管理", description = "学期的查询、新增、删除、更新和当前学期设置")
public class TermsMangtController {
    @Autowired
    private CmTermService cmTermService;

    /**
     *获取学期信息
     * @return Result
     */
    @GetMapping
    @ApiOperation(value = "获取学期列表", notes = "查询系统中全部学期信息。")
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
    @ApiOperation(value = "新建学期", notes = "创建新的学期记录。")
    public Result createTerms(@ApiParam(value = "学期信息", required = true) @RequestBody CmTerm cmTerm) {
        return cmTermService.createTerms(cmTerm);
    }

    /**
     * 删除学期，缺少删除班级和专业，以及学期相关的其它信息
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除学期", notes = "按学期 ID 列表批量删除学期。")
    public Result deleteTermsByID(@ApiParam(value = "学期 ID 列表", required = true) @RequestBody List<String> ids) {
        return cmTermService.deleteTermsByID(ids);
    }

    @PostMapping
    @ApiOperation(value = "更新学期信息", notes = "修改学期名称、起止日期、备注等信息。")
    public Result updateTerm(@ApiParam(value = "学期信息", required = true) @RequestBody CmTerm cmTerm){
        return cmTermService.updateTermByID(cmTerm);
    }

    @GetMapping("/currentterm")
    @ApiOperation(value = "设置当前学期", notes = "将指定学期设置为当前正在使用的学期。")
    public Result setCurrentTerms(@ApiParam(value = "学期 ID", required = true, example = "term-2025-2") @RequestParam(name = "id")String id) {
        return cmTermService.setCurrentTerms(id);
    }
}
