package com.sxquan.manage.generator.controller;


import com.sxquan.core.entity.ServerResponse;
import com.sxquan.core.pojo.generator.GeneratorConfig;
import com.sxquan.manage.common.annotation.ControllerEndpoint;
import com.sxquan.manage.generator.service.IGeneratorConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 代码生成配置表 前端控制器
 * </p>
 *
 * @author sxquan
 * @since 2020-05-21
 */
@RestController
@Validated
@RequestMapping("/generator/config")
public class GeneratorConfigController {

    @Autowired
    IGeneratorConfigService generatorConfigService;

    @PutMapping()
    @RequiresPermissions("generator:configure:update")
    @ControllerEndpoint(operation = "修改GeneratorConfig", exceptionMessage = "修改GeneratorConfig失败")
    public ServerResponse updateGeneratorConfig(@Valid GeneratorConfig generatorConfig) {
        this.generatorConfigService.updateGeneratorConfig(generatorConfig);
        return ServerResponse.success();
    }
}

