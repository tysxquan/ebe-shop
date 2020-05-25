package com.sxquan.manage.category.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.manage.category.mapper.CategoryProductMapper;
import com.sxquan.manage.category.pojo.CategoryProduct;
import com.sxquan.manage.category.service.ICategoryProductService;
import com.sxquan.manage.common.properties.EbeProperties;
import com.sxquan.manage.middle.service.ICategoryProductSpuService;
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
public class CategoryProductServiceImpl extends ServiceImpl<CategoryProductMapper, CategoryProduct> implements ICategoryProductService {

    @Autowired
    EbeProperties dispatching;

    @Autowired
    ICategoryProductSpuService categoryProductSpuService;

    @Override
    public IPage<CategoryProduct> listProduct(CategoryProduct product, RequestPage requestPage) {
        Page<CategoryProduct> page = new Page<>(requestPage.getPageNum(),requestPage.getPageSize());
        IPage<CategoryProduct> selectPage = baseMapper.selectPage(page, new LambdaQueryWrapper<CategoryProduct>()
                .like(StringUtils.isNotBlank(product.getCategoryName()), CategoryProduct::getCategoryName, product.getCategoryName())
                .eq(ObjectUtils.isNotEmpty(product.getParentId()),CategoryProduct::getParentId,product.getParentId()));
        selectPage.getRecords().forEach( x -> {
            if (StringUtils.isNotBlank(x.getCategoryImg())) {
                x.setCategoryImg(dispatching.getFileServer() + CategoryProduct.CATEGORY_IMG_SUB_PATH + x.getCategoryImg());
            }
        });
        return selectPage;
    }

    @Override
    public List<CategoryProduct> findProductCallTree(Long parentId) {
        List<CategoryProduct> selectList = baseMapper.selectList(new LambdaQueryWrapper<CategoryProduct>()
                .eq(CategoryProduct::getParentId, parentId));
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
        LambdaQueryWrapper<CategoryProduct> eq = new LambdaQueryWrapper<CategoryProduct>()
                .eq(CategoryProduct::getCategoryImg, imageName);
        CategoryProduct categoryProduct = new CategoryProduct();
        categoryProduct.setCategoryImg("");
        baseMapper.update(categoryProduct,eq);
    }

    @Override
    public CategoryProduct findCategoryProductById(Long categoryId) {
        CategoryProduct categoryProduct = baseMapper.selectById(categoryId);
        if(StringUtils.isNotBlank(categoryProduct.getCategoryImg())) {
            categoryProduct.setCategoryImg(dispatching.getFileServer() + CategoryProduct.CATEGORY_IMG_SUB_PATH + categoryProduct.getCategoryImg());
        }
        return categoryProduct;

    }

    @Override
    public List<CategoryProduct> findProductTree() {
        return baseMapper.selectList(new LambdaQueryWrapper<CategoryProduct>()
                    .eq(CategoryProduct::getStatus,CategoryProduct.STATUS_USING));
    }

    @Override
    public void updateCategoryProductById(CategoryProduct categoryProduct) {
        this.updateById(categoryProduct);

    }

    @Override
    public void addCategoryProductAdd(CategoryProduct categoryProduct) {

        this.save(categoryProduct);
    }

}
