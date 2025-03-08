package com.wjt.lease.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.SystemPost;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wjt.lease.model.enums.BaseStatus;

import java.util.List;

/**
* @author liubo
* @description 针对表【system_post(岗位信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface SystemPostService extends IService<SystemPost> {

    /**
     * 分页获取岗位信息
     * @param current 页码
     * @param size 每页条数
     * @return IPage<SystemPost>
     */
    Result<IPage<SystemPost>> pageSystem(long current, long size);

    /**
     * 保存或更新岗位信息
     * @param systemPost 岗位信息
     * @return Result
     */
    Result saveOrUpdateSystem(SystemPost systemPost);

    /**
     * 根据id删除岗位
     * @param id 岗位id
     * @return Result
     */
    Result removeSystemById(Long id);

    /**
     * 根据id获取岗位信息
     * @param id 岗位id
     * @return Result<SystemPost>
     */
    Result<SystemPost> getSystemById(Long id);

    /**
     * 获取全部岗位列表
     * @return
     */
    Result<List<SystemPost>> listSystem();

    /**
     * 根据岗位id修改状态
     * @param id 岗位id
     * @param status 状态
     * @return Result
     */
    Result updateStatusByPostId(Long id, BaseStatus status);
}
