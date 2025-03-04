package com.wjt.lease.web.admin.service;

import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.DistrictInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @author liubo
* @description 针对表【district_info】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface DistrictInfoService extends IService<DistrictInfo> {

    /**
     * 根据城市id查询区县信息
     * @param id 城市id
     * @return 区县信息列表
     */
    Result<List<DistrictInfo>> listDistrictInfoByCityId(Long id);
}
