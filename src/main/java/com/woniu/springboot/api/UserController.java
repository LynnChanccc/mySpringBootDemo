package com.woniu.springboot.api;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.woniu.springboot.config.ApiResultData;
import com.woniu.springboot.model.User;
import com.woniu.springboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cl
 * @Date 2020/3/6 14:26
 */
@RestController
@RequestMapping("/api/user")
@Api(tags = "用户接口")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUserList")
    @ApiOperation("获取用户集合")
    public ApiResultData getUserList() {
        List<User> list = new ArrayList<>();
        User user1 = new User(20, 23123123L, "小李", "女");
        User user2 = new User(22, 231212123L, "小王", "女");
        User user3 = new User(23, 231232323L, "小程", "男");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        ApiResultData apiResultData = new ApiResultData();
        apiResultData.setData(list);
        return apiResultData;
    }

    @GetMapping("/queryUserByName")
    @ApiOperation("通过姓名查询用户")
    @ApiImplicitParam(name = "name", value = "姓名")
    public ApiResultData queryUserByName(String name) {
        ApiResultData apiResultData = new ApiResultData();
        userService.queryByName(name);
        apiResultData.setData(userService.queryByName(name));
        apiResultData.setDesc("查询成功");
        System.out.println(1 / 0);
        return apiResultData;
    }

    @PostMapping("/addUser")
    @ApiOperation("添加用户")
    public ApiResultData addUser(@RequestBody User user) {
        System.out.println(user);
        userService.addUser(user);
        ApiResultData apiResultData = new ApiResultData();
        apiResultData.setDesc("添加成功");
        return apiResultData;
    }

    @GetMapping("/queryUserList")
    @ApiOperation("查询所有用户")
    public ApiResultData queryUserList() {
        List<User> userList = userService.queryUserList();
        ApiResultData apiResultData = new ApiResultData();
        apiResultData.setData(userList);
        apiResultData.setDesc("查询成功");
        return apiResultData;
    }

    @GetMapping("/pageHelperTest")
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页数"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数据条数")
    })
    public String pageHelperTest(Integer pageNum, Integer pageSize) {
        ApiResultData apiResultData = new ApiResultData();
        PageInfo pageInfo = userService.pageHelperQuery(pageNum, pageSize);
        apiResultData.setData(pageInfo);
        apiResultData.setDesc("查询成功");
        return JSONObject.toJSONString(apiResultData);
    }

    @PostMapping("/insertUser")
    @ApiOperation("插入用户")
    public ApiResultData insertUser(@RequestBody User user) {
        userService.insertUser(user);
        ApiResultData apiResultData = new ApiResultData();
        apiResultData.setDesc("插入成功");
        return apiResultData;
    }

    @GetMapping("/queryUser")
    @ApiOperation("多参数查询")
    public ApiResultData queryUser(String name,String sex){
        List<User> list = userService.queryUser(name, sex);
        ApiResultData apiResultData = new ApiResultData();
        apiResultData.setData(list);
        apiResultData.setDesc("查询成功");
        return apiResultData;
    }

    /*@PostMapping("/addUserTest")
    @ApiOperation("实体属性用作参数，并且参数类型不是application/json时，swagger会报错")
    public ApiResultData addUserTest(User user){
        System.out.println("user:"+user);
        return new ApiResultData();
    }*/
}
