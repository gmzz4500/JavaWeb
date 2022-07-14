package com.atguigu.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Date:2022/4/16
 * Author:ybc
 * Description:
 */
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //获取session对象
        HttpSession session = request.getSession();
        //获取session中共享的用户信息
        Object user = session.getAttribute("user");
        //判断user是否为null
        if(user == null){
            //表示当前未登录，需要跳转到登录页面，并且展示提示信息
            request.setAttribute("errorMsg", "订单功能请先登录");
            request.getRequestDispatcher("/UserServlet?method=toLogin").forward(request, response);
        }else{
            //表示已登录，放行
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
