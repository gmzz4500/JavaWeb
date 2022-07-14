package com.atguigu.dao.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.UserDao;
import com.atguigu.pojo.User;

/**
 * Date:2022/4/8
 * Author:ybc
 * Description:
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {
    @Override
    public User getUserByLogin(String username, String password) {
        String sql = "select * from bs_user where username = ? and password = ?";
        return getBean(User.class, sql, username, password);
    }

    @Override
    public User getUserByRegist(String username) {
        String sql = "select * from bs_user where username = ?";
        return getBean(User.class, sql, username);
    }

    @Override
    public void insertUser(User user) {
        String sql = "insert into bs_user values(null,?,?,?)";
        update(sql, user.getUsername(), user.getPassword(), user.getEmail());
    }
}
