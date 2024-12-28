package com.example.user_service.vo;

import lombok.Data;

import java.util.List;

@Data
public class ResponseUser {

    private int id;
    private String name;
    private String email;
    private String password;
    private List<ResponseOrder> orders;
}
