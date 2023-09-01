package com.junzhou.infop.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.junzhou.infop.service.api.domain.SendRequest;
import com.junzhou.infop.service.api.service.SendService;
import com.junzhou.infop.vo.BasicResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SendController {
    @Autowired
    SendService sendService;

    @PostMapping("/send")
    public BasicResultVo send(@RequestBody SendRequest sendRequest) {
        String userEmail = (String) StpUtil.getExtra("email");
        sendRequest.setUserObj(userEmail);
        return sendService.send(sendRequest);
    }
}
