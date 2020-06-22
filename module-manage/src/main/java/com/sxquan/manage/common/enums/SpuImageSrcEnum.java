package com.sxquan.manage.common.enums;

/**
 * SPU的图片地址枚举
 * @author sxquan
 * @since 2020/3/12 16:53
 */
public enum SpuImageSrcEnum {
    /**
     * cover主图
     */
    COVER("cover","/images/spu/cover/"),
    /**
     * 轮播图
     */
    BANNER_IMAGES("bannerImages","/images/spu/bannerImages/"),
    /**
     * 详情图
     */
    DETAIL_IMG("detailImg","/images/spu/detailImg/");


    /**
     * 属性名
     */
    private final String paramName;
    /**
     * 存储地址
     */
    private final String src;

    SpuImageSrcEnum(String paramName, String src) {
        this.paramName = paramName;
        this.src = src;
    }

    public String getParamName() {
        return paramName;
    }

    public String getSrc() {
        return src;
    }
}
