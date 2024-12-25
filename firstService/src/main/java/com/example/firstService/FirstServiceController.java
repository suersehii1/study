package com.example.firstService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first-service")
@Slf4j
public class FirstServiceController {

    @GetMapping("welcome")
    public String welcome(HttpServletRequest req) {
        log.info("port==>"+ req.getLocalPort());
        return "Welcom to the First service";
    }
}
