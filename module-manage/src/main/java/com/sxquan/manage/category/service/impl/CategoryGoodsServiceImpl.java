package com.sxquan.manage.category.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.pojo.category.CategoryGoods;
import com.sxquan.manage.category.mapper.CategoryGoodsMapper;
import com.sxquan.manage.category.service.ICategoryGoodsService;
import com.sxquan.manage.common.properties.EbeProperties;
import com.sxquan.manage.middle.service.ICategoryGoodsSpuService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品分类表 服务实现类
 * </p>
 *
 * @author sxquan
 * @since 2020-03-08
 */
@Service
public class CategoryGoodsServiceImpl extends ServiceImpl<CategoryGoodsMapper, CategoryGoods> implements ICategoryGoodsService {

    @Autowired
    EbeProperties dispatching;

    @Autowired
    ICategoryGoodsSpuService categoryProductSpuService;

    @Override
    public IPage<CategoryGoods> listProduct(CategoryGoods product, RequestPage requestPage) {
        Page<CategoryGoods> page = new Page<>(requestPage.getPageNum(),requestPage.getPageSize());
        IPage<CategoryGoods> selectPage = baseMapper.selectPage(page, new LambdaQueryWrapper<CategoryGoods>()
                .like(StringUtils.isNotBlank(product.getCategoryName()), CategoryGoods::getCategoryName, product.getCategoryName())
                .eq(ObjectUtils.isNotEmpty(product.getParentId()), CategoryGoods::getParentId,product.getParentId()));
        selectPage.getRecords().forEach( x -> {
            if (StringUtils.isNotBlank(x.getCategoryImg())) {
                x.setCategoryImg(dispatching.getFileServer() + CategoryGoods.CATEGORY_IMG_SUB_PATH + x.getCategoryImg());
            }
        });
        return selectPage;
    }

    @Override
    public List<CategoryGoods> findProductCallTree(Long parentId) {
        List<CategoryGoods> selectList = baseMapper.selectList(new LambdaQueryWrapper<CategoryGoods>()
                .eq(CategoryGoods::getParentId, parentId));
        List<Long> parentIds = baseMapper.findParentIdsByParentId(parentId);
        selectList.forEach(x -> {
            boolean isExits = parentIds.stream().anyMatch(p -> p.longValue() == x.getCategoryId().longValue());
            if (isExits) {
                x.setHaveChild(true);
            }
        });
        return selectList;
    }

    @Override
    public void updateRemoveImageValue(String imageName) {
        LambdaQueryWrapper<CategoryGoods> eq = new LambdaQueryWrapper<CategoryGoods>()
                .eq(CategoryGoods::getCategoryImg, imageName);
        CategoryGoods categoryGoods = new CategoryGoods();
        categoryGoods.setCategoryImg("");
        baseMapper.update(categoryGoods,eq);
    }

    @Override
    public CategoryGoods findCategoryProductById(Long categoryId) {
        CategoryGoods categoryGoods = baseMapper.selectById(categoryId);
        if(StringUtils.isNotBlank(categoryGoods.getCategoryImg())) {
            categoryGoods.setCategoryImg(dispatching.getFileServer() + CategoryGoods.CATEGORY_IMG_SUB_PATH + categoryGoods.getCategoryImg());
        }
        return categoryGoods;

    }

    @Override
    public List<CategoryGoods> findProductTree() {
        return baseMapper.selectList(new LambdaQueryWrapper<CategoryGoods>()
                    .eq(CategoryGoods::getStatus, CategoryGoods.STATUS_USING));
    }

    @Override
    public void updateCategoryProductById(CategoryGoods categoryGoods) {
        this.updateById(categoryGoods);

    }

    @Override
    public void addCategoryProductAdd(CategoryGoods categoryGoods) {

        this.save(categoryGoods);
    }

}
