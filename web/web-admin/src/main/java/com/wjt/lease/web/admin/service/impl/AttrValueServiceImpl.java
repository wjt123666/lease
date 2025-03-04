package com.wjt.lease.web.admin.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjt.lease.common.result.Result;
import com.wjt.lease.common.result.ResultCodeEnum;
import com.wjt.lease.model.entity.AttrValue;
import com.wjt.lease.web.admin.service.AttrValueService;
import com.wjt.lease.web.admin.mapper.AttrValueMapper;
import com.wjt.lease.web.admin.vo.attr.AttrKeyVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【attr_value(房间基本属性值表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class AttrValueServiceImpl extends ServiceImpl<AttrValueMapper, AttrValue> implements AttrValueService{

    /**
     * 新增或更新属性值
     * @param attrValue 属性值
     * @return Result
     */
    @Override
    public Result saveOrUpdateAttrValue(AttrValue attrValue) {
        if (ObjUtil.isNull(attrValue)) return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        return saveOrUpdate(attrValue) ? Result.ok() : Result.fail();
    }

    /**
     * 根据id删除属性值
     * @param id 属性值id
     * @return Result
     */
    @Override
    public Result removeAttrValueById(Long id) {
        if (ObjUtil.isNull(id)) return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        return removeById(id) ? Result.ok() : Result.fail();
    }
}




