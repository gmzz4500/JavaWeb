package com.atguigu.servlet;

import com.atguigu.bean.Result;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.servlet.base.ModelBaseServlet;
import com.atguigu.utils.JsonUtils;
import com.atguigu.utils.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Date:2022/4/12
 * Author:ybc
 * Description:
 */
public class UserServlet extends ModelBaseServlet {

    private UserService userService = new UserServiceImpl();

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
        //对密码进行加密
        password = MD5Util.encode(password);
        User user = userService.checkLogin(username, password);
        //通过判断业务逻辑处理的结果响应浏览器
        if(user != null){
            //表示登录成功，获取HttpSession对象，将用户信息的登录状态在session中共享
            HttpSession session = request.getSession();
            user.setPassword("");
            session.setAttribute("user", user);
            //需要通过重定向跳转到登录成功页面login_success.html
            response.sendRedirect(request.getContextPath() + "/UserServlet?method=toLoginSuccess");
        }else{
            //表示登录失败，在请求域中共享错误信息
            request.setAttribute("errorMsg", "用户名或密码错误");
            //需要通过转发跳转到登录页面login.html
            processTemplate("user/login", request, response);
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
        String code = request.getParameter("code");
        //获取HttpSession对象
        HttpSession session = request.getSession();
        //获取session中正确的验证码
        String codeInSession = (String) session.getAttribute("codeInSession");
        //判断验证码是否正确
        if(code.equals(codeInSession)){
            //验证码正确，实现注册功能
            //调用service处理注册功能的业务逻辑
            //对密码进行加密
            password = MD5Util.encode(password);
            User user = new User(null, username, password, email);
            boolean flag = userService.registUser(user);
            //根据业务逻辑处理的结果响应浏览器
            if(flag){
                //表示注册成功，需要通过重定向跳转到注册成功页面regist_success.html
                response.sendRedirect(request.getContextPath() + "/UserServlet?method=toRegistSuccess");
            }else{
                //表示注册失败，在请求域中共享错误信息
                request.setAttribute("usernameError", "用户名已经被注册");
                //需要通过转发跳转到注册页面regist.html
                processTemplate("user/regist", request, response);
            }
        }else{
            //验证码错误，将错误信息共享在请求域中，并且转发到注册页面重新注册
            request.setAttribute("codeError", "验证码错误");
            //需要通过转发跳转到注册页面regist.html
            processTemplate("user/regist", request, response);
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
        processTemplate("user/login", request, response);
    }

    /**
     * 跳转到注册页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void toRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processTemplate("user/regist", request, response);
    }

    /**
     * 跳转到登陆成功页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void toLoginSuccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processTemplate("user/login_success", request, response);
    }

    /**
     * 跳转到注册成功页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void toRegistSuccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processTemplate("user/regist_success", request, response);
    }

    /**
     * 注销（退出登录）
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session
        HttpSession session = request.getSession();
        //强制使session失效
        session.invalidate();
        response.sendRedirect(request.getContextPath());
    }

    /**
     * 通过ajax请求验证用户名是否可用
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void checkUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数
        String username = request.getParameter("username");
        //调用service处理业务逻辑
        boolean flag = userService.checkUsername(username);
        //判断flag，响应到浏览器指定的结果
        Result result = null;
        if(flag) {
            //表示用户名可用
            result = new Result(true, "用户名可用");
        }else{
            //表示用户名不可用
            result = new Result(false, "用户名已经被注册");
        }
        //将result对象响应到浏览器
        JsonUtils.writeResult(response, result);
    }
}
