package com.woniu.springboot.service;

import com.github.pagehelper.PageInfo;
import com.woniu.springboot.model.User;

import java.util.List;

/**
 * @author cl
 * @Date 2020/3/7 13:43
 */
public interface UserService {
    /**
     * 添加用户
     *
     * @param user
     */
    void addUser(User user);

    User queryByName(String name);

    List<User> queryUserList();

    //分页查询
    PageInfo pageHelperQuery(Integer pageNum, Integer pageSize);

    void insertUser(User user);

    List<User> queryUser(String name,String sex);

}
