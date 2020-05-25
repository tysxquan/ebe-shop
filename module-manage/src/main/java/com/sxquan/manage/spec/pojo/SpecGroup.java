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
 * 规格组表
 * </p>
 *
 * @author sxquan
 * @since 2020-03-11
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = false)
@TableName("spec_group")
@ApiModel(value="SpecGroup对象", description="规格组表")
public class SpecGroup implements Serializable {

    private static final long serialVersionUID = -1059559883111672522L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "spec_group_id", type = IdType.AUTO)
    private Long specGroupId;

    @ApiModelProperty(value = "规格组的名称")
    @TableField("group_name")
    private String groupName;

    @ApiModelProperty(value = "是否标准：0-不是，1-是")
    @TableField("is_standard")
    private Boolean standard;

    @ApiModelProperty(value = "描述")
    @TableField("description")
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


}
