package com.junzhou.infop.service.api.dao;

import com.junzhou.infop.service.api.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountDao extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByEmail(String email);

}
