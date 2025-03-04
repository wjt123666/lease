package com.wjt.lease.web.admin.service;

import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.FacilityInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wjt.lease.model.enums.ItemType;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @author liubo
* @description 针对表【facility_info(配套信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface FacilityInfoService extends IService<FacilityInfo> {


    /**
     * [根据类型]查询配套信息列表
     * @param type 类型
     * @return 配套信息列表
     */
    Result<List<FacilityInfo>> listFacility(ItemType type);


    /**
     * 新增或修改配套信息
     * @param facilityInfo 配套信息
     * @return  Result
     */
    Result saveOrUpdateFacility(FacilityInfo facilityInfo);


    /**
     * 根据id删除配套信息
     * @param id 配套信息id
     * @return Result
     */
    Result removeFacilityById(Long id);
}
