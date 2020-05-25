package com.sxquan.core.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 图片业务对象
 * @author sxquan
 * @since 2020/3/5 22:05
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageBO {

    @ApiModelProperty(value = "图片访问地址")
    private String url;

    @ApiModelProperty(value = "图片名字")
    private String imageName;
}
