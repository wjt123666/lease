package com.wjt.lease.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wjt.lease.model.enums.BaseStatus;
import com.wjt.lease.web.admin.vo.user.UserInfoQueryVo;

/**
* @author liubo
* @description 针对表【user_info(用户信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface UserInfoService extends IService<UserInfo> {

    /**
     * 分页查询用户信息
     * @param current 页码
     * @param size 页大小
     * @param queryVo 查询条件
     * @return 分页数据
     */
    Result<IPage<UserInfo>> pageUserInfo(long current, long size, UserInfoQueryVo queryVo);

    /**
     * 根据用户id更新账号状态
     * @param id 用户id
     * @param status 账号状态
     * @return 更新结果
     */
    Result updateStatusById(Long id, BaseStatus status);
}
