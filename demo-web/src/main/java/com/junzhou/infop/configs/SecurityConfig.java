package com.junzhou.infop.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        // Here you can specify the paths that you want Spring Security to ignore.
//        // Replace "/api/**" with the actual paths that you want to ignore.
//        web.ignoring().antMatchers("/api/**");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().anyRequest().permitAll().and().oauth2Login().userInfoEndpoint().userService(customOAuth2UserService).and().successHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                OAuth2User user = (OAuth2User) authentication.getPrincipal();
                String token = (String) user.getAttributes().get("token");

                response.sendRedirect("http://localhost:3000/oauth2?token=" + token);
            }
        });
    }
}