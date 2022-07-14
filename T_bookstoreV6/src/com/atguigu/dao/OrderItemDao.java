package com.atguigu.dao;

import com.atguigu.pojo.OrderItem;

import java.util.List;

/**
 * Date:2022/4/18
 * Author:ybc
 * Description:
 */
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
