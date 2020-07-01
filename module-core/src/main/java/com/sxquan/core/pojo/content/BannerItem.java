package com.sxquan.core.pojo.content;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * banner_item内容管理表
 *
 * @author sxquan
 * @since 2020-05-28 14:40:36
 */
@Data
@TableName("content_banner_item")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BannerItem implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * banner表外键
     */
    @TableField("banner_id")
    private Long bannerId;

    /**
     * 名称
     */
    @TableField("banner_item_name")
    private String bannerItemName;

    /**
     * 图片
     */
    @TableField("img")
    private String img;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

}
