package com.wjt.lease.web.admin.service;

import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.RoomInfo;
import com.wjt.lease.model.enums.ReleaseStatus;
import com.wjt.lease.web.admin.vo.room.RoomDetailVo;
import com.wjt.lease.web.admin.vo.room.RoomItemVo;
import com.wjt.lease.web.admin.vo.room.RoomQueryVo;
import com.wjt.lease.web.admin.vo.room.RoomSubmitVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author liubo
* @description 针对表【room_info(房间信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface RoomInfoService extends IService<RoomInfo> {

    /**
     * 保存或更新房间信息
     * @param roomSubmitVo 房间信息
     * @return Result
     */
    Result saveOrUpdateRoom(RoomSubmitVo roomSubmitVo);

    /**
     * 根据条件分页查询房间列表
     * @param current 页码
     * @param size 页大小
     * @param queryVo 查询条件
     * @return IPage<RoomItemVo>
     */
    Result<IPage<RoomItemVo>> pageItem(long current, long size, RoomQueryVo queryVo);

    /**
     * 根据id获取房间详细信息
     * @param id 房间id
     * @return Result<RoomDetailVo>
     */
    Result<RoomDetailVo> getDetailById(Long id);

    /**
     * 根据id删除房间信息
     * @param id 房间id
     * @return Result
     */
    Result removeRoomById(Long id);

    /**
     * 根据id修改房间发布状态
     * @param id 房间id
     * @param status 发布状态
     * @return Result
     */
    Result updateReleaseStatusById(Long id, ReleaseStatus status);

    /**
     * 根据公寓id查询房间列表
     * @param id 公寓id
     * @return Result<List<RoomInfo>>
     */
    Result<List<RoomInfo>> listBasicByApartmentId(Long id);
}
