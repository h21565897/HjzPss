package com.junzhou.infop.handler.utils;

import com.alibaba.fastjson.JSON;
import com.junzhou.infop.service.api.dao.ChannelAccountDao;
import com.junzhou.infop.service.api.entity.ChannelAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountUtils {
    @Autowired
    ChannelAccountDao channelAccountDao;

    public <T> T getAccountById(Integer sendAccountId, Class<T> clazz) {
        Optional<ChannelAccount> optionalChannelAccount = channelAccountDao.findById(Long.valueOf(sendAccountId));
        if (optionalChannelAccount.isPresent()) {
            ChannelAccount channelAccount = optionalChannelAccount.get();
            return JSON.parseObject(channelAccount.getAccountConfig(), clazz);
        }
        return null;
    }
}
