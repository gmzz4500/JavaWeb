package com.yyds.servlet;

import javax.servlet.*;
import java.io.IOException;

public class SecondServlet implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //ServletConfig表示当前Servlet的配置信息
        //1、获取Servlet的友好名称，即web.xml中注册此Servlet的servlet-name标签的值
        String servletName = servletConfig.getServletName();
        System.out.println("SecondServlet的友好名称:"+servletName);
        //2、获取Servlet的初始化参数
        String initParam = servletConfig.getInitParameter("initParam");
        System.out.println("SecondServlet的初始化参数:"+initParam);
        //3、获取ServletContext对象
        ServletContext servletContext = servletConfig.getServletContext();

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}

