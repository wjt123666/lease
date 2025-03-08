package com.wjt.lease.web.admin.service;

import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.SystemUser;
import com.wjt.lease.model.enums.BaseStatus;
import com.wjt.lease.web.admin.vo.system.user.SystemUserItemVo;
import com.wjt.lease.web.admin.vo.system.user.SystemUserQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author liubo
* @description 针对表【system_user(员工信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface SystemUserService extends IService<SystemUser> {

    /**
     * 根据条件分页查询后台用户列表
     * @param current 页码
     * @param size 每页条数
     * @param queryVo 查询条件
     * @return 分页数据
     */
    Result<IPage<SystemUserItemVo>> pageSystemUser(long current, long size, SystemUserQueryVo queryVo);

    /**
     * 根据ID查询后台用户信息
     * @param id 用户ID
     * @return 用户信息
     */
    Result<SystemUserItemVo> getSystemUserById(Long id);

    /**
     * 保存或更新后台用户信息
     * @param systemUser 用户信息
     * @return Result
     */
    Result saveOrUpdateSystemUser(SystemUser systemUser);

    /**
     * 判断后台用户名是否可用
     * @param username 用户名
     * @return Boolean 是否可用
     */
    Result<Boolean> isUsernameExists(String username);

    /**
     * 根据ID删除后台用户信息
     * @param id 用户ID
     * @return Result
     */
    Result removeSystemUserById(Long id);

    /**
     * 根据ID修改后台用户状态
     * @param id 用户ID
     * @param status 状态
     * @return Result
     */
    Result updateStatusByUserId(Long id, BaseStatus status);
}
