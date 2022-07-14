package com.yyds.dao;


import com.yyds.pojo.User;


public interface UserDao {

    /**
     * 根据用户名和密码查询用户对象
     * @param username
     * @param password
     * @return
     */
    User getUserByLogin(String username, String password);

    /**
     * 注册功能中通过用户名查询用户对象
     * @param username
     * @return
     */
    User getUserByRegist(String username);

    /**
     * 添加用户信息
     * @param user
     */
    void insertUser(User user);
}
