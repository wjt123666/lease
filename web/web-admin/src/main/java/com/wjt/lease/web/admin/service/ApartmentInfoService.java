package com.wjt.lease.web.admin.service;

import com.wjt.lease.common.result.Result;
import com.wjt.lease.model.entity.ApartmentInfo;
import com.wjt.lease.model.enums.ReleaseStatus;
import com.wjt.lease.web.admin.vo.apartment.ApartmentDetailVo;
import com.wjt.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.wjt.lease.web.admin.vo.apartment.ApartmentQueryVo;
import com.wjt.lease.web.admin.vo.apartment.ApartmentSubmitVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @author liubo
* @description 针对表【apartment_info(公寓信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface ApartmentInfoService extends IService<ApartmentInfo> {

    /**
     * 保存或更新公寓信息
     * @param apartmentSubmitVo 公寓信息提交表单
     * @return Result
     */
    Result saveOrUpdateApartment(ApartmentSubmitVo apartmentSubmitVo);

    /**
     * 根据条件分页查询公寓列表
     * @param current 页码
     * @param size 每页条数
     * @param queryVo 查询条件
     * @return IPage<ApartmentItemVo>
     */
    Result<IPage<ApartmentItemVo>> pageItem(long current, long size, ApartmentQueryVo queryVo);

    /**
     * 根据ID获取公寓详细信息
     * @param id 公寓ID
     * @return ApartmentDetailVo
     */
    Result<ApartmentDetailVo> getDetailById(Long id);

    /**
     * 根据id删除公寓信息
     * @param id 公寓ID
     * @return Result
     */
    Result removeApartmentById(Long id);

    /**
     * 根据id修改公寓发布状态
     * @param id 公寓ID
     * @param status 发布状态
     * @return Result
     */
    Result updateReleaseStatusById(Long id, ReleaseStatus status);

    /**
     * 根据区县id查询公寓信息列表
     * @param id 区县ID
     * @return List<ApartmentInfo>
     */
    Result<List<ApartmentInfo>> listInfoByDistrictId(Long id);
}
