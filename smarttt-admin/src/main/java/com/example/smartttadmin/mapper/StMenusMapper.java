package com.example.smartttadmin.mapper;

import com.example.smartttadmin.dto.CourseListReq;
import com.example.smartttadmin.dto.MenuTree;
import com.example.smartttadmin.dto.MenusResponse;
import com.example.smartttadmin.dto.UpdateMenuReq;
import com.example.smartttadmin.pojo.StMenus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface StMenusMapper {
    /**
     * 得到全部菜单
     * @return 菜单列表
     */
    @Select("select * from st_menus")
    List<StMenus> getAllMenus();
    /**
     * 用角色id查找该角色的所有权限（显示在侧边栏）
     * @param roleid 角色id (安全考虑后续可能改为rolecode)
     * @return 菜单列表
     */
    @Select("select * from st_menus where id in (select menuid from st_rolemenu where roleid = #{roleid} and status IN (1, 2))")
    List<MenuTree> getMenusByRoleID(String roleid);

    /**
     * 用角色代码查到层级菜单列表（全部），并包括该角色的状态
     * @param id 角色代码
     * @return 层级菜单列表（配置权限）
     */
    @Select("SELECT st_menus.id,st_menus.pid,st_menus.orderno,st_menus.name,st_rolemenu.status AS status\n" +
            "FROM st_menus\n" +
            "LEFT JOIN st_rolemenu ON st_menus.id = st_rolemenu.menuid AND st_rolemenu.roleid = #{id}\n" +
            "ORDER BY st_menus.id;")
    List<MenuTree> getAllMenuByRoleID(String id);

    /*
    查找角色的权限状态
    @Select("select * from st_rolemenu where roleid = (select id from st_roles where rolecode = #{rodecode})\n" +
            "and menuid = #{updateMenuReq.id}")
    List<StRoleMenu> getMenusByRoleCodeAndName(UpdateMenuReq updateMenuReq);
     */

    /**
     * 更新角色的权限状态
     * @param updateMenuReq 用于角色更新的返回参数
     */
    @Update("update st_rolemenu set `status` = #{status} WHERE menuid = #{id} AND roleid = #{roleid}")
    void updateMenuStatus(UpdateMenuReq updateMenuReq);


    @Select("select status from st_rolemenu where roleid = #{roleid} and menuid = #{menuid}")
    List<String> getStatueInRoleUser(@Param("roleid") String roleid, @Param("menuid") String menu);

    @Select("select cm_classroom_classroommenu.classroomId as id ,cm_classroom.classroomName from cm_classroom_classroommenu,cm_classroom where cm_classroom.id=classroomId and stuId = #{id}")
    List<CourseListReq> getStudentCourseList(String id);

    @Select("select classroomName from cm_classroom where id = #{id}")
    String getCourseName(String id);
}
