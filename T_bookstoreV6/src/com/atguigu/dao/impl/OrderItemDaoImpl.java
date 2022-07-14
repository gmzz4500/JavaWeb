package com.atguigu.dao.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.pojo.OrderItem;

import java.util.List;

/**
 * Date:2022/4/18
 * Author:ybc
 * Description:
 */
public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {
    @Override
    public void saveOrderItem(OrderItem orderItem) {
        String sql = "insert into bs_orderitem values(null,?,?,?,?,?,?)";
        update(sql, orderItem.getBookName(), orderItem.getPrice(), orderItem.getImgPath(), orderItem.getCount(), orderItem.getAmount(), orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> getDetails(String orderId) {
        String sql = "select id,book_name bookName,price,img_path imgPath,count,amount,order_id orderId from bs_orderitem where order_id = ?";
        return getBeanList(OrderItem.class, sql, orderId);
    }
}
