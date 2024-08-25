package com.example.smartttadmin.service.impl;

import com.example.smartttadmin.dto.*;
import com.example.smartttadmin.mapper.StMenusMapper;
import com.example.smartttadmin.service.StMenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.smartttadmin.Utils.CommonFunctions.TokenSK;
import static com.example.smartttadmin.Utils.JwtTokenUtils.getToken;

@Service
public class StMenusServiceImpl implements StMenusService {
    @Autowired
    private StMenusMapper stMenusMapper;

    public Result getMenusList(String roleid){
        List<MenuTree> allMenuTree = stMenusMapper.getMenusByRoleID(roleid);
        List<MenuTree> rootMenus = getAllMenuTree(allMenuTree,"0");
        List<MenuTree> finalMenus = new ArrayList<>();
        for(MenuTree menuTree : rootMenus){
            if(menuTree.getChildren()!=null)
                finalMenus.addAll(menuTree.getChildren());
        }
        List<MenuTree> simplifiedMenus = finalMenus.stream()
                .map(menu -> new MenuTree(menu.getId(),menu.getName(), simplifyChildren(menu.getChildren()),menu.getUrl()))
                .collect(Collectors.toList());

        return Result.success(simplifiedMenus);
    }

    private List<MenuTree> simplifyChildren(List<MenuTree> children) {
        if (children == null) {
            return null;
        }
        return children.stream()
                .map(menu -> new MenuTree(menu.getId(),menu.getName(), simplifyChildren(menu.getChildren()),menu.getUrl()))
                .collect(Collectors.toList());
    }

    @Override
    public Result getMenuTree(String id) {
        List<MenuTree> allMenuTree = stMenusMapper.getAllMenuByRoleID(id);
        List<MenuTree> rootMenus = getAllMenuTree(allMenuTree,"0");
        List<MenuTree> simplifiedMenus = rootMenus.stream()
                .map(menu -> new MenuTree(menu.getId(),menu.getName(), menu.getStatus(), simplifyChildrenForRP(menu.getChildren())))
                .collect(Collectors.toList());
        return Result.success(simplifiedMenus);
    }
    private List<MenuTree> getAllMenuTree(List<MenuTree> allMenuTree,String rootID){
        //Map<String, List<MenuTree>> menuMap = allMenuTree.stream().collect(Collectors.groupingBy(MenuTree::getPid));
        Map<String, List<MenuTree>> menuMap = allMenuTree.stream()
                .collect(Collectors.groupingBy(MenuTree::getPid,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingInt(MenuTree::getOrderno))
                                        .collect(Collectors.toList())
                        )
                ));
        List<MenuTree> rootMenus = menuMap.get(rootID); // 根菜单的pid通常为null
        // 递归构建菜单树
        buildMenuTree(rootMenus, menuMap);
        return rootMenus;
    }

    /**
     * 用于构建树形层级菜单的递归函数
     * @param parentMenus 父菜单
     * @param menuMap 菜单与pid(父节点)的映射
     */
    private void buildMenuTree(List<MenuTree> parentMenus, Map<String, List<MenuTree>> menuMap) {
        for (MenuTree parentMenu : parentMenus) {
            //找出pid为父菜单的孩子
            List<MenuTree> childMenus = menuMap.get(parentMenu.getId());
            if (childMenus != null) {
                parentMenu.setChildren(childMenus);
                buildMenuTree(childMenus, menuMap);
            }
        }
    }

    /**
     * 子菜单简单化（返回太多参数的安全问题，多余的都置为null）加上MenuTree的返回json类型被设为not null可完成简化
     * @param children 子菜单
     * @return 子菜单简单化之后的列表
     */
    private List<MenuTree> simplifyChildrenForRP(List<MenuTree> children) {
        if (children == null) {
            return null;
        }
        return children.stream()
                .map(menu -> new MenuTree(menu.getId(),menu.getName(), menu.getStatus(), simplifyChildrenForRP(menu.getChildren())))
                .collect(Collectors.toList());
    }

    @Override
    public Result  updateMenuStatus(UpdateMenuReq updateMenuReq) {
       stMenusMapper.updateMenuStatus(updateMenuReq);
       return Result.success();
    }

    @Override
    public Result getStudentCourse(String id) {
        return Result.success(stMenusMapper.getStudentCourseList(id));
    }

    @Override
    public Result getStudentCourseInfor(Token token) {
        SwitchCourseReq switchCourseReq = new SwitchCourseReq(getToken(token,TokenSK),stMenusMapper.getCourseName(token.getObsid()));
        return Result.success(switchCourseReq);
    }

    @Override
    public Result getTeachingGoal(String obsid) {

        return null;
    }

}
