package com.woniu.springboot.test;

/**
 * @author cl
 * @Date 2020/3/11 13:02
 * 线程
 */
public class ThreadTest {
    public static void main(String[] args) {

        new Thread(() -> System.out.println("线程一执行了。。。。")).start();

        new Thread() {
            @Override
            public void run() {
                System.out.println("线程一执行了。。。。");
            }
        }.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程二执行了。。。。");
            }
        });
        t2.start();
    }
}

//多线程单列模式
class SingletonTest {

    private static SingletonTest singletonTest = null;

    private SingletonTest() {
    }

    public static SingletonTest getInstance() {
        if (singletonTest == null) {
            synchronized (SingletonTest.class) {
                if (singletonTest == null) {
                    singletonTest = new SingletonTest();
                }
            }
        }
        return singletonTest;
    }
}

