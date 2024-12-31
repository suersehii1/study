package com.example.user_service.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseOrder {

    private String orderId;
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private Date createAt;


}
