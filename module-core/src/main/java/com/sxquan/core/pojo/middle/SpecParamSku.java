package com.sxquan.core.pojo.middle;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * SKU与规格参数中间表
 * </p>
 *
 * @author sxquan
 * @since 2020-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("spec_param_sku")
@ApiModel(value="SpecParamSku对象", description="SKU与规格参数中间表")
public class SpecParamSku implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "规格参数id")
    @TableField("spec_param_id")
    private Long specParamId;

    @ApiModelProperty(value = "skuId")
    @TableField("sku_id")
    private Long skuId;


}
