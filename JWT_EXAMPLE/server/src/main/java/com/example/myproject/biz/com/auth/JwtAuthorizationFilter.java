package com.example.myproject.biz.com.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String uri = request.getRequestURI();
        if (uri.equals("/api/portal/menu")) {
            System.out.println(" 그냥 pass");

        } else {
            String header = request.getHeader("Authorization");
            if (header == null || !header.startsWith("Bearer ")) {
                chain.doFilter(request, response);
                return;
            }
            String token = header.replace("Bearer ", "");
            String username = JwtUtils.validateTokenAndGetUsername(token);
            if (username != null) {
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(username, null, null)
                );
            }
        }

        chain.doFilter(request, response);
    }
}