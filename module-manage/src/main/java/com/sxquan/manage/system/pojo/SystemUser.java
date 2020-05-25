package com.sxquan.manage.system.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author sxquan
 * @since 2020-02-03
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = false)
@TableName("system_user")
@ApiModel(value="SystemUser对象", description="系统用户表")
public class SystemUser implements Serializable {

    private static final long serialVersionUID = -156894579163195544L;

    /**
     * 用户状态：有效
     */
    public static final Integer STATUS_VALID = 1;
    /**
     * 用户状态：锁定
     */
    public static final Integer STATUS_LOCK = 0;
    /**
     * 性别男
     */
    public static final Integer SEX_MALE = 1;
    /**
     * 性别女
     */
    public static final Integer SEX_FEMALE = 2;
    /**
     * 性别保密
     */
    public static final Integer SEX_SECRECY = 0;
    /**
     * 默认密码
     */
    public static final String DEFAULT_PASSWORD = "qweasd";
    /**
     * 默认头像
     */
    public static final String DEFAULT_AVATAR = "default.jpg";


    @ApiModelProperty(value = "主键")
    @TableId(value = "system_user_id", type = IdType.AUTO)
    private Long systemUserId;

    @NotBlank( message = "{required}")
    @Size(min = 4, max = 10, message = "{range}")
    @ApiModelProperty(value = "登录账号")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "登录密码")
    @JsonIgnore
    @Size(min = 6, max = 18, message = "{range}")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "真实姓名")
    @TableField("true_name")
    private String trueName;

    @NotNull( message = "{required}")
    @ApiModelProperty(value = "性别;0：未知，1:男，2：女")
    @TableField("sex")
    private Integer sex;

    @ApiModelProperty(value = "联系电话")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty(value = "头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty(value = "描述")
    @TableField("description")
    private String description;

    @NotNull( message = "{required}")
    @ApiModelProperty(value = "类别状态1-正常,0-锁定")
    @TableField("status")
    private Integer status;


    @ApiModelProperty(value = "上次登录时间")
    @TableField("last_login_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private String createTimeRange;

    @TableField(exist = false)
    private String createTimeStart;

    @TableField(exist = false)
    private String createTimeEnd;

    @TableField(exist = false)
    private String roleName;

    @TableField(exist = false)
    private String roleIds;

    @TableField(exist = false)
    private String sexValue;
}
