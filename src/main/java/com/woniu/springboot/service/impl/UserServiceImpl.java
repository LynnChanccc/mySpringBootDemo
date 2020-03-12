package com.woniu.springboot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.springboot.mapper.UserMapper;
import com.woniu.springboot.model.User;
import com.woniu.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author cl
 * @Date 2020/3/7 13:57
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public User queryByName(String name) {
        return userMapper.queryByName(name);
    }

    @Override
    public List<User> queryUserList() {
        //缓存具体使用
        //先从缓存里面取
        List<User> list;
        list = (List<User>) redisTemplate.opsForValue().get("queryUserList::");
        if (list != null) { //缓存中有数据
            System.out.println("从缓存中读取数据");
            return list;
        }
        //缓存没数据，查询数据库
        System.out.println("从数据库中取");
        list = userMapper.queryUserList();
        //打印日志
        log.info("查询出的所有用户 -> [{}]", JSONObject.toJSONString(list));
        //手动设置缓存,2分钟后缓存过期
        redisTemplate.opsForValue().set("queryUserList::",list,120, TimeUnit.SECONDS);
        return list;
    }

    @Override
    public PageInfo pageHelperQuery(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> list = userMapper.queryUserList();
        PageInfo<User> userPageInfo = new PageInfo<>(list);
        return userPageInfo;
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }


}
