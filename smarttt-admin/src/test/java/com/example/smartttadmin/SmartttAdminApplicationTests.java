package com.example.smartttadmin;

import com.example.smartttadmin.pojo.StMenus;
import com.example.smartttadmin.pojo.StRoleMenu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import static com.example.smartttadmin.Utils.CommonFunctions.generateEnhancedID;
import static com.example.smartttadmin.Utils.EncryptionUtil.*;
@SpringBootTest
class SmartttAdminApplicationTests {
    @Autowired
    private Mapper mapper;
    @Test
    void contextLoads() {
        String ID = generateEnhancedID("st_menus");
        System.out.println(ID);
        String jiami = encrypt(ID);
        System.out.println(jiami);
        System.out.println(decrypt(jiami));

    }
    @Test
    void createMenus() {
        String roleMenuID = generateEnhancedID("st_menus");
        mapper.createStMenus(new StMenus(roleMenuID,"复制往届课程","531500340-536f98a8-b11f-480a-a511-0c4d2f51fc35","11",
                "/coursemangt/pastCourse","1",LocalDateTime.now().toString(),"103.111",
                "/课程管理模块/复制往届课程",""));
        List<String> stringList = mapper.getAllStRoleid();
        for(String string : stringList){
            StRoleMenu stRoleMenu = new StRoleMenu(generateEnhancedID("st_rolemenu"),string,roleMenuID,"3",LocalDateTime.now().toString(),"");
            mapper.createRoleMenus(stRoleMenu);
        }
    }
    @Test
    void dealRoleMenus(){
        List<String> stringList = mapper.getAllStRoleid();
        List<String> stringList1 = mapper.getAllMenuid();
        for(String roleID : stringList){
            for(String menuID :stringList1){
                if(mapper.getrolemenu(roleID,menuID).isEmpty()) {
                    StRoleMenu stRoleMenu = new StRoleMenu(generateEnhancedID("st_rolemenu"), roleID, menuID, "3", LocalDateTime.now().toString(), "");
                    mapper.createRoleMenus(stRoleMenu);
                }
            }
        }
        mapper.deleteRoleMenu();
    }

}