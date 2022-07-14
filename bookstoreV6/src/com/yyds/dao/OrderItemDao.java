package com.yyds.dao;

import com.yyds.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {
    /**
     * 保存订单项
     * @param orderItem
     */
    void saveOrderItem(OrderItem orderItem);

    /**
     * 查询订单详情
     * @param orderId
     * @return
     */
    List<OrderItem> getDetails(String orderId);
}
