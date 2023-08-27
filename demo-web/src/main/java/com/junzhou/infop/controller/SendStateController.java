package com.junzhou.infop.controller;


import com.junzhou.infop.utils.TraceUtils;
import com.junzhou.infop.vo.BasicResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SendStateController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/trace")
    public BasicResultVo trace(@RequestAttribute("userObj") String userObj) {
        try {
            String redisKey = TraceUtils.generateRedisKeyWithUserId(userObj);
            List<String> range = stringRedisTemplate.opsForList().range(redisKey, 0, -1);
            return BasicResultVo.success(range);
        } catch (Exception e) {
            return BasicResultVo.fail("internal error");
        }
    }
}
