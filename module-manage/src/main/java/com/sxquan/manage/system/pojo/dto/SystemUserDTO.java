package com.sxquan.manage.system.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Description 请求传输对象
 * @Author sxquan
 * @Date 2020/2/17 17:46
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = false)
@TableName("system_user")
@ApiModel(value="SystemUserDTO传输对象", description="系统用户表")
public class SystemUserDTO {

    @NotBlank( message = "{required}")
    @ApiModelProperty(value = "主键")
    @TableId(value = "system_user_id", type = IdType.AUTO)
    private Long systemUserId;

    @NotBlank( message = "{required}")
    @ApiModelProperty(value = "登录账号")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "登录密码")
    @JsonIgnore
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "真实姓名")
    @TableField("true_name")
    private String trueName;

    @NotBlank( message = "{required}")
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

    @NotBlank( message = "{required}")
    @ApiModelProperty(value = "类别状态1-正常,0-锁定")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "上次登录时间")
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;


    @TableField(exist = false)
    private String createTimeRange;

    @TableField(exist = false)
    private String createTimeStart;

    @TableField(exist = false)
    private String createTimeEnd;

    @TableField(exist = false)
    private String roleName;

    @TableField(exist = false)
    private String roleId;

    @TableField(exist = false)
    private String sexValue;

}
