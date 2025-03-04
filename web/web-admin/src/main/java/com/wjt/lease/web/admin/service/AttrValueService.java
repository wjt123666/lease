package com.wjt.lease.web.admin.service;

import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.AttrValue;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wjt.lease.web.admin.vo.attr.AttrKeyVo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @author liubo
* @description 针对表【attr_value(房间基本属性值表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface AttrValueService extends IService<AttrValue> {


    /**
     * 新增或更新属性值
     * @param attrValue 属性值
     * @return Result
     */
    Result saveOrUpdateAttrValue(AttrValue attrValue);

    /**
     * 根据id删除属性值
     * @param id 属性值id
     * @return Result
     */
    Result removeAttrValueById(Long id);
}
