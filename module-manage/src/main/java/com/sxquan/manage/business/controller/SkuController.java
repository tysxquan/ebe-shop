package com.sxquan.manage.business.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.sxquan.core.entity.ImageBO;
import com.sxquan.core.entity.LayuiPage;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.entity.ServerResponse;
import com.sxquan.core.pojo.business.Sku;
import com.sxquan.manage.business.service.ISkuService;
import com.sxquan.manage.common.annotation.ControllerEndpoint;
import com.sxquan.manage.common.properties.EbeProperties;
import com.sxquan.manage.common.util.UploadUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

/**
 * <p>
 * sku表 前端控制器
 * </p>
 *
 * @author sxquan
 * @since 2020-03-12
 */
@RestController
@RequestMapping("/business/sku")
public class SkuController {

    @Autowired
    ISkuService skuService;

    @Autowired
    EbeProperties properties;

    @GetMapping("list")
    @RequiresPermissions("sku:view")
    public ServerResponse skuList(Sku sku, RequestPage requestPage) {
        IPage<Sku> page = skuService.listSku(sku,requestPage);
        return ServerResponse.success(new LayuiPage<>(page.getRecords(),page.getTotal()));
    }

    @PostMapping()
    @RequiresPermissions("sku:add")
    @ControllerEndpoint(operation = "新增SKU",exceptionMessage = "新增SKU失败")
    public ServerResponse addSku( Sku sku) {
        //生成code编码
        sku.setCode(sku.getSpuId() + StringPool.DOLLAR + sku.getCode());
        skuService.addSku(sku);
        return ServerResponse.success();
    }

    @PutMapping()
    @RequiresPermissions("sku:update")
    @ControllerEndpoint(operation = "修改SKU",exceptionMessage = "修改SKU失败")
    public ServerResponse updateSku(Sku sku) {
        //生成code编码
        sku.setCode(sku.getSpuId() + StringPool.DOLLAR + sku.getCode());
        skuService.updateSku(sku);
        return ServerResponse.success();
    }
    @DeleteMapping("{skuIds}")
    @RequiresPermissions("sku:delete")
    @ControllerEndpoint(operation = "删除SKU",exceptionMessage = "删除SKU失败")
    public ServerResponse deleteSku(@PathVariable String skuIds) {
        String[] split = skuIds.split(StringPool.COMMA);
        skuService.deleteSku(Arrays.asList(split));
        return ServerResponse.success();
    }

    @DeleteMapping("/image")
    @RequiresPermissions(value = {"sku:add","sku:update"})
    public ServerResponse deleteImage(String imageName) {
        UploadUtil.delFile(Sku.IMAGE_SUB_PATH , imageName);
        skuService.updateRemoveImageValue(imageName);
        return ServerResponse.success();
    }


    @PostMapping("/image")
    @RequiresPermissions(value = {"sku:add","sku:update"})
    public ServerResponse imageUpload(MultipartFile imgFile) {
        ImageBO imageBO = new ImageBO();
        //上传图片
        if (!imgFile.isEmpty()) {
            String fileName = UploadUtil.uploadImg(imgFile,Sku.IMAGE_SUB_PATH);
            imageBO.setImageName(fileName);
            imageBO.setUrl(properties.getFileServer() +Sku.IMAGE_SUB_PATH + fileName);
            return ServerResponse.success(imageBO);
        }
        return ServerResponse.error();
    }

}

