package com.atguigu.service;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.pojo.User;

import java.util.List;

/**
 * Date:2022/4/18
 * Author:ybc
 * Description:
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
     * 查询我的订单
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
