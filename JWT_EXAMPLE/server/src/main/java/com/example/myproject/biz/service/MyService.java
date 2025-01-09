package com.example.myproject.biz.service;

import com.example.myproject.biz.com.CustomResultMap;
import com.example.myproject.biz.com.auth.JwtUtils;
import com.example.myproject.biz.dto.LoginRequest;
import com.example.myproject.biz.dto.User;
import com.example.myproject.biz.mapper.UserMapper;
import com.example.myproject.biz.trace.MapperBeanChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:9090")
@RequestMapping("/api") // API 기본 경로
public class MyService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MapperBeanChecker beanChecker;

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
//        System.out.println("login start");
//        if ("admin".equals(request.getId()) && "password".equals(request.getPassword())) {
//            String token = JwtUtils.generateToken(request.getId());
//            return ResponseEntity.ok(Collections.singletonMap("token", token));
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//    }







    // ID로 사용자 정보 조회
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") int id) {
        System.out.println("start..");

        beanChecker.check();

        return userMapper.getUserById(id); // MyBatis Mapper 직접 호출
    }

    // ID로 사용자 정보 조회
    @GetMapping("/user/all")
    public List<CustomResultMap> getAllUser() {
        System.out.println("start..");
        return userMapper.getAllUser();
    }

    // 모든 유저의 role을 가져온다.
    @GetMapping("/user/getUserRoles")
    public List<CustomResultMap> getUserRoles() {
        System.out.println("getUserRoles-start..");
        return userMapper.getUserRoles();
    }
}
