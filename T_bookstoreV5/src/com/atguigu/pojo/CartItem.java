package com.atguigu.pojo;

import java.math.BigDecimal;

/**
 * Date:2022/4/15
 * Author:ybc
 * Description:
 */
public class CartItem {

    private Book book;

    private Integer count;

    private Double amount;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getAmount() {
        BigDecimal bigDecimal1 = new BigDecimal(count+"");
        BigDecimal bigDecimal2 = new BigDecimal(book.getPrice()+"");
        return bigDecimal1.multiply(bigDecimal2).doubleValue();
    }
}
