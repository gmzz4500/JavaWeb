package com.yyds.service;


import com.yyds.pojo.User;


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
