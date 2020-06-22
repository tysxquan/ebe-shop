package com.sxquan.manage.category.controller;


import com.sxquan.core.pojo.category.CategoryGoods;
import com.sxquan.manage.category.service.ICategoryGoodsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author sxquan
 * @since 2020/3/8 17:09
 */
@Controller("/category-view")
public class ViewController {

    @Autowired
    ICategoryGoodsService categoryProductService;

    @GetMapping("/category/goods")
    @RequiresPermissions("goodsCategory:view")
    public String categoryProduct(){return "/category/goods/goods";}

    @GetMapping("/category/goods/add")
    @RequiresPermissions("goodsCategory:add")
    public String categoryProductAdd(){return "/category/goods/goodsAdd";}

    @GetMapping("/category/goods/update/{categoryId}")
    @RequiresPermissions("goodsCategory:update")
    public String categoryProductUpdate(@PathVariable Long categoryId, Model model) {
        CategoryGoods categoryGoods = categoryProductService.findCategoryProductById(categoryId);
        model.addAttribute("categoryGoods", categoryGoods);
        return "/category/goods/goodsUpdate";
    }

    @GetMapping("/category/goods/children/{categoryId}")
    @RequiresPermissions("goodsCategory:view")
    public String categoryProductChildren(@PathVariable Long categoryId,Model model){
        model.addAttribute("categoryId",categoryId);
        return "/category/goods/children/childrenCategory";
    }

    @GetMapping("/category/goods/children/add")
    @RequiresPermissions("goodsCategory:add")
    public String categoryProductChildrenAdd(Long parentId,Model model){
        model.addAttribute("parentId",parentId);
        return "/category/goods/children/childrenCategoryAdd";
    }

    @GetMapping("/category/goods/children/update/{categoryId}")
    @RequiresPermissions("goodsCategory:update")
    public String categoryProductChildrenUpdate(@PathVariable Long categoryId,Model model){
        CategoryGoods childrenCategory = categoryProductService.findCategoryProductById(categoryId);
        model.addAttribute("childrenCategory",childrenCategory);
        return "/category/goods/children/childrenCategoryUpdate";
    }
}
