package com.sxquan.manage.middle.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sxquan.core.pojo.middle.CategoryGoodsSpu;


/**
 * <p>
 * SPU与商品分类中间表 服务类
 * </p>
 *
 * @author sxquan
 * @since 2020-03-14
 */
public interface ICategoryGoodsSpuService extends IService<CategoryGoodsSpu> {

    /**
     * 通过spuId更新内容
     * @param categoryGoodsSpu 数据对象
     */
    void updateCategoryGoodsBySpuId(CategoryGoodsSpu categoryGoodsSpu);

    /**
     * 通过CategoryId更新内容
     * @param categoryGoodsSpu 数据对象
     */
    void updateCategoryGoodsByCategoryId(CategoryGoodsSpu categoryGoodsSpu);

    /**
     * 通过spuId查询内容
     * @param spuId 主键
     * @return
     */
    CategoryGoodsSpu findCategoryGoodsSpuBySpuId(Long spuId);
}
