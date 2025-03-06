package com.wjt.lease.web.admin.service;

import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.ViewAppointment;
import com.wjt.lease.model.enums.AppointmentStatus;
import com.wjt.lease.web.admin.vo.appointment.AppointmentQueryVo;
import com.wjt.lease.web.admin.vo.appointment.AppointmentVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author liubo
* @description 针对表【view_appointment(预约看房信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface ViewAppointmentService extends IService<ViewAppointment> {

    /**
     * 根据id更新预约状态
     * @param id 预约id
     * @param status 预约状态
     * @return 更新结果
     */
    Result updateStatusById(Long id, AppointmentStatus status);

    /**
     * 分页查询预约信息
     * @param current 页码
     * @param size 页大小
     * @param queryVo 查询条件
     * @return 分页查询结果
     */
    Result<IPage<AppointmentVo>> pageAppointmentByQuery(long current, long size, AppointmentQueryVo queryVo);
}
