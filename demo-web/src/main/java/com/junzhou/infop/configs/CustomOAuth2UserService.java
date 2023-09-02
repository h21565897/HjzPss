package com.junzhou.infop.configs;

import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);
        String accessToken = userRequest.getAccessToken().getTokenValue();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        HttpEntity entity = new HttpEntity("", headers);
        ResponseEntity<String> response = restTemplate.exchange("https://api.github.com/user/emails", HttpMethod.GET, entity, String.class);

        // Get the email address
        String responseJsonString = response.getBody().toString();
        JSONArray parsedJsonArray = JSON.parseArray(responseJsonString);
        JSONObject emailJsonObject = (JSONObject) parsedJsonArray.get(0);
        String email = emailJsonObject.getString("email");
        // Perform login and get the token
        StpUtil.login(email, SaLoginConfig.setExtra("email", email));
        String token = StpUtil.getTokenValue();

        // Add the token to the user attributes
        Map<String, Object> attributes = new HashMap<>(user.getAttributes());
        attributes.put("token", token);
        attributes.put("email", email);
        return new DefaultOAuth2User(user.getAuthorities(), attributes, "email");
    }
}