package com.example.myproject.biz.mapper;


import com.example.myproject.biz.com.CustomResultMap;
import com.example.myproject.biz.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    User getUserById(@Param("id") int id); // 매퍼 XML에 정의된 SQL과 매핑되는 메서드
    List<CustomResultMap> getAllUser();
    List<CustomResultMap> getUserRoles();
}
