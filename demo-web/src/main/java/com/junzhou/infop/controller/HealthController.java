package com.junzhou.infop.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.junzhou.infop.vo.BasicResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HealthController {

    @GetMapping("/")
    public BasicResultVo HealthCheck() throws Exception {
        return BasicResultVo.success();
    }

    @GetMapping("/api/authcheck")
    public BasicResultVo AuthorizationCheck() {
        return BasicResultVo.success(StpUtil.getExtra("email"));
    }
}
