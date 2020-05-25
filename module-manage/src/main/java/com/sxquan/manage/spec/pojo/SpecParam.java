package com.sxquan.manage.spec.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 规格组下的参数表
 * </p>
 *
 * @author sxquan
 * @since 2020-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("spec_param")
@ApiModel(value="SpecParam对象", description="规格组下的参数表")
public class SpecParam implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "spec_param_id", type = IdType.AUTO)
    private Long specParamId;

    @ApiModelProperty(value = "参数名")
    @TableField("param_name")
    private String paramName;

    @ApiModelProperty(value = "数字类型参数的单位，非数字类型可以为空")
    @TableField("unit")
    private String unit;

    @ApiModelProperty(value = "是否是数字类型参数，0-false,1-true")
    @TableField("is_number")
    private Boolean number;

    @ApiModelProperty(value = "是否是sku通用属性，0-false,1-true")
    @TableField("generic")
    private Boolean generic;

    @ApiModelProperty(value = "是否用于搜索过滤，0-false,1-true")
    @TableField("searching")
    private Boolean searching;

    @ApiModelProperty(value = "数值类型参数，如果需要搜索，则添加分段间隔值，如CPU频率间隔：0.5-1.0")
    @TableField("segments")
    private String segments;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private Long specGroupId;

}
