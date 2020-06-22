package com.sxquan.manage.order.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sxquan.core.pojo.order.OrderItem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * @author sxquan
 * @since 2020/5/13 16:42
 */
@Data
public class OrderInfoVO {


    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @ApiModelProperty(value = "订单ID")
    @TableField("order_code")
    private String orderCode;

    @ApiModelProperty(value = "支付类型，1、在线支付，2、货到付款")
    @TableField("pay_type")
    private Integer payType;


    @ApiModelProperty(value = "配送方式：1-自提，2-商品配送")
    @TableField("delivery_mode")
    private Integer deliveryMode;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "用户姓名")
    @TableField("user_true_name")
    private String userTrueMame;

    @ApiModelProperty(value = "用户手机号")
    @TableField("user_mobile")
    private String userMobile;

    /**
     * 收货地址第一级地址
     */
    @TableField("province_name")
    private String provinceName;

    /**
     * 收货地址第二级地址
     */
    @TableField("city_name")
    private String cityName;

    /**
     * 收货地址第三级地址
     */
    @TableField("county_name")
    private String countyName;

    @ApiModelProperty(value = "用户详细地址")
    @TableField("user_address_detail")
    private String userAddressDetail;

    @ApiModelProperty(value = "商铺ID")
    @TableField("shop_info_id")
    private Long shopInfoId;

    @ApiModelProperty(value = "配送费")
    @TableField("send_cost")
    private BigDecimal sendCost;

    @ApiModelProperty(value = "总价")
    @TableField("total_money")
    private BigDecimal totalMoney;

    @ApiModelProperty(value = "优惠金额")
    @TableField("discount_money")
    private BigDecimal discountMoney;

    @ApiModelProperty(value = "优惠劵ID")
    @TableField("coupon_id")
    private String couponId;

    @ApiModelProperty(value = "实付金额")
    @TableField("pay_money")
    private BigDecimal payMoney;

    @ApiModelProperty(value = "送货员ID")
    @TableField("delivery_id")
    private Long deliveryId;

    @ApiModelProperty(value = "送货员姓名")
    @TableField("delivery_name")
    private String deliveryName;

    @ApiModelProperty(value = "送货员联系电话")
    @TableField("delivery_mobile")
    private String deliveryMobile;

    @ApiModelProperty(value = "付款时间")
    @TableField("payment_time")
    private LocalDateTime paymentTime;

    @ApiModelProperty(value = "状态;0：取消订单,1:等待买家付款,2:买家已付款,3:等待商家送货,4:商家已送达，5：确定收货，6：订单完成")
    @TableField("status")
    private Integer status;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "交易完成时间")
    @TableField("end_time")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "预约配送的时间点（开始）")
    @TableField("reserve_start_time")
    private LocalTime reserveStartTime;

    @ApiModelProperty(value = "预约配送的时间点（结束）")
    @TableField("reserve_over_time")
    private LocalTime reserveOverTime;

    @ApiModelProperty(value = "预约日期")
    @TableField("reserve_date")
    private LocalDate reserveDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


    private List<OrderItem> orderItems;


}
