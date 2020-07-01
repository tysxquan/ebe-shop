package com.sxquan.manage.content.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.pojo.content.BannerItem;

import java.util.List;

/**
 * banner_item内容管理表 Service接口
 *
 * @author sxquan
 * @since 2020-05-28 14:40:36
 */
public interface IBannerItemService extends IService<BannerItem> {
    /**
     * 查询列表（分页）
     *
     * @param bannerItem bannerItem
     * @param request 分页条件
     * @param bannerId
     * @return IPage<BannerItem>
     */
    IPage<BannerItem> findBannerItemList(Long bannerId,BannerItem bannerItem, RequestPage request);

    /**
     * 查询（所有）
     *
     * @param bannerItem bannerItem
     * @return List<BannerItem>
     */
    List<BannerItem> findBannerItemAll(BannerItem bannerItem);

    /**
     * 根据主键查询详情
     * @param id 主键
     * @return
     */
    BannerItem findBannerItemById(Long id);

    /**
     * 新增
     *
     * @param bannerItem bannerItem
     */
    void addBannerItem(BannerItem bannerItem);

    /**
     * 修改
     *
     * @param bannerItem bannerItem
     */
    void updateBannerItem(BannerItem bannerItem);

    /**
     * 通过主键删除
     *
     * @param idList 主键集合
     */
    void deleteBannerItem(List<String> idList);



    /**
     * 除图片属性
     * @param imageName
     */
    void updateRemoveImageValue(String imageName);
}
