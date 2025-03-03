package com.wjt.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.LeaseTerm;
import com.wjt.lease.web.admin.service.LeaseTermService;
import com.wjt.lease.web.admin.mapper.LeaseTermMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【lease_term(租期)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class LeaseTermServiceImpl extends ServiceImpl<LeaseTermMapper, LeaseTerm> implements LeaseTermService{

    /**
     * 查询全部租期列表
     * @return Result<List<LeaseTerm>>
     */
    @Override
    public Result<List<LeaseTerm>> listLeaseTerm() {
        return Result.ok(list());
    }

    /**
     * 保存或更新租期信息
     * @param leaseTerm 租期信息
     * @return Result
     */
    @Override
    public Result saveOrUpdateLT(LeaseTerm leaseTerm) {
        return saveOrUpdate(leaseTerm) ? Result.ok() : Result.fail();
    }

    /**
     * 根据ID删除租期
     * @param id 租期ID
     * @return Result
     */
    @Override
    public Result deleteLeaseTermById(Long id) {
        return removeById(id) ? Result.ok() : Result.fail();
    }
}




