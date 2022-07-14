package com.atguigu.servlet.base;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Date:2022/4/11
 * Author:ybc
 * Description:
 */
public class ModelBaseServlet extends ViewBaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //设置编码
            request.setCharacterEncoding("UTF-8");
            //获取请求参数
            String methodName = request.getParameter("method");
            //根据method值，动态获取要执行的方法
            Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //忽略方法的访问权限
            method.setAccessible(true);
            //执行方法
            method.invoke(this, request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
