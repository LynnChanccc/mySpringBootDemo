package com.woniu.springboot.mapper;

import com.woniu.springboot.model.User;
import com.woniu.springboot.util.SimpleInsertObjectUtil;
import com.woniu.springboot.util.SimpleUpdateObjectUtil;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author cl
 * @Date 2020/3/7 13:17
 */
@Repository
public interface UserMapper {

    void addUser(User user);

    @Select("select * from user where name = #{name}")
    User queryByName(String name);


    @Select("select * from user")
    List<User> queryUserList();

    @Insert("insert into user(#{user})")
    @Lang(SimpleInsertObjectUtil.class)
    void insertUser(User user);

}
