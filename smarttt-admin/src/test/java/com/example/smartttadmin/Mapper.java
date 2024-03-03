package com.example.smartttadmin;

import com.example.smartttadmin.pojo.StMenus;
import com.example.smartttadmin.pojo.StRoleMenu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface Mapper {
    @Insert("insert into st_menus(id,name,pid,orderno,url,isused,createtime,levelcode,fullpath) values (#{id},#{name},#{pid},#{orderno},#{url},#{isused},#{createtime},#{levelcode},#{fullpath})" )
    void createStMenus(StMenus stMenus);
    @Select("select id from st_roles")
    List<String> getAllStRoleid();
    @Insert("INSERT INTO st_rolemenu(id,roleid,menuid,status,createtime)" +
            "values (#{id},#{roleid},#{menuid},#{status},#{createtime})")
    void createRoleMenus(StRoleMenu stRoleMenu);

}
