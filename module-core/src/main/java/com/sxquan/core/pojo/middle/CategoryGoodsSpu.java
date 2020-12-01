package com.sxquan.core.pojo.middle;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * SPU与商品分类中间表
 * </p>
 *
 * @author sxquan
 * @since 2020-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("category_goods_spu")
public class CategoryGoodsSpu implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * spuid
     */
    @TableField("spu_id")
    private Long spuId;

    /**
     * 商品一级类目id
     */
    @TableField("cg_id1")
    private Long cgId1;

    /**
     * 商品二级类目id
     */
    @TableField("cg_id2")
    private Long cgId2;

    /**
     * 商品三级类目id（未使用）
     */
    @TableField("cg_id3")
    private Long cgId3;


}
