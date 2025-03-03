package com.wjt.lease.web.admin.service;

import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.LabelInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wjt.lease.model.enums.ItemType;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @author liubo
* @description 针对表【label_info(标签信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface LabelInfoService extends IService<LabelInfo> {

    /**
     * （根据类型）查询标签列表
     * @param type 标签类型
     * @return 标签列表
     */
    Result<List<LabelInfo>> labelList(ItemType type);


    /**
     * 新增或修改标签信息
     * @param labelInfo 标签信息
     * @return Result
     */
    Result saveOrUpdateLabel(LabelInfo labelInfo);


    /**
     * 根据id删除标签信息
     * @param id 标签id
     * @return Result
     */
    Result deleteLabelById(Long id);

}



