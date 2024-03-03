package com.example.smartttadmin.service.impl;

import com.example.smartttadmin.dto.MenuTree;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.UnitTree;
import com.example.smartttadmin.mapper.StUnitMapper;
import com.example.smartttadmin.service.StUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
@Controller
public class StUnitServicelmpl implements StUnitService {
    @Autowired
    private StUnitMapper stUnitMapper;
    @Override
    public Result getUnitTree(String rolecode) {
        List<UnitTree> allUnitTree = stUnitMapper.getUnit(rolecode);
        Map<String, List<UnitTree>> unitMap = allUnitTree.stream().collect(Collectors.groupingBy(UnitTree::getPid));
        Map<String, List<UnitTree>> unitMap = allUnitTree.stream()
                .collect(Collectors.groupingBy(UnitTree::getPid,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingInt(UnitTree::getOrderno))
                                        .collect(Collectors.toList())
                        )
                ));
        List<UnitTree> rootMenus = unitMap.get("0"); // 根菜单的pid通常为null
        // 递归构建菜单树
        buildUnitTree(rootMenus, unitMap);
        List<UnitTree> simplifiedMenus = rootMenus.stream()
                .map(menu -> new MenuTree(menu.getId(),menu.getName(), menu.getStatus(), simplifyChildren(unit.getChildren())))
                .collect(Collectors.toList());
        return Result.success(simplifiedMenus);
    }

    /**
     * 用于构建树形层级菜单的递归函数
     * @param parentMenus 父菜单
     * @param menuMap 菜单与pid(父节点)的映射
     */
    private void buildUnitTree(List<MenuTree> parentMenus, Map<String, List<MenuTree>> menuMap) {
        for (MenuTree parentMenu : parentMenus) {
            //找出pid为父菜单的孩子
            List<MenuTree> childMenus = menuMap.get(parentMenu.getId());
            if (childMenus != null) {
                parentMenu.setChildren(childMenus);
                buildUnitTree(childMenus, menuMap);
            }
        }
    }

    /**
     * 子菜单简单化（返回太多参数的安全问题，多余的都置为null）加上MenuTree的返回json类型被设为not null可完成简化
     * @param children 子菜单
     * @return 子菜单简单化之后的列表
     */
    private List<MenuTree> simplifyChildren(List<MenuTree> children) {
        if (children == null) {
            return null;
        }
        return children.stream()
                .map(menu -> new MenuTree(menu.getId(),menu.getName(), menu.getStatus(), simplifyChildren(menu.getChildren())))
                .collect(Collectors.toList());
    }
}

