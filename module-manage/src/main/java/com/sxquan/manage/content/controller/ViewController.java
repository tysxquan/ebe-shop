package com.sxquan.manage.content.controller;


import com.sxquan.core.pojo.content.Banner;
import com.sxquan.core.pojo.content.BannerItem;
import com.sxquan.manage.content.service.IBannerItemService;
import com.sxquan.manage.content.service.IBannerService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sxquan
 * @since 2020/5/28 16:51
 */
@RequestMapping("content")
@Controller("BannerView")
@Validated
public class ViewController {

    @Autowired
    private IBannerService bannerService;

    @Autowired
    private IBannerItemService bannerItemService;
    

    @GetMapping("banner")
    public String bannerIndex(){
        return "content/banner/banner";
    }

    @GetMapping("banner/add")
    @RequiresPermissions("banner:add")
    public String bannerAdd() {return "content/banner/bannerAdd";}

    @GetMapping("banner/update/{id}")
    @RequiresPermissions("banner:update")
    public String bannerUpdate(@PathVariable Long id, Model model) {
        Banner banner = bannerService.findBannerById(id);
        model.addAttribute("banner",banner);
        return "content/banner/bannerUpdate";
    }

    @GetMapping("banner/{bannerId}/bannerItemAdd")
    @RequiresPermissions("bannerItem:add")
    public String bannerItemAdd(@PathVariable Long bannerId, Model model) {
        model.addAttribute("bannerId",bannerId);
        return "content/banner/bannerItemAdd";
    }

    @GetMapping("banner/bannerItemUpdate/{id}")
    @RequiresPermissions("bannerItem:add")
    public String bannerItemUpdate(@PathVariable Long id, Model model) {
        BannerItem bannerItem = bannerItemService.findBannerItemById(id);
        model.addAttribute("bannerItem",bannerItem);
        return "content/banner/bannerItemUpdate";
    }

}
