package com.yyds.dao;


import com.yyds.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class BaseDao<T> {

    public int updateNew(String sql, Object... params){
        Connection connection = null;
        int result = 0;
        try {
            connection = JDBCUtils.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i+1, params[i]);
            }
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private QueryRunner queryRunner = new QueryRunner();

    /**
     * 执行增删改的SQL语句
     * @param sql：要执行的SQL
     * @param params：按顺序为通配符所赋的值
     * @return：增删改SQL执行之后受影响的行数
     */
    public int update(String sql, Object... params){
        Connection connection = null;
        int result = 0;
        try {
            connection = JDBCUtils.getConnection();
            result = queryRunner.update(connection, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return result;
    }

    /**
     * 将查询的一条数据转换为实体类对象
     * @param clazz：要转换的实体类类型的Class对象
     * @param sql：要执行的SQL
     * @param params：按顺序为通配符所赋的值
     * @return：查询的一条数据转换为的实体类对象
     */
    public T getBean(Class<T> clazz, String sql, Object... params){
        Connection connection = null;
        T t = null;
        try {
            connection = JDBCUtils.getConnection();
            t = queryRunner.query(connection, sql, new BeanHandler<>(clazz), params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return t;
    }

    /**
     * 将查询的多条数据转换为实体类集合
     * @param clazz ：要转换的实体类类型的Class对象
     * @param sql ：要执行的SQL
     * @param params ：按顺序为通配符所赋的值
     * @return：查询的多条数据转换为的实体类集合
     */
    public List<T> getBeanList(Class<T> clazz, String sql, Object... params){
        Connection connection = null;
        List<T> list = null;
        try {
            connection = JDBCUtils.getConnection();
            list = queryRunner.query(connection, sql, new BeanListHandler<>(clazz), params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return list;
    }

    /**
     * 查询单行单列的数据
     * @param sql ：要执行的SQL
     * @param params ：按顺序为通配符所赋的值
     * @return：单行单列的数据转换为的Java数据
     */
    public Object getSingleData(String sql, Object... params){
        Connection connection = null;
        Object o = null;
        try {
            connection = JDBCUtils.getConnection();
            o = queryRunner.query(connection, sql, new ScalarHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return o;
    }

}
