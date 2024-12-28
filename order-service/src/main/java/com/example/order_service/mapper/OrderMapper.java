package com.example.order_service.mapper;

import com.example.order_service.vo.ResponseOrder;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {

    void createOrder(Map<String,Object> insertOrder);
    List<ResponseOrder> findOrderByUserId(String userId);

}
