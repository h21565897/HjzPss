package com.junzhou.infop.configs;

import com.junzhou.infop.auth.JWTHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegistrationConfig {
    @Autowired
    JWTHelper jwtHelper;

//    @Bean
//    public FilterRegistrationBean<JWTFilter> jwtFilter() {
//        FilterRegistrationBean<JWTFilter> filterRegistrationBean = new FilterRegistrationBean<JWTFilter>();
//        filterRegistrationBean.setFilter(new JWTFilter(jwtHelper));
//        filterRegistrationBean.addUrlPatterns("/api/*");
//        filterRegistrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
//        return filterRegistrationBean;
//    }
}
