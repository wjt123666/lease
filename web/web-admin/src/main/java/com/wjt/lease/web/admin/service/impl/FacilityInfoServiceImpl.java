package com.wjt.lease.web.admin.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjt.lease.common.result.Result;
import com.wjt.lease.common.result.ResultCodeEnum;
import com.wjt.lease.model.entity.FacilityInfo;
import com.wjt.lease.model.enums.ItemType;
import com.wjt.lease.web.admin.service.FacilityInfoService;
import com.wjt.lease.web.admin.mapper.FacilityInfoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【facility_info(配套信息表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class FacilityInfoServiceImpl extends ServiceImpl<FacilityInfoMapper, FacilityInfo> implements FacilityInfoService{

    /**
     * [根据类型]查询配套信息列表
     * @param type 类型
     * @return 配套信息列表
     */
    @Override
    public Result<List<FacilityInfo>> listFacility(ItemType type) {
        return Result.ok(list(
                new LambdaQueryWrapper<FacilityInfo>()
                        .eq(ObjUtil.isNotNull(type),FacilityInfo::getType,type)));
    }

    /**
     * 新增或修改配套信息
     * @param facilityInfo 配套信息
     * @return  Result
     */
    @Override
    public Result saveOrUpdateFacility(FacilityInfo facilityInfo) {
        if (ObjUtil.isNull(facilityInfo)) return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        return saveOrUpdate(facilityInfo) ? Result.ok() : Result.fail();
    }

    /**
     * 根据id删除配套信息
     * @param id 配套信息id
     * @return Result
     */
    @Override
    public Result removeFacilityById(Long id) {
        if (ObjUtil.isNull(id)) return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        return removeById(id) ? Result.ok() : Result.fail();
    }
}




