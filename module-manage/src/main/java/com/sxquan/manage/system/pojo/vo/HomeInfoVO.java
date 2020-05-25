package com.sxquan.manage.system.pojo.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description 主页信息
 * @Author sxquan
 * @Date 2020/1/5 14:27
 */
public class HomeInfoVO {

    @ApiModelProperty(value = "主页名称")
    private String title;

    @ApiModelProperty(value = "主页图标")
    private String icon;

    @ApiModelProperty(value = "主页地址")
    private String href;
}
