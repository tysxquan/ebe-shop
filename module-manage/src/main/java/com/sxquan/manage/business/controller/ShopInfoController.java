package com.sxquan.manage.business.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.sxquan.core.constant.SystemConstant;
import com.sxquan.core.entity.ImageBO;
import com.sxquan.core.entity.LayuiPage;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.entity.ServerResponse;
import com.sxquan.core.pojo.business.ShopInfo;
import com.sxquan.manage.business.service.IShopInfoService;
import com.sxquan.manage.common.annotation.ControllerEndpoint;
import com.sxquan.manage.common.properties.EbeProperties;
import com.sxquan.manage.common.util.UploadUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 商铺信息表 前端控制器
 * </p>
 *
 * @author sxquan
 * @since 2020-02-24
 */
@RestController
@RequestMapping("/shopInfo")
public class ShopInfoController {

    @Autowired
    IShopInfoService shopInfoService;

    @Autowired
    EbeProperties dispatching;

    @GetMapping("list")
    @RequiresPermissions("shopInfo:view")
    public ServerResponse shopList(ShopInfo shopInfo, RequestPage requestPage) {
        String createTimeRange = shopInfo.getCreateTimeRange();
        if (StringUtils.isNotBlank(createTimeRange)){
            String[] split = StringUtils.splitByWholeSeparator(createTimeRange, SystemConstant.SEPARATOR_MINUS);
            shopInfo.setCreateTimeStart(split[0]);
            shopInfo.setCreateTimeEnd(split[1]+" 23:59:59");
        }
        IPage<ShopInfo> shopInfoPage = shopInfoService.listShop(shopInfo, requestPage);
        return ServerResponse.success(new LayuiPage<>(shopInfoPage.getRecords(), shopInfoPage.getTotal()));
    }

    @DeleteMapping("/image")
    @RequiresPermissions(value = {"shopInfo:add","shopInfo:update"})
    public ServerResponse deleteImage(String imageName) {
        UploadUtil.delFile(ShopInfo.STORE_IMG_SUB_PATH , imageName);
        shopInfoService.updateRemoveImageValue(imageName);
        return ServerResponse.success();
    }


    @PostMapping("/image")
    @RequiresPermissions(value = {"shopInfo:add","shopInfo:update"})
    public ServerResponse imageUpload(MultipartFile imgFile) {
        ImageBO imageBO = new ImageBO();
        //上传图片
        if (!imgFile.isEmpty()) {
            String fileName = UploadUtil.uploadImg(imgFile,ShopInfo.STORE_IMG_SUB_PATH);
            imageBO.setImageName(fileName);
            imageBO.setUrl(dispatching.getFileServer() + ShopInfo.STORE_IMG_SUB_PATH + fileName);
            return ServerResponse.success(imageBO);
        }
        return ServerResponse.error();
    }


    @PostMapping()
    @RequiresPermissions("shopInfo:add")
    @ControllerEndpoint(operation = "添加店铺", exceptionMessage = "添加店铺失败")
    public ServerResponse addShopInfo(ShopInfo shopInfo) {
        String[] split = StringUtils.split(shopInfo.getBusinessHours(), StringPool.DASH);
        shopInfo.setBeginTime(LocalTime.parse(split[0].trim()));
        shopInfo.setEndTime(LocalTime.parse(split[1].trim()));
        shopInfoService.save(shopInfo);
        return ServerResponse.success();
    }

    @PutMapping()
    @RequiresPermissions("shopInfo:update")
    @ControllerEndpoint(operation = "修改店铺", exceptionMessage = "修改店铺失败")
    public ServerResponse updateShopInfo(ShopInfo shopInfo) {
        if (shopInfo.getBusinessHours() != null) {
            String[] split = StringUtils.split(shopInfo.getBusinessHours(), SystemConstant.SEPARATOR_MINUS);
            shopInfo.setBeginTime(LocalTime.parse(split[0]));
            shopInfo.setEndTime(LocalTime.parse(split[1]));
        }
        shopInfoService.updateById(shopInfo);
        return ServerResponse.success();
    }

    @DeleteMapping("/{shopInfoIds}")
    @RequiresPermissions("shopInfo:delete")
    @ControllerEndpoint(operation = "删除店铺", exceptionMessage = "删除店铺失败")
    public ServerResponse deleteShopInfo(@PathVariable String shopInfoIds) {
        String[] split = StringUtils.split(shopInfoIds, StringPool.COMMA);
        List<String> ids = Arrays.asList(split);
        shopInfoService.removeByIds(ids);
        return ServerResponse.success();
    }
}

