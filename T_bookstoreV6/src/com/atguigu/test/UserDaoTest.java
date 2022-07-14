package com.atguigu.test;

import com.atguigu.dao.BaseDao;
import com.atguigu.pojo.User;
import org.junit.Test;

import java.util.List;

/**
 * Date:2022/4/8
 * Author:ybc
 * Description:
 */
public class UserDaoTest extends BaseDao<User> {

    @Test
    public void testUpdate(){
        String sql = "insert into bs_user values(null,?,?,?)";
        int result = update(sql, "admin", "123456", "123456@qq.com");
        System.out.println("result:"+result);
    }

    @Test
    public void testgetBean(){
        String sql = "select * from bs_user where id = ?";
        User user = getBean(User.class, sql, 1);
        System.out.println(user);
    }

    @Test
    public void testgetBeanList(){
        String sql = "select * from bs_user";
        List<User> list = getBeanList(User.class, sql);
        list.forEach(System.out::println);
    }

    @Test
    public void testgetSingleData(){
        String sql = "select count(*) from bs_user";
        Object o = getSingleData(sql);
        System.out.println(o);
    }

}
