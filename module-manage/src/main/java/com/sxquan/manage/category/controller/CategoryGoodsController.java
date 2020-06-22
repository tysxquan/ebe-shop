package com.sxquan.manage.category.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.sxquan.core.entity.ImageBO;
import com.sxquan.core.entity.LayuiPage;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.entity.ServerResponse;
import com.sxquan.core.pojo.category.CategoryGoods;
import com.sxquan.manage.category.service.ICategoryGoodsService;
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
@RequestMapping("/category/goods")
public class CategoryGoodsController {

    @Autowired
    ICategoryGoodsService categoryProductService;

    @Autowired
    EbeProperties dispatching;

    @GetMapping("list")
    @RequiresPermissions("goodsCategory:view")
    public ServerResponse productList(CategoryGoods product, RequestPage requestPage) {
        product.setParentId(CategoryGoods.PARENT_ROOT);
        IPage<CategoryGoods> listProduct = categoryProductService.listProduct(product, requestPage);
        return ServerResponse.success(new LayuiPage<>(listProduct.getRecords(), listProduct.getTotal()));
    }

    @GetMapping("childrenList")
    @RequiresPermissions("goodsCategory:view")
    public ServerResponse productChildrenList(CategoryGoods product, RequestPage requestPage) {
        IPage<CategoryGoods> listProduct = categoryProductService.listProduct(product, requestPage);
        return ServerResponse.success(new LayuiPage<>(listProduct.getRecords(), listProduct.getTotal()));
    }

    @GetMapping("/tree")
    public ServerResponse productCallTree(Long parentId) {
        return ServerResponse.success(categoryProductService.findProductTree());
    }

    @DeleteMapping("/image")
    @RequiresPermissions({"goodsCategory:add","goodsCategory:update"})
    public ServerResponse deleteImage(String imageName) {
        UploadUtil.delFile(CategoryGoods.CATEGORY_IMG_SUB_PATH, imageName);
        categoryProductService.updateRemoveImageValue(imageName);
        return ServerResponse.success();
    }

    @PostMapping("/image")
    @RequiresPermissions({"goodsCategory:add","goodsCategory:update"})
    public ServerResponse imageUpload(MultipartFile imgFile) {
        ImageBO imageBO = new ImageBO();
        //上传图片
        if (!imgFile.isEmpty()) {
            String fileName = UploadUtil.uploadImg(imgFile, CategoryGoods.CATEGORY_IMG_SUB_PATH);
            imageBO.setImageName(fileName);
            imageBO.setUrl(dispatching.getFileServer() + CategoryGoods.CATEGORY_IMG_SUB_PATH + fileName);
            return ServerResponse.success(imageBO);
        }
        return ServerResponse.error();
    }

    @PostMapping()
    @RequiresPermissions("goodsCategory:add")
    @ControllerEndpoint(operation = "添加商品分类/子分类", exceptionMessage = "添加商品分类失败/子分类失败")
    public ServerResponse categoryProductAdd(CategoryGoods categoryGoods) {
        categoryProductService.save(categoryGoods);
        return ServerResponse.success();
    }

    @PutMapping()
    @RequiresPermissions("goodsCategory:update")
    @ControllerEndpoint(operation = "修改商品分类/子分类", exceptionMessage = "删修改商品分类失败/子分类失败")
    public ServerResponse categoryProductUpdate(CategoryGoods categoryGoods) {
        categoryProductService.updateById(categoryGoods);
        return ServerResponse.success();
    }

    @DeleteMapping("/{categoryIds}")
    @RequiresPermissions("goodsCategory:delete")
    @ControllerEndpoint(operation = "删除商品分类/子分类", exceptionMessage = "删除商品分类失败/子分类失败")
    public ServerResponse categoryProductDelete(@PathVariable String categoryIds) {
        String[] split = StringUtils.split(categoryIds, StringPool.COMMA);
        List<String> ids = Arrays.asList(split);
        categoryProductService.removeByIds(ids);
        return ServerResponse.success();
    }

}

