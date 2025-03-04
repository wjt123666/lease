package com.wjt.lease.web.admin.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wjt.lease.common.result.Result;
import com.wjt.lease.common.result.ResultCodeEnum;
import com.wjt.lease.model.entity.FeeKey;
import com.wjt.lease.model.entity.FeeValue;
import com.wjt.lease.web.admin.mapper.FeeKeyMapper;
import com.wjt.lease.web.admin.service.FeeKeyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjt.lease.web.admin.service.FeeValueService;
import com.wjt.lease.web.admin.vo.fee.FeeKeyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author liubo
* @description 针对表【fee_key(杂项费用名称表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class FeeKeyServiceImpl extends ServiceImpl<FeeKeyMapper, FeeKey> implements FeeKeyService{

    @Autowired
    private FeeKeyMapper feeKeyMapper;
    @Autowired
    private FeeValueService feeValueService;

    /**
     * 保存或更新杂费名称
     * @param feeKey 杂费名称
     * @return Result
     */
    @Override
    public Result saveOrUpdateFeeKey(FeeKey feeKey) {
        if (ObjUtil.isNull(feeKey)) return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        return saveOrUpdate(feeKey) ? Result.ok() : Result.fail();
    }

    /**
     * 查询全部杂费名称和杂费值列表
     * @return Result<List<FeeKeyVo>>
     */
    @Override
    public Result<List<FeeKeyVo>> feeInfoList() {
        return Result.ok(feeKeyMapper.feeInfoList());
    }

    /**
     * 根据id删除杂费名称
     * @param feeKeyId 杂费名称id
     * @return Result
     */
    @Transactional
    @Override
    public Result deleteFeeKeyById(Long feeKeyId) {
        if (ObjUtil.isNull(feeKeyId)) return Result.build(null, ResultCodeEnum.PARAM_ERROR);

        // 根据id删除杂费名称
        boolean res = removeById(feeKeyId);
        // 根据杂费名称id删除杂费值
        boolean resV = feeValueService.remove(new LambdaQueryWrapper<FeeValue>().eq(FeeValue::getFeeKeyId, feeKeyId));

        // 返回结果
        return res && resV? Result.ok() : Result.fail();
    }
}




