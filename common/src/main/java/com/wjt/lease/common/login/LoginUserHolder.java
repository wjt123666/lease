package com.wjt.lease.common.login;

/**
 * @ClassName LoginUserHolder
 * @Description: threadLocal 用于存储登录用户信息
 * @Author 86178
 * @Date 2025/3/8 000810:34
 * @Version 1.0
 */
public class LoginUserHolder {
    public static ThreadLocal<LoginUser> threadLocal = new ThreadLocal<>();

    public static void setLoginUser(LoginUser loginUser) {
        threadLocal.set(loginUser);
    }

    public static LoginUser getLoginUser() {
        return threadLocal.get();
    }

    public static void clear() {
        threadLocal.remove();
    }
}
