package com.wjt.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.AttrKey;
import com.wjt.lease.model.entity.AttrValue;
import com.wjt.lease.web.admin.vo.attr.AttrKeyVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【attr_key(房间基本属性表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.wjt.lease.model.AttrKey
*/
public interface AttrKeyMapper extends BaseMapper<AttrKey> {

    List<AttrKeyVo> listAttrInfo();
}




