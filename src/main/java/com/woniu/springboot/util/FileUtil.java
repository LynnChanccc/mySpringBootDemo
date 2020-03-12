package com.woniu.springboot.util;

import java.io.File;
import java.io.InputStream;

/**
 * @author cl
 * @Date 2020/3/12 13:28
 * 文件IO工具类
 */
public class FileUtil {
    /**
     * 获取输入流
     *
     * @param fileName 文件名
     * @return
     */
    public static InputStream getResourceFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
    }

    /**
     * 获取项目编译后的路径xx/target/classes目录
     *
     * @return
     */
    public static String getPath() {
        return FileUtil.class.getResource("/").getPath();
    }

    /**
     * 生成新的文件
     *
     * @param pathName
     * @return
     */
    public static File createNewFile(String pathName) {
        File file = new File(getPath() + pathName);
        if (file.exists()) {
            file.delete();
        } else {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdir();
            }
        }
        return file;
    }

    /**
     * 生成File对象
     *
     * @param pathName
     * @return
     */
    public static File readFile(String pathName) {
        return new File(getPath() + pathName);
    }
}
