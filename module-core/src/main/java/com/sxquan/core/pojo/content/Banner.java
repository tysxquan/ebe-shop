package com.sxquan.core.pojo.content;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * banner内容管理表
 *
 * @author sxquan
 * @since 2020-05-28 14:40:02
 */
@Data
@TableName("content_banner")
public class Banner implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 主键
     */
    @TableId(value = "banner_id", type = IdType.AUTO)
    private Long bannerId;

    /**
     * 名称
     */
    @TableField("banner_name")
    private String bannerName;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 图片
     */
    @TableField("img")
    private String img;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

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
