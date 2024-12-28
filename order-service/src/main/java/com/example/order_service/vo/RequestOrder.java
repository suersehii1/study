package com.example.order_service.vo;

import lombok.Data;

@Data
public class RequestOrder {

    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

    private String orderId;
    private String userId;
}
