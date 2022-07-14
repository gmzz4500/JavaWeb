package com.yyds.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cart implements Serializable {

    private Map<String,CartItem> map = new LinkedHashMap<>();

    private Integer totalCount;

    private Double totalAmount;


    public Integer getTotalCount() {
        totalCount = 0;
        for (CartItem cartItem : getCartItemList()) {
            totalCount += cartItem.getCount();
        }
        return totalCount;
    }

    public Double getTotalAmount() {
        BigDecimal bigDecimal1 = new BigDecimal("0.0");
        for (CartItem cartItem : getCartItemList()) {
            BigDecimal bigDecimal2 = new BigDecimal(cartItem.getAmount() + "");
            bigDecimal1 = bigDecimal1.add(bigDecimal2);
        }
        return bigDecimal1.doubleValue();
    }

    public List<CartItem> getCartItemList(){
        return new ArrayList<>(map.values());
    }

    /**
     * 添加图书到购物车
     */
    public void addBook2Cat(Book book){
        //获取添加到购物车的图书的id
        String bookId = book.getBookId()+"";
        //通过图书的id获取map集合中存储的购物项信息
        CartItem cartItem = map.get(bookId);
        //判断cartItem对象是否为null
        if (cartItem == null){
            //表示购物车中没有此购物项
            cartItem = new CartItem();
            cartItem.setBook(book);
            cartItem.setCount(1);
            map.put(bookId,cartItem);
        }else {
            //表示购物车中有此购物项,将数量+1
           cartItem.setCount(cartItem.getCount()+1);
        }
    }

    /**
     * 删除购物车中的购物项
     * @param bookId
     */
    public void removeCartItem(String bookId){
        map.remove(bookId);
    }

    /**
     * 清空购物车
     */
    public void clearCart(){
        map.clear();
    }

    /**
     * 更新购物项的数量
     * @param bookId
     * @param countStr
     */
    public void updateCount(String bookId,String countStr){
        try {
            //将从浏览器获取的字符串类型的数量转换为数值
            int count = Integer.parseInt(countStr);
            //通过bookId获取购物项
            CartItem cartItem = map.get(bookId);
            //若count>0,则更新购物项的数量
            if (count > 0) {
                cartItem.setCount(count);
            }
            //若count=0,则将购物项删除
            if (count == 0) {
                map.remove(bookId);
            }
        } catch (NumberFormatException e) {

        }
    }
}
