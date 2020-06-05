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
 * SPU与规格组中间表
 * </p>
 *
 * @author sxquan
 * @since 2020-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("spec_group_spu")
@ApiModel(value="SpecGroupSpu对象", description="SPU与规格组中间表")
public class SpecGroupSpu implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "规格组id")
    @TableField("spec_group_id")
    private Long specGroupId;

    @ApiModelProperty(value = "spuid")
    @TableField("spu_id")
    private Long spuId;


}
