package com.wjt.lease.web.admin.service;

import com.wjt.lease.common.result.Result;
import com.wjt.lease.web.admin.vo.login.CaptchaVo;
import com.wjt.lease.web.admin.vo.login.LoginVo;
import com.wjt.lease.web.admin.vo.system.user.SystemUserInfoVo;

public interface LoginService {

    /**
     * 获取图形验证码
     * @return 图形验证码
     */
    Result<CaptchaVo> getCaptcha();

    /**
     * 登录
     * @param loginVo 登录信息
     * @return 登录token
     */
    Result<String> login(LoginVo loginVo);

    /**
     * 获取登陆用户个人信息
     * @return 用户信息
     */
    Result<SystemUserInfoVo> info();
}
