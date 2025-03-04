package com.wjt.lease.web.admin.service;

import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.AttrKey;
import com.wjt.lease.model.entity.AttrValue;
import com.wjt.lease.web.admin.vo.attr.AttrKeyVo;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @author liubo
* @description 针对表【attr_key(房间基本属性表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface AttrKeyService extends IService<AttrKey> {

    /**
     * 新增或更新属性名称
     * @param attrKey 属性名称
     * @return Result
     */
    Result saveOrUpdateAttrKey(AttrKey attrKey);


    /**
     * 查询全部属性名称和属性值列表
     * @return Result<List<AttrKeyVo>>
     */
    Result<List<AttrKeyVo>> listAttrInfo();

    /**
     * 根据id删除属性名称
     * @param attrKeyId 属性名称id
     * @return Result
     */
    Result removeAttrKeyById(Long attrKeyId);
}
