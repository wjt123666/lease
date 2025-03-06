package com.wjt.lease.web.admin.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.*;
import com.wjt.lease.model.enums.LeaseStatus;
import com.wjt.lease.web.admin.mapper.*;
import com.wjt.lease.web.admin.service.LeaseAgreementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjt.lease.web.admin.vo.agreement.AgreementQueryVo;
import com.wjt.lease.web.admin.vo.agreement.AgreementVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 * @description 针对表【lease_agreement(租约信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class LeaseAgreementServiceImpl extends ServiceImpl<LeaseAgreementMapper, LeaseAgreement> implements LeaseAgreementService {

    @Autowired
    private LeaseAgreementMapper leaseAgreementMapper;
    @Autowired
    private ApartmentInfoMapper apartmentInfoMapper;
    @Autowired
    private RoomInfoMapper roomInfoMapper;
    @Autowired
    private PaymentTypeMapper paymentTypeMapper;
    @Autowired
    private LeaseTermMapper leaseTermMapper;

    /**
     * 保存或修改租约信息
     * @param leaseAgreement 租约信息
     * @return Result
     */
    @Override
    public Result saveOrUpdateLeaseAgreement(LeaseAgreement leaseAgreement) {
        return super.saveOrUpdate(leaseAgreement)? Result.ok() : Result.fail(500, "保存或修改租约信息失败");
    }

    /**
     * 根据条件分页查询租约列表
     * @param current 页码
     * @param size 每页条数
     * @param queryVo 查询条件
     * @return IPage<AgreementVo>
     */
    @Override
    public Result<IPage<AgreementVo>> pageAgreement(long current, long size, AgreementQueryVo queryVo) {
        IPage<LeaseAgreement> page = new Page<>(current, size);
        IPage<AgreementVo> res = leaseAgreementMapper.pageAgreement(page, queryVo);
        return Result.ok(res);
    }

    /**
     * 根据id查询租约信息
     * @param id 租约id
     * @return Result<AgreementVo>
     */
    @Override
    public Result<AgreementVo> getAgreementById(Long id) {
        // 根据租约id查询租约信息
        LeaseAgreement leaseAgreement = leaseAgreementMapper.selectById(id);
        if (ObjUtil.isNull(leaseAgreement)) return Result.fail(500, "租约信息不存在");

        // 根据公寓id查询公寓信息
        ApartmentInfo apartmentInfo = apartmentInfoMapper.selectById(leaseAgreement.getApartmentId());
        // 根据房间id查询房间信息
        RoomInfo roomInfo = roomInfoMapper.selectById(leaseAgreement.getRoomId());
        // 根据支付方式id查询支付方式
        PaymentType paymentType = paymentTypeMapper.selectById(leaseAgreement.getPaymentTypeId());
        // 根据租期id查询租期信息
        LeaseTerm leaseTerm = leaseTermMapper.selectById(leaseAgreement.getLeaseTermId());

        // 封装返回结果
        AgreementVo agreementVo = new AgreementVo();
        BeanUtils.copyProperties(leaseAgreement, agreementVo);
        agreementVo.setApartmentInfo(apartmentInfo);
        agreementVo.setRoomInfo(roomInfo);
        agreementVo.setPaymentType(paymentType);
        agreementVo.setLeaseTerm(leaseTerm);
        return Result.ok(agreementVo);
    }

    /**
     * 根据id删除租约信息
     * @param id 租约id
     * @return Result
     */
    @Override
    public Result removeAggregateById(Long id) {
        return super.removeById(id) ? Result.ok() : Result.fail(500, "删除租约信息失败");
    }

    /**
     * 根据id更新租约状态
     * @param id 租约id
     * @param status 租约状态
     * @return Result
     */
    @Override
    public Result updateAggregateStatusById(Long id, LeaseStatus status) {
        boolean b = super.update(new LambdaUpdateWrapper<LeaseAgreement>()
                .eq(LeaseAgreement::getId, id)
                .set(LeaseAgreement::getStatus, status));
        return b ? Result.ok() : Result.fail(500, "更新租约状态失败");
    }
}




