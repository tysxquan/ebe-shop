package com.sxquan.manage.content.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.pojo.content.Banner;

import java.util.List;

/**
 * banner内容管理表 Service接口
 *
 * @author sxquan
 * @since 2020-05-28 14:40:02
 */
public interface IBannerService extends IService<Banner> {
    /**
     * 查询列表（分页）
     *
     * @param banner banner
     * @param request 分页条件
     * @return IPage<Banner>
     */
    IPage<Banner> findBannerList(Banner banner, RequestPage request);

    /**
     * 查询（所有）
     *
     * @param banner banner
     * @return List<Banner>
     */
    List<Banner> findBannerAll(Banner banner);

    /**
     *  通过主键查询banner详情
     * @param id 主键
     * @return
     */
    Banner findBannerById(Long id);

    /**
     * 新增
     *
     * @param banner banner
     */
    void addBanner(Banner banner);

    /**
     * 修改
     *
     * @param banner banner
     */
    void updateBanner(Banner banner);

    /**
     * 通过主键删除
     *
     * @param idList 主键集合
     */
    void deleteBanner(List<String> idList);

    /**
     * 清除图片属性
     * @param imageName 图片名
     */
    void updateRemoveImageValue(String imageName);
}
