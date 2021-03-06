package com.sxquan.manage.common.config;

import com.sxquan.manage.common.properties.EbeProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description 配置虚拟访问路径
 * @author sxquan
 * @since 2020/2/28 20:38
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    EbeProperties properties;

    /**
     * 支持put和delete请求
     */
    // @Bean
    // public FormContentFilter formContentFilter() {
    //     return new FormContentFilter();
    // }



    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:index");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("file:///" + properties.getProfile());
        registry.addResourceHandler("/doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");;
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }



}
