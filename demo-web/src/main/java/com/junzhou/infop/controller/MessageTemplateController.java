package com.junzhou.infop.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.junzhou.infop.service.MessageTemplateService;
import com.junzhou.infop.service.api.entity.MessageTemplate;
import com.junzhou.infop.vo.BasicResultVo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/messageTemplate")
@AllArgsConstructor
public class MessageTemplateController {


    private MessageTemplateService messageTemplateService;

    @PostMapping("/save")
    public BasicResultVo saveOrUpdate(@RequestBody MessageTemplate messageTemplate) {
        
        String userEmail = (String) StpUtil.getExtra("email");
        if (ObjectUtil.isEmpty(messageTemplate.getCreator())) {
            messageTemplate.setCreator(userEmail);
        }
        messageTemplate.setUpdator(userEmail);
        messageTemplateService.saveOrUpdate(messageTemplate);
        return BasicResultVo.success("operation success");
    }

    @GetMapping("/{id}")
    public BasicResultVo queryById(@PathVariable("id") Long id) {
        MessageTemplate messageTemplate = messageTemplateService.queryById(id);
        if (Objects.isNull(messageTemplate)) {
            return BasicResultVo.fail("message template does not exist");
        }
        return BasicResultVo.success(messageTemplate);
    }

    @DeleteMapping("/{id}")
    public BasicResultVo deleteById(@PathVariable("id") Long id) {
        messageTemplateService.deleteById(id);
        return BasicResultVo.success();
    }

    @GetMapping("/list")
    public BasicResultVo list() {
        String userEmail = (String) StpUtil.getExtra("email");
        return BasicResultVo.success(messageTemplateService.listAll(userEmail));
    }
}
