package com.wjt.lease.web.admin.service;

import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.LeaseTerm;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @author liubo
* @description 针对表【lease_term(租期)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface LeaseTermService extends IService<LeaseTerm> {

    /**
     * 查询全部租期列表
     * @return Result<List<LeaseTerm>>
     */
    Result<List<LeaseTerm>> listLeaseTerm();


    /**
     * 保存或更新租期信息
     * @param leaseTerm 租期信息
     * @return Result
     */
    Result saveOrUpdateLT(LeaseTerm leaseTerm);


    /**
     * 根据ID删除租期
     * @param id 租期ID
     * @return Result
     */
    Result deleteLeaseTermById(Long id);
}
