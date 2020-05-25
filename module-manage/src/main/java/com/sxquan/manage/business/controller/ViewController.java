package com.sxquan.manage.business.controller;


import com.sxquan.manage.business.pojo.ShopInfo;
import com.sxquan.manage.business.pojo.Sku;
import com.sxquan.manage.business.pojo.Spu;
import com.sxquan.manage.business.service.IShopInfoService;
import com.sxquan.manage.business.service.ISkuService;
import com.sxquan.manage.business.service.ISpuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description 视图映射
 * @Author sxquan
 * @since 2020/2/26 13:23
 */
@Controller("/BusinessView")
public class ViewController {

    @Autowired
    IShopInfoService shopInfoService;

    @Autowired
    ISpuService spuService;

    @Autowired
    ISkuService skuService;

    @GetMapping("business/shopInfo")
    @RequiresPermissions("shopInfo:view")
    public String businessShopInfo() {
        return "business/shop/shop";
    }

    @GetMapping("business/shopInfo/add")
    @RequiresPermissions("shopInfo:add")
    public String businessShopInfoAdd() { return "business/shop/shopAdd";}

    @GetMapping("business/shopInfo/detail/{shopInfoId}")
    @RequiresPermissions("shopInfo:view")
    public String businessShopInfoDetail(@PathVariable Long shopInfoId, Model model) {
        ShopInfo shopInfo = shopInfoService.findShopInfoByShopInfoId(shopInfoId);
        model.addAttribute("shopInfo",shopInfo);
        return "business/shop/shopDetail";
    }

    @RequiresPermissions("shopInfo:update")
    @GetMapping("business/shopInfo/update/{shopInfoId}")
    public String businessShopInfoUpdate(@PathVariable Long shopInfoId,Model model) {
        ShopInfo shopInfo = shopInfoService.findShopInfoByShopInfoId(shopInfoId);
        model.addAttribute("shopInfo",shopInfo);
        return "business/shop/shopUpdate";
    }


    @GetMapping("business/spu")
    @RequiresPermissions("spu:view")
    public String businessSpu() {
        return "business/spu/spu";
    }

    @GetMapping("business/spu/add")
    @RequiresPermissions("spu:add")
    public String businessSpuAdd() {
        return "business/spu/spuAdd";
    }

    @GetMapping("business/spu/update/{spuId}")
    @RequiresPermissions("spu:update")
    public String businessSpuUpdate(@PathVariable Long spuId,Model model) {
        Spu spu = spuService.findSpuBySpuId(spuId);
        model.addAttribute("spu",spu);
        return "business/spu/spuUpdate";
    }

    @GetMapping("business/sku")
    @RequiresPermissions("sku:view")
    public String businessSku() {
        return "business/sku/sku";
    }

    @GetMapping("business/sku/add")
    @RequiresPermissions("sku:add")
    public String businessSkuAdd() {
        return "business/sku/skuAdd";
    }

    @GetMapping("business/sku/update/{skuId}")
    @RequiresPermissions("sku:update")
    public String businessSkuUpdate(@PathVariable Long skuId,Model model) {
        Sku sku = skuService.findSkuBySkuId(skuId);
        model.addAttribute("sku",sku);
        return "business/sku/skuUpdate";
    }

}
