package com.example.catalog_service.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseCatalog {

    private String productId;
    private String productName;
    private Integer unitPrice;
    private Integer stock;
    private Date createAt;

}
