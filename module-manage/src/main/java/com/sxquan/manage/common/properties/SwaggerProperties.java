package com.sxquan.manage.common.properties;

import lombok.Data;

/**
 * @author sxquan
 * @since  2020/5/26 15:20
 */
@Data
public class SwaggerProperties {
    private String basePackage;
    private String title;
    private String description;
    private String version;
    private String author;
    private String url;
    private String email;
    private String license;
    private String licenseUrl;
}