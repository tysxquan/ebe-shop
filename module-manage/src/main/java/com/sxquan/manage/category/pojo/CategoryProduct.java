package com.sxquan.manage.category.pojo;

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
 * 商品分类表
 * </p>
 *
 * @author sxquan
 * @since 2020-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("category_product")
@ApiModel(value="CategoryProduct对象", description="商品分类表")
public class CategoryProduct implements Serializable {

    private static final long serialVersionUID = 1905899577436965777L;

    /**
     * 分类图片子路径
     */
    public static final String CATEGORY_IMG_SUB_PATH = "/images/category/product/";

    /**
     * 父Id：根节点 0
     */
    public static final Long PARENT_ROOT = 0L;
    /**
     * 状态：1-使用
     */
    public static final Integer STATUS_USING = 1;

    @ApiModelProperty(value = "主键")
    @TableId(value = "category_id", type = IdType.AUTO)
    private Long categoryId;

    @ApiModelProperty(value = "上级分类id,父类别id=0时说明是根节点,一级类别")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "分类名")
    @TableField("category_name")
    private String categoryName;

    @ApiModelProperty(value = "分类图片")
    @TableField("category_img")
    private String categoryImg;

    @ApiModelProperty(value = "排序编号,同类展示顺序,数值相等则自然排序")
    @TableField("sort_order")
    private Integer sortOrder;

    @ApiModelProperty(value = "描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "状态；0：禁用，1：启用")
    @TableField("status")
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


    @ApiModelProperty(value = "是否有子项")
    @TableField(exist = false)
    private Boolean haveChild;
}
