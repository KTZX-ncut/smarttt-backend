package com.example.smartttcourse.Utils;

/**
 * 学期表名上下文持有者（线程安全）
 */
public class TermTableContextHolder {
    // 使用ThreadLocal保存当前线程的表后缀
    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    /**
     * 设置当前表后缀
     * @param suffix 表后缀
     */
    public static void setSuffix(String suffix) {
        CONTEXT.set(suffix);
    }

    /**
     * 获取当前表后缀
     * @return 表后缀
     */
    public static String getSuffix() {
        return CONTEXT.get();
    }

    /**
     * 清除当前线程的上下文
     */
    public static void clear() {
        CONTEXT.remove();
    }
}