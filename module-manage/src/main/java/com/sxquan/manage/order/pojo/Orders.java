package com.sxquan.manage.order.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * 订单主表
 * </p>
 *
 * @author sxquan
 * @since 2020-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("orders")
@ApiModel(value="Orders对象", description="订单主表")
public class Orders implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "订单ID")
    @TableField("order_id")
    private String orderId;

    @ApiModelProperty(value = "支付类型，1、在线支付，2、货到付款")
    @TableField("pay_type")
    private String payType;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "用户姓名")
    @TableField("user_true_name")
    private String userTrueMame;

    @ApiModelProperty(value = "用户手机号")
    @TableField("user_mobile")
    private String userMobile;

    @ApiModelProperty(value = "用户地区名称(省，市，县)街道")
    @TableField("user_receiver_area_name")
    private String userReceiverAreaName;

    @ApiModelProperty(value = "用户详细地址")
    @TableField("user_address")
    private String userAddress;

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
    @TableField("system_user_id")
    private Long systemUserId;

    @ApiModelProperty(value = "送货员姓名")
    @TableField("system_user_name")
    private String systemUserName;

    @ApiModelProperty(value = "送货员联系电话")
    @TableField("system_user_mobile")
    private String systemUserMobile;

    @ApiModelProperty(value = "付款时间")
    @TableField("payment_time")
    private LocalDateTime paymentTime;

    @ApiModelProperty(value = "状态;0：取消订单,1:等待买家付款,2:买家已付款,3:等待商家送货,4:商家已送达，5：确定收货，6：订单完成")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "交易完成时间")
    @TableField("end_time")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "限定的时间")
    @TableField("demand_time")
    private LocalTime demandTime;

    @ApiModelProperty(value = "预约时间")
    @TableField("reserve_time")
    private LocalDateTime reserveTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


}
