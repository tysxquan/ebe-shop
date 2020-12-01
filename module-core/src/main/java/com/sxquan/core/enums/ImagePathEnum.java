package com.sxquan.core.enums;

import com.sxquan.core.exception.ShopException;
import com.sxquan.core.util.EnumUtil;

import java.util.Optional;

/**
 * 图片路径
 * @author sxquan
 * @since 2020/6/27 17:37
 */
public enum ImagePathEnum {

    /**
     * banner图片
     */
    BANNER_IMG("bannerImg", "/images/banner/img/"),
    /**
     * bannerItem图片
     */
    BANNER_ITEM_IMG("bannerItemImg","/images/bannerItem/img/");
    /**
     * 属性名
     */
    private final String imgName;
    /**
     * 存储地址
     */
    private final String path;

    ImagePathEnum(String imgName, String path) {
        this.imgName = imgName;
        this.path = path;
    }

    public String getImgName() {
        return imgName;
    }

    public String getPath() {
        return path;
    }

    /**
     * 根据图片名获取路径
     * @param imgName 图片名
     * @return
     */
    public String getPath(String imgName) {
        Optional<ImagePathEnum> enumObject = EnumUtil.getEnumObject(ImagePathEnum.class, x -> x.imgName.equals(imgName));
        if (enumObject.isPresent()) {
            return enumObject.get().path;
        }
        throw new ShopException("非法状态码");
    }
}
