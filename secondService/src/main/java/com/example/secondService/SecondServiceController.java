package com.example.secondService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second-service")
@Slf4j
public class SecondServiceController {

    @GetMapping("welcome")
    public String welcome(HttpServletRequest req) {
        log.info("port==>"+ req.getLocalPort());
        return "Welcom to the Second service";
    }
}
