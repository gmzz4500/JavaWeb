package com.atguigu.filter;

import com.atguigu.utils.JDBCUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Date:2022/4/18
 * Author:ybc
 * Description:
 */
public class TransactionFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //获取连接对象
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            //表示每个servlet的执行，即功能的实现
            chain.doFilter(req, resp);
            //表示功能执行成功
            System.out.println("功能执行成功");
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //表示功能执行失败
            try {
                System.out.println("功能执行失败");
                connection.rollback();
                response.sendRedirect(request.getContextPath() + "/error.html");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            JDBCUtils.closeConnection(connection);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
