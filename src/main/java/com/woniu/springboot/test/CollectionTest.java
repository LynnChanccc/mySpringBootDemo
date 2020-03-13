package com.woniu.springboot.test;

import org.junit.Test;

import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author cl
 * @Date 2020/3/13 13:57
 */
public class CollectionTest {
    @Test
    public void mapTest() {
        //Hashtable本身比较低效,因为它的实现基本就是put get size等各种方法加 上"synchronized"来实现同步。
        Hashtable<Integer, Integer> hashtable = new Hashtable<>();
        new Thread(() -> hashtable.put(3, 30)).start();
        new Thread(() -> hashtable.put(2, 20)).start();
        new Thread(() -> hashtable.put(1, 10)).start();
        System.out.println(hashtable);
    }

    @Test
    public void concurrentHashMapTest() {
        //基于分离式锁实现的ConcurrentHashMap并发实现
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        new Thread(() -> map.put(3, 30)).start();
        new Thread(() -> map.put(2, 20)).start();
        new Thread(() -> map.put(1, 10)).start();
        System.out.println(map);
    }




}
