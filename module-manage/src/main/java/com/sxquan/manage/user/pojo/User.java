package com.sxquan.manage.user.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author sxquan
 * @since 2020-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
@ApiModel(value="User对象", description="用户信息表")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户昵称")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "联系电话")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty(value = "登录密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "微信openid")
    @TableField("open_id")
    private String openId;

    @ApiModelProperty(value = "钱包")
    @TableField("wallet")
    private BigDecimal wallet;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "用户真实姓名")
    @TableField("true_name")
    private String trueName;

    @ApiModelProperty(value = "用户真头像")
    @TableField("avatar_url")
    private String avatarUrl;

    @ApiModelProperty(value = "性别;0：未知，1:男，2：女")
    @TableField("sex")
    private Integer sex;

    @ApiModelProperty(value = "类别状态1-正常,0-锁定")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "是否删除；0:否，1：是")
    @TableField("is_deleted")
    private Boolean deleted;

    @ApiModelProperty(value = "加入时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


}
