package com.sxquan.manage.business.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.sxquan.core.entity.ImageBO;
import com.sxquan.core.entity.LayuiPage;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.entity.ServerResponse;
import com.sxquan.core.exception.ShopException;
import com.sxquan.core.util.ShopUtil;
import com.sxquan.manage.business.pojo.Spu;
import com.sxquan.manage.business.service.ISpuService;
import com.sxquan.manage.common.annotation.ControllerEndpoint;
import com.sxquan.manage.common.enums.SpuImageSrcEnum;
import com.sxquan.manage.common.properties.EbeProperties;
import com.sxquan.manage.common.util.UploadUtil;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

/**
 * <p>
 * SPU信息表 前端控制器
 * </p>
 *
 * @author sxquan
 * @since 2020-03-12
 */
@RestController
@RequestMapping("/business/spu")
public class SpuController {

    @Autowired
    private ISpuService spuService;

    @Autowired
    private EbeProperties dispatching;

    @GetMapping("list")
    @RequiresPermissions("spu:view")
    public ServerResponse spuList(Spu spu, RequestPage requestPage) {
        IPage<Spu> page = spuService.ListSpu(spu,requestPage);
        return ServerResponse.success(new LayuiPage<>(page.getRecords(),page.getTotal()));
    }

    @GetMapping("/form/all")
    public ServerResponse getSpuFormAll(String title) {
        return ServerResponse.success(spuService.findSpuFormAll(title));
    }

    @PostMapping("image/{imgParamName}")
    @RequiresPermissions(value = {"spu:add","spu:update"})
    public ServerResponse imageUpload(MultipartFile imgFile, @PathVariable String imgParamName) {
        //转下划线
        String underscore = ShopUtil.camelToUnderscoreLowerCase(imgParamName);
        SpuImageSrcEnum spuImageSrcEnum = EnumUtils.getEnumIgnoreCase(SpuImageSrcEnum.class, underscore);
        if (ObjectUtils.isEmpty(spuImageSrcEnum)) {
            throw new ShopException("不存在该图片参数！");
        }
        ImageBO imageBO = new ImageBO();
        //上传图片
        if (!imgFile.isEmpty()) {
            String fileName = UploadUtil.uploadImg(imgFile, spuImageSrcEnum.getSrc());
            imageBO.setImageName(fileName);
            imageBO.setUrl(dispatching.getFileServer() + spuImageSrcEnum.getSrc() + fileName);
            return ServerResponse.success(imageBO);
        }
        return ServerResponse.error();
    }

    @DeleteMapping("/image/{imgParamName}")
    @RequiresPermissions(value = {"spu:add","spu:update"})
    public ServerResponse deleteImage( @PathVariable String imgParamName,String imageName) {
        //转下划线
        String underscore = ShopUtil.camelToUnderscoreLowerCase(imgParamName);
        SpuImageSrcEnum spuImageSrcEnum = EnumUtils.getEnumIgnoreCase(SpuImageSrcEnum.class, underscore);

        if (ObjectUtils.isEmpty(spuImageSrcEnum)) {
            throw new ShopException("不存在该图片参数！");
        }
        UploadUtil.delFile(spuImageSrcEnum.getSrc() , imageName);
        spuService.updateRemoveImageValue(imageName,spuImageSrcEnum.getParamName());
        return ServerResponse.success();
    }

    @PostMapping()
    @RequiresPermissions("spu:add")
    @ControllerEndpoint(operation = "新增改SPU",exceptionMessage = "新增SPU失败")
    public ServerResponse addSpu(Spu spu) {
        spuService.addSpu(spu);
        return ServerResponse.success();
    }

    @PutMapping()
    @RequiresPermissions("spu:update")
    @ControllerEndpoint(operation = "修改SPU",exceptionMessage = "修改SPU失败")
    public ServerResponse updateSpu(Spu spu) {
        spuService.updateSpu(spu);
        return ServerResponse.success();
    }

    @DeleteMapping("{spuIds}")
    @RequiresPermissions("spu:delete")
    public ServerResponse deleteSpu(@PathVariable String spuIds) {
        String[] split = spuIds.split(StringPool.COMMA);
        spuService.deleteSpuBySpuIds(Arrays.asList(split));
        return ServerResponse.success();
    }
}

