package com.example.smartttadmin.mapper;

import com.example.smartttadmin.dto.MenusResponse;
import com.example.smartttadmin.dto.UnitTree;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StUnitMapper {

    /**
     * 用角色id查找该角色的所有权限（显示在侧边栏）
     * @param roleid 角色id (安全考虑后续可能改为rolecode)
     * @return 菜单列表
     */
    @Select("select * from st_menus where id in (select menuid from st_rolemenu where roleid = #{roleid} and status IN (1, 2))")
    List<MenusResponse> getMenusByRoleID(String roleid);

    List<UnitTree> getUnit(String rolecode);

    //List<UnitMangt> getUnit(String rolecode);
}
