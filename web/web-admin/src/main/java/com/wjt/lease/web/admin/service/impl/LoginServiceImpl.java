package com.wjt.lease.web.admin.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wf.captcha.SpecCaptcha;
import com.wjt.lease.common.constant.RedisConstant;
import com.wjt.lease.common.exception.LeaseException;
import com.wjt.lease.common.login.LoginUser;
import com.wjt.lease.common.login.LoginUserHolder;
import com.wjt.lease.common.result.Result;
import com.wjt.lease.common.result.ResultCodeEnum;
import com.wjt.lease.common.utils.JwtUtil;
import com.wjt.lease.model.entity.SystemUser;
import com.wjt.lease.model.enums.BaseStatus;
import com.wjt.lease.web.admin.mapper.SystemUserMapper;
import com.wjt.lease.web.admin.service.LoginService;
import com.wjt.lease.web.admin.vo.login.CaptchaVo;
import com.wjt.lease.web.admin.vo.login.LoginVo;
import com.wjt.lease.web.admin.vo.system.user.SystemUserInfoVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private SystemUserMapper systemUserMapper;

    /**
     * 获取图形验证码
     * @return 图形验证码
     */
    @Override
    public Result<CaptchaVo> getCaptcha() {
        // 获取验证码
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        specCaptcha.setCharType(SpecCaptcha.TYPE_DEFAULT);

        String code = specCaptcha.text().toLowerCase();

        // 拼接redis key
        String key = RedisConstant.ADMIN_LOGIN_PREFIX + IdUtil.fastSimpleUUID();

        // 存入redis
        stringRedisTemplate.opsForValue().set(key, // key
                code, // value
                RedisConstant.ADMIN_LOGIN_CAPTCHA_TTL_SEC, // 过期时间
                TimeUnit.SECONDS); // 过期时间单位

        // 返回前端图片验证码和key
        return Result.ok(new CaptchaVo(specCaptcha.toBase64(),key));
    }

    /**
     * 登录
     * @param loginVo 登录信息
     * @return 登录token
     */
    @Override
    public Result<String> login(LoginVo loginVo) {
        String captchaCode = loginVo.getCaptchaCode();
        String captchaKey = loginVo.getCaptchaKey();
        String username = loginVo.getUsername();
        String password = loginVo.getPassword();

        // 校验验证码是否为空
        if (ObjUtil.isNull(captchaCode)) throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_NOT_FOUND);

        // 根据captchaKey从Redis中获取验证码 判断验证码是否为空
        String code = stringRedisTemplate.opsForValue().get(captchaKey);
        if (ObjUtil.isNull(code)) throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_EXPIRED);

        // 判断验证码是否正确
        if (!captchaCode.equals(code)) throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_ERROR);

        // 根据用户名查询用户信息，判断用户是否存在
        SystemUser systemUser = systemUserMapper.selectOne(new LambdaQueryWrapper<SystemUser>()
                .eq(SystemUser::getUsername, username));
        if (ObjUtil.isNull(systemUser)) throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_NOT_EXIST_ERROR);

        // 判断用户是否被禁用
        if (systemUser.getStatus() == BaseStatus.DISABLE) throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_DISABLED_ERROR);

        // 判断密码是否正确
        if (!systemUser.getPassword().equals(DigestUtils.md5Hex(password))) throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_ERROR);

        // 生成token返回
        return Result.ok(JwtUtil.createToken(systemUser.getId(), systemUser.getUsername()));
    }

    /**
     * 获取登陆用户个人信息
     * @return 用户信息
     */
    @Override
    public Result<SystemUserInfoVo> info() {
        // 从threadlocal中获取用户信息
        LoginUser loginUser = LoginUserHolder.getLoginUser();
        SystemUser systemUser = systemUserMapper.selectById(loginUser.getUserId());
        return Result.ok(new SystemUserInfoVo(systemUser.getName(),systemUser.getAvatarUrl()));
    }
}
