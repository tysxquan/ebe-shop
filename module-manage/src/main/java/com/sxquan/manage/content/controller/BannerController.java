package com.sxquan.manage.content.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.sxquan.core.entity.ImageBO;
import com.sxquan.core.entity.LayuiPage;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.entity.ServerResponse;
import com.sxquan.core.enums.ImagePathEnum;
import com.sxquan.core.pojo.content.Banner;
import com.sxquan.manage.common.annotation.ControllerEndpoint;
import com.sxquan.manage.common.properties.EbeProperties;
import com.sxquan.manage.common.util.UploadUtil;
import com.sxquan.manage.content.service.IBannerService;
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
 * banner内容管理表 前端控制器
 *
 * @author sxquan
 * @since 2020-05-28 14:40:02
 */
@Slf4j
@Validated
@RestController
@RequestMapping("content/banner")
public class BannerController {

    @Autowired
    private IBannerService bannerService;

    @Autowired
    EbeProperties properties;


    @GetMapping("all")
    @RequiresPermissions("banner:view")
    public ServerResponse getBannerAll(Banner banner) {
        return ServerResponse.success(bannerService.findBannerAll(banner));
    }


    @GetMapping("list")
    @RequiresPermissions("banner:view")
    public ServerResponse bannerList(Banner banner, RequestPage request) {
        IPage<Banner> page = this.bannerService.findBannerList(banner, request);
        return ServerResponse.success(new LayuiPage<>(page.getRecords(),page.getTotal()));
    }

    @PostMapping()
    @RequiresPermissions("banner:add")
    @ControllerEndpoint(operation = "新增Banner", exceptionMessage = "新增Banner失败")
    public ServerResponse addBanner(@Valid Banner banner) {
        this.bannerService.addBanner(banner);
        return ServerResponse.success();
    }

    @DeleteMapping("{ids}")
    @RequiresPermissions("banner:delete")
    @ControllerEndpoint(operation = "删除Banner", exceptionMessage = "删除Banner失败")
    public ServerResponse deleteBanner(@PathVariable String ids) {
        String[] split = StringUtils.split(ids, StringPool.COMMA);
        this.bannerService.deleteBanner(Arrays.asList(split));
        return ServerResponse.success();
    }

    @PutMapping()
    @RequiresPermissions("banner:update")
    @ControllerEndpoint(operation = "修改Banner", exceptionMessage = "修改Banner失败")
    public ServerResponse updateBanner(Banner banner) {
        this.bannerService.updateBanner(banner);
        return ServerResponse.success();
    }

    @PostMapping("/image/upload")
    @RequiresPermissions(value = {"banner:add","banner:update"})
    public ServerResponse imageUpload(MultipartFile imgFile) {
        ImageBO imageBO = new ImageBO();
        //上传图片
        if (!imgFile.isEmpty()) {
            String fileName = UploadUtil.uploadImg(imgFile, ImagePathEnum.BANNER_IMG.getPath());
            imageBO.setImageName(fileName);
            imageBO.setUrl(properties.getFileServer() + ImagePathEnum.BANNER_IMG.getPath() + fileName);
            return ServerResponse.success(imageBO);
        }
        return ServerResponse.error();
    }

    @DeleteMapping("/image")
    @RequiresPermissions(value = {"banner:add","banner:update"})
    public ServerResponse deleteImage(String imageName) {
        bannerService.updateRemoveImageValue(imageName);
        return ServerResponse.success();
    }

}
