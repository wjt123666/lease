package com.wjt.lease.web.admin.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.SystemPost;
import com.wjt.lease.model.entity.SystemUser;
import com.wjt.lease.model.enums.BaseStatus;
import com.wjt.lease.web.admin.mapper.SystemPostMapper;
import com.wjt.lease.web.admin.mapper.SystemUserMapper;
import com.wjt.lease.web.admin.service.SystemUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjt.lease.web.admin.vo.system.user.SystemUserItemVo;
import com.wjt.lease.web.admin.vo.system.user.SystemUserQueryVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 * @description 针对表【system_user(员工信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserService {

    @Autowired
    private SystemUserMapper systemUserMapper;
    @Autowired
    private SystemPostMapper systemPostMapper;

    /**
     * 根据条件分页查询后台用户列表
     * @param current 页码
     * @param size 每页条数
     * @param queryVo 查询条件
     * @return 分页数据
     */
    @Override
    public Result<IPage<SystemUserItemVo>> pageSystemUser(long current, long size, SystemUserQueryVo queryVo) {
        IPage<SystemUserItemVo> page = new Page<>(current, size);

        return Result.ok(systemUserMapper.pageSystemUser(page, queryVo));
    }

    /**
     * 根据ID查询后台用户信息
     * @param id 用户ID
     * @return 用户信息
     */
    @Override
    public Result<SystemUserItemVo> getSystemUserById(Long id) {
        if (ObjUtil.isNull(id)) return Result.fail(500,"ID不能为空");
        // 根据用户id查询用户信息
        SystemUser systemUser = systemUserMapper.selectById(id);
        if (ObjUtil.isNull(systemUser)) return Result.fail(500,"用户不存在");

        // 根据用户id查询用户岗位信息
        SystemPost systemPost = systemPostMapper.selectById(systemUser.getPostId());

        // 封装用户信息
        SystemUserItemVo systemUserItemVo = new SystemUserItemVo();
        BeanUtils.copyProperties(systemUser, systemUserItemVo);
        systemUserItemVo.setPostName(systemPost.getName());
        return Result.ok(systemUserItemVo);
    }


    /**
     * 保存或更新后台用户信息
     * @param systemUser 用户信息
     * @return Result
     */
    @Override
    public Result saveOrUpdateSystemUser(SystemUser systemUser) {
        if(ObjUtil.isNotNull(systemUser.getPassword())) {
            String newPwd = DigestUtils.md5Hex(systemUser.getPassword());
            systemUser.setPassword(newPwd);
        }
        return super.saveOrUpdate(systemUser) ? Result.ok() : Result.fail(500,"保存或更新用户信息失败");
    }

    /**
     * 判断后台用户名是否可用
     * @param username 用户名
     * @return Boolean 是否可用
     */
    @Override
    public Result<Boolean> isUsernameExists(String username) {
        long count = super.count(new LambdaQueryWrapper<SystemUser>()
                .eq(SystemUser::getUsername, username));
        return Result.ok(count == 0);
    }

    /**
     * 根据ID删除后台用户信息
     * @param id 用户ID
     * @return Result
     */
    @Override
    public Result removeSystemUserById(Long id) {
        return super.removeById(id) ? Result.ok() : Result.fail(500,"删除用户信息失败");
    }

    /**
     * 根据ID修改后台用户状态
     * @param id 用户ID
     * @param status 状态
     * @return Result
     */
    @Override
    public Result updateStatusByUserId(Long id, BaseStatus status) {
        boolean b = super.update(new LambdaUpdateWrapper<SystemUser>()
                .eq(SystemUser::getId, id)
                .set(SystemUser::getStatus, status));
        return b ? Result.ok() : Result.fail(500,"修改用户状态失败");
    }
}




