package com.junzhou.infop.service.impl;

import cn.hutool.core.date.DateUtil;
import com.junzhou.infop.service.ChannelAccountService;
import com.junzhou.infop.service.api.dao.ChannelAccountDao;
import com.junzhou.infop.service.api.entity.ChannelAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ChannelAccountServiceImpl implements ChannelAccountService {
    @Autowired
    ChannelAccountDao channelAccountDao;

    @Override
    public ChannelAccount saveOrUpdate(ChannelAccount channelAccount) {
        if (Objects.isNull(channelAccount.getId())) {
            channelAccount.setCreated(Math.toIntExact(DateUtil.currentSeconds()));
        }
        channelAccount.setUpdated(Math.toIntExact(DateUtil.currentSeconds()));
        return channelAccountDao.save(channelAccount);
    }

    @Override
    public ChannelAccount queryById(Long id) {
        return null;
    }

    @Override
    public List<ChannelAccount> listAll(String creator) {
        return channelAccountDao.findAllByCreatorEquals(creator);
    }

    @Override
    public void deleteById(Long id) {

    }
}
