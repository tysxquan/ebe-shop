package com.sxquan.manage.monitor.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 登录日志表 Entity
 *
 * @author sxquan
 * @since 2020-05-25 20:55:35
 */
@Data
@TableName("log_login")
public class LogLogin implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 登录时间
     */
    @TableField("login_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginTime;

    /**
     * 登录地点
     */
    @TableField("location")
    private String location;

    /**
     * IP地址
     */
    @TableField("ip")
    private String ip;

    /**
     * 操作系统
     */
    @TableField("system")
    private String system;

    /**
     * 浏览器
     */
    @TableField("browser")
    private String browser;

    @TableField(exist = false)
    private String loginTimeRange;

    @TableField(exist = false)
    private String loginTimeStart;

    @TableField(exist = false)
    private String loginTimeEnd;

    public void setSystemBrowserInfo(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(header);
        this.system = userAgent.getOperatingSystem().getName();
        this.browser = userAgent.getBrowser().getName();
    }

}
