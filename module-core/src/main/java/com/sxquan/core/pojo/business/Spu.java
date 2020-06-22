package com.sxquan.core.pojo.business;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * SPU信息表
 * </p>
 *
 * @author sxquan
 * @since 2020-03-12
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = false)
@TableName("spu")
@ApiModel(value="Spu对象", description="SPU信息表")
public class Spu implements Serializable {

    private static final long serialVersionUID = -5378576964047857059L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "spu_id", type = IdType.AUTO)
    private Long spuId;

    @ApiModelProperty(value = "商品标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "副标题")
    @TableField("subhead")
    private String subhead;

    @ApiModelProperty(value = "商品封面主图")
    @TableField("cover")
    private String cover;

    @ApiModelProperty(value = "商品轮播图")
    @TableField("banner_images")
    private String bannerImages;

    @ApiModelProperty(value = "详情图")
    @TableField("detail_img")
    private String detailImg;

    @ApiModelProperty(value = "原价")
    @TableField("origin_price")
    private BigDecimal originPrice;

    @ApiModelProperty(value = "售价")
    @TableField("sell_price")
    private BigDecimal sellPrice;

    @ApiModelProperty(value = "折扣")
    @TableField("discount")
    private BigDecimal discount;

    @ApiModelProperty(value = "收藏数")
    @TableField("collect")
    private Integer collect;

    @ApiModelProperty(value = "限购数量")
    @TableField("limit_num")
    private Integer limitNum;

    @ApiModelProperty(value = "单位")
    @TableField("unit")
    private String unit;

    @ApiModelProperty(value = "总的销量")
    @TableField("total_sales")
    private Integer totalSales;

    @ApiModelProperty(value = "月销量")
    @TableField("month_sales")
    private Integer monthSales;

    @ApiModelProperty(value = "好评率")
    @TableField("praise_rate")
    private BigDecimal praiseRate;

    @ApiModelProperty(value = "描述")
    @TableField("description")
    private String description;


    @ApiModelProperty(value = "状态；1：上架，2：下架")
    @TableField("status")
    private Integer status;

    @JsonIgnore
    @ApiModelProperty(value = "是否删除；0:未删除，1：已删除")
    @TableField("is_deleted")
    private Boolean deleted;

    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "聚合分类id")
    @TableField(exist = false)
    private String mergerCategoryId;

    @ApiModelProperty(value = "规格组")
    @TableField(exist = false)
    private String specGroup;

    @ApiModelProperty(value = "分类名")
    @TableField(exist = false)
    private String categoryName;
}
