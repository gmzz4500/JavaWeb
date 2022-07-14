package com.atguigu.service.impl;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;

/**
 * Date:2022/4/8
 * Author:ybc
 * Description:
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public User checkLogin(String username, String password) {
        //通过用户名和密码同时作为条件在bs_user表中查询数据
        User user = userDao.getUserByLogin(username, password);
        return user;
    }

    @Override
    public boolean registUser(User user) {
        //通过用户名查询用户对象
        User userBySQL = userDao.getUserByRegist(user.getUsername());
        //判断userBySQL是否为null
        if(userBySQL != null){
            //表示通过用户名查询到了用户信息，即用户名已经被注册
            return false;
        }else{
            //表示通过用户名没有查询到用户信息，即用户名可以被注册，直接注册用户信息
            userDao.insertUser(user);
            return true;
        }
    }

    @Override
    public boolean checkUsername(String username) {
        //调用dao，通过用户名作为条件查询用户信息
        User user = userDao.getUserByRegist(username);
        if(user == null){
            return true;
        }
        return false;
    }
}
