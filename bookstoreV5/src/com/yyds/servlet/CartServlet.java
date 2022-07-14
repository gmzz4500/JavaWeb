package com.yyds.servlet;

import com.yyds.bean.Result;
import com.yyds.pojo.Book;
import com.yyds.pojo.Cart;
import com.yyds.service.BookService;
import com.yyds.service.impl.BookServiceImpl;
import com.yyds.servlet.base.ModelBaseServlet;
import com.yyds.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CartServlet extends ModelBaseServlet {

    private BookService bookService = new BookServiceImpl();
    Result result = null;
    /**
     * 跳转到购物车页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void toCartPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processTemplate("cart/cart",request,response);
    }

    /**
     * 添加图书到购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void addBook2Cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //获取请求参数
            String bookId = request.getParameter("bookId");
            //根据图书的id查询图书信息
            Book book = bookService.getBookById(Integer.parseInt(bookId));
            //获取session
            HttpSession session = request.getSession();
            //获取session中共享的购物车信息
            Cart cart = (Cart)session.getAttribute("cart");
            //判断cart对象是否为null
            if (cart==null){
                //当前为第一次添加图书到购物车
                cart = new Cart();
                //将cart购物车信息在session中共享
                session.setAttribute("cart",cart);
            }
            //将图书添加到购物车中
            cart.addBook2Cat(book);
            result = new Result(true,"添加购物车成功",cart.getTotalCount());
        } catch (Exception e) {
            result = new Result(false,"添加购物车失败");
        }
        JsonUtils.writeResult(response,result);
    }

    /**
     * 获取购物车中图书的总数量
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session对象
        HttpSession session = request.getSession();
        //获取session中共享的购物车信息
        Cart cart = (Cart)session.getAttribute("cart");
        Integer count = 0;
        //判断cart对象是否为null
        if (cart != null){
            count = cart.getTotalCount();
        }
        Result result = new Result(true,"",count);
        JsonUtils.writeResult(response,result);
    }

    /**
     * 获取session中共享的购物车信息的json数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getCartJson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session对象
        HttpSession session = request.getSession();
        //获取session中共享的购物车信息
        Cart cart = (Cart)session.getAttribute("cart");
        Result result = null;
        //判断cart对象是否为null或者cart对象中没有任何的购物项
        if (cart == null || cart.getCartItemList().size() == 0){
            result = new Result(false,"");
        }else {
            result = new Result(true,"获取购物车信息成功",cart);
        }
        JsonUtils.writeResult(response,result);
    }

    /**
     * 删除购物项
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数
        String bookId = request.getParameter("bookId");
        //获取session对象
        HttpSession session = request.getSession();
        //获取session中共享的购物车信息
       Cart cart = (Cart)session.getAttribute("cart");
       //删除bookId所对应的购物项
        cart.removeCartItem(bookId);
       //判断购物车中是否还存在购物项
        Result result = null;
        if (cart.getCartItemList().size() == 0){
            result = new Result(false,"购物车中没有购物项");
        }else{
            result = new Result(true,"删除购物项成功",cart);
        }
        JsonUtils.writeResult(response,result);
    }

    /**
     * 更新书籍数量
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数
        String bookId = request.getParameter("bookId");
        String count = request.getParameter("count");
        //获取session对象
        HttpSession session = request.getSession();
        //获取session中共享的购物车信息
        Cart cart = (Cart)session.getAttribute("cart");
        //更新指定购物项的数量
        cart.updateCount(bookId,count);
        //判断购物车中是否还存在购物项
        Result result = null;
        if (cart.getCartItemList().size()==0){
            result = new Result(false,"购物车中没有购物项");
        }else {
            result = new Result(true,"删除购物项成功",cart);
        }
        JsonUtils.writeResult(response,result);
    }

    /**
     * 清除购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session对象
        HttpSession session = request.getSession();
        //获取session中共享的购物车信息
        Cart cart = (Cart) session.getAttribute("cart");
        //清空购物车信息
        cart.clearCart();
        //转发到cart
//        processTemplate("cart/cart",request,response);
//        重定向到购物车页面
        response.sendRedirect(request.getContextPath()+"/CartServlet?method=toCartPage");
    }
}

