package com.example.smartttadmin.controller;

import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.SmObs;
import com.example.smartttadmin.pojo.StLevel;
import com.example.smartttadmin.service.SmObsService;
import com.example.smartttadmin.service.StLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sysmangt/schoolmangt")
@Api(tags = "3. 学校与层级", description = "学校基础信息与层级配置接口")
public class SchoolMangtController {
    @Autowired
    private SmObsService smObsService;
    @Autowired
    private StLevelService stLevelService;
    @GetMapping("")
    @ApiOperation(value = "获取学校基础信息", notes = "查询学校节点的基础信息及层级配置。")
    public Result getSchoolInformation(){
        return stLevelService.getSchoolInfor();
    }
    @PostMapping("")
    @ApiOperation(value = "更新学校基础信息", notes = "修改学校节点名称、备注等基础信息。")
    public Result updateSchoolInfor(@ApiParam(value = "学校节点信息", required = true) @RequestBody SmObs smObs){
        SmObs dealSmObs = new SmObs(smObs);
        return smObsService.updateOneObsByID(dealSmObs);
    }
    @PostMapping("/update")
    @ApiOperation(value = "更新层级配置", notes = "更新指定层级的名称、深度及教师/学生可用标识。")
    public Result updateSchoolLevel(@ApiParam(value = "层级配置信息", required = true) @RequestBody StLevel stLevel){
        return stLevelService.updateLevel(stLevel);
    }

}
