package com.sxquan.manage.business.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * <p>
 * 商铺信息表
 * </p>
 *
 * @author sxquan
 * @since 2020-02-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("shop_info")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="ShopInfo对象", description="商铺信息表")
public class ShopInfo implements Serializable {

    private static final long serialVersionUID = 2680457804295290395L;

    /**
     * 门店图片子路径
     */
    public static final String STORE_IMG_SUB_PATH = "/images/shopInfo/store/";

    @ApiModelProperty(value = "主键")
    @TableId(value = "shop_info_id", type = IdType.AUTO)
    private Long shopInfoId;

    @ApiModelProperty(value = "商品名称")
    @TableField("shop_name")
    private String shopName;

    @ApiModelProperty(value = "联系人")
    @TableField("contact_man")
    private String contactMan;

    @ApiModelProperty(value = "联系电话")
    @TableField("contact_mobile")
    private String contactMobile;

    @ApiModelProperty(value = "门店类型")
    @TableField("cate_id")
    private Integer cateId;

    @ApiModelProperty(value = "营业开始时间")
    @TableField("begin_time")
    private LocalTime beginTime;

    @ApiModelProperty(value = "营业结束时间")
    @TableField("end_time")
    private LocalTime endTime;

    @ApiModelProperty(value = "门店图片")
    @TableField("store_img")
    private String storeImg;

    @ApiModelProperty(value = "店内图片")
    @TableField("instore_img")
    private String instoreImg;

    @ApiModelProperty(value = "logo图片")
    @TableField("logo_img")
    private String logoImg;

    @ApiModelProperty(value = "经度")
    @TableField("longitude")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    @TableField("latitude")
    private String latitude;

    @ApiModelProperty(value = "省")
    @TableField("province")
    private String province;

    @ApiModelProperty(value = "市")
    @TableField("city")
    private String city;

    @ApiModelProperty(value = "区")
    @TableField("district")
    private String district;

    @ApiModelProperty(value = "联动路径")
    @TableField("region")
    private String region;

    @ApiModelProperty(value = "详细地址")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "街道/城镇")
    @TableField("street")
    private String street;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "其他图片")
    @TableField("images")
    private String images;

    @ApiModelProperty(value = "商家公告")
    @TableField("notice")
    private String notice;

    @ApiModelProperty(value = "配送时间")
    @TableField("send_time")
    private LocalDateTime sendTime;

    @ApiModelProperty(value = "配送费用")
    @TableField("send_cost")
    private BigDecimal sendCost;

    @ApiModelProperty(value = "起送消费")
    @TableField("floor_send_cost")
    private BigDecimal floorSendCost;

    @ApiModelProperty(value = "状态:0-休息，1-营业")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "创建时间范围")
    private String createTimeRange;

    @TableField(exist = false)
    @ApiModelProperty(value = "营业时间范围")
    private String businessHours;

    @TableField(exist = false)
    private String createTimeStart;

    @TableField(exist = false)
    private String createTimeEnd;
}
