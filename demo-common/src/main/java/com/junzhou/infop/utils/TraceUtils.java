package com.junzhou.infop.utils;


import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TraceUtils {
    public static String generateBusinessId(Long templateId, String userObj) {
        return userObj + DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN) + String.valueOf(templateId);
    }

    public static String generateRedisReceiverId(String receiver, String userObj) {
        return userObj + DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN) + receiver;
    }

    public static String generateRedisKeyWithUserId(String userObj) {
        return userObj + StrUtil.DASHED + DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN);
    }
}
