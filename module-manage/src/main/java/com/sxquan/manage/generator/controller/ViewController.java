package com.sxquan.manage.generator.controller;


import com.sxquan.core.pojo.generator.GeneratorConfig;
import com.sxquan.manage.generator.service.IGeneratorConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author sxquan
 * @since 2020/5/22 0:29
 */
@Controller("generator-view")
public class ViewController {

    @Autowired
    IGeneratorConfigService generatorConfigService;

    @GetMapping("generator/configure")
    @RequiresPermissions("generator:configure:view")
    public String generatorConfigure(Model model) {
        GeneratorConfig generatorConfig = generatorConfigService.findGeneratorConfig();
        model.addAttribute("config", generatorConfig);
        return "generator/configure";
    }

    @GetMapping("generator/generate")
    @RequiresPermissions("generator:view")
    public String generator() {
        return "generator/generate";
    }
}
