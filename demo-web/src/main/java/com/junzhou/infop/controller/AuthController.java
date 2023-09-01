package com.junzhou.infop.controller;

import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.junzhou.infop.auth.JWTHelper;
import com.junzhou.infop.service.api.dao.UserAccountDao;
import com.junzhou.infop.service.api.entity.UserAccount;
import com.junzhou.infop.vo.BasicResultVo;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        String userEmail = loginData.get("email");
        String password = loginData.get("password");
        Optional<UserAccount> userAccountOpt = userAccountDao.findByEmail(userEmail);
        if (userAccountOpt.isEmpty()) {
            return BasicResultVo.fail("username does not exist, please create one");
        }
        UserAccount userAccount = userAccountOpt.get();
        if (!userAccount.getPassword().equals(password)) {
            return BasicResultVo.fail("password incorrect");
        }
        StpUtil.login(userEmail, SaLoginConfig.setExtra("email", userEmail));

        Map<String, String> loginResponse = new HashMap<>();
        loginResponse.put("access_token", StpUtil.getTokenValue());
        return BasicResultVo.success(loginResponse);
    }

    @PostMapping("/register")
    private BasicResultVo register(@RequestBody Map<String, String> registerData) {
        String email = registerData.get("email");
        String password = registerData.get("password");
        if (ObjectUtil.isEmpty(email) || ObjectUtil.isEmpty(password)) {
            return BasicResultVo.fail("bad request parameter");
        }
        Optional<UserAccount> userAccountOptional = userAccountDao.findByEmail(email);
        if (userAccountOptional.isPresent()) {
            return BasicResultVo.fail("user already Exists");
        }
        userAccountDao.save(UserAccount.builder().email(email).password(password).build());
        return BasicResultVo.success();
    }

    @GetMapping("/github")
    public void handleGithubAuth(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect("https://github.com/login/oauth/authorize?client_id=5d221ce4f919e581b514&scope=user:email");
    }

    @GetMapping("/github/callback")
    public void handleGithubAuth(@RequestParam(defaultValue = "") String code, HttpServletResponse httpServletResponse) throws IOException {
        if (ObjectUtil.isEmpty(code)) {
            httpServletResponse.sendRedirect("http://localhost:3000/oauth2/github?error=true");
        }
        OkHttpClient client = new OkHttpClient();
        okhttp3.RequestBody formBody = new FormBody.Builder().add("client_id", "5d221ce4f919e581b514").add("client_secret", "48c517ea05c165e3b724f60967e047f324f53367").add("code", code).build();
        okhttp3.Request request = new okhttp3.Request.Builder().url("https://github.com/login/oauth/access_token").post(formBody).header("Accept", "application/json").build();
        try {
            Response execute = client.newCall(request).execute();
            if (!execute.isSuccessful()) {
                throw new Exception(execute.body().string());
            }
            JSONObject response = JSON.parseObject(execute.body().string());
            String access_token = response.getString("access_token");
            okhttp3.Request getEmailRequest = new okhttp3.Request.Builder().url("https://api.github.com/user/emails").get().header("Authorization", "Bearer " + access_token).build();
            Response emailExecute = client.newCall(getEmailRequest).execute();
            if (!emailExecute.isSuccessful()) {
                throw new Exception(emailExecute.body().string());
            }
            String emailResponseData = emailExecute.body().string();
            JSONArray emailArray = JSON.parseArray(emailResponseData);
            JSONObject parsedEmailObject = (JSONObject) emailArray.get(0);
            String userEmail = parsedEmailObject.getString("email");
            StpUtil.login(userEmail, SaLoginConfig.setExtra("email", userEmail));
            httpServletResponse.sendRedirect("http://localhost:3000/oauth2/github?token=" + StpUtil.getTokenValue());
        } catch (Exception e) {
            httpServletResponse.sendRedirect("http://localhost:3000/oauth2/github?error=true");
        }
    }
}
