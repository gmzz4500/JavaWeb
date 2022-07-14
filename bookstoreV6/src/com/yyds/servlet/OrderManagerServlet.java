package com.yyds.servlet;


import com.yyds.pojo.Order;
import com.yyds.pojo.OrderItem;
import com.yyds.service.OrderService;
import com.yyds.service.impl.OrderServiceImpl;
import com.yyds.servlet.base.ModelBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Date:2022/4/18
 * Author:ybc
 * Description:
 */
public class OrderManagerServlet extends ModelBaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    /**
     * 查询所有订单
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getAllOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service处理业务逻辑
        List<Order> orderList = orderService.getAllOrder();
        //将所有的订单信息在请求域中共享
        request.setAttribute("orderList", orderList);
        //转发到manager/order_manager所对应的页面
        processTemplate("manager/order_manager", request, response);
    }

    /**
     * 发货，status:0-->1
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void sendOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数
        String orderId = request.getParameter("orderId");
        //调用service处理业务逻辑
        orderService.updateOrderStatus(orderId, 1);
        //重定向到订单管理功能
        response.sendRedirect(request.getContextPath() + "/OrderManagerServlet?method=getAllOrder");
    }

    /**
     * 管理端查询订单详情的功能
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
        processTemplate("manager/order_details", request, response);
    }
}
