package com.yyds.servlet;

import com.yyds.pojo.Cart;
import com.yyds.pojo.Order;
import com.yyds.pojo.User;
import com.yyds.service.OrderService;
import com.yyds.service.impl.OrderServiceImpl;
import com.yyds.servlet.base.ModelBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class OrderClientServlet extends ModelBaseServlet {

    private OrderService orderService = new OrderServiceImpl();
    /**
     * 实现结账功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session
        HttpSession session = request.getSession();
        //获取session中共享的购物车信息和用户信息
        Cart cart = (Cart)session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        //调用service处理业务逻辑
        String orderId = orderService.checkout(cart,user);
        //将购物车清空
        session.removeAttribute("cart");
        //将订单号在session中共享
        session.setAttribute("orderId",orderId);
        //跳转到结账成功页面
        response.sendRedirect(request.getContextPath() + "/OrderClientServlet?method=toCheckoutPage");
    }


    /**
     * 跳转到结账成功页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void toCheckoutPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processTemplate("cart/checkout",request,response);
    }

    /**
     * 我的订单
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getMyOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session
        HttpSession session = request.getSession();
        //获取session中共享的用户信息
        User user = (User) session.getAttribute("user");
        //获取用户id
        Integer userId = user.getId();
        //调用service处理业务逻辑
        List<Order> orderList = orderService.getMyOrder(userId);
        //将我的订单信息在请求域中共享
        request.setAttribute("orderList", orderList);
        processTemplate("order/order_client", request, response);
    }
}
