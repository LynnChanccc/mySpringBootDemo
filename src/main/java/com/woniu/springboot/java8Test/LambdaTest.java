package com.woniu.springboot.java8Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author cl
 * @Date 2020/3/11 13:28
 * lambda
 * 基本语法：(parameters) -> expression
 *          或
*           (parameters) -> {
 *               statements;
 *           }
 */
public class LambdaTest {
    public static void main(String[] args) {
        String[] array = {"小王", "小黄",
                "Jack",
                "Rose", "Kobe",
                "James", "Curry",
                "Lynn"};
        //数组转集合
        List<String> players = Arrays.asList(array);

        //以前的遍历方式
        for (String p : players) {
            System.out.print(p + " ");
        }
        System.out.println();
        //使用lambda
        players.forEach(p -> System.out.print(p + " "));
        System.out.println();
        //双冒号操作符遍历
        players.forEach(System.out::println);

        //lambda实线Runnable接口
        new Thread(() -> System.out.println("Hello World!")).start();


    }
}
