package com.sxquan.manage.business.controller;


import com.sxquan.core.entity.ServerResponse;
import com.sxquan.manage.business.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 中国行政地址信息表 前端控制器
 * </p>
 *
 * @author sxquan
 * @since 2020-03-06
 */
@RestController
@RequestMapping("/region")
public class RegionController {

    @Autowired
    IRegionService regionService;

    @GetMapping("/{parentCode}")
    public ServerResponse getRegion(@PathVariable String parentCode) {
        return ServerResponse.success(regionService.findRegionByParentCode(parentCode));
    }

}

