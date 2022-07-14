package com.atguigu.pojo;

import java.io.Serializable;

/**
 * Date:2022/4/18
 * Author:ybc
 * Description:
 */
public class Order implements Serializable {

    private String id;

    private String createTime;

    private Integer totalCount;

    private Double totalAmount;

    private Integer status;

    private Integer userId;

    public Order(String id, String createTime, Integer totalCount, Double totalAmount, Integer status, Integer userId) {
        this.id = id;
        this.createTime = createTime;
        this.totalCount = totalCount;
        this.totalAmount = totalAmount;
        this.status = status;
        this.userId = userId;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
