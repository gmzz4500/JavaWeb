package com.yyds.dao.impl;

import com.yyds.dao.BaseDao;
import com.yyds.dao.OrderDao;
import com.yyds.pojo.Order;

import java.util.List;

/**
 * @ClassName: OrderDaoImpl
 * @Author: yyd
 * @Date: 2022/4/21
 * @Description:
 */
public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {
    @Override
    public void saveOrder(Order order) {
        String sql = "insert into bs_order values(?,?,?,?,?,?)";
        update(sql,order.getId(),order.getCreateTime(),order.getTotalCount(),order.getTotalAmount(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> getMyOrder(Integer userId) {
        String sql = "select id,DATE_FORMAT(create_time,'%Y-%m-%d %T') createTime,total_count totalCount,total_amount totalAmount,status,user_id userId from bs_order where user_id = ?";
        return getBeanList(Order.class, sql, userId);
    }

    @Override
    public List<Order> getAllOrder() {
        String sql = "select id,DATE_FORMAT(create_time,'%Y-%m-%d %T') createTime,total_count totalCount,total_amount totalAmount,status,user_id userId from bs_order";
        return getBeanList(Order.class, sql);
    }

    @Override
    public void updateOrderStatus(String orderId, int status) {
        String sql = "update bs_order set status = ? where id = ?";
        update(sql, status, orderId);
    }
}
