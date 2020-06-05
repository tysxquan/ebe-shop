package com.sxquan.manage.middle.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.core.pojo.middle.CategoryProductSpu;
import com.sxquan.manage.middle.mapper.CategoryProductSpuMapper;
import com.sxquan.manage.middle.service.ICategoryProductSpuService;
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
public class CategoryProductSpuServiceImpl extends ServiceImpl<CategoryProductSpuMapper, CategoryProductSpu> implements ICategoryProductSpuService {


    @Override
    public void updateCategoryProductBySpuId(CategoryProductSpu categoryProductSpu) {
        baseMapper.update(categoryProductSpu,new LambdaQueryWrapper<CategoryProductSpu>()
                .eq(CategoryProductSpu::getSpuId,categoryProductSpu.getSpuId()));
    }

    @Override
    public void updateCategoryProductByCategoryId(CategoryProductSpu categoryProductSpu) {
        baseMapper.update(categoryProductSpu,new LambdaQueryWrapper<CategoryProductSpu>()
                .eq(CategoryProductSpu::getCategoryId,categoryProductSpu.getCategoryId()));
    }

    @Override
    public CategoryProductSpu findCategoryProductSpuBySpuId(Long spuId) {
        return baseMapper.selectOne(new LambdaQueryWrapper<CategoryProductSpu>()
            .eq(CategoryProductSpu::getSpuId,spuId));
    }
}
