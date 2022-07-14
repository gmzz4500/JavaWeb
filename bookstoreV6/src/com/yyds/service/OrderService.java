package com.yyds.service;

import com.yyds.pojo.Cart;
import com.yyds.pojo.Order;
import com.yyds.pojo.OrderItem;
import com.yyds.pojo.User;

import java.util.List;

/**
 * @ClassName OrderService
 * @Author yyd
 * @Date 2022/4/21
 * @Description TODO
 */
public interface OrderService {

    /**
     * 结账
     * @param cart
     * @param user
     * @return
     */
    String checkout(Cart cart, User user);

    /**
     * 我的订单
     * @param userId
     * @return
     */
    List<Order> getMyOrder(Integer userId);

    /**
     * 查询所有订单
     * @return
     */
    List<Order> getAllOrder();

    /**
     * 更新订单的状态
     * @param orderId
     * @param status
     */
    void updateOrderStatus(String orderId, int status);

    /**
     * 查询订单详情
     * @param orderId
     * @return
     */
    List<OrderItem> getDetails(String orderId);
}
