package com.woniu.springboot.hutool;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import org.junit.Test;

import java.awt.*;

/**
 * @author cl
 * @Date 2020/4/1 13:50
 * 图片工具
 */
public class ImgUtilTest {
    /**
     * 缩放图片
     */
    @Test
    public void scaleImg(){
        ImgUtil.scale(
                FileUtil.file("d:\\20141020224133_Ur54c.jpeg"),
                FileUtil.file("d:\\result.jpeg"),
                //缩放比例，缩小一倍，宽、高各缩小一倍像素
                0.5f
        );
    }

    /**
     * 图片类型转换
     */
    @Test
    public void convertImg(){
        ImgUtil.convert(FileUtil.file("d:\\8.jpg"),FileUtil.file("d:\\convert-8.png"));
    }

    /**
     * 彩色图片转为黑白
     */
    @Test
    public void grayImg(){
        ImgUtil.gray(FileUtil.file("d:\\20141020224133_Ur54c.jpeg"),FileUtil.file("d:\\gray-result.jpeg"));
    }

    /**
     * 添加文字水印
     */
    @Test
    public void pressTextImg(){
        ImgUtil.pressText(
                FileUtil.file("d:\\20141020224133_Ur54c.jpeg"),
                FileUtil.file("d:\\pressText-result.jpeg"),
                "版权所有", Color.white, //文字
                new Font("黑体",Font.BOLD,35),//字体
                250,//x坐标修正值。 默认在中间，偏移量相对于中间偏移
                300,//y坐标修正值。 默认在中间，偏移量相对于中间偏移
                0.5f//透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
        );
    }
}
