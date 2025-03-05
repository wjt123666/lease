package com.wjt.lease.web.admin.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjt.lease.common.exception.LeaseException;
import com.wjt.lease.common.result.Result;
import com.wjt.lease.common.result.ResultCodeEnum;
import com.wjt.lease.model.entity.*;
import com.wjt.lease.model.enums.ItemType;
import com.wjt.lease.model.enums.ReleaseStatus;
import com.wjt.lease.web.admin.mapper.*;
import com.wjt.lease.web.admin.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjt.lease.web.admin.vo.apartment.ApartmentDetailVo;
import com.wjt.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.wjt.lease.web.admin.vo.apartment.ApartmentQueryVo;
import com.wjt.lease.web.admin.vo.apartment.ApartmentSubmitVo;
import com.wjt.lease.web.admin.vo.fee.FeeValueVo;
import com.wjt.lease.web.admin.vo.graph.GraphVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author liubo
 * @description 针对表【apartment_info(公寓信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class ApartmentInfoServiceImpl extends ServiceImpl<ApartmentInfoMapper, ApartmentInfo> implements ApartmentInfoService {

    @Autowired
    private GraphInfoService graphInfoService;
    @Autowired
    private ApartmentFacilityService apartmentFacilityService;
    @Autowired
    private ApartmentLabelService apartmentLabelService;
    @Autowired
    private ApartmentFeeValueService apartmentFeeValueService;
    @Autowired
    private ApartmentInfoMapper apartmentInfoMapper;


    @Autowired
    private GraphInfoMapper graphInfoMapper;
    @Autowired
    private LabelInfoMapper labelInfoMapper;
    @Autowired
    private FacilityInfoMapper facilityInfoMapper;
    @Autowired
    private FeeValueMapper feeValueMapper;
    @Autowired
    private RoomInfoMapper roomInfoMapper;

    /**
     * 保存或更新公寓信息
     * @param apartmentSubmitVo 公寓信息提交表单
     * @return Result
     */
    @Transactional
    @Override
    public Result saveOrUpdateApartment(ApartmentSubmitVo apartmentSubmitVo) {
        Long apartmentId = apartmentSubmitVo.getId();
        // 判断是新增还是更新
        boolean isUpdate = ObjUtil.isNotNull(apartmentId);
        super.saveOrUpdate(apartmentSubmitVo);
        if (isUpdate) {
            // 1.删除图片列表
            graphInfoService.remove(new LambdaQueryWrapper<GraphInfo>()
                            .eq(GraphInfo::getItemId, apartmentId)
                            .eq(GraphInfo::getItemType, ItemType.APARTMENT));
            // 2.删除配套列表
            apartmentFacilityService.remove(new LambdaQueryWrapper<ApartmentFacility>()
                    .eq(ApartmentFacility::getApartmentId, apartmentId));
            // 3.删除标签列表
            apartmentLabelService.remove(new LambdaQueryWrapper<ApartmentLabel>()
                    .eq(ApartmentLabel::getApartmentId, apartmentId));
            // 4.删除杂费列表
            apartmentFeeValueService.remove(new LambdaQueryWrapper<ApartmentFeeValue>()
                    .eq(ApartmentFeeValue::getApartmentId, apartmentId));
        }
        // 1.新增图片列表
        List<GraphVo> graphVoList = apartmentSubmitVo.getGraphVoList();
        if (!CollectionUtils.isEmpty(graphVoList)) {
            ArrayList<GraphInfo> graphInfos = new ArrayList<>();
            for (GraphVo graphVo : graphVoList) {
                GraphInfo graphInfo = new GraphInfo();
                BeanUtils.copyProperties(graphVo, graphInfo);
                graphInfo.setItemId(apartmentId);
                graphInfo.setItemType(ItemType.APARTMENT);
                graphInfos.add(graphInfo);

            }
            graphInfoService.saveBatch(graphInfos);
        }
        // 2.新增配套列表
        List<Long> facilityInfoIds = apartmentSubmitVo.getFacilityInfoIds();
        if (!CollectionUtils.isEmpty(facilityInfoIds)) {
            ArrayList<ApartmentFacility> apartmentFacilities = new ArrayList<>();
            for (Long facilityInfoId : facilityInfoIds) {
                ApartmentFacility apartmentFacility = new ApartmentFacility();
                apartmentFacility.setApartmentId(apartmentId);
                apartmentFacility.setFacilityId(facilityInfoId);
                apartmentFacilities.add(apartmentFacility);
            }
            apartmentFacilityService.saveBatch(apartmentFacilities);
        }
        // 3.新增标签列表
        List<Long> labelIds = apartmentSubmitVo.getLabelIds();
        if (!CollectionUtils.isEmpty(labelIds)) {
            ArrayList<ApartmentLabel> apartmentLabels = new ArrayList<>();
            for (Long labelId : labelIds) {
                ApartmentLabel apartmentLabel = new ApartmentLabel();
                apartmentLabel.setApartmentId(apartmentId);
                apartmentLabel.setLabelId(labelId);
                apartmentLabels.add(apartmentLabel);
            }
            apartmentLabelService.saveBatch(apartmentLabels);
        }
        // 4.新增杂费列表
        List<Long> feeValueIds = apartmentSubmitVo.getFeeValueIds();
        if (!CollectionUtils.isEmpty(feeValueIds)) {
            ArrayList<ApartmentFeeValue> apartmentFeeValues = new ArrayList<>();
            for (Long feeValueId : feeValueIds) {
                ApartmentFeeValue apartmentFeeValue = new ApartmentFeeValue();
                apartmentFeeValue.setApartmentId(apartmentId);
                apartmentFeeValue.setFeeValueId(feeValueId);
                apartmentFeeValues.add(apartmentFeeValue);
            }
            apartmentFeeValueService.saveBatch(apartmentFeeValues);
        }
        return Result.ok();
    }

    /**
     * 根据条件分页查询公寓列表
     * @param current 页码
     * @param size 每页条数
     * @param queryVo 查询条件
     * @return IPage<ApartmentItemVo>
     */
    @Override
    public Result<IPage<ApartmentItemVo>> pageItem(long current, long size, ApartmentQueryVo queryVo) {
        // 1.1构造分页条件
        IPage<ApartmentItemVo> page = new Page<>(current, size);
        IPage<ApartmentItemVo> pageResult = apartmentInfoMapper.pageItem(page, queryVo);
        return Result.ok(pageResult);
    }

    /**
     * 根据ID获取公寓详细信息
     * @param id 公寓ID
     * @return ApartmentDetailVo
     */
    @Override
    public Result<ApartmentDetailVo> getDetailById(Long id) {
        // 1.查询id对应的公寓信息
        ApartmentInfo apartmentInfo = super.getById(id);
        if (ObjUtil.isNull(apartmentInfo)) throw new LeaseException(ResultCodeEnum.DATA_ERROR);

        // 2.查询图片列表
        List<GraphVo> graphVoList = graphInfoMapper.selectGraphVoList(id, ItemType.APARTMENT);

        // 3.查询标签列表
        List<LabelInfo> labelInfoList = labelInfoMapper.selectLabelInfoList(id);

        // 4.查询配套列表
        List<FacilityInfo> facilityInfoList = facilityInfoMapper.selectFacilityInfoList(id);

        // 5.查询杂费列表
        List<FeeValueVo> feeValueVoList = feeValueMapper.selectFeeValueVoList(id);

        // 6.组装数据并返回
        ApartmentDetailVo apartmentDetailVo = new ApartmentDetailVo();
        BeanUtils.copyProperties(apartmentInfo, apartmentDetailVo);
        apartmentDetailVo.setGraphVoList(graphVoList);
        apartmentDetailVo.setLabelInfoList(labelInfoList);
        apartmentDetailVo.setFacilityInfoList(facilityInfoList);
        apartmentDetailVo.setFeeValueVoList(feeValueVoList);
        return Result.ok(apartmentDetailVo);
    }

    /**
     * 根据id删除公寓信息
     * @param apartmentId 公寓ID
     * @return Result
     */
    @Override
    @Transactional
    public Result removeApartmentById(Long apartmentId) {
        // 判断当前公寓下是否有房间信息
        Long count = roomInfoMapper.selectCount(new LambdaQueryWrapper<RoomInfo>()
                .eq(RoomInfo::getApartmentId, apartmentId));
        if (count > 0) {
            throw new LeaseException(ResultCodeEnum.ADMIN_APARTMENT_DELETE_ERROR);
        }

        // 0.根据id删除公寓信息
        super.removeById(apartmentId);
        // 1.删除图片列表
        graphInfoService.remove(new LambdaQueryWrapper<GraphInfo>()
                .eq(GraphInfo::getItemId, apartmentId)
                .eq(GraphInfo::getItemType, ItemType.APARTMENT));
        // 2.删除配套列表
        apartmentFacilityService.remove(new LambdaQueryWrapper<ApartmentFacility>()
                .eq(ApartmentFacility::getApartmentId, apartmentId));
        // 3.删除标签列表
        apartmentLabelService.remove(new LambdaQueryWrapper<ApartmentLabel>()
                .eq(ApartmentLabel::getApartmentId, apartmentId));
        // 4.删除杂费列表
        apartmentFeeValueService.remove(new LambdaQueryWrapper<ApartmentFeeValue>()
                .eq(ApartmentFeeValue::getApartmentId, apartmentId));
        return Result.ok();
    }

    /**
     * 根据id修改公寓发布状态
     * @param id 公寓ID
     * @param status 发布状态
     * @return Result
     */
    @Override
    public Result updateReleaseStatusById(Long id, ReleaseStatus status) {
        boolean b = super.update(new LambdaUpdateWrapper<ApartmentInfo>()
                .eq(ApartmentInfo::getId, id)
                .set(ApartmentInfo::getIsRelease, status));

        return b ? Result.ok() : Result.fail();
    }

    /**
     * 根据区县id查询公寓信息列表
     * @param id 区县ID
     * @return List<ApartmentInfo>
     */
    @Override
    public Result<List<ApartmentInfo>> listInfoByDistrictId(Long id) {
        List<ApartmentInfo> apartmentInfos = super.list(new LambdaQueryWrapper<ApartmentInfo>()
                .eq(ApartmentInfo::getDistrictId, id));
        return Result.ok(apartmentInfos);
    }
}




