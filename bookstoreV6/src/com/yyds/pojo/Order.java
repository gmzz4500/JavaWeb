package com.yyds.pojo;

/**
 * @ClassName: Order
 * @Author: yyd
 * @Date: 2022/4/21
 * @Description:
 */
public class Order {

    private String id;

    private String createTime;

    private Integer totalCount;

    private Double totalAmount;

    private Integer status;

    private Integer userId;

    public Order() {
    }

    public Order(String id, String createTime, Integer totalCount, Double totalAmount, Integer status, Integer userId) {
        this.id = id;
        this.createTime = createTime;
        this.totalCount = totalCount;
        this.totalAmount = totalAmount;
        this.status = status;
        this.userId = userId;
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
