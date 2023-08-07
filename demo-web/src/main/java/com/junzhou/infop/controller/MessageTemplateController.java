package com.junzhou.infop.controller;


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
    public BasicResultVo saveOrUpdate(@RequestBody MessageTemplate messageTemplate, @RequestAttribute("userObj") String userObj) {
        if (ObjectUtil.isEmpty(messageTemplate.getCreator())) {
            messageTemplate.setCreator(userObj);
        }
        messageTemplate.setUpdator(userObj);
        messageTemplateService.saveOrUpdate(messageTemplate);
        return BasicResultVo.success("operation success");
    }

    @GetMapping("query/{id}")
    public BasicResultVo queryById(@PathVariable("id") Long id) {
        MessageTemplate messageTemplate = messageTemplateService.queryById(id);
        if (Objects.isNull(messageTemplate)) {
            return BasicResultVo.fail("message template does not exist");
        }
        return BasicResultVo.success(messageTemplate);
    }

    @DeleteMapping("delete/{id}")
    public void deleteByIds(@PathVariable("id") Long id) {
        messageTemplateService.deleteById(id);
    }

    @GetMapping("/list")
    public BasicResultVo list(@RequestAttribute("userObj") String userObj) {

        return BasicResultVo.success(messageTemplateService.listAll(userObj));
    }
}
