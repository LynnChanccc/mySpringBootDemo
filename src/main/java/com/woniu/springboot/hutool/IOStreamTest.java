package com.woniu.springboot.hutool;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import org.junit.Test;

import java.io.*;

/**
 * @author cl
 * @Date 2020/4/1 13:10
 * hutool类库IO流相关
 * IO工具类的存在主要针对InputStream、OutputStream、Reader、Writer封装简化，并对NIO相关操作做封装简化
 */
public class IOStreamTest {

    /**
     * 文件拷贝
     */
    @Test
    public void copyFile(){
        BufferedInputStream in = FileUtil.getInputStream("D:\\test.txt");
        BufferedOutputStream out = FileUtil.getOutputStream("D:\\test2.txt");
        long copy = IoUtil.copy(in, out, IoUtil.DEFAULT_BUFFER_SIZE);
        IoUtil.close(in);
        IoUtil.close(out);
    }

    /**
     * 读取文件
     * @throws IOException
     */
    @Test
    public void readFile() throws IOException {
        BufferedReader reader = IoUtil.getReader(FileUtil.getReader("D:\\test.txt", "utf-8"));
        String readLine = reader.readLine();
        System.out.println(readLine);

    }


}
