package com.woniu.springboot.excelTest;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.woniu.springboot.model.ComplexHeadData;
import com.woniu.springboot.model.User;
import com.woniu.springboot.util.FileUtil;
import org.junit.Test;
import java.util.*;

/**
 * @author cl
 * @Date 2020/3/12 12:53
 *  阿里巴巴easyexcel框架：
 *  EasyExcel是一个基于Java的简单、省内存的读写Excel的开源项目。在尽可能节约内存的情况下支持读写百M的Excel。
 * github地址:https://github.com/alibaba/easyexcel
 * 官方文档：
 * https://alibaba-easyexcel.github.io/index.html
 * 简单excel写出实现
 */
public class ExcelWriteTest {

    /**
     * 最简单的写
     */
    @Test
    public void simpleWrite() {
        // 写法1
        String fileName = FileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        //User是excel对应的实体对象
        EasyExcel.write(fileName, User.class).sheet("模板").doWrite(data());

        //写法2
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = EasyExcel.write(fileName, User.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
        excelWriter.write(data(), writeSheet);
        // 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }

    /**
     * 根据参数忽略某一列
     */
    @Test
    public void excludeWrite() {

        String fileName = FileUtil.getPath() + "excludeOrIncludeWrite" + System.currentTimeMillis() + ".xlsx";
        //根据用户传入字段，假设我们要忽略性别
        Set<String> excludeColumnFiledNames = new HashSet<>();
        excludeColumnFiledNames.add("sex");
        EasyExcel.write(fileName, User.class).excludeColumnFiledNames(excludeColumnFiledNames).sheet("sheet1")
                .doWrite(data());

    }

    /**
     * 只导出某列
     */
    @Test
    public void includeWrite() {
        String fileName = FileUtil.getPath() + "includeWrite" + System.currentTimeMillis() + ".xlsx";
//        根据用户传入字段 假设我们只要导出 name
        Set<String> includeColumnFileName = new HashSet<>();
        includeColumnFileName.add("name");
        EasyExcel.write(fileName, User.class).includeColumnFiledNames(includeColumnFileName).sheet("sheet1")
                .doWrite(data());


    }

    /**
     * 复杂表头写入
     */
    @Test
    public void complexHeadWrite() {
        String fileName = FileUtil.getPath() + "complexHeadWrite" + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(fileName, ComplexHeadData.class).sheet("sheet1").doWrite(complexHeadData());
    }

    /**
     * 重复多次写入（写到单个或者多个Sheet）
     */
    @Test
    public void repeatedWrite() {
        //方法1 写到同一个sheet
        String fileName = FileUtil.getPath() + "repeatedWrite" + System.currentTimeMillis() + ".xlsx";
        ExcelWriter excelWriter = EasyExcel.write(fileName, ComplexHeadData.class).build();
        WriteSheet sheet = EasyExcel.writerSheet("sheet1").build();
        for (int i = 0; i < 5; i++) {
            // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
            List<ComplexHeadData> complexHeadData = complexHeadData();
            excelWriter.write(complexHeadData, sheet);
        }
        excelWriter.finish();

        //方法2 如果写到不同的sheet 同一个对象
        fileName = FileUtil.getPath() + "repeatedWrite" + System.currentTimeMillis() + ".xlsx";
        excelWriter = EasyExcel.write(fileName, ComplexHeadData.class).build();
        // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
        for (int i = 0; i < 5; i++) {
            // 每次都要创建writeSheet 这里注意必须指定sheetNo
            sheet = EasyExcel.writerSheet(i, "sheet" + (i + 1)).build();
            // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
            List<ComplexHeadData> complexHeadData = complexHeadData();
            excelWriter.write(complexHeadData, sheet);
        }
        excelWriter.finish();

    }

    /**
     * 通用数据生成
     *
     * @return
     */
    private List<User> data() {
        ArrayList<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setSex("男");
            user.setAge(20);
            user.setId(23123123L);
            user.setName("老王");
            list.add(user);
        }
        return list;
    }

    /**
     * 通用数据生成
     *
     * @return
     */
    private List<ComplexHeadData> complexHeadData() {
        ArrayList<ComplexHeadData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ComplexHeadData data = new ComplexHeadData();
            data.setDate(new Date());
            data.setDoubleData(0.56);
            data.setString("字符串" + i);
            list.add(data);
        }
        return list;
    }
}
