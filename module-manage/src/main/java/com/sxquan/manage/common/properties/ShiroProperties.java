package com.sxquan.manage.common.properties;

import lombok.Data;

/**
 * @Title ShiroProperties
 * @Description Shiro配置类
 * @Author sxquan
 * @Date 2019/12/20 15:21
 */
@Data
public class ShiroProperties {
    /**
     * session 超时时间，单位为秒
     */
    private long sessionTimeout;
    /**
     * rememberMe cookie有效时长，单位为秒
     */
    private int cookieTimeout;
    /**
     * 免认证的路径配置，如静态资源等
     */
    private String anonUrl;
    /**
     * 登录 url
     */
    private String loginUrl;
    /**
     * 首页 url
     */
    private String successUrl;
    /**
     * 登出 url
     */
    private String logoutUrl;
    /**
     * 未授权跳转 url
     */
    private String unauthorizedUrl;
}
