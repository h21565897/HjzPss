package com.junzhou.infop.configs;

import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.jwt.StpLogicJwtForStateless;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.junzhou.infop.vo.BasicResultVo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaTokenRegistrationConfig {
    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter().addInclude("/api/**").setAuth(obj -> {
            SaRouter.match("/api/**", () -> StpUtil.checkLogin());
        }).setError(e -> JSON.toJSON(BasicResultVo.fail(e.getMessage())).toString()).setBeforeAuth(obj -> {
            SaRouter.match(SaHttpMethod.OPTIONS).stop();
        });
    }

    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForStateless();
    }
}
