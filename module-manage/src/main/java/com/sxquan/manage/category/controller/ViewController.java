package com.sxquan.manage.category.controller;


import com.sxquan.manage.category.pojo.CategoryProduct;
import com.sxquan.manage.category.service.ICategoryProductService;
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
    ICategoryProductService categoryProductService;

    @GetMapping("/category/product")
    @RequiresPermissions("productCategory:view")
    public String categoryProduct(){return "/category/product/product";}

    @GetMapping("/category/product/add")
    @RequiresPermissions("productCategory:add")
    public String categoryProductAdd(){return "/category/product/productAdd";}

    @GetMapping("/category/product/update/{categoryId}")
    @RequiresPermissions("productCategory:update")
    public String categoryProductUpdate(@PathVariable Long categoryId, Model model) {
        CategoryProduct categoryProduct = categoryProductService.findCategoryProductById(categoryId);
        model.addAttribute("categoryProduct",categoryProduct);
        return "/category/product/productUpdate";
    }

    @GetMapping("/category/product/children/{categoryId}")
    @RequiresPermissions("productCategory:view")
    public String categoryProductChildren(@PathVariable Long categoryId,Model model){
        model.addAttribute("categoryId",categoryId);
        return "/category/product/children/childrenCategory";
    }

    @GetMapping("/category/product/children/add")
    @RequiresPermissions("productCategory:add")
    public String categoryProductChildrenAdd(Long parentId,Model model){
        model.addAttribute("parentId",parentId);
        return "/category/product/children/childrenCategoryAdd";
    }

    @GetMapping("/category/product/children/update/{categoryId}")
    @RequiresPermissions("productCategory:update")
    public String categoryProductChildrenUpdate(@PathVariable Long categoryId,Model model){
        CategoryProduct childrenCategory = categoryProductService.findCategoryProductById(categoryId);
        model.addAttribute("childrenCategory",childrenCategory);
        return "/category/product/children/childrenCategoryUpdate";
    }
}
