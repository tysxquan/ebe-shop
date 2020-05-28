package com.sxquan.manage.monitor.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 管理员操作日志
 * </p>
 *
 * @author sxquan
 * @since 2020-02-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("operation_log")
@ApiModel(value="OperationLog对象", description="管理员操作日志")
public class OperationLog implements Serializable {

    private static final long serialVersionUID = 6081549752432533525L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "log_id", type = IdType.AUTO)
    private Long logId;

    @ApiModelProperty(value = "管理员ID")
    @TableField("system_user_id")
    private Long systemUserId;

    @ApiModelProperty(value = "管理者名字")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "操作")
    @TableField("operation")
    private String operation;

    @ApiModelProperty(value = "操作方法")
    @TableField("method")
    private String method;

    @ApiModelProperty(value = "方法参数")
    @TableField("param")
    private String param;

    @ApiModelProperty(value = "耗时")
    @TableField("run_time")
    private Long runTime;

    @ApiModelProperty(value = "IP")
    @TableField("ip")
    private String ip;

    @ApiModelProperty(value = "操作地点")
    @TableField("location")
    private String location;

    @ApiModelProperty(value = "操作结果:0-失败，1-成功")
    @TableField("result")
    private Boolean result;

    @ApiModelProperty(value = "创建时间")
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField(exist = false)
    private String createTimeRange;

    @TableField(exist = false)
    private String createTimeStart;

    @TableField(exist = false)
    private String createTimeEnd;

}
