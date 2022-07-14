package com.atguigu.service;

import com.atguigu.pojo.User;

/**
 * Date:2022/4/8
 * Author:ybc
 * Description:
 */
public interface UserService {

    /**
     * 通过用户名和密码验证登录
     * @param username
     * @param password
     * @return
     */
    User checkLogin(String username, String password);

    /**
     * 注册用户信息
     * @param user
     * @return
     */
    boolean registUser(User user);

    /**
     * 验证用户名是否可用
     * @param username
     * @return
     */
    boolean checkUsername(String username);
}
