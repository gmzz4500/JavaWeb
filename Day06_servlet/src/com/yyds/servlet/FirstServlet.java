package com.yyds.servlet;

import javax.servlet.*;
import java.io.IOException;

public class FirstServlet implements Servlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("FirstServlet-->初始化");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("FirstServlet-->服务");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("FirstServlet-->销毁");
    }
}
