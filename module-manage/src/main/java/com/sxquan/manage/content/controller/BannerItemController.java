package com.sxquan.manage.content.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.sxquan.core.entity.ImageBO;
import com.sxquan.core.entity.LayuiPage;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.entity.ServerResponse;
import com.sxquan.core.enums.ImagePathEnum;
import com.sxquan.core.pojo.content.BannerItem;
import com.sxquan.manage.common.annotation.ControllerEndpoint;
import com.sxquan.manage.common.properties.EbeProperties;
import com.sxquan.manage.common.util.UploadUtil;
import com.sxquan.manage.content.service.IBannerItemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * banner_item内容管理表 前端控制器
 *
 * @author sxquan
 * @since 2020-05-28 14:40:36
 */
@Slf4j
@Validated
@RestController()
@RequestMapping("/content")
public class BannerItemController {

    @Autowired
    private IBannerItemService bannerItemService;

    @Autowired
    private EbeProperties properties;



    @GetMapping("/bannerItem/all")
    @RequiresPermissions("banner:update")
    public ServerResponse getBannerItemAll(BannerItem bannerItem) {
        return ServerResponse.success(bannerItemService.findBannerItemAll(bannerItem));
    }

    @GetMapping("banner/{bannerId}/bannerItem/list")
    @RequiresPermissions("banner:update")
    public ServerResponse bannerItemList(@PathVariable Long bannerId,BannerItem bannerItem, @Validated RequestPage request) {
        IPage<BannerItem> page = this.bannerItemService.findBannerItemList(bannerId,bannerItem, request);
        return ServerResponse.success(new LayuiPage<>(page.getRecords(), page.getTotal()));
    }

    @PostMapping("bannerItem")
    @RequiresPermissions("bannerItem:add")
    @ControllerEndpoint(operation = "新增BannerItem", exceptionMessage = "新增BannerItem失败")
    public ServerResponse addBannerItem(@Valid BannerItem bannerItem) {
        this.bannerItemService.addBannerItem(bannerItem);
        return ServerResponse.success();
    }

    @DeleteMapping("bannerItem/{ids}")
    @RequiresPermissions("bannerItem:delete")
    @ControllerEndpoint(operation = "删除BannerItem", exceptionMessage = "删除BannerItem失败")
    public ServerResponse deleteBannerItem(@PathVariable String ids) {
        String[] split = StringUtils.split(ids, StringPool.COMMA);
        this.bannerItemService.deleteBannerItem(Arrays.asList(split));
        return ServerResponse.success();
    }

    @PutMapping("bannerItem")
    @RequiresPermissions("bannerItem:update")
    @ControllerEndpoint(operation = "修改BannerItem", exceptionMessage = "修改BannerItem失败")
    public ServerResponse updateBannerItem(BannerItem bannerItem) {
        this.bannerItemService.updateBannerItem(bannerItem);
        return ServerResponse.success();
    }

    @PostMapping("/bannerItem/image/upload")
    @RequiresPermissions(value = {"bannerItem:add","bannerItem:update"})
    public ServerResponse imageUpload(MultipartFile imgFile) {
        ImageBO imageBO = new ImageBO();
        //上传图片
        if (!imgFile.isEmpty()) {
            String fileName = UploadUtil.uploadImg(imgFile, ImagePathEnum.BANNER_ITEM_IMG.getPath());
            imageBO.setImageName(fileName);
            imageBO.setUrl(properties.getFileServer() + ImagePathEnum.BANNER_ITEM_IMG.getPath() + fileName);
            return ServerResponse.success(imageBO);
        }
        return ServerResponse.error();
    }

    @DeleteMapping("/bannerItem/image")
    @RequiresPermissions(value = {"bannerItem:add","bannerItem:update"})
    public ServerResponse deleteImage(String imageName) {
        bannerItemService.updateRemoveImageValue(imageName);
        return ServerResponse.success();
    }

}
