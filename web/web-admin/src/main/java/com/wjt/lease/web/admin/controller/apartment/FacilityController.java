package com.wjt.lease.web.admin.controller.apartment;


import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.FacilityInfo;
import com.wjt.lease.model.enums.ItemType;
import com.wjt.lease.web.admin.service.FacilityInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "配套管理")
@RestController
@RequestMapping("/admin/facility")
public class FacilityController {

    @Autowired
    private FacilityInfoService facilityInfoService;

    @Operation(summary = "[根据类型]查询配套信息列表")
    @GetMapping("list")
    public Result<List<FacilityInfo>> listFacility(@RequestParam(required = false) ItemType type) {
        return facilityInfoService.listFacility(type);
    }

    @Operation(summary = "新增或修改配套信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody FacilityInfo facilityInfo) {
        return facilityInfoService.saveOrUpdateFacility(facilityInfo);
    }

    @Operation(summary = "根据id删除配套信息")
    @DeleteMapping("deleteById")
    public Result removeFacilityById(@RequestParam Long id) {
        return facilityInfoService.removeFacilityById(id);
    }

}
