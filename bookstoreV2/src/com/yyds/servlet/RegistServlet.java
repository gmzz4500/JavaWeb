package com.yyds.servlet;


import com.yyds.pojo.User;
import com.yyds.service.UserService;
import com.yyds.service.impl.UserServiceImpl;
import com.yyds.utils.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        //获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        //调用service处理注册功能的业务逻辑
        UserService userService = new UserServiceImpl();
        //对密码进行加密
        password = MD5Util.encode(password);
        User user = new User(null, username, password, email);
        boolean flag = userService.registUser(user);
        //根据业务逻辑处理的结果响应浏览器
        if(flag){
            //表示注册成功，需要通过重定向跳转到注册成功页面regist_success.html
            response.sendRedirect(request.getContextPath() + "/pages/user/regist_success.html");
        }else{
            //表示注册失败，需要通过转发跳转到注册页面regist.html
            request.getRequestDispatcher("/pages/user/regist.html").forward(request, response);
        }
    }
}
