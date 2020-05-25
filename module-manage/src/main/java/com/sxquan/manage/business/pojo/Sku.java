package com.sxquan.manage.business.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sxquan.manage.spec.pojo.SpecGroupParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * sku表
 * </p>
 *
 * @author sxquan
 * @since 2020-03-12
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = false)
@TableName("sku")
@ApiModel(value="Sku对象", description="sku表")
public class Sku implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 图片子路径
     */
    public static final String IMAGE_SUB_PATH = "/images/sku/";

    @ApiModelProperty(value = "主键")
    @TableId(value = "sku_id", type = IdType.AUTO)
    private Long skuId;

    @ApiModelProperty(value = "编码")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "spu_id关联")
    @TableField("spu_id")
    private String spuId;

    @ApiModelProperty(value = "标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "图片")
    @TableField("image")
    private String image;

    @ApiModelProperty(value = "原价")
    @TableField("origin_price")
    private BigDecimal originPrice;

    @ApiModelProperty(value = "售价")
    @TableField("sell_price")
    private BigDecimal sellPrice;

    @ApiModelProperty(value = "折扣价")
    @TableField("discount")
    private BigDecimal discount;

    @ApiModelProperty(value = "库存")
    @TableField("inventory")
    private Integer inventory;

    @ApiModelProperty(value = "状态：0-禁用，1-启用")
    @TableField("status")
    private Integer status;

    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private String paramIds;


    @TableField(exist = false)
    private List<SpecGroupParam> groupParam;

}
