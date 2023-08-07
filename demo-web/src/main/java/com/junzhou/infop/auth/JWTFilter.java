package com.junzhou.infop.auth;

import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class JWTFilter implements Filter {
    private final static String AUTHORIZATION_HEADER = "Authorization";
    private JWTHelper jwtHelper;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getMethod().equals("OPTIONS")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String header = request.getHeader(AUTHORIZATION_HEADER);
        String userObj = resolveToken(header);

        if (ObjectUtil.isEmpty(userObj)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        request.setAttribute("userObj", userObj);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String resolveToken(String bearerToken) {
        if (!ObjectUtil.isEmpty(bearerToken) && bearerToken.startsWith("Bearer ")) {
            String jwt = bearerToken.substring(7);
            return jwtHelper.validateToken(jwt);
        }
        return null;
    }

}
