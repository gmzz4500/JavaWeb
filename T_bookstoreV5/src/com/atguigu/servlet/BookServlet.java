package com.atguigu.servlet;

import com.atguigu.pojo.Book;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.servlet.base.ModelBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Date:2022/4/12
 * Author:ybc
 * Description:
 */
public class BookServlet extends ModelBaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 查询所有的图书信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getAllBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service处理业务逻辑
        List<Book> bookList = bookService.getAllBook();
        //将所有的图书信息在请求域中共享
        request.setAttribute("bookList", bookList);
        //转发到manager/book_manager所对应的视图
        processTemplate("manager/book_manager", request, response);
    }

    /**
     * 删除图书信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数bookId
        Integer bookId = Integer.parseInt(request.getParameter("bookId"));
        //调用service处理业务逻辑
        bookService.deleteBook(bookId);
        //重新访问列表功能
        response.sendRedirect(request.getContextPath() + "/BookServlet?method=getAllBook");
    }

    /**
     * 跳转到book_add.html页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void toAddPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processTemplate("manager/book_add", request, response);
    }

    /**
     * 实现添加图书或修改图书的功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void editBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数
        String bookId = request.getParameter("bookId");
        String bookName = request.getParameter("bookName");
        Double price = Double.parseDouble(request.getParameter("price"));
        String author = request.getParameter("author");
        Integer sales = Integer.parseInt(request.getParameter("sales"));
        Integer stock = Integer.parseInt(request.getParameter("stock"));
        if(bookId == null){
            //表示实现添加功能
            //调用service处理业务逻辑
            Book book = new Book(null, bookName, author, price, sales, stock);
            bookService.addBook(book);
        }else{
            //表示实现修改功能
            //调用service处理业务逻辑
            Book book = new Book(Integer.parseInt(bookId), bookName, author, price, sales, stock);
            bookService.updateBook(book);
        }
        //重新访问列表功能
        response.sendRedirect(request.getContextPath() + "/BookServlet?method=getAllBook");
    }

    /**
     * 跳转到book_edit.html页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void toEditPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数
        Integer bookId = Integer.parseInt(request.getParameter("bookId"));
        //调用service处理业务逻辑
        Book book = bookService.getBookById(bookId);
        //将要修改的图书信息在请求域中共享
        request.setAttribute("book", book);
        //转发到manager/book_edit所对应的页面
        processTemplate("manager/book_edit", request, response);
    }
}
