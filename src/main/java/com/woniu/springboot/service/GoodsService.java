package com.woniu.springboot.service;

import com.woniu.springboot.model.Goods;

/**
 * @Author cl
 * @Date 2020/3/22 21:49
 */
public interface GoodsService {


    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Goods record);

}

