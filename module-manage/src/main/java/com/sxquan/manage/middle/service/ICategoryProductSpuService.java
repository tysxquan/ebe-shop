package com.sxquan.manage.middle.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sxquan.manage.middle.pojo.CategoryProductSpu;

/**
 * <p>
 * SPU与商品分类中间表 服务类
 * </p>
 *
 * @author sxquan
 * @since 2020-03-14
 */
public interface ICategoryProductSpuService extends IService<CategoryProductSpu> {

    /**
     * 通过spuId更新内容
     * @param categoryProductSpu 数据对象
     */
    void updateCategoryProductBySpuId(CategoryProductSpu categoryProductSpu);

    /**
     * 通过CategoryId更新内容
     * @param categoryProductSpu 数据对象
     */
    void updateCategoryProductByCategoryId(CategoryProductSpu categoryProductSpu);

    /**
     * 通过spuId查询内容
     * @param spuId 主键
     * @return
     */
    CategoryProductSpu findCategoryProductSpuBySpuId(Long spuId);
}
