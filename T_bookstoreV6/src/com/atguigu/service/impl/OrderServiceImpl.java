package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.*;
import com.atguigu.service.OrderService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Date:2022/4/18
 * Author:ybc
 * Description:
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String checkout(Cart cart, User user) {
        //获取系统当前时间的时间戳作为订单id
        String orderId = System.currentTimeMillis() + "";
        //获取系统当前时间作为订单创建的时间
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = sdf.format(now);
        //将购物车信息转换为订单信息
        Order order = new Order(orderId, createTime, cart.getTotalCount(), cart.getTotalAmount(), 0, user.getId());
        //保存订单
        orderDao.saveOrder(order);
        //获取购物车中的购物项
        List<CartItem> cartItemList = cart.getCartItemList();
        for (CartItem cartItem : cartItemList) {
            //将购物项转换为订单项
            Book book = cartItem.getBook();
            OrderItem orderItem = new OrderItem(null, book.getBookName(), book.getPrice(), book.getImgPath(), cartItem.getCount(), cartItem.getAmount(), orderId);
            //保存订单项
            orderItemDao.saveOrderItem(orderItem);
            //更新图书的库存和销量
            bookDao.updateSalesAndStock(book.getBookId(), cartItem.getCount());
        }
        return orderId;
    }

    @Override
    public List<Order> getMyOrder(Integer userId) {
        return orderDao.getMyOrder(userId);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderDao.getAllOrder();
    }

    @Override
    public void updateOrderStatus(String orderId, int status) {
        orderDao.updateOrderStatus(orderId, status);
    }

    @Override
    public List<OrderItem> getDetails(String orderId) {
        return orderItemDao.getDetails(orderId);
    }
}
