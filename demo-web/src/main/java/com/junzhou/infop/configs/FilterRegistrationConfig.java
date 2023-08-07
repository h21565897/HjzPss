package com.junzhou.infop.configs;

import com.junzhou.infop.auth.JWTFilter;
import com.junzhou.infop.auth.JWTHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class FilterRegistrationConfig {
    @Autowired
    JWTHelper jwtHelper;

    @Bean
    public FilterRegistrationBean<JWTFilter> jwtFilter() {
        FilterRegistrationBean<JWTFilter> filterRegistrationBean = new FilterRegistrationBean<JWTFilter>();
        filterRegistrationBean.setFilter(new JWTFilter(jwtHelper));
        filterRegistrationBean.addUrlPatterns("/api/*");
        filterRegistrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
        return filterRegistrationBean;
    }
}
