package com.wjt.lease.web.admin.service;

import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.LeaseAgreement;
import com.wjt.lease.model.enums.LeaseStatus;
import com.wjt.lease.web.admin.vo.agreement.AgreementQueryVo;
import com.wjt.lease.web.admin.vo.agreement.AgreementVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author liubo
* @description 针对表【lease_agreement(租约信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface LeaseAgreementService extends IService<LeaseAgreement> {

    /**
     * 保存或修改租约信息
     * @param leaseAgreement 租约信息
     * @return Result
     */
    Result saveOrUpdateLeaseAgreement(LeaseAgreement leaseAgreement);

    /**
     * 根据条件分页查询租约列表
     * @param current 页码
     * @param size 每页条数
     * @param queryVo 查询条件
     * @return IPage<AgreementVo>
     */
    Result<IPage<AgreementVo>> pageAgreement(long current, long size, AgreementQueryVo queryVo);

    /**
     * 根据id查询租约信息
     * @param id 租约id
     * @return Result<AgreementVo>
     */
    Result<AgreementVo> getAgreementById(Long id);

    /**
     * 根据id删除租约信息
     * @param id 租约id
     * @return Result
     */
    Result removeAggregateById(Long id);

    /**
     * 根据id更新租约状态
     * @param id 租约id
     * @param status 租约状态
     * @return Result
     */
    Result updateAggregateStatusById(Long id, LeaseStatus status);
}
