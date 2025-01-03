package com.example.user_service.controller;

import com.example.user_service.client.OrderServiceClient;
import com.example.user_service.mapper.UserMapper;
import com.example.user_service.vo.Greeting;
import com.example.user_service.vo.RequestUser;
import com.example.user_service.vo.ResponseOrder;
import com.example.user_service.vo.ResponseUser;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/user-service/")
@Slf4j
public class UserController implements UserDetailsService {

    private Environment env;
    private Greeting greeting;
    private UserMapper userMapper;
    private BCryptPasswordEncoder passwordEncoder;
    private OrderServiceClient orderServiceClient;

    @Autowired
    public UserController(Environment env, Greeting greeting, UserMapper userMapper, BCryptPasswordEncoder passwordEncoder
            , OrderServiceClient orderServiceClient) {
        this.env = env;
        this.greeting = greeting;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.orderServiceClient = orderServiceClient;
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
        List<ResponseOrder> ordersList = null;

        try {
            result = this.userMapper.getUserByUserId(userId);

            /* Using a feign client */
            /* #2 Feign exception handling */
            try {
                ordersList = orderServiceClient.findOrderByUserId(userId);
                result.setOrders(ordersList == null || ordersList.size() == 0 ? new ArrayList(): ordersList);

            } catch (FeignException ex) {
                log.error(ex.getMessage());
            }


        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }



    @GetMapping("/health-check")
    public String status() {
        return String.format("It's working in User Service on PORT ("
                +  this.env.getProperty("local.server.port") + ")"
                + ", token expiration time=" + env.getProperty("token.expiration_time")
        );
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
            insertData.put("password", passwordEncoder.encode(user.getPassword()+""));
            insertData.put("name", String.valueOf(user.getName()));

            userMapper.createUser(insertData);
        } catch (Exception ex ){
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        System.out.println("loadUserByUsername........../userName"+ userName);

        ResponseUser userInfo = userMapper.getUserByName(userName);

        if (userInfo == null)
            throw new UsernameNotFoundException(userName + ": not found");

        return new User(userInfo.getName(), userInfo.getPassword(),
                true, true, true, true,
                new ArrayList<>());
    }

    public ResponseUser getUserByName(String userName) throws Exception {
        return this.userMapper.getUserByName(userName);
    }
}
