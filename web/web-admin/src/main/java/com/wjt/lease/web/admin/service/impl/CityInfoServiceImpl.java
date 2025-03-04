package com.wjt.lease.web.admin.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjt.lease.common.result.Result;
import com.wjt.lease.common.result.ResultCodeEnum;
import com.wjt.lease.model.entity.CityInfo;
import com.wjt.lease.web.admin.service.CityInfoService;
import com.wjt.lease.web.admin.mapper.CityInfoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【city_info】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class CityInfoServiceImpl extends ServiceImpl<CityInfoMapper, CityInfo> implements CityInfoService{

    /**
     * 根据省份id查询城市信息列表
     * @param id 省份id
     * @return Result<List<CityInfo>>
     */
    @Override
    public Result<List<CityInfo>> listCityInfoByProvinceId(Long id) {
        if (ObjUtil.isNull(id)) return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        return Result.ok(list(new LambdaQueryWrapper<CityInfo>().eq(CityInfo::getProvinceId,id)));
    }
}




