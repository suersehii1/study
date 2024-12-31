package com.example.order_service.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseOrder {

    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private Integer userId;
    private String orderId;
    private Date createAt;


}
