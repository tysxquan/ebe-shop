package com.sxquan.manage.system.pojo.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sxquan.core.entity.TreeBO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description 菜单树业务对象
 * @Author sxquan
 * @Date 2020/2/22 16:26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuTreeBO extends TreeBO {

    private static final long serialVersionUID = -2539676675320355745L;

    @ApiModelProperty(value = "菜单/按钮名称")
    @TableField("menu_name")
    private String menuName;


    @ApiModelProperty(value = "图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "菜单url")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "权限标识")
    @TableField("perms")
    private String perms;

    @ApiModelProperty(value = "排序编号,同类展示顺序,数值相等则自然排序")
    @TableField("sort_order")
    private Integer sortOrder;


}
