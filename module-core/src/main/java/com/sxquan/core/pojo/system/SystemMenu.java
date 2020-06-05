package com.sxquan.core.pojo.system;

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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统菜单表
 * </p>
 *
 * @author sxquan
 * @since 2019-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("system_menu")
@ApiModel(value="SystemMenu对象", description="系统菜单表")
public class SystemMenu implements Serializable {

    private static final long serialVersionUID=1L;
    /**
     * 类型：目录
     */
    public static final Integer TYPE_CATALOG = 0;
    /**
     * 类型：菜单
     */
    public static final Integer TYPE_MENU = 1;
    /**
     * 类型：按钮
     */
    public static final Integer TYPE_BTN = 2;
    /**
     * 菜单Id：根节点 0
     */
    public static final Long PARENT_ROOT = 0L;

    @ApiModelProperty(value = "菜单/按钮ID")
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    @NotBlank(message = "{required}")
    @ApiModelProperty(value = "菜单/按钮名称")
    @TableField("menu_name")
    private String menuName;

    @ApiModelProperty(value = "上级菜单id,父类别id当id=0时说明是根节点,一级类别")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "菜单url")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "权限标识")
    @TableField("perms")
    private String perms;

    @NotNull(message = "{required}")
    @ApiModelProperty(value = "类型 0-目录,1-菜单,2-按钮")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "排序编号,同类展示顺序,数值相等则自然排序")
    @TableField("sort_order")
    private Integer sortOrder;

    @ApiModelProperty(value = "描述")
    @TableField("descpt")
    private String descpt;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "是否有子项")
    private Boolean haveChild;

}
