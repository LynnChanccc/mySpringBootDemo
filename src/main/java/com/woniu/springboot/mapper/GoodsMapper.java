package com.woniu.springboot.mapper;

import com.woniu.springboot.model.Goods;
import org.springframework.stereotype.Repository;

/**
 * @Author cl
 * @Date 2020/3/22 21:49
 * --@Repository注解其实可以不用加，但是如果业务层注入数据层mapper对象是用@Autowired时，idea抛红，就需要加一下了
 */
public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Goods record);
}