package com.wjt.lease.web.admin.custom.interceptor;

import com.wjt.lease.common.login.LoginUser;
import com.wjt.lease.common.login.LoginUserHolder;
import com.wjt.lease.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @ClassName AuthenticationInterceptor
 * @Description: 自定义校验登录拦截器
 * @Author 86178
 * @Date 2025/3/8 00089:13
 * @Version 1.0
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头中获取token
        String token = request.getHeader("access-token");
        // 校验token是否有效
        Claims claims = JwtUtil.parseToken(token);
        Long userId = claims.get("userId", Long.class);
        String userName = claims.get("userName", String.class);
        // 存储到ThreadLocal中
        LoginUserHolder.setLoginUser(new LoginUser(userId, userName));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清除threadLocal中的登录用户信息
        LoginUserHolder.clear();
    }
}
