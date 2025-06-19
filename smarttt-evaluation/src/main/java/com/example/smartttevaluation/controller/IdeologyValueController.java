package com.example.smartttevaluation.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.example.smartttevaluation.dto.IdeologyValueReq;
import com.example.smartttevaluation.exception.res.ResponseEnum;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.exception.utils.SmartAssert;
import com.example.smartttevaluation.pojo.IdeologyValue;
import com.example.smartttevaluation.service.IdeologyValueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/evaluation/ideology")
public class IdeologyValueController {
    private IdeologyValueService ideologyValueService;

    public IdeologyValueController(IdeologyValueService ideologyValueService) {
        this.ideologyValueService = ideologyValueService;
    }


    /**
     *  获取所有思想价值
     */
    @GetMapping
    public Result getIdeologyValueList() {
        List<IdeologyValue> ideologyValueList = ideologyValueService.getIdeologyValueList();
        return Result.success(ideologyValueList);
    }

    /**
     *  根据条件获取思想价值
     */
    @PostMapping
    public Result getIdeologyValueListByCondition(@RequestBody IdeologyValueReq ideologyValueReq) {
        List<IdeologyValue> ideologyValueList = ideologyValueService.getIdeologyValueListByCondition(ideologyValueReq);
        return Result.success(ideologyValueList);
    }

    /**
     * 根据id获取思想价值
     */
    @GetMapping("/{id}")
    public Result getIdeologyValueListByCondition(@PathVariable("id") Long id) {
        IdeologyValue ideologyValue = ideologyValueService.getIdeologyValueById(id);
        return Result.success(ideologyValue);
    }

    /**
     * 添加思想价值
     */
    @PostMapping("/add")
    public Result addIdeologyValue(@RequestBody IdeologyValue ideologyValue) {
        SmartAssert.checkExpression(StrUtil.isNotBlank(ideologyValue.getVname()), ResponseEnum.IDEOLOGY_NAME_IS_NOT_NULL);
        // vname必须唯一
        Integer count = ideologyValueService.countIdeologyByVname(ideologyValue.getVname());
        SmartAssert.checkExpression(Objects.equals(count,0), ResponseEnum.IDEOLOGY_VALUE_TYPE_NAME_EXIST);
        return Result.success(ideologyValueService.addIdeologyValue(ideologyValue));
    }

    /**
     * 根据id更新思想价值
     */
    @PostMapping("/update")
    public Result updateIdeologyValue(@RequestBody IdeologyValue ideologyValue) {
        SmartAssert.checkExpression(Objects.nonNull(ideologyValue.getId()), ResponseEnum.IDEOLOGY_ID_IS_NOT_NULL);
        Integer count = ideologyValueService.countIdeologyByVname(ideologyValue.getVname());
        SmartAssert.checkExpression(Objects.equals(count,0), ResponseEnum.IDEOLOGY_VALUE_TYPE_NAME_EXIST);
        return Result.success(ideologyValueService.updateIdeologyValue(ideologyValue));
    }

    /**
     * 根据id删除思想价值
     */
    @DeleteMapping
    public Result delIdeologyValue(@RequestParam("idList") List<Long> idList) {
        SmartAssert.checkExpression(CollectionUtil.isNotEmpty(idList),ResponseEnum.IDEOLOGY_ID_LIST_IS_NOT_NULL);
        return Result.success(ideologyValueService.delIdeologyValueByIdList(idList));
    }


}
