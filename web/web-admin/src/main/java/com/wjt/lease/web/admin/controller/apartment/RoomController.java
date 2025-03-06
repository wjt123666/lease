package com.wjt.lease.web.admin.controller.apartment;


import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.RoomInfo;
import com.wjt.lease.model.enums.ReleaseStatus;
import com.wjt.lease.web.admin.service.RoomInfoService;
import com.wjt.lease.web.admin.vo.room.RoomDetailVo;
import com.wjt.lease.web.admin.vo.room.RoomItemVo;
import com.wjt.lease.web.admin.vo.room.RoomQueryVo;
import com.wjt.lease.web.admin.vo.room.RoomSubmitVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "房间信息管理")
@RestController
@RequestMapping("/admin/room")
public class RoomController {

    @Autowired
    private RoomInfoService roomInfoService;

    @Operation(summary = "保存或更新房间信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody RoomSubmitVo roomSubmitVo) {
        return roomInfoService.saveOrUpdateRoom(roomSubmitVo);
    }

    @Operation(summary = "根据条件分页查询房间列表")
    @GetMapping("pageItem")
    public Result<IPage<RoomItemVo>> pageItem(@RequestParam long current, @RequestParam long size, RoomQueryVo queryVo) {
        return roomInfoService.pageItem(current, size, queryVo);
    }

    @Operation(summary = "根据id获取房间详细信息")
    @GetMapping("getDetailById")
    public Result<RoomDetailVo> getDetailById(@RequestParam Long id) {
        return roomInfoService.getDetailById(id);
    }

    @Operation(summary = "根据id删除房间信息")
    @DeleteMapping("removeById")
    public Result removeById(@RequestParam Long id) {
        return roomInfoService.removeRoomById(id);
    }

    @Operation(summary = "根据id修改房间发布状态")
    @PostMapping("updateReleaseStatusById")
    public Result updateReleaseStatusById(Long id, ReleaseStatus status) {
        return roomInfoService.updateReleaseStatusById(id, status);
    }

    @GetMapping("listBasicByApartmentId")
    @Operation(summary = "根据公寓id查询房间列表")
    public Result<List<RoomInfo>> listBasicByApartmentId(Long id) {
        return roomInfoService.listBasicByApartmentId(id);
    }

}


















