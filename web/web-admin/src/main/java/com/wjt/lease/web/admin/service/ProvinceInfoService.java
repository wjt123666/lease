package com.wjt.lease.web.admin.service;

import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.ProvinceInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
* @author liubo
* @description 针对表【province_info】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface ProvinceInfoService extends IService<ProvinceInfo> {

    /**
     * 查询省份信息列表
     * @return Result<List<ProvinceInfo>>
     */
    Result<List<ProvinceInfo>> listProvince();

}
