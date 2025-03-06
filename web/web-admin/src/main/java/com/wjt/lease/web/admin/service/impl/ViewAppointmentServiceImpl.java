package com.wjt.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.ViewAppointment;
import com.wjt.lease.model.enums.AppointmentStatus;
import com.wjt.lease.web.admin.mapper.ViewAppointmentMapper;
import com.wjt.lease.web.admin.service.ViewAppointmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjt.lease.web.admin.vo.appointment.AppointmentQueryVo;
import com.wjt.lease.web.admin.vo.appointment.AppointmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 * @description 针对表【view_appointment(预约看房信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class ViewAppointmentServiceImpl extends ServiceImpl<ViewAppointmentMapper, ViewAppointment> implements ViewAppointmentService {

    @Autowired
    private ViewAppointmentMapper viewAppointmentMapper;

    /**
     * 根据id更新预约状态
     * @param id 预约id
     * @param status 预约状态
     * @return 更新结果
     */
    @Override
    public Result updateStatusById(Long id, AppointmentStatus status) {
        boolean b = super.update(new LambdaUpdateWrapper<ViewAppointment>()
                .eq(ViewAppointment::getId, id)
                .set(ViewAppointment::getAppointmentStatus, status));
        return b ? Result.ok() : Result.fail();
    }

    /**
     * 分页查询预约信息
     * @param current 页码
     * @param size 页大小
     * @param queryVo 查询条件
     * @return 分页查询结果
     */
    @Override
    public Result<IPage<AppointmentVo>> pageAppointmentByQuery(long current, long size, AppointmentQueryVo queryVo) {
        IPage<ViewAppointment> page = new Page<>(current,size);
        IPage<AppointmentVo> res = viewAppointmentMapper.pageAppointmentByQuery(page, queryVo);
        return Result.ok(res);
    }
}




