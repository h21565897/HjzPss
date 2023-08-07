package com.junzhou.infop.controller;

import cn.hutool.core.util.ObjectUtil;
import com.junzhou.infop.service.ChannelAccountService;
import com.junzhou.infop.service.api.entity.ChannelAccount;
import com.junzhou.infop.vo.BasicResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/channelAccount")
public class ChannelAccountController {

    @Autowired
    ChannelAccountService channelAccountService;

    @PostMapping("/save")
    private BasicResultVo saveOrUpdate(@RequestBody ChannelAccount channelAccount, @RequestAttribute("userObj") String userObj) {
        if (ObjectUtil.isEmpty(channelAccount.getCreator())) {
            channelAccount.setCreator(userObj);
        }
        channelAccountService.saveOrUpdate(channelAccount);
        return BasicResultVo.success("operation success");
    }

    @GetMapping("/query/{id}")
    private BasicResultVo queryById(@PathVariable("id") Long id) {
        ChannelAccount channelAccount = channelAccountService.queryById(id);
        if (ObjectUtils.isEmpty(channelAccount)) {
            return BasicResultVo.fail("account does not exist");
        }
        return BasicResultVo.success(channelAccount);
    }

    @GetMapping("/list")
    private BasicResultVo list(@RequestAttribute("userObj") String userObj) {
        List<ChannelAccount> channelAccounts = channelAccountService.listAll(userObj);
        return BasicResultVo.success(channelAccounts);
    }

}
