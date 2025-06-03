package com.example.smartttcourse.Utils;

import com.example.smartttcourse.mapper.CmTermMapper;
import com.example.smartttcourse.service.CmTermService;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Properties;
import java.util.regex.Pattern;
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class DynamicTableInterceptor implements Interceptor {
    private static final Logger logger = LoggerFactory.getLogger(DynamicTableInterceptor.class);

    @Lazy
    @Autowired
    private CmTermMapper cmTermMapper;


    // 表名模式匹配正则
    private static final Pattern TABLE_PATTERN = Pattern.compile("(\\b)(\\w+)(\\b)");

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler handler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(handler);

        // 获取MappedStatement
        MappedStatement ms = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        String mapperId = ms.getId();

        try {
            // 解析Mapper接口和方法
            String className = mapperId.substring(0, mapperId.lastIndexOf("."));
            String methodName = mapperId.substring(mapperId.lastIndexOf(".") + 1);
            Class<?> mapperClass = Class.forName(className);
            Method method = findMethod(mapperClass, methodName);

            // 检查方法是否有@DynamicTable注解
            if (method == null || !method.isAnnotationPresent(DynamicTable.class)) {
                return invocation.proceed();
            }

            // 获取注解配置
            DynamicTable annotation = method.getAnnotation(DynamicTable.class);
            String baseTableName = annotation.value();
            boolean forceReplace = annotation.force();

            // 获取原始SQL
            BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
            String originalSql = boundSql.getSql();

            // 获取表后缀
            String tableSuffix = getTableSuffix(boundSql,annotation.isCurrent());
            if (tableSuffix == null || tableSuffix.isEmpty()) {
                logger.warn("No table suffix found for SQL: {}", originalSql);
                return invocation.proceed();
            }

            // 替换表名
            String newSql = replaceTableName(originalSql, baseTableName, tableSuffix, forceReplace);
            metaObject.setValue("delegate.boundSql.sql", newSql);

            if (logger.isDebugEnabled()) {
                logger.debug("Replaced table in SQL: {} => {}", originalSql, newSql);
            }

        } catch (ClassNotFoundException e) {
            logger.error("Mapper class not found for id: {}", mapperId, e);
        }

        return invocation.proceed();
    }

    /**
     * 查找匹配的方法
     */
    private Method findMethod(Class<?> mapperClass, String methodName) {
        for (Method method : mapperClass.getMethods()) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }

    /**
     * 替换SQL中的表名
     */
    private String replaceTableName(String sql, String baseTableName, String suffix, boolean force) {
        // 构建匹配模式
        String pattern = "(?i)(\\b)" + Pattern.quote(baseTableName) + "(\\b)";
        if (!force) {
            pattern = "(?i)(\\b)" + Pattern.quote(baseTableName) + "(?!" + Pattern.quote("_") + ")(\\b)";
        }

        return sql.replaceAll(pattern, "$1" + baseTableName + "_" + suffix + "$2");
    }

    /**
     * 获取表后缀
     */
    private String getTableSuffix(BoundSql boundSql, boolean isCurrent) {
        if(isCurrent){
           return cmTermMapper.getCurrentTermNo();
        }
        else{
            String suffix = TermTableContextHolder.getSuffix();
            if (suffix != null) {
                return suffix;
            }
        }
        return "0";
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // 可以接收mybatis配置的参数
    }
}
