package com.sxquan.core.pojo.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 角色菜单关联表
 * </p>
 *
 * @author sxquan
 * @since 2020-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("system_role_menu")
@ApiModel(value="SystemRoleMenu对象", description="角色菜单关联表")
public class SystemRoleMenu implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "角色ID")
    @TableField("role_id")
    private Long roleId;

    @ApiModelProperty(value = "菜单/按钮ID")
    @TableField("menu_id")
    private Long menuId;


}
