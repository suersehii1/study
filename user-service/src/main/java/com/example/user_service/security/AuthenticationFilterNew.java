package com.example.user_service.security;

import com.example.user_service.controller.UserController;
import com.example.user_service.vo.RequestLogin;
import com.example.user_service.vo.ResponseUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.lang.annotation.Repeatable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

@Slf4j
public class AuthenticationFilterNew  extends UsernamePasswordAuthenticationFilter {

    private UserController userController;
    private Environment environment;

    @Autowired
    public AuthenticationFilterNew(AuthenticationManager authenticationManager,
                                   UserController userController, Environment environment) {
        super(authenticationManager);
        setFilterProcessesUrl("/user-service/login"); // 로그인 엔드포인트 설정
        this.userController = userController;
        this.environment = environment;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            System.out.println("attemptAuthentication");

            RequestLogin creds = new ObjectMapper().readValue(req.getInputStream(), RequestLogin.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(creds.getName(), creds.getPassword(), new ArrayList<>()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException  {

        System.out.println("successfulAuthentication............");

        String userName = ((User) auth.getPrincipal()).getUsername();

        ResponseUser userInfo = null;
        try {
            userInfo = userController.getUserByName(userName);
        }catch (Exception ex) {
            ex.printStackTrace();
        }


        byte[] secretKeyBytes = Base64.getEncoder().encode(environment.getProperty("token.secret").getBytes());
        SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyBytes);

        Instant now = Instant.now();

        String token = Jwts.builder()
                .setSubject(String.valueOf(userInfo.getId()))
                .setExpiration(Date.from(now.plusMillis(Long.parseLong(environment.getProperty("token.expiration_time")))))
                .setIssuedAt(Date.from(now))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();


        res.addHeader("token", token);
        res.addHeader("userId", String.valueOf(userInfo.getId()));
    }
}
