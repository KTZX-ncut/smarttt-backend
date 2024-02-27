package com.example.smartttadmin;

import com.example.smartttadmin.pojo.StMenus;
import com.example.smartttadmin.pojo.StRoleMenu;
import com.sun.javaws.IconUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.smartttadmin.pojo.EnhancedUniqueID.generateEnhancedID;
import static com.example.smartttadmin.pojo.EncryptionUtil.*;
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
        mapper.createStMenus(new StMenus(roleMenuID,"人员管理","531500340-04c5e8a8-a599-4dc2-8c8d-36112d472f2d","6",
                "/sysmangt/","1",LocalDateTime.now().toString(),"101.106",
                "基础信息管理/人员管理",""));
        List<String> stringList = mapper.getAllStRoleid();
        for(String string : stringList){
            StRoleMenu stRoleMenu = new StRoleMenu(generateEnhancedID("st_rolemenu"),string,roleMenuID,"3",LocalDateTime.now().toString(),"");
            mapper.createRoleMenus(stRoleMenu);
        }
    }

}
