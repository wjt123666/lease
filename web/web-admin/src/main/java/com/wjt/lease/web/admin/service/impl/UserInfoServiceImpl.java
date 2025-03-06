package com.wjt.lease.web.admin.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.UserInfo;
import com.wjt.lease.model.enums.BaseStatus;
import com.wjt.lease.web.admin.service.UserInfoService;
import com.wjt.lease.web.admin.mapper.UserInfoMapper;
import com.wjt.lease.web.admin.vo.user.UserInfoQueryVo;
import org.springframework.stereotype.Service;

/**
* @author liubo
* @description 针对表【user_info(用户信息表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService{

    /**
     * 分页查询用户信息
     * @param current 页码
     * @param size 页大小
     * @param queryVo 查询条件
     * @return 分页数据
     */
    @Override
    public Result<IPage<UserInfo>> pageUserInfo(long current, long size, UserInfoQueryVo queryVo) {
        String phone = queryVo.getPhone();
        BaseStatus status = queryVo.getStatus();
        // 构建分页条件
        IPage<UserInfo> page = new Page<>(current, size);
        // 构建查询条件
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<UserInfo>()
                .eq(ObjUtil.isNotNull(phone), UserInfo::getPhone, phone)
                .eq(ObjUtil.isNotNull(status), UserInfo::getStatus, status);
        page = super.page(page, queryWrapper);
        // 返回分页数据
        return Result.ok(page);
    }

    /**
     * 根据用户id更新账号状态
     * @param id 用户id
     * @param status 账号状态
     * @return 更新结果
     */
    @Override
    public Result updateStatusById(Long id, BaseStatus status) {
        boolean b = super.update(new LambdaUpdateWrapper<UserInfo>()
                .eq(UserInfo::getId, id)
                .set(UserInfo::getStatus, status));
        return b ? Result.ok() : Result.fail(500,"更新账号状态失败");
    }
}




