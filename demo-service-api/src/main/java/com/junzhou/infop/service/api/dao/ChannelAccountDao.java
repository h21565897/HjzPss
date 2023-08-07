package com.junzhou.infop.service.api.dao;

import com.junzhou.infop.service.api.entity.ChannelAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChannelAccountDao extends JpaRepository<ChannelAccount, Long> {

    List<ChannelAccount> findAllByCreatorEquals(String creator);
}
