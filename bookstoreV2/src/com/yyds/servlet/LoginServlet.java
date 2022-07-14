package com.yyds.servlet;


import com.yyds.service.UserService;
import com.yyds.service.impl.UserServiceImpl;
import com.yyds.utils.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        //获取请求参数用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //调用service处理登录功能的业务逻辑
        UserService userService = new UserServiceImpl();
        //对密码进行加密
        password = MD5Util.encode(password);
        boolean flag = userService.checkLogin(username, password);
        //通过判断业务逻辑处理的结果响应浏览器
        if(flag){
            //表示登录成功，需要通过重定向跳转到登录成功页面login_success.html
            response.sendRedirect(request.getContextPath() + "/pages/user/login_success.html");
        }else{
            //表示登录失败，需要通过转发跳转到登录页面login.html
            request.getRequestDispatcher("/pages/user/login.html").forward(request, response);
        }
    }
}
