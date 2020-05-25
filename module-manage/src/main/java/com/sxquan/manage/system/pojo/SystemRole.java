package com.sxquan.manage.system.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统角色表
 * </p>
 *
 * @author sxquan
 * @since 2019-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("system_role")
@ApiModel(value="SystemRole对象", description="系统角色表")
public class SystemRole implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 不可以删除的数据范围（不含此数）
     */
    public static final Long NOT_DELETE_RANGE = 5L;

    @ApiModelProperty(value = "权限角色ID")
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    @NotBlank(message = "{required}")
    @ApiModelProperty(value = "角色名称")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty(value = "角色描述")
    @TableField("role_desc")
    private String roleDesc;

    @NotNull(message = "{required}")
    @ApiModelProperty(value = "1：有效 0：无效")
    @TableField("status")
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 角色对应的菜单（按钮） id
     */
    @TableField(exist = false)
    private transient String menuIds;

}
