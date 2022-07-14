package com.yyds.test;


import com.yyds.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;


public class JDBCTest {

    @Test
    public void testJDBCUtils(){
        Connection connection = JDBCUtils.getConnection();
        System.out.println(connection);
    }

}
