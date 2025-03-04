package com.wjt.lease.web.admin.service;

import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.CityInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @author liubo
* @description 针对表【city_info】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface CityInfoService extends IService<CityInfo> {

    /**
     * 根据省份id查询城市信息列表
     * @param id 省份id
     * @return Result<List<CityInfo>>
     */
    Result<List<CityInfo>> listCityInfoByProvinceId(Long id);

}
