package com.atguigu.servlet;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.pojo.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import com.atguigu.servlet.base.ModelBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Date:2022/4/16
 * Author:ybc
 * Description:
 */
public class OrderClientServlet extends ModelBaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    /**
     * 结账
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session
        HttpSession session = request.getSession();
        //获取session中共享的购物车信息和用户信息
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        //调用service处理业务逻辑
        String orderId = orderService.checkout(cart, user);
        //将购物车清空
        session.removeAttribute("cart");
        //将订单号在session中共享
        session.setAttribute("orderId", orderId);
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
        processTemplate("cart/checkout", request, response);
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

    /**
     * 收货，status：1-->2
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void takeOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数
        String orderId = request.getParameter("orderId");
        //调用service处理业务逻辑
        orderService.updateOrderStatus(orderId, 2);
        //重定向到我的订单功能
        response.sendRedirect(request.getContextPath() + "/OrderClientServlet?method=getMyOrder");
    }

    /**
     * 客户端查询订单详情的功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数
        String orderId = request.getParameter("orderId");
        //调用service处理业务逻辑
        List<OrderItem> orderItemList = orderService.getDetails(orderId);
        //将订单详情在请求域中共享
        request.setAttribute("orderItemList", orderItemList);
        //转发到order/order_details所对应的页面
        processTemplate("order/order_details", request, response);
    }
}
