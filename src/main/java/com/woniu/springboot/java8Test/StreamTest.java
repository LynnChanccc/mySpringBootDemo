package com.woniu.springboot.java8Test;

import com.woniu.springboot.model.User;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author cl
 * @Date 2020/3/6 12:07
 * java8 stream对集合的应用
 */
public class StreamTest {
    public static void main(String[] args) {
        User u1 = new User(20, (long) 1000011769, "小王", "男");
        User u2 = new User(20, (long) 1000011534, "小李", "女");
        User u3 = new User(25, (long) 1000011100, "小张", "男");
        User u4 = new User(23, (long) 1000011135, "小红", "女");
        List<User> userList = new ArrayList<>();
        userList.add(u1);
        userList.add(u2);
        userList.add(u3);
        userList.add(u4);

        //1、分组
        Map<String, List<User>> groupingBySex = userList.stream().collect(Collectors.groupingBy(User::getSex));
        //遍历map
        for (Map.Entry<String, List<User>> entry : groupingBySex.entrySet()) {
            String key = entry.getKey();
            List<User> value = entry.getValue();
            System.out.println("key:"+key+"===="+"value:"+value);
        }

        //2、过滤(找出符合条件的)
        List<User> filterUserByAge = userList.stream().filter(u -> u.getAge() != 20).collect(Collectors.toList());
        System.out.println(filterUserByAge);

        //3、求和
        int sum = userList.stream().mapToInt(User::getAge).sum();
        System.out.println("用户年龄总和:"+sum);

        //4、最值
        //最大
        Integer min = userList.stream().map(User::getAge).min(Integer::compareTo).get();
        System.out.println("年龄最小:"+min);
        //最大
        Integer max = userList.stream().map(User::getAge).max(Integer::compareTo).get();
        System.out.println("年龄最大:"+max);

        //5、排序
        //单字段排序(从小到大)
        userList.sort(Comparator.comparing(User::getId));
        System.out.println("只根据id排序后:"+userList);
        //多字段排序(先看年龄，年龄一样在根据id大小排序)
        userList.sort(Comparator.comparing(User::getAge).thenComparing(User::getId));
        System.out.println("根据年龄和id排序:"+userList);

        //6、去重
        List<Object> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(2L);
        List<Object> distinct = list.stream().distinct().collect(Collectors.toList());
        System.out.println("去重后:"+distinct);

        //7、获取list中某个字段组装新的list
        List<String> newList = userList.stream().map(u -> u.getName()).collect(Collectors.toList());
        System.out.println("获取某个字段组装新的list:"+newList);

        //8、批量设置list中某个字段为同一值
        userList.stream().forEach(u -> u.setSex("人妖"));
        System.out.println("批量设置性别:"+userList);
    }
}
