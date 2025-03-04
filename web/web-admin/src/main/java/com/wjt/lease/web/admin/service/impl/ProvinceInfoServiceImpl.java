package com.wjt.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.ProvinceInfo;
import com.wjt.lease.web.admin.service.ProvinceInfoService;
import com.wjt.lease.web.admin.mapper.ProvinceInfoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【province_info】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class ProvinceInfoServiceImpl extends ServiceImpl<ProvinceInfoMapper, ProvinceInfo> implements ProvinceInfoService{

    /**
     * 查询省份信息列表
     * @return Result<List<ProvinceInfo>>
     */
    @Override
    public Result<List<ProvinceInfo>> listProvince() {
        return Result.ok(list());
    }
}




