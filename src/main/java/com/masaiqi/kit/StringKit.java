package com.masaiqi.kit;

import org.springframework.lang.Nullable;

/**
 * 字符串工具类
 */
public class StringKit {

    /**
     * 判断字符串是否为空(去除空格)
     * @param str
     * @return
     */
    public static boolean isEmptyExcludeTrim(@Nullable String str){
       return ("".equals(str.trim()) || str == null);
    }
}
