package com.junzhou.infop.controller;


import com.junzhou.infop.service.api.domain.SendRequest;
import com.junzhou.infop.service.api.service.SendService;
import com.junzhou.infop.vo.BasicResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SendController {
    @Autowired
    SendService sendService;

    @PostMapping("/send")
    public BasicResultVo send(@RequestBody SendRequest sendRequest, @RequestAttribute("userObj") String userObj) {
        sendRequest.setUserObj(userObj);
        return sendService.send(sendRequest);
    }
}
