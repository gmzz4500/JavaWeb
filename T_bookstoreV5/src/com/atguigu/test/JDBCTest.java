package com.atguigu.test;

import com.atguigu.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * Date:2022/4/8
 * Author:ybc
 * Description:
 */
public class JDBCTest {

    @Test
    public void testJDBCUtils(){
        Connection connection = JDBCUtils.getConnection();
        System.out.println(connection);
    }

}
