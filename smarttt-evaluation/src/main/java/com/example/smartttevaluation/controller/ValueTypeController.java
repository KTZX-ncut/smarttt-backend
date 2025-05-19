package com.example.smartttevaluation.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.example.smartttevaluation.dto.ValueTypeDto;
import com.example.smartttevaluation.exception.res.ResponseEnum;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.exception.utils.SmartAssert;
import com.example.smartttevaluation.pojo.ValueType;
import com.example.smartttevaluation.service.ValueTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/evaluation/value/type")
public class ValueTypeController {

    @Resource
    private ValueTypeService valueTypeService;


    /**
     * 查询所有的type和type下的value
     * @return
     */
    @GetMapping
    public Result getAllValueType(){
        List<ValueTypeDto> valueTypeDtoList = valueTypeService.getAllValueType();
        return Result.success(valueTypeDtoList);
    }

    /**
     * 创建valueType
     */
    @PostMapping("/create")
    public Result createValueType(@RequestBody ValueType valueType){
        SmartAssert.checkExpression(StrUtil.isNotBlank(valueType.getName()), ResponseEnum.VALUE_TYPE_NAME_NOT_NULL);
        // 校验value-type的name是否存在
        Integer count = valueTypeService.getValueTypeByName(valueType.getName());
        SmartAssert.checkExpression(Objects.equals(count,0),ResponseEnum.VALUE_TYPE_NAME_EXIST);
        Boolean f = valueTypeService.create(valueType);
        return Result.success(f);
    }

    /**
     * 修改valueType
     */
    @PutMapping("/update")
    public Result updateValueType(@RequestBody ValueType valueType){
        // 校验value-type的name是否存在
        Integer count = valueTypeService.getValueTypeByName(valueType.getName());
        SmartAssert.checkExpression(Objects.equals(count,0),ResponseEnum.VALUE_TYPE_NAME_EXIST);
        Boolean f = valueTypeService.update(valueType);
        return Result.success(f);
    }


    /**
     * 删除valueType
     */
    @DeleteMapping ("/delete")
    public Result deleteValueType(@RequestParam("idList") List<Long> idList){
        SmartAssert.checkExpression(CollectionUtil.isNotEmpty(idList),ResponseEnum.VALUE_TYPE_ID_NOT_NULL);
        try {
            Boolean f = valueTypeService.delete(idList);
            return Result.success(f);
        }catch (Exception e){
            return Result.error(-710,e.getMessage());
        }
    }

}
