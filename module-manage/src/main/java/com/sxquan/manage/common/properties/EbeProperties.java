package com.sxquan.manage.common.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * 配置类
 * @Author sxquan
 * @Date 2019/12/20 15:20
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:ebe.properties"})
@ConfigurationProperties(prefix = "ebe")
public class EbeProperties {
    /**
     * 项目启动后是否使用系统默认浏览器打开登录页，默认开启
     */
    private boolean autoOpenBrowser = true;
    /**
     * 项目启动后使用系统默认浏览器打开登录页的环境
     */
    private String[] autoOpenBrowserEnv = {};

    /**
     *  图片上传路径
     */
    private String profile;
    /**
     * 文件服务器
     */
    private String fileServer;

    private ShiroProperties shiro = new ShiroProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

    private SwaggerProperties swagger = new SwaggerProperties();
}
