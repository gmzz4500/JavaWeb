package com.yyds.servlet;

import com.yyds.pojo.Book;
import com.yyds.service.BookService;
import com.yyds.service.impl.BookServiceImpl;
import com.yyds.servlet.base.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProtalServlet extends ViewBaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取bookService对象
        BookService bookService = new BookServiceImpl();
        //查询所有的图书信息
        List<Book> bookList = bookService.getAllBook();
        //将所有的图书信息在请求域中共享
        request.setAttribute("bookList",bookList);
        processTemplate("index",request,response);
    }
}
