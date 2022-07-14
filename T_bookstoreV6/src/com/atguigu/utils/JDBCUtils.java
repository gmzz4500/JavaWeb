package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Date:2022/4/8
 * Author:ybc
 * Description:
 */
public class JDBCUtils {

    private static DataSource dataSource;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    static {
        try {
            Properties prop = new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            prop.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取链接对象
     * @return
     */
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = threadLocal.get();
            if(connection == null){
                connection = dataSource.getConnection();
                threadLocal.set(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 将连接对象返还给druid数据源
     * @param connection
     */
    public static void closeConnection(Connection connection){
        if(connection != null){
            try {
                connection.close();
                threadLocal.remove();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
