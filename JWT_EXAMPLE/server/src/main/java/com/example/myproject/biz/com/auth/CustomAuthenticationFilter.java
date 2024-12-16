package com.example.myproject.biz.com.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/api/login"); // 필터가 처리할 URL
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 요청 본문에서 JSON 데이터 읽기
            String requestBody = request.getReader().lines().collect(Collectors.joining());
            ObjectMapper objectMapper = new ObjectMapper();

            // JSON 데이터를 파싱하여 ID와 Password 추출
            Map<String, String> authRequest = objectMapper.readValue(requestBody, Map.class);
            String username = authRequest.get("id");
            String password = authRequest.get("password");

            System.out.println("username:" + username);
            System.out.println("password:" + password);

//            // 요청에서 ID와 Password 추출
//            String username = request.getParameter("id");
//            String password = request.getParameter("password");

            // Oracle DB에서 사용자 확인
            if (!validateUserFromDB(username, password)) {
                throw new BadCredentialsException("Invalid username or password");
            }

            // 인증 객체 생성
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, password);

            // AuthenticationManager로 인증 진행
            //return this.getAuthenticationManager().authenticate(authenticationToken);
            //return authenticationManager.authenticate(authenticationToken);
            return authenticationToken;

        } catch (Exception e) {
            throw new RuntimeException("Authentication failed");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {
        // JWT 생성
        String token = generateJwtToken(authResult);

        // 응답 헤더 또는 바디에 JWT 추가
        response.setContentType("application/json");
        response.getWriter().write("{\"token\": \"" + token + "\"}");
    }

    private String generateJwtToken(Authentication authResult) {
        String username = authResult.getName();

        return JwtUtils.generateToken(username);


//        List<String> roles = authResult.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .toList();
//
//        // JWT 생성 (예제: HMAC SHA256)
//        return Jwts.builder()
//                .setSubject(username)
//                .claim("roles", roles)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24시간
//                .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256)) // 비밀키 설정
//                .compact();
    }

    private boolean validateUserFromDB(String username, String password) {
        String sql = "SELECT COUNT(*) FROM USER WHERE ID = ? AND PASSWORD = ?";
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, username);
//            statement.setString(2, password);
//            ResultSet rs = statement.executeQuery();
//            if (rs.next()) {
//                return rs.getInt(1) > 0;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;

        return true;
    }
}
