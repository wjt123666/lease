package com.wjt.lease.web.admin.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjt.lease.common.result.Result;
import com.wjt.lease.common.result.ResultCodeEnum;
import com.wjt.lease.model.entity.CityInfo;
import com.wjt.lease.model.entity.DistrictInfo;
import com.wjt.lease.web.admin.service.DistrictInfoService;
import com.wjt.lease.web.admin.mapper.DistrictInfoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【district_info】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class DistrictInfoServiceImpl extends ServiceImpl<DistrictInfoMapper, DistrictInfo> implements DistrictInfoService{

    /**
     * 根据城市id查询区县信息
     * @param id 城市id
     * @return 区县信息列表
     */
    @Override
    public Result<List<DistrictInfo>> listDistrictInfoByCityId(Long id) {
        if (ObjUtil.isNull(id)) return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        return Result.ok(list(new LambdaQueryWrapper<DistrictInfo>().eq(DistrictInfo::getCityId,id)));
    }
}




