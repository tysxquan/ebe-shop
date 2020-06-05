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
 * 用户角色关联表
 * </p>
 *
 * @author sxquan
 * @since 2020-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("system_user_role")
@ApiModel(value="SystemUserRole对象", description="用户角色关联表")
public class SystemUserRole implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "系统用户ID")
    @TableField("system_user_id")
    private Long systemUserId;

    @ApiModelProperty(value = "角色ID")
    @TableField("role_id")
    private Long roleId;


}
