package com.sxquan.core.pojo.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author sxquan
 * @since 2020-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户昵称
     */
    @TableField("username")
    private String username;

    /**
     * 联系电话
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 登录密码
     */
    @TableField("password")
    private String password;

    /**
     * 微信昵称
     */
    @TableField("nickName")
    private String nickName;

    /**
     * 微信openid
     */
    @TableField("open_id")
    private String openId;

    /**
     * 微信头像
     */
    @TableField("avatar_url")
    private String avatarUrl;

    /**
     * 微信国家
     */
    @TableField("country")
    private String country;

    /**
     * 微信省名
     */
    @TableField("province")
    private String province;

    /**
     * 微信城市
     */
    @TableField("city")
    private String city;

    /**
     * 钱包
     */
    @TableField("wallet")
    private BigDecimal wallet;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 用户真实姓名
     */
    @TableField("true_name")
    private String trueName;

    /**
     * 性别;0：未知，1:男，2：女
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 类别状态1-正常,0-锁定
     */
    @TableField("status")
    private Integer status;

    /**
     * 是否删除；0:否，1：是
     */
    @TableField("is_deleted")
    private Boolean deleted;

    /**
     * 加入时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;


}
