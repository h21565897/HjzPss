package com.junzhou.infop.service;

import com.junzhou.infop.service.api.entity.ChannelAccount;

import java.util.List;

public interface ChannelAccountService {
    ChannelAccount saveOrUpdate(ChannelAccount channelAccount);

    ChannelAccount queryById(Long id);

    List<ChannelAccount> listAll(String creator);

    void deleteById(Long id);
}
