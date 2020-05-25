package com.sxquan.manage.category.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.manage.category.pojo.CategoryProduct;

import java.util.List;

/**
 * <p>
 * 商品分类表 服务类
 * </p>
 *
 * @author sxquan
 * @since 2020-03-08
 */
public interface ICategoryProductService extends IService<CategoryProduct> {

    /**
     * 分页查询商品分类
     *
     * @param product 筛选条件
     * @param requestPage 分页条件
     * @return
     */
    IPage<CategoryProduct> listProduct(CategoryProduct product, RequestPage requestPage);

    /**
     * 查询商品分类懒加载
     * @param parentId 父id
     * @return
     */
    List<CategoryProduct> findProductCallTree(Long parentId);

    /**
     * 移除图片属性的值
     * @param imageName 图片名
     */
    void updateRemoveImageValue(String imageName);

    /**
     *  通过主键查询详情
     * @param categoryId 主键
     * @return CategoryProduct
     */
    CategoryProduct findCategoryProductById(Long categoryId);

    /**
     *  查询所有分类节点树
     * @return
     */
    List<CategoryProduct> findProductTree();

    /**
     * 通过主键修改内容
     * @param categoryProduct 修改信息
     */
    void updateCategoryProductById(CategoryProduct categoryProduct);

    /**
     * 添加分类/子分类
     * @param categoryProduct 添加信息
     */
    void addCategoryProductAdd(CategoryProduct categoryProduct);
}
