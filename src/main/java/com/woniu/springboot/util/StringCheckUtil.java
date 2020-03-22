package com.woniu.springboot.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cl
 * @Date 2020/3/22 13:56
 * 字符串工具类
 */
public class StringCheckUtil {

    /**
     * 判断字符串是否为null或空串，是就返回true
     * @param string
     * @return
     */
    public static Boolean isEmpty(String string) {
        return string == null || "".equals(string.trim());
    }

    /**
     * 验证手机号码合法性，合法返回true
     * @param telephone
     * @return

     */
    public static Boolean isPhone(String telephone){
        Pattern pattern = Pattern.compile("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$");
        Matcher matcher = pattern.matcher(telephone);
        return matcher.matches();
    }


    public static void main(String[] args) {
        System.out.println(StringCheckUtil.isPhone("18084076960"));
    }
}
