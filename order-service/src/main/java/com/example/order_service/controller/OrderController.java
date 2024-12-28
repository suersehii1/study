package com.example.order_service.controller;

import com.example.order_service.mapper.OrderMapper;
import com.example.order_service.vo.RequestOrder;
import com.example.order_service.vo.ResponseOrder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/order-service/")
public class OrderController {
    private Environment env;
    private OrderMapper orderMapper;

    public OrderController(Environment env, OrderMapper orderMapper) {
        this.env = env;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/{userId}/order")
    public ResponseEntity createOrder(@PathVariable("userId") String userId, @RequestBody RequestOrder order) {
        try {
            System.out.println("createOrder");
            System.out.println(order.getProductId());
            System.out.println(order.getQty());
            System.out.println(order.getUnitPrice());
            System.out.println(order.getTotalPrice());
            System.out.println(userId);

            Random random = new Random();
            int randomNumber = random.nextInt(1000);

            LocalDateTime currentDate = LocalDateTime .now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String dateString = currentDate.format(formatter);

            HashMap<String, Object> insertOrder = new HashMap<>();
            insertOrder.put("orderId", dateString+randomNumber);
            insertOrder.put("productId", order.getProductId());
            insertOrder.put("qty", String.valueOf(order.getQty()));
            insertOrder.put("totalPrice", String.valueOf(order.getTotalPrice()));
            insertOrder.put("unitPrice", String.valueOf(order.getUnitPrice()));
            insertOrder.put("userId", userId);

            orderMapper.createOrder(insertOrder);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return  new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{userId}/order")
    public ResponseEntity findOrderByUserId(@PathVariable("userId") String userId) {
        List<ResponseOrder> result = null;
        try {
           result  = orderMapper.findOrderByUserId(userId);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    @GetMapping("/health-check")
    public String status() {
        return String.format("It's working in Catalog Service on PORT %s", this.env.getProperty("local.server.port"));
    }


}
