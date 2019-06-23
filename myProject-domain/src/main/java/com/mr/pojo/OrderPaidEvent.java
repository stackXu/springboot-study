package com.mr.pojo;

import lombok.Data;

import java.io.Serializable;


public class OrderPaidEvent implements Serializable {

    private String orderId;

    private int paidMoney;

    public OrderPaidEvent() {
    }

    public OrderPaidEvent(String orderId, int paidMoney) {
        this.orderId = orderId;
        this.paidMoney = paidMoney;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(int paidMoney) {
        this.paidMoney = paidMoney;
    }



}