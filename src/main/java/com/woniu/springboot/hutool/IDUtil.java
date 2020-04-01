package com.woniu.springboot.hutool;

import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.junit.Test;

/**
 * @author cl
 * @Date 2020/4/1 13:35
 * 在分布式环境中，唯一ID生成应用十分广泛，生成方法也多种多样，Hutool针对一些常用生成策略做了简单封装。
 */
public class IDUtil {

    /**
     * UUID全称通用唯一识别码（universally unique identifier）
     */
    @Test
    public void uuidCreate() {
        //生成的UUID是带-的字符串 e1cceaaf-1beb-4322-a796-a058900b3d76
        String uuid = IdUtil.randomUUID();
        System.out.println(uuid);
        //生成的是不带-的字符串 14af1512fde1420ba973a3e480c5ea5c
        String uuid1 = IdUtil.simpleUUID();
        System.out.println(uuid1);

    }

    /**
     * ObjectId是MongoDB数据库的一种唯一ID生成策略，是UUID version1的变种
     */
    @Test
    public void objectID() {
        //生成类似:5e8429caf3874be3a71173b2
        String next = ObjectId.next();
        System.out.println(next);
        //方法2：从Hutool-4.1.14开始提供
        String objectId = IdUtil.objectId();
        System.out.println(objectId);

    }

    /**
     * 雪花算法生成全局唯一ID
     * 分布式系统中，有一些需要使用全局唯一ID的场景，有些时候我们希望能使用一种简单一些的ID，
     * 并且希望ID能够按照时间有序生成。Twitter的Snowflake 算法就是这种生成器。
     */
    @Test
    public void snowflakeId() {
        //参数1为终端ID
        //参数2为数据中心ID
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        long l = snowflake.nextId();
        System.out.println(l);
    }


}
