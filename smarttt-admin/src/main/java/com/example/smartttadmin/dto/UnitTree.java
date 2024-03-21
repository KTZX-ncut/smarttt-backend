package com.example.smartttadmin.dto;

import com.example.smartttadmin.Utils.EncryptField;
import com.example.smartttadmin.Utils.EncryptFieldSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UnitTree {
/**
 * 指定教学单位的层级列表，用于生成嵌套格式的json，返回的参数设为not_null
 */
    @EncryptField
    @JsonSerialize(using = EncryptFieldSerializer.class)//id进行加密
    private String id;//标识
    private String pid;//父节点标识
    private Integer orderno;//顺序号
    private String name;//名称
    private String innerno;//层级码
    private String obsPath;//全路径
    private String remark;//备注
    private List<UnitTree> children;//子菜单

    public UnitTree(String id,String name, List<UnitTree> unitTrees) {
        this.id = id;
        this.name = name;
        this.children = unitTrees;
    }
}

