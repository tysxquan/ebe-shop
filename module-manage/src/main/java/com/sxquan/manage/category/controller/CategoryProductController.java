package com.sxquan.manage.category.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.sxquan.core.entity.ImageBO;
import com.sxquan.core.entity.LayuiPage;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.entity.ServerResponse;
import com.sxquan.manage.category.pojo.CategoryProduct;
import com.sxquan.manage.category.service.ICategoryProductService;
import com.sxquan.manage.common.annotation.ControllerEndpoint;
import com.sxquan.manage.common.properties.EbeProperties;
import com.sxquan.manage.common.util.UploadUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 商品分类表 前端控制器
 * </p>
 *
 * @author sxquan
 * @since 2020-03-08
 */
@RestController
@RequestMapping("/category/product")
public class CategoryProductController {

    @Autowired
    ICategoryProductService categoryProductService;

    @Autowired
    EbeProperties dispatching;

    @GetMapping("list")
    @RequiresPermissions("productCategory:view")
    public ServerResponse productList(CategoryProduct product, RequestPage requestPage) {
        product.setParentId(CategoryProduct.PARENT_ROOT);
        IPage<CategoryProduct> listProduct = categoryProductService.listProduct(product, requestPage);
        return ServerResponse.success(new LayuiPage<>(listProduct.getRecords(), listProduct.getTotal()));
    }

    @GetMapping("childrenList")
    @RequiresPermissions("productCategory:view")
    public ServerResponse productChildrenList(CategoryProduct product, RequestPage requestPage) {
        IPage<CategoryProduct> listProduct = categoryProductService.listProduct(product, requestPage);
        return ServerResponse.success(new LayuiPage<>(listProduct.getRecords(), listProduct.getTotal()));
    }

    @GetMapping("/tree")
    public ServerResponse productCallTree(Long parentId) {
        return ServerResponse.success(categoryProductService.findProductTree());
    }

    @DeleteMapping("/image")
    @RequiresPermissions({"productCategory:add","productCategory:update"})
    public ServerResponse deleteImage(String imageName) {
        UploadUtil.delFile(CategoryProduct.CATEGORY_IMG_SUB_PATH, imageName);
        categoryProductService.updateRemoveImageValue(imageName);
        return ServerResponse.success();
    }

    @PostMapping("/image")
    @RequiresPermissions({"productCategory:add","productCategory:update"})
    public ServerResponse imageUpload(MultipartFile imgFile) {
        ImageBO imageBO = new ImageBO();
        //上传图片
        if (!imgFile.isEmpty()) {
            String fileName = UploadUtil.uploadImg(imgFile, CategoryProduct.CATEGORY_IMG_SUB_PATH);
            imageBO.setImageName(fileName);
            imageBO.setUrl(dispatching.getFileServer() + CategoryProduct.CATEGORY_IMG_SUB_PATH + fileName);
            return ServerResponse.success(imageBO);
        }
        return ServerResponse.error();
    }

    @PostMapping()
    @RequiresPermissions("productCategory:add")
    @ControllerEndpoint(operation = "添加商品分类/子分类", exceptionMessage = "添加商品分类失败/子分类失败")
    public ServerResponse categoryProductAdd(CategoryProduct categoryProduct) {
        categoryProductService.save(categoryProduct);
        return ServerResponse.success();
    }

    @PutMapping()
    @RequiresPermissions("productCategory:update")
    @ControllerEndpoint(operation = "修改商品分类/子分类", exceptionMessage = "删修改商品分类失败/子分类失败")
    public ServerResponse categoryProductUpdate(CategoryProduct categoryProduct) {
        categoryProductService.updateById(categoryProduct);
        return ServerResponse.success();
    }

    @DeleteMapping("/{categoryIds}")
    @RequiresPermissions("productCategory:delete")
    @ControllerEndpoint(operation = "删除商品分类/子分类", exceptionMessage = "删除商品分类失败/子分类失败")
    public ServerResponse categoryProductDelete(@PathVariable String categoryIds) {
        String[] split = StringUtils.split(categoryIds, StringPool.COMMA);
        List<String> ids = Arrays.asList(split);
        categoryProductService.removeByIds(ids);
        return ServerResponse.success();
    }

}

