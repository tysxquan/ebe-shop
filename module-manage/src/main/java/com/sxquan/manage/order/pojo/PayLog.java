package com.sxquan.manage.order.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单支付日志
 * </p>
 *
 * @author sxquan
 * @since 2020-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pay_log")
@ApiModel(value="PayLog对象", description="订单支付日志")
public class PayLog implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "pay_id", type = IdType.AUTO)
    private Long payId;

    @ApiModelProperty(value = "交易号码")
    @TableField("transaction_id")
    private String transactionId;

    @ApiModelProperty(value = "支付金额（分）")
    @TableField("total_fee")
    private BigDecimal totalFee;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "交易状态")
    @TableField("trade_state")
    private String tradeState;

    @ApiModelProperty(value = "订单编号")
    @TableField("order_id")
    private String orderId;

    @ApiModelProperty(value = "支付类型：1-微信,2-支付宝")
    @TableField("pay_type")
    private Integer payType;

    @ApiModelProperty(value = "支付完成时间")
    @TableField("pay_time")
    private LocalDateTime payTime;

    @ApiModelProperty(value = "创建日期")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


}
