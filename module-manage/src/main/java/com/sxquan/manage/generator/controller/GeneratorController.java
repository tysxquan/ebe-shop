package com.sxquan.manage.generator.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sxquan.core.entity.LayuiPage;
import com.sxquan.core.entity.RequestPage;
import com.sxquan.core.entity.ServerResponse;
import com.sxquan.core.exception.ShopException;
import com.sxquan.core.util.ShopUtil;
import com.sxquan.manage.common.annotation.ControllerEndpoint;
import com.sxquan.manage.common.constant.GeneratorConstant;
import com.sxquan.manage.common.util.FileUtil;
import com.sxquan.manage.generator.helper.GeneratorHelper;
import com.sxquan.manage.generator.pojo.Column;
import com.sxquan.manage.generator.pojo.GeneratorConfig;
import com.sxquan.manage.generator.pojo.Table;
import com.sxquan.manage.generator.service.IGeneratorConfigService;
import com.sxquan.manage.generator.service.IGeneratorService;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author sxquan
 * @since 2020/5/21 1:07
 */
@RestController
@Validated
@RequestMapping("generator")
public class GeneratorController {

    private static final String SUFFIX = "_code.zip";

    @Autowired
    private IGeneratorConfigService generatorConfigService;

    @Autowired
    private IGeneratorService generatorService;

    @Autowired
    private GeneratorHelper generatorHelper;

    @GetMapping("table/list")
    public ServerResponse getTableList(String tableName, RequestPage requestPage) {
        IPage<Table> page = generatorService.findTableList(tableName,requestPage);
        return ServerResponse.success(new LayuiPage<>(page.getRecords(),page.getTotal()));
    }


    @GetMapping("code")
    @ControllerEndpoint(exceptionMessage = "代码生成失败")
    @RequiresPermissions("generator:generate")
    public void generate(@NotBlank(message = "{required}") String name, String remark, HttpServletResponse response) throws Exception {
        GeneratorConfig generatorConfig = generatorConfigService.findGeneratorConfig();
        if (generatorConfig == null) {
            throw new ShopException("代码生成配置为空");
        }

        String className = name;
        if (GeneratorConfig.TRIM_YES.equals(generatorConfig.getIsTrim())) {
            className = RegExUtils.replaceFirst(name, generatorConfig.getTrimValue(), StringUtils.EMPTY);
        }

        generatorConfig.setTableName(name);
        generatorConfig.setClassName(ShopUtil.underscoreToCamel(className));
        generatorConfig.setTableComment(remark);

        // 生成代码到临时目录
        List<Column> columns = generatorService.getColumns(GeneratorConstant.DATABASE_NAME, name);
        generatorHelper.generateEntityFile(columns, generatorConfig);
        generatorHelper.generateMapperFile(columns, generatorConfig);
        generatorHelper.generateMapperXmlFile(columns, generatorConfig);
        generatorHelper.generateServiceFile(columns, generatorConfig);
        generatorHelper.generateServiceImplFile(columns, generatorConfig);
        generatorHelper.generateControllerFile(columns, generatorConfig);
        // 打包
        String zipFile = System.currentTimeMillis() + SUFFIX;
        FileUtil.compress(GeneratorConstant.TEMP_PATH + "src", zipFile);
        // 下载
        FileUtil.download(zipFile, name + SUFFIX, true, response);
        // 删除临时目录
        FileUtil.delete(GeneratorConstant.TEMP_PATH);
    }
}
