package com.yyds.dao.impl;

import com.yyds.dao.BaseDao;
import com.yyds.dao.OrderItemDao;
import com.yyds.pojo.OrderItem;

import java.util.List;

/**
 * @ClassName: OrderItemDaoImpl
 * @Author: yyd
 * @Date: 2022/4/21
 * @Description:
 */
public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {
    @Override
    public void saveOrderItem(OrderItem orderItem) {
        String sql = "insert into bs_orderitem values(null,?,?,?,?,?,?)";
        update(sql,orderItem.getBookName(),orderItem.getPrice(),orderItem.getImgPath(),orderItem.getCount(),orderItem.getAmount(),orderItem.getOrderId());
    }
    @Override
    public List<OrderItem> getDetails(String orderId) {
        String sql = "select id,book_name bookName,price,img_path imgPath,count,amount,order_id orderId from bs_orderitem where order_id = ?";
        return getBeanList(OrderItem.class, sql, orderId);
    }
}
