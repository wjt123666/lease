package com.wjt.lease.web.admin.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wjt.lease.common.result.Result;
import com.wjt.lease.common.result.ResultCodeEnum;
import com.wjt.lease.model.entity.AttrKey;
import com.wjt.lease.model.entity.AttrValue;
import com.wjt.lease.web.admin.mapper.AttrKeyMapper;
import com.wjt.lease.web.admin.mapper.AttrValueMapper;
import com.wjt.lease.web.admin.service.AttrKeyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjt.lease.web.admin.service.AttrValueService;
import com.wjt.lease.web.admin.vo.attr.AttrKeyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author liubo
* @description 针对表【attr_key(房间基本属性表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class AttrKeyServiceImpl extends ServiceImpl<AttrKeyMapper, AttrKey> implements AttrKeyService{

    @Autowired
    private AttrKeyMapper attrKeyMapper;

    @Autowired
    private AttrValueService attrValueService;

    /**
     * 新增或更新属性名称
     * @param attrKey 属性名称
     * @return Result
     */
    @Override
    public Result saveOrUpdateAttrKey(AttrKey attrKey) {
        if (ObjUtil.isNull(attrKey)) return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        return saveOrUpdate(attrKey) ? Result.ok() : Result.fail();
    }

    /**
     * 查询全部属性名称和属性值列表
     * @return Result<List<AttrKeyVo>>
     */
    @Override
    public Result<List<AttrKeyVo>> listAttrInfo() {
        return Result.ok(attrKeyMapper.listAttrInfo());
    }

    /**
     * 根据id删除属性名称
     * @param attrKeyId 属性名称id
     * @return Result
     */
    @Transactional
    @Override
    public Result removeAttrKeyById(Long attrKeyId) {
        // 删除属性名称时还需要将对应的所有属性值删除
        if (ObjUtil.isNull(attrKeyId)) return Result.build(null, ResultCodeEnum.PARAM_ERROR);

        //先删除属性名
        boolean res = removeById(attrKeyId);

        //再删除属性值
        boolean resV = attrValueService.remove(
                new LambdaQueryWrapper<AttrValue>()
                        .eq(AttrValue::getAttrKeyId, attrKeyId));

        return res && resV ? Result.ok() : Result.fail();
    }
}




