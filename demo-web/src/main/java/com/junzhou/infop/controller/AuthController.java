package com.junzhou.infop.controller;

import com.junzhou.infop.auth.JWTHelper;
import com.junzhou.infop.service.api.dao.UserAccountDao;
import com.junzhou.infop.service.api.entity.UserAccount;
import com.junzhou.infop.vo.BasicResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserAccountDao userAccountDao;

    @Autowired
    JWTHelper jwtHelper;

    @PostMapping("/login")
    private BasicResultVo login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");
        Optional<UserAccount> userAccountOpt = userAccountDao.findByUsername(username);
        if (userAccountOpt.isEmpty()) {
            return BasicResultVo.fail("username does not exist, please create one");
        }
        UserAccount userAccount = userAccountOpt.get();
        if (!userAccount.getPassword().equals(password)) {
            return BasicResultVo.fail("password incorrect");
        }
        Map<String, String> loginResponse = new HashMap<>();
        loginResponse.put("access_token", jwtHelper.generateToken(userAccount.getUsername(), new Date(new Date().getTime() + 7 * 24 * 60 * 60 * 1000)));
        return BasicResultVo.success(loginResponse);
    }
}
