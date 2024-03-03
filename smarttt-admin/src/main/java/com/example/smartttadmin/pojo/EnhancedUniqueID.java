package com.example.smartttadmin.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 生成唯一id
 * 传入数据库中表名
 */
public class EnhancedUniqueID {
    public static String generateEnhancedID(String name) {
        int nameHash = Math.abs(name.hashCode()*3+10);
        UUID uuid = UUID.randomUUID();
        return nameHash+"-"+uuid;
    }
}
