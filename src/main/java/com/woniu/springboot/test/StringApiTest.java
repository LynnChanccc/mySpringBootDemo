package com.woniu.springboot.test;

/**
 * @author cl
 * @Date 2020/3/10 13:08
 * 字符串API测试
 */
public class StringApiTest {
    public static void main(String[] args) {
        String str = "a,2,3,b,3,v,";
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        //删除最后一个","号，动态拼接有时可能会用上
        sb.deleteCharAt(sb.lastIndexOf(","));
        System.out.println(sb);
    }
}
