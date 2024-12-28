package com.example.user_service.mapper;

import com.example.user_service.com.CustomResultMap;
import com.example.user_service.vo.ResponseUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface UserMapper {

    List<ResponseUser> getUserAll();
    ResponseUser getUserByUserId(String userId);
    ResponseUser getUserByName(String userName);
    void createUser(Map<String,Object> insertData);
}
