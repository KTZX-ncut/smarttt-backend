package com.example.smartttevaluation.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.example.smartttevaluation.dto.KwaBindReq;
import com.example.smartttevaluation.dto.KwaValueTagBindReq;
import com.example.smartttevaluation.exception.res.ResponseEnum;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.exception.utils.SmartAssert;
import com.example.smartttevaluation.pojo.ValueTag;
import com.example.smartttevaluation.service.ValueTagService;
import com.example.smartttevaluation.service.ValueTypeService;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author lunSir
 * @create 2025-04-26 14:05
 */
@RestController
@RequestMapping("/evaluation/value")
public class ValueTagController {

    @Resource
    private ValueTagService valueTagService;

    @Resource
    private ValueTypeService valueTypeService;

    /***
     * 查询所有的V
     */
    @GetMapping
    public Result getAllValue(){
        List<ValueTag> valueTagList =valueTagService.getAllValue();
        return Result.success(valueTagList);
    }

    /**
     * 查询某一个类型下的v
     */
    @GetMapping("/getValueByTypeId")
    public Result getValueByTypeId(@RequestParam("typeId") Long typeId){
        List<ValueTag> valueTagList =valueTagService.getValueByTypeId(typeId);
        return Result.success(valueTagList);
    }

    /**
     * 添加 V
     */
    @PostMapping("/create")
    public Result createValue(@RequestBody ValueTag valueTag){
        // 验证typeId 和 name
        SmartAssert.checkExpression(valueTag.getTypeId() != null, ResponseEnum.TYPE_ID_IS_NOT_NULL);
        SmartAssert.checkExpression(!StrUtil.isBlank(valueTag.getName()), ResponseEnum.NAME_IS_NOT_NULL);
        // typeId 是否合法
        SmartAssert.checkExpression(Objects.equals(valueTypeService.countById(valueTag.getTypeId()),1), ResponseEnum.VALUE_TYPE_ID_NOT_VALID);
        // name 是否重复
        Integer count = valueTagService.getValueByName(valueTag.getName());
        SmartAssert.checkExpression(Objects.equals(count,0),ResponseEnum.VALUE_TAG_NAME_EXIST);
        // 入库
        Boolean f = valueTagService.createValue(valueTag);
        return Result.success(f);
    }


    /**
     * 修改V
     */

    @PutMapping("/update")
    public Result updateValue(@RequestBody ValueTag valueTag){
        SmartAssert.checkExpression(valueTag.getId() != null, ResponseEnum.VALUE_TAG_ID_IS_NOT_NULL);
        if(StrUtil.isNotBlank(valueTag.getName())){
            // 说明有修改name
            // name 是否重复
            Integer count = valueTagService.getValueByName(valueTag.getName());
            SmartAssert.checkExpression(Objects.equals(count,0),ResponseEnum.VALUE_TAG_NAME_EXIST);
        }
        if(valueTag.getTypeId() != null){
            // 说明有修改typeId
            // typeId 是否合法
            SmartAssert.checkExpression(Objects.equals(valueTypeService.countById(valueTag.getTypeId()),1), ResponseEnum.VALUE_TYPE_ID_NOT_VALID);
        }
        Boolean f = valueTagService.update(valueTag);
        return Result.success(f);
    }


    /**
     * 删除V
     */
    @DeleteMapping("/delete")
    public Result deleteValue(@RequestParam("idList") List<Long> idList){
        SmartAssert.checkExpression(CollectionUtil.isNotEmpty(idList),ResponseEnum.VALUE_TAG_ID_NOT_NULL);
        Boolean f = valueTagService.delete(idList);
        return Result.success(f);
    }

    /**
     * 绑定kwa 和 v
     */
    @PostMapping("/bindKwaAndValue")
    public Result bindKwaAndValue(@RequestBody KwaBindReq kwaBindReq){
        String f = this.validKwaValueTagBindReqList(kwaBindReq.getKwaValueTagBindReqList());
        if(!Objects.equals(f,"OK")){
            return Result.error(f);
        }
        // 验证kwaId是否合法
        Integer count = valueTagService.countKwaByKwaId(kwaBindReq.getKwaId());
        SmartAssert.checkExpression(Objects.equals(count,1),ResponseEnum.VALUE_TAG_KWA_ID_NOT_VALID);
        Boolean b = valueTagService.bindKwaAndValue(kwaBindReq);
        return Result.success(b);
    }


    private String validKwaValueTagBindReqList(List<KwaValueTagBindReq> kwaValueTagBindReqList){
        // 验证kwaValueTagBindReqList的合法性
        // 1. id valueTagId 是否合法
        List<Long> tagIdList = kwaValueTagBindReqList.stream().map(KwaValueTagBindReq::getId).collect(Collectors.toList());
        List<KwaValueTagBindReq> sourceInfo = valueTagService.validTagIdAndName(tagIdList);
        if(!Objects.equals(sourceInfo.size(),kwaValueTagBindReqList.size())){
            return "添加的valueTag中存在非法id";
        }
        // 验证name是否合法
        Map<Long, List<KwaValueTagBindReq>> collect = sourceInfo.stream().collect(Collectors.groupingBy(KwaValueTagBindReq::getId));
        for (KwaValueTagBindReq kwaValueTagBindReq : kwaValueTagBindReqList) {
            if(!Objects.equals(kwaValueTagBindReq.getName(),collect.get(kwaValueTagBindReq.getId()).get(0).getName())){
                return "添加的valueTag中存在非法name";
            }
        }
        return "OK";
    }







}
