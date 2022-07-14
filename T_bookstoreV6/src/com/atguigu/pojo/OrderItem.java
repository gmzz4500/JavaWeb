package com.atguigu.pojo;

import java.io.Serializable;

/**
 * Date:2022/4/18
 * Author:ybc
 * Description:
 */
public class OrderItem implements Serializable {

    private Integer id;

    private String bookName;

    private Double price;

    private String imgPath;

    private Integer count;

    private Double amount;

    private String orderId;

    public OrderItem(Integer id, String bookName, Double price, String imgPath, Integer count, Double amount, String orderId) {
        this.id = id;
        this.bookName = bookName;
        this.price = price;
        this.imgPath = imgPath;
        this.count = count;
        this.amount = amount;
        this.orderId = orderId;
    }

    public OrderItem() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
