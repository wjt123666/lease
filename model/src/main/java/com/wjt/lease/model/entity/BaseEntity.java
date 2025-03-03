package com.wjt.lease.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT) // fill = FieldFill.INSERT 表示在插入时自动填充该字段
    @JsonIgnore // 序列化时不返回该字段
    private Date createTime;

    @Schema(description = "更新时间")
    @TableField(value = "update_time",fill = FieldFill.UPDATE) // fill = FieldFill.UPDATE 表示在更新时自动填充该字段
    @JsonIgnore
    private Date updateTime;

    @Schema(description = "逻辑删除")
    @TableField("is_deleted")
    @TableLogic // 它可以自动为查询操作增加is_deleted=0过滤条件
    @JsonIgnore
    private Byte isDeleted;

}