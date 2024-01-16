package com.example.smartttadmin.mapper;

import com.example.smartttadmin.dto.MenusResponse;
import com.example.smartttadmin.pojo.StMenus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface StMenusMapper {
    //用该用户的该角色查找对应的菜单栏
    @Select("select * from st_menus where id in (select menuid from st_rolemenu where roleid = #{roleid})")
    List<MenusResponse> getMenusByRoleid(String roleid);
}
