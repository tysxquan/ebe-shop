package com.sxquan.manage.middle.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.core.pojo.middle.CategoryGoodsSpu;

import com.sxquan.manage.middle.mapper.CategoryGoodsSpuMapper;
import com.sxquan.manage.middle.service.ICategoryGoodsSpuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * SPU与商品分类中间表 服务实现类
 * </p>
 *
 * @author sxquan
 * @since 2020-03-14
 */
@Service
public class CategoryGoodsSpuServiceImpl extends ServiceImpl<CategoryGoodsSpuMapper, CategoryGoodsSpu> implements ICategoryGoodsSpuService {


    @Override
    public void updateCategoryGoodsBySpuId(CategoryGoodsSpu categoryProductSpu) {
        baseMapper.update(categoryProductSpu,new LambdaQueryWrapper<CategoryGoodsSpu>()
                .eq(CategoryGoodsSpu::getSpuId,categoryProductSpu.getSpuId()));
    }

    @Override
    public void updateCategoryGoodsByCategoryId(CategoryGoodsSpu categoryProductSpu) {
        //todo
        baseMapper.update(categoryProductSpu,new LambdaQueryWrapper<CategoryGoodsSpu>()
                .eq(CategoryGoodsSpu::getCgId2,categoryProductSpu.getCgId2()));
    }

    @Override
    public CategoryGoodsSpu findCategoryGoodsSpuBySpuId(Long spuId) {
        return baseMapper.selectOne(new LambdaQueryWrapper<CategoryGoodsSpu>()
            .eq(CategoryGoodsSpu::getSpuId,spuId));
    }
}
