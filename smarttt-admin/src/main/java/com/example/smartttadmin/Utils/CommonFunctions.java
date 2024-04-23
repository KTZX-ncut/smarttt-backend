package com.example.smartttadmin.Utils;

import com.example.smartttadmin.dto.TreeStructure;

import java.util.*;


public class CommonFunctions {
    /**
     * 生成ID的方法
     * @param name
     * @return
     */
    public static final String StuRoleID = "516761049-9a741546-0b55-489b-9dc4-31789ee07153";
    public static final String TokenSK = "123456";
    public static String generateEnhancedID(String name) {
        int nameHash = Math.abs(name.hashCode()*3+10);
        UUID uuid = UUID.randomUUID();
        return nameHash+"-"+uuid;
    }

    /**
     * 生成一棵树路径
     * @param treeList 所有的树形结构都满足
     * @return 一棵树的路径，可用与生成层级码和path
     * id->(pid,orderno)
     */
    public static List<TreeStructure> generateTreeStructureList(List<TreeStructure> treeList, String id){

        // 创建映射
        Map<String, TreeStructure> idMapping = new HashMap<>();

        // 添加映射关系
        for(TreeStructure treeStructure:treeList){
            idMapping.put(treeStructure.getId(), treeStructure);
        }
        //回朔路径
        List<TreeStructure> treeStructuresList = new ArrayList<>();
        String currentId = id;
        while(!Objects.equals(currentId, "0")){
            TreeStructure treeStructure = idMapping.get(currentId);
            currentId = treeStructure.getPid();
            treeStructuresList.add(treeStructure);
        }
        Collections.reverse(treeStructuresList);
        return treeStructuresList;

    }
    /**
     * 生成层级码的方法
     * @param treeStructureList
     * @return
     */
    public static String generateLevelCode(List<TreeStructure> treeStructureList){
        StringBuilder levelCode = new StringBuilder();
        for(TreeStructure treeStructure : treeStructureList){
            levelCode.append(treeStructure.getOrderno() + 100);
            levelCode.append(".");
        }
        levelCode.deleteCharAt(levelCode.length() - 1);
        return levelCode.toString();
    }

}

