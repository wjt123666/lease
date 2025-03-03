package com.wjt.lease.web.admin.service.impl;

import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjt.lease.common.result.Result;
import com.wjt.lease.common.result.ResultCodeEnum;
import com.wjt.lease.model.entity.LabelInfo;
import com.wjt.lease.model.enums.ItemType;
import com.wjt.lease.web.admin.service.LabelInfoService;
import com.wjt.lease.web.admin.mapper.LabelInfoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【label_info(标签信息表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class LabelInfoServiceImpl extends ServiceImpl<LabelInfoMapper, LabelInfo> implements LabelInfoService{

    /**
     * （根据类型）查询标签列表
     * @param type 标签类型
     * @return 标签列表
     */
    @Override
    public Result<List<LabelInfo>> labelList(ItemType type) {
        return Result.ok(list(
                new LambdaQueryWrapper<LabelInfo>()
                        .eq(ObjUtil.isNotNull(type),LabelInfo::getType,type))
        );
    }

    /**
     * 新增或修改标签信息
     * @param labelInfo 标签信息
     * @return Result
     */
    @Override
    public Result saveOrUpdateLabel(LabelInfo labelInfo) {
        if (ObjUtil.isNull(labelInfo)) return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        return saveOrUpdate(labelInfo) ? Result.ok() : Result.fail();
    }

    /**
     * 根据id删除标签信息
     * @param id 标签id
     * @return Result
     */
    @Override
    public Result deleteLabelById(Long id) {
        if (ObjUtil.isNull(id)) return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        return removeById(id) ? Result.ok() : Result.fail();
    }
}




