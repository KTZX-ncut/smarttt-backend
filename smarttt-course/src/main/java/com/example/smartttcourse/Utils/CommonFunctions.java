package com.example.smartttcourse.Utils;


import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.dto.TreeStructure;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


public class CommonFunctions {
    /**
     * 生成ID的方法
     * @param name
     * @return
     */
    public static final String StuRoleID = "516761049-9a741546-0b55-489b-9dc4-31789ee07153";
    public static final String classroomRoleId = "516761049-a74a69b4-427d-4d7f-addb-521d0a493f8f";
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

    // 解析token
    public static Token getToken(HttpServletRequest request){
        // 从请求头或请求参数中提取出 token
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            token = request.getParameter("token");
        }
        return JwtTokenUtils.parseToken(token,TokenSK);
    }

    // 模拟一个token
    public static String getTokenTest(){
        Token token = new Token();
        token.setObsid("8aea800182e80d000182e886980c0d7a");
        return JwtTokenUtils.getToken(token,TokenSK);
    }
}

