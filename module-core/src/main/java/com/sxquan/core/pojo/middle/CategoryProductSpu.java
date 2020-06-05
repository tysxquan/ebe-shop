package com.sxquan.core.pojo.middle;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * SPU与商品分类中间表
 * </p>
 *
 * @author sxquan
 * @since 2020-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("category_product_spu")
@ApiModel(value="CategoryProductSpu对象", description="SPU与商品分类中间表")
public class CategoryProductSpu implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "规格组id")
    @TableField("category_id")
    private Long categoryId;

    @ApiModelProperty(value = "spuid")
    @TableField("spu_id")
    private Long spuId;

    @ApiModelProperty(value = "分类id聚合")
    @TableField("merger_category_id")
    private String mergerCategoryId;

}
