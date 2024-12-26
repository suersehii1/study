package com.example.user_service.controller;

import com.example.user_service.mapper.UserMapper;
import com.example.user_service.vo.Greeting;
import com.example.user_service.vo.RequestUser;
import com.example.user_service.vo.ResponseUser;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/user-service/")
public class UserController {

    private Environment env;
    private Greeting greeting;
    private UserMapper userMapper;

    @Autowired
    public UserController(Environment env, Greeting greeting, UserMapper userMapper) {
        this.env = env;
        this.greeting = greeting;
        this.userMapper = userMapper;

    }


    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUserAll() {
        List<ResponseUser> result = new ArrayList<>();
        try {
            result = this.userMapper.getUserAll();
            System.out.println(result);
        } catch( Exception ex ) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUserByUserId(@PathVariable("userId") String userId ) {

        ResponseUser result = null;
        try {
            result = this.userMapper.getUserByUserId(userId);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }



    @GetMapping("/health-check")
    public String status() {
        return String.format("It's working in User Service on PORT %s", this.env.getProperty("local.server.port"));
    }




    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@RequestBody RequestUser user) {

        try {
            System.out.println("createUser called");
            System.out.println(user.getEmail());
            System.out.println(user.getName());
            System.out.println(user.getPassword());

            Random random = new Random();
            int randomNumber = random.nextInt(1000000);

            HashMap<String, Object> insertData = new HashMap<>();
            insertData.put("id", String.valueOf(randomNumber));
            insertData.put("email", String.valueOf(user.getEmail()));
            insertData.put("password", String.valueOf(user.getPassword()));
            insertData.put("name", String.valueOf(user.getName()));

            userMapper.createUser(insertData);
        } catch (Exception ex ){
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
