package com.wjt.lease.web.admin.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.SystemPost;
import com.wjt.lease.model.enums.BaseStatus;
import com.wjt.lease.web.admin.service.SystemPostService;
import com.wjt.lease.web.admin.mapper.SystemPostMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【system_post(岗位信息表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class SystemPostServiceImpl extends ServiceImpl<SystemPostMapper, SystemPost> implements SystemPostService{

    /**
     * 分页获取岗位信息
     * @param current 页码
     * @param size 每页条数
     * @return IPage<SystemPost>
     */
    @Override
    public Result<IPage<SystemPost>> pageSystem(long current, long size) {
        IPage<SystemPost> page = new Page<>(current, size);
        return Result.ok(super.page(page));
    }

    /**
     * 保存或更新岗位信息
     * @param systemPost 岗位信息
     * @return Result
     */
    @Override
    public Result saveOrUpdateSystem(SystemPost systemPost) {
        return super.saveOrUpdate(systemPost) ? Result.ok() : Result.fail(500, "保存或更新岗位信息失败");
    }

    /**
     * 根据id删除岗位
     * @param id 岗位id
     * @return Result
     */
    @Override
    public Result removeSystemById(Long id) {
        return super.removeById(id) ? Result.ok() : Result.fail(500, "删除岗位信息失败");
    }

    /**
     * 根据id获取岗位信息
     * @param id 岗位id
     * @return Result<SystemPost>
     */
    @Override
    public Result<SystemPost> getSystemById(Long id) {
        if (ObjUtil.isNull(id)) return Result.fail(500, "岗位id不能为空");
        return Result.ok(super.getById(id));
    }

    /**
     * 获取全部岗位列表
     * @return
     */
    @Override
    public Result<List<SystemPost>> listSystem() {
        return Result.ok(super.list());
    }

    /**
     * 根据岗位id修改状态
     * @param id 岗位id
     * @param status 状态
     * @return Result
     */
    @Override
    public Result updateStatusByPostId(Long id, BaseStatus status) {
        boolean b = super.update(new LambdaUpdateWrapper<SystemPost>()
                .eq(SystemPost::getId, id)
                .set(SystemPost::getStatus, status));
        return b ? Result.ok() : Result.fail(500, "修改岗位状态失败");
    }
}




