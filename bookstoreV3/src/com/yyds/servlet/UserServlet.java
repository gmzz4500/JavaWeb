package com.yyds.servlet;

import com.yyds.pojo.User;
import com.yyds.service.UserService;
import com.yyds.service.impl.UserServiceImpl;
import com.yyds.servlet.base.ModelBaseServlet;
import com.yyds.utils.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends ModelBaseServlet {
    /**
     * 验证登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            //表示登录成功,需要通过重定向跳跃到登录成功页面Login_success.html
            response.sendRedirect(request.getContextPath() + "/UserServlet?method=toLoginSuccess");
        }else {
            //表示登录失败,需要通过转发跳跃到登录页面Login.html
            processTemplate("user/login",request,response);
        }
    }

    /**
     * 实现注册功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        //调用service处理注册功能的业务逻辑
        UserService userService = new UserServiceImpl();
        //对密码进行加密
        password = MD5Util.encode(password);
        User user = new User(null,username,password,email);
        boolean flag = userService.registUser(user);
        //根据业务逻辑处理的结果响应浏览器
        if(flag){
            //表示注册成功,需要通过重定向跳转到注册成功页面regist_success.html
            response.sendRedirect(request.getContextPath() + "/UserServlet?method=toRegistSuccess");
        }else {
            //表示注册失败,需要通过转发跳转到注册页面regist.html
            processTemplate("user/regist",request,response);
        }
    }

    /**
     * 跳转到登录页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void toLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processTemplate("user/login",request,response);
    }

    /**
     * 跳转到注册页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void toRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processTemplate("user/regist",request,response);
    }


    /**
     * 跳转到登录成功页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void toLoginSuccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processTemplate("user/login_success",request,response);
    }

    /**
     * 跳转到注册成功页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void toRegistSuccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processTemplate("user/regist_success",request,response);
    }
}
