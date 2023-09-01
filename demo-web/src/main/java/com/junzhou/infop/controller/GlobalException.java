package com.junzhou.infop.controller;


import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.junzhou.infop.vo.BasicResultVo;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalException {


    public GlobalException() {
        System.out.println("fewlfjljwefjjwjfwfwlwjffwcccddwed");
    }

    @ExceptionHandler
    public BasicResultVo handlerException(Exception e, HttpServletRequest request, HttpServletResponse response) throws Exception {
        e.printStackTrace();
        String message;
        if (e instanceof NotLoginException) {
            NotLoginException ee = (NotLoginException) e;
            message = ee.getMessage();
        } else if (e instanceof NotRoleException) {
            NotRoleException ee = (NotRoleException) e;
            message = ee.getMessage();
        } else if (e instanceof NotPermissionException) {
            NotPermissionException ee = (NotPermissionException) e;
            message = ee.getMessage();
        } else {
            message = e.getMessage();
        }
        return BasicResultVo.fail(message);
    }
}
