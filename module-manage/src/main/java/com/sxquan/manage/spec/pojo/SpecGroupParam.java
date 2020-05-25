package com.sxquan.manage.spec.pojo;

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
 * 规格组与规格参数中间表
 * </p>
 *
 * @author sxquan
 * @since 2020-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("spec_group_param")
@ApiModel(value="SpecGroupParam对象", description="规格组与规格参数中间表")
public class SpecGroupParam implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "规格组id")
    @TableField("spec_group_id")
    private Long specGroupId;

    @ApiModelProperty(value = "规格参数id")
    @TableField("spec_param_id")
    private Long specParamId;


}
