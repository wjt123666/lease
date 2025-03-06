package com.wjt.lease.web.admin.mapper;

import com.wjt.lease.model.entity.LeaseAgreement;
import com.wjt.lease.web.admin.vo.agreement.AgreementQueryVo;
import com.wjt.lease.web.admin.vo.agreement.AgreementVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

/**
* @author liubo
* @description 针对表【lease_agreement(租约信息表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.wjt.lease.model.LeaseAgreement
*/
public interface LeaseAgreementMapper extends BaseMapper<LeaseAgreement> {
    IPage<AgreementVo> pageAgreement(IPage<LeaseAgreement> page, AgreementQueryVo queryVo);
}




