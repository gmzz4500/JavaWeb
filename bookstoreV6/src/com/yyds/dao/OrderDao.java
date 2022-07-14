package com.yyds.dao;

import com.yyds.pojo.Order;

import java.util.List;

public interface OrderDao {
    //保存订单
    void saveOrder(Order order);

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
}
