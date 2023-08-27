package com.junzhou.infop.controller;

import com.junzhou.infop.vo.BasicResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HealthController {

    @GetMapping("/")
    public BasicResultVo HealthCheck() {
        return BasicResultVo.success();
    }
}
