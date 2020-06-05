package com.sxquan.manage.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.pojo.business.ShopInfo;


/**
 * <p>
 * 商铺信息表 服务类
 * </p>
 *
 * @author sxquan
 * @since 2020-02-24
 */
public interface IShopInfoService extends IService<ShopInfo> {

    /**
     * 分页查询店铺列表
     *
     * @param shopInfo 查询条件
     * @param requestPage 分页参数
     * @return 查询结果
     */
    IPage<ShopInfo> listShop(ShopInfo shopInfo, RequestPage requestPage);

    /**
     *  通过id查询店铺详情
     * @param shopInfoId 主键
     * @return 店铺详情
     */
    ShopInfo findShopInfoByShopInfoId(Long shopInfoId);

    /**
     *
     * 移除图片属性的值
     *
     * @param imageName 图片名
     */
    void updateRemoveImageValue(String imageName);
}
